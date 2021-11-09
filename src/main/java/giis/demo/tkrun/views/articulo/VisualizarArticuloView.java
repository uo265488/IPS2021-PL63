package giis.demo.tkrun.views.articulo;

import java.awt.Checkbox;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import giis.demo.tkrun.controllers.articulo.ArticuloController;
import giis.demo.tkrun.controllers.editor.EditorController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisorEntity;

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
	private JTextArea txtRevisoresSugeridos;
	private JLabel lblRevisoresSugeridos;

	private EditorController editorController = new EditorController();
	private ArticuloController articuloController = new ArticuloController();

	/**
	 * Create the frame.
	 */
	public VisualizarArticuloView(ArticuloEntity articulo) {
		setFocusTraversalPolicyProvider(true);
		this.articulo = articulo;

		setTitle("Visualizar articulo: " + articulo.getTitulo());
		setBackground(Color.WHITE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 571, 636);
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
		contentPane.add(getTxtRevisoresSugeridos());
		contentPane.add(getLblRevisoresSugeridos());
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
			txtTitulo.setBounds(34, 46, 501, 20);
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
			txtAutorPrincipal.setBounds(34, 102, 501, 20);

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
			txtAutoresSecundarios.setBounds(34, 158, 501, 20);

			txtAutoresSecundarios.setText(articuloController.getOtrosAutores("" + articulo.getIdArticulo()));
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
			txtResumen.setBounds(34, 214, 501, 100);

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
			txtPalabrasClave.setBounds(34, 343, 501, 20);
			txtPalabrasClave.setColumns(10);

			txtPalabrasClave.setText(articulo.getPalabrasClave());
		}
		return txtPalabrasClave;
	}

	private JLabel getLblCV() {
		if (lblCV == null) {
			lblCV = new JLabel("CV:");
			lblCV.setBackground(Color.WHITE);
			lblCV.setBounds(202, 509, 169, 14);
		}
		return lblCV;
	}

	private JTextField getTxtCV() {
		if (txtCV == null) {
			txtCV = new JTextField();
			txtCV.setEditable(false);
			txtCV.setBackground(Color.WHITE);
			txtCV.setBounds(202, 534, 158, 20);
			txtCV.setColumns(10);

			txtCV.setText(articulo.getCV());
		}
		return txtCV;
	}

	private JLabel getLblCartaPresentacion() {
		if (lblCartaPresentacion == null) {
			lblCartaPresentacion = new JLabel("Carta de presentación:");
			lblCartaPresentacion.setBackground(Color.WHITE);
			lblCartaPresentacion.setBounds(370, 509, 161, 14);
		}
		return lblCartaPresentacion;
	}

	private JTextField getTxtCartaPresentacion() {
		if (txtCartaPresentacion == null) {
			txtCartaPresentacion = new JTextField();
			txtCartaPresentacion.setEditable(false);
			txtCartaPresentacion.setBackground(Color.WHITE);
			txtCartaPresentacion.setBounds(370, 534, 165, 20);
			txtCartaPresentacion.setColumns(10);

			txtCartaPresentacion.setText(articulo.getCartaPresentacion());
		}
		return txtCartaPresentacion;
	}

	private JLabel getLblFicheroFuente() {
		if (lblFicheroFuente == null) {
			lblFicheroFuente = new JLabel("Fichero fuente:");
			lblFicheroFuente.setBackground(Color.WHITE);
			lblFicheroFuente.setBounds(34, 509, 169, 14);
		}
		return lblFicheroFuente;
	}

	private JTextField getTxtFicheroFuente() {
		if (txtFicheroFuente == null) {
			txtFicheroFuente = new JTextField();
			txtFicheroFuente.setEditable(false);
			txtFicheroFuente.setBackground(Color.WHITE);
			txtFicheroFuente.setBounds(34, 534, 158, 20);
			txtFicheroFuente.setColumns(10);

			txtFicheroFuente.setText(articulo.getFicheroFuente());
		}
		return txtFicheroFuente;
	}

	private Checkbox getCheckFirma() {
		if (checkFirma == null) {
			checkFirma = new Checkbox(
					"El autor ha confirmado que es el autor del artículo y no ha incurrido en plagio.");
			checkFirma.setBounds(34, 560, 431, 37);

			checkFirma.setState(articulo.isFirma());
		}
		return checkFirma;
	}

	private JTextArea getTxtRevisoresSugeridos() {
		if (txtRevisoresSugeridos == null) {
			txtRevisoresSugeridos = new JTextArea();

			txtRevisoresSugeridos.setLineWrap(true);
			txtRevisoresSugeridos.setEditable(false);
			txtRevisoresSugeridos.setBorder(new LineBorder(new Color(0, 0, 0)));
			txtRevisoresSugeridos.setBackground(Color.WHITE);
			txtRevisoresSugeridos.setBounds(34, 398, 501, 100);

			txtRevisoresSugeridos.setText(getRevisoresSugeridos());
		}
		return txtRevisoresSugeridos;
	}

	private String getRevisoresSugeridos() {

		return editorController.getRevisoresSugeridos(articulo).stream()
				.map(revisor -> " - " + revisor.toString() + "\n").reduce("", String::concat);
	}

	private JLabel getLblRevisoresSugeridos() {
		if (lblRevisoresSugeridos == null) {
			lblRevisoresSugeridos = new JLabel("Revisores sugeridos por el autor del artículo:");
			lblRevisoresSugeridos.setBounds(34, 374, 501, 14);
		}
		return lblRevisoresSugeridos;
	}
}
