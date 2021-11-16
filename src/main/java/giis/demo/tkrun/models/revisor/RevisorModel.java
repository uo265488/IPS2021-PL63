package giis.demo.tkrun.models.revisor;

import java.util.List;

import giis.demo.tkrun.models.dtos.RevisorDto;
import giis.demo.util.Database;

public class RevisorModel {

    private Database db = new Database();

    public void addRevisor(RevisorDto revisorDto) {
	String sql = "insert into revisores values (?, ?, ?, ?, ?)";
	db.executeUpdate(sql, revisorDto.getIdRevisor(), revisorDto.getNombre(), revisorDto.getEstado(),
		revisorDto.getCorreo(), revisorDto.getEspecialidad());
    }

    public RevisorDto findById(String idRevisor) {
	String sql = "SELECT * from revisores where idRevisor=?";

	return db.executeQueryPojo(RevisorDto.class, sql, idRevisor).get(0);
    }

    /**
     * Obtiene la lista de revisores disponibles en forma de objetos
     */
    public List<RevisorDto> getRevisoresDisponibles() {

	String sql = "SELECT * from revisores where estado='DISPONIBLE'";

	return db.executeQueryPojo(RevisorDto.class, sql);
    }

    public void sugerirRevisores(String id_articulo, RevisorDto revisor) {
	String sql = "delete from sugerencias where idArticulo = ? and idRevisor = ?";
	db.executeUpdate(sql, id_articulo, revisor.getIdRevisor());

	sql = "insert into sugerencias(idArticulo, idRevisor) values (?, ?)";
	db.executeUpdate(sql, id_articulo, revisor.getIdRevisor());
    }

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

}
