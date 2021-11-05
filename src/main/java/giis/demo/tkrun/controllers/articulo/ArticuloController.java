package giis.demo.tkrun.controllers.articulo;

import java.util.List;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.models.articulo.ArticuloModel;
import giis.demo.tkrun.models.autor.AutorModel;
import giis.demo.tkrun.models.autoresSecundarios.AutoresSecundariosModel;
import giis.demo.util.DtoMapper;
import giis.demo.util.EntityAssembler;

public class ArticuloController {

	private ArticuloModel artModel = new ArticuloModel();
	private AutorModel autorModel =  new AutorModel();
	private AutoresSecundariosModel secundariosModel = new AutoresSecundariosModel();

	public ArticuloController() {
		this.artModel = new ArticuloModel();
	}

	/**
	 * Obtiene del ArticuloModel una lista con los articulos nuevos
	 * 
	 * @return List de ArticuloEntity
	 */
	public List<ArticuloEntity> getArticulosNuevos() {
		return EntityAssembler.toArticuloEntityList(artModel.listArticulosNuevos());
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

	public void aceptarArticulo(ArticuloEntity articulo) {
		articulo.setEstado(ArticuloEntity.ACEPTADO);
		artModel.update(DtoMapper.toArticuloDto(articulo));
		;
		;
	}

	public void visualizarArticulo(ArticuloEntity articulo) {
		articulo.setEstado(ArticuloEntity.CON_EL_EDITOR);
		artModel.update(DtoMapper.toArticuloDto(articulo));
	}

	public void enviarDecision(ArticuloEntity articulo, String nuevoEstado) {
		asignarCartaDecision(articulo);
		articulo.setEstado(nuevoEstado);
		artModel.update(DtoMapper.toArticuloDto(articulo));
	}

	private void asignarCartaDecision(ArticuloEntity articulo) {
		String carta = "'" + articulo.getTitulo() + "-" + articulo.getPrimerAutor() + "-decision.pdf'";
		articulo.setCartaDecision(carta);
		artModel.update(DtoMapper.toArticuloDto(articulo));
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

	// ----------------------------OSCAR----------------------------------------------------------------------------------

	/**
	 * Devuelve una cadena con los autores secundarios del articulo
	 * 
	 * @param idArticulo
	 * @return
	 */
	public String getOtrosAutores(String idArticulo) {
		
		if ( secundariosModel.getAutoresSecundariosByIdArticulo(idArticulo).size() > 0) {
			return "" + secundariosModel.getAutoresSecundariosByIdArticulo(idArticulo)
	        .stream()
	        .map(aut -> autorModel.findByIdAutor(aut.getIdAutor()))
	        .map(a -> a.toString() + " - ")
			.reduce(String::concat).get();
		} 

		return "El artículo no tiene autores secundarios. ";
	}

}
