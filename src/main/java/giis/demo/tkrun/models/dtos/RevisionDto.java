package giis.demo.tkrun.models.dtos;

public class RevisionDto {
	
	private int idRevisor;
	private int idArticulo;
	private String fecha;
	private String comentariosAutor;
	private String comentariosEditor;
	private String decision;
	private boolean enviarAlEditor;
	

	public int getIdRevisor() {
		return idRevisor;
	}
	public void setIdRevisor(int idRevisor) {
		this.idRevisor = idRevisor;
	}
	public int getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
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
	public String getDecision() {
		return decision;
	}
	public void setDecision(String decision) {
		this.decision = decision;
	}
	
	
	
	
	
	

}
