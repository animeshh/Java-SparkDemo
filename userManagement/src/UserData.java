/* This is the UserData class which actually stores data by implementing Serializable Interface, 
 * and it stores various information of user in form of ArrayList, which later on mapped to Unique ID
 * and store as Key,Value pair using HashMap in UserServices class.
 * 
 */

import java.io.Serializable;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;

public class UserData implements Serializable{
	 
    private static final long serialVersionUID = 1L;
 
    private static List<UserData> users = new ArrayList<UserData>();
    
    private String firstName;   //(alphabets)

    private String middleName;  //(alphabets but it is optional)

    private String lastName;   //(alphabets)

    private Integer age;      // (valid non zero positive number)

    private Character gender; // (M or F)

    private String phone;    //(10-digit positive number)

    private String zip;     //(optional field);
 
    public UserData(String Id, String name, String middleName, String lastName, String age, 
    		        String gender, String phone, String zip) {
        
    	this.firstName  = name;
        this.middleName = middleName;
        this.lastName   = lastName;
        this.age        = Integer.valueOf(age);
        this.gender     = gender.charAt(0);
        this.phone      = phone;
        this.zip        = zip;	
    }
 
    public UserData() {}
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setMiddleName(String middleName){
    	this.middleName = middleName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setAge(String age) {
        this.age = Integer.valueOf(age);
    }
    public void setGender(String gender) {
        this.gender = gender.charAt(0);
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void setZip(String zip) {
        this.zip = zip;
    }
 
    public static List<UserData> getAll(){
        return users;
 
    }
}

