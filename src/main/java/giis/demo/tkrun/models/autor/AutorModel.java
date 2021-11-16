package giis.demo.tkrun.models.autor;

import java.util.List;

import giis.demo.tkrun.models.dtos.ArticuloDeAutorDto;
import giis.demo.tkrun.models.dtos.ArticuloDto;
import giis.demo.tkrun.models.dtos.AutorDto;
import giis.demo.util.Database;

public class AutorModel {

    private Database db = new Database();

    public List<ArticuloDto> articulosDeUnAutor(String id) {
	// validaciones (en este caso nada)
	String sql = "select a.idArticulo, a.titulo, a.primerAutor, a.estado, a.resumen, a.palabrasClave, a.ficheroFuente, a.cartaPresentacion, a.CV, a.firma, a.vecesRevisado"
		+ " from autoressecundarios, articulos as a "
		+ "where idAutor = ? and a.idArticulo = autoressecundarios.idArticulo";
	return db.executeQueryPojo(ArticuloDto.class, sql, id);
    }

    public List<ArticuloDto> articulosAceptadosSinVersionDefinitiva(String id) {
	// validaciones (en este caso nada)
	String sql = "select * " + "from autoressecundarios, articulos "
		+ "where idAutor = ? and articulos.idArticulo = autoressecundarios.idArticulo and estado = 'aceptado' and versionDefinitiva = false";
	return db.executeQueryPojo(ArticuloDto.class, sql, id);
    }

    public void enviarVersionDefinitiva(String id) {
	String sql = "update articulos set firma = true, versionDefinitiva = true where idArticulo = ?";
	db.executeUpdate(sql, id);
    }

    public AutorDto findAutor(String nombre, String dni) {
	String sql = "select * from autores where nombre = ? and dni = ?";
	List<AutorDto> list = db.executeQueryPojo(AutorDto.class, sql, nombre, dni);

	if (list.isEmpty()) {
	    return null;
	} else {
	    return list.get(0);
	}
    }

    public AutorDto findById(String id) {
	String sql = "select * from autores where idAutor = ?";
	List<AutorDto> list = db.executeQueryPojo(AutorDto.class, sql, id);

	if (list.isEmpty()) {
	    return null;
	} else {
	    return list.get(0);
	}
    }

    public void addAutor(AutorDto autor) {
	String sql = "insert into autores(idAutor, nombre, dni) values (?, ?, ?)";
	db.executeUpdate(sql, autor.getIdAutor(), autor.getNombre(), autor.getDni());

    }

    public List<ArticuloDeAutorDto> findOtrosAutores(String idArticulo, String id_autor) {
	String sql = "select * from autoressecundarios where idArticulo = ? and idAutor <> ?";

	return db.executeQueryPojo(ArticuloDeAutorDto.class, sql, idArticulo, id_autor);
    }

    public AutorDto findByIdArticulo(String idArticulo) {
	String sql = "select a.idAutor, a.nombre, a.dni from autores as a, autoressecundarios as s where s.idArticulo = ? and s.idAutor = a.idAutor and s.tipoAutor = 'PRINCIPAL'";
	List<AutorDto> list = db.executeQueryPojo(AutorDto.class, sql, idArticulo);
	if (list.isEmpty()) {
	    return null;
	} else {
	    return list.get(0);
	}
    }

}
