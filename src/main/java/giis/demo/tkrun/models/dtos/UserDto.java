package giis.demo.tkrun.models.dtos;

public class UserDto {

	private String nombre;
	private String tipoUsuario;  //If the user is an editor, author or reviewer.
	
	public UserDto(String userName, String type) {
		super();
		this.nombre = userName;
		this.tipoUsuario = type;
	}
	
	public UserDto() {}

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
	
}
