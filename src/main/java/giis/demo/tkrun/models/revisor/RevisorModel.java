package giis.demo.tkrun.models.revisor;

import giis.demo.tkrun.models.dtos.RevisorDto;
import giis.demo.util.Database;

public class RevisorModel {
	
	private Database db = new Database();

	public void update(RevisorDto revisorDto) {
		// validaciones (en este caso nada)
		String sql = "update revisores set estado = ?, nombre = ? where idRevisor = ?";

		db.executeUpdate(sql, revisorDto.getEstado(), revisorDto.getNombre(), revisorDto.getIdRevisor());
		
	}

}
