package giis.demo.tkrun.models.revision;

import java.util.List;
import java.util.Optional;

import giis.demo.tkrun.models.dtos.ArticuloDto;
import giis.demo.tkrun.models.dtos.RevisionDto;
import giis.demo.tkrun.models.dtos.RevisorDto;
import giis.demo.util.Database;

public class RevisionModel {

	private Database db = new Database();

	/**
	 * Añade una revision a la base de datos
	 * 
	 * @param revisionDto
	 */
	public void add(RevisionDto revisionDto) {
		String sql = "insert into revisiones(idArticulo, idRevisor, fecha, estadoDeLaPropuesta) values (?,?,?,?)";

		db.executeUpdate(sql, revisionDto.getIdArticulo(), revisionDto.getIdRevisor(), revisionDto.getFecha(),
				revisionDto.getEstadoDeLaPropuesta());

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
			boolean enviarAlEditor, int idArticulo, int idRevisor, int numeroRevision) {
		// validaciones (en este caso nada)
		String sql = "update revisiones set comentariosAutor = ?, comentariosEditor = ?, decision = ?, enviarAlEditor = ? where idArticulo = ? and idRevisor = ? and numeroRevision = ?";
		db.executeUpdate(sql, comentariosAutor, comentariosEditor, decision, enviarAlEditor, idArticulo, idRevisor,
				numeroRevision);
	}

	public RevisionDto visualizarSinRevisar(int idRevisor, int idArticulo) {
		String sql = "select * " + "from revisiones "
				+ "where idRevisor = ? and enviarAlEditor = false and idArticulo = ?";

		return db.executeQueryPojo(RevisionDto.class, sql, idRevisor, idArticulo).get(0);
	}

	public List<ArticuloDto> articulosSinRevisar(int idRevisor) {
		String sql = "select articulos.idArticulo, titulo " + "from revisiones, articulos "
				+ "where idRevisor = ? and enviarAlEditor = false and articulos.idArticulo = revisiones.idArticulo and estado = 'en revision'";

		return db.executeQueryPojo(ArticuloDto.class, sql, idRevisor);

	}

	public List<RevisionDto> findByIdArticulo(int idArticulo) {
		String sql = "select * " + "from revisiones " + "where idArticulo = ?";

		return db.executeQueryPojo(RevisionDto.class, sql, idArticulo);
	}

	public List<RevisionDto> findAll() {
		String sql = "select * from revisiones";

		return db.executeQueryPojo(RevisionDto.class, sql);
	}

	public List<RevisionDto> findRevisionesRechazadas() {
		String sql = "select * from revisiones where estadoDeLaPropuesta='" + RevisionDto.RECHAZADA + "'";

		return db.executeQueryPojo(RevisionDto.class, sql);
	}

	public List<RevisionDto> getRevisionesPendientesDeUnArticulo(int idArticulo) {
		String sql = "select * from revisiones where estadoDeLaPropuesta='" + RevisionDto.PENDIENTE + "'";

		return db.executeQueryPojo(RevisionDto.class, sql);
	}

	public Optional<RevisionDto> findRevisionRechazada(int idArticulo, int id) {

		String sql = "select * from revisiones where idArticulo=? and idRevisor=? and estadoDeLaPropuesta='"
				+ RevisionDto.RECHAZADA + "'";

		if (db.executeQueryPojo(RevisionDto.class, sql, idArticulo, id).isEmpty()) {
			return Optional.ofNullable(null);
		}

		return Optional.ofNullable(db.executeQueryPojo(RevisionDto.class, sql, idArticulo, id).get(0));
	}

	public List<RevisionDto> getRevisionesFiltradoNumeroRevision(int idArticulo, int numeroRevision) {
		String sql = "select * from revisiones where idArticulo=? and numeroRevision=?";

		return db.executeQueryPojo(RevisionDto.class, sql, idArticulo, numeroRevision);
	}

	public List<RevisionDto> getRevisionesArticuloDeUnRevisor(int idArticulo, int idRevisor) {
		String sql = "select * from revisiones where idArticulo=? and idRevisor=?";

		return db.executeQueryPojo(RevisionDto.class, sql, idArticulo, idRevisor);
	}

	public List<RevisionDto> getRevisionPorNumeroRevision(int idArticulo, int idRevisor, int numeroRevision) {
		String sql = "select * from revisiones where idArticulo=? and idRevisor=? and numeroRevision=?";

		return db.executeQueryPojo(RevisionDto.class, sql, idArticulo, idRevisor, numeroRevision);
	}

	public void generarSegundaRevision(int idArticulo, int idRevisor, String fecha) {
		String sql = "insert into revisiones(idArticulo, idRevisor, numeroRevision, fecha, estadoDeLaPropuesta) values (?,?,?,?,?)";

		db.executeUpdate(sql, idArticulo, idRevisor, 2, fecha, "ACEPTADO");

	}

}
