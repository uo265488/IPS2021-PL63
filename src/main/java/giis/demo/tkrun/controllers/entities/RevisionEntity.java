package giis.demo.tkrun.controllers.entities;

public class RevisionEntity {
	
	private RevisorEntity revisor;
	
	private ArticuloEntity articulo;
	
	private String fecha;
	
	private String comentariosEditor;
	
	private String comentariosAutor;
	
	private String decision;
	
	private boolean enviadoAlEditor;

	public RevisorEntity getRevisor() {
		return revisor;
	}

	public void setRevisor(RevisorEntity revisor) {
		this.revisor = revisor;
	}

	public ArticuloEntity getArticulo() {
		return articulo;
	}

	public void setArticulo(ArticuloEntity articulo) {
		this.articulo = articulo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	public String getComentariosEditor() {
		return comentariosEditor;
	}

	public void setComentariosEditor(String comentariosEditor) {
		this.comentariosEditor = comentariosEditor;
	}

	public String getComentariosAutor() {
		return comentariosAutor;
	}

	public void setComentariosAutor(String comentariosAutor) {
		this.comentariosAutor = comentariosAutor;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public boolean isEnviadoAlEditor() {
		return enviadoAlEditor;
	}

	public void setEnviadoAlEditor(boolean enviadoAlEditor) {
		this.enviadoAlEditor = enviadoAlEditor;
	}

	public RevisionEntity(RevisorEntity revisor, ArticuloEntity articulo, String fecha, String comentariosAutor, String comentariosEditor, String decision, boolean enviadoAlEditor) {
		super();
		this.revisor = revisor;
		this.articulo = articulo;
		this.fecha = fecha;
		this.comentariosAutor = comentariosAutor;
		this.comentariosEditor = comentariosEditor;
		this.decision = decision;
		this.enviadoAlEditor = enviadoAlEditor;
		
	}

	public RevisionEntity() {
		
	}
}
