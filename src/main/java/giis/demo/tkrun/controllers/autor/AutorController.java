package giis.demo.tkrun.controllers.autor;

import java.util.List;
import java.util.Random;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.AutorEntity;
import giis.demo.tkrun.models.articulo.ArticuloModel;
import giis.demo.tkrun.models.autor.AutorModel;
import giis.demo.tkrun.models.dtos.ArticuloDto;
import giis.demo.tkrun.models.dtos.AutorDto;
import giis.demo.tkrun.models.dtos.RevisorDto;
import giis.demo.tkrun.models.revisor.RevisorModel;
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

    // public AutorController(AutorModel m, EditorView v) {
    // this.model = m;
    // this.view = v;
    // no hay inicializacion especifica del modelo, solo de la vista
    // this.initView();
    // }

    public AutorController(int id_autor) {
	this.model = new AutorModel();
	this.id_autor = id_autor;
	articuloModel = new ArticuloModel();
	revisorModel = new RevisorModel();
	initView();
    }

    private void initView() {
	this.view = new MenuAutor(this, id_autor);
	view.setVisible(true);
    }

    public List<ArticuloEntity> getArticulosPropios(int id) {

	return EntityAssembler.toArticuloEntityList(model.articulosDeUnAutor(id));
    }

    public List<ArticuloEntity> getArticulosAceptadosSinVersionDefinitiva(int id) {

	return EntityAssembler.toArticuloEntityList(model.articulosAceptadosSinVersionDefinitiva(id));
    }

    public AutorEntity findById(int id) {
	return EntityAssembler.toAutorEntity(model.findById(id));
    }

    public void getEnviarVersionDefinitiva(int id) {

	model.enviarVersionDefinitiva(id);
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
	String[] autores = otrosAutores.split(";");

	for (String line : autores) {
	    String[] autorAParsear = line.split("-");
	    AutorDto autor = new AutorDto();
	    autor.setIdAutor(new Random().nextInt());
	    autor.setNombre(autorAParsear[0]);
	    autor.setDni(autorAParsear[1]);
	    if (model.findById(autor.getIdAutor()) == null) {
		model.addAutor(autor);
	    }

	    articuloModel.asignarOtroAutor(id_Articulo, autor.getIdAutor());
	}
    }

    public void sugerirRevisores(int id_articulo, RevisorDto revisor) {

    }
}