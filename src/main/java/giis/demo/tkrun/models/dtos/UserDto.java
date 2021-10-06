package giis.demo.tkrun.models.dtos;

public class UserDto {

	private String userName;
	private String type;  //If the user is an editor, author or reviewer.
	
	public UserDto(String userName, String type) {
		super();
		this.userName = userName;
		this.type = type;
	}
	
	public UserDto() {}

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
	
}
