package giis.demo.tkrun.models.articulo;

import giis.demo.tkrun.models.dtos.ArticuloDto;
import giis.demo.tkrun.models.dtos.RevisorDto;
import giis.demo.util.Database;

public class ArticuloModel {

	private Database db = new Database();

	public void update(ArticuloDto articuloDto) {
		// validaciones (en este caso nada)
		String sql = "update articulos set cartaPresentacion = ?, CVAutor = ?, estado=?, ficheroFuente = ?,"
				+ " firma=?, CVAutor = ?, palabrasClave = ?, primerAutor=?, resumen = ?, titulo=? where id = ?";

		db.executeQueryPojo(RevisorDto.class, sql, articuloDto.getCartaPresentacion(), articuloDto.getCV(),
				articuloDto.getEstado(), articuloDto.getFicheroFuente(), articuloDto.getFirma(), 
				articuloDto.getPalabrasClave(), articuloDto.getPrimerAutor(),
				articuloDto.getResumen(), articuloDto.getTitulo(), articuloDto.getIdArticulo());

	}

}
