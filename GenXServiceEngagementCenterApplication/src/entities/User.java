package entities;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Class		: User		
 * Description	: User Entity
 * This is users entity and we can maintain/retrieve a users login details.											
 * @author HARSHIT KUMAR 			
 * Date			    : 30 November, 2017
 * Source File name	: User.java       	
 * 
 *
*/

public class User {
	
	private Integer personid;
	private SimpleStringProperty username;
	private SimpleStringProperty password;
	private SimpleBooleanProperty admin;
	private SimpleStringProperty secretquestion;
	private SimpleStringProperty secretanswer;
	private SimpleStringProperty email;
	private Integer roleid;
	
	public User(Integer personid, String username, String password, Boolean admin, String secretquestion,
			String secretanswer, String email,Integer roleid) {
		super();
		this.personid = personid;
		this.username = new SimpleStringProperty(username);
		this.password = new SimpleStringProperty(password);
		this.admin = new SimpleBooleanProperty(admin);
		this.secretquestion = new SimpleStringProperty(secretquestion);
		this.secretanswer = new SimpleStringProperty(secretanswer);
		this.email = new SimpleStringProperty(email);
		this.roleid = roleid;
	}




	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	
	public Integer getPersonid() {
		return personid;
	}
	public void setPersonid(Integer personid) {
		this.personid = personid;
	}




	public String getUsername() {
		return username.get();
	}
	public void setUserName(String username) {
		this.username = new SimpleStringProperty(username);
	}


	public String getPassword() {
		return password.get();
	}
	public void setPassword(String password) {
		this.password = new SimpleStringProperty(password);
	}


	public Boolean getAdmin() {
		return admin.getValue();
	}
	public void setAdmin(Boolean admin) {
		this.admin = new SimpleBooleanProperty(admin);
	}


	public String getSecretquestion() {
		return secretquestion.get();
	}
	public void setSecretquestion(String secretquestion) {
		this.secretquestion = new SimpleStringProperty(secretquestion);
	}


	public String getSecretanswer() {
		return secretanswer.get();
	}
	public void setSecretanswer(String secretanswer) {
		this.secretanswer = new SimpleStringProperty(secretanswer);
	}


	public String getEmail() {
		return email.get();
	}
	public void setEmail(String email) {
		this.email = new SimpleStringProperty(email);
	}

}