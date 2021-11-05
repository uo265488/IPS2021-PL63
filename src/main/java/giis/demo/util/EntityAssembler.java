package giis.demo.util;

import java.util.ArrayList;
import java.util.List;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisionEntity;
import giis.demo.tkrun.controllers.entities.RevisorEntity;
import giis.demo.tkrun.models.dtos.ArticuloDto;
import giis.demo.tkrun.models.dtos.RevisionDto;
import giis.demo.tkrun.models.dtos.RevisorDto;
import giis.demo.tkrun.controllers.entities.UserEntity;
import giis.demo.tkrun.models.dtos.UserDto;

public class EntityAssembler {

	
	public static List<RevisorEntity> toRevisorEntityList(List<RevisorDto> revisores) {
		List<RevisorEntity> ents = new ArrayList<>();
		for (RevisorDto rev : revisores) {
			ents.add(toRevisorEntity(rev));
		}
		return ents;
	}

	public static RevisorEntity toRevisorEntity(RevisorDto rev) {
		RevisorEntity ent = new RevisorEntity();
		
		ent.setEstado(rev.getEstado());
		ent.setNombre(rev.getNombre());
		ent.setId(rev.getIdRevisor());
		ent.setCorreo(rev.getCorreo());
		ent.setEspecialidad(rev.getEspecialidad());

		return ent;
	}

	public static List<ArticuloEntity> toArticuloEntityList(List<ArticuloDto> listArticulosNuevos) {
		List<ArticuloEntity> ents = new ArrayList<>();
		for (ArticuloDto rev : listArticulosNuevos) {
			ents.add(toArticuloEntity(rev));
		}
		return ents;
	}

	public static ArticuloEntity toArticuloEntity(ArticuloDto dto) {
		ArticuloEntity ent = new ArticuloEntity();

		ent.setCartaPresentacion(dto.getCartaPresentacion());
		ent.setCV(dto.getCV());
		ent.setEstado(dto.getEstado());
		ent.setFicheroFuente(dto.getFicheroFuente());
		ent.setFirma(dto.isFirma());
		ent.setIdArticulo(dto.getIdArticulo());
		ent.setOtrosAutores(dto.getOtrosAutores());
		ent.setPalabrasClave(dto.getPalabrasClave());
		ent.setPrimerAutor(dto.getPrimerAutor());
		ent.setResumen(dto.getResumen());
		ent.setTitulo(dto.getTitulo());
		ent.setVecesRevisado(dto.getVecesRevisado());
		ent.setVersionDefinitiva(dto.isVersionDefinitiva());
		return ent;
	}

	public static List<RevisionEntity> toRevisionEntityList(List<RevisionDto> revs) {
		List<RevisionEntity> ents = new ArrayList<>();
		for (RevisionDto rev : revs) {
			ents.add(toRevisionEntity(rev));
		}
		return ents;
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
	
	public static RevisionEntity toRevisionEntity(RevisionDto art) {
		RevisionEntity ent = new RevisionEntity();
		
		ent.setIdRevisor(art.getIdRevisor());
		ent.setIdArticulo(art.getIdArticulo());
		ent.setComentariosEditor(art.getComentariosEditor());
		ent.setComentariosAutor(art.getComentariosAutor());
		ent.setEnviarAlEditor(art.isEnviarAlEditor());
		ent.setFecha(art.getFecha());
		ent.setDecision(art.getDecision());
		ent.setEstadoDeLaPropuesta(art.getEstadoDeLaPropuesta());
		
		return ent;
	}
}
