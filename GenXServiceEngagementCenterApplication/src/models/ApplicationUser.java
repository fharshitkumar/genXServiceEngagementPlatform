package models;

import entities.User;
/**
 * Class		: ApplicationUSer		
 * Description	: Allows the application components to access who is the current user logged In
 *                 
 *
 *	.....................................................................
 *	....+-----------------------------------------------------------+....						
 *	....|            ApplicationUser Class                          |....						
 *	....|             << concrete class >>                          |....	                    
 *	....+-----------------------------------------------------------+....						
 *	....| [-] applicationUser           : USer  <<static>>          |....												
 *	....+-----------------------------------------------------------+....						
 *	....| [+] DatabaseConnector()                                   |....						
 *	....| [+] Connection: DBConnectionManager()                     |....							
 *	....+-----------------------------------------------------------+....						
 *	.....................................................................																				
 * 																								
 * @author HARSHIT KUMAR 			
 * Date			    : 28 November, 2017
 * Source File name	: ApplicationUser.java       	
 * 
 *
*/

public class ApplicationUser {

	private static User applicationUser = null;
	
	public ApplicationUser(User guest) {
		applicationUser = guest;
		System.out.println("Welcome "+ applicationUser.getPersonid());
	}
	
	
	/*********************************************************************
	 * GETTER : TO GET THE CURRENT USER WHO IS LOGGED IN
	 * @return the applicationUser
	 */
	public static User getApplicationUser() {
		return applicationUser;
	}
	/********************************************************************
	 * SETTER: TO SET THE CURRENT USER WHO IS LOGGED IN
	 * @param applicationUser the applicationUser to set
	 */
	public static void setApplicationUser(User applicationUser) {
		ApplicationUser.applicationUser = applicationUser;
	}
	
	
	
}
