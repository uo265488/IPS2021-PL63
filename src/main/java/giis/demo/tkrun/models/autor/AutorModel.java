package giis.demo.tkrun.models.autor;

import java.util.List;
import giis.demo.tkrun.models.dtos.ArticuloDto;
import giis.demo.util.Database;

public class AutorModel {

	private Database db = new Database();

	public List<ArticuloDto> articulosDeUnAutor(int id) {
		// validaciones (en este caso nada)
		String sql = "select titulo, primerAutor, estado "
				+ "from articulosDeAutores, articulo "
				+ "where idAutor = ? and articulo.idArticulo = articulosDeAutores.idArticulo";
		return db.executeQueryPojo(ArticuloDto.class, sql, id);
		
	}
	
	public List<ArticuloDto> articulosAceptadosSinVersionDefinitiva(int id) {
		// validaciones (en este caso nada)
		String sql = "select articulo.idArticulo, titulo, primerAutor "
				+ "from articulosDeAutores, articulo "
				+ "where idAutor = ? and articulo.idArticulo = articulosDeAutores.idArticulo and estado = 'aceptado' and versionDefinitiva = false";
		return db.executeQueryPojo(ArticuloDto.class, sql, id);
	}
	
	public void enviarVersionDefinitiva(int id) {
		String sql = "update articulo set firma = true, versionDefinitiva = true where idArticulo = ?";
		db.executeUpdate(sql, id);
	}

}
