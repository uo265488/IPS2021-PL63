package giis.demo.tkrun.controllers.entities;

public class RevisorEntity {

    public static final String NO_DISPONIBLE = "NO DISPONIBLE";
    public static final String DISPONIBLE = "DISPONIBLE";
    public static final String SUGERIDO = "SUGERIDO";

    private String id;
    private String nombre;
    private String estado;
    private String correo;
    private String especialidad;

    public RevisorEntity() {
    }

    public RevisorEntity(String id, String nombre, String estado) {
	super();
	this.id = id;
	this.nombre = nombre;
	this.estado = estado;
    }

    public String getCorreo() {
	return correo;
    }

    public String getEspecialidad() {
	return especialidad;
    }

    public String getEstado() {
	return estado;
    }

    public String getId() {
	return id;
    }

    public String getNombre() {
	return nombre;
    }

    public void setCorreo(String correo) {
	this.correo = correo;
    }

    public void setEspecialidad(String especialidad) {
	this.especialidad = especialidad;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public void setId(String id) {
	this.id = id;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    @Override
    public String toString() {
	return id + " - " + nombre + " - " + correo + " - " + especialidad;
    }

}
