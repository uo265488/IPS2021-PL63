package giis.demo.tkrun.models.articulo;

import java.util.List;

import giis.demo.tkrun.models.dtos.ArticuloDto;
import giis.demo.util.Database;

public class ArticuloModel {

	private Database db = new Database();

	/**
	 * Actualizar articulo en funcion del id
	 * @param articuloDto
	 */
	public void update(ArticuloDto articuloDto) {
		// validaciones (en este caso nada)
		String sql = "update articulos set cartaPresentacion = ?, CVAutor = ?, estado=?, ficheroFuente = ?,"
				+ " firma=?, CVAutor = ?, otrosAutores=?, palabrasClave = ?, primerAutor=?, resumen = ?, titulo=? where id = ?";

		db.executeQueryPojo(ArticuloDto.class, sql, articuloDto.getCartaPresentacion(), articuloDto.getCVAutor(),
				articuloDto.getEstado(), articuloDto.getFicheroFuente(), articuloDto.getFirma(),
				articuloDto.getOtrosAutores(), articuloDto.getPalabrasClave(), articuloDto.getPrimerAutor(),
				articuloDto.getResumen(), articuloDto.getTitulo(), articuloDto.getId());

	}
	
	/**
	 * Listado de los articulos nuevos
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

}
