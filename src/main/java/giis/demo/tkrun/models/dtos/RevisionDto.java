package giis.demo.tkrun.models.dtos;

public class RevisionDto {
	private int idArticulo;
	private int idRevisor;
	private String comentariosAutor;
	private String comentariosEditor;
	private boolean enviarAlEditor;
	
	public RevisionDto(int idArticulo, int idRevisor, String comentariosAutor, String comentariosEditor, boolean enviarAlEditor) {
		super();
		this.idArticulo = idArticulo;
		this.idRevisor = idRevisor;
		this.comentariosAutor = comentariosAutor;
		this.comentariosEditor = comentariosEditor;
		this.enviarAlEditor = enviarAlEditor;
	}

	
	public RevisionDto() {}
	
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
