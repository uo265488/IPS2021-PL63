package giis.demo.tkrun.models.sugerencia;

import java.util.List;

import giis.demo.tkrun.models.dtos.RevisorDto;
import giis.demo.util.Database;

public class SugerenciaModel {

    private Database db = new Database();

    public List<RevisorDto> getRevisoresSugeridos(String idArticulo) {

	String sql = "select * from Sugerencias where idArticulo=?";

	return db.executeQueryPojo(RevisorDto.class, sql, idArticulo);

    }
}
