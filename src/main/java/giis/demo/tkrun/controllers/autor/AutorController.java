package giis.demo.tkrun.controllers.autor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    private String id_autor;
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

    public AutorController(String id_autor) {
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

    public List<ArticuloEntity> getArticulosPropios(String id) {

	return EntityAssembler.toArticuloEntityList(model.articulosDeUnAutor(id));
    }

    public List<ArticuloEntity> getArticulosAceptadosSinVersionDefinitiva(String id) {

	return EntityAssembler.toArticuloEntityList(model.articulosAceptadosSinVersionDefinitiva(id));
    }

    public AutorEntity findAutor(String nombre, String dni) {
	return EntityAssembler.toAutorEntity(model.findAutor(nombre, dni));
    }

    public AutorEntity findById(String id) {
	return EntityAssembler.toAutorEntity(model.findById(id));
    }

    public boolean crearBorrador(ArticuloDto articuloDto) {
	if (!parseOtrosAutores(articuloDto.getIdArticulo(), articuloDto.getOtrosAutores())) {
	    return false;
	} else {
	    articuloModel.crearBorrador(articuloDto);
	    articuloModel.asignarAutor(articuloDto, id_autor);
	    return true;

	}

    }

    public boolean crearArticulo(ArticuloDto articuloDto) {
	if (!parseOtrosAutores(articuloDto.getIdArticulo(), articuloDto.getOtrosAutores())) {
	    return false;
	}
	articuloModel.crearArticulo(articuloDto);
	articuloModel.asignarAutor(articuloDto, id_autor);
	return true;

    }

    public boolean parseOtrosAutores(String id_Articulo, String otrosAutores) {
	if (!(otrosAutores.replaceAll("//s", "").trim().length()==0)) {
	    String[] autores = otrosAutores.split(";");
	    if (autores.length > 0) {
		for (String line : autores) {
		    String[] autorAParsear = line.split("-");
		    if (autorAParsear.length > 1) {
			AutorDto autor = new AutorDto();
			autor.setNombre(autorAParsear[0].toLowerCase());
			autor.setDni(autorAParsear[1].toLowerCase());
			if (model.findAutor(autor.getNombre(), autor.getDni()) == null) {
			    autor.setIdAutor(UUID.randomUUID().toString());
			    model.addAutor(autor);
			    createUser(autor);
			} else {
			    autor.setIdAutor(model.findAutor(autor.getNombre(), autor.getDni()).getIdAutor());
			}

			articuloModel.asignarOtroAutor(id_Articulo, autor.getIdAutor());

			return true;
		    }
		    return false;
		}
	    }
	    return false;
	}
	return true;

    }

    public void sugerirRevisores(String id_articulo, RevisorDto revisor) {
	revisorModel.sugerirRevisores(id_articulo, revisor);
    }

    private void createUser(AutorDto autor) {
	UserDto user = new UserDto();
	user.setIdUsuario(autor.getIdAutor());
	user.setNombre(autor.getNombre());
	user.setTipoUsuario("Autor");

	userModel.addUser(user);
    }

    public boolean actualizarBorrador(ArticuloDto articuloDto) {
	if (!parseOtrosAutores(articuloDto.getIdArticulo(), articuloDto.getOtrosAutores())) {
	    return false;
	}
	articuloModel.actualizarBorrador(articuloDto);
	return true;

    }

    public boolean enviarBorrador(ArticuloDto articuloDto) {
	if (!parseOtrosAutores(articuloDto.getIdArticulo(), articuloDto.getOtrosAutores())) {
	    return false;
	}
	articuloModel.enviarBorrador(articuloDto);
	articuloModel.asignarAutor(articuloDto, id_autor);
	return true;

    }

    public List<AutorEntity> findOtrosAutorEntities(String idArticulo, String id_autor) {
	List<ArticuloDeAutorDto> ids = model.findOtrosAutores(idArticulo, id_autor);
	List<AutorEntity> autores = new ArrayList<>();
	for (ArticuloDeAutorDto id : ids) {
	    autores.add(EntityAssembler.toAutorEntity(model.findById(id.getIdAutor())));
	}
	return autores;
    }

    public void getEnviarVersionDefinitiva(String id) {

	model.enviarVersionDefinitiva(id);
    }

    public void editarArticulo(ArticuloDto articuloDto) {
	articuloModel.update(articuloDto);
    }

    public void modificarArticulo(ArticuloDto articuloDto) {
	articuloModel.modificarArticulo(articuloDto);

    }

    public AutorEntity getAutorById(String id_autor) {
	return EntityAssembler.toAutorEntity(model.findById(id_autor));
    }

    public AutorEntity findAutorByArticulo(String idArticulo) {
	return EntityAssembler.toAutorEntity(model.findByIdArticulo(idArticulo));
    }

}