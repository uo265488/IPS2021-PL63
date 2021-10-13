package giis.demo.tkrun.controllers.revisor;

import java.util.List;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisionEntity;
import giis.demo.tkrun.models.revision.RevisionModel;
import giis.demo.util.EntityAssembler;

public class RevisorController {

	//private EditorView view; No hay vista todavía asi que esta todo comentado
		private RevisionModel model;
		//private RevisionModel revisionModel;
		//private ArticuloModel articuloModel;
		//private RevisorModel revisoresModel;
		
		//public AutorController(AutorModel m, EditorView v) {
			//this.model = m;
			//this.view = v;
			//no hay inicializacion especifica del modelo, solo de la vista
			//this.initView();
		//}
		
		public RevisorController(RevisionModel m) {
			this.model = m;
			//no hay inicializacion especifica del modelo, solo de la vista
			//this.initView();
		}

		//private void initView() {

			//this.view = new EditorView(this);
			//view.setVisible(true);
			
			
		//}
		
		public RevisionEntity getArticulosSinRevisar(int id, int idArt) {
			
			return EntityAssembler.toRevisionEntity(model.visualizarSinRevisar(id, idArt));
		}
		
		public List<ArticuloEntity> getTituloArticulosSinRevisar(int id) {
			
			return EntityAssembler.toArticuloEntityList(model.articulosSinRevisar(id));
		}
		
		public void actualizarRevision(String comAutor, String comEditor, String decision, boolean enviarAlEditor, int id, int idArt) {
			
			model.revisarArticulo(comAutor, comEditor, decision, enviarAlEditor, idArt, id);
		}
}
