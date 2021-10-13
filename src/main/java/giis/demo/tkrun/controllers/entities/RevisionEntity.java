package giis.demo.tkrun.controllers.entities;

public class RevisionEntity {
	
	private int idArticulo;
	private int idRevisor;
	private String fecha;
	private String comentariosAutor;
	private String comentariosEditor;
	private boolean enviarAlEditor;
	
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
	public String getComentariosAutor() {
		return comentariosAutor;
	}
	public void setComentariosAutor(String comentariosAutor) {
		this.comentariosAutor = comentariosAutor;
	}
	public String getComentariosEditor() {
		return comentariosEditor;
	}
	public void setComentariosEditor(String comentariosEditor) {
		this.comentariosEditor = comentariosEditor;
	}
	public boolean isEnviarAlEditor() {
		return enviarAlEditor;
	}
	public void setEnviarAlEditor(boolean enviarAlEditor) {
		this.enviarAlEditor = enviarAlEditor;
	}
	
	
	

}
