package giis.demo.tkrun.views.editor;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.editor.EditorController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.util.articulo.ArticuloComparatorAutor;
import giis.demo.util.articulo.ArticuloComparatorTitulo;

public class EditorPrincipalView extends JDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private EditorController controller;
	private List<ArticuloEntity> articulos;
	private JPanel contentPane;
	private JButton btnDetallesArticulo;
	private JLabel lbArticulos;
	private JLabel lbOrdenar;
	private JPanel pnOrdenar;
	private JButton btnOrdenarAutor;
	private JButton btnOrdenarTitulo;
	private JLabel lbFiltrar;
	private JComboBox<String> cbBoxFiltrar;
	private JButton btnFiltrar;
	private JTextField txtFFiltrado;
	private JButton btnQuitarFiltros;
	private JScrollPane sPArticulos;
	private JList<ArticuloEntity> listArticulos;
	private JButton btnEstadoAsignaciones;

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

	protected void abrirVentanaEstadoDeAsignaciones() {
		EstadoDeAsignacionesView vista = new EstadoDeAsignacionesView();
		vista.setVisible(true);

	}

	private ListModel<ArticuloEntity> addModel() {
		DefaultListModel<ArticuloEntity> model = new DefaultListModel<>();
		for (ArticuloEntity articulo : articulos) {
			model.addElement(articulo);
		}

		return model;
	}

	private void filtrar() {
		String filtrado = (String) getCbBoxFiltrar().getSelectedItem();
		String filtro = getTxtFFiltrado().getText();

		if (filtrado.equals("Titulo")) {
			filtrarTitulo(filtro);
		}
		if (filtrado.equals("Autor")) {
			filtrarAutor(filtro);
		}
	}

	private void filtrarAutor(String autor) {
		articulos = controller.getArticulosFiltradoAutor(autor);

		setComboBoxModel();
	}

	private void filtrarTitulo(String titulo) {
		articulos = controller.getArticulosFiltradoTitulo(titulo);

		setComboBoxModel();
	}

	private JButton getBtnDetallesArticulo() {
		if (btnDetallesArticulo == null) {
			btnDetallesArticulo = new JButton("Detalles del articulo");
			btnDetallesArticulo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					launchAsignar();
				}
			});
			btnDetallesArticulo.setMnemonic('D');
			btnDetallesArticulo.setBounds(319, 284, 150, 29);
		}
		return btnDetallesArticulo;
	}

	private JButton getBtnEstadoAsignaciones() {
		if (btnEstadoAsignaciones == null) {
			btnEstadoAsignaciones = new JButton("Estado de asignaciones");
			btnEstadoAsignaciones.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					abrirVentanaEstadoDeAsignaciones();
				}
			});
			btnEstadoAsignaciones.setBackground(Color.GREEN);
			btnEstadoAsignaciones.setBounds(319, 250, 150, 23);
		}
		return btnEstadoAsignaciones;
	}

	private JButton getBtnFiltrar() {
		if (btnFiltrar == null) {
			btnFiltrar = new JButton("Filtrar");
			btnFiltrar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					filtrar();
				}
			});
			btnFiltrar.setBounds(187, 223, 89, 23);
		}
		return btnFiltrar;
	}

	private JButton getBtnOrdenarAutor() {
		if (btnOrdenarAutor == null) {
			btnOrdenarAutor = new JButton("Autor");
			btnOrdenarAutor.addActionListener(new ActionListener() {
				@Override
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
				@Override
				public void actionPerformed(ActionEvent e) {
					ordenarPorTitulo();
				}
			});
		}
		return btnOrdenarTitulo;
	}

	private JButton getBtnQuitarFiltros() {
		if (btnQuitarFiltros == null) {
			btnQuitarFiltros = new JButton("Quitar filtros");
			btnQuitarFiltros.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					quitarFiltros();
				}
			});
			btnQuitarFiltros.setBounds(161, 257, 115, 23);
		}
		return btnQuitarFiltros;
	}

	private JComboBox<String> getCbBoxFiltrar() {
		if (cbBoxFiltrar == null) {
			cbBoxFiltrar = new JComboBox<String>();
			cbBoxFiltrar.setModel(new DefaultComboBoxModel<String>(new String[] { "Titulo", "Autor" }));
			cbBoxFiltrar.setBounds(75, 191, 105, 21);
		}
		return cbBoxFiltrar;
	}

	private JLabel getLbArticulos() {
		if (lbArticulos == null) {
			lbArticulos = new JLabel("Articulos disponibles en la revista:");
			lbArticulos.setDisplayedMnemonic('A');
			lbArticulos.setBounds(50, 11, 220, 21);
		}
		return lbArticulos;
	}

	private JLabel getLbFiltrar() {
		if (lbFiltrar == null) {
			lbFiltrar = new JLabel("Filtrar por:");
			lbFiltrar.setBounds(10, 195, 71, 14);
		}
		return lbFiltrar;
	}

	private JLabel getLbOrdenar() {
		if (lbOrdenar == null) {
			lbOrdenar = new JLabel("Ordenar por:");
			lbOrdenar.setBounds(310, 87, 71, 21);
		}
		return lbOrdenar;
	}

	private JList<ArticuloEntity> getListArticulos() {
		if (listArticulos == null) {
			listArticulos = new JList<ArticuloEntity>();
			listArticulos.setModel(addModel());
		}
		return listArticulos;
	}

	private JPanel getPnOrdenar() {
		if (pnOrdenar == null) {
			pnOrdenar = new JPanel();
			pnOrdenar.setBounds(386, 53, 105, 91);
			pnOrdenar.setLayout(new GridLayout(0, 1, 0, 0));
			pnOrdenar.add(getBtnOrdenarTitulo());
			pnOrdenar.add(getBtnOrdenarAutor());
		}
		return pnOrdenar;
	}

	private JScrollPane getSPArticulos() {
		if (sPArticulos == null) {
			sPArticulos = new JScrollPane();
			sPArticulos.setBounds(26, 53, 244, 117);
			sPArticulos.setViewportView(getListArticulos());
		}
		return sPArticulos;
	}

	private JTextField getTxtFFiltrado() {
		if (txtFFiltrado == null) {
			txtFFiltrado = new JTextField();
			txtFFiltrado.setBounds(190, 192, 86, 20);
			txtFFiltrado.setColumns(10);
		}
		return txtFFiltrado;
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setTitle("Editor: Articulos disponibles");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 517, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnDetallesArticulo());
		contentPane.add(getLbArticulos());
		contentPane.add(getLbOrdenar());
		contentPane.add(getPnOrdenar());
		contentPane.add(getLbFiltrar());
		contentPane.add(getCbBoxFiltrar());
		contentPane.add(getBtnFiltrar());
		contentPane.add(getTxtFFiltrado());
		contentPane.add(getBtnQuitarFiltros());
		contentPane.add(getSPArticulos());
		contentPane.add(getBtnEstadoAsignaciones());
	}

	private void launchAsignar() {
		ArticuloEntity articulo = getListArticulos().getSelectedValue();
		EditorAsignarView eV = new EditorAsignarView(articulo);
		eV.setLocationRelativeTo(this);
		eV.setModal(true);
		eV.setVisible(true);
	}

	private void ordenarPorAutor() {
		articulos.sort(new ArticuloComparatorAutor());
		setComboBoxModel();
	}

	private void ordenarPorTitulo() {
		articulos.sort(new ArticuloComparatorTitulo());
		setComboBoxModel();
	}

	private void quitarFiltros() {
		articulos = controller.getArticulos();

		setComboBoxModel();
	}

	private void setComboBoxModel() {
		ArticuloEntity[] articulosEntity = new ArticuloEntity[this.articulos.size()];
		for (int i = 0; i < articulosEntity.length; i++) {
			articulosEntity[i] = articulos.get(i);
		}

	}
}
