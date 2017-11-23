package models;

import entities.User;

public class ApplicationUser {

	public static User applicationUser = null;
	public ApplicationUser(User guest) {
		applicationUser = guest;
		System.out.println("Welcome "+ applicationUser.getPersonid());
	}
	
	
}
