package giis.demo.tkrun.controllers.entities;

public class UserEntity {

	private String userName;
	private String type;  //If the user is an editor, author or reviewer.

	public UserEntity(String userName, String type) {
		super();
		this.userName = userName;
		this.type = type;
	}

	public UserEntity() {}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return userName + " - " + type;
	}

	

}
