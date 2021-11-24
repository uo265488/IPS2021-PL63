package giis.demo.tkrun.models.dtos;

import java.time.LocalDate;

public class DebateDto {

	private String idDebate;
	private String idArticulo;
	private LocalDate fecha;
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

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public boolean isAbierto() {
		return abierto;
	}

	public void setAbierto(boolean abierto) {
		this.abierto = abierto;
	}

}
