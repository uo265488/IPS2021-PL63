package giis.demo.tkrun.views.autor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.autor.AutorController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.views.articulo.VisualizarArticuloView;

public class AutorView extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lbAutor;
    private JLabel lbId;
    private JTextField txId;
    private JScrollPane scrollPane;
    private List<ArticuloEntity> articulosDelEditor = new ArrayList<ArticuloEntity>();
    private List<ArticuloEntity> articulosAceptadosSinVersionDefinitiva = new ArrayList<ArticuloEntity>();
    private AutorController controller;
    private JButton btConfirmar;
    private JButton btMirarArticulos;
    private JLabel lbSinPublicar;
    private JComboBox<ArticuloEntity> cbArticulosSinPublicar;
    private JCheckBox chCopy;
    private JButton btnEnviarArticulo;
    private JButton btVisualizar;
    private int id_autor;
    private JButton btnModificarBorrador;
    private JList<ArticuloEntity> listArticulos;

    /// **
    // * Launch the application.
    // */
    // public static void main(String[] args) {
    // EventQueue.invokeLater(new Runnable() {
    // public void run() {
    // try {
    // AutorView frame = new AutorView();
    // frame.setVisible(true);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }
    // });
    // }

    /**
     * Create the frame.
     */
    public AutorView(AutorController controller, int id_autor) {
	this.controller = controller;
	this.id_autor = id_autor;
	inicialize();
    }

    private void inicialize() {
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 1083, 586);
	setTitle("Vista de Autor");
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getLbAutor());
	contentPane.add(getLbId());
	contentPane.add(getTxId());
	contentPane.add(getScrollPane());
	contentPane.add(getBtConfirmar());
	contentPane.add(getBtMirarArticulos());
	contentPane.add(getLbSinPublicar());
	contentPane.add(getCbArticulosSinPublicar());
	contentPane.add(getChCopy());
	contentPane.add(getBtnEnviarArticulo());
	contentPane.add(getBtVisualizar());
	contentPane.add(getBtnModificarBorrador());
	setVisible(true);
	setResizable(false);
    }

    private JLabel getLbAutor() {
	if (lbAutor == null) {
	    lbAutor = new JLabel("Vista Autor:");
	    lbAutor.setFont(new Font("Tahoma", Font.ITALIC, 25));
	    lbAutor.setBounds(482, 11, 178, 33);
	}
	return lbAutor;
    }

    private JLabel getLbId() {
	if (lbId == null) {
	    lbId = new JLabel("Meta su identificador para ver sus artículos:");
	    lbId.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lbId.setBounds(10, 77, 298, 22);
	}
	return lbId;
    }

    private JTextField getTxId() {
	if (txId == null) {
	    txId = new JTextField();
	    txId.setText("" + id_autor);
	    txId.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    txId.setBounds(318, 78, 191, 20);
	    txId.setColumns(10);

	}
	return txId;
    }

    private JScrollPane getScrollPane() {
	if (scrollPane == null) {
	    scrollPane = new JScrollPane();
	    scrollPane.setBounds(10, 168, 594, 319);
	    scrollPane.setViewportView(getListArticulos());
	}
	return scrollPane;
    }

    private JButton getBtConfirmar() {
	if (btConfirmar == null) {
	    btConfirmar = new JButton("Visualizar Artículos");
	    btConfirmar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    getBtnEnviarArticulo().setEnabled(false);
		    if (!getTxId().getText().isEmpty()) {
			articulosAceptadosSinVersionDefinitiva.clear();
			rellenarComboBox();
			try {
			    int idAutor = Integer.parseInt(getTxId().getText());
			    articulosDelEditor = controller.getArticulosPropios(idAutor);
			    getListArticulos().setModel(addModel());
			} catch (Exception e1) {
			    JOptionPane.showMessageDialog(null, "Debe introducir un id con solo números", "Error de Id",
				    JOptionPane.ERROR_MESSAGE);
			    System.err.print(e1.getMessage());
			}
		    }
		}
	    });
	    btConfirmar.setForeground(new Color(255, 255, 255));
	    btConfirmar.setBackground(new Color(47, 79, 79));
	    btConfirmar.setBounds(20, 110, 148, 23);
	}
	return btConfirmar;
    }

    private ListModel<ArticuloEntity> addModel() {
	DefaultListModel<ArticuloEntity> model = new DefaultListModel<>();
	for (ArticuloEntity articulo : articulosDelEditor) {
	    model.addElement(articulo);
	}

	return model;
    }

    private JButton getBtMirarArticulos() {
	if (btMirarArticulos == null) {
	    btMirarArticulos = new JButton("Articulos aceptados sin versión definitiva");
	    btMirarArticulos.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    articulosAceptadosSinVersionDefinitiva.clear();
		    articulosDelEditor.clear();
		    rellenarComboBox();
		    if (!getTxId().getText().isEmpty()) {
			try {
			    int idAutor = Integer.parseInt(getTxId().getText());
			    articulosAceptadosSinVersionDefinitiva = controller
				    .getArticulosAceptadosSinVersionDefinitiva(idAutor);
			    rellenarComboBox();
			    if (articulosAceptadosSinVersionDefinitiva.size() == 0)
				getBtnEnviarArticulo().setEnabled(false);
			    else
				getBtnEnviarArticulo().setEnabled(true);
			} catch (Exception e1) {
			    JOptionPane.showMessageDialog(null, "Debe introducir un id con solo números", "Error de Id",
				    JOptionPane.ERROR_MESSAGE);
			}
		    }
		}
	    });
	    btMirarArticulos.setBackground(new Color(224, 255, 255));
	    btMirarArticulos.setBounds(413, 110, 272, 23);
	}
	return btMirarArticulos;
    }

    private void rellenarComboBox() {
	ArticuloEntity[] vector = new ArticuloEntity[0];
	if (this.articulosAceptadosSinVersionDefinitiva.size() > 0) {
	    vector = new ArticuloEntity[this.articulosAceptadosSinVersionDefinitiva.size()];
	    for (int i = 0; i < vector.length; i++) {
		vector[i] = this.articulosAceptadosSinVersionDefinitiva.get(i);
	    }
	}
	cbArticulosSinPublicar.setModel(new DefaultComboBoxModel<ArticuloEntity>(vector));
    }

    private JLabel getLbSinPublicar() {
	if (lbSinPublicar == null) {
	    lbSinPublicar = new JLabel("Articulos Aceptados que aún no tienen su versión definitiva enviada:");
	    lbSinPublicar.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lbSinPublicar.setBounds(623, 141, 444, 16);
	}
	return lbSinPublicar;
    }

    private JComboBox<ArticuloEntity> getCbArticulosSinPublicar() {
	if (cbArticulosSinPublicar == null) {
	    cbArticulosSinPublicar = new JComboBox<ArticuloEntity>();
	    cbArticulosSinPublicar.setBounds(605, 168, 462, 22);
	}
	return cbArticulosSinPublicar;
    }

    private JCheckBox getChCopy() {
	if (chCopy == null) {
	    chCopy = new JCheckBox("Confirme su firma de CopyRight");
	    chCopy.setBounds(610, 396, 208, 23);
	}
	return chCopy;
    }

    private JButton getBtnEnviarArticulo() {
	if (btnEnviarArticulo == null) {
	    btnEnviarArticulo = new JButton("Enviar Versión Definitiva");
	    btnEnviarArticulo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    if (!getChCopy().isSelected())
			JOptionPane.showMessageDialog(null, "Tiene que confirmar la firma de CopyRight");
		    else {
			ArticuloEntity articulo = (ArticuloEntity) getCbArticulosSinPublicar().getSelectedItem();
			if (articulo != null)
			    controller.getEnviarVersionDefinitiva(articulo.getIdArticulo());
		    }
		}
	    });
	    btnEnviarArticulo.setForeground(new Color(255, 255, 255));
	    btnEnviarArticulo.setBackground(new Color(0, 0, 128));
	    btnEnviarArticulo.setBounds(842, 396, 225, 23);
	    btnEnviarArticulo.setEnabled(false);
	}
	return btnEnviarArticulo;
    }

    private JButton getBtVisualizar() {
	if (btVisualizar == null) {
	    btVisualizar = new JButton("Visualizar Artículo");
	    btVisualizar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    mostrarVentanaVisualizacion();
		}
	    });
	    btVisualizar.setForeground(Color.WHITE);
	    btVisualizar.setBackground(new Color(173, 216, 230));
	    btVisualizar.setBounds(727, 259, 225, 23);
	}
	return btVisualizar;
    }

    private void mostrarVentanaVisualizacion() {
	if (getCbArticulosSinPublicar().getSelectedItem() != null) {
	    VisualizarArticuloView vA = new VisualizarArticuloView(
		    (ArticuloEntity) getCbArticulosSinPublicar().getSelectedItem());
	    vA.setVisible(true);
	}
    }

    private JButton getBtnModificarBorrador() {
	if (btnModificarBorrador == null) {
	    btnModificarBorrador = new JButton("Modificar borrador");
	    btnModificarBorrador.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    modificarBorrador();
		}
	    });
	    btnModificarBorrador.setEnabled(false);
	    btnModificarBorrador.setBounds(10, 498, 121, 22);
	}
	return btnModificarBorrador;
    }

    private boolean isBorrador() {
	ArticuloEntity articulo = getListArticulos().getSelectedValue();
	if (articulo.getEstado().equals("borrador")) {
	    return true;
	}
	return false;
    }

    private JList<ArticuloEntity> getListArticulos() {
	if (listArticulos == null) {
	    listArticulos = new JList<ArticuloEntity>();
	    listArticulos.setModel(addModel());
	    listArticulos.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
		    if (isBorrador()) {
			getBtnModificarBorrador().setEnabled(true);
		    } else {
			getBtnModificarBorrador().setEnabled(false);
		    }
		}
	    });

	}
	return listArticulos;
    }

    private void modificarBorrador() {
	AutorCreacionView ver = new AutorCreacionView(controller, id_autor, getListArticulos().getSelectedValue());
	ver.setVisible(true);
	ver.setModal(true);
    }
}