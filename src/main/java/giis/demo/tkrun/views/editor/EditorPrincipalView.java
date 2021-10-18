package giis.demo.tkrun.views.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.editor.EditorController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;

public class EditorPrincipalView extends JFrame {

	private EditorController controller;
	private List<ArticuloEntity> articulos;
	private JPanel contentPane;
	private JComboBox<ArticuloEntity> cbArticulos;
	private JButton btnDetallesArticulo;
	private JLabel lbArticulos;
	private JLabel lbTODO;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EditorPrincipalView frame = new EditorPrincipalView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public EditorPrincipalView(EditorController controller) {
		this.controller = controller;
		this.articulos = this.controller.getArticulosTomarDecision();
		initialize();
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setTitle("Editor: Articulos disponibles");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getCbArticulos());
		contentPane.add(getBtnDetallesArticulo());
		contentPane.add(getLbArticulos());
		contentPane.add(getLbTODO());
	}
	private JComboBox<ArticuloEntity> getCbArticulos() {
		if (cbArticulos == null) {
			cbArticulos = new JComboBox<ArticuloEntity>();
			cbArticulos.setBounds(50, 43, 220, 123);
			setComboBoxModel();
		}
		return cbArticulos;
	}
	private JButton getBtnDetallesArticulo() {
		if (btnDetallesArticulo == null) {
			btnDetallesArticulo = new JButton("Detalles del articulo");
			btnDetallesArticulo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					launchAsignar();
				}
			});
			btnDetallesArticulo.setMnemonic('D');
			btnDetallesArticulo.setBounds(267, 233, 150, 29);
		}
		return btnDetallesArticulo;
	}
	private JLabel getLbArticulos() {
		if (lbArticulos == null) {
			lbArticulos = new JLabel("Articulos disponibles en la revista:");
			lbArticulos.setLabelFor(getCbArticulos());
			lbArticulos.setDisplayedMnemonic('A');
			lbArticulos.setBounds(50, 11, 220, 21);
		}
		return lbArticulos;
	}
	private JLabel getLbTODO() {
		if (lbTODO == null) {
			lbTODO = new JLabel("Aqui va lo de ordenar/filtrar");
			lbTODO.setBounds(307, 14, 150, 168);
		}
		return lbTODO;
	}
	
	private void setComboBoxModel() {
		ArticuloEntity[] articulosEntity = new ArticuloEntity[this.articulos.size()];
		for (int i = 0; i<articulosEntity.length;  i++) {
			articulosEntity[i] = articulos.get(i);
		}
		
		getCbArticulos().setModel(new DefaultComboBoxModel<ArticuloEntity>(articulosEntity));
		
	}
	
	private void launchAsignar() {
		ArticuloEntity articulo = (ArticuloEntity) getCbArticulos().getSelectedItem();
		EditorView eV = new EditorView(controller, articulo);
		eV.setLocationRelativeTo(this);
		eV.setModal(true);
		eV.setVisible(true);
	}
		
}
