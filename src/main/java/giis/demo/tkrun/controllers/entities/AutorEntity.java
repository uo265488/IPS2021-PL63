package giis.demo.tkrun.controllers.entities;

public class AutorEntity {

    private String idAutor;
    private String nombre;
    private String dni;

    public AutorEntity(String id, String nombre, String dni) {
	super();
	this.idAutor = id;
	this.nombre = nombre;
	this.dni = dni;
    }

    public AutorEntity() {
    }

    public String getIdAutor() {
	return idAutor;
    }

    public void setIdAutor(String id) {
	this.idAutor = id;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getDni() {
	return dni;
    }

    public void setDni(String dni) {
	this.dni = dni;
    }

    @Override
    public String toString() {
	return nombre + " - " + dni;
    }
}
