package giis.demo.tkrun.views.autor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.autor.AutorController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.models.dtos.ArticuloDto;

public class AutorEditarArticuloView extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lbTitulo;
    private JTextField txtFTitulo;
    private JLabel lbAutor;
    private JTextField txtFAutor;
    private JLabel lbOtrosAutores;
    private JTextField txtFOtrosAutores;
    private JLabel lbResumen;
    private JTextField txtFResumen;
    private JLabel lbPalabrasClave;
    private JTextField txtFPalabrasClave;
    private JLabel lbFicheroFuente;
    private JTextField txtFFicheroFuente;
    private JLabel lbCartaPresentación;
    private JTextField txtFCartaPresentacion;
    private JLabel lbCVAutor;
    private JTextField txtFCVAutor;
    private JButton btnEnviar;
    private AutorController autorController;
    private JLabel lbCabeza;
    private ArticuloEntity articulo;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AutorCreacionView frame = new AutorCreacionView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

    public AutorEditarArticuloView(ArticuloEntity articulo) {
	this.autorController = new AutorController(false);
	this.articulo = articulo;
	initialize();
    }

    /**
     * Create the frame.
     */
    public void initialize() {
	setResizable(false);
	setTitle("Autor: " + autorController.findAutorByArticulo(articulo.getIdArticulo()).getNombre()
		+ ". Editar un artículo");
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 696, 451);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getLbTitulo());
	contentPane.add(getTxtFTitulo());
	contentPane.add(getLbAutor());
	contentPane.add(getTxtFAutor());
	contentPane.add(getLbOtrosAutores());
	contentPane.add(getTxtFOtrosAutores());
	contentPane.add(getLbResumen());
	contentPane.add(getTxtFResumen());
	contentPane.add(getLbPalabrasClave());
	contentPane.add(getTxtFPalabrasClave());
	contentPane.add(getLbFicheroFuente());
	contentPane.add(getTxtFFicheroFuente());
	contentPane.add(getLbCartaPresentación());
	contentPane.add(getTxtFCartaPresentacion());
	contentPane.add(getLbCVAutor());
	contentPane.add(getTxtFCVAutor());
	contentPane.add(getBtnEnviar());
	contentPane.add(getLbCabeza());
	cargarDatosArticulo();
    }

    private void cargarDatosArticulo() {
	if (articulo != null) {
	    getTxtFTitulo().setText(articulo.getTitulo());
	    getTxtFAutor().setText(articulo.getPrimerAutor());
	    getTxtFOtrosAutores().setText(articulo.getOtrosAutores());
	    getTxtFCartaPresentacion().setText(articulo.getCartaPresentacion());
	    getTxtFCVAutor().setText(articulo.getCV());
	    getTxtFFicheroFuente().setText(articulo.getFicheroFuente());
	    getTxtFPalabrasClave().setText(articulo.getPalabrasClave());
	    getTxtFResumen().setText(articulo.getResumen());
	}
    }

    private JLabel getLbTitulo() {
	if (lbTitulo == null) {
	    lbTitulo = new JLabel("Título: ");
	    lbTitulo.setLabelFor(getTxtFTitulo());
	    lbTitulo.setDisplayedMnemonic('T');
	    lbTitulo.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lbTitulo.setBounds(10, 57, 150, 20);
	}
	return lbTitulo;
    }

    private JTextField getTxtFTitulo() {
	if (txtFTitulo == null) {
	    txtFTitulo = new JTextField();
	    txtFTitulo.setBounds(170, 58, 348, 20);
	    txtFTitulo.setColumns(10);
	}
	return txtFTitulo;
    }

    private JLabel getLbAutor() {
	if (lbAutor == null) {
	    lbAutor = new JLabel("Autor: ");
	    lbAutor.setDisplayedMnemonic('L');
	    lbAutor.setLabelFor(getTxtFAutor());
	    lbAutor.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lbAutor.setBounds(10, 88, 150, 20);
	}
	return lbAutor;
    }

    private JTextField getTxtFAutor() {
	if (txtFAutor == null) {
	    txtFAutor = new JTextField();
	    txtFAutor.setEditable(false);
	    txtFAutor.setColumns(10);
	    txtFAutor.setBounds(170, 89, 348, 20);
	}
	return txtFAutor;
    }

    private JLabel getLbOtrosAutores() {
	if (lbOtrosAutores == null) {
	    lbOtrosAutores = new JLabel("Otros autores: ");
	    lbOtrosAutores.setLabelFor(getTxtFOtrosAutores());
	    lbOtrosAutores.setDisplayedMnemonic('O');
	    lbOtrosAutores.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lbOtrosAutores.setBounds(10, 119, 150, 20);
	}
	return lbOtrosAutores;
    }

    private JTextField getTxtFOtrosAutores() {
	if (txtFOtrosAutores == null) {
	    txtFOtrosAutores = new JTextField();
	    txtFOtrosAutores.setColumns(10);
	    txtFOtrosAutores.setBounds(170, 120, 348, 20);
	}
	return txtFOtrosAutores;
    }

    private JLabel getLbResumen() {
	if (lbResumen == null) {
	    lbResumen = new JLabel("Resumen: ");
	    lbResumen.setLabelFor(getTxtFResumen());
	    lbResumen.setDisplayedMnemonic('R');
	    lbResumen.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lbResumen.setBounds(10, 150, 150, 20);
	}
	return lbResumen;
    }

    private JTextField getTxtFResumen() {
	if (txtFResumen == null) {
	    txtFResumen = new JTextField();
	    txtFResumen.setColumns(10);
	    txtFResumen.setBounds(170, 151, 348, 20);
	}
	return txtFResumen;
    }

    private JLabel getLbPalabrasClave() {
	if (lbPalabrasClave == null) {
	    lbPalabrasClave = new JLabel("Palabras clave:");
	    lbPalabrasClave.setLabelFor(getTxtFPalabrasClave());
	    lbPalabrasClave.setDisplayedMnemonic('P');
	    lbPalabrasClave.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lbPalabrasClave.setBounds(10, 181, 150, 20);
	}
	return lbPalabrasClave;
    }

    private JTextField getTxtFPalabrasClave() {
	if (txtFPalabrasClave == null) {
	    txtFPalabrasClave = new JTextField();
	    txtFPalabrasClave.setColumns(10);
	    txtFPalabrasClave.setBounds(170, 182, 348, 20);
	}
	return txtFPalabrasClave;
    }

    private JLabel getLbFicheroFuente() {
	if (lbFicheroFuente == null) {
	    lbFicheroFuente = new JLabel("Fichero fuente: ");
	    lbFicheroFuente.setLabelFor(getTxtFFicheroFuente());
	    lbFicheroFuente.setDisplayedMnemonic('F');
	    lbFicheroFuente.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lbFicheroFuente.setBounds(10, 212, 150, 20);
	}
	return lbFicheroFuente;
    }

    private JTextField getTxtFFicheroFuente() {
	if (txtFFicheroFuente == null) {
	    txtFFicheroFuente = new JTextField();
	    txtFFicheroFuente.setColumns(10);
	    txtFFicheroFuente.setBounds(170, 213, 348, 20);
	}
	return txtFFicheroFuente;
    }

    private JLabel getLbCartaPresentación() {
	if (lbCartaPresentación == null) {
	    lbCartaPresentación = new JLabel("Carta de presentación: ");
	    lbCartaPresentación.setLabelFor(getTxtFCartaPresentacion());
	    lbCartaPresentación.setDisplayedMnemonic('C');
	    lbCartaPresentación.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lbCartaPresentación.setBounds(10, 243, 150, 20);
	}
	return lbCartaPresentación;
    }

    private JTextField getTxtFCartaPresentacion() {
	if (txtFCartaPresentacion == null) {
	    txtFCartaPresentacion = new JTextField();
	    txtFCartaPresentacion.setColumns(10);
	    txtFCartaPresentacion.setBounds(170, 244, 348, 20);
	}
	return txtFCartaPresentacion;
    }

    private JLabel getLbCVAutor() {
	if (lbCVAutor == null) {
	    lbCVAutor = new JLabel("CV del autor: ");
	    lbCVAutor.setDisplayedMnemonic('V');
	    lbCVAutor.setLabelFor(getTxtFCVAutor());
	    lbCVAutor.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lbCVAutor.setBounds(10, 274, 150, 20);
	}
	return lbCVAutor;
    }

    private JTextField getTxtFCVAutor() {
	if (txtFCVAutor == null) {
	    txtFCVAutor = new JTextField();
	    txtFCVAutor.setColumns(10);
	    txtFCVAutor.setBounds(170, 275, 348, 20);
	}
	return txtFCVAutor;
    }

    private JButton getBtnEnviar() {
	if (btnEnviar == null) {
	    btnEnviar = new JButton("Confirmar Cambios");
	    btnEnviar.setBackground(new Color(233, 150, 122));
	    btnEnviar.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    btnEnviar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    if (checkFields()) {
			enviarARevista();
			dispose();
		    } else {
			JOptionPane.showMessageDialog(null, "Todos los campos deben estar completos");
		    }
		}
	    });
	    btnEnviar.setMnemonic('E');
	    btnEnviar.setBounds(264, 332, 162, 23);
	}
	return btnEnviar;
    }

    private boolean checkFields() {
	String titulo = getTxtFTitulo().getText();
	String autor = getTxtFAutor().getText();
	String resumen = getTxtFResumen().getText();
	String palabrasClave = getTxtFPalabrasClave().getText();
	String ficheroFuente = getTxtFFicheroFuente().getText();
	String cartaPresentacion = getTxtFCartaPresentacion().getText();
	String cVAutor = getTxtFCVAutor().getText();

	if (titulo.replaceAll("\\s", "").length() == 0) {
	    return false;
	}
	if (autor.replaceAll("\\s", "").length() == 0) {
	    return false;
	}
	if (resumen.replaceAll("\\s", "").length() == 0) {
	    return false;
	}
	if (palabrasClave.replaceAll("\\s", "").length() == 0) {
	    return false;
	}
	if (ficheroFuente.replaceAll("\\s", "").length() == 0) {
	    return false;
	}
	if (cartaPresentacion.replaceAll("\\s", "").length() == 0) {
	    return false;
	}
	if (cVAutor.replaceAll("\\s", "").length() == 0) {
	    return false;
	}

	return true;
    }

    private void enviarARevista() {

	ArticuloDto articuloDto = new ArticuloDto();
	articuloDto.setIdArticulo(articulo.getIdArticulo());
	articuloDto.setTitulo(getTxtFTitulo().getText());
	articuloDto.setPrimerAutor(getTxtFAutor().getText());
	articuloDto.setOtrosAutores(getTxtFOtrosAutores().getText());
	articuloDto.setResumen(getTxtFResumen().getText());
	articuloDto.setPalabrasClave(getTxtFPalabrasClave().getText());
	articuloDto.setFicheroFuente(getTxtFFicheroFuente().getText());
	articuloDto.setCartaPresentacion(getTxtFCartaPresentacion().getText());
	articuloDto.setCV(getTxtFCVAutor().getText());

	articuloDto.setCartaDecision(articulo.getCartaDecision());
	articuloDto.setEstado(articulo.getEstado());
	articuloDto.setDOI(articulo.getDOI());
	articuloDto.setFirma(articulo.isFirma());
	articuloDto.setVecesRevisado(articulo.getVecesRevisado());
	articuloDto.setVolumen(articulo.getVolumen());

	autorController.editarArticulo(articuloDto);
    }

    private JLabel getLbCabeza() {
	if (lbCabeza == null) {
	    lbCabeza = new JLabel("Autor: Editar Articulo:");
	    lbCabeza.setFont(new Font("Tahoma", Font.ITALIC, 21));
	    lbCabeza.setBounds(234, 11, 230, 36);
	}
	return lbCabeza;
    }
}
