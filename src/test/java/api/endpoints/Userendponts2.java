package api.endpoints;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import io.restassured.response.Response;
import api.payload.User;
//created for perform crud operations
public class Userendponts2 {
	//method created for getting the URL's from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes");//load properties file
		return routes;
	}
	
	public static Response createUser(User payload)
	{
		String post_url=getURL().getString("post_url");
		Response res = given()
			.contentType(ContentType.JSON)
			.accept("application/json")
			.body(payload)
		.when()
			.post(post_url);
		
		return res;
	}
	
	public static Response readUser(String userName)
	{
		String get_url=getURL().getString("get_url");

		Response res = given()
			.pathParam("username", userName)
		.when()
			.get(get_url);
		
		return res;
	}
	
	public static Response updateUser(String userName,User payload)
	{
		
		String update_url=getURL().getString("update_url");

		Response res = given()
			.contentType(ContentType.JSON)
			.accept("application/json")
			.pathParam("username", userName)
			.body(payload)
		.when()
			.put(update_url);
		
		return res;
	}
	
	public static Response deleteUser(String userName)
	{
		String delete_url=getURL().getString("delete_url");

		Response res = given()
				.pathParam("username", userName)
			.when()
				.delete(delete_url);
			
			return res;
	}
	
}
