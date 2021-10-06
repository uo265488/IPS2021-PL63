package giis.demo.tkrun.models.dtos;

public class RevisorDto {

	private int id;
	private String nombre;
	private String estado;
	
	public RevisorDto(int id, String nombre, String estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
	}

	public RevisorDto() {}

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

	@Override
	public String toString() {
		return "RevisorDto [id=" + id + ", nombre=" + nombre + ", estado=" + estado + "]";
	}
	
	
}
