package giis.demo.tkrun.models.articulo;

import java.util.ArrayList;
import java.util.List;

import giis.demo.tkrun.models.dtos.ArticuloDto;
import giis.demo.tkrun.models.dtos.RevisionDto;
import giis.demo.util.Database;

public class ArticuloModel {

	private Database db = new Database();

	/**
	 * Actualizar articulo en funcion del id
	 * 
	 * @param articuloDto
	 */
	public void update(ArticuloDto articuloDto) {
		// validaciones (en este caso nada)
		String sql = "update articulos set cartaPresentacion = ?, CV = ?, estado=?, ficheroFuente = ?, palabrasClave=?, primerAutor=?, resumen=?, titulo=?, vecesRevisado=?, firma=?, versionDefinitiva=?, DOI=?, fecha=?, volumen=? where idArticulo = ?";

		db.executeUpdate(sql, articuloDto.getCartaPresentacion(), articuloDto.getCV(), articuloDto.getEstado(),
				articuloDto.getFicheroFuente(), articuloDto.getPalabrasClave(),
				articuloDto.getPrimerAutor(), articuloDto.getResumen(), articuloDto.getTitulo(),
				articuloDto.getVecesRevisado(), articuloDto.isFirma(), articuloDto.isVersionDefinitiva(),
				articuloDto.getIdArticulo(), articuloDto.getDOI(), articuloDto.getFecha(), articuloDto.getVolumen());

	}

	/**
	 * Listado de los articulos nuevos
	 * 
	 * @return
	 */
	public List<ArticuloDto> listArticulosNuevos() {
		// validaciones (en este caso nada)
		String sql = "select * from articulos where estado=?";

		return db.executeQueryPojo(ArticuloDto.class, sql, "nuevo");
	}

//	/**
//	 * Borrar articulo en funcion del id
//	 * @param articuloDto
//	 */
//	public void delete(ArticuloDto articuloDto) {
//		String sql = "delete from articulos where id=?";
//		
//		db.executeQueryPojo(ArticuloDto.class, sql, articuloDto.getId());
//	}

	/**
	 * Obtiene la lista de articulos que han sido enviados por los autores.
	 * 
	 * @return
	 */
	public List<ArticuloDto> getArticulos() {
		String sql = "SELECT * from articulos where estado = 'con el editor'";

		return db.executeQueryPojo(ArticuloDto.class, sql);
	}

	/**
	 * Obtiene la lista de articulos que deben ser evaluados por el editor
	 */
	public List<ArticuloDto> getArticulosTomarDecision() {
		String sql = "SELECT * from articulos where vecesRevisado <= 1 and estado = 'con el editor'";
		List<ArticuloDto> idsArticulos = db.executeQueryPojo(ArticuloDto.class, sql);

		List<RevisionDto> infoArtRevisados = new ArrayList<RevisionDto>();
		List<String> idsArticulosRevisados = new ArrayList<String>();

		for (ArticuloDto str : idsArticulos) {
			sql = "SELECT * from revisiones where idArticulo = ?";
			infoArtRevisados = db.executeQueryPojo(RevisionDto.class, sql, str.getIdArticulo());
			if (infoArtRevisados.size() == 3) {
				boolean estaRevisado = true;
				for (RevisionDto revision : infoArtRevisados) {
					if (!revision.isEnviarAlEditor()) {
						estaRevisado = false;
						break;
					}
				}
				if (estaRevisado)
					idsArticulosRevisados.add("" + infoArtRevisados.get(0).getIdArticulo());
			}
		}

		sql = "SELECT idArticulo, titulo, primerAutor, vecesRevisado from articulos where idArticulo = ?";

		idsArticulos.clear();
		for (String id : idsArticulosRevisados) {
			ArticuloDto art = db.executeQueryPojo(ArticuloDto.class, sql, id).get(0);
			idsArticulos.add(art);
		}

		return idsArticulos;
	}

	public void aceptar(ArticuloDto articuloDto) {
		int vecesRev = articuloDto.getVecesRevisado() + 1;
		// validaciones (en este caso nada)
		String sql = "update articulos set estado = 'aceptado', vecesRevisado=? where idArticulo = ?";

		db.executeUpdate(sql, vecesRev, articuloDto.getIdArticulo());

	}

	public void rechazar(ArticuloDto articuloDto) {
		int vecesRev = articuloDto.getVecesRevisado() + 1;
		// validaciones (en este caso nada)
		String sql = "update articulos set estado = 'rechazado', vecesRevisado=? where idArticulo = ?";

		db.executeUpdate(sql, vecesRev, articuloDto.getIdArticulo());

	}

	public void crearBorrador(ArticuloDto articulo) {
		String sql_into_articulos = "insert into usuarios values (?, ?, ?, 'borrador', ?, ?, ?, ?, ?, ?, ?, ?)";
		String sql_into_articulosDeAutor = "insert into articulosDeAutores values (?, ?)";

		db.executeUpdate(sql_into_articulos, articulo.getIdArticulo(), articulo.getTitulo(), articulo.getPrimerAutor(),
				articulo.getResumen(), articulo.getPalabrasClave(), articulo.getFicheroFuente(),
				articulo.getCartaPresentacion(), articulo.getCV(), articulo.isFirma(), articulo.getVecesRevisado());

		db.executeUpdate(sql_into_articulosDeAutor, articulo.getIdArticulo(), articulo.getOtrosAutores());
	}

	public void crearArticulo(ArticuloDto articulo) {
		String sql_into_articulos = "insert into usuarios values (?, ?, ?, 'con el editor', ?, ?, ?, ?, ?, ?, ?, ?)";
		String sql_into_articulosDeAutor = "insert into articulosDeAutores values (?, ?)";

		db.executeUpdate(sql_into_articulos, articulo.getIdArticulo(), articulo.getTitulo(), articulo.getPrimerAutor(),
				articulo.getResumen(), articulo.getPalabrasClave(), articulo.getFicheroFuente(),
				articulo.getCartaPresentacion(), articulo.getCV(), articulo.isFirma(), articulo.getVecesRevisado());

		db.executeUpdate(sql_into_articulosDeAutor, articulo.getIdArticulo(), articulo.getOtrosAutores());
	}

	public List<ArticuloDto> getArticulosAsignados(int id) {
		String sql = "select * from articulos a, revisiones r where r.idRevisor = ? and a.idArticulo = r.idArticulo";

		return db.executeQueryPojo(ArticuloDto.class, sql, id);
	}

	public List<ArticuloDto> getArticulosFiltradoTitulo(String titulo) {
		String sql = "SELECT * from articulos where estado <> 'borrador' and titulo = ?";

		return db.executeQueryPojo(ArticuloDto.class, sql, titulo);
	}

	public List<ArticuloDto> getArticulosFiltradoAutor(String autor) {
		String sql = "SELECT * from articulos where estado <> 'borrador' and primerAutor = ?";

		return db.executeQueryPojo(ArticuloDto.class, sql, autor);
	}
	
	public void publicar(ArticuloDto articulo) {
        String sql = "update articulos set estado = 'publicado', fecha=?, DOI = ?, volumen = ? where idArticulo = ?";
       
        db.executeUpdate(sql, articulo.getFecha(), articulo.getDOI(), articulo.getVolumen(), articulo.getIdArticulo());
    }

}
