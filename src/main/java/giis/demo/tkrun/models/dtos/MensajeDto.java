package giis.demo.tkrun.models.dtos;

public class MensajeDto {
    
    private String idMensaje;
    private String idDebate;
    private String mensaje;
    
    public String getIdMensaje() {
        return idMensaje;
    }
    public void setIdMensaje(String idMensaje) {
        this.idMensaje = idMensaje;
    }
    public String getIdDebate() {
        return idDebate;
    }
    public void setIdDebate(String idDebate) {
        this.idDebate = idDebate;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}