package giis.demo.tkrun.models.autoresSecundarios;

import java.util.List;

import giis.demo.tkrun.models.dtos.AutoresSecundariosDto;
import giis.demo.util.Database;

public class AutoresSecundariosModel {

	private Database db = new Database();

	public List<AutoresSecundariosDto> getAutoresSecundariosByIdArticulo(String idArticulo) {

		String sql = "select * from AutoresSecundarios where idArticulo=?";

		return db.executeQueryPojo(AutoresSecundariosDto.class, sql, idArticulo);
	}
}
