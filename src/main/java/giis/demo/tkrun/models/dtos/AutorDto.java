package giis.demo.tkrun.models.dtos;

public class AutorDto {

	private int id;
	private String nombre;
	private String dni;
	
	public AutorDto(int id, String nombre, String dni) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.dni = dni;
	}

	public AutorDto() {}

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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "RevisorDto [id=" + id + ", nombre=" + nombre + ", dni=" + dni + "]";
	}
	
}
