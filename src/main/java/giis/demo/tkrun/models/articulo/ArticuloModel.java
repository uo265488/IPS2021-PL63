package giis.demo.tkrun.models.articulo;


import java.util.List;

import giis.demo.tkrun.models.dtos.ArticuloDto;
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
		String sql = "update articulos set cartaPresentacion = ?, CV = ?, estado=?, ficheroFuente = ?, otrosAutores=?, palabrasClave=?, primerAutor=?, resumen=?, titulo=?, vecesRevisado=?, firma=?, versionDefinitiva=? where idArticulo = ?";

		db.executeUpdate(sql, articuloDto.getCartaPresentacion(), articuloDto.getCV(), articuloDto.getEstado(),
				articuloDto.getFicheroFuente(), articuloDto.getOtrosAutores(), articuloDto.getPalabrasClave(),
				articuloDto.getPrimerAutor(), articuloDto.getResumen(), articuloDto.getTitulo(),
				articuloDto.getVecesRevisado(), articuloDto.isFirma(), articuloDto.isVersionDefinitiva(),
				articuloDto.getIdArticulo());

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
	 * @return
	 */
	public List<ArticuloDto> getArticulos() {
		String sql = "SELECT * from articulos where estado <> 'borrador'";

		return db.executeQueryPojo(ArticuloDto.class, sql);
	}
	
	/**
	 * Obtiene la lista de articulos listos para aceptar o rechazar
	 */
	public List<ArticuloDto> getArticulosTomarDecision() {
		// validaciones (en este caso nada)
		String sql = "SELECT id, titulo, autor, estado from articulos where estado = 'con el editor' and vecesRevisado <= 1";
		return db.executeQueryPojo(ArticuloDto.class, sql);

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
	
	public List<ArticuloDto> getArticulosAsignados(int id){
		String sql = "select * from articulos a, revisiones r where r.idRevisor = ? and a.idArticulo = r.idArticulo";
		
		return db.executeQueryPojo(ArticuloDto.class, sql, id);
	}

}
