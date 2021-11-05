package giis.demo.tkrun.models.revisor;

import java.util.List;

import giis.demo.tkrun.models.dtos.RevisorDto;
import giis.demo.util.Database;

public class RevisorModel {
	
	private Database db = new Database();

//----------------------------------OSCAR-------------------------------------------------------
	/**
	 * Actualiza el revisor dado por parametro
	 * 
	 * @param revisorDto
	 */
	public void update(RevisorDto revisorDto) {
		String sql = "update revisores set estado = ?, nombre = ?, correo=?, especialidad=? where idRevisor = ?";
		
		db.executeUpdate(sql, revisorDto.getEstado(), revisorDto.getNombre(), revisorDto.getCorreo(), revisorDto.getEspecialidad(), revisorDto.getIdRevisor());
		
	}
	
	/**
	 * Obtiene la lista de revisores disponibles en forma de objetos
	 */
	public List<RevisorDto> getRevisoresDisponibles() {
		String sql = "SELECT * from revisores where estado='disponible'";

		return db.executeQueryPojo(RevisorDto.class, sql);
	}

	public RevisorDto findById(int idRevisor) {
		String sql = "SELECT * from revisores where idRevisor=?";

		return db.executeQueryPojo(RevisorDto.class, sql, idRevisor).get(0);
	}
//-----------------------------------------------------------------------------------------------
	
}
