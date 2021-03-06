package giis.demo.tkrun.models.articulo;

import java.util.ArrayList;
import java.util.List;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.models.dtos.ArticuloDto;
import giis.demo.tkrun.models.dtos.RevisionDto;
import giis.demo.util.Database;

public class ArticuloModel {

    private Database db = new Database();

    public void aceptar(ArticuloDto articuloDto) {
	int vecesRev = articuloDto.getVecesRevisado() + 1;
	// validaciones (en este caso nada)
	String sql = "update articulos set estado = 'aceptado', vecesRevisado=? where idArticulo = ?";

	db.executeUpdate(sql, vecesRev, articuloDto.getIdArticulo());

    }

    public void actualizarBorrador(ArticuloDto articuloDto) {

	String sql = "update articulos set titulo = ?, resumen = ?, palabrasClave = ?,ficheroFuente = ? "
		+ ", cartaPresentacion = ?, CV = ?, firma = ? where idArticulo = ?";

	db.executeUpdate(sql, articuloDto.getTitulo(), articuloDto.getResumen(), articuloDto.getPalabrasClave(),
		articuloDto.getFicheroFuente(), articuloDto.getCartaPresentacion(), articuloDto.getCV(),
		articuloDto.isFirma(), articuloDto.getIdArticulo());
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

    public void asignarAutor(ArticuloDto articulo, String id_autor) {
	String sql = "insert into autoressecundarios(idArticulo, idAutor, tipoAutor) values (?, ?, ?)";
	db.executeUpdate(sql, articulo.getIdArticulo(), id_autor, "PRINCIPAL");
    }

    public void asignarOtroAutor(String id_articulo, String id_segundo_Autor) {
	String sql = "insert into autoressecundarios(idArticulo, idAutor, tipoAutor) values (?, ?, ?)";
	db.executeUpdate(sql, id_articulo, id_segundo_Autor, "SECUNDARIO");
    }

    public void crearArticulo(ArticuloDto articulo) {
	String sql_into_articulos = "insert into articulos values (?, ?, ?, 'con el editor', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	db.executeUpdate(sql_into_articulos, articulo.getIdArticulo(), articulo.getTitulo(), articulo.getPrimerAutor(),
		articulo.getResumen(), articulo.getPalabrasClave(), articulo.getFicheroFuente(),
		articulo.getCartaPresentacion(), articulo.getCV(), articulo.isFirma(), articulo.getVecesRevisado(),
		articulo.isVersionDefinitiva(), articulo.getDOI(), articulo.getFecha(), articulo.getVolumen(), false);
    }

