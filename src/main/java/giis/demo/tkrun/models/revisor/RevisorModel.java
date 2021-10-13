package giis.demo.tkrun.models.revisor;

import java.util.List;

import giis.demo.tkrun.models.dtos.RevisorDto;
import giis.demo.util.Database;

public class RevisorModel {
	
	private Database db = new Database();

	/**
	 * Actualiza el revisor dado por parametro
	 * 
	 * @param revisorDto
	 */
	public void update(RevisorDto revisorDto) {
		// validaciones (en este caso nada)
		String sql = "update revisores set estado = ?, nombre = ? where idRevisor = ?";

		db.executeUpdate(sql, revisorDto.getEstado(), revisorDto.getNombre(), revisorDto.getIdRevisor());
		
	}
	
	/**
	 * Obtiene la lista de revisores disponibles en forma de objetos
	 */
	public List<RevisorDto> getRevisoresDisponibles() {
		// validaciones (en este caso nada)
		String sql = "SELECT idRevisor, nombre, estado from revisores where estado='disponible'";

		return db.executeQueryPojo(RevisorDto.class, sql);

	}
}
