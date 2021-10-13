package giis.demo.tkrun.controllers.entities;

public class RevisionEntity {
	
	private int idArticulo;
	private int idRevisor;
	private String fecha;
	private String comentarios;
	public int getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}
	public int getIdRevisor() {
		return idRevisor;
	}
	public void setIdRevisor(int idRevisor) {
		this.idRevisor = idRevisor;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	

}
