package giis.demo.tkrun.models.dtos;

public class UserDto {

    private String nombre;
    private String tipoUsuario; // If the user is an editor, author or reviewer.
    private int idUsuario;

    public UserDto(String userName, String type, int idUsuario) {
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

    public int getIdUsuario() {
	return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
	this.idUsuario = idUsuario;
    }

}
