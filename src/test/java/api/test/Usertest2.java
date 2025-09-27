package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.Userendponts;
import api.endpoints.Userendponts2;
import api.payload.User;
import io.restassured.response.Response;

public class Usertest2 {
	
	Faker faker;
	User userpayload;
	public Logger logger;
	@BeforeClass
	public void setupDate()
	{
		faker = new Faker();
		userpayload = new User();
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5, 10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		
		logger = LogManager.getLogger(this.getClass());
		logger.debug("debugging");
	}
	@Test(priority=1)
	void testPostUser()
	{
		logger.info("Creating User");
		Response response = Userendponts2.createUser(userpayload);
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		logger.info("Created User");

	}
	@Test(priority=2)
	void getuser()
	{
		logger.info("reading User");

		Response res = Userendponts2.readUser(this.userpayload.getUsername());
		res.then().log().all();
		AssertJUnit.assertEquals(res.getStatusCode(), 200);
		logger.info("reading User completed");

	}
	@Test(priority=3)
	void updateuser()
	{
		//update the data using the payload
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		
		
		logger.info("updating User");

		Response res = Userendponts2.updateUser(this.userpayload.getUsername(), userpayload);
		res.then().log().body();
		
		AssertJUnit.assertEquals(res.getStatusCode(), 200);
		
		// checking data after updating
		
		Response response = Userendponts2.updateUser(this.userpayload.getUsername(), userpayload);
		response.then().log().body();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		logger.info("updated User");

	}
	@Test(priority=4)
	void testdelete()
	{
		logger.info("deleting User");

		Response res = Userendponts2.deleteUser(this.userpayload.getUsername());
		res.then().log().body().statusCode(200);
		logger.info("deleted User");

		
	}
}
