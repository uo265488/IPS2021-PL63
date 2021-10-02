package giis.demo.tkrun.editor.controllers;

import java.util.List;

import giis.demo.tkrun.articulo.ArticuloEntity;
import giis.demo.tkrun.articulo.model.ArticuloModel;
import giis.demo.tkrun.editor.model.EditorModel;
import giis.demo.tkrun.editor.view.EditorView;
import giis.demo.tkrun.revision.model.RevisionModel;
import giis.demo.tkrun.revisor.RevisorEntity;
import giis.demo.tkrun.revisor.model.RevisorModel;
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
