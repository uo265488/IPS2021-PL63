package giis.demo.tkrun.models.revision;

import java.util.List;

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
	String sql = "insert into revisiones(idArticulo, idRevisor, fecha) values (?,?,?)";

	db.executeUpdate(sql, revisionDto.getIdArticulo(), revisionDto.getIdRevisor(), revisionDto.getFecha());

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

    /*
     * public void add(RevisionDto revisionDto) { String sql =
     * "insert into revisiones(revisor_id, articulo_id, fecha) values (?,?,?)";
     * 
     * db.executeQueryPojo(RevisorDto.class, sql, revisionDto.getRevisor().getId(),
     * revisionDto.getArticulo().getIdArticulo(), revisionDto.getFecha());
     * 
     * }
     */

    public void revisarArticulo(String comentariosAutor, String comentariosEditor, String decision,
	    boolean enviarAlEditor, int idArticulo, int idRevisor) {
	// validaciones (en este caso nada)
	String sql = "update revisiones set comentariosAutor = ?, comentariosEditor = ?, decision = ?, enviarAlEditor = ? where idArticulo = ? and idRevisor = ?";
	db.executeUpdate(sql, comentariosAutor, comentariosEditor, decision, enviarAlEditor, idArticulo, idRevisor);
    }

    public RevisionDto visualizarSinRevisar(int idRevisor, int idArticulo) {
	String sql = "select * " + "from revisiones "
		+ "where idRevisor = ? and enviarAlEditor = false and idArticulo = ?";

	return db.executeQueryPojo(RevisionDto.class, sql, idRevisor, idArticulo).get(0);
    }

	public List<ArticuloDto> articulosSinRevisar(int idRevisor){
		String sql = "select articulos.idArticulo, titulo, vecesRevisado "
				+ "from revisiones, articulos "
				+ "where idRevisor = ? and enviarAlEditor = false and articulos.idArticulo = revisiones.idArticulo and estado = 'en revision'";
		
		return db.executeQueryPojo(ArticuloDto.class, sql, idRevisor);

	}
	
	public void decisionArticulo(int idRev, int idArt, boolean condicion) {
		String decision = "";
		if(condicion)
			decision = "'ACEPTADO'";
		else
			decision = "'RECHAZADO'";
		String sql = "update revisiones set estadoDeLaPropuesta = ? where idArticulo = ? and idRevisor = ?";
		
		db.executeUpdate(sql, decision, idArt, idRev);
	}

	public RevisionDto getFecha(int idRev, int idArticulo) {
		String sql = "select fecha "
				+ "from revisiones "
				+ "where idRevisor = ? and idArticulo = ?";
		return db.executeQueryPojo(RevisionDto.class, sql, idRev, idArticulo).get(0);
	}

	public List<RevisionDto> articulosAceptados(int idArticulo) {
		String sql = "select * "
				+ "from revisiones "
				+ "where idArticulo = ? and estadoDeLaPropuesta = 'ACEPTADO'";
		return db.executeQueryPojo(RevisionDto.class, sql, idArticulo);
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

	    public RevisorDto findById(int id) {
		String sql = "select * from revisores where idRevisor = ?";
		List<RevisorDto> revisores = db.executeQueryPojo(RevisorDto.class, sql, id);

		if (revisores.isEmpty()) {
		    return null;
		} else {
		    return revisores.get(0);
		}
	    }

	    public List<SugerenciaDto> findSugeridos(int idArticulo) {
		String sql = "select * from sugerencias where idArticulo = ?";

		return db.executeQueryPojo(SugerenciaDto.class, sql, idArticulo);
	    }
}
