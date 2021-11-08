package giis.demo.tkrun.controllers.revisor;

import java.util.List;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisionEntity;
import giis.demo.tkrun.controllers.entities.RevisorEntity;
import giis.demo.tkrun.models.articulo.ArticuloModel;
import giis.demo.tkrun.models.revision.RevisionModel;
import giis.demo.tkrun.models.revisor.RevisorModel;
import giis.demo.tkrun.views.revisor.RevisorAsignadosView;
import giis.demo.util.EntityAssembler;

public class RevisorController {

	//private EditorView view; No hay vista todavía asi que esta todo comentado
		private RevisionModel model = new RevisionModel();
		//private RevisionModel revisionModel;
		private ArticuloModel articuloModel = new ArticuloModel();
		//private RevisorModel revisoresModel;
		private RevisorAsignadosView rav;
		private int idRevisor;
		
		private RevisorModel revisorModel = new RevisorModel();
		
		public RevisorController(int idRevisor) {
			this.model = new RevisionModel();
			this.articuloModel = new ArticuloModel();;
			this.idRevisor = idRevisor;
			//no hay inicializacion especifica del modelo, solo de la vista
			this.initView();
		}

		public RevisorController() {
			// TODO Auto-generated constructor stub
		}

		private void initView() {
			rav = new RevisorAsignadosView(this, idRevisor);
			rav.setVisible(true);
		}
		
		public RevisionEntity getArticulosSinRevisar(int id, int idArt) {
			
			return EntityAssembler.toRevisionEntity(model.visualizarSinRevisar(id, idArt));
		}
		
		public List<ArticuloEntity> getTituloArticulosSinRevisar(int id) {
			
			return EntityAssembler.toArticuloEntityList(model.articulosSinRevisar(id));
		}
		
		public void actualizarRevision(String comAutor, String comEditor, String decision, boolean enviarAlEditor, int id, int idArt, String estadoDeLaPropuesta) {
			
			model.revisarArticulo(comAutor, comEditor, decision, enviarAlEditor, idArt, id, estadoDeLaPropuesta);
		}
		
		public List<ArticuloEntity> getArticulosAsignados(int id){
			return EntityAssembler.toArticuloEntityList(articuloModel.getArticulosAsignados(id));
		}

//--------------------------OSCAR-----------------------------------------------------------------------------
		public List<RevisionEntity> getAllRevisiones() {
			return EntityAssembler.toRevisionEntityList(model.findAll());
		}

		public RevisorEntity getRevisorById(int id) {
			return EntityAssembler.toRevisorEntity(revisorModel.findById(id));
		}
}
