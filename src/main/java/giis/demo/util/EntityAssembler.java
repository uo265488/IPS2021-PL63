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
	
	public static List<ArticuloEntity> toArticuloEntityList(List<ArticuloDto> articulos){
		List<ArticuloEntity> ents = new ArrayList<>();
		for (ArticuloDto art : articulos) {
			ents.add(toArticuloEntity(art));
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
		
		ent.setCartaPresentacion(art.getCartaPresentacion());
		ent.setCVAutor(art.getCVAutor());
		ent.setEstado(art.getEstado());
		ent.setFicheroFuente(art.getFicheroFuente());
		ent.setFirma(art.getFirma());
		ent.setId(art.getId());
		ent.setOtrosAutores(art.getOtrosAutores());
		ent.setPalabrasClave(art.getPalabrasClave());
		ent.setPrimerAutor(art.getPrimerAutor());
		ent.setResumen(art.getResumen());
		ent.setTitulo(art.getTitulo());
				
		return ent;
	}

	
}
