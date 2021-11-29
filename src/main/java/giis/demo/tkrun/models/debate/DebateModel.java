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

    public List<MensajeDto> getMensajesDebate(String idArticulo) {
	String sql = "select * from Debates where idArticulo=?";
	List<DebateDto> list = db.executeQueryPojo(DebateDto.class, sql, idArticulo);
	DebateDto debate = list.get(0);
	sql = "select * from mensajes where idDebate=?";
	return db.executeQueryPojo(MensajeDto.class, sql, debate.getIdDebate());
    }

    public boolean getEstadoDelDebate(String idArticulo) {
	String sql = "select abierto from Debates where idArticulo=?";
	List<DebateDto> debate = db.executeQueryPojo(DebateDto.class, sql, idArticulo);
	if (debate.size() > 0) {
	    return debate.get(0).isAbierto();
	} else
	    return false;
    }

    public DebateDto getDebate(String idArticulo) {
	String sql = "select * from Debates where idArticulo=?";
	List<DebateDto> list = db.executeQueryPojo(DebateDto.class, sql, idArticulo);
	return list.get(0);
    }

    public void cerrarDebate(String idArticulo) {
	String sql = "update debates set abierto = ? where idArticulo = ?";

	db.executeUpdate(sql, false, idArticulo);

    }
}
