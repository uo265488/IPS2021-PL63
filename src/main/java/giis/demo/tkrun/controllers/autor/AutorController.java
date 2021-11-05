package giis.demo.tkrun.controllers.autor;

import java.util.List;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.models.articulo.ArticuloModel;
import giis.demo.tkrun.models.autor.AutorModel;
import giis.demo.tkrun.models.dtos.ArticuloDto;
import giis.demo.tkrun.views.autor.MenuAutor;
import giis.demo.util.EntityAssembler;

public class AutorController {
	
	private AutorModel model;
	private ArticuloModel articuloModel;
	private MenuAutor view;

	public AutorController() {
		this.model = new AutorModel();
		articuloModel = new ArticuloModel();
		initView();
	}

	private void initView() {
		this.view = new MenuAutor(this);
		view.setVisible(true);
	}

	public List<ArticuloEntity> getArticulosPropios(int id) {
		
		return EntityAssembler.toArticuloEntityList(model.articulosDeUnAutor(id));
	}
	
	public List<ArticuloEntity> getArticulosAceptadosSinVersionDefinitiva(int id) {
		
		return EntityAssembler.toArticuloEntityList(model.articulosAceptadosSinVersionDefinitiva(id));
	}
	
	public void getEnviarVersionDefinitiva(int id) {
		
		model.enviarVersionDefinitiva(id);
	}
	
	public void crearBorrador(ArticuloDto articuloDto) {
		articuloModel.crearBorrador(articuloDto);
	}
	
	public void crearArticulo(ArticuloDto articuloDto) {
		articuloModel.crearArticulo(articuloDto);
	}

}