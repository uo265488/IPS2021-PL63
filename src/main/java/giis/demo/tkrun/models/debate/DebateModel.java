package giis.demo.tkrun.models.debate;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
	public String abrirDebate(int idArticulo, LocalDate date) {

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
	 * @param idDebate 	Id del debate 
	 * @return	Lista de mensajes
	 */
	public List<MensajeDto> devolverMensajes(String idDebate) {
	    String sql = "select * from Mensajes where idDebate = ?";

	    return db.executeQueryPojo(MensajeDto.class, sql, idDebate);

	}

}