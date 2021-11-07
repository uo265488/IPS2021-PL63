package giis.demo.util;

import java.util.ArrayList;
import java.util.List;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.AutorEntity;
import giis.demo.tkrun.controllers.entities.RevisionEntity;
import giis.demo.tkrun.controllers.entities.RevisorEntity;
import giis.demo.tkrun.controllers.entities.UserEntity;
import giis.demo.tkrun.models.dtos.ArticuloDto;
import giis.demo.tkrun.models.dtos.AutorDto;
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

    public static RevisorEntity toRevisorEntity(RevisorDto rev) {
	if (rev == null) {
	    return null;
	}

	RevisorEntity ent = new RevisorEntity();

	ent.setEstado(rev.getEstado());
	ent.setNombre(rev.getNombre());
	ent.setId(rev.getIdRevisor());

	return ent;
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

    public static List<ArticuloEntity> toArticuloEntityList(List<ArticuloDto> listArticulosNuevos) {
	List<ArticuloEntity> ents = new ArrayList<>();
	for (ArticuloDto rev : listArticulosNuevos) {
	    ents.add(toArticuloEntity(rev));
	}
	return ents;
    }

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

    public static List<RevisionEntity> toRevisionEntityList(List<RevisionDto> revs) {
	List<RevisionEntity> ents = new ArrayList<>();
	for (RevisionDto rev : revs) {
	    ents.add(toRevisionEntity(rev));
	}
	return ents;
    }

    public static List<UserEntity> toUserEntityList(List<UserDto> usersDto) {
	List<UserEntity> usersEntity = new ArrayList<>();
	for (UserDto user : usersDto) {
	    usersEntity.add(toUserEntity(user));
	}

	return usersEntity;
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

    public static RevisionEntity toRevisionEntity(RevisionDto art) {
	RevisionEntity ent = new RevisionEntity();

	ent.setIdRevisor(art.getIdRevisor());
	ent.setIdArticulo(art.getIdArticulo());
	ent.setComentariosEditor(art.getComentariosEditor());
	ent.setComentariosAutor(art.getComentariosAutor());
	ent.setEnviarAlEditor(art.isEnviarAlEditor());
	ent.setFecha(art.getFecha());
	ent.setDecision(art.getDecision());

	return ent;
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

}
