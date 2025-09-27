package api.endpoints;

/*swagger
 * swagger URL: https://petstore.swagger.io
 * 
 * create user : https://petstore.swagger.io/v2/user
 * get user: https://petstore.swagger.io/v2/user/{username}
 * update user: https://petstore.swagger.io/v2/user/{username}
 * delete user: https://petstore.swagger.io/v2/user/{username}
 * 
 */



public class Routes {
	// if I user the static then i can access this varible anywhere withoutcreating the object for the class
	
	public static String base_url = "https://petstore.swagger.io/v2";
	
	// user modal
	
	public static String post_url = base_url+"/user";
	
	public static String get_url = base_url+"/user/{username}";

	public static String update_url = base_url+"/user/{username}";
	
	public static String delete_url = base_url+"/user/{username}";
	
	// store module
	
		// here you will create store module URL's
	// pet module
		// here you will create pet module URL

	

}
