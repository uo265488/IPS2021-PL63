package giis.demo.tkrun.models.editor;

import java.util.List;

import giis.demo.tkrun.models.dtos.RevisorDto;
import giis.demo.util.Database;

public class EditorModel {

	private Database db = new Database();

	/**
	 * Obtiene la lista de revisores disponibles en forma de objetos
	 */
	public List<RevisorDto> getRevisoresDisponibles() {
		// validaciones (en este caso nada)
		String sql = "SELECT * from revisores where estado='disponible'";

		List<RevisorDto> revisores = db.executeQueryPojo(RevisorDto.class, sql);
		
		System.out.print(revisores.get(2).getIdRevisor());
		
		return revisores;

	}

}
