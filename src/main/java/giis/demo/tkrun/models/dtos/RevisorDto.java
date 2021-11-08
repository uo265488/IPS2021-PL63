package giis.demo.tkrun.models.dtos;

public class RevisorDto {

	private int idRevisor;
	private String nombre;
	private String estado;



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

	public int getIdRevisor() {
		return idRevisor;
	}

	public void setIdRevisor(int idRevisor) {
		this.idRevisor = idRevisor;
	}
	
	

}
