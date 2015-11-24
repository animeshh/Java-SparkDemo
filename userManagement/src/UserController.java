/* This class sends JSON data and Service Name from UserTest class to UserServices Class, 
 * and calls directly UserServices based on Spark methods.
 * 
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import spark.Spark;

public class UserController
{
 
    private static Gson GSON = new GsonBuilder().create();
 
    
    public UserController(final UserServices srv)
    {
    	//createUser
        Spark.post("/users/:id",  (request, response) -> {
        	UserData newUser = null;
            //parsing JSON input
            newUser = srv.createUser(request.params(":id"), request.queryParams("firstName"),request.queryParams("middleName"),
            			request.queryParams("lastName"),request.queryParams("age"),request.queryParams("gender"),
            			request.queryParams("phone"),request.queryParams("zip")        			
            			);
            return GSON.toJson(newUser);
            
        });
        
        //Update user
        Spark.put("/users/:id",  (request, response) -> {
            
            String Id = request.params(":id");
            if(srv.getUserData(Id) == null){
            	response.status(404);
                return "User Not Found";
            }
            //parsing JSON input
            UserData user = srv.updateUser(Id, request.queryParams("firstName"),request.queryParams("middleName"),
            request.queryParams("lastName"),request.queryParams("age"),request.queryParams("gender"),
            request.queryParams("phone"),request.queryParams("zip"));
            //Converting Java obj to JSON
            return GSON.toJson(user);
                
        });
 
        //List All users
        Spark.get("/users",  (request, response) -> {
            return GSON.toJson(srv.getAllUsers());
        });
    }
}

