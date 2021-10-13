package giis.demo.tkrun.controllers.entities;

public class ArticuloEntity {


	private int id;
	private String titulo;
	private String primerAutor;
	private String otrosAutores; // como almacenar varios autores?????
	private String resumen;
	private String palabrasClave;
	private String ficheroFuente;
	private String cartaPresentacion;
	private String CVAutor;
	private boolean firma; // para asegurarse d q no hay plagios (string, boolean... da lo mismo)
	private String estado;

	public static String CON_EL_EDITOR = "con el editor";
	public static String BORRADOR = "borrador";
	public static String RECHAZADO = "rechazado";
	public static String ACEPTADO = "aceptado";
	public static final String EN_REVISION = "en revision";
	
	
	public ArticuloEntity() {
	}

	public ArticuloEntity(int id, String titulo, String primerAutor, String otrosAutores, String resumen,
			String palabrasClave, String ficheroFuente, String cartaPresentacion, String cVAutor, boolean firma) {
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
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public boolean getFirma() {
		return firma;
	}

	public void setFirma(boolean firma) {
		this.firma = firma;
	}

	public void setEstado(String string) {
		this.estado = string;
	}

	public String getEstado() {
		return this.estado;
	}

	@Override
	public String toString() {
		return id + " - " + titulo + " - Autor: " + primerAutor;
	}
	
	
}
