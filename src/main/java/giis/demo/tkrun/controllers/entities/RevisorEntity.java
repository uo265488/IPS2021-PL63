package giis.demo.tkrun.controllers.entities;

public class RevisorEntity {

	public static final String NO_DISPONIBLE = "no disponible";
	public static final String DISPONIBLE = "disponible";
	public static final String SUGERIDO = "sugerido";

	private int id;
	private String nombre;
	private String estado;
	private String correo;
	private String especialidad;

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

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

	public RevisorEntity(int id, String nombre, String estado, String correo, String especialidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
		this.correo = correo;
		this.especialidad = especialidad;
	}

	public String getCorreo() {
		return correo;
	}

	@Override
	public String toString() {
		return id + " - " + nombre + " - "  + correo + " - " + especialidad;
	}

}
