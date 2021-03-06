package giis.demo.tkrun.models.dtos;

public class AutorDto {

	private String idAutor;
	private String nombre;
	private String dni;

	public AutorDto() {
	}

	public AutorDto(String id, String nombre, String dni) {
		super();
		this.idAutor = id;
		this.nombre = nombre;
		this.dni = dni;
	}

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(String id) {
		this.idAutor = id;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Autor: " + nombre + " Dni: " + dni;
	}

}
