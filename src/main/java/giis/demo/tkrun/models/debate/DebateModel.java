
package giis.demo.tkrun.models.debate;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import giis.demo.tkrun.models.dtos.DebateDto;
import giis.demo.tkrun.models.dtos.MensajeDto;
import giis.demo.util.Database;

public class DebateModel {

	private Database db = new Database();

	/**
	 * Crear debate para el articulo con la fecha de cierra
	 *
	 * @param idArticulo
	 * @param date
	 */
	public String abrirDebate(String idArticulo, LocalDate date) {
		String idDebate = UUID.randomUUID().toString();

		String sql = "insert into Debates values (?,?,?,?)";

		db.executeUpdate(sql, idDebate, idArticulo, date, false);

		return idDebate;
	}

	public void escribirMensaje(String idDebate, String mensaje) {

		String sql = "insert into mensajes values (?,?,?)";

		db.executeUpdate(sql, UUID.randomUUID(), idDebate, mensaje);

	}

	/**
	 * Saca una lista con todos los mensajes del debate
	 *
	 * @param idDebate Id del debate
	 * @return Lista de mensajes
	 */
	public List<MensajeDto> devolverMensajes(String idDebate) {
		String sql = "select * from Mensajes where idDebate = ?";

		return db.executeQueryPojo(MensajeDto.class, sql, idDebate);

	}

	public List<DebateDto> getIdDebate(String idArticulo) {
		String sql = "select idDebate from Debates where idArticulo = ?";

		return db.executeQueryPojo(DebateDto.class, sql, idArticulo);
	}

	public void cerrarDebate(String idArticulo) {
		String sql = "update debates set abierto = ? where idArticulo = ?";

		db.executeUpdate(sql, false, idArticulo);

	}

	public DebateDto getDebate(String idArticulo) {
		String sql = "select * from Debates where idArticulo=?";
		List<DebateDto> list = db.executeQueryPojo(DebateDto.class, sql, idArticulo);
		return list.get(0);
	}

	public boolean getEstadoDelDebate(String idArticulo) {
		String sql = "select abierto from Debates where idArticulo=?";
		List<DebateDto> debate = db.executeQueryPojo(DebateDto.class, sql, idArticulo);
		if (debate.size() > 0) {
			return debate.get(0).isAbierto();
		} else {
			return false;
		}
	}

	public List<MensajeDto> getMensajesDebate(String idArticulo) {
		String sql = "select * from Debates where idArticulo=?";
		List<DebateDto> list = db.executeQueryPojo(DebateDto.class, sql, idArticulo);
		DebateDto debate = list.get(0);
		sql = "select * from mensajes where idDebate=?";
		return db.executeQueryPojo(MensajeDto.class, sql, debate.getIdDebate());
	}

}
