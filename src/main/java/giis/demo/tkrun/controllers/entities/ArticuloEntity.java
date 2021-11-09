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
	private String cartaDecision;
	private String DOI;
	private String fecha;
	private int volumen;
	
	public static final String CON_EL_EDITOR = "con el editor";
	public static final String PENDIENTE_DE_ACEPTACION = "pendiente de aceptacion";
	public static final String EN_REVISION = "en revision";
	public static final String BORRADOR = "borrador";
	public static final String RECHAZADO = "rechazado";
	public static final String ACEPTADO = "aceptado";
	public static final String PUBLICADO = "publicado";

	
	
	public ArticuloEntity(int idArticulo, String titulo, String primerAutor, String otrosAutores, String resumen,
			String palabrasClave, String ficheroFuente, String cartaPresentacion, String cV, boolean firma,
			String estado, int vecesRevisado, boolean versionDefinitiva, String cartaDecision, String dOI, String fecha,
			int volumen) {
		super();
		this.idArticulo = idArticulo;
		this.titulo = titulo;
		this.primerAutor = primerAutor;
		this.otrosAutores = otrosAutores;
		this.resumen = resumen;
		this.palabrasClave = palabrasClave;
		this.ficheroFuente = ficheroFuente;
		this.cartaPresentacion = cartaPresentacion;
		CV = cV;
		this.firma = firma;
		this.estado = estado;
		this.vecesRevisado = vecesRevisado;
		this.versionDefinitiva = versionDefinitiva;
		this.cartaDecision = cartaDecision;
		DOI = dOI;
		this.fecha = fecha;
		this.volumen = volumen;
	}

	public ArticuloEntity() {
		// TODO Auto-generated constructor stub
	}

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
		String str = "" + idArticulo;
		if(titulo != null)
			str += " - " + titulo;
		if(primerAutor != null)
			str += " - Autor: " + primerAutor;
		if(estado != null)
			str += " - Estado: " + estado;
		str += " - Veces Revisado: " + vecesRevisado;
		return str;
	}

	public String getCartaDecision() {
		return cartaDecision;
	}

	public void setCartaDecision(String cartaDecision) {
		this.cartaDecision = cartaDecision;
	}

	public String getDOI() {
		return DOI;
	}

	public void setDOI(String dOI) {
		DOI = dOI;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getVolumen() {
		return volumen;
	}

	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}

	public String toStringForTable() {
		String str = "" + idArticulo;
		if(titulo != null)
			str += " - " + titulo;
		if(primerAutor != null)
			str += " - Autor: " + primerAutor;

		return str;
	}	
}


