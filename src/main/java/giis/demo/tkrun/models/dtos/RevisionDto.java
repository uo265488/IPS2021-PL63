package giis.demo.tkrun.models.dtos;

public class RevisionDto {
	
	private RevisorDto revisor;
	
	private ArticuloDto articulo;
	
	private String fecha;

	public RevisorDto getRevisor() {
		return revisor;
	}

	public void setRevisor(RevisorDto revisor) {
		this.revisor = revisor;
	}

	public ArticuloDto getArticulo() {
		return articulo;
	}

	public void setArticulo(ArticuloDto articulo) {
		this.articulo = articulo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public RevisionDto(RevisorDto revisor, ArticuloDto articulo, String fecha) {
		super();
		this.revisor = revisor;
		this.articulo = articulo;
		this.fecha = fecha;
	}
	
	public RevisionDto() { }
	
	
	
	

}
