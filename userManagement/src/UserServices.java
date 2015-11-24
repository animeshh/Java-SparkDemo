/* This class acts as wrapper to UserData class which before calling check if variables are valid or not.
 * It gets Request from UserController  class for executing specific service.
 * 
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServices {
	
	//for storing data as collection of objects
	private Map<String, UserData> userMap = new HashMap<>();
   
	

	public UserData createUser(String Id, String firstName, String middleName, String lastName, String age, 
							   String gender, String phone, String zip) {
		UserData user = userMap.get(Id);
		if (user != null) {
			throw new IllegalArgumentException("User already Present");
		}
		chkValidity(firstName, middleName, lastName, age, gender, phone);
		user = new UserData(Id,firstName, middleName, lastName, age, 
									gender, phone, zip);
		userMap.put(Id, user);
		return user;
	}

	public UserData updateUser(String Id, String firstName, String middleName, String lastName, String age, 
                               String gender, String phone, String zip) {
		UserData user = userMap.get(Id);
		chkValidity(firstName, middleName, lastName, age, gender,phone);
		user.setFirstName(firstName);
		user.setMiddleName(middleName);
		user.setLastName(lastName);
		user.setAge(age);
		user.setGender(gender);
		user.setPhone(phone);
		user.setZip(zip);
		
		return user;
	}
	
	public List<UserData> getAllUsers() {
		return new ArrayList<>(userMap.values());
	}

	public UserData getUserData(String Id) {
		return userMap.get(Id);
	}
	//internal to this class only
	private void chkValidity(String firstName, String middleName, String lastName, String age, 
            String gender, String phone) {
		if (firstName == null || firstName.isEmpty()|| !firstName.matches("[a-zA-Z]+")) {
			throw new IllegalArgumentException("Invalid 'FirstName' ");
		}
				
		if(middleName!=null && !middleName.matches("[a-zA-Z]+")){
			throw new IllegalArgumentException("Invalid 'MiddleName' ");
		}
		
		if (lastName == null || lastName.isEmpty()|| !lastName.matches("[a-zA-Z]+")) {
			throw new IllegalArgumentException("Invalid 'LastName' ");
		}
		
		if(gender==null || gender.length()!=1 || (gender.charAt(0)!='M' &&gender.charAt(0)!='F') ){
			throw new IllegalArgumentException("Invalid 'Gender' ");
		}
		
		if(age==null || !age.matches("[0-9]+")|| Integer.valueOf(age)==0){
			throw new IllegalArgumentException("Invalid 'Age' ");
		}
		if(phone==null || phone.length()!=10 || !phone.matches("[0-9]+") ){
			throw new IllegalArgumentException("Invalid 'Phone No.' ");
		}
	
	}
}