package giis.demo.tkrun.models.dtos;

public class ArticuloDeAutorDto {

	private int idArticulo;
	private int idAutor;
	
	public ArticuloDeAutorDto() {}

	public ArticuloDeAutorDto(int idArticulo, int idAutor) {
		//no se pueden hacer validaciones todavia porque se puede rellenar la informacion en cualquier momento
		
		this.idArticulo = idArticulo;
		this.idAutor = idAutor;
	}

	public int getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

	public int getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}

}
