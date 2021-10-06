package giis.demo.tkrun.models.user;

import java.util.List;

import giis.demo.tkrun.models.dtos.UserDto;
import giis.demo.util.Database;

public class UserModel {
	
	private Database db = new Database();
	
	public List<UserDto> getUsers() {
		String query = "select * from user";
		
		return db.executeQueryPojo(UserDto.class, query);
	}

}
