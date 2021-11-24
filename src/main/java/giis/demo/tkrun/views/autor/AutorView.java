package giis.demo.tkrun.views.autor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
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
import giis.demo.tkrun.views.articulo.ArticuloCambiosView;
import giis.demo.util.DtoMapper;

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
    private List<ArticuloEntity> articulosDelAutor = new ArrayList<ArticuloEntity>();
    private AutorController controller;
    private JButton btConfirmar;
    private JCheckBox chCopy;
    private JButton btnEnviarArticulo;
    private JButton btVisualizar;
    private int id_autor;
    private JButton btnModificarBorrador;
    private JList<ArticuloEntity> listArticulos;
    private JButton btnCambiosSugeridos;

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
     * 
     * @wbp.parser.constructor
     */
    public AutorView(AutorController controller, int id_autor) {
	this.controller = controller;
	this.id_autor = id_autor;
	inicialize();
    }

    public AutorView(int id_autor) {
	this.controller = new AutorController(false);
	this.id_autor = id_autor;
	inicialize();
    }

    private ListModel<ArticuloEntity> addModel() {
	DefaultListModel<ArticuloEntity> model = new DefaultListModel<>();
	for (ArticuloEntity articulo : articulosDelAutor) {
	    model.addElement(articulo);
	}

	return model;
    }

    private JButton getBtConfirmar() {
	if (btConfirmar == null) {
	    btConfirmar = new JButton("Actualizar Artículos");
	    btConfirmar.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    if (!getTxId().getText().isEmpty()) {
			try {
			    id_autor = Integer.parseInt(getTxId().getText());
			    articulosDelAutor = controller.getArticulosPropios(id_autor);
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

    private JButton getBtnCambiosSugeridos() {
	if (btnCambiosSugeridos == null) {
	    btnCambiosSugeridos = new JButton("Cambios sugeridos");
	    btnCambiosSugeridos.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    modificarArticulo();
		}
	    });
	    btnCambiosSugeridos.setEnabled(false);
	    btnCambiosSugeridos.setBounds(223, 498, 178, 23);
	}
	return btnCambiosSugeridos;
    }

    private JButton getBtnEnviarArticulo() {
	if (btnEnviarArticulo == null) {
	    btnEnviarArticulo = new JButton("Enviar Versión Definitiva");
	    btnEnviarArticulo.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    if (getChCopy().isSelected()) {
			if (listArticulos.getSelectedValuesList().size() > 1)
				JOptionPane.showMessageDialog(null, "Debe seleccionar solo un artículo",
						"Error al seleccionar artículos", JOptionPane.ERROR_MESSAGE);
			else if (listArticulos.getSelectedValuesList().size() == 0)
				JOptionPane.showMessageDialog(null, "Seleccione un artículo para poder revisarlo",
						"Error al seleccionar artículos", JOptionPane.ERROR_MESSAGE);
			else {
			    ArticuloEntity art = listArticulos.getSelectedValue();
				if(art.getEstado().equals(ArticuloEntity.ACEPTADO) && !art.isVersionDefinitiva()) {
				    art.setFirma(true);
				    art.setVersionDefinitiva(true);
				    controller.editarArticulo(DtoMapper.toArticuloDto(art));
				    //controller.getEnviarVersionDefinitiva(art.getIdArticulo());
				}
				else {
				    JOptionPane.showMessageDialog(null, "El artículo ya tiene versión definitiva o no ha sido aceptado todavía",
						"Error al enviar versión definitiva", JOptionPane.ERROR_MESSAGE);
				}
			}
		    }
		    else {
			    JOptionPane.showMessageDialog(null, "Debe confirmar la firma de copyright",
					"Error al enviar versión definitiva", JOptionPane.ERROR_MESSAGE);
			}
		}
	    });
	    btnEnviarArticulo.setForeground(new Color(255, 255, 255));
	    btnEnviarArticulo.setBackground(new Color(0, 0, 128));
	    btnEnviarArticulo.setBounds(824, 444, 225, 23);
	}
	return btnEnviarArticulo;
    }

    private JButton getBtnModificarBorrador() {
	if (btnModificarBorrador == null) {
	    btnModificarBorrador = new JButton("Modificar borrador");
	    btnModificarBorrador.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    modificarBorrador();
		}
	    });
	    btnModificarBorrador.setEnabled(false);
	    btnModificarBorrador.setBounds(10, 498, 178, 23);
	}
	return btnModificarBorrador;
    }

    private JButton getBtVisualizar() {
	if (btVisualizar == null) {
	    btVisualizar = new JButton("Editar para versión definitiva de un artículo");
	    btVisualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    btVisualizar.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    mostrarVentanaVisualizacion();
		}
	    });
	    btVisualizar.setForeground(Color.WHITE);
	    btVisualizar.setBackground(new Color(173, 216, 230));
	    btVisualizar.setBounds(614, 399, 309, 23);
	}
	return btVisualizar;
    }

    private JCheckBox getChCopy() {
	if (chCopy == null) {
	    chCopy = new JCheckBox("Confirme su firma de CopyRight");
	    chCopy.setBounds(610, 444, 208, 23);
	}
	return chCopy;
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

    private JList<ArticuloEntity> getListArticulos() {
	if (listArticulos == null) {
	    listArticulos = new JList<ArticuloEntity>();
	    listArticulos.setModel(addModel());
	    listArticulos.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
		    if (isBorrador()) {
			getBtnModificarBorrador().setEnabled(true);
		    } else if (!isBorrador()) {
			getBtnModificarBorrador().setEnabled(false);
		    }
		    if (isRevisado()) {
			getBtnCambiosSugeridos().setEnabled(true);
		    } else if (!isRevisado()) {
			getBtnCambiosSugeridos().setEnabled(false);
		    }
		}
	    });
	}
	return listArticulos;

    }

    private JScrollPane getScrollPane() {
	if (scrollPane == null) {
	    scrollPane = new JScrollPane();
	    scrollPane.setBounds(10, 168, 594, 319);
	    scrollPane.setViewportView(getListArticulos());
	}
	return scrollPane;
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

    private void inicialize() {
	setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
	setBounds(100, 100, 1174, 586);
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
	contentPane.add(getChCopy());
	contentPane.add(getBtnEnviarArticulo());
	contentPane.add(getBtVisualizar());
	contentPane.add(getBtnModificarBorrador());
	contentPane.add(getBtnCambiosSugeridos());
	setVisible(true);
	setResizable(false);
	actualizarArticulos();
    }

    private void actualizarArticulos() {
	articulosDelAutor = controller.getArticulosPropios(id_autor);
	getListArticulos().setModel(addModel());
    }

    private boolean isBorrador() {
	ArticuloEntity articulo = getListArticulos().getSelectedValue();
	if (articulo.getEstado().equals("borrador")) {
	    return true;
	}
	return false;
    }

    private boolean isRevisado() {
	ArticuloEntity articulo = getListArticulos().getSelectedValue();

	if ((articulo.getVecesRevisado() == 1) && articulo.getEstado().equals("aceptado")
		|| (articulo.getEstado().equals("aceptado con cambios menores") && articulo.isPendienteDeCambios())
		|| articulo.getEstado().equals("aceptado con cambios mayores") && articulo.isPendienteDeCambios()) {
	    return true;
	}
	return false;
    }

    private void modificarArticulo() {
	ArticuloEntity articulo = getListArticulos().getSelectedValue();

	ArticuloCambiosView acw = new ArticuloCambiosView(articulo, id_autor);
	acw.setVisible(true);
	actualizarArticulos();
    }

    private void modificarBorrador() {
	AutorCreacionView ver = new AutorCreacionView(controller, id_autor, getListArticulos().getSelectedValue());
	ver.setVisible(true);
	actualizarArticulos();
    }

    private void mostrarVentanaVisualizacion() {
	if (listArticulos.getSelectedValuesList().size() > 1)
		JOptionPane.showMessageDialog(null, "Debe seleccionar solo un artículo",
				"Error al seleccionar artículos", JOptionPane.ERROR_MESSAGE);
	else if (listArticulos.getSelectedValuesList().size() == 0)
		JOptionPane.showMessageDialog(null, "Seleccione un artículo para poder revisarlo",
				"Error al seleccionar artículos", JOptionPane.ERROR_MESSAGE);
	else {
		ArticuloEntity art = listArticulos.getSelectedValue();
		if(art.getEstado().equals(ArticuloEntity.ACEPTADO) && !art.isVersionDefinitiva()) {
		    AutorEditarArticuloView vA = new AutorEditarArticuloView(art);
			vA.setVisible(true);
			vA.setModal(true);
		}
		else {
		    JOptionPane.showMessageDialog(null, "El artículo ya tiene versión definitiva o no ha sido aceptado todavía",
				"Error al enviar versión definitiva", JOptionPane.ERROR_MESSAGE);
		}
		actualizarArticulos();
	}
    }
}
