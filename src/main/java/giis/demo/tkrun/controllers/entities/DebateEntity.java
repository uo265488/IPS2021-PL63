package giis.demo.tkrun.controllers.entities;

public class DebateEntity {
	private String idDebate;
	private String idArticulo;
	private String fecha;
	private boolean abierto;

	public String getIdDebate() {
		return idDebate;
	}

	public void setIdDebate(String idDebate) {
		this.idDebate = idDebate;
	}

	public String getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public boolean isAbierto() {
		return abierto;
	}

	public void setAbierto(boolean abierto) {
		this.abierto = abierto;
	}
}
