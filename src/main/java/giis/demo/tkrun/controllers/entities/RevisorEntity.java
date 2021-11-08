package giis.demo.tkrun.controllers.entities;

public class RevisorEntity {

    public static final String NO_DISPONIBLE = "no disponible";

    private int id;
    private String nombre;
    private String estado;
    private String correo;
    private String especialidad;

    public RevisorEntity(int id, String nombre, String estado) {
	super();
	this.id = id;
	this.nombre = nombre;
	this.estado = estado;
    }

    public RevisorEntity() {
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
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

    @Override
    public String toString() {
	return nombre + " - " + id;
    }

}
