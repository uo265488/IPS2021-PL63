package giis.demo.tkrun.controllers.entities;

public class UserEntity {

	private String userName;
	private String type;  //If the user is an editor, author or reviewer.
	private int idUser;

	public UserEntity(String userName, String type, int idUser) {
		super();
		this.userName = userName;
		this.type = type;
		this.idUser = idUser;
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

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	@Override
	public String toString() {
		return userName + " - " + type;
	}

	

}
