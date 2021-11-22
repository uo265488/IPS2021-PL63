package giis.demo.tkrun.models.dtos;

public class RevisionDto {

    public static String PENDIENTE = "PENDIENTE";
    public static String ACEPTADA = "ACEPTADA";
    public static String RECHAZADA = "RECHAZADA";

    private String idRevisor;
    private String idArticulo;
    private String fecha;
    private String comentariosAutor;
    private String comentariosEditor;

    private String decision;

    private boolean enviarAlEditor;
    private int numeroRevision;
//
//	private boolean decisionArticulo;
//	private boolean decisionTomada;
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

    public String getIdArticulo() {
	return idArticulo;
    }

    public String getIdRevisor() {
	return idRevisor;
    }

    public int getNumeroRevision() {
	return numeroRevision;
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

    public void setIdArticulo(String idArticulo) {
	this.idArticulo = idArticulo;
    }

    public void setIdRevisor(String idRevisor) {
	this.idRevisor = idRevisor;
    }

    public void setNumeroRevision(int numeroRevision) {
	this.numeroRevision = numeroRevision;
    }
//	public boolean isDecisionArticulo() {
//		return decisionArticulo;
//	}
//	public void setDecisionArticulo(boolean decisionArticulo) {
//		this.decisionArticulo = decisionArticulo;
//	}
//	public boolean isDecisionTomada() {
//		return decisionTomada;
//	}
//	public void setDecisionTomada(boolean decisionTomada) {
//		this.decisionTomada = decisionTomada;
//	}

}
