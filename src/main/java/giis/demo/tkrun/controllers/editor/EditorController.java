package giis.demo.tkrun.controllers.editor;

import java.util.ArrayList;
import java.util.List;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisionEntity;
import giis.demo.tkrun.controllers.entities.RevisorEntity;
import giis.demo.tkrun.models.articulo.ArticuloModel;
import giis.demo.tkrun.models.revision.RevisionModel;
import giis.demo.tkrun.models.revisor.RevisorModel;
import giis.demo.tkrun.models.sugerencia.SugerenciaModel;
import giis.demo.util.DtoMapper;
import giis.demo.util.EntityAssembler;

public class EditorController {

	
	private RevisionModel revisionModel = new RevisionModel();
	private ArticuloModel articuloModel = new ArticuloModel();
	private RevisorModel revisoresModel = new RevisorModel();
	private SugerenciaModel sugerenciaModel = new SugerenciaModel();

//------------------------------ OSCAR ---------------------------------------------------------	
	
	public EditorController() { }

	/**
	 * Devuelve una lista con todos los revisores disponibles
	 * @return
	 */
	public List<RevisorEntity> getRevisoresDisponibles() {

		return EntityAssembler.toRevisorEntityList(revisoresModel.getRevisoresDisponibles());
	}

	/**
	 * Metodo para asignar el revisor (generar revision, marcar articulo como 'en
	 * revision' y el revisor como 'no disponible'
	 * 
	 * @param revisor
	 * @param articulo
	 * @param fecha
	 */
	public void asignarRevisor(RevisorEntity revisor, ArticuloEntity articulo, String fecha) {

		cambiarEstadoRevisorNoDisponible(revisor);

		generarRevision(revisor, articulo, fecha);
		
		if (getNumeroDeRevisoresAsignados(articulo) == 3) 
			cambiarEstadoArticuloEnRevision(articulo);

	}

	/**
	 * Cambia el estado de los revisores a no disponibles
	 * 
	 * @param revisores
	 */
	private void cambiarEstadoRevisorNoDisponible(RevisorEntity revisor) {

		revisor.setEstado(RevisorEntity.NO_DISPONIBLE);
		
		revisoresModel.update(DtoMapper.toRevisorDto(revisor));

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
	private void generarRevision(RevisorEntity revisor, ArticuloEntity articulo, String fecha) {

		revisionModel.add(DtoMapper.toRevisionDto(revisor, articulo, fecha));
	}

	/**
	 * Devuelve el numero de revisores asignados a un articulo
	 * @param articulo
	 * @return
	 */
	public int getNumeroDeRevisoresAsignados(ArticuloEntity articulo) {
		return revisionModel.findByIdArticulo(articulo.getIdArticulo()).size();
	}
	
	/**
	 * Devuelve una lista con los revisores de las sugerencias en funcion del articulo
	 * @param articulo
	 * @return
	 */
	public List<RevisorEntity> getRevisoresSugeridos(ArticuloEntity articulo) {

        return EntityAssembler.toRevisorEntityList(sugerenciaModel.getRevisoresSugeridos(articulo.getIdArticulo()));
	}
	
	/**
	 * Devuelve una lista con los revisores de las sugerencias en funcion del articulo
	 * @param articulo
	 * @return
	 */
	public List<RevisorEntity> getRevisoresAsignados(ArticuloEntity articulo) {

		List<RevisionEntity> revisiones = EntityAssembler.toRevisionEntityList(revisionModel.getRevisionesDeUnArticulo(DtoMapper.toArticuloDto(articulo)));
		List<RevisorEntity> revisores = new ArrayList<>();
		
		for (RevisionEntity r : revisiones) {
			revisores.add(EntityAssembler.toRevisorEntity(revisoresModel.findById(r.getIdRevisor())));
		}
		
		return revisores;
	}
	
	/**
	 * Devuelve true si el articulo se encuentra en estado 'con el editor'
	 * @param articulo
	 * @return
	 */
	public boolean checkArticuloParaAsignar(ArticuloEntity articulo) {

		return EntityAssembler.toArticuloEntity(articuloModel.findById(articulo.getIdArticulo()).get(0)).getEstado()
				.equals(ArticuloEntity.CON_EL_EDITOR);
	}


//-------------------------------------------------------------------------------------------------------
	
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

	public List<ArticuloEntity> getArticulosFiltradoTitulo(String titulo) {
		return EntityAssembler.toArticuloEntityList(articuloModel.getArticulosFiltradoTitulo(titulo));
	}

	public List<ArticuloEntity> getArticulosFiltradoAutor(String autor) {
		return EntityAssembler.toArticuloEntityList(articuloModel.getArticulosFiltradoAutor(autor));
	}

	public void rechazarDefinitivimenteArticulo(ArticuloEntity articulo) {
		articuloModel.rechazarDefinitivamente(DtoMapper.toArticuloDto(articulo));

	}





}
