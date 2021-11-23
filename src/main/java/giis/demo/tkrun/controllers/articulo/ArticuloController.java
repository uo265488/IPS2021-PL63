package giis.demo.tkrun.controllers.articulo;

import java.util.ArrayList;
import java.util.List;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisionEntity;
import giis.demo.tkrun.models.articulo.ArticuloModel;
import giis.demo.tkrun.models.autor.AutorModel;
import giis.demo.tkrun.models.autoresSecundarios.AutoresSecundariosModel;
import giis.demo.tkrun.models.dtos.ArticuloDto;
import giis.demo.tkrun.models.revision.RevisionModel;
import giis.demo.util.DtoMapper;
import giis.demo.util.EntityAssembler;

public class ArticuloController {

	private ArticuloModel artModel;
	private AutoresSecundariosModel secundariosModel = new AutoresSecundariosModel();
	private AutorModel autorModel = new AutorModel();
	private RevisionModel revisionesModel = new RevisionModel();

	public ArticuloController() {
		this.artModel = new ArticuloModel();
	}

	public void aceptarArticulo(ArticuloEntity articulo) {
		articulo.setEstado(ArticuloEntity.ACEPTADO);
		artModel.update(DtoMapper.toArticuloDto(articulo));
	}

	public void actualizarArticulo(ArticuloEntity articulo) {
		artModel.update(DtoMapper.toArticuloDto(articulo));
	}

	private void asignarCartaDecision(ArticuloEntity articulo) {
		String carta = "'" + articulo.getTitulo() + "-" + articulo.getPrimerAutor() + "-decision.pdf'";
		articulo.setCartaDecision(carta);
		artModel.update(DtoMapper.toArticuloDto(articulo));
	}

	public void enviarDecision(ArticuloEntity articulo, String nuevoEstado) {
		asignarCartaDecision(articulo);
		articulo.setEstado(nuevoEstado);
		artModel.update(DtoMapper.toArticuloDto(articulo));
	}

	public ArticuloEntity findArticulo(String titulo, String autor) {
		return EntityAssembler.toArticuloEntity(artModel.findArticulo(titulo, autor));
	}
	
	public ArticuloEntity findArticulo(int idArt) {
		List<ArticuloDto> lista = artModel.getArticulo(idArt);
		if(lista.isEmpty())
			return null;
		return EntityAssembler.toArticuloEntity(lista.get(0));
	}

	public ArticuloEntity getArticulo(int idArt) {
		List<ArticuloDto> articulos = artModel.getArticulo(idArt);
		if (articulos.isEmpty()) {
			return null;
		} else {
			return EntityAssembler.toArticuloEntity(articulos.get(0));
		}
	}

	public ArticuloEntity getArticuloById(int idArticulo) {
		return EntityAssembler.toArticuloEntity(artModel.findById(idArticulo).get(0));
	}

	/**
	 * Obtiene del ArticuloModel una lista con los articulos nuevos
	 *
	 * @return List de ArticuloEntity
	 */
	public List<ArticuloEntity> getArticulosNuevos() {
		return EntityAssembler.toArticuloEntityList(artModel.listArticulosNuevos());
	}

	public List<ArticuloEntity> getArticulosParaReasignar() {
		List<RevisionEntity> revisiones = getRevisionesRechazadas();
		List<ArticuloEntity> articulos = new ArrayList<>();

		for (RevisionEntity r : revisiones) {
			System.out.println(revisionesModel.getRevisionesPendientesDeUnArticulo(r.getIdArticulo()).size()
					+ revisionesModel.getRevisionesAsignadasDeUnArticulo(r.getIdArticulo()).size());
			if ((revisionesModel.getRevisionesPendientesDeUnArticulo(r.getIdArticulo()).size()
					+ revisionesModel.getRevisionesAsignadasDeUnArticulo(r.getIdArticulo()).size()) < 3) {
				articulos.add(EntityAssembler.toArticuloEntity(artModel.findById(r.getIdArticulo()).get(0)));
			}
		}

		return articulos;

	}

	// --------------------OSCAR------------------
	/**
	 * Devuelve una cadena con los autores secundarios del articulo
	 *
	 * @param idArticulo
	 * @return
	 */
	public String getOtrosAutores(String idArticulo) {

		if (secundariosModel.getAutoresSecundariosByIdArticulo(idArticulo).size() > 0) {
			return "" + secundariosModel.getAutoresSecundariosByIdArticulo(idArticulo).stream()
					.map(aut -> autorModel.findById(aut.getIdAutor())).map(a -> a.toString() + " - ")
					.reduce(String::concat).get();
		}

		return "El artículo no tiene autores secundarios. ";
	}

	public List<RevisionEntity> getRevisionesRechazadas() {

		return EntityAssembler.toRevisionEntityList(revisionesModel.findRevisionesRechazadas());
	}

	/**
	 * Se cambia el estado de articulo a publicado, y se añade la carta de decision
	 * comunicando al autor que el articulo ha sido publicado
	 *
	 * @param articulo a publicar
	 * @param DOI
	 * @param fecha
	 * @param volumen
	 */
	public void publicarArticulo(ArticuloEntity articulo, String DOI, String fecha, int volumen) {
		articulo.setEstado(ArticuloEntity.PUBLICADO);
		articulo.setCartaDecision("'" + articulo.getTitulo() + "-" + articulo.getPrimerAutor() + "-PUBLICADO.pdf'");
		articulo.setDOI(DOI);
		articulo.setFecha(fecha);
		articulo.setVolumen(volumen);
		artModel.publicar(DtoMapper.toArticuloDto(articulo));
	}

	/**
	 * Rechaza el articulo que recibe por parametro
	 *
	 * @param selectedItem
	 */
	public void rechazarArticulo(ArticuloEntity articulo) {
		articulo.setEstado(ArticuloEntity.RECHAZADO);
		artModel.update(DtoMapper.toArticuloDto(articulo));
	}

	public void visualizarArticulo(ArticuloEntity articulo) {
		articulo.setEstado(ArticuloEntity.CON_EL_EDITOR);
		artModel.update(DtoMapper.toArticuloDto(articulo));
	}
}
