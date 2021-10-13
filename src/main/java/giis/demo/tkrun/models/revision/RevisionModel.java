package giis.demo.tkrun.models.revision;

import java.util.List;

import giis.demo.tkrun.models.dtos.ArticuloDto;
import giis.demo.tkrun.models.dtos.RevisionDto;
import giis.demo.util.Database;

public class RevisionModel {

	private Database db = new Database();

	/*
	 * public void add(RevisionDto revisionDto) { String sql =
	 * "insert into revisiones(revisor_id, articulo_id, fecha) values (?,?,?)";
	 * 
	 * db.executeQueryPojo(RevisorDto.class, sql, revisionDto.getRevisor().getId(),
	 * revisionDto.getArticulo().getIdArticulo(), revisionDto.getFecha());
	 * 
	 * }
	 */
	
	public void revisarArticulo(String comentariosAutor, String comentariosEditor, String decision, boolean enviarAlEditor, int idArticulo, int idRevisor) {
		// validaciones (en este caso nada)
		String sql = "update revisiones set comentariosAutor = ?, comentariosEditor = ?, decision = ?, enviarAlEditor = ? where idArticulo = ? and idRevisor = ?";
		db.executeUpdate(sql, comentariosAutor, comentariosEditor, decision, enviarAlEditor, idArticulo, idRevisor);
	}
	
	public RevisionDto visualizarSinRevisar(int idRevisor, int idArticulo){
		String sql = "select * "
				+ "from revisiones "
				+ "where idRevisor = ? and enviarAlEditor = false and idArticulo = ?";
		
		return db.executeQueryPojo(RevisionDto.class, sql, idRevisor, idArticulo).get(0);
	}
	
	public List<ArticuloDto> articulosSinRevisar(int idRevisor){
		String sql = "select articulo.idArticulo, titulo "
				+ "from revisiones, articulo "
				+ "where idRevisor = ? and enviarAlEditor = false and articulo.idArticulo = revisiones.idArticulo";
		
		return db.executeQueryPojo(ArticuloDto.class, sql, idRevisor);

	}

}
