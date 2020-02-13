package com.myRegistrationApp;

import java.util.HashMap;

public class Url {
	public static FileReadAndWrite f = new FileReadAndWrite();

	public boolean checkUrlExists(String siteUrl,String username) {
		HashMap<String,String> siteCredentialsMap = f.readCredentialsFromFile();
		return siteCredentialsMap.containsKey(username.toUpperCase()+":::"+siteUrl.toUpperCase());
	}

	public String fetchCredentials(String siteUrl,String username) {
		HashMap<String,String> siteCredentialsMap = f.readCredentialsFromFile();
		String siteCredentials = siteCredentialsMap.get(username.toUpperCase()+":::"+siteUrl.toUpperCase());
		return "\nSite URL: "+ siteCredentials.split(":::")[0] +
				"\nSite Username: "+ siteCredentials.split(":::")[1] +
				"\nSite Password: "+ siteCredentials.split(":::")[2];
	}

	public boolean saveCredentials(String username,String siteUrl, String siteUsername, String sitePassword) {
			return f.writeCredentialsToFile(username, siteUrl,  siteUsername,  sitePassword);
	}

}
