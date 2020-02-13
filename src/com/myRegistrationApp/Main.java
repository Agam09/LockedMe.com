package com.myRegistrationApp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class Main {



	//Entry Point
	public static void main(String[] args) throws FileNotFoundException, IOException {
		RegisterAndLogin r = new RegisterAndLogin();
		Url u = new Url();
		String username = "";
		String password = "";
		String siteUrl = "";
		String siteUsername = "";
		String sitePassword = "";
		//Display Welcome message
		System.out.println("==========================================");
		System.out.println("========== Welcome to lockedMe! ==========");
		System.out.println("===========================================");
		//System.out.print("Press \"1\" for login or \"2\" for registration:");
		Scanner sc = new Scanner(System.in);
		int x=0;//sc.nextInt();
		boolean result=true;
		while(x!=1 && x!=2) {
			System.out.print("\n"+"Press \"1\" for login or \"2\" for registration:");
			x = sc.nextInt();
		}
		while(result) {
			System.out.println("Please enter the following details:");
			System.out.print("Please enter username:");
			username = sc.next();
			System.out.print("Please enter password:");
			password = sc.next();
			//System.out.println(username+", "+ password);

			if(x==1) {
				if(r.authenticate(username, password)) {
					result=false;
					System.out.println("\n"+" User login successful ..!!!");
				}
				else {
					System.out.println("User login failed");
					System.out.print("Press \"1\" for login or \"2\" for registration:");
					x = sc.nextInt();
				}
			}
			else {
				if(r.userNameExists(username)) {
					System.out.println("Username \"" +username+ "\" already exists");
				}
				else if(r.registration(username, password)) {
					result=false;
					System.out.println("User registration was successfull");
				}
				else {
					System.out.println("User registration failed");
				}
			}
		}
		while(true) {
			if(!result) {
				//System.out.println("Please enter 1 to retrieve credentials and 2 to store credentials");
				int y=0;
				while(y!=1 && y!=2 && y!=3) {
					System.out.print("\n"+"Press \"1\" for retrieve credentials or \"2\" to store credentials and \"3\" to Exit:");
					y = sc.nextInt();
				}
				/*
				 * System.out.println("Please enter the following details:");
				 * System.out.print("Please enter site url:"); siteUrl = sc.next();
				 */
				if(y==1) {
					System.out.println("\n"+"Please enter the following details:");
					System.out.print("Please enter site url:");
					siteUrl = sc.next();
					if(u.checkUrlExists(siteUrl,username)) {
						System.out.println("\n"+"Credentials are:"+ u.fetchCredentials(siteUrl,username));
					}
					else {
						System.out.println("\n"+"Credentials not found for the given site url.");
					}
				}
				else if(y==2) {
					System.out.println("\n"+"Please enter the following details:");
					System.out.print("Please enter site url:");
					siteUrl = sc.next();
					System.out.print("Please enter site username:");
					siteUsername = sc.next();
					System.out.print("Please enter site password:");
					sitePassword = sc.next();
					if(u.saveCredentials(username,siteUrl,siteUsername,sitePassword)) {
						System.out.println("\n"+"Credentails saved successfully...!!!");
					}
					else {
						System.out.println("\n"+"Credentials could not be saved..!!!");
					}
				}
				else {
					System.out.println("\n"+"You have successfully Exited !!!");
					break;
				}
			}


		}

	}

}
