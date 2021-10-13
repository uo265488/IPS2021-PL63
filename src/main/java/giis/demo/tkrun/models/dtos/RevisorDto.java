package giis.demo.tkrun.models.dtos;

public class RevisorDto {

	private int idRevisor;
	private String nombre;
	private String estado;
	
	public RevisorDto(int idRevisor, String nombre, String estado) {
		super();
		this.idRevisor = idRevisor;
		this.nombre = nombre;
		this.estado = estado;
	}

	public RevisorDto() {}

	public int getIdRevisor() {
		return idRevisor;
	}

	public void setIdRevisor(int id) {
		this.idRevisor = id;
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
		return "RevisorDto [id=" + idRevisor + ", nombre=" + nombre + ", estado=" + estado + "]";
	}
	
	
}
