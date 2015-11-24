/*  This class is used for Initializing, Testing, and Stopping Spark Server/Services, 
 *   It uses JUnit APIs to valid various conditions and test cases. 
 */

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

import spark.Spark;
import spark.utils.IOUtils;


public class UserTest {

	@BeforeClass
	public static void beforeClass() {
		new UserController(new UserServices());	
	}

	@Test
	public void TestService() {
		String firstName = "animesh";   // alphabets
	    String middleName = null;       //(alphabets but it is optional)
	    String lastName   = "gupta" ;   //(alphabets)
	    Integer   age       = 26;        // (valid non zero positive number)
	    Character gender  =  'M';     // (M or F)
	    String phone     =  "6319743020";   //(10-digit positive number)
	    String zip = "11790";	    
	    String path = "?firstName="+ firstName +"&middleName=" + middleName + "&lastName="+ lastName + 
	    			  "&age=" + age + "&gender=" + gender + "&phone="  + phone + "&zip=" + zip;
	    
	    //Testing POST-CREATE SERVICE
	    String method = "POST";	    
	    
        TestOutput res = request(method, "/users/1"+path);    //Unique ID, either given by User or generated Uniquely using UID API. 
	    System.out.println("Following data stored for USER with UniqueID 1:"+res.body);
		
	    res = request(method, "/users/2"+ path);
	    //System.out.println(res.body);
		
	    res = request(method, "/users/3"+path);
		//System.out.println(res.body);
		
		res = request(method, "/users/4"+path);
		//System.out.println(res.body);
		
		//Testing GET-DISPLAY SERVICE
	    method = "GET";
	    res = request(method, "/users");
		System.out.println("Display all users:"+ res.body);
		
		//Testing PUT-UPDATE SERVICE
	  	method = "PUT";
		age = 20;
		firstName = "changeName";
		res = request(method, "/users/4"+ "?firstName="+ firstName +"&middleName=" + middleName + "&lastName="+ lastName + 
  			  "&age=" + age + "&gender=" + gender + "&phone="  + phone + "&zip=" + zip);
		assertEquals(firstName, new Gson().fromJson(res.body, HashMap.class).get(firstName));
		System.out.println("Data Successfully changed for User with ID 4:"+ res.body);		
		assertEquals(10, new Gson().fromJson(res.body, HashMap.class).get(age));
		
	}
	
	private TestOutput request(String method, String query) {
		try {
			URL url = new URL("http://localhost:4567" + query);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);
			connection.connect();
			String body = IOUtils.toString(connection.getInputStream());
			return new TestOutput(connection.getResponseCode(), body);
		} 
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static class TestOutput {

		public final String body;
		public final int method;

		public TestOutput(int method, String body) {
			this.body = body;
			this.method = method;
		}
	}	
	@AfterClass
	public static void afterClass() {
		Spark.stop();
	}
}