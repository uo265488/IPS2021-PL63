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
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.autor.AutorController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.views.articulo.ArticuloCambiosView;
import giis.demo.util.DtoMapper;
import giis.demo.tkrun.views.articulo.VisualizarArticuloView;

public class AutorView extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lbAutor;
    private JLabel lbId;
    private JScrollPane scrollPane;
    private List<ArticuloEntity> articulosDelAutor = new ArrayList<ArticuloEntity>();
    private AutorController controller;
    private JButton btConfirmar;
    private JCheckBox chCopy;
    private JButton btnEnviarArticulo;
    private JButton btVisualizar;
    private String id_autor;
    private JButton btnModificarBorrador;
    private JList<ArticuloEntity> listArticulos;
    private JButton btnCambiosSugeridos;
    private JButton btnVisualizar;

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
    public AutorView(AutorController controller, String id_autor) {
	this.controller = controller;
	this.id_autor = id_autor;
	inicialize();
    }

    public AutorView(String id_autor) {
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
		    articulosDelAutor = controller.getArticulosPropios(id_autor);
		    getListArticulos().setModel(addModel());
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
	    btnCambiosSugeridos.setBackground(new Color(0, 206, 209));
	    btnCambiosSugeridos.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    modificarArticulo();
		}
	    });
	    btnCambiosSugeridos.setEnabled(false);
	    btnCambiosSugeridos.setBounds(223, 498, 178, 33);
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
	    btnModificarBorrador.setBackground(new Color(46, 139, 87));
	    btnModificarBorrador.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    modificarBorrador();
		}
	    });
	    btnModificarBorrador.setEnabled(false);
	    btnModificarBorrador.setBounds(10, 498, 178, 33);
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
	    lbId = new JLabel(
		    "Identificador del autor:                " + controller.getAutorById(id_autor).toString());
	    lbId.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lbId.setBounds(10, 77, 675, 22);
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
	contentPane.add(getScrollPane());
	contentPane.add(getBtConfirmar());
	contentPane.add(getChCopy());
	contentPane.add(getBtnEnviarArticulo());
	contentPane.add(getBtVisualizar());
	contentPane.add(getBtnModificarBorrador());
	contentPane.add(getBtnCambiosSugeridos());
	contentPane.add(getBtnVisualizar());
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
		|| (articulo.getEstado().equals("aceptado con cambios menores") && (articulo.getVecesRevisado() == 1))
		|| articulo.getEstado().equals("aceptado con cambios mayores") && (articulo.getVecesRevisado() == 1)) {
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

    private JButton getBtnVisualizar() {
	if (btnVisualizar == null) {
	    btnVisualizar = new JButton("Visualizar");
	    btnVisualizar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    ArticuloEntity articulo = getListArticulos().getSelectedValue();
		    if (articulo == null) {
			JOptionPane.showMessageDialog(getContentPane(), "Debes seleccionar un artículo.");
		    } else {
			VisualizarArticuloView vav = new VisualizarArticuloView(articulo);
			vav.setVisible(true);
		    }
		}
	    });
	    btnVisualizar.setEnabled(true);
	    btnVisualizar.setBackground(new Color(70, 130, 180));
	    btnVisualizar.setBounds(426, 498, 178, 33);
	}
	return btnVisualizar;
    }
}
