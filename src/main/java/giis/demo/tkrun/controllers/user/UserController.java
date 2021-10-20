package giis.demo.tkrun.controllers.user;

import java.util.List;

import giis.demo.tkrun.controllers.entities.UserEntity;
import giis.demo.tkrun.models.user.UserModel;
import giis.demo.tkrun.views.user.UserView;
import giis.demo.util.EntityAssembler;

public class UserController {

	private UserModel userModel;
	private UserView userView;
	
	public UserController(UserModel m, UserView v) {
		this.userModel = m;
		this.userView = v;
		
		this.initView();
	}
	
	public UserController() {
		this.userModel = new UserModel();
		this.initView();
	}
	
	private void initView() {
		this.userView = new UserView(this);
		userView.setVisible(true);
	}
	
	public List<UserEntity> getUsers(){
		return EntityAssembler.toUserEntityList(userModel.getUsers());
	}
}
