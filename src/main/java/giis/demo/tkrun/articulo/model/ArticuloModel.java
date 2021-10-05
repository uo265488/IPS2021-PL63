package giis.demo.tkrun.articulo.model;

import giis.demo.tkrun.articulo.ArticuloDto;
import giis.demo.tkrun.revisor.RevisorDto;
import giis.demo.util.Database;

public class ArticuloModel {

	private Database db = new Database();

	public void update(ArticuloDto articuloDto) {
		// validaciones (en este caso nada)
		String sql = "update articulos set cartaPresentacion = ?, CVAutor = ?, estado=?, ficheroFuente = ?,"
				+ " firma=?, CVAutor = ?, otrosAutores=?, palabrasClave = ?, primerAutor=?, resumen = ?, titulo=? where id = ?";

		db.executeQueryPojo(RevisorDto.class, sql, articuloDto.getCartaPresentacion(), articuloDto.getCVAutor(),
				articuloDto.getEstado(), articuloDto.getFicheroFuente(), articuloDto.getFirma(),
				articuloDto.getOtrosAutores(), articuloDto.getPalabrasClave(), articuloDto.getPrimerAutor(),
				articuloDto.getResumen(), articuloDto.getTitulo(), articuloDto.getId());

	}

}