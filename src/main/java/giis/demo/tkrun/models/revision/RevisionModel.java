package giis.demo.tkrun.models.revision;

import java.util.List;
import java.util.Optional;

import giis.demo.tkrun.controllers.entities.RevisionEntity;
import giis.demo.tkrun.models.dtos.ArticuloDto;
import giis.demo.tkrun.models.dtos.RevisionDto;
import giis.demo.tkrun.models.dtos.RevisorDto;
import giis.demo.tkrun.models.dtos.SugerenciaDto;
import giis.demo.util.Database;

public class RevisionModel {

    private Database db = new Database();

    /**
     * Añade una revision a la base de datos
     *
     * @param revisionDto
     */
    public void add(RevisionDto revisionDto) {
	String sql = "insert into revisiones(idArticulo, idRevisor, fecha, estadoDeLaPropuesta, numeroRevision) values (?,?,?,?, ?)";

	db.executeUpdate(sql, revisionDto.getIdArticulo(), revisionDto.getIdRevisor(), revisionDto.getFecha(),
		revisionDto.getEstadoDeLaPropuesta(), revisionDto.getNumeroRevision());

    }

    public List<RevisionDto> articulosAceptados(String idArticulo) {
	String sql = "select * " + "from revisiones " + "where idArticulo = ? and estadoDeLaPropuesta = 'ACEPTADA'";
	return db.executeQueryPojo(RevisionDto.class, sql, idArticulo);
    }

    public List<ArticuloDto> articulosSinRevisar(String idRevisor) {
	String sql = "select articulos.idArticulo, titulo, vecesRevisado " + "from revisiones, articulos "
		+ "where idRevisor = ? and enviarAlEditor = false and articulos.idArticulo = revisiones.idArticulo and estado = 'en revision'"
		+ " and estadoDeLaPropuesta = 'ACEPTADA'";

	return db.executeQueryPojo(ArticuloDto.class, sql, idRevisor);

    }

    public void decisionArticulo(String idRev, String idArt, boolean condicion) {
	String decision = "";
	if (condicion) {
	    decision = RevisionEntity.ACEPTADA;
	} else {
	    decision = RevisionEntity.RECHAZADA;
	}
	String sql = "update revisiones set estadoDeLaPropuesta = ?, enviarAlEditor = false where idArticulo = ? and idRevisor = ?";

	db.executeUpdate(sql, decision, idArt, idRev);
    }

    public List<RevisionDto> findAll() {
	String sql = "select * from revisiones";

	return db.executeQueryPojo(RevisionDto.class, sql);
    }

    public RevisorDto findByIdRevisor(String id) {
	String sql = "select * from revisores where idRevisor = ?";
	List<RevisorDto> revisores = db.executeQueryPojo(RevisorDto.class, sql, id);

	if (revisores.isEmpty()) {
	    return null;
	} else {
	    return revisores.get(0);
	}
    }

    /*
     * public void add(RevisionDto revisionDto) { String sql =
     * "insert into revisiones(revisor_id, articulo_id, fecha) values (?,?,?)";
     *
     * db.executeQueryPojo(RevisorDto.class, sql, revisionDto.getRevisor().getId(),
     * revisionDto.getArticulo().getIdArticulo(), revisionDto.getFecha());
     *
     * }
     */

    /**
     * Encuentra revisiones en funcion del idArticulo
     *
     * @param idArticulo
     * @return
     */
    public List<RevisionDto> findByIdArticulo(String idArticulo) {
	String sql = "select * " + "from revisiones " + "where idArticulo = ?";

	return db.executeQueryPojo(RevisionDto.class, sql, idArticulo);
    }

    public List<RevisionDto> findRevisionesRechazadas() {
	String sql = "select * from revisiones where estadoDeLaPropuesta='" + RevisionDto.RECHAZADA + "'";

	return db.executeQueryPojo(RevisionDto.class, sql);
    }

    public Optional<RevisionDto> findRevisionRechazada(String idArticulo, String idRevisor) {

	String sql = "select * from revisiones where idArticulo=? and idRevisor=? and estadoDeLaPropuesta='"
		+ RevisionDto.RECHAZADA + "'";

	if (db.executeQueryPojo(RevisionDto.class, sql, idArticulo, idRevisor).isEmpty()) {
	    return Optional.ofNullable(null);
	}

	return Optional.ofNullable(db.executeQueryPojo(RevisionDto.class, sql, idArticulo, idRevisor).get(0));
    }

    public RevisorDto findRevisor(String nombre, String correo, String especialidad) {
	String sql = "select * from revisores where nombre = ? and correo = ? and especialidad = ?";

	List<RevisorDto> revisores = db.executeQueryPojo(RevisorDto.class, sql, nombre, correo, especialidad);

	if (revisores.isEmpty()) {
	    return null;
	} else {
	    return revisores.get(0);
	}
    }

    public List<SugerenciaDto> findSugeridos(String idArticulo) {
	String sql = "select * from sugerencias where idArticulo = ?";

	return db.executeQueryPojo(SugerenciaDto.class, sql, idArticulo);
    }

    public void generarSegundaRevision(String idArticulo, String idRevisor, String fecha) {
	String sql = "insert into revisiones(idArticulo, idRevisor, numeroRevision, fecha, comentariosAutor, "
		+ "comentariosEditor, decision, enviarAlEditor, estadoDeLaPropuesta) values (?,?,?,?,?,?,?,?,?)";

	db.executeUpdate(sql, idArticulo, idRevisor, 2, fecha, "", "", "", false, "ACEPTADA");

    }

    /**
     * Obtiene todas las revisiones hechas sobre un articulo
     *
     * @param articuloDto
     * @return
     */
    public List<RevisionDto> getComentariosDeRevisionDeUnArticulo(ArticuloDto articulo, RevisorDto revisor) {

	String sql = "select * from revisiones where idArticulo = ? and idRevisor <> ?";

	return db.executeQueryPojo(RevisionDto.class, sql, articulo.getIdArticulo(), revisor.getIdRevisor());
    }

    public RevisionDto getFecha(String idRev, String idArticulo) {
	String sql = "select fecha " + "from revisiones " + "where idRevisor = ? and idArticulo = ?";
	return db.executeQueryPojo(RevisionDto.class, sql, idRev, idArticulo).get(0);
    }

    /**
     * Obtiene la revision atraves del id del articulo y el revisor
     *
     * @param articulo
     * @param revisor
     * @return
     */
    public RevisionDto getRevision(ArticuloDto articulo, RevisorDto revisor) {

	String sql = "select * from revisiones where idArticulo = ? and idRevisor = ?";

	return db.executeQueryPojo(RevisionDto.class, sql, articulo.getIdArticulo(), revisor.getIdRevisor()).get(0);

    }

    public RevisionDto getRevisionByIds(String idArticulo, String idRevisor, int numeroRevision) {
	String sql = "select * from revisiones where idArticulo = ? and idRevisor = ? and numeroRevision = ?";

	return db.executeQueryPojo(RevisionDto.class, sql, idArticulo, idRevisor, numeroRevision).get(0);

    }

    public List<RevisionDto> getRevisionesArticuloDeUnRevisor(String idArticulo, String idRevisor) {
	String sql = "select * from revisiones where idArticulo=? and idRevisor=?";

	return db.executeQueryPojo(RevisionDto.class, sql, idArticulo, idRevisor);
    }

    public List<RevisionDto> getRevisionesAsignadasDeUnArticulo(String idArticulo) {
	String sql = "select * from revisiones where estadoDeLaPropuesta='" + RevisionDto.ACEPTADA
		+ "' and idArticulo=?";

	return db.executeQueryPojo(RevisionDto.class, sql, idArticulo);
    }

    /**
     * Obtiene todas las revisiones hechas sobre un articulo
     *
     * @param articuloDto
     * @return
     */
    public List<RevisionDto> getRevisionesDeUnArticulo(ArticuloDto articulo) {

	String sql = "select * from Revisiones where idArticulo = ?";

	return db.executeQueryPojo(RevisionDto.class, sql, articulo.getIdArticulo());
    }

    public List<RevisionDto> getRevisionesFiltradoNumeroRevision(String idArticulo, int numeroRevision) {
	String sql = "select * from revisiones where idArticulo=? and numeroRevision=?";

	return db.executeQueryPojo(RevisionDto.class, sql, idArticulo, numeroRevision);
    }

    public List<RevisionDto> getRevisionesPendientesDeUnArticulo(String idArticulo) {
	String sql = "select * from revisiones where estadoDeLaPropuesta='" + RevisionDto.PENDIENTE
		+ "' and idArticulo=?";

	return db.executeQueryPojo(RevisionDto.class, sql, idArticulo);
    }

    public List<RevisionDto> getRevisionPorNumeroRevision(String idArticulo, String idRevisor, int numeroRevision) {
	String sql = "select * from revisiones where idArticulo=? and idRevisor=? and numeroRevision=?";

	return db.executeQueryPojo(RevisionDto.class, sql, idArticulo, idRevisor, numeroRevision);
    }

    public List<RevisionDto> numeroRevisiones(String idRev, String idArt) {
	String sql = "select * " + "from revisiones " + "where idRevisor = ? and idArticulo = ?";

	return db.executeQueryPojo(RevisionDto.class, sql, idRev, idArt);
    }

    public RevisionDto primeraRevision(String idRev, String idArt) {
	String sql = "select * " + "from revisiones " + "where idRevisor = ? and idArticulo = ? and numeroRevision = 1";

	return db.executeQueryPojo(RevisionDto.class, sql, idRev, idArt).get(0);
    }

    public void revisarArticulo(String comentariosAutor, String comentariosEditor, String decision,
	    boolean enviarAlEditor, String idArticulo, String idRevisor, int numeroRevision) {
	// validaciones (en este caso nada)
	String sql = "update revisiones set comentariosAutor = ?, comentariosEditor = ?, decision = ?, enviarAlEditor = ? where idArticulo = ? and idRevisor = ? and numeroRevision = ?";
	db.executeUpdate(sql, comentariosAutor, comentariosEditor, decision, enviarAlEditor, idArticulo, idRevisor,
		numeroRevision);
    }

    public List<RevisionDto> revisionesEnviadas(String idArt, int numeroRevision) {
	String sql = "select * " + "from revisiones "
		+ "where idArticulo = ? and numeroRevision = ? and enviarAlEditor = true";

	return db.executeQueryPojo(RevisionDto.class, sql, idArt, numeroRevision);
    }

    public List<RevisionDto> visualizarSinRevisar(String idRevisor) {
	String sql = "select * " + "from revisiones "
		+ "where idRevisor = ? and enviarAlEditor = false and estadoDeLaPropuesta = 'ACEPTADA'";

	return db.executeQueryPojo(RevisionDto.class, sql, idRevisor);
    }

    public boolean checkArticuloRevisado(ArticuloDto a) {

	String sql = "select * from revisiones where idArticulo = ? and enviarAlEditor=true";

	return db.executeQueryPojo(RevisionDto.class, sql, a.getIdArticulo()).size() == 3;
    }

    public void updateRevisiones(String idArticulo) {
	String sql = "update revisiones set enviarAlEditor = false where idArticulo = ?";

	db.executeUpdate(sql, idArticulo);

    }

}
