package giis.demo.tkrun.views.editor;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.editor.EditorController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.views.articulo.VisualizarArticuloView;

import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ListModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class EditorViewArticulosParaPublicar extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnActualizar;
	private JButton btnDetallesArticulo;
	private JButton btnPublicar;
	private JScrollPane spArticulosParaPublicar;
	private List<ArticuloEntity> articulos;
	private EditorController controller;
	private JList<ArticuloEntity> listArticulosParaPublicar;

	/**
	 * Create the frame.
	 */
	public EditorViewArticulosParaPublicar(EditorController controller) {
		setTitle("Articulos Pendientes de Publicar");
		this.controller = controller;
		this.articulos = controller.getArticulosParaPublicar();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnActualizar());
		contentPane.add(getBtnDetallesArticulo());
		contentPane.add(getBtnPublicar());
		contentPane.add(getSpArticulosParaPublicar());
	}
	private JButton getBtnActualizar() {
		if (btnActualizar == null) {
			btnActualizar = new JButton("Actualizar");
			btnActualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					articulos = controller.getArticulosParaPublicar();
					listArticulosParaPublicar.setModel(addModel());
				}
			});
			btnActualizar.setBounds(526, 27, 131, 22);
		}
		return btnActualizar;
	}
	private JButton getBtnDetallesArticulo() {
		if (btnDetallesArticulo == null) {
			btnDetallesArticulo = new JButton("Detalles del Articulo");
			btnDetallesArticulo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    if (getListArticulosParaPublicar().getSelectedValue() == null) {
						JOptionPane.showMessageDialog(getContentPane(), "Debes escoger un artículo.");
					    } else {
						verArticulo();
					    }
				}
			});
			btnDetallesArticulo.setBounds(526, 172, 131, 22);
		}
		return btnDetallesArticulo;
	}
	
    private void verArticulo() {
	ArticuloEntity articulo = getListArticulosParaPublicar().getSelectedValue();
	VisualizarArticuloView eV = new VisualizarArticuloView(articulo);
	eV.setLocationRelativeTo(this);
	//eV.setModal(true);
	eV.setVisible(true);
    }
    
    private JButton getBtnPublicar() {
		if (btnPublicar == null) {
			btnPublicar = new JButton("Publicar Articulo");
			btnPublicar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    if (getListArticulosParaPublicar().getSelectedValue() == null) {
						JOptionPane.showMessageDialog(getContentPane(), "Debes escoger un artículo.");
					    } else {
						verVentanaPublicarArticulo();
					    }
				}
			});
			btnPublicar.setBounds(526, 215, 131, 22);
		}
		return btnPublicar;
	}
    
    private void verVentanaPublicarArticulo() {
    	EditorViewPublicarArticulo ven = new EditorViewPublicarArticulo((getListArticulosParaPublicar().getSelectedValue()));
    	ven.setVisible(true);
    }
    
	private JScrollPane getSpArticulosParaPublicar() {
		if (spArticulosParaPublicar == null) {
			spArticulosParaPublicar = new JScrollPane();
			spArticulosParaPublicar.setBounds(25, 27, 469, 223);
			spArticulosParaPublicar.setViewportView(getListArticulosParaPublicar());
		}
		return spArticulosParaPublicar;
	}
	
    private ListModel<ArticuloEntity> addModel() {
	DefaultListModel<ArticuloEntity> model = new DefaultListModel<>();
	for (ArticuloEntity articulo : articulos) {
	    model.addElement(articulo);
	}

	return model;
    }
	private JList<ArticuloEntity> getListArticulosParaPublicar() {
		if (listArticulosParaPublicar == null) {
			listArticulosParaPublicar = new JList<ArticuloEntity>();
			listArticulosParaPublicar.setModel(addModel());
		}
		return listArticulosParaPublicar;
	}
}
