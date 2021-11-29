package giis.demo.tkrun.models.dtos;

public class ArticuloDeAutorDto {

    private String idArticulo;
    private String idAutor;

    public ArticuloDeAutorDto() {
    }

    public ArticuloDeAutorDto(String idArticulo, String idAutor) {
	// no se pueden hacer validaciones todavia porque se puede rellenar la
	// informacion en cualquier momento

	this.idArticulo = idArticulo;
	this.idAutor = idAutor;
    }

    public String getIdArticulo() {
	return idArticulo;
    }

    public void setIdArticulo(String idArticulo) {
	this.idArticulo = idArticulo;
    }

    public String getIdAutor() {
	return idAutor;
    }

    public void setIdAutor(String idAutor) {
	this.idAutor = idAutor;
    }

}
