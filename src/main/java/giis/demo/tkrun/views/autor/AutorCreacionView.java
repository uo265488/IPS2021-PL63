package giis.demo.tkrun.views.autor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.autor.AutorController;
import giis.demo.tkrun.controllers.revisor.RevisorController;
import giis.demo.tkrun.models.dtos.ArticuloDto;
import giis.demo.tkrun.models.dtos.RevisorDto;

public class AutorCreacionView extends JDialog {

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
    private JCheckBox chckBoxFirmaPlagio;
    private JButton btnBorrador;
    private JButton btnEnviar;
    private AutorController autorController;
    private int id_autor;
    private JLabel lbContstraints;
    private JLabel lbSugerirRevisores;
    private JPanel panelSugeridos;
    private JTextField txtFSugerido1;
    private JTextField txtFSugerido2;
    private JTextField txtFSugerido3;
    private JLabel lbConstraintsSugeridos;
    private RevisorController revisorController;

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

    public AutorCreacionView(AutorController autorController, int id_autor) {
	this.autorController = autorController;
	this.id_autor = id_autor;
	revisorController = new RevisorController();
	initialize();
    }

    /**
     * Create the frame.
     */
    public void initialize() {
	setResizable(false);
	setTitle("Autor. Crear un artículo");
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 604, 638);
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
	contentPane.add(getChckBoxFirmaPlagio());
	contentPane.add(getBtnBorrador());
	contentPane.add(getBtnEnviar());
	contentPane.add(getLbContstraints());
	contentPane.add(getLbSugerirRevisores());
	contentPane.add(getPanelSugeridos());
	contentPane.add(getLbConstraintsSugeridos());
    }

    private JLabel getLbTitulo() {
	if (lbTitulo == null) {
	    lbTitulo = new JLabel("Título: ");
	    lbTitulo.setLabelFor(getTxtFTitulo());
	    lbTitulo.setDisplayedMnemonic('T');
	    lbTitulo.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lbTitulo.setBounds(23, 25, 150, 20);
	}
	return lbTitulo;
    }

    private JTextField getTxtFTitulo() {
	if (txtFTitulo == null) {
	    txtFTitulo = new JTextField();
	    txtFTitulo.setBounds(183, 25, 321, 20);
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
	    lbAutor.setBounds(23, 56, 150, 20);
	}
	return lbAutor;
    }

    private JTextField getTxtFAutor() {
	if (txtFAutor == null) {
	    txtFAutor = new JTextField();
	    txtFAutor.setEditable(false);
	    txtFAutor.setText(autorController.findById(id_autor).getNombre());
	    txtFAutor.setColumns(10);
	    txtFAutor.setBounds(183, 56, 321, 20);
	}
	return txtFAutor;
    }

    private JLabel getLbOtrosAutores() {
	if (lbOtrosAutores == null) {
	    lbOtrosAutores = new JLabel("Otros autores: ");
	    lbOtrosAutores.setLabelFor(getTxtFOtrosAutores());
	    lbOtrosAutores.setDisplayedMnemonic('O');
	    lbOtrosAutores.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lbOtrosAutores.setBounds(23, 87, 150, 20);
	}
	return lbOtrosAutores;
    }

    private JTextField getTxtFOtrosAutores() {
	if (txtFOtrosAutores == null) {
	    txtFOtrosAutores = new JTextField();
	    txtFOtrosAutores.setForeground(Color.BLACK);
	    txtFOtrosAutores.setColumns(10);
	    txtFOtrosAutores.setBounds(183, 87, 321, 20);
	}
	return txtFOtrosAutores;
    }

    private JLabel getLbResumen() {
	if (lbResumen == null) {
	    lbResumen = new JLabel("Resumen: ");
	    lbResumen.setLabelFor(getTxtFResumen());
	    lbResumen.setDisplayedMnemonic('R');
	    lbResumen.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lbResumen.setBounds(23, 137, 150, 20);
	}
	return lbResumen;
    }

    private JTextField getTxtFResumen() {
	if (txtFResumen == null) {
	    txtFResumen = new JTextField();
	    txtFResumen.setColumns(10);
	    txtFResumen.setBounds(183, 138, 321, 20);
	}
	return txtFResumen;
    }

    private JLabel getLbPalabrasClave() {
	if (lbPalabrasClave == null) {
	    lbPalabrasClave = new JLabel("Palabras clave:");
	    lbPalabrasClave.setLabelFor(getTxtFPalabrasClave());
	    lbPalabrasClave.setDisplayedMnemonic('P');
	    lbPalabrasClave.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lbPalabrasClave.setBounds(23, 168, 150, 20);
	}
	return lbPalabrasClave;
    }

    private JTextField getTxtFPalabrasClave() {
	if (txtFPalabrasClave == null) {
	    txtFPalabrasClave = new JTextField();
	    txtFPalabrasClave.setColumns(10);
	    txtFPalabrasClave.setBounds(183, 168, 321, 20);
	}
	return txtFPalabrasClave;
    }

    private JLabel getLbFicheroFuente() {
	if (lbFicheroFuente == null) {
	    lbFicheroFuente = new JLabel("Fichero fuente: ");
	    lbFicheroFuente.setLabelFor(getTxtFFicheroFuente());
	    lbFicheroFuente.setDisplayedMnemonic('F');
	    lbFicheroFuente.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lbFicheroFuente.setBounds(23, 199, 150, 20);
	}
	return lbFicheroFuente;
    }

    private JTextField getTxtFFicheroFuente() {
	if (txtFFicheroFuente == null) {
	    txtFFicheroFuente = new JTextField();
	    txtFFicheroFuente.setColumns(10);
	    txtFFicheroFuente.setBounds(183, 200, 321, 20);
	}
	return txtFFicheroFuente;
    }

    private JLabel getLbCartaPresentación() {
	if (lbCartaPresentación == null) {
	    lbCartaPresentación = new JLabel("Carta de presentación: ");
	    lbCartaPresentación.setLabelFor(getTxtFCartaPresentacion());
	    lbCartaPresentación.setDisplayedMnemonic('C');
	    lbCartaPresentación.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lbCartaPresentación.setBounds(23, 231, 150, 20);
	}
	return lbCartaPresentación;
    }

    private JTextField getTxtFCartaPresentacion() {
	if (txtFCartaPresentacion == null) {
	    txtFCartaPresentacion = new JTextField();
	    txtFCartaPresentacion.setColumns(10);
	    txtFCartaPresentacion.setBounds(183, 231, 321, 20);
	}
	return txtFCartaPresentacion;
    }

    private JLabel getLbCVAutor() {
	if (lbCVAutor == null) {
	    lbCVAutor = new JLabel("CV del autor: ");
	    lbCVAutor.setDisplayedMnemonic('V');
	    lbCVAutor.setLabelFor(getTxtFCVAutor());
	    lbCVAutor.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lbCVAutor.setBounds(23, 262, 150, 20);
	}
	return lbCVAutor;
    }

    private JTextField getTxtFCVAutor() {
	if (txtFCVAutor == null) {
	    txtFCVAutor = new JTextField();
	    txtFCVAutor.setColumns(10);
	    txtFCVAutor.setBounds(183, 262, 321, 20);
	}
	return txtFCVAutor;
    }

    private JCheckBox getChckBoxFirmaPlagio() {
	if (chckBoxFirmaPlagio == null) {
	    chckBoxFirmaPlagio = new JCheckBox(
		    "Comfirmo que el artículo es propio, original y que no se encuentra en ningún proceso de aceptación en ninguna otra revista. \r\n");
	    chckBoxFirmaPlagio.setMnemonic('O');
	    chckBoxFirmaPlagio.setBounds(23, 524, 421, 23);
	}
	return chckBoxFirmaPlagio;
    }

    private JButton getBtnBorrador() {
	if (btnBorrador == null) {
	    btnBorrador = new JButton("Guardar BORRADOR");
	    btnBorrador.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    crearBorrador();
		    dispose();
		}
	    });
	    btnBorrador.setMnemonic('B');
	    btnBorrador.setBounds(264, 565, 131, 23);
	}
	return btnBorrador;

    }

    private JButton getBtnEnviar() {
	if (btnEnviar == null) {
	    btnEnviar = new JButton("ENVIAR a revista");
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
	    btnEnviar.setBounds(405, 565, 131, 23);
	}
	return btnEnviar;
    }

    private void crearBorrador() {

	ArticuloDto articuloDto = new ArticuloDto();
	articuloDto.setIdArticulo(new Random().nextInt());
	articuloDto.setTitulo(getTxtFTitulo().getText());
	articuloDto.setPrimerAutor(getTxtFAutor().getText());
	articuloDto.setOtrosAutores(getTxtFOtrosAutores().getText());
	articuloDto.setResumen(getTxtFResumen().getText());
	articuloDto.setPalabrasClave(getTxtFPalabrasClave().getText());
	articuloDto.setFicheroFuente(getTxtFFicheroFuente().getText());
	articuloDto.setCartaPresentacion(getTxtFCartaPresentacion().getText());
	articuloDto.setCV(getTxtFCVAutor().getText());
	articuloDto.setFirma(getChckBoxFirmaPlagio().isSelected());

	revisoresSugeridos(articuloDto.getIdArticulo(), getTextFSugerido1().getText(), getTextFSugerido2().getText(),
		getTextFSugerido3().getText());

	autorController.crearBorrador(articuloDto);

    }

    private boolean checkFields() {
	String titulo = getTxtFTitulo().getText();
	String autor = getTxtFAutor().getText();
	String otrosAutores = getTxtFOtrosAutores().getText();
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
	if (otrosAutores.replaceAll("\\s", "").length() == 0) {
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
	if (!getChckBoxFirmaPlagio().isSelected()) {
	    return false;
	}
	return true;
    }

    private void enviarARevista() {

	ArticuloDto articuloDto = new ArticuloDto();
	articuloDto.setIdArticulo(new Random().nextInt());
	articuloDto.setTitulo(getTxtFTitulo().getText());
	articuloDto.setPrimerAutor(getTxtFAutor().getText());
	articuloDto.setOtrosAutores(getTxtFOtrosAutores().getText());
	articuloDto.setResumen(getTxtFResumen().getText());
	articuloDto.setPalabrasClave(getTxtFPalabrasClave().getText());
	articuloDto.setFicheroFuente(getTxtFFicheroFuente().getText());
	articuloDto.setCartaPresentacion(getTxtFCartaPresentacion().getText());
	articuloDto.setCV(getTxtFCVAutor().getText());
	articuloDto.setFirma(getChckBoxFirmaPlagio().isSelected());
	articuloDto.setVecesRevisado(0);
	articuloDto.setVersionDefinitiva(false);
	articuloDto.setDOI("");
	articuloDto.setFecha("");
	articuloDto.setVolumen(0);

	autorController.crearArticulo(articuloDto);
    }

    private JLabel getLbContstraints() {
	if (lbContstraints == null) {
	    lbContstraints = new JLabel("* Nombre y dni separados por -. Cada autor separado por ;");
	    lbContstraints.setFont(new Font("Tahoma", Font.PLAIN, 10));
	    lbContstraints.setBounds(183, 112, 321, 14);
	}
	return lbContstraints;
    }

    private JLabel getLbSugerirRevisores() {
	if (lbSugerirRevisores == null) {
	    lbSugerirRevisores = new JLabel("Sugerir revisores: (máximo 3)");
	    lbSugerirRevisores.setFont(new Font("Tahoma", Font.BOLD, 13));
	    lbSugerirRevisores.setHorizontalAlignment(SwingConstants.CENTER);
	    lbSugerirRevisores.setBounds(175, 321, 238, 33);
	}
	return lbSugerirRevisores;
    }

    private JPanel getPanelSugeridos() {
	if (panelSugeridos == null) {
	    panelSugeridos = new JPanel();
	    panelSugeridos.setBounds(23, 390, 555, 110);
	    panelSugeridos.setLayout(null);
	    panelSugeridos.add(getTextFSugerido1());
	    panelSugeridos.add(getTextFSugerido2());
	    panelSugeridos.add(getTextFSugerido3());
	}
	return panelSugeridos;
    }

    private JTextField getTextFSugerido1() {
	if (txtFSugerido1 == null) {
	    txtFSugerido1 = new JTextField();
	    txtFSugerido1.setBounds(0, 12, 314, 20);
	    txtFSugerido1.setColumns(10);
	}
	return txtFSugerido1;
    }

    private JTextField getTextFSugerido2() {
	if (txtFSugerido2 == null) {
	    txtFSugerido2 = new JTextField();
	    txtFSugerido2.setColumns(10);
	    txtFSugerido2.setBounds(0, 44, 314, 20);
	}
	return txtFSugerido2;
    }

    private JTextField getTextFSugerido3() {
	if (txtFSugerido3 == null) {
	    txtFSugerido3 = new JTextField();
	    txtFSugerido3.setColumns(10);
	    txtFSugerido3.setBounds(0, 76, 314, 20);
	}
	return txtFSugerido3;
    }

    private JLabel getLbConstraintsSugeridos() {
	if (lbConstraintsSugeridos == null) {
	    lbConstraintsSugeridos = new JLabel("*Formato:  Nombre - Correo - Especialidad");
	    lbConstraintsSugeridos.setFont(new Font("Tahoma", Font.PLAIN, 10));
	    lbConstraintsSugeridos.setBounds(23, 365, 315, 14);
	}
	return lbConstraintsSugeridos;
    }

    private void revisoresSugeridos(int id_articulo, String revisor1, String revisor2, String revisor3) {
	if (!revisor1.isEmpty()) {
	    String[] rev1 = revisor1.split("-"); // *Formato: Nombre - Correo - Especialidad
	    String nombre = rev1[0];
	    String correo = rev1[1];
	    String especialidad = rev1[2];
	    if (revisorController.findRevisor(nombre, correo, especialidad) == null) {
		RevisorDto revisorDto1 = new RevisorDto();
		revisorDto1.setIdRevisor(new Random().nextInt());
		revisorDto1.setNombre(rev1[0]);
		revisorDto1.setCorreo(rev1[1]);
		revisorDto1.setEspecialidad(rev1[2]);
		revisorDto1.setEstado("Sugerido");

		autorController.sugerirRevisores(id_articulo, revisorDto1);
	    }
	}

	if (!revisor2.isEmpty()) {
	    String[] rev2 = revisor2.split("-"); // *Formato: Nombre - Correo - Especialidad
	    String nombre = rev2[0];
	    String correo = rev2[1];
	    String especialidad = rev2[2];
	    if (revisorController.findRevisor(nombre, correo, especialidad) == null) {
		RevisorDto revisorDto2 = new RevisorDto();
		revisorDto2.setIdRevisor(new Random().nextInt());
		revisorDto2.setNombre(rev2[0]);
		revisorDto2.setCorreo(rev2[1]);
		revisorDto2.setEspecialidad(rev2[2]);
		revisorDto2.setEstado("Sugerido");

		autorController.sugerirRevisores(id_articulo, revisorDto2);
	    }
	}

	if (!revisor3.isEmpty()) {
	    String[] rev3 = revisor1.split("-"); // *Formato: Nombre - Correo - Especialidad
	    String nombre = rev3[0];
	    String correo = rev3[1];
	    String especialidad = rev3[2];
	    if (revisorController.findRevisor(nombre, correo, especialidad) == null) {
		RevisorDto revisorDto3 = new RevisorDto();
		revisorDto3.setIdRevisor(new Random().nextInt());
		revisorDto3.setNombre(rev3[0]);
		revisorDto3.setCorreo(rev3[1]);
		revisorDto3.setEspecialidad(rev3[2]);
		revisorDto3.setEstado("Sugerido");

		autorController.sugerirRevisores(id_articulo, revisorDto3);
	    }
	}
    }
}
