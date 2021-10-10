package giis.demo.tkrun.revisor.model;

import giis.demo.tkrun.revisor.RevisorDto;
import giis.demo.util.Database;

public class RevisorModel {
	
	private Database db = new Database();

	public void update(RevisorDto revisorDto) {
		// validaciones (en este caso nada)
		String sql = "update articulos set estado = ?, nombre = ? where id = ?";

		db.executeQueryPojo(RevisorDto.class, sql, revisorDto.getEstado(), revisorDto.getNombre(), revisorDto.getId());
		
	}

}
