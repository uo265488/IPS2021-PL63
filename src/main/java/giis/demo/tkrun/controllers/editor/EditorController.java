package giis.demo.tkrun.controllers.editor;

import java.util.List;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisorEntity;
import giis.demo.tkrun.models.articulo.ArticuloModel;
import giis.demo.tkrun.models.revision.RevisionModel;
import giis.demo.tkrun.models.revisor.RevisorModel;
import giis.demo.tkrun.views.editor.EditorPrincipalView;
import giis.demo.tkrun.views.editor.EditorView;
import giis.demo.util.DtoMapper;
import giis.demo.util.EntityAssembler;

public class EditorController {

	private EditorView view;
	private EditorPrincipalView principalView;
	private RevisionModel revisionModel;
	private ArticuloModel articuloModel;
	private RevisorModel revisoresModel;


	private void initView(ArticuloEntity articulo) {

		this.view = new EditorView(this, articulo);
		view.setVisible(true);

	}
	
	private void initView() {
		this.principalView = new EditorPrincipalView(this);
		this.principalView.setVisible(true);
	}

	public EditorController(ArticuloEntity articulo) {

		this.revisionModel = new RevisionModel();
		this.articuloModel = new ArticuloModel();
		this.revisoresModel = new RevisorModel();

		initView(articulo);
	}
	
	public EditorController() {
		this.revisionModel = new RevisionModel();
		this.articuloModel = new ArticuloModel();
		this.revisoresModel = new RevisorModel();
		
		initView();
	}

	public List<RevisorEntity> getRevisoresDisponibles() {

		return EntityAssembler.toRevisorEntityList(revisoresModel.getRevisoresDisponibles());
	}
	
	public boolean asignarRevisoresAlArticulo(List<RevisorEntity> revisores, ArticuloEntity articulo, String fecha) {

		// validaciones:
		// -articulo q no sea null y demas

		// model.asignarRevisores( DtoMapper.toRevisorDtoList(revisores), articulo);

		generarRevisiones(revisores, articulo, fecha); // Generamos una revsion con los datos y enviamos el dto al model
														// para añadirlo a la base de datos

		cambiarEstadoArticuloEnRevision(articulo);// cambiar el estado del articulo

		cambiarEstadoRevisoresNoDisponible(revisores);// cambiar el estado del revisor a no disponible

		return false;

	}

	/**
	 * Cambia el estado de los revisores a no disponibles
	 * 
	 * @param revisores
	 */
	private void cambiarEstadoRevisoresNoDisponible(List<RevisorEntity> revisores) {

		for (RevisorEntity rev : revisores) {
			rev.setEstado(RevisorEntity.NO_DISPONIBLE);
			revisoresModel.update(DtoMapper.toRevisorDto(rev));
		}

	}

	/**
	 * Cambia el estado del articulo a "En revision"
	 * 
	 * @param articulo
	 */
	private void cambiarEstadoArticuloEnRevision(ArticuloEntity articulo) {

		articulo.setEstado(ArticuloEntity.EN_REVISION);

		articuloModel.update(DtoMapper.toArticuloDto(articulo));

	}

	/**
	 * Metodo que genera las revisiones a partir del articulo, los revisores y la
	 * fecha
	 * 
	 * @return
	 */
	private void generarRevisiones(List<RevisorEntity> revisores, ArticuloEntity articulo, String fecha) {
		for (RevisorEntity rev : revisores) {

			revisionModel.add(DtoMapper.toRevisionDto(rev, articulo, fecha));
		}
	}

	public List<ArticuloEntity> getArticulos() {
		return EntityAssembler.toArticuloEntityList(articuloModel.getArticulos());
	}

	public List<ArticuloEntity> getArticulosTomarDecision() {

		return EntityAssembler.toArticuloEntityList(articuloModel.getArticulosTomarDecision());
	}
	
	
	public void aceptarArticulo(ArticuloEntity articulo) {
		articuloModel.aceptar(DtoMapper.toArticuloDto(articulo));
	}
	
	public void rechazarArticulo(ArticuloEntity articulo) {
		articuloModel.rechazar(DtoMapper.toArticuloDto(articulo));
	}
	

}
