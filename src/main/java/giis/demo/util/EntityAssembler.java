package giis.demo.util;

import java.util.ArrayList;
import java.util.List;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisionEntity;
import giis.demo.tkrun.controllers.entities.RevisorEntity;
import giis.demo.tkrun.models.dtos.ArticuloDto;
import giis.demo.tkrun.models.dtos.RevisionDto;
import giis.demo.tkrun.models.dtos.RevisorDto;

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
		ent.setId(rev.getIdRevisor());

		return ent;
	}

	public static List<ArticuloEntity> toArticuloEntityList(List<ArticuloDto> listArticulosNuevos) {
		List<ArticuloEntity> ents = new ArrayList<>();
		for (ArticuloDto rev : listArticulosNuevos) {
			ents.add(toArticuloEntity(rev));
		}
		return ents;
	}

	private static ArticuloEntity toArticuloEntity(ArticuloDto dto) {
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

		return ent;
	}

	public static List<RevisionEntity> toRevisionEntityList(List<RevisionDto> revs) {
		List<RevisionEntity> ents = new ArrayList<>();
		for (RevisionDto rev : revs) {
			ents.add(toRevisionEntity(rev));
		}
		return ents;
	}

	private static RevisionEntity toRevisionEntity(RevisionDto rev) {

		RevisionEntity ent = new RevisionEntity();

        ent.setComentarios(rev.getComentarios());
        ent.setFecha(rev.getFecha());
        ent.setIdArticulo(rev.getArticulo().getIdArticulo());
        ent.setIdRevisor(rev.getRevisor().getIdRevisor());

		return ent;
	}

}
