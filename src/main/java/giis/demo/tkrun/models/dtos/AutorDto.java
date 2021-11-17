package giis.demo.tkrun.models.dtos;

public class AutorDto {

	private int idAutor;
	private String nombre;
	private String dni;

	public AutorDto() {
	}

	public AutorDto(int id, String nombre, String dni) {
		super();
		this.idAutor = id;
		this.nombre = nombre;
		this.dni = dni;
	}

	public String getDni() {
		return dni;
	}

	public int getIdAutor() {
		return idAutor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setIdAutor(int id) {
		this.idAutor = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Autor: " + nombre + " Dni: " + dni;
	}

}
