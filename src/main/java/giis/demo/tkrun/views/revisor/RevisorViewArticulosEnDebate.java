package giis.demo.tkrun.views.revisor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.revisor.RevisorController;

public class RevisorViewArticulosEnDebate extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lbArticulos;
    private JScrollPane scrollPane;
    private JList<ArticuloEntity> listArticulos;
    private JButton btAbrirDebate;
    private List<ArticuloEntity> articulosEnDebate = new ArrayList<>();
    private RevisorController controller;
    private String idRevisor;

    /**
     * Create the frame.
     */
    public RevisorViewArticulosEnDebate(RevisorController controller, String idrevisor) {
	this.controller = controller;
	this.idRevisor = idrevisor;
	inicialice();
    }
    private void inicialice() {
	articulosEnDebate = controller.getArticulosEnDebate(idRevisor);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 748, 498);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getLbArticulos());
	contentPane.add(getScrollPane());
	contentPane.add(getBtAbrirDebate());
    }
	private JLabel getLbArticulos() {
		if (lbArticulos == null) {
			lbArticulos = new JLabel("Articulos en debate:");
			lbArticulos.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lbArticulos.setBounds(20, 55, 169, 19);
		}
		return lbArticulos;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(20, 80, 438, 368);
			scrollPane.setViewportView(getListArticulos());
		}
		return scrollPane;
	}
	private JList<ArticuloEntity> getListArticulos() {
		if (listArticulos == null) {
			listArticulos = new JList<ArticuloEntity>();
			listArticulos.setModel(addModel());
		}
		return listArticulos;
	}
	private JButton getBtAbrirDebate() {
		if (btAbrirDebate == null) {
			btAbrirDebate = new JButton("Abrir Debate");
			btAbrirDebate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    if(getListArticulos().getSelectedIndex() == -1)
					JOptionPane.showMessageDialog(null, "Debe seleccionar un artículo para ver el debate", 
						"Error con la selección del artículo", JOptionPane.ERROR_MESSAGE);
				    else if(getListArticulos().getSelectedIndex() > 1)
					JOptionPane.showMessageDialog(null, "Solo puede seleccionar un artículo para ver el debate", 
						"Error con la selección del artículo", JOptionPane.ERROR_MESSAGE);
				    else {
					ArticuloEntity art = getListArticulos().getSelectedValue();
					RevisorViewDebate vista = new RevisorViewDebate(art);
					vista.setModal(true);
					vista.setVisible(true);
				    }
				}
			});
			btAbrirDebate.setBackground(new Color(255, 218, 185));
			btAbrirDebate.setFont(new Font("Tahoma", Font.PLAIN, 21));
			btAbrirDebate.setBounds(507, 234, 192, 35);
		}
		return btAbrirDebate;
	}
	
	private ListModel<ArticuloEntity> addModel() {
		DefaultListModel<ArticuloEntity> model = new DefaultListModel<>();
		for (ArticuloEntity articulo : articulosEnDebate) {
		    model.addElement(articulo);
		}

		return model;
	    }
}
