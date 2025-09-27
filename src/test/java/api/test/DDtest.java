package api.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.Userendponts;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDtest {
	
	@Test(priority=1,dataProvider = "Data", dataProviderClass=DataProviders.class)
	public void testpostuser(String UserID, String UserName, String FirstName, String LastName, String Email, String Password, String Phone )
	{
		User userpayload = new User();
		userpayload.setId(Integer.parseInt(UserID));
		userpayload.setUsername(UserName);
		userpayload.setLastName(LastName);
		userpayload.setUsername(UserName);
		userpayload.setEmail(Email);
		userpayload.setPassword(Password);
		userpayload.setPhone(Phone);
		
		Response response = Userendponts.createUser(userpayload);
		//response.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
	@Test(priority=2,dataProvider = "username", dataProviderClass=DataProviders.class)
	public void testdeleteuser(String UserName)
	{
		Response response = Userendponts.deleteUser(UserName);
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
	

}
