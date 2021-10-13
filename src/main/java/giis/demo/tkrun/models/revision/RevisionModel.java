package giis.demo.tkrun.models.revision;

import java.util.List;

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
		String sql = "insert into revisiones(idArticulo, idRevisor, fecha) values (?,?,?)";

		db.executeUpdate(sql, revisionDto.getRevisor().getIdRevisor(), revisionDto.getArticulo().getId(),
				revisionDto.getFecha());

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

		return db.executeQueryPojo(RevisionDto.class, sql, articulo.getId(), revisor.getIdRevisor()).get(0);

	}

	/**
	 * Obtiene todas las revisiones hechas sobre un articulo
	 * @param articuloDto
	 * @return
	 */
	public List<RevisionDto> getComentariosDeRevisionDeUnArticulo(ArticuloDto articulo, RevisorDto revisor) {

		String sql = "select * from revisiones where idArticulo = ? and idRevisor <> ?";
		
		return db.executeQueryPojo(RevisionDto.class, sql, articulo.getId(), revisor.getIdRevisor());
	}

}
