package com.myRegistrationApp;

import java.util.HashMap;

public class RegisterAndLogin {
	public static FileReadAndWrite f = new FileReadAndWrite();
	
	public boolean userNameExists(String username) {
		HashMap<String, String> ldapContent = f.readDataFromFile();
		return ldapContent.containsKey(username.toUpperCase());
	}
	
	public boolean authenticate(String username, String password) {
		HashMap<String, String> ldapContent = f.readDataFromFile();
		if(ldapContent.containsKey(username.toUpperCase())) {
			String pass = ldapContent.get(username.toUpperCase());
			return pass.equals(password);
		}
		else {
			return false;
		}
	}
	
	public boolean registration(String username, String password) {
		
		if(!userNameExists(username)) {
			return f.writeDataToFile(username, password);
		}
		else {
			return false;
		}
		
	}

}
