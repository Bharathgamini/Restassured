package api.endpoints;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import api.payload.User;
//created for perform crud operations
public class Userendponts {
	
	public static Response createUser(User payload)
	{
		Response res = given()
			.contentType(ContentType.JSON)
			.accept("application/json")
			.body(payload)
		.when()
			.post(Routes.post_url);
		
		return res;
	}
	
	public static Response readUser(String userName)
	{
		Response res = given()
			.pathParam("username", userName)
		.when()
			.get(Routes.get_url);
		
		return res;
	}
	
	public static Response updateUser(String userName,User payload)
	{
		Response res = given()
			.contentType(ContentType.JSON)
			.accept("application/json")
			.pathParam("username", userName)
			.body(payload)
		.when()
			.put(Routes.update_url);
		
		return res;
	}
	
	public static Response deleteUser(String userName)
	{
		Response res = given()
				.pathParam("username", userName)
			.when()
				.delete(Routes.delete_url);
			
			return res;
	}
	
}
