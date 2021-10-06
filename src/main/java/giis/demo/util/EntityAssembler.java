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
	
	public static List<ArticuloEntity> toArticuloEntityList(List<ArticuloDto> articulos) {
		List<ArticuloEntity> ents = new ArrayList<>();
		for (ArticuloDto rev : articulos) {
			ents.add(toArticuloEntity(rev));
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

	private static ArticuloEntity toArticuloEntity(ArticuloDto art) {
		ArticuloEntity ent = new ArticuloEntity();
		
		ent.setEstado(art.getEstado());
		ent.setTitulo(art.getTitulo());
		ent.setPrimerAutor(art.getPrimerAutor());
		ent.setIdArticulo(art.getIdArticulo());
		
		
		return ent;
	}
}
