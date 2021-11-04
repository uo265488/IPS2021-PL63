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
		// validaciones (en este caso nada)
		String sql = "update revisores set estado = ?, nombre = ?, correo=?, especialidad=? where idRevisor = ?";
		
		////TESTEO
		System.out.print(revisorDto.getEstado());
		System.out.print(revisorDto.getNombre());
		System.out.print(revisorDto.getIdRevisor());
		System.out.print(revisorDto.getCorreo());
		System.out.print(revisorDto.getEspecialidad());
		
		db.executeUpdate(sql, revisorDto.getEstado(), revisorDto.getNombre(), revisorDto.getCorreo(), revisorDto.getEspecialidad(), revisorDto.getIdRevisor());
		
	}
	
	/**
	 * Obtiene la lista de revisores disponibles en forma de objetos
	 */
	public List<RevisorDto> getRevisoresDisponibles() {
		// validaciones (en este caso nada)
		String sql = "SELECT * from revisores where estado='disponible'";

		return db.executeQueryPojo(RevisorDto.class, sql);
	}

	public RevisorDto findById(int idRevisor) {
		String sql = "SELECT * from revisores where idRevisor=?";

		return db.executeQueryPojo(RevisorDto.class, sql, idRevisor).get(0);
	}
//-----------------------------------------------------------------------------------------------
	
}
