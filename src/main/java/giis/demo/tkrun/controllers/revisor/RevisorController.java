package giis.demo.tkrun.controllers.revisor;

import java.util.ArrayList;
import java.util.List;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisionEntity;
import giis.demo.tkrun.controllers.entities.RevisorEntity;
import giis.demo.tkrun.controllers.entities.SugerenciaEntity;
import giis.demo.tkrun.models.articulo.ArticuloModel;
import giis.demo.tkrun.models.debate.DebateModel;
import giis.demo.tkrun.models.dtos.ArticuloDto;
import giis.demo.tkrun.models.dtos.DebateDto;
import giis.demo.tkrun.models.dtos.MensajeDto;
import giis.demo.tkrun.models.dtos.RevisionDto;
import giis.demo.tkrun.models.dtos.RevisorDto;
import giis.demo.tkrun.models.revision.RevisionModel;
import giis.demo.tkrun.models.revisor.RevisorModel;
import giis.demo.tkrun.views.revisor.RevisorMenu;
import giis.demo.util.EntityAssembler;

public class RevisorController {

    // private EditorView view; No hay vista todavía asi que esta todo comentado
    private RevisionModel model;
    // private RevisionModel revisionModel;
    private ArticuloModel articuloModel;
    private RevisorModel revisoresModel;
    private RevisorMenu rm;
    private DebateModel debateModel;
    private String idRevisor;

    // public AutorController(AutorModel m, EditorView v) {
    // this.model = m;
    // this.view = v;
    // no hay inicializacion especifica del modelo, solo de la vista
    // this.initView();
    // }

    public RevisorController() {
	this.model = new RevisionModel();
	this.articuloModel = new ArticuloModel();
	this.revisoresModel = new RevisorModel();
	this.debateModel = new DebateModel();
    }

    public RevisorController(String idRevisor) {
	this.model = new RevisionModel();
	this.articuloModel = new ArticuloModel();
	this.revisoresModel = new RevisorModel();
	this.debateModel = new DebateModel();
	this.idRevisor = idRevisor;
	this.revisoresModel = new RevisorModel();
	// no hay inicializacion especifica del modelo, solo de la vista
	this.initView();
    }

    // public AutorController(AutorModel m, EditorView v) {
    // this.model = m;
    // this.view = v;
    // no hay inicializacion especifica del modelo, solo de la vista
    // this.initView();
    // }

    public void actualizarRevision(String comAutor, String comEditor, String decision, boolean enviarAlEditor,
	    String id, String idArt, int numeroRevision) {

	model.revisarArticulo(comAutor, comEditor, decision, enviarAlEditor, idArt, id, numeroRevision);
    }

    public void addRevisor(RevisorDto revisorDto) {
	revisoresModel.addRevisor(revisorDto);
    }

    public void decisionArticulo(String idRev, String idArt, boolean decision) {
	model.decisionArticulo(idRev, idArt, decision);
    }

    public RevisorEntity findRevisor(String nombre, String correo, String especialidad) {
	return EntityAssembler.toRevisorEntity(model.findRevisor(nombre, correo, especialidad));
    }

    public List<RevisorEntity> findSugeridos(String idArticulo) {
	List<SugerenciaEntity> ids = EntityAssembler.toSugerenciaEntityList(model.findSugeridos(idArticulo));
	List<RevisorEntity> revisores = new ArrayList<RevisorEntity>();
	for (SugerenciaEntity id : ids) {
	    revisores.add(EntityAssembler.toRevisorEntity(model.findByIdRevisor(id.getIdRevisor())));
	}

	return revisores;
    }

    // public void actualizarRevision(String comAutor, String comEditor, String
    // decision, boolean enviarAlEditor, int id, int idArt) {

    // model.revisarArticulo(comAutor, comEditor, decision, enviarAlEditor, idArt,
    // id);
    // }

    // --------------------------OSCAR-----------------------------------------------------------------------------
    public List<RevisionEntity> getAllRevisiones() {
	return EntityAssembler.toRevisionEntityList(model.findAll());
    }

    public List<RevisionEntity> getArticulosAceptados(String idArticulo) {
	return EntityAssembler.toRevisionEntityList(model.articulosAceptados(idArticulo));
    }

    public List<ArticuloEntity> getArticulosAsignados(String id) {
	return EntityAssembler.toArticuloEntityList(articuloModel.getArticulosAsignados(id));
    }

    public List<ArticuloEntity> getArticulosSinResponder(String id) {
	return EntityAssembler.toArticuloEntityList(articuloModel.getArticulosSinResponder(id));
    }

    public List<ArticuloEntity> getArticulosSinRevisar(String idArt) {

	List<RevisionDto> list = model.visualizarSinRevisar(idArt);
	List<ArticuloDto> listArt = new ArrayList<>();

	for (RevisionDto rev : list) {
	    listArt.add(articuloModel.findById(rev.getIdArticulo()));
	}

	return EntityAssembler.toArticuloEntityList(listArt);
    }

    public RevisionEntity getRevision(String idArticulo, String idRevisor) {
	return EntityAssembler.toRevisionEntity(model.getRevisionByIds(idArticulo, idRevisor));
    }

    public RevisionDto getFecha(String idRev, String idArticulo) {
	return model.getFecha(idRev, idArticulo);
    }

    public RevisionEntity getRevisionAnterior(String idArt, String idRev) {
	return EntityAssembler.toRevisionEntity(model.primeraRevision(idRev, idArt));
    }

    public RevisorEntity getRevisorById(String id) {
	return EntityAssembler.toRevisorEntity(revisoresModel.findById(id));
    }

    public List<ArticuloEntity> getTituloArticulosSinRevisar(String id) {

	return EntityAssembler.toArticuloEntityList(model.articulosSinRevisar(id));
    }

    private void initView() {
	rm = new RevisorMenu(idRevisor, this);
	rm.setVisible(true);

    }

    public int numeroRevisiones(String idArt, String idRev) {
	List<RevisionEntity> revisiones = EntityAssembler.toRevisionEntityList(model.numeroRevisiones(idRev, idArt));
	return revisiones.size();
    }

    public boolean todasLasRevisionesEnviadas(String idArt, int numeroRevision) {
	List<RevisionEntity> revisiones = EntityAssembler
		.toRevisionEntityList(model.revisionesEnviadas(idArt, numeroRevision));
	return revisiones.size() == 3;
    }

    public void updateArticulo(ArticuloDto articulo) {
	articuloModel.update(articulo);
    }
    
    public List<MensajeDto> mensajesDebate(String idDebate){
	return debateModel.devolverMensajes(idDebate);
    }
    
    public String idDebate(String idArticulo) {
	List<DebateDto> lista = debateModel.getIdDebate(idArticulo);
	if(lista.size() > 0)
	    return lista.get(0).getIdDebate();
	return "";
    }
    
    public List<ArticuloEntity> getArticulosEnDebate(String id) {
	return EntityAssembler.toArticuloEntityList(articuloModel.getArticulosEnDebate(id));
    }
    
    public void envioMensaje(String idDebate, String mensaje) {
	debateModel.escribirMensaje(idDebate, mensaje);
    }
    
}
