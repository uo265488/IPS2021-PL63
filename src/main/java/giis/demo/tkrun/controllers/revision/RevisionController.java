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
	
	
	
	/**
	 * Checkear si el revisor ha enviado sus comentarios
	 * 
	 * @param articulo
	 * @param revisor
	 * @return
	 */
	public boolean checkComentariosDeRevisionEnviados(ArticuloEntity articulo, RevisorEntity revisor) {
		if ( model.getRevision(DtoMapper.toArticuloDto(articulo), DtoMapper.toRevisorDto(revisor)).isEnviarAlEditor()) return false;  //comprobar si este metodo chekea si se enviaron los comentarios o no
		
		return true;
	}
	
	public void enviarComentariosRevisionAlAutor(RevisionEntity revision) {
		
	}



	public List<RevisionEntity> getRevisionesDelArticulo(ArticuloEntity articulo) {
		
		return EntityAssembler.toRevisionEntityList(model.getRevisionesDeUnArticulo(DtoMapper.toArticuloDto(articulo)));
	}
	
	
	

}
