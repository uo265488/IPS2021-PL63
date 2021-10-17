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
import giis.demo.util.articulo.ArticuloComparatorAutor;
import giis.demo.util.articulo.ArticuloComparatorTitulo;

import java.awt.GridLayout;

public class EditorPrincipalView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EditorController controller;
	private List<ArticuloEntity> articulos;
	private JPanel contentPane;
	private JComboBox<ArticuloEntity> cbArticulos;
	private JButton btnDetallesArticulo;
	private JLabel lbArticulos;
	private JLabel lbOrdenar;
	private JPanel pnOrdenar;
	private JButton btnOrdenarAutor;
	private JButton btnOrdenarTitulo;
	private JLabel lbFiltrar;
	private JPanel pnOrdenar_1;
	private JButton btnFiltrarTitulo;
	private JButton btnFiltrarAutor;

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
		this.articulos = this.controller.getArticulos();
		initialize();
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setTitle("Editor: Articulos disponibles");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getCbArticulos());
		contentPane.add(getBtnDetallesArticulo());
		contentPane.add(getLbArticulos());
		contentPane.add(getLbOrdenar());
		contentPane.add(getPnOrdenar());
		contentPane.add(getLbFiltrar());
		contentPane.add(getPnOrdenar_1());
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
			btnDetallesArticulo.setBounds(269, 284, 150, 29);
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
		
	private JLabel getLbOrdenar() {
		if (lbOrdenar == null) {
			lbOrdenar = new JLabel("Ordenar por:");
			lbOrdenar.setBounds(306, 58, 71, 21);
		}
		return lbOrdenar;
	}
	private JPanel getPnOrdenar() {
		if (pnOrdenar == null) {
			pnOrdenar = new JPanel();
			pnOrdenar.setBounds(382, 24, 105, 91);
			pnOrdenar.setLayout(new GridLayout(0, 1, 0, 0));
			pnOrdenar.add(getBtnOrdenarTitulo());
			pnOrdenar.add(getBtnOrdenarAutor());
		}
		return pnOrdenar;
	}
	private JButton getBtnOrdenarAutor() {
		if (btnOrdenarAutor == null) {
			btnOrdenarAutor = new JButton("Autor");
			btnOrdenarAutor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ordenarPorAutor();
				}
			});
		}
		return btnOrdenarAutor;
	}
	private JButton getBtnOrdenarTitulo() {
		if (btnOrdenarTitulo == null) {
			btnOrdenarTitulo = new JButton("Titulo");
			btnOrdenarTitulo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ordenarPorTitulo();
				}
			});
		}
		return btnOrdenarTitulo;
	}
	private JLabel getLbFiltrar() {
		if (lbFiltrar == null) {
			lbFiltrar = new JLabel("Filtrar por:");
			lbFiltrar.setBounds(306, 182, 71, 21);
		}
		return lbFiltrar;
	}
	private JPanel getPnOrdenar_1() {
		if (pnOrdenar_1 == null) {
			pnOrdenar_1 = new JPanel();
			pnOrdenar_1.setBounds(382, 148, 105, 91);
			pnOrdenar_1.setLayout(new GridLayout(0, 1, 0, 0));
			pnOrdenar_1.add(getBtnFiltrarTitulo());
			pnOrdenar_1.add(getBtnFiltrarAutor());
		}
		return pnOrdenar_1;
	}
	private JButton getBtnFiltrarTitulo() {
		if (btnFiltrarTitulo == null) {
			btnFiltrarTitulo = new JButton("Titulo");
		}
		return btnFiltrarTitulo;
	}
	private JButton getBtnFiltrarAutor() {
		if (btnFiltrarAutor == null) {
			btnFiltrarAutor = new JButton("Autor");
		}
		return btnFiltrarAutor;
	}
	
	private void ordenarPorTitulo() {
		articulos.sort(new ArticuloComparatorTitulo());
		setComboBoxModel();
	}
	
	private void ordenarPorAutor() {
		articulos.sort(new ArticuloComparatorAutor());
		setComboBoxModel();
	}
}
