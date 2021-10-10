package giis.demo.tkrun.articulo;

public class ArticuloEntity {

	private String id;
	private String titulo;
	private String primerAutor;
	private String otrosAutores; // como almacenar varios autores?????
	private String resumen;
	private String palabrasClave;
	private String ficheroFuente;
	private String cartaPresentacion;
	private String CVAutor;
	private String firma; // para asegurarse d q no hay plagios (string, boolean... da lo mismo)
	private String estado;

	public ArticuloEntity() {
	}

	public ArticuloEntity(String id, String titulo, String primerAutor, String otrosAutores, String resumen,
			String palabrasClave, String ficheroFuente, String cartaPresentacion, String cVAutor, String firma) {
		// no se pueden hacer validaciones todavia porque se puede rellenar la
		// informacion en cualquier momento

		this.id = id;
		this.titulo = titulo;
		this.primerAutor = primerAutor;
		this.otrosAutores = otrosAutores;
		this.resumen = resumen;
		this.palabrasClave = palabrasClave;
		this.ficheroFuente = ficheroFuente;
		this.cartaPresentacion = cartaPresentacion;
		CVAutor = cVAutor;
		this.firma = firma;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getOtrosAutores() {
		return otrosAutores;
	}

	public void setOtrosAutores(String otrosAutores) {
		this.otrosAutores = otrosAutores;
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

	public String getCVAutor() {
		return CVAutor;
	}

	public void setCVAutor(String cVAutor) {
		CVAutor = cVAutor;
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public void setEstado(String string) {
		this.estado = string;
	}

	public String getEstado() {
		return this.estado;
	}
}
