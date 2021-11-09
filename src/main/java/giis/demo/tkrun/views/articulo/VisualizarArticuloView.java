package giis.demo.tkrun.views.articulo;


import java.awt.Checkbox;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;

public class VisualizarArticuloView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArticuloEntity articulo;
	private JLabel lblTitulo;
	private JTextField txtTitulo;
	private JLabel lblAutorPrincipal;
	private JTextField txtAutorPrincipal;
	private JTextField txtAutoresSecundarios;
	private JLabel lblAutoresSecundarios;
	private JLabel lblResumen;
	private JTextArea txtResumen;
	private JLabel lblPalabrasClave;
	private JTextField txtPalabrasClave;
	private JLabel lblCV;
	private JTextField txtCV;
	private JLabel lblCartaPresentacion;
	private JTextField txtCartaPresentacion;
	private JLabel lblFicheroFuente;
	private JTextField txtFicheroFuente;
	private Checkbox checkFirma;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				ArticuloEntity articulo = new ArticuloEntity();
//				articulo.setTitulo("Articulo sobre la naturaleza xd");
//				articulo.setPrimerAutor("Oscar");
//				articulo.setOtrosAutores("Dtoke y papo");
//				articulo.setCartaPresentacion("cartadepresentacion.txt");
//				articulo.setCVAutor("CVdeOscar.css");
//				articulo.setFicheroFuente("ficheroguente.com");
//				articulo.setFirma(true);
//				articulo.setPalabrasClave("Queso - Fernando - Teclado");
//				articulo.setResumen("Cada PL se dividirá en equipos, de 4 alumnos. Los alumnos formarán sus propios equipos,\r\n" + 
//						"aunque el profesor podrá realizar cambios para que se cumplan las cifras anteriores, o cuando\r\n" + 
//						"haya bajas o nuevas incorporaciones");
//				
//				try {
//					VisualizarArticuloView frame = new VisualizarArticuloView(articulo);
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
	public VisualizarArticuloView(ArticuloEntity articulo) {
		setFocusTraversalPolicyProvider(true);
		this.articulo = articulo;
		
		
		setTitle("Visualizar articulo: " + articulo.getTitulo());
		setBackground(Color.WHITE);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 508, 582);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblTitulo());
		contentPane.add(getTxtTitulo());
		contentPane.add(getLblAutorPrincipal());
		contentPane.add(getTxtAutorPrincipal());
		contentPane.add(getTxtAutoresSecundarios());
		contentPane.add(getLblAutoresSecundarios());
		contentPane.add(getLblResumen());
		contentPane.add(getTxtResumen());
		contentPane.add(getLblPalabrasClave());
		contentPane.add(getTxtPalabrasClave());
		contentPane.add(getLblCV());
		contentPane.add(getTxtCV());
		contentPane.add(getLblCartaPresentacion());
		contentPane.add(getTxtCartaPresentacion());
		contentPane.add(getLblFicheroFuente());
		contentPane.add(getTxtFicheroFuente());
		contentPane.add(getCheckFirma());
	}

	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Titulo:");
			lblTitulo.setLabelFor(getTxtTitulo());
			lblTitulo.setBounds(34, 25, 46, 14);
		}
		return lblTitulo;
	}
	private JTextField getTxtTitulo() {
		if (txtTitulo == null) {
			txtTitulo = new JTextField();
			txtTitulo.setBackground(Color.WHITE);
			txtTitulo.setEditable(false);
			txtTitulo.setBounds(34, 46, 431, 20);
			txtTitulo.setColumns(10);
			
			txtTitulo.setText(articulo.getTitulo());
		}
		return txtTitulo;
	}
	private JLabel getLblAutorPrincipal() {
		if (lblAutorPrincipal == null) {
			lblAutorPrincipal = new JLabel("Autor principal:");
			lblAutorPrincipal.setLabelFor(lblAutorPrincipal);
			lblAutorPrincipal.setBounds(34, 77, 373, 14);
		}
		return lblAutorPrincipal;
	}
	private JTextField getTxtAutorPrincipal() {
		if (txtAutorPrincipal == null) {
			txtAutorPrincipal = new JTextField();
			txtAutorPrincipal.setEditable(false);
			txtAutorPrincipal.setColumns(10);
			txtAutorPrincipal.setBackground(Color.WHITE);
			txtAutorPrincipal.setBounds(34, 102, 431, 20);
			
			txtAutorPrincipal.setText(articulo.getPrimerAutor());
		}
		return txtAutorPrincipal;
	}
	private JTextField getTxtAutoresSecundarios() {
		if (txtAutoresSecundarios == null) {
			txtAutoresSecundarios = new JTextField();
			txtAutoresSecundarios.setEditable(false);
			txtAutoresSecundarios.setColumns(10);
			txtAutoresSecundarios.setBackground(Color.WHITE);
			txtAutoresSecundarios.setBounds(34, 158, 431, 20);
			
			txtAutoresSecundarios.setText(articulo.getOtrosAutores());
		}
		return txtAutoresSecundarios;
	}
	private JLabel getLblAutoresSecundarios() {
		if (lblAutoresSecundarios == null) {
			lblAutoresSecundarios = new JLabel("Autores Secundarios");
			lblAutoresSecundarios.setLabelFor(getTxtAutoresSecundarios());
			lblAutoresSecundarios.setBounds(34, 133, 373, 14);
		}
		return lblAutoresSecundarios;
	}
	private JLabel getLblResumen() {
		if (lblResumen == null) {
			lblResumen = new JLabel("Resumen:");
			lblResumen.setLabelFor(getTxtResumen());
			lblResumen.setBackground(Color.WHITE);
			lblResumen.setBounds(34, 189, 373, 14);
		}
		return lblResumen;
	}
	private JTextArea getTxtResumen() {
		if (txtResumen == null) {
			txtResumen = new JTextArea();
			txtResumen.setBorder(new LineBorder(new Color(0, 0, 0)));
			txtResumen.setLineWrap(true);
			txtResumen.setBackground(Color.WHITE);
			txtResumen.setEditable(false);
			txtResumen.setBounds(34, 214, 431, 100);
			
			txtResumen.setText(articulo.getResumen());
		}
		return txtResumen;
	}
	private JLabel getLblPalabrasClave() {
		if (lblPalabrasClave == null) {
			lblPalabrasClave = new JLabel("Palabras clave:");
			lblPalabrasClave.setLabelFor(getTxtPalabrasClave());
			lblPalabrasClave.setBounds(34, 325, 373, 14);
		}
		return lblPalabrasClave;
	}
	private JTextField getTxtPalabrasClave() {
		if (txtPalabrasClave == null) {
			txtPalabrasClave = new JTextField();
			txtPalabrasClave.setEditable(false);
			txtPalabrasClave.setBackground(Color.WHITE);
			txtPalabrasClave.setBounds(34, 343, 431, 20);
			txtPalabrasClave.setColumns(10);
			
			txtPalabrasClave.setText(articulo.getPalabrasClave());
		}
		return txtPalabrasClave;
	}
	private JLabel getLblCV() {
		if (lblCV == null) {
			lblCV = new JLabel("CV:");
			lblCV.setBackground(Color.WHITE);
			lblCV.setBounds(34, 376, 169, 14);
		}
		return lblCV;
	}
	private JTextField getTxtCV() {
		if (txtCV == null) {
			txtCV = new JTextField();
			txtCV.setEditable(false);
			txtCV.setBackground(Color.WHITE);
			txtCV.setBounds(34, 390, 210, 20);
			txtCV.setColumns(10);
			
			txtCV.setText(articulo.getCV());
		}
		return txtCV;
	}
	private JLabel getLblCartaPresentacion() {
		if (lblCartaPresentacion == null) {
			lblCartaPresentacion = new JLabel("Carta de presentación:");
			lblCartaPresentacion.setBackground(Color.WHITE);
			lblCartaPresentacion.setBounds(254, 374, 182, 14);
		}
		return lblCartaPresentacion;
	}
	private JTextField getTxtCartaPresentacion() {
		if (txtCartaPresentacion == null) {
			txtCartaPresentacion = new JTextField();
			txtCartaPresentacion.setEditable(false);
			txtCartaPresentacion.setBackground(Color.WHITE);
			txtCartaPresentacion.setBounds(254, 390, 211, 20);
			txtCartaPresentacion.setColumns(10);
			
			txtCartaPresentacion.setText(articulo.getCartaPresentacion());
		}
		return txtCartaPresentacion;
	}
	private JLabel getLblFicheroFuente() {
		if (lblFicheroFuente == null) {
			lblFicheroFuente = new JLabel("Fichero fuente:");
			lblFicheroFuente.setBackground(Color.WHITE);
			lblFicheroFuente.setBounds(34, 421, 169, 14);
		}
		return lblFicheroFuente;
	}
	private JTextField getTxtFicheroFuente() {
		if (txtFicheroFuente == null) {
			txtFicheroFuente = new JTextField();
			txtFicheroFuente.setEditable(false);
			txtFicheroFuente.setBackground(Color.WHITE);
			txtFicheroFuente.setBounds(34, 440, 210, 20);
			txtFicheroFuente.setColumns(10);
			
			txtFicheroFuente.setText(articulo.getFicheroFuente());
		}
		return txtFicheroFuente;
	}
	private Checkbox getCheckFirma() {
		if (checkFirma == null) {
			checkFirma = new Checkbox("El autor ha confirmado que es el autor del artículo y no ha incurrido en plagio.");
			checkFirma.setState(articulo.isFirma());
			checkFirma.setBounds(34, 482, 431, 37);
			
		}
		return checkFirma;
	}

}
