package giis.demo.tkrun.controllers.entities;

public class ArticuloEntity {
	
	private int idArticulo;
	private String titulo;
	private String primerAutor;
	private String otrosAutores; // como almacenar varios autores?????
	private String resumen;
	private String palabrasClave;
	private String ficheroFuente;
	private String cartaPresentacion;
	private String CV;
	private boolean firma; // para asegurarse d q no hay plagios (string, boolean... da lo mismo)
	private String estado;
	private int vecesRevisado;
	private boolean versionDefinitiva;

	public static String CON_EL_EDITOR = "con el editor";
	public static String BORRADOR = "borrador";
	public static String RECHAZADO = "rechazado";
	public static String ACEPTADO = "aceptado";
	public static final String EN_REVISION = "en revision";

	public int getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
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

	public String getCV() {
		return CV;
	}

	public void setCV(String cV) {
		CV = cV;
	}

	public boolean isFirma() {
		return firma;
	}

	public void setFirma(boolean firma) {
		this.firma = firma;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getVecesRevisado() {
		return vecesRevisado;
	}

	public void setVecesRevisado(int vecesRevisado) {
		this.vecesRevisado = vecesRevisado;
	}

	public boolean isVersionDefinitiva() {
		return versionDefinitiva;
	}

	public void setVersionDefinitiva(boolean versionDefinitiva) {
		this.versionDefinitiva = versionDefinitiva;
	}

	@Override
	public String toString() {
		return idArticulo + " - " + titulo + " - Autor: " + primerAutor;
	}
}


