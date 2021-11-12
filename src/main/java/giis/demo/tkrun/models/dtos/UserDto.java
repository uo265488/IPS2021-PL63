package giis.demo.tkrun.models.dtos;

public class UserDto {

    private String nombre;
    private String tipoUsuario; // If the user is an editor, author or reviewer.
    private String idUsuario;

    public UserDto(String userName, String type, String idUsuario) {
	super();
	this.nombre = userName;
	this.tipoUsuario = type;
	this.idUsuario = idUsuario;
    }

    public UserDto() {
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String userName) {
	this.nombre = userName;
    }

    public String getTipoUsuario() {
	return tipoUsuario;
    }

    public void setTipoUsuario(String type) {
	this.tipoUsuario = type;
    }

    public String getIdUsuario() {
	return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
	this.idUsuario = idUsuario;
    }

}
