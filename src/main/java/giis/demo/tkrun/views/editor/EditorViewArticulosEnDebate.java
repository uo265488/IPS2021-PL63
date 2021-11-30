package giis.demo.tkrun.views.editor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.editor.EditorController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;

public class EditorViewArticulosEnDebate extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnCargarArticulos;
    private JComboBox<ArticuloEntity> cbrticulosEnDebate;
    private List<ArticuloEntity> articulos = new ArrayList<ArticuloEntity>();
    private EditorController controller;
    private JButton btnVerDebate;

    /**
     * Create the frame.
     */
    public EditorViewArticulosEnDebate(EditorController controller) {
	this.controller = controller;
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 705, 185);
	contentPane = new JPanel();
	contentPane.setBackground(Color.WHITE);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getBtnCargarArticulos());
	contentPane.add(getCbrticulosEnDebate());
	contentPane.add(getBtnVerDebate());
	setTitle("Editor. Articulos en debate");
    }

    private JButton getBtnCargarArticulos() {
	if (btnCargarArticulos == null) {
	    btnCargarArticulos = new JButton("Cargar Articulos");
	    btnCargarArticulos.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    articulos = controller.getArticulosEnDebate();
		    rellenarComboBox();
		}
	    });
	    btnCargarArticulos.setBackground(new Color(0, 204, 0));
	    btnCargarArticulos.setForeground(Color.WHITE);
	    btnCargarArticulos.setBounds(536, 29, 128, 28);
	}
	return btnCargarArticulos;
    }

    private void rellenarComboBox() {
	ArticuloEntity[] vector = new ArticuloEntity[0];
	if (this.articulos.size() > 0) {
	    vector = new ArticuloEntity[this.articulos.size()];
	    for (int i = 0; i < vector.length; i++) {
		vector[i] = this.articulos.get(i);
	    }
	}
	getCbrticulosEnDebate().setModel(new DefaultComboBoxModel<ArticuloEntity>(vector));
    }

    private JComboBox<ArticuloEntity> getCbrticulosEnDebate() {
	if (cbrticulosEnDebate == null) {
	    cbrticulosEnDebate = new JComboBox<ArticuloEntity>();
	    cbrticulosEnDebate.setBounds(24, 31, 472, 25);
	}
	return cbrticulosEnDebate;
    }

    private JButton getBtnVerDebate() {
	if (btnVerDebate == null) {
	    btnVerDebate = new JButton("Ver Debate");
	    btnVerDebate.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    mostrarDebate();
		}
	    });
	    btnVerDebate.setForeground(Color.WHITE);
	    btnVerDebate.setBackground(new Color(0, 204, 0));
	    btnVerDebate.setBounds(536, 81, 128, 28);
	}
	return btnVerDebate;
    }

    private void mostrarDebate() {
	if (getCbrticulosEnDebate().getItemCount() > 0) {
	    ArticuloEntity art = (ArticuloEntity) getCbrticulosEnDebate().getSelectedItem();
	    if (controller.getEstadoDelDebate(art.getIdArticulo())) {
		EditorDebateView ven = new EditorDebateView(art, controller);
		ven.setVisible(true);
	    } else {
		JOptionPane.showMessageDialog(this, "No hay un debate abierto de este articulo");
	    }
	}
    }
}
