package giis.demo.tkrun.controllers.articulo;



import java.util.List;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.models.articulo.ArticuloModel;
import giis.demo.util.DtoMapper;
import giis.demo.util.EntityAssembler;

public class ArticuloController {

	private ArticuloModel artModel;
	
	/**
	 * Obtiene del ArticuloModel una lista con los articulos nuevos
	 * @return List de ArticuloEntity
	 */
	public List<ArticuloEntity> getArticulosNuevos() {
		return EntityAssembler.toArticuloEntityList(artModel.listArticulosNuevos());
	}


	/**
	 * Rechaza el articulo que recibe por parametro
	 * @param selectedItem
	 */
	public void rechazarArticulo(ArticuloEntity articulo) {
		articulo.setEstado(ArticuloEntity.RECHAZADO);
		artModel.update(DtoMapper.toArticuloDto(articulo));
	}


	public void visualizarArticulo(ArticuloEntity articulo) {
		articulo.setEstado(ArticuloEntity.CON_EL_EDITOR);
		artModel.update(DtoMapper.toArticuloDto(articulo));
	}
	

	
	
	
	
}
