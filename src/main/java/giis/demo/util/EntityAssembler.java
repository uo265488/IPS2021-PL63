package giis.demo.util;

import java.util.ArrayList;
import java.util.List;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisorEntity;
import giis.demo.tkrun.models.dtos.ArticuloDto;
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
		ent.setId(rev.getId());
		
		
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
		ent.setCVAutor(dto.getCVAutor());
		ent.setEstado(dto.getEstado());
		ent.setFicheroFuente(dto.getFicheroFuente());
		ent.setFirma(dto.getFirma());
		ent.setId(dto.getId());
		ent.setOtrosAutores(dto.getOtrosAutores());
		ent.setPalabrasClave(dto.getPalabrasClave());
		ent.setPrimerAutor(dto.getPrimerAutor());
		ent.setResumen(dto.getResumen());
		ent.setTitulo(dto.getTitulo());
		
		return ent;
	}

	
}
