package giis.demo.tkrun.controllers.articulo;



import java.util.List;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.models.articulo.ArticuloModel;
import giis.demo.tkrun.views.articulo.ArticulosNuevosView;
import giis.demo.util.EntityAssembler;

public class ArticuloController {

	private ArticuloModel artModel;
	private ArticulosNuevosView articulosNuevosView;
	
	
	protected List<ArticuloEntity> getArticulosNuevos() {
		return EntityAssembler.toArticuloEntityList(artModel.listArticulosNuevos());
	}
	
	
}
