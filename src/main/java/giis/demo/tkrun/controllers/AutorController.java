package giis.demo.tkrun.controllers;

import java.util.List;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.models.autor.AutorModel;
import giis.demo.tkrun.models.dtos.AutorDto;
import giis.demo.util.EntityAssembler;

public class AutorController {
	
	//private EditorView view; No hay vista todavía asi que esta todo comentado
	private AutorModel model;
	//private RevisionModel revisionModel;
	//private ArticuloModel articuloModel;
	//private RevisorModel revisoresModel;
	
	//public AutorController(AutorModel m, EditorView v) {
		//this.model = m;
		//this.view = v;
		//no hay inicializacion especifica del modelo, solo de la vista
		//this.initView();
	//}
	
	public AutorController(AutorModel m) {
		this.model = m;
		//no hay inicializacion especifica del modelo, solo de la vista
		//this.initView();
	}

	//private void initView() {

		//this.view = new EditorView(this);
		//view.setVisible(true);
		
		
	//}

	public List<ArticuloEntity> getArticulosPropios(AutorDto autor) {
		
		return EntityAssembler.toArticuloEntityList(model.update(autor));
	}

}
