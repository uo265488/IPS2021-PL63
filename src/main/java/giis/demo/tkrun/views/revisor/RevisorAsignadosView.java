package giis.demo.tkrun.views.revisor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.editor.EditorController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.revisor.RevisorController;
import giis.demo.tkrun.views.editor.EditorViewComentariosArticulo;

public class RevisorAsignadosView extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private RevisorController revisorController;
    private List<ArticuloEntity> articulosAsignados;
    private JLabel lbArticulosAsignados;
    private JButton btnRevisar;
    private JScrollPane scPaneArticulosAsignados;
    private JList<ArticuloEntity> listArticulosAsignados;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EditorAsignadosView frame = new EditorAsignadosView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

    public RevisorAsignadosView(RevisorController revisorController, String idRevisor) {
	setResizable(false);
	this.revisorController = revisorController;
	articulosAsignados = this.revisorController.getArticulosAsignados(idRevisor);
	setTitle("Revisor: " + revisorController.getRevisorById(idRevisor).getNombre() + ". Articulos asignados");
	initialize();
    }

    /**
     * Create the frame.
     */
    public void initialize() {
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 603, 335);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getLbArticulosAsignados());
	contentPane.add(getBtnRevisar());
	contentPane.add(getScPaneArticulosAsignados());
    }

    private JLabel getLbArticulosAsignados() {
	if (lbArticulosAsignados == null) {
	    lbArticulosAsignados = new JLabel("Articulos asignados para revisar:");
	    lbArticulosAsignados.setDisplayedMnemonic('A');
	    lbArticulosAsignados.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lbArticulosAsignados.setBounds(38, 49, 205, 25);
	}
	return lbArticulosAsignados;
    }

    private JButton getBtnRevisar() {
	if (btnRevisar == null) {
	    btnRevisar = new JButton("Visualizar");
	    btnRevisar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    EditorViewComentariosArticulo evca = new EditorViewComentariosArticulo(
			    getListArticulosAsignados().getSelectedValue(), new EditorController());

		    evca.setVisible(true);
		}
	    });
	    btnRevisar.setForeground(new Color(255, 255, 255));
	    btnRevisar.setBackground(new Color(0, 100, 0));
	    btnRevisar.setMnemonic('R');
	    btnRevisar.setBounds(432, 86, 89, 23);
	}
	return btnRevisar;
    }

    private JScrollPane getScPaneArticulosAsignados() {
	if (scPaneArticulosAsignados == null) {
	    scPaneArticulosAsignados = new JScrollPane();
	    scPaneArticulosAsignados.setBounds(48, 86, 374, 191);
	    scPaneArticulosAsignados.setViewportView(getListArticulosAsignados());
	}
	return scPaneArticulosAsignados;
    }

    private JList<ArticuloEntity> getListArticulosAsignados() {
	if (listArticulosAsignados == null) {
	    listArticulosAsignados = new JList<ArticuloEntity>();
	    listArticulosAsignados.setModel(addModel());
	}
	return listArticulosAsignados;
    }

    private ListModel<ArticuloEntity> addModel() {
	DefaultListModel<ArticuloEntity> articulos = new DefaultListModel<>();

	for (ArticuloEntity art : articulosAsignados) {
	    articulos.addElement(art);
	}

	return articulos;

    }
}
