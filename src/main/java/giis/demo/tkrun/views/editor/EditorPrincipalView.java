package giis.demo.tkrun.views.editor;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

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
    private JScrollPane sPArticulos;
    private JList<ArticuloEntity> listArticulos;
    private JButton btnEstadoAsignaciones;
    private final ButtonGroup buttonGroupOrdenar = new ButtonGroup();
    private JPanel pnFiltrarOrdenar;
    private JPanel pnOrdenar;
    private JRadioButton rdbtnOrdenarTitulo;
    private JRadioButton rdbtnOrdenarAutor;
    private JPanel pnFiltrar;
    private JButton btnFiltrar;
    private JButton btnQuitarFiltros;
    private JRadioButton rdbtnFiltrarTitulo;
    private JRadioButton rdbtnFiltrarAutor;
    private final ButtonGroup buttonGroupFiltrar = new ButtonGroup();
    private JTextField txtFFilter;

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

    private void filtrarAutor(String autor) {
	articulos = controller.getArticulosFiltradoAutor(autor);

	getListArticulos().setModel(addModel());
    }

    private void filtrarTitulo(String titulo) {
	articulos = controller.getArticulosFiltradoTitulo(titulo);

	getListArticulos().setModel(addModel());
    }

    private JButton getBtnDetallesArticulo() {
	if (btnDetallesArticulo == null) {
	    btnDetallesArticulo = new JButton("Detalles del articulo");
	    btnDetallesArticulo.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    if (getListArticulos().getSelectedValue() == null) {
			JOptionPane.showMessageDialog(getContentPane(), "Debes escoger un artículo.");
		    } else {
			launchAsignar();
		    }

		}
	    });
	    btnDetallesArticulo.setMnemonic('D');
	    btnDetallesArticulo.setBounds(207, 334, 150, 29);
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
	    btnEstadoAsignaciones.setBounds(26, 337, 150, 23);
	}
	return btnEstadoAsignaciones;
    }

    private JLabel getLbArticulos() {
	if (lbArticulos == null) {
	    lbArticulos = new JLabel("Articulos disponibles en la revista:");
	    lbArticulos.setDisplayedMnemonic('A');
	    lbArticulos.setBounds(26, 22, 220, 21);
	}
	return lbArticulos;
    }

    private JList<ArticuloEntity> getListArticulos() {
	if (listArticulos == null) {
	    listArticulos = new JList<ArticuloEntity>();
	    listArticulos.setModel(addModel());
	}
	return listArticulos;
    }

    private JScrollPane getSPArticulos() {
	if (sPArticulos == null) {
	    sPArticulos = new JScrollPane();
	    sPArticulos.setBorder(new LineBorder(new Color(130, 135, 144), 2));
	    sPArticulos.setBounds(26, 53, 626, 262);
	    sPArticulos.setViewportView(getListArticulos());
	}
	return sPArticulos;
    }

    /**
     * Create the frame.
     */
    public void initialize() {
	setTitle("Editor. Pantalla principal de asignación");
	setResizable(false);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 1015, 439);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getBtnDetallesArticulo());
	contentPane.add(getLbArticulos());
	contentPane.add(getSPArticulos());
	contentPane.add(getBtnEstadoAsignaciones());
	contentPane.add(getPnFiltrarOrdenar());
    }

    private void launchAsignar() {
	ArticuloEntity articulo = getListArticulos().getSelectedValue();
	EditorAsignarView eV = new EditorAsignarView(articulo);
	eV.setLocationRelativeTo(this);
	eV.setModal(true);
	eV.setVisible(true);
    }

    private void sortBy(String sorter) {
	if (sorter.equals("Titulo")) {
	    articulos.sort(new ArticuloComparatorTitulo());
	}
	if (sorter.equals("Autor")) {
	    articulos.sort(new ArticuloComparatorAutor());
	}

	getListArticulos().setModel(addModel());

    }

    private JPanel getPnFiltrarOrdenar() {
	if (pnFiltrarOrdenar == null) {
	    pnFiltrarOrdenar = new JPanel();
	    pnFiltrarOrdenar.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true),
		    "Filtrar y ordenar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    pnFiltrarOrdenar.setBounds(669, 46, 303, 329);
	    pnFiltrarOrdenar.setLayout(null);
	    pnFiltrarOrdenar.add(getPnOrdenar());
	    pnFiltrarOrdenar.add(getPnFiltrar());
	}
	return pnFiltrarOrdenar;
    }

    private JPanel getPnOrdenar() {
	if (pnOrdenar == null) {
	    pnOrdenar = new JPanel();
	    pnOrdenar.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Ordenar", TitledBorder.LEADING,
		    TitledBorder.TOP, null, null));
	    pnOrdenar.setBounds(10, 27, 283, 85);
	    pnOrdenar.setLayout(new GridLayout(2, 0, 0, 0));
	    pnOrdenar.add(getRdbtnOrdenarTitulo());
	    pnOrdenar.add(getRdbtnOrdenarAutor());
	}
	return pnOrdenar;
    }

    private JRadioButton getRdbtnOrdenarTitulo() {
	if (rdbtnOrdenarTitulo == null) {
	    rdbtnOrdenarTitulo = new JRadioButton("Ordenar por titulo");
	    rdbtnOrdenarTitulo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    sortBy("Titulo");
		}
	    });
	    buttonGroupOrdenar.add(rdbtnOrdenarTitulo);
	}
	return rdbtnOrdenarTitulo;
    }

    private JRadioButton getRdbtnOrdenarAutor() {
	if (rdbtnOrdenarAutor == null) {
	    rdbtnOrdenarAutor = new JRadioButton("Ordenar por autor");
	    rdbtnOrdenarAutor.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    sortBy("Autor");
		}
	    });
	    buttonGroupOrdenar.add(rdbtnOrdenarAutor);
	}
	return rdbtnOrdenarAutor;
    }

    private JPanel getPnFiltrar() {
	if (pnFiltrar == null) {
	    pnFiltrar = new JPanel();
	    pnFiltrar.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Filtrar",
		    TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    pnFiltrar.setBounds(10, 123, 283, 188);
	    pnFiltrar.setLayout(null);
	    pnFiltrar.add(getBtnFiltrar());
	    pnFiltrar.add(getBtnQuitarFiltros());
	    pnFiltrar.add(getRdbtnFiltrarTitulo());
	    pnFiltrar.add(getRdbtnFiltrarAutor());
	    pnFiltrar.add(getTxtFFilter());
	}
	return pnFiltrar;
    }

    private JButton getBtnFiltrar() {
	if (btnFiltrar == null) {
	    btnFiltrar = new JButton("Filtrar");
	    btnFiltrar.setForeground(new Color(0, 0, 0));
	    btnFiltrar.setBackground(new Color(102, 153, 51));
	    btnFiltrar.setFont(new Font("Times New Roman", Font.BOLD, 12));
	    btnFiltrar.setMnemonic('F');
	    btnFiltrar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    String filter = getTxtFFilter().getText();
		    if (getRdbtnFiltrarTitulo().isSelected()) {
			filtrarTitulo(filter);
		    } else {
			filtrarAutor(filter);
		    }
		}
	    });
	    btnFiltrar.setBounds(20, 144, 105, 33);
	}
	return btnFiltrar;
    }

    private JButton getBtnQuitarFiltros() {
	if (btnQuitarFiltros == null) {
	    btnQuitarFiltros = new JButton("Quitar filtros");
	    btnQuitarFiltros.setBackground(SystemColor.activeCaption);
	    btnQuitarFiltros.setFont(new Font("Times New Roman", Font.BOLD, 12));
	    btnQuitarFiltros.setMnemonic('Q');
	    btnQuitarFiltros.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    getTxtFFilter().setText("");
		    articulos = controller.getArticulos();
		    getListArticulos().setModel(addModel());
		}
	    });
	    btnQuitarFiltros.setBounds(145, 144, 105, 33);
	}
	return btnQuitarFiltros;
    }

    private JRadioButton getRdbtnFiltrarTitulo() {
	if (rdbtnFiltrarTitulo == null) {
	    rdbtnFiltrarTitulo = new JRadioButton("Filtrar por titulo");
	    rdbtnFiltrarTitulo.setSelected(true);
	    buttonGroupFiltrar.add(rdbtnFiltrarTitulo);
	    rdbtnFiltrarTitulo.setBounds(21, 20, 128, 23);
	}
	return rdbtnFiltrarTitulo;
    }

    private JRadioButton getRdbtnFiltrarAutor() {
	if (rdbtnFiltrarAutor == null) {
	    rdbtnFiltrarAutor = new JRadioButton("Filtrar por autor");
	    buttonGroupFiltrar.add(rdbtnFiltrarAutor);
	    rdbtnFiltrarAutor.setBounds(151, 20, 126, 23);
	}
	return rdbtnFiltrarAutor;
    }

    private JTextField getTxtFFilter() {
	if (txtFFilter == null) {
	    txtFFilter = new JTextField();
	    txtFFilter.setHorizontalAlignment(SwingConstants.CENTER);
	    txtFFilter.setBounds(77, 77, 128, 33);
	    txtFFilter.setColumns(10);
	}
	return txtFFilter;
    }
}
