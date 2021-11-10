package giis.demo.tkrun.models.dtos;

public class RevisionDto {

	public static final String RECHAZADA = "RECHAZADA";
	public static final String ACEPTADA = "ACEPTADA";
	public static final String PENDIENTE = "PENDIENTE";

	private int idRevisor;
	private int idArticulo;
	private String fecha;
	private String comentariosAutor;
	private String comentariosEditor;
	private String decision;
	private boolean enviarAlEditor;

	private String estadoDeLaPropuesta;

	public String getComentariosAutor() {
		return comentariosAutor;
	}

	public String getComentariosEditor() {
		return comentariosEditor;
	}

	public String getDecision() {
		return decision;
	}

	public String getEstadoDeLaPropuesta() {
		return estadoDeLaPropuesta;
	}

	public String getFecha() {
		return fecha;
	}

	public int getIdArticulo() {
		return idArticulo;
	}

	public int getIdRevisor() {
		return idRevisor;
	}

	public boolean isEnviarAlEditor() {
		return enviarAlEditor;
	}

	public void setComentariosAutor(String comentariosAutor) {
		this.comentariosAutor = comentariosAutor;
	}

	public void setComentariosEditor(String comentariosEditor) {
		this.comentariosEditor = comentariosEditor;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public void setEnviarAlEditor(boolean enviarAlEditor) {
		this.enviarAlEditor = enviarAlEditor;
	}

	public void setEstadoDeLaPropuesta(String estadoDeLaPropuesta) {
		this.estadoDeLaPropuesta = estadoDeLaPropuesta;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

	public void setIdRevisor(int idRevisor) {
		this.idRevisor = idRevisor;
	}

}
