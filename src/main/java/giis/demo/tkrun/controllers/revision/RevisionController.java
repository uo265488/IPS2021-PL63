package giis.demo.tkrun.controllers.revision;

import java.util.List;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisionEntity;
import giis.demo.tkrun.controllers.entities.RevisorEntity;
import giis.demo.tkrun.models.revision.RevisionModel;
import giis.demo.util.DtoMapper;
import giis.demo.util.EntityAssembler;

public class RevisionController {
	
	private RevisionModel model = new RevisionModel();
	
	/**
	 * Devuelve los comentarios de revision en funcion del articulo
	 * @param articulo
	 * @param revisor
	 * @return
	 */
	public List<RevisionEntity> getComentariosDeRevisionDelArticulo(ArticuloEntity articulo, RevisorEntity revisor) {
		
		return EntityAssembler.toRevisionEntityList(model.getComentariosDeRevisionDeUnArticulo(DtoMapper.toArticuloDto(articulo), DtoMapper.toRevisorDto(revisor)));
	}
	
	public boolean checkComentariosDeRevisionEnviados(ArticuloEntity articulo, RevisorEntity revisor) {
		if ( model.getRevision(DtoMapper.toArticuloDto(articulo), DtoMapper.toRevisorDto(revisor)).getComentarios().isEmpty()) return false;
		
		return true;
	}
	
	
	

}
