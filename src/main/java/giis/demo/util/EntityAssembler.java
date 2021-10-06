package giis.demo.util;

import java.util.ArrayList;
import java.util.List;

import giis.demo.tkrun.controllers.entities.RevisorEntity;
import giis.demo.tkrun.controllers.entities.UserEntity;
import giis.demo.tkrun.models.dtos.RevisorDto;
import giis.demo.tkrun.models.dtos.UserDto;

public class EntityAssembler {

	
	public static List<RevisorEntity> toRevisorEntityList(List<RevisorDto> revisores) {
		List<RevisorEntity> ents = new ArrayList<>();
		for (RevisorDto rev : revisores) {
			ents.add(toRevisorEntity(rev));
		}
		return ents;
	}

	private static RevisorEntity toRevisorEntity(RevisorDto rev) {
		RevisorEntity ent = new RevisorEntity();
		
		ent.setEstado(rev.getEstado());
		ent.setNombre(rev.getNombre());
		ent.setId(rev.getId());
		
		
		return ent;
	}
	
	public static List<UserEntity> toUserEntityList(List<UserDto> usersDto){
		List<UserEntity> usersEntity = new ArrayList<>();
		for(UserDto user : usersDto) {
			usersEntity.add(toUserEntity(user));
		}
		
		return usersEntity;
	}
	
	private static UserEntity toUserEntity(UserDto userDto) {
		UserEntity userEntity = new UserEntity();
		
		userEntity.setUserName(userDto.getUserName());
		userEntity.setType(userDto.getType());
		
		return userEntity;
	}

}
