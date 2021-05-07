package nLayeredDemo.entities.concretes;

import nLayeredDemo.entities.abstracts.Entity;

public class User implements Entity {
	private int	id;
	private String firstName;
	private String lastName;
	private String mail;
	private String password;
	private boolean accountActivated;
	
	public User(String mail, String password) {
		this.mail = mail;
		this.password = password;
	}
	
	public User(String firstName, String lastName, String mail, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAccountActivated() {
		return accountActivated;
	}

	public void setAccountActivated(boolean accountActivated) {
		this.accountActivated = accountActivated;
	}
}
