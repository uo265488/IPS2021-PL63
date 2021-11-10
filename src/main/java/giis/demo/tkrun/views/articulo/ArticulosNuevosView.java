package giis.demo.tkrun.views.articulo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.articulo.ArticuloController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;

public class ArticulosNuevosView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<ArticuloEntity> cbArticulos;
	private JButton btnRechazar;
	private JButton btnVisualizar;
	private JLabel lblArticulosNuevos;
	
	private ArticuloController articulosController;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ArticulosNuevosView frame = new ArticulosNuevosView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	

	/**
	 * Create the frame.
	 */
	public ArticulosNuevosView(ArticuloController controller) {
		
		this.articulosController = controller;
		
		setResizable(false);
		setTitle("Articulos nuevos view");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 331);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getCbArticulos());
		contentPane.add(getBtnRechazar());
		contentPane.add(getBtnVisualizar());
		contentPane.add(getLblArticulosNuevos());
	}

	private JComboBox<ArticuloEntity> getCbArticulos() {
		if (cbArticulos == null) {
			cbArticulos = new JComboBox<ArticuloEntity>();
			cbArticulos.setBounds(55, 79, 288, 57);
			setComboBoxModel();
		}
		return cbArticulos;
	}

	/**
	 * Le pide al controlador los articulos nuevos y los muestra
	 */
	private void setComboBoxModel() {
		ArticuloEntity[] array = new ArticuloEntity[this.articulosController.getArticulosNuevos().size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = articulosController.getArticulosNuevos().get(i);
		}
		cbArticulos.setModel(new DefaultComboBoxModel<ArticuloEntity>(array));

	}

	private JButton getBtnRechazar() {
		if (btnRechazar == null) {
			btnRechazar = new JButton("Rechazar");
			btnRechazar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rechazarArticulo();
				}
			});
			btnRechazar.setBackground(Color.RED);
			btnRechazar.setBounds(432, 212, 89, 34);
		}
		return btnRechazar;
	}

	/**
	 * Rechaza el articulo
	 */
	protected void rechazarArticulo() {
		if (cbArticulos.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(this, "Primero debes seleccionar un artículo para rechazar.");
		} else {
			if ((JOptionPane.showConfirmDialog(this, "Estás seguro de que quieres rechazar el artículo")) == JOptionPane.YES_OPTION) {
				
				articulosController.rechazarArticulo((ArticuloEntity)cbArticulos.getSelectedItem());
				setComboBoxModel();//actualizar el combobox
			}
		}
		
	}

	private JButton getBtnVisualizar() {
		if (btnVisualizar == null) {
			btnVisualizar = new JButton("Visualizar");
			btnVisualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					visualizarArticulo();
				}
			});
			btnVisualizar.setBackground(Color.GREEN);
			btnVisualizar.setBounds(432, 87, 89, 40);
		}
		return btnVisualizar;
	}

	/**
	 * Muestra la informacion del articulo seleccionado en el comboBox
	 */
	protected void visualizarArticulo() {
		if (cbArticulos.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(this, "Primero debes seleccionar un artículo para visualizar.");
		} else {
			articulosController.visualizarArticulo((ArticuloEntity)cbArticulos.getSelectedItem());
			
			VisualizarArticuloView vistaArticulo = new VisualizarArticuloView((ArticuloEntity)cbArticulos.getSelectedItem());
			
			vistaArticulo.setModal(true);
			vistaArticulo.setLocationRelativeTo(this);
			vistaArticulo.setVisible(true);
			
		}
		
	}

	private JLabel getLblArticulosNuevos() {
		if (lblArticulosNuevos == null) {
			lblArticulosNuevos = new JLabel("Articulos nuevos:");
			lblArticulosNuevos.setLabelFor(getCbArticulos());
			lblArticulosNuevos.setBounds(55, 54, 132, 14);
		}
		return lblArticulosNuevos;
	}
}
