package entities;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {
	

	private Integer personid;
	private SimpleStringProperty username;
	private SimpleStringProperty password;
	private SimpleBooleanProperty admin;
	private SimpleStringProperty secretquestion;
	private SimpleStringProperty secretanswer;
	private SimpleStringProperty email;
	
	
	public User(Integer personid, String username, String password, Boolean admin, String secretquestion,
			String secretanswer, String email) {
		super();
		this.personid = personid;
		this.username = new SimpleStringProperty(username);
		this.password = new SimpleStringProperty(password);
		this.admin = new SimpleBooleanProperty(admin);
		this.secretquestion = new SimpleStringProperty(secretquestion);
		this.secretanswer = new SimpleStringProperty(secretanswer);
		this.email = new SimpleStringProperty(email);
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