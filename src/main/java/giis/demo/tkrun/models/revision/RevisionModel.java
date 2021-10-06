package giis.demo.tkrun.models.revision;

import giis.demo.tkrun.models.dtos.RevisionDto;
import giis.demo.tkrun.models.dtos.RevisorDto;
import giis.demo.util.Database;

public class RevisionModel {

	private Database db = new Database();

	public void add(RevisionDto revisionDto) {
		String sql = "insert into revisiones(revisor_id, articulo_id, fecha) values (?,?,?)";

		db.executeQueryPojo(RevisorDto.class, sql, revisionDto.getRevisor().getId(), revisionDto.getArticulo().getIdArticulo(),
				revisionDto.getFecha());

	}

}
