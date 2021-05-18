package model.people;

import java.io.Serializable;

public abstract class User implements Serializable {

	private String userID, userFName, userLName, userPIN;

	public User(String userID, String userPIN, String userFName, String userLName) {
		this.userID = userID;
		this.userPIN = userPIN;
		this.userFName = userFName;
		this.userLName = userLName;
	}

	public String getUserID() {
		return userID;
	}

	public String getUserPIN() {
		return userPIN;
	}

	public String getUserFName() {
		return userFName;
	}

	public String getUserLName() {
		return userLName;
	}

}
