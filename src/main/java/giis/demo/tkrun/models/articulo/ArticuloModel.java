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

}
