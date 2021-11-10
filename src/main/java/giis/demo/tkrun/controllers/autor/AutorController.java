package giis.demo.tkrun.controllers.autor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.AutorEntity;
import giis.demo.tkrun.models.articulo.ArticuloModel;
import giis.demo.tkrun.models.autor.AutorModel;
import giis.demo.tkrun.models.dtos.ArticuloDeAutorDto;
import giis.demo.tkrun.models.dtos.ArticuloDto;
import giis.demo.tkrun.models.dtos.AutorDto;
import giis.demo.tkrun.models.dtos.RevisorDto;
import giis.demo.tkrun.models.dtos.UserDto;
import giis.demo.tkrun.models.revisor.RevisorModel;
import giis.demo.tkrun.models.user.UserModel;
import giis.demo.tkrun.views.autor.MenuAutor;
import giis.demo.util.EntityAssembler;

public class AutorController {

    private int id_autor;
    // private EditorView view; No hay vista todavía asi que esta todo comentado
    private AutorModel model;
    // private RevisionModel revisionModel;
    private ArticuloModel articuloModel;
    private MenuAutor view;
    private RevisorModel revisorModel;
    private UserModel userModel;
    // private RevisorModel revisoresModel;

    // public AutorController(AutorModel m, EditorView v) {
    // this.model = m;
    // this.view = v;
    // no hay inicializacion especifica del modelo, solo de la vista
    // this.initView();
    // }

    public AutorController() {
	this.model = new AutorModel();
	this.articuloModel = new ArticuloModel();
	initView();
    }

    public AutorController(boolean vista) {
	this.model = new AutorModel();
	this.articuloModel = new ArticuloModel();
    }

    public AutorController(int id_autor) {
	this.model = new AutorModel();
	this.id_autor = id_autor;
	articuloModel = new ArticuloModel();
	revisorModel = new RevisorModel();
	userModel = new UserModel();

	initView();
    }

    private void initView() {
	this.view = new MenuAutor(this, id_autor);
	view.setVisible(true);
	// view.setModal(true);
    }

    public List<ArticuloEntity> getArticulosPropios(int id) {

	return EntityAssembler.toArticuloEntityList(model.articulosDeUnAutor(id));
    }

    public List<ArticuloEntity> getArticulosAceptadosSinVersionDefinitiva(int id) {

	return EntityAssembler.toArticuloEntityList(model.articulosAceptadosSinVersionDefinitiva(id));
    }

    public AutorEntity findAutor(String nombre, String dni) {
	return EntityAssembler.toAutorEntity(model.findAutor(nombre, dni));
    }

    public AutorEntity findById(int id) {
	return EntityAssembler.toAutorEntity(model.findById(id));
    }

    public void crearBorrador(ArticuloDto articuloDto) {
	articuloModel.crearBorrador(articuloDto);
	articuloModel.asignarAutor(articuloDto, id_autor);
	parseOtrosAutores(articuloDto.getIdArticulo(), articuloDto.getOtrosAutores());
    }

    public void crearArticulo(ArticuloDto articuloDto) {
	articuloModel.crearArticulo(articuloDto);
    }

    public void parseOtrosAutores(int id_Articulo, String otrosAutores) {
	if (!otrosAutores.replaceAll("//s", "").isBlank()) {
	    String[] autores = otrosAutores.split(";");
	    if (autores.length > 0) {
		for (String line : autores) {
		    String[] autorAParsear = line.split("-");
		    AutorDto autor = new AutorDto();
		    autor.setNombre(autorAParsear[0].toLowerCase());
		    autor.setDni(autorAParsear[1].toLowerCase());
		    if (model.findAutor(autor.getNombre(), autor.getDni()) == null) {
			autor.setIdAutor(new Random().nextInt());
			model.addAutor(autor);
			createUser(autor);
		    } else {
			autor.setIdAutor(model.findAutor(autor.getNombre(), autor.getDni()).getIdAutor());
		    }

		    articuloModel.asignarOtroAutor(id_Articulo, autor.getIdAutor());
		}
	    }
	}

    }

    public void sugerirRevisores(int id_articulo, RevisorDto revisor) {
	revisorModel.sugerirRevisores(id_articulo, revisor);
    }

    private void createUser(AutorDto autor) {
	UserDto user = new UserDto();
	user.setIdUsuario(autor.getIdAutor());
	user.setNombre(autor.getNombre());
	user.setTipoUsuario("Autor");

	userModel.addUser(user);
    }

    public void actualizarBorrador(ArticuloDto articuloDto) {
	articuloModel.actualizarBorrador(articuloDto);
	parseOtrosAutores(articuloDto.getIdArticulo(), articuloDto.getOtrosAutores());

    }

    public void enviarBorrador(ArticuloDto articuloDto) {
	articuloModel.enviarBorrador(articuloDto);
	articuloModel.asignarAutor(articuloDto, id_autor);
	parseOtrosAutores(articuloDto.getIdArticulo(), articuloDto.getOtrosAutores());
    }

    public List<AutorEntity> findOtrosAutorEntities(int idArticulo, int id_autor) {
	List<ArticuloDeAutorDto> ids = model.findOtrosAutores(idArticulo, id_autor);
	List<AutorEntity> autores = new ArrayList<>();
	for (ArticuloDeAutorDto id : ids) {
	    autores.add(EntityAssembler.toAutorEntity(model.findById(id.getIdAutor())));
	}
	return autores;
    }

    public void getEnviarVersionDefinitiva(int id) {

	model.enviarVersionDefinitiva(id);
    }

    public void editarArticulo(ArticuloDto articuloDto) {
	articuloModel.update(articuloDto);
    }

    public void modificarArticulo(ArticuloDto articuloDto) {
	articuloModel.modificarArticulo(articuloDto);

    }

}