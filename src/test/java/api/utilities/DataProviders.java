package api.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	// DataProvider 1: All data from Sheet1 (multi-column)
	@DataProvider(name="Data", parallel=false)
	public String[][] getAllData() throws IOException {
		String path = "./testdata/userlist.xlsx";
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int totalRows = xlutil.getRowCount("Sheet1");
		int totalCols = xlutil.getCellCount("Sheet1", 0); // header row defines columns
		
		List<String[]> dataList = new ArrayList<>();
		
		// start from row 1 (skip header row)
		for (int i = 1; i < totalRows; i++) {
			boolean isRowEmpty = true;
			String[] rowData = new String[totalCols];
			
			for (int j = 0; j < totalCols; j++) {
				String cellData = xlutil.getCellData("Sheet1", i, j);
				rowData[j] = cellData;
				
				if (!cellData.isEmpty()) {
					isRowEmpty = false;
				}
			}
			
			// add only non-empty rows
			if (!isRowEmpty) {
				dataList.add(rowData);
			}
		}
		
		return dataList.toArray(new String[0][0]);
	}
	
	
	// DataProvider 2: Only usernames column
	@DataProvider(name="username", parallel=false)
	public String[] getUsernames() throws IOException {
		String path = "./testdata/userlist.xlsx";
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int totalRows = xlutil.getRowCount("Sheet1");
		
		List<String> usernames = new ArrayList<>();
		
		// start from row 1 (skip header row)
		for (int i = 1; i < totalRows; i++) {
			String cellData = xlutil.getCellData("Sheet1", i, 1); // Username column = 0
			if (!cellData.isEmpty()) {
				usernames.add(cellData);
			}
		}
		
		return usernames.toArray(new String[0]);
	}
}