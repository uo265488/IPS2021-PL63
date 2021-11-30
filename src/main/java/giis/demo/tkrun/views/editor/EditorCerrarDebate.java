package giis.demo.tkrun.views.editor;

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
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import giis.demo.tkrun.controllers.articulo.ArticuloController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;

public class EditorCerrarDebate extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private ArticuloController controller = new ArticuloController();
    private JScrollPane scPDebates;
    private JList<ArticuloEntity> list;
    private JLabel lbDebates;
    private JButton btnCerrarDebate;
    private List<ArticuloEntity> articulos;

    /**
     * Create the dialog.
     */
    public EditorCerrarDebate() {
	setTitle("Editor. Cerrar debates");
	setResizable(false);
	setBounds(100, 100, 400, 300);
	getContentPane().setLayout(null);
	getContentPane().add(getScPDebates());
	getContentPane().add(getLbDebates());
	getContentPane().add(getBtnCerrarDebate());
    }

    private JScrollPane getScPDebates() {
	if (scPDebates == null) {
	    scPDebates = new JScrollPane();
	    scPDebates.setBounds(41, 45, 302, 147);
	    scPDebates.setViewportView(getList());
	}
	return scPDebates;
    }

    private JList<ArticuloEntity> getList() {
	if (list == null) {
	    list = new JList<ArticuloEntity>();
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    list.setModel(addModel());
	}
	return list;
    }

    private JLabel getLbDebates() {
	if (lbDebates == null) {
	    lbDebates = new JLabel("Articulos con debates abiertos: ");
	    lbDebates.setBounds(41, 11, 184, 23);
	}
	return lbDebates;
    }

    private JButton getBtnCerrarDebate() {
	if (btnCerrarDebate == null) {
	    btnCerrarDebate = new JButton("Cerrar debate");
	    btnCerrarDebate.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    ArticuloEntity articulo = getList().getSelectedValue();
		    controller.cerrarDebate(articulo.getIdArticulo());
		    getList().setModel(addModel());
		}
	    });
	    btnCerrarDebate.setForeground(new Color(255, 255, 255));
	    btnCerrarDebate.setFont(new Font("Tahoma", Font.BOLD, 12));
	    btnCerrarDebate.setBackground(new Color(70, 130, 180));
	    btnCerrarDebate.setMnemonic('C');
	    btnCerrarDebate.setBounds(121, 203, 141, 28);
	}
	return btnCerrarDebate;
    }

    private ListModel<ArticuloEntity> addModel() {
	DefaultListModel<ArticuloEntity> model = new DefaultListModel<>();
	articulos = controller.getArticulosConDebate();

	for (ArticuloEntity articulo : articulos) {
	    model.addElement(articulo);
	}

	return model;
    }
}
