package giis.demo.tkrun.models.dtos;

public class ArticuloDto {

	private String idArticulo;
	private String titulo;
	private String primerAutor;
	private String resumen;
	private String palabrasClave;
	private String ficheroFuente;
	private String cartaPresentacion;
	private String CV;
	private boolean firma;        //para asegurarse d q no hay plagios (string, boolean... da lo mismo)
	private String estado;
	private boolean revisado;
	
	public ArticuloDto() {}

	public ArticuloDto(String id, String titulo, String primerAutor, String resumen,
			String palabrasClave, String ficheroFuente, String cartaPresentacion, String cVAutor, boolean firma) {
		//no se pueden hacer validaciones todavia porque se puede rellenar la informacion en cualquier momento
		
		this.idArticulo = id;
		this.titulo = titulo;
		this.primerAutor = primerAutor;
		this.resumen = resumen;
		this.palabrasClave = palabrasClave;
		this.ficheroFuente = ficheroFuente;
		this.cartaPresentacion = cartaPresentacion;
		CV = cVAutor;
		this.firma = firma;
	}

	public String getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(String id) {
		this.idArticulo = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getPrimerAutor() {
		return primerAutor;
	}

	public void setPrimerAutor(String primerAutor) {
		this.primerAutor = primerAutor;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public String getPalabrasClave() {
		return palabrasClave;
	}

	public void setPalabrasClave(String palabrasClave) {
		this.palabrasClave = palabrasClave;
	}

	public String getFicheroFuente() {
		return ficheroFuente;
	}

	public void setFicheroFuente(String ficheroFuente) {
		this.ficheroFuente = ficheroFuente;
	}

	public String getCartaPresentacion() {
		return cartaPresentacion;
	}

	public void setCartaPresentacion(String cartaPresentacion) {
		this.cartaPresentacion = cartaPresentacion;
	}

	public String getCV() {
		return CV;
	}

	public void setCV(String cVAutor) {
		CV = cVAutor;
	}

	public boolean getFirma() {
		return firma;
	}

	public void setFirma(boolean firma) {
		this.firma = firma;
	}

	public void setEstado(String estado) {
		this.estado = estado;
		
	}
	
	public String getEstado() {
		return this.estado;
	}

	public boolean getRevisado() {
		return revisado;
	}

	public void setRevisado(boolean revisado) {
		this.revisado = revisado;
	}
	
	
	
	
	
	
	
}
