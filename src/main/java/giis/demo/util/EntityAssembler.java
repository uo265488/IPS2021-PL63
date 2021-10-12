package giis.demo.util;

import java.util.ArrayList;
import java.util.List;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisorEntity;
import giis.demo.tkrun.controllers.entities.UserEntity;
import giis.demo.tkrun.models.dtos.ArticuloDto;
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

	public static List<ArticuloEntity> toArticuloEntityList(List<ArticuloDto> articulosDto){
		List<ArticuloEntity> articulosEntity = new ArrayList<>();
		for(ArticuloDto articulo : articulosDto) {
			articulosEntity.add(toArticuloEntity(articulo));
		}
		
		return articulosEntity;
	}
	
	private static ArticuloEntity toArticuloEntity(ArticuloDto articuloDto) {
		ArticuloEntity articuloEntity = new ArticuloEntity();
		
		articuloEntity.setTitulo(articuloDto.getTitulo());
		articuloEntity.setResumen(articuloDto.getResumen());
		articuloEntity.setPrimerAutor(articuloDto.getPrimerAutor());
		articuloEntity.setPalabrasClave(articuloDto.getPalabrasClave());
		articuloEntity.setOtrosAutores(articuloDto.getOtrosAutores());
		articuloEntity.setId(articuloDto.getId());
		articuloEntity.setFirma(articuloDto.getFirma());
		articuloEntity.setFicheroFuente(articuloDto.getFicheroFuente());
		articuloEntity.setEstado(articuloDto.getEstado());
		articuloEntity.setCVAutor(articuloDto.getCVAutor());
		articuloEntity.setCartaPresentacion(articuloDto.getCartaPresentacion());
		
		return articuloEntity;
	}
}
