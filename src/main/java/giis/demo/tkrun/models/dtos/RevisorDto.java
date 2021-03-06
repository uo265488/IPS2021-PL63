package giis.demo.tkrun.models.dtos;

public class RevisorDto {

    private String idRevisor;
    private String nombre;
    private String estado;
    private String correo;
    private String especialidad;

    public String getCorreo() {
	return correo;
    }

    public void setCorreo(String correo) {
	this.correo = correo;
    }

    public String getEspecialidad() {
	return especialidad;
    }

    public void setEspecialidad(String especialidad) {
	this.especialidad = especialidad;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getEstado() {
	return estado;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    @Override
    public String toString() {
	return nombre + " - " + idRevisor;
    }

    public String getIdRevisor() {
	return idRevisor;
    }

    public void setIdRevisor(String idRevisor) {
	this.idRevisor = idRevisor;
    }

}
