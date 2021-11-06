package giis.demo.tkrun.controllers.revisor;

import java.util.List;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisionEntity;
import giis.demo.tkrun.controllers.entities.RevisorEntity;
import giis.demo.tkrun.models.articulo.ArticuloModel;
import giis.demo.tkrun.models.revision.RevisionModel;
import giis.demo.tkrun.views.revisor.RevisorAsignadosView;
import giis.demo.util.EntityAssembler;

public class RevisorController {

    // private EditorView view; No hay vista todavía asi que esta todo comentado
    private RevisionModel model;
    // private RevisionModel revisionModel;
    private ArticuloModel articuloModel;
    // private RevisorModel revisoresModel;
    private RevisorAsignadosView rav;
    private int idRevisor;

    // public AutorController(AutorModel m, EditorView v) {
    // this.model = m;
    // this.view = v;
    // no hay inicializacion especifica del modelo, solo de la vista
    // this.initView();
    // }

    public RevisorController(int idRevisor) {
	this.model = new RevisionModel();
	this.articuloModel = new ArticuloModel();
	this.idRevisor = idRevisor;
	// no hay inicializacion especifica del modelo, solo de la vista
	this.initView();
    }

    public RevisorController() {
	this.model = new RevisionModel();
	this.articuloModel = new ArticuloModel();
    }

    private void initView() {

	rav = new RevisorAsignadosView(this, idRevisor);
	rav.setVisible(true);

    }

    public RevisionEntity getArticulosSinRevisar(int id, int idArt) {

	return EntityAssembler.toRevisionEntity(model.visualizarSinRevisar(id, idArt));
    }

    public List<ArticuloEntity> getTituloArticulosSinRevisar(int id) {

	return EntityAssembler.toArticuloEntityList(model.articulosSinRevisar(id));
    }

    public void actualizarRevision(String comAutor, String comEditor, String decision, boolean enviarAlEditor, int id,
	    int idArt) {

	model.revisarArticulo(comAutor, comEditor, decision, enviarAlEditor, idArt, id);
    }

    public List<ArticuloEntity> getArticulosAsignados(int id) {
	return EntityAssembler.toArticuloEntityList(articuloModel.getArticulosAsignados(id));
    }

    public RevisorEntity findRevisor(String nombre, String correo, String especialidad) {
	return EntityAssembler.toRevisorEntity(model.findRevisor(nombre, correo, especialidad));
    }
}
