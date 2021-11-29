package giis.demo.util;

import java.util.ArrayList;
import java.util.List;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.AutorEntity;
import giis.demo.tkrun.controllers.entities.DebateEntity;
import giis.demo.tkrun.controllers.entities.MensajeEntity;
import giis.demo.tkrun.controllers.entities.RevisionEntity;
import giis.demo.tkrun.controllers.entities.RevisorEntity;
import giis.demo.tkrun.controllers.entities.SugerenciaEntity;
import giis.demo.tkrun.controllers.entities.UserEntity;
import giis.demo.tkrun.models.dtos.ArticuloDto;
import giis.demo.tkrun.models.dtos.AutorDto;
import giis.demo.tkrun.models.dtos.DebateDto;
import giis.demo.tkrun.models.dtos.MensajeDto;
import giis.demo.tkrun.models.dtos.RevisionDto;
import giis.demo.tkrun.models.dtos.RevisorDto;
import giis.demo.tkrun.models.dtos.SugerenciaDto;
import giis.demo.tkrun.models.dtos.UserDto;

public class EntityAssembler {

	public static ArticuloEntity toArticuloEntity(ArticuloDto dto) {
		if (dto == null) {
			return null;
		}

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

	public static List<ArticuloEntity> toArticuloEntityList(List<ArticuloDto> listArticulosNuevos) {
		List<ArticuloEntity> ents = new ArrayList<>();
		for (ArticuloDto rev : listArticulosNuevos) {
			ents.add(toArticuloEntity(rev));
		}
		return ents;
	}

	public static AutorEntity toAutorEntity(AutorDto autor) {
		if (autor == null) {
			return null;
		}

		AutorEntity res = new AutorEntity();

		res.setIdAutor(autor.getIdAutor());
		res.setNombre(autor.getNombre());
		res.setDni(autor.getDni());

		return res;
	}

	public static List<AutorEntity> toAutorEntiyList(List<AutorDto> dtos) {
		List<AutorEntity> entities = new ArrayList<>();
		for (AutorDto dto : dtos) {
			entities.add(toAutorEntity(dto));
		}

		return entities;
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

	public static List<RevisionEntity> toRevisionEntityList(List<RevisionDto> revs) {
		List<RevisionEntity> ents = new ArrayList<>();
		for (RevisionDto rev : revs) {
			ents.add(toRevisionEntity(rev));
		}
		return ents;
	}

	public static RevisorDto toRevisorDto(RevisorEntity rev) {
		if (rev == null) {
			return null;
		}
		RevisorDto dto = new RevisorDto();
		dto.setIdRevisor(rev.getId());
		dto.setNombre(rev.getNombre());
		dto.setEstado(rev.getEstado());
		dto.setCorreo(rev.getCorreo());
		dto.setEspecialidad(rev.getEspecialidad());

		return dto;
	}

	public static RevisorEntity toRevisorEntity(RevisorDto rev) {
		if (rev == null) {
			return null;
		}

		RevisorEntity ent = new RevisorEntity();

		ent.setEstado(rev.getEstado());
		ent.setNombre(rev.getNombre());
		ent.setId(rev.getIdRevisor());
		ent.setCorreo(rev.getCorreo());
		ent.setEspecialidad(rev.getEspecialidad());

		return ent;
	}

	public static List<RevisorEntity> toRevisorEntityList(List<RevisorDto> revisores) {
		List<RevisorEntity> ents = new ArrayList<>();
		for (RevisorDto rev : revisores) {
			ents.add(toRevisorEntity(rev));
		}
		return ents;
	}


	private static SugerenciaEntity toSugerenciaEntity(SugerenciaDto dto) {
		SugerenciaEntity ent = new SugerenciaEntity();
		ent.setIdArticulo(dto.getIdArticulo());
		ent.setIdRevisor(dto.getIdRevisor());

		return ent;
	}

	public static List<SugerenciaEntity> toSugerenciaEntityList(List<SugerenciaDto> dtos) {
		List<SugerenciaEntity> ents = new ArrayList<>();
		for (SugerenciaDto sug : dtos) {
			ents.add(toSugerenciaEntity(sug));
		}

		return ents;
	}

	private static UserEntity toUserEntity(UserDto userDto) {
		if (userDto == null) {
			return null;
		}

		UserEntity userEntity = new UserEntity();

		userEntity.setIdUser(userDto.getIdUsuario());
		userEntity.setUserName(userDto.getNombre());
		userEntity.setType(userDto.getTipoUsuario());

		return userEntity;
	}

	public static List<UserEntity> toUserEntityList(List<UserDto> usersDto) {
		List<UserEntity> usersEntity = new ArrayList<>();
		for (UserDto user : usersDto) {
			usersEntity.add(toUserEntity(user));
		}

		return usersEntity;
	}
	
	private static MensajeEntity toMensajeEntity(MensajeDto mensajeDto) {
		if (mensajeDto == null) {
			return null;
		}
		MensajeEntity mensajeEntity = new MensajeEntity();
		
		mensajeEntity.setIdDebate(mensajeDto.getIdDebate());
		mensajeEntity.setIdMensaje(mensajeDto.getIdMensaje());
		mensajeEntity.setMensaje(mensajeDto.getMensaje());
		
		return mensajeEntity;
	}
	
	public static List<MensajeEntity> toMensajeEntityList(List<MensajeDto> mensajesDto){
		List<MensajeEntity> mensajesEntity = new ArrayList<>();
		for (MensajeDto mensaje : mensajesDto) {
			mensajesEntity.add(toMensajeEntity(mensaje));
		}
		return mensajesEntity;
	}

	public static DebateEntity toDebateEntity(DebateDto debateDto) {
		if (debateDto == null) {
			return null;
		}
		else {
			DebateEntity debateEntity = new DebateEntity();
			debateEntity.setIdArticulo(debateDto.getIdArticulo());
			debateEntity.setIdDebate(debateDto.getIdDebate());
			debateEntity.setFecha(debateDto.getFecha());
			debateEntity.setAbierto(debateDto.isAbierto());
			return debateEntity;
		}
	}

}
