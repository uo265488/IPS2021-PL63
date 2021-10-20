package giis.demo.tkrun.models.dtos;

public class AutorDto {

	private int idAutor;
	private String nombre;
	private String dni;
	
	public AutorDto(int id, String nombre, String dni) {
		super();
		this.idAutor = id;
		this.nombre = nombre;
		this.dni = dni;
	}

	public AutorDto() {}

	public int getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(int id) {
		this.idAutor = id;
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
		return "RevisorDto [id=" + idAutor + ", nombre=" + nombre + ", dni=" + dni + "]";
	}
	
}