package giis.demo.tkrun.controllers.entities;

public class ArticuloEntity {

    public static String CON_EL_EDITOR = "con el editor";
    public static String BORRADOR = "borrador";
    public static String RECHAZADO = "rechazado";
    public static String ACEPTADO = "aceptado";
    public static final String EN_REVISION = "en revision";
    public static final String PUBLICADO = "publicado";
    public static final String ACEPTADO_CAMBIOS_MENORES = "aceptado con cambios menores";
    public static final String ACEPTADO_CAMBIOS_MAYORES = "aceptado con cambios mayores";
    public static final String EN_DEBATE = "en debate";

<<<<<<< HEAD
	private int vecesRevisado;
	private boolean versionDefinitiva;
	private String cartaDecision;
	private String DOI;
	private String fecha;
	private int volumen;
	private boolean pendienteDeCambios;
=======
    private String idArticulo;
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
>>>>>>> refs/heads/master

    private int vecesRevisado;
    private boolean versionDefinitiva;
    private String cartaDecision;
    private String DOI;
    private String fecha;
    private int volumen;

    public String getCartaDecision() {
	return cartaDecision;
    }

    public String getCartaPresentacion() {
	return cartaPresentacion;
    }

    public String getCV() {
	return CV;
    }

    public String getDOI() {
	return DOI;
    }

    public String getEstado() {
	return estado;
    }

    public String getFecha() {
	return fecha;
    }

    public String getFicheroFuente() {
	return ficheroFuente;
    }

    public String getIdArticulo() {
	return idArticulo;
    }

    public String getOtrosAutores() {
	return otrosAutores;
    }

    public String getPalabrasClave() {
	return palabrasClave;
    }

    public String getPrimerAutor() {
	return primerAutor;
    }

    public String getResumen() {
	return resumen;
    }

    public String getTitulo() {
	return titulo;
    }

    public int getVecesRevisado() {
	return vecesRevisado;
    }

    public int getVolumen() {
	return volumen;
    }

    public boolean isFirma() {
	return firma;
    }

    public boolean isVersionDefinitiva() {
	return versionDefinitiva;
    }

    public void setCartaDecision(String cartaDecision) {
	this.cartaDecision = cartaDecision;
    }

    public void setCartaPresentacion(String cartaPresentacion) {
	this.cartaPresentacion = cartaPresentacion;
    }

    public void setCV(String cV) {
	CV = cV;
    }

    public void setDOI(String dOI) {
	DOI = dOI;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public void setFecha(String fecha) {
	this.fecha = fecha;
    }

    public void setFicheroFuente(String ficheroFuente) {
	this.ficheroFuente = ficheroFuente;
    }

    public void setFirma(boolean firma) {
	this.firma = firma;
    }

    public void setIdArticulo(String idArticulo) {
	this.idArticulo = idArticulo;
    }

    public void setOtrosAutores(String otrosAutores) {
	this.otrosAutores = otrosAutores;
    }

    public void setPalabrasClave(String palabrasClave) {
	this.palabrasClave = palabrasClave;
    }

    public void setPrimerAutor(String primerAutor) {
	this.primerAutor = primerAutor;
    }

    public void setResumen(String resumen) {
	this.resumen = resumen;
    }

    public void setTitulo(String titulo) {
	this.titulo = titulo;
    }

    public void setVecesRevisado(int vecesRevisado) {
	this.vecesRevisado = vecesRevisado;
    }

    public void setVersionDefinitiva(boolean versionDefinitiva) {
	this.versionDefinitiva = versionDefinitiva;
    }

    public void setVolumen(int volumen) {
	this.volumen = volumen;
    }

    @Override
    public String toString() {
	String str = "";
	if (titulo != null) {
	    str += " - " + titulo;
	}
	if (primerAutor != null) {
	    str += " - Autor: " + primerAutor;
	}
	if (estado != null) {
	    str += " - Estado: " + estado;
	}
	str += " - Veces Revisado: " + vecesRevisado;
	return str;
    }

    public String toStringForTable() {
	String str = "";
	if (titulo != null) {
	    str += " - " + titulo;
	}
	if (primerAutor != null) {
	    str += " - Autor: " + primerAutor;
	}

	return str;
    }

	public boolean isPendienteDeCambios() {
		return pendienteDeCambios;
	}

	public void setPendienteDeCambios(boolean pendienteDeCambios) {
		this.pendienteDeCambios = pendienteDeCambios;
	}

}
