package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    private String path;

    public ExcelUtility(String path) {
        this.path = path;
    }

    // Get total row count (including header)
    public int getRowCount(String sheetname) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {
            XSSFSheet sheet = workbook.getSheet(sheetname);
            return (sheet == null) ? 0 : sheet.getPhysicalNumberOfRows();
        }
    }

    // Get total cell count in a row
    public int getCellCount(String sheetname, int rownum) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {
            XSSFSheet sheet = workbook.getSheet(sheetname);
            if (sheet == null) return 0;
            XSSFRow row = sheet.getRow(rownum);
            return (row == null) ? 0 : row.getLastCellNum();
        }
    }

    // Get cell data
    public String getCellData(String sheetname, int rownum, int colnum) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {
            XSSFSheet sheet = workbook.getSheet(sheetname);
            if (sheet == null) return "";
            XSSFRow row = sheet.getRow(rownum);
            if (row == null) return "";
            XSSFCell cell = row.getCell(colnum);
            if (cell == null) return "";
            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);
        }
    }

    // Set cell data
    public void setCellData(String sheetname, int rownum, int colnum, String data) throws IOException {
        File xlfile = new File(path);
        if (!xlfile.exists()) {
            try (XSSFWorkbook workbook = new XSSFWorkbook();
                 FileOutputStream fo = new FileOutputStream(path)) {
                workbook.write(fo);
            }
        }

        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {

            XSSFSheet sheet = workbook.getSheet(sheetname);
            if (sheet == null) sheet = workbook.createSheet(sheetname);

            XSSFRow row = sheet.getRow(rownum);
            if (row == null) row = sheet.createRow(rownum);

            XSSFCell cell = row.getCell(colnum);
            if (cell == null) cell = row.createCell(colnum);

            cell.setCellValue(data);

            try (FileOutputStream fo = new FileOutputStream(path)) {
                workbook.write(fo);
            }
        }
    }

    // Fill green color
    public void fillGreenColor(String sheetname, int rownum, int colnum) throws IOException {
        setCellColor(sheetname, rownum, colnum, IndexedColors.GREEN);
    }

    // Fill red color
    public void fillRedColor(String sheetname, int rownum, int colnum) throws IOException {
        setCellColor(sheetname, rownum, colnum, IndexedColors.RED);
    }

    // Helper: set cell color
    private void setCellColor(String sheetname, int rownum, int colnum, IndexedColors color) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {

            XSSFSheet sheet = workbook.getSheet(sheetname);
            if (sheet == null) return;

            XSSFRow row = sheet.getRow(rownum);
            if (row == null) return;

            XSSFCell cell = row.getCell(colnum);
            if (cell == null) return;

            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(color.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            cell.setCellStyle(style);

            try (FileOutputStream fo = new FileOutputStream(path)) {
                workbook.write(fo);
            }
        }
    }
}
