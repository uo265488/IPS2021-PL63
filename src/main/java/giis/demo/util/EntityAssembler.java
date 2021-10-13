package giis.demo.util;

import java.util.ArrayList;
import java.util.List;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisionEntity;
import giis.demo.tkrun.controllers.entities.RevisorEntity;
import giis.demo.tkrun.controllers.entities.UserEntity;
import giis.demo.tkrun.models.dtos.ArticuloDto;
import giis.demo.tkrun.models.dtos.RevisionDto;
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
	
	public static List<RevisionEntity> toRevisionEntityList(List<RevisionDto> articulos) {
		List<RevisionEntity> ents = new ArrayList<>();
		for (RevisionDto rev : articulos) {
			ents.add(toRevisionEntity(rev));
		}
		return ents;
	}

	private static RevisorEntity toRevisorEntity(RevisorDto rev) {
		RevisorEntity ent = new RevisorEntity();
		
		ent.setEstado(rev.getEstado());
		ent.setNombre(rev.getNombre());
		ent.setId(rev.getIdRevisor());
		
		
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
		
		userEntity.setUserName(userDto.getNombre());
		userEntity.setType(userDto.getTipoUsuario());
		
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
		articuloEntity.setIdArticulo(articuloDto.getIdArticulo());
		articuloEntity.setFirma(articuloDto.getFirma());
		articuloEntity.setFicheroFuente(articuloDto.getFicheroFuente());
		articuloEntity.setEstado(articuloDto.getEstado());
		articuloEntity.setCV(articuloDto.getCV());
		articuloEntity.setCartaPresentacion(articuloDto.getCartaPresentacion());
		
		return articuloEntity;
	}
	
	public static RevisionEntity toRevisionEntity(RevisionDto art) {
		RevisionEntity ent = new RevisionEntity();
		
		ent.setIdRevisor(art.getIdRevisor());
		ent.setIdArticulo(art.getIdArticulo());
		ent.setComentariosEditor(art.getComentariosEditor());
		ent.setComentariosAutor(art.getComentariosAutor());
		ent.setEnviarAlEditor(art.isEnviarAlEditor());
		
		
		return ent;
	}
}
