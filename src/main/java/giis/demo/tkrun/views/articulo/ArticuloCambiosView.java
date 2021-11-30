package giis.demo.tkrun.views.articulo;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import giis.demo.tkrun.controllers.autor.AutorController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisionEntity;
import giis.demo.tkrun.controllers.revision.RevisionController;
import giis.demo.tkrun.views.autor.AutorCreacionView;

public class ArticuloCambiosView extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private ArticuloEntity articulo;
    private String id_autor;
    private JLabel lbTituloArticulo;
    private JTextField txtFArticulo;
    private JLabel lbCambios;
    private JLabel lbComentariosRevisores;
    private JScrollPane scrollPane;
    private JTextArea txtAreaComentariosRevisores;
    private JLabel lblComentariosEditor;

    private RevisionController revisionController;
    private AutorController autorController;
    private JButton btnModificarArticulo;
    private JTextField txtFComentariosAutor;

//    /**
//     * Launch the application.
//     */
//    public static void main(String[] args) {
//	EventQueue.invokeLater(new Runnable() {
//	    public void run() {
//		try {
//		    AutorCambiosView frame = new AutorCambiosView();
//		    frame.setVisible(true);
//		} catch (Exception e) {
//		    e.printStackTrace();
//		}
//	    }
//	});
//    }

    /**
     * Create the frame.
     */
    public ArticuloCambiosView(ArticuloEntity articulo, String id_autor) {
	this.articulo = articulo;
	revisionController = new RevisionController();
	autorController = new AutorController(id_autor);
	this.id_autor = id_autor;
	initialize();

    }

    private void initialize() {
	setTitle("Autor. Cambios sugeridos");
	setBounds(100, 100, 630, 515);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	getContentPane().setLayout(null);
	getContentPane().add(getLbTituloArticulo());
	getContentPane().add(getTxtFArticulo());
	getContentPane().add(getLbCambios());
	getContentPane().add(getLbComentariosRevisores());
	getContentPane().add(getScrollPane());
	getContentPane().add(getLblComentariosEditor());
	getContentPane().add(getBtnModificarArticulo());
	getContentPane().add(getTxtFComentariosAutor());
    }

    private JLabel getLbTituloArticulo() {
	if (lbTituloArticulo == null) {
	    lbTituloArticulo = new JLabel("Artículo: ");
	    lbTituloArticulo.setBounds(10, 11, 79, 24);
	}
	return lbTituloArticulo;
    }

    private JTextField getTxtFArticulo() {
	if (txtFArticulo == null) {
	    txtFArticulo = new JTextField();
	    txtFArticulo.setEditable(false);
	    txtFArticulo.setBounds(99, 11, 505, 24);
	    txtFArticulo.setColumns(10);
	    txtFArticulo.setText(articulo.toString());
	}
	return txtFArticulo;
    }

    private JLabel getLbCambios() {
	if (lbCambios == null) {
	    lbCambios = new JLabel("Cambios a realizar");
	    lbCambios.setHorizontalAlignment(SwingConstants.CENTER);
	    lbCambios.setFont(new Font("Tahoma", Font.BOLD, 13));
	    lbCambios.setBounds(226, 52, 162, 24);
	}
	return lbCambios;
    }

    private JLabel getLbComentariosRevisores() {
	if (lbComentariosRevisores == null) {
	    lbComentariosRevisores = new JLabel("Comentarios de los revisores:");
	    lbComentariosRevisores.setBounds(10, 104, 173, 24);
	}
	return lbComentariosRevisores;
    }

    private JScrollPane getScrollPane() {
	if (scrollPane == null) {
	    scrollPane = new JScrollPane();
	    scrollPane.setBounds(10, 139, 594, 119);
	    scrollPane.setViewportView(getTxtAreaComentariosRevisores());
	}
	return scrollPane;
    }

    private JTextArea getTxtAreaComentariosRevisores() {
	if (txtAreaComentariosRevisores == null) {
	    txtAreaComentariosRevisores = new JTextArea();
	    txtAreaComentariosRevisores.setEditable(false);
	    txtAreaComentariosRevisores.setText(getComentariosRevisores());
	}
	return txtAreaComentariosRevisores;
    }

    private JLabel getLblComentariosEditor() {
	if (lblComentariosEditor == null) {
	    lblComentariosEditor = new JLabel("Comentarios del editor: ");
	    lblComentariosEditor.setBounds(10, 269, 173, 24);
	}
	return lblComentariosEditor;
    }

    private String getComentariosRevisores() {
	StringBuffer comentarios = new StringBuffer();
	List<RevisionEntity> revisiones = revisionController.getRevisionesDelArticulo(articulo);

	for (RevisionEntity rev : revisiones) {
	    comentarios.append(rev.getComentariosAutor() + "\n");
	}

	return comentarios.toString();
    }

    private JButton getBtnModificarArticulo() {
	if (btnModificarArticulo == null) {
	    btnModificarArticulo = new JButton("Modificar articulo");
	    btnModificarArticulo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    modificarArticulo();
		}
	    });
	    btnModificarArticulo.setBounds(390, 434, 140, 29);
	}
	return btnModificarArticulo;
    }

    private void modificarArticulo() {
	AutorCreacionView acv = new AutorCreacionView(autorController, id_autor, articulo);
	acv.setVisible(true);
	//acv.setModal(true);
	this.dispose();
    }

    private JTextField getTxtFComentariosAutor() {
	if (txtFComentariosAutor == null) {
	    txtFComentariosAutor = new JTextField();
	    txtFComentariosAutor.setEditable(false);
	    txtFComentariosAutor.setBounds(10, 304, 594, 24);
	    txtFComentariosAutor.setColumns(10);
	    txtFComentariosAutor.setText(articulo.getCartaDecision());
	}
	return txtFComentariosAutor;
    }
}
