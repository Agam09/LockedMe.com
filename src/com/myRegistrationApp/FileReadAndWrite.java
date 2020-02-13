package com.myRegistrationApp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FileReadAndWrite {

	public HashMap<String, String> readDataFromFile(){

		HashMap<String, String> ldapContent = new HashMap<String, String>();
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("database.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (String key : properties.stringPropertyNames()) {
			ldapContent.put(key, properties.get(key).toString());
		}

		return ldapContent;
	}
	
	public HashMap<String, String> readCredentialsFromFile(){

		HashMap<String, String> ldapContent = new HashMap<String, String>();
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("locker-file.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (String key : properties.stringPropertyNames()) {
			ldapContent.put(key, properties.get(key).toString());
		}

		return ldapContent;
	}

	public boolean writeDataToFile(String username, String password) {
		HashMap<String, String> ldapContent = this.readDataFromFile();
		ldapContent.put(username.toUpperCase(),password);
		Properties properties = new Properties();
		for (Map.Entry<String,String> entry : ldapContent.entrySet()) {
			properties.put(entry.getKey(),entry.getValue());
		}
		try(OutputStream  output = new FileOutputStream("database.txt")){
			try {
				properties.store(output, null);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean writeCredentialsToFile(String username, String siteUrl, String siteUsername, String sitePassword) {
		HashMap<String, String> ldapContent = this.readCredentialsFromFile();
		ldapContent.put(username.toUpperCase()+":::"+siteUrl.toUpperCase(),siteUrl+":::"+siteUsername+":::"+sitePassword);
		Properties properties = new Properties();
		for (Map.Entry<String,String> entry : ldapContent.entrySet()) {
			properties.put(entry.getKey(),entry.getValue());
		}
		try(OutputStream  output = new FileOutputStream("locker-file.txt")){
			try {
				properties.store(output, null);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
	}
	

}