    public void crearBorrador(ArticuloDto articulo) {
	String sql_into_articulos = "insert into articulos values (?, ?, ?,'borrador', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	db.executeUpdate(sql_into_articulos, articulo.getIdArticulo(), articulo.getTitulo(), articulo.getPrimerAutor(),
		articulo.getResumen(), articulo.getPalabrasClave(), articulo.getFicheroFuente(),
		articulo.getCartaPresentacion(), articulo.getCV(), articulo.isFirma(), articulo.getVecesRevisado(),
		articulo.isVersionDefinitiva(), articulo.getDOI(), articulo.getFecha(), articulo.getVolumen(), false);
    }

    public void enviarBorrador(ArticuloDto articuloDto) {
	String remove = "delete from articulos where idArticulo = ?";
	String remove_autor = "delete from autoressecundarios where idArticulo = ?";
	db.executeUpdate(remove, articuloDto.getIdArticulo());
	db.executeUpdate(remove_autor, articuloDto.getIdArticulo());

	crearArticulo(articuloDto);

    }

    public ArticuloDto findArticulo(String nombre, String autor) {
	String sql = "select * from articulos where titulo = ? and primerAutor = ?";
	List<ArticuloDto> list = db.executeQueryPojo(ArticuloDto.class, sql, nombre, autor);

	if (list.isEmpty()) {
	    return null;
	} else {
	    return list.get(0);
	}
    }

    public ArticuloDto findById(String idArticulo) {

	String sql = "SELECT * from articulos where idArticulo=?";

	return db.executeQueryPojo(ArticuloDto.class, sql, idArticulo).get(0);
    }

    public List<ArticuloDto> findByIdRevisor(String idRev) {
	String sql = "select * from articulos as a, autoressecundarios as s where a.idArticulo = s.idArticulo and s.idAutor = ?";

	return db.executeQueryPojo(ArticuloDto.class, sql, idRev);
    }

    public List<ArticuloDto> getArticulo(String idArt) {
	String sql = "SELECT * from articulos where idArticulo = ?";
	return db.executeQueryPojo(ArticuloDto.class, sql, idArt);
    }

    /**
     * Obtiene la lista de articulos que han sido enviados por los autores.
     *
     * @return
     */
    public List<ArticuloDto> getArticulos() {
	String sql = "SELECT * from articulos where estado <> 'borrador'";
	return db.executeQueryPojo(ArticuloDto.class, sql);
    }

    public List<ArticuloDto> getArticulosAsignados(String id) {
	String sql = "select * from articulos a, revisiones r where r.idRevisor = ? and a.idArticulo = r.idArticulo and r.enviarAlEditor = true";

	return db.executeQueryPojo(ArticuloDto.class, sql, id);
    }

    public List<ArticuloDto> getArticulosEnDebate(String id) {
	String sql = "select * from articulos a, revisiones r where r.idRevisor = ? and a.idArticulo = r.idArticulo and a.estado = 'en debate'";

	return db.executeQueryPojo(ArticuloDto.class, sql, id);
    }

    public List<ArticuloDto> getArticulosFiltradoAutor(String autor) {
	String sql = "SELECT * from articulos where estado <> 'borrador' and lower(primerAutor) = ?";

	return db.executeQueryPojo(ArticuloDto.class, sql, autor.toLowerCase());
    }

    public List<ArticuloDto> getArticulosFiltradoTitulo(String titulo) {
	String sql = "SELECT * from articulos where estado <> 'borrador' and lower(titulo) = ?";

	return db.executeQueryPojo(ArticuloDto.class, sql, titulo.toLowerCase());
    }

    public List<ArticuloDto> getArticulosSinResponder(String id) {
	String sql = "select * from articulos a, revisiones r where r.idRevisor = ? and a.idArticulo = r.idArticulo and r.estadoDeLaPropuesta = 'PENDIENTE'";

	return db.executeQueryPojo(ArticuloDto.class, sql, id);
    }

    // public List<ArticuloDto> getArticulosAsignados(int id) {
    // String sql = "select * from articulos a, revisiones r where r.idRevisor = ?
    // and a.idArticulo = r.idArticulo";

    // return db.executeQueryPojo(ArticuloDto.class, sql, id);
    // }

    /**
     * Obtiene la lista de articulos que deben ser evaluados por el editor
     */
    public List<ArticuloDto> getArticulosTomarDecision() {
	String sql = "SELECT * from articulos where vecesRevisado <= 2 and (estado = 'con el editor' or (estado = 'aceptado con cambios menores' and pendienteDeCambios = false))";
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
		if (estaRevisado) {
		    idsArticulosRevisados.add("" + infoArtRevisados.get(0).getIdArticulo());
		}
	    } else if (infoArtRevisados.size() == 6) {
		boolean estaRevisado = true;
		int count = 0;
		for (RevisionDto revision : infoArtRevisados) {
		    if (count >= 3) {
			if (!revision.isEnviarAlEditor()) {
			    estaRevisado = false;
			    break;
			}
		    }
		    count++;
		}
		if (estaRevisado) {
		    idsArticulosRevisados.add("" + infoArtRevisados.get(0).getIdArticulo());
		}
	    }
	}

	sql = "SELECT * from articulos where idArticulo = ?";
	idsArticulos.clear();
	for (String id : idsArticulosRevisados) {
	    ArticuloDto art = db.executeQueryPojo(ArticuloDto.class, sql, id).get(0);
	    idsArticulos.add(art);
	}

	return idsArticulos;
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

    /**
     * @param articuloDto
     */
    /*
     * public void modificarArticulo(ArticuloDto articuloDto) { String sql =
     * "update articulos set titulo = ?, resumen = ?, palabrasClave = ?, ficheroFuente = ? "
     * +
     * ", cartaPresentacion = ?, CV = ?, firma = ?, pendienteDeCambios = ? where idArticulo = ?"
     * ;
     *
     * db.executeUpdate(sql, articuloDto.getTitulo(), articuloDto.getResumen(),
     * articuloDto.getPalabrasClave(), articuloDto.getFicheroFuente(),
     * articuloDto.getCartaPresentacion(), articuloDto.getCV(),
     * articuloDto.isFirma(), articuloDto.isPendienteDeCambios(),
     * articuloDto.getIdArticulo());
     *
     * db.executeUpdate(sql, articuloDto.getTitulo(), articuloDto.getResumen(),
     * articuloDto.getPalabrasClave(), articuloDto.getFicheroFuente(),
     * articuloDto.getCartaPresentacion(), articuloDto.getCV(),
     * articuloDto.isFirma(), ArticuloEntity.CON_EL_EDITOR,
     * articuloDto.getIdArticulo()); } else { sql =
     * "update articulos set titulo = ?, resumen = ?, palabrasClave = ?, ficheroFuente = ? "
     * + ", cartaPresentacion = ?, CV = ?, firma = ? where idArticulo = ?";
     *
     * db.executeUpdate(sql, articuloDto.getTitulo(), articuloDto.getResumen(),
     * articuloDto.getPalabrasClave(), articuloDto.getFicheroFuente(),
     * articuloDto.getCartaPresentacion(), articuloDto.getCV(),
     * articuloDto.isFirma(), articuloDto.getIdArticulo());
     *
     * } }
     */

    public void modificarArticulo(ArticuloDto articuloDto) {
	String sql = "";

	if (articuloDto.getEstado().equals(ArticuloEntity.ACEPTADO_CAMBIOS_MAYORES)) {
	    sql = "update articulos set titulo = ?, resumen = ?, estado = ?, palabrasClave = ?, ficheroFuente = ? "
		    + ", cartaPresentacion = ?, CV = ?, firma = ?, pendienteDeCambios = ? where idArticulo = ?";

	    db.executeUpdate(sql, articuloDto.getTitulo(), articuloDto.getResumen(), ArticuloEntity.EN_REVISION,
		    articuloDto.getPalabrasClave(), articuloDto.getFicheroFuente(), articuloDto.getCartaPresentacion(),
		    articuloDto.getCV(), articuloDto.isFirma(), articuloDto.isPendienteDeCambios(),
		    articuloDto.getIdArticulo());
	} else {
	    sql = "update articulos set titulo = ?, resumen = ?, palabrasClave = ?, ficheroFuente = ? "
		    + ", cartaPresentacion = ?, CV = ?, firma = ?, pendienteDeCambios = ? where idArticulo = ?";

	    db.executeUpdate(sql, articuloDto.getTitulo(), articuloDto.getResumen(), articuloDto.getPalabrasClave(),
		    articuloDto.getFicheroFuente(), articuloDto.getCartaPresentacion(), articuloDto.getCV(),
		    articuloDto.isFirma(), articuloDto.isPendienteDeCambios(), articuloDto.getIdArticulo());
	}

    }

    public void publicar(ArticuloDto articulo) {
	String sql = "update articulos set estado = 'publicado', fecha=?, DOI = ?, volumen = ? where idArticulo = ?";

	db.executeUpdate(sql, articulo.getFecha(), articulo.getDOI(), articulo.getVolumen(), articulo.getIdArticulo());
    }

    public void rechazar(ArticuloDto articuloDto) {
	int vecesRev = articuloDto.getVecesRevisado() + 1;
	// validaciones (en este caso nada)
	String sql = "update articulos set estado = 'rechazado', vecesRevisado=? where idArticulo = ?";

	db.executeUpdate(sql, vecesRev, articuloDto.getIdArticulo());

    }

    public void rechazarDefinitivamente(ArticuloDto articuloDto) {

	String sql = "update articulos set estado = 'rechazado', vecesRevisado=2 where idArticulo = ?";

	db.executeUpdate(sql, articuloDto.getIdArticulo());

    }

    /**
     * Actualizar articulo en funcion del id
     *
     * @param articuloDto
     */
    public void update(ArticuloDto articuloDto) {
	// validaciones (en este caso nada)
	String sql = "update articulos set cartaPresentacion = ?, CV = ?, estado=?, ficheroFuente = ?, palabrasClave=?, primerAutor=?, resumen=?, titulo=?, vecesRevisado=?, firma=?, versionDefinitiva=?, DOI=?, fecha=?, volumen=?, pendienteDeCambios=? where idArticulo = ?";

	db.executeUpdate(sql, articuloDto.getCartaPresentacion(), articuloDto.getCV(), articuloDto.getEstado(),
		articuloDto.getFicheroFuente(), articuloDto.getPalabrasClave(), articuloDto.getPrimerAutor(),
		articuloDto.getResumen(), articuloDto.getTitulo(), articuloDto.getVecesRevisado(),
		articuloDto.isFirma(), articuloDto.isVersionDefinitiva(), articuloDto.getDOI(), articuloDto.getFecha(),
		articuloDto.getVolumen(), articuloDto.isPendienteDeCambios(), articuloDto.getIdArticulo());

    }

    public List<ArticuloDto> getArticulosEnDebate() {
	String sql = "select * from Articulos where estado=?";
	return db.executeQueryPojo(ArticuloDto.class, sql, ArticuloEntity.EN_DEBATE);
    }

    public void cerrarDebate(String idArticulo) {

	String sql = "update articulos set estado  = ? where idArticulo = ?";

	db.executeUpdate(sql, ArticuloEntity.EN_REVISION, idArticulo);

    }

    public List<ArticuloDto> getArticulosParaPublicar() {
	String sql = "select * from Articulos where estado=? and versionDefinitiva = true";
	return db.executeQueryPojo(ArticuloDto.class, sql, ArticuloEntity.ACEPTADO);
    }

//	/**
//	 * Devuelve una lista con los articulos debatibles
//	 *
//	 * @return
//	 */
//	public List<ArticuloDto> getArticulosDebatibles() {
//
//		String sql = "SELECT * from articulos where debatible=" ;
//
//		return db.executeQueryPojo(ArticuloDto.class, sql);
//	}

    public void cambiarEstadoEnDebate(ArticuloEntity articulo) {

	String sql = "update articulos set estado=? where idArticulo = ?";

	db.executeUpdate(sql, ArticuloEntity.EN_DEBATE, articulo.getIdArticulo());

    }

}
