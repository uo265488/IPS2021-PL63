package giis.demo.tkrun.controllers.editor;

import java.util.List;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisorEntity;
import giis.demo.tkrun.models.articulo.ArticuloModel;
import giis.demo.tkrun.models.editor.EditorModel;
import giis.demo.tkrun.models.revision.RevisionModel;
import giis.demo.tkrun.models.revisor.RevisorModel;
import giis.demo.tkrun.views.EditorView;
import giis.demo.util.DtoMapper;
import giis.demo.util.EntityAssembler;

public class EditorController {

    private EditorView view;
	private EditorModel model;
	private RevisionModel revisionModel;
	private ArticuloModel articuloModel;
	private RevisorModel revisoresModel;
	
	public EditorController(EditorModel m, EditorView v) {
		this.model = m;
		this.view = v;
		//no hay inicializacion especifica del modelo, solo de la vista
		this.initView();
	}
	
	public EditorController(EditorModel m) {
		this.model = m;
		//no hay inicializacion especifica del modelo, solo de la vista
		this.initView();
	}

	private void initView() {

		this.view = new EditorView(this);
		view.setVisible(true);
		
		
	}

	public List<RevisorEntity> getRevisoresDisponibles() {
		
		return EntityAssembler.toRevisorEntityList(model.getRevisoresDisponibles());
	}
	
	public boolean asignarRevisoresAlArticulo(List<RevisorEntity> revisores, ArticuloEntity articulo, String fecha) {
		
		//validaciones:
		//   -tiene q haber tres revisores
		//   -articulo q no sea null y demas
		
		//model.asignarRevisores( DtoMapper.toRevisorDtoList(revisores), articulo);
		
		generarRevisiones(revisores, articulo, fecha); //Generamos una revsion con los datos y enviamos el dto al model para añadirlo a la base de datos
		
		cambiarEstadoArticuloEnRevision(articulo);//cambiar el estado del articulo
		
		cambiarEstadoRevisoresNoDisponible(revisores);//cambiar el estado del revisor a no disponible
		
		
		return false;
		
	}

	/**
	 * Cambia el estado de los revisores a no disponibles
	 * @param revisores
	 */
	private void cambiarEstadoRevisoresNoDisponible(List<RevisorEntity> revisores) {
		
		for(RevisorEntity rev : revisores) {
			rev.setEstado("no disponible");
			revisoresModel.update(DtoMapper.toRevisorDto(rev));
		}
		
	}

	/**
	 * Cambia el estado del articulo a "En revision"
	 * @param articulo
	 */
	private void cambiarEstadoArticuloEnRevision(ArticuloEntity articulo) {
		
		articulo.setEstado("En revision");
		
		articuloModel.update(DtoMapper.toArticuloDto(articulo));
		
	}

	/**
	 * Metodo que genera las revisiones a partir del articulo, los revisores y la fecha
	 * 
	 * @return
	 */
	private void generarRevisiones(List<RevisorEntity> revisores, ArticuloEntity articulo, String fecha) {
		for( RevisorEntity rev : revisores) {
			revisionModel.add(DtoMapper.toRevisionDto(rev, articulo, fecha));
		}
	}


	
	
}
