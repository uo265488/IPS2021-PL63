package giis.demo.tkrun.controllers.editor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisionEntity;
import giis.demo.tkrun.controllers.entities.RevisorEntity;
import giis.demo.tkrun.models.articulo.ArticuloModel;
import giis.demo.tkrun.models.debate.DebateModel;
import giis.demo.tkrun.models.dtos.RevisorDto;
import giis.demo.tkrun.models.dtos.UserDto;
import giis.demo.tkrun.models.revision.RevisionModel;
import giis.demo.tkrun.models.revisor.RevisorModel;
import giis.demo.tkrun.models.sugerencia.SugerenciaModel;
import giis.demo.tkrun.models.user.UserModel;
import giis.demo.util.DtoMapper;
import giis.demo.util.EntityAssembler;

public class EditorController {

	private RevisionModel revisionModel = new RevisionModel();
	private ArticuloModel articuloModel = new ArticuloModel();
	private RevisorModel revisoresModel = new RevisorModel();
	private SugerenciaModel sugerenciaModel = new SugerenciaModel();
	private UserModel userModel = new UserModel();
	private DebateModel debateModel = new DebateModel();

	public EditorController() {

	}

	public EditorController(boolean mostrarVista) {
		this.revisionModel = new RevisionModel();
		this.articuloModel = new ArticuloModel();
		this.revisoresModel = new RevisorModel();
		this.sugerenciaModel = new SugerenciaModel();
	}

	public void aceptarArticulo(ArticuloEntity articulo) {
		articuloModel.aceptar(DtoMapper.toArticuloDto(articulo));
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
			userModel.addUser(new UserDto(dto.getNombre(), "Revisor", dto.getIdRevisor()));
			return true;
		}
		return false;
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

		if (getNumeroDeRevisoresAsignados(articulo) == 3) {
			cambiarEstadoArticuloEnRevision(articulo);
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
	 * Cambia el estado de los revisores a no disponibles
	 *
	 * @param revisores
	 */
	private void cambiarEstadoRevisorNoDisponible(RevisorEntity revisor) {

		revisor.setEstado(RevisorEntity.NO_DISPONIBLE);

		revisoresModel.update(DtoMapper.toRevisorDto(revisor));

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
	 * Metodo que genera las revisiones a partir del articulo, los revisores y la
	 * fecha
	 *
	 * @return
	 */
	private void generarRevision(RevisorEntity revisor, ArticuloEntity articulo, String fecha) {

		revisionModel.add(DtoMapper.toRevisionDto(revisor, articulo, fecha, RevisionEntity.PENDIENTE));
	}

	public void generarSegundaRevision(int idArticulo, int idRevisor, String fecha) {
		revisionModel.generarSegundaRevision(idArticulo, idRevisor, fecha);
	}

	public List<ArticuloEntity> getArticulos() {
		return EntityAssembler.toArticuloEntityList(articuloModel.getArticulos());
	}

	public List<ArticuloEntity> getArticulosFiltradoAutor(String autor) {
		return EntityAssembler.toArticuloEntityList(articuloModel.getArticulosFiltradoAutor(autor));
	}

	public List<ArticuloEntity> getArticulosFiltradoTitulo(String titulo) {
		return EntityAssembler.toArticuloEntityList(articuloModel.getArticulosFiltradoTitulo(titulo));
	}

	public List<ArticuloEntity> getArticulosTomarDecision() {
		return EntityAssembler.toArticuloEntityList(articuloModel.getArticulosTomarDecision());
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

	public List<RevisionEntity> getRevisionesArticulo(ArticuloEntity articulo) {
		return EntityAssembler
				.toRevisionEntityList(revisionModel.getRevisionesDeUnArticulo(DtoMapper.toArticuloDto(articulo)));
	}

	public List<RevisionEntity> getRevisionesArticuloDeUnRevisor(int idArticulo, int idRevisor) {
		return EntityAssembler
				.toRevisionEntityList(revisionModel.getRevisionesArticuloDeUnRevisor(idArticulo, idRevisor));
	}

	public List<RevisionEntity> getRevisionesFiltradas(int idArticulo, int numeroRevision) {
		return EntityAssembler
				.toRevisionEntityList(revisionModel.getRevisionesFiltradoNumeroRevision(idArticulo, numeroRevision));
	}

//	-------------------------------------------

	public List<RevisionEntity> getRevisionPorNumeroRevision(int numeroRevision, int idRevisor, int idArticulo) {
		return EntityAssembler.toRevisionEntityList(
				revisionModel.getRevisionPorNumeroRevision(idArticulo, idRevisor, numeroRevision));
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

			if (!r.getEstadoDeLaPropuesta().equals(RevisionEntity.RECHAZADA)) {
				revisores.add(EntityAssembler.toRevisorEntity(revisoresModel.findById(r.getIdRevisor())));
			}

		}

		return revisores;

	}

	public List<RevisorEntity> getRevisoresDisponibles() {

		return EntityAssembler.toRevisorEntityList(revisoresModel.getRevisoresDisponibles());
	}

	/**
	 * Devuelve una lista con todos los revisores disponibles
	 *
	 * @return
	 */
	public List<RevisorEntity> getRevisoresDisponibles(ArticuloEntity articulo) {

		List<RevisorEntity> revisoresDisponibles = EntityAssembler
				.toRevisorEntityList(revisoresModel.getRevisoresDisponibles());

		return revisoresDisponibles.stream()
				.filter(r -> revisionModel.findRevisionRechazada(articulo.getIdArticulo(), r.getId()).isEmpty())
				.collect(Collectors.toList());

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

	public void rechazarArticulo(ArticuloEntity articulo) {
		articuloModel.rechazar(DtoMapper.toArticuloDto(articulo));
	}

	public void rechazarDefinitivimenteArticulo(ArticuloEntity articulo) {
		articuloModel.rechazarDefinitivamente(DtoMapper.toArticuloDto(articulo));

	}

	/**
	 * Abre debate del articulo con la fecha asignada
	 *
	 * @param selectedValue
	 * @param selectedItem
	 */
	public void abrirDebate(ArticuloEntity articulo, LocalDate date) {

		String idDebate = debateModel.abrirDebate(articulo.getIdArticulo(), date);

		articuloModel.cambiarEstadoEnDebate(articulo);

		debateModel.escribirMensaje(idDebate, "El debate ha sido abierto con fecha límite: " + date.toString());
	}
}
