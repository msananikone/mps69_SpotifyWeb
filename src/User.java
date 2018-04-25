

import java.util.UUID;

public class User {
	private String userID;
	private String userName;
	private String password;
	
	public User(String userName, String password){
		this.userName = userName;
		this.password = password;
		this.userID = UUID.randomUUID().toString();
	}
	
	@Override
	public String toString(){
		return "User name: " + this.userName + "; Password: " + password; 
	}
}
