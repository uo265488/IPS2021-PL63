package giis.demo.tkrun.models.autor;

import java.util.List;

import giis.demo.tkrun.models.dtos.ArticuloDto;
import giis.demo.tkrun.models.dtos.AutorDto;
import giis.demo.util.Database;

public class AutorModel {

	private Database db = new Database();

	public List<ArticuloDto> update(AutorDto autorDto) {
		// validaciones (en este caso nada)
		String sql = "select * from articulos where articulos.idAutorPrin = ?";

		return db.executeQueryPojo(ArticuloDto.class, sql, autorDto.getId());
		
	}
}
