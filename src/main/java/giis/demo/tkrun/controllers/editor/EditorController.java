package giis.demo.tkrun.controllers.editor;

import java.util.List;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisorEntity;
import giis.demo.tkrun.models.articulo.ArticuloModel;
import giis.demo.tkrun.models.revision.RevisionModel;
import giis.demo.tkrun.models.revisor.RevisorModel;
import giis.demo.tkrun.views.editor.MenuEditor;
import giis.demo.util.DtoMapper;
import giis.demo.util.EntityAssembler;

public class EditorController {

	private MenuEditor principalView;
	// private EditorView view;
	private RevisionModel revisionModel;
	private ArticuloModel articuloModel;
	private RevisorModel revisoresModel;
	private SugerenciaModel sugerenciaModel = new SugerenciaModel();

	private void initView() {
		this.principalView = new MenuEditor(this);
		this.principalView.setVisible(true);
		// this.principalView.setModal(true);
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

	public EditorController(boolean mostrarVista) {
		this.revisionModel = new RevisionModel();
		this.articuloModel = new ArticuloModel();
		this.revisoresModel = new RevisorModel();
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

	public List<ArticuloEntity> getArticulosFiltradoTitulo(String titulo) {
		return EntityAssembler.toArticuloEntityList(articuloModel.getArticulosFiltradoTitulo(titulo));
	}

	public List<ArticuloEntity> getArticulosFiltradoAutor(String autor) {
		return EntityAssembler.toArticuloEntityList(articuloModel.getArticulosFiltradoAutor(autor));
	}


	public void rechazarDefinitivimenteArticulo(ArticuloEntity articulo) {
		articuloModel.rechazarDefinitivamente(DtoMapper.toArticuloDto(articulo));

	}
	public List<RevisionEntity> getRevisionesFiltradas(int idArticulo, int numeroRevision) {
		return EntityAssembler.toRevisionEntityList(revisionModel.getRevisionesFiltradoNumeroRevision(idArticulo, numeroRevision));
	}
	public List<RevisionEntity> getRevisionesArticulo(ArticuloEntity articulo){
		return EntityAssembler.toRevisionEntityList(revisionModel.getRevisionesDeUnArticulo(DtoMapper.toArticuloDto(articulo)));
	}
	
	public List<RevisionEntity> getRevisionesArticuloDeUnRevisor(int idArticulo, int idRevisor){
		return EntityAssembler.toRevisionEntityList(revisionModel.getRevisionesArticuloDeUnRevisor(idArticulo, idRevisor));
	}
	
	public List<RevisionEntity> getRevisionPorNumeroRevision(int numeroRevision, int idRevisor, int idArticulo) {
		return EntityAssembler.toRevisionEntityList(revisionModel.getRevisionPorNumeroRevision(idArticulo, idRevisor, numeroRevision));
	}
	public void generarSegundaRevision(int idArticulo, int idRevisor, String fecha) {
		revisionModel.generarSegundaRevision(idArticulo, idRevisor, fecha);
	}
	//------------------------------ OSCAR ---------------------------------------------------------	

		public EditorController() {
		}

		/**
		 * Devuelve una lista con todos los revisores disponibles
		 * 
		 * @return
		 */
		public List<RevisorEntity> getRevisoresDisponibles(ArticuloEntity articulo) {

			List<RevisorEntity> revisoresDisponibles = EntityAssembler
					.toRevisorEntityList(revisoresModel.getRevisoresDisponibles());

			return revisoresDisponibles
					.stream()
					//.filter(r -> revisionModel.findRevisionRechazada(articulo.getIdArticulo(), r.getId()).isEmpty())
					.filter(r -> !revisionModel.findRevisionRechazada(articulo.getIdArticulo(), r.getId()).isPresent())
					.collect(Collectors.toList());

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
		 * 
		 * @param articulo
		 * @return
		 */
		public int getNumeroDeRevisoresAsignados(ArticuloEntity articulo) {
			return revisionModel.findByIdArticulo(articulo.getIdArticulo()).size();
		}

		/**
		 * Devuelve una lista con los revisores de las sugerencias en funcion del
		 * articulo
		 * 
		 * @param articulo
		 * @return
		 */
		public List<RevisorEntity> getRevisoresSugeridos(ArticuloEntity articulo) {
			List<RevisorEntity> list = new ArrayList<>();
			for (RevisorDto dto : sugerenciaModel.getRevisoresSugeridos(articulo.getIdArticulo())) {
				list.add(EntityAssembler.toRevisorEntity(revisoresModel.findById(dto.getIdRevisor())));
			}
			return list;
		}

		/**
		 * Devuelve una lista con los revisores de las sugerencias en funcion del
		 * articulo
		 * 
		 * @param articulo
		 * @return
		 */
		public List<RevisorEntity> getRevisoresAsignados(ArticuloEntity articulo) {

			List<RevisionEntity> revisiones = EntityAssembler
					.toRevisionEntityList(revisionModel.getRevisionesDeUnArticulo(DtoMapper.toArticuloDto(articulo)));
			List<RevisorEntity> revisores = new ArrayList<>();

			for (RevisionEntity r : revisiones) {
				revisores.add(EntityAssembler.toRevisorEntity(revisoresModel.findById(r.getIdRevisor())));
			}

			return revisores;
		}

		/**
		 * Devuelve true si el articulo se encuentra en estado 'con el editor'
		 * 
		 * @param articulo
		 * @return
		 */
		public boolean checkArticuloParaAsignar(ArticuloEntity articulo) {

			return EntityAssembler.toArticuloEntity(articuloModel.findById(articulo.getIdArticulo()).get(0)).getEstado()
					.equals(ArticuloEntity.CON_EL_EDITOR);
		}

		/**
		 * Añade el revisor a la lista de revisores si es que no esta añadido
		 * 
		 * @param selectedValue
		 * @return
		 */
		public boolean añadirRevisorAListaDeRevisores(RevisorEntity selectedValue) {
			if (revisoresModel.findById(selectedValue.getId()).getEstado().equals(RevisorEntity.SUGERIDO)) {
				RevisorDto dto = DtoMapper.toRevisorDto(selectedValue);
				dto.setEstado(RevisorEntity.DISPONIBLE);
				revisoresModel.update(dto);
				return true;
			}
			return false;
		}

	//-------------------------------------------------------------------------------------------------------

}
