package giis.demo.tkrun.models.autor;

import java.util.ArrayList;
import java.util.List;

import giis.demo.tkrun.models.dtos.ArticuloDeAutorDto;
import giis.demo.tkrun.models.dtos.ArticuloDto;
import giis.demo.util.Database;

public class AutorModel {

	private Database db = new Database();

	public List<ArticuloDto> articulosDeUnAutor(int id) {
		// validaciones (en este caso nada)
		String sql = "select idArticulo from articulosDeAutores where idAutor = ?";
		List<ArticuloDeAutorDto> prueba = db.executeQueryPojo(ArticuloDeAutorDto.class, sql, id);
		List<ArticuloDto> articulos = new ArrayList<ArticuloDto>();
		sql = "select * from articulo where idArticulo = ?";
		for(ArticuloDeAutorDto idArticulo: prueba) {
			int idArt = idArticulo.getIdArticulo();
			articulos.add(db.executeQueryPojo(ArticuloDto.class, sql, idArt).get(0));
		}
		return articulos;
		
	}
}
