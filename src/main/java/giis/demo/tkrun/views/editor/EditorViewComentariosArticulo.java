package giis.demo.tkrun.views.editor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisionEntity;
import javax.swing.JButton;



public class EditorViewComentariosArticulo extends JDialog {

	private static String INDIVIDUALES = "individuales";
	private static String COLECTIVAS = "Colectivas";
	private static String COMENTARIOS = "Comentarios";
	private static final String DECISIONES = "Decisiones";
	private static final String TODA_LA_INFORMACION = "Toda la informacion";

	private JPanel contentPane;
	private ArticuloEntity articulo;
	private List<RevisionEntity> revisiones;
	
	private JLabel lblArticulo;
	private JLabel lblTituloArticulo;
	private JComboBox cbSeleccionarRevisiones;
	private JLabel lblSeleccionarRevisiones;
	private JComboBox cbFormatoVisualizacion;
	private JComboBox cbSeleccionarRevisor;
	private JLabel lblRevisor;
	private JLabel lblFormato;
	private JTextArea txtRevisiones;
	private JButton btnEnviarComentariosAutor;
	private EditorView ventanaAnterior;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			EditorViewComentariosArticulo1 dialog = new EditorViewComentariosArticulo1();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public EditorViewComentariosArticulo(ArticuloEntity articulo, List<RevisionEntity> revisiones, EditorView ventanaAnterior) {
		setTitle("Comentarios Revision");
		this.ventanaAnterior = ventanaAnterior;
		this.articulo = articulo;
		this.revisiones = revisiones;
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 585, 492);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblArticulo());
		contentPane.add(getLblTituloArticulo());
		contentPane.add(getCbSeleccionarRevisiones());
		contentPane.add(getLblSeleccionarRevisiones());
		contentPane.add(getCbFormatoVisualizacion());
		contentPane.add(getCbSeleccionarRevisor());
		contentPane.add(getLblRevisor());
		contentPane.add(getLblFormato());
		contentPane.add(getTxtRevisiones());
		contentPane.add(getBtnEnviarComentariosAutor());
	}
	private JLabel getLblArticulo() {
		if (lblArticulo == null) {
			lblArticulo = new JLabel("Articulo:");
			lblArticulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblArticulo.setBounds(10, 11, 147, 23);
		}
		return lblArticulo;
	}
	private JLabel getLblTituloArticulo() {
		if (lblTituloArticulo == null) {
			lblTituloArticulo = new JLabel("");
			lblTituloArticulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblTituloArticulo.setBounds(10, 45, 268, 30);
			lblTituloArticulo.setText(articulo.toString());
		}
		return lblTituloArticulo;
	}
	private JComboBox getCbSeleccionarRevisiones() {
		if (cbSeleccionarRevisiones == null) {
			cbSeleccionarRevisiones = new JComboBox();
			cbSeleccionarRevisiones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setCbFormatoVisualizacion();
					setTextoRevisiones();
				}
			});
			cbSeleccionarRevisiones.setBounds(10, 125, 105, 23);
			String[] opciones = {"Individuales", "Colectivas"};
			cbSeleccionarRevisiones.setModel(new DefaultComboBoxModel(new String[] {INDIVIDUALES, COLECTIVAS}));
		}
		return cbSeleccionarRevisiones;
	}

	private JLabel getLblSeleccionarRevisiones() {
		if (lblSeleccionarRevisiones == null) {
			lblSeleccionarRevisiones = new JLabel("Revisiones:");
			lblSeleccionarRevisiones.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblSeleccionarRevisiones.setBounds(10, 86, 83, 23);
		}
		return lblSeleccionarRevisiones;
	}
	private JComboBox getCbFormatoVisualizacion() {
		if (cbFormatoVisualizacion == null) {
			cbFormatoVisualizacion = new JComboBox();
			cbFormatoVisualizacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setTextoRevisiones();
				}
			});
			cbFormatoVisualizacion.setBounds(386, 126, 147, 23);
			setCbFormatoVisualizacion();
		}
		return cbFormatoVisualizacion;
	}
	
	private void setCbFormatoVisualizacion() {
		String[] opciones;
		if (getCbSeleccionarRevisiones().getSelectedItem().equals(INDIVIDUALES)) {
			String[] opcionesInd = {COMENTARIOS, TODA_LA_INFORMACION};
			opciones = opcionesInd;
			getCbSeleccionarRevisor().setEnabled(true);
			getCbSeleccionarRevisor().setModel(new DefaultComboBoxModel(revisiones.toArray()));
		}
		else {
			String[] opcionesCol = {COMENTARIOS, DECISIONES};
			opciones = opcionesCol;
			getCbSeleccionarRevisor().setEnabled(false);
		}
		getCbFormatoVisualizacion().setModel(new DefaultComboBoxModel(opciones));
	}
	
	/**
	 * Establece el texto de las revisiones dependiendo de la opcion seleccionada con las comboBoxes
	 */
	private void setTextoRevisiones() {
		String text = "";
		if (revisiones.size() != 0) {
		if (getCbSeleccionarRevisiones().getSelectedItem().equals(INDIVIDUALES)) 
			text = setTextoRevisionesIndividuales();
		else 
			text = setTextoRevisionesColectivas();
		}
		else text = "No hay revisiones aun de este articulo";
		getTxtRevisiones().setText(text);
	}
	
	private String setTextoRevisionesIndividuales() {
		String text = "";
		RevisionEntity revision = null;
		//Busca el revisor seleccionado 
		for (RevisionEntity rev : revisiones) {
			if (rev.getIdRevisor() == Integer.parseInt(getCbSeleccionarRevisor().getSelectedItem().toString())) {
				revision = rev;
				break;
			}	
		}
		if (getCbFormatoVisualizacion().getSelectedItem().equals(COMENTARIOS)) {
			text = "Comentarios: " + revision.getComentariosEditor();
		}
		else {
			text = "Comentarios al editor:\n" + revision.getComentariosEditor()
			+ "\n\n Comentarios al autor:\n"+ revision.getComentariosAutor()
			+ "\n\n Decision:\n" + revision.getDecision();
		}
		return text;
	}
	
	private String setTextoRevisionesColectivas() {
		String text = "";
		if (getCbFormatoVisualizacion().getSelectedItem().equals(COMENTARIOS)) {
			for (RevisionEntity revision : revisiones) {
				text += "Revisor " + revision.getIdRevisor() + ": \n comentario: " + revision.getComentariosEditor() + "\n\n";
			}
		}
		else {
			for (RevisionEntity revision : revisiones) {
				text += "Revisor " + revision.getIdRevisor() + ": \n decision: " + revision.getDecision() + "\n\n";
			}				
		}			
		return text;
	}
	
	private JComboBox getCbSeleccionarRevisor() {
		if (cbSeleccionarRevisor == null) {
			cbSeleccionarRevisor = new JComboBox();
			cbSeleccionarRevisor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setTextoRevisiones();
				}
			});
			cbSeleccionarRevisor.setEnabled(false);
			cbSeleccionarRevisor.setBounds(125, 125, 119, 23);
		}
		return cbSeleccionarRevisor;
	}
	private JLabel getLblRevisor() {
		if (lblRevisor == null) {
			lblRevisor = new JLabel("Revisor:");
			lblRevisor.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblRevisor.setBounds(125, 90, 105, 19);
		}
		return lblRevisor;
	}
	private JLabel getLblFormato() {
		if (lblFormato == null) {
			lblFormato = new JLabel("Formato:");
			lblFormato.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblFormato.setBounds(386, 91, 147, 18);
		}
		return lblFormato;
	}
	private JTextArea getTxtRevisiones() {
		if (txtRevisiones == null) {
			txtRevisiones = new JTextArea();
			txtRevisiones.setBorder(new LineBorder(new Color(0, 0, 0)));
			txtRevisiones.setEditable(false);
			txtRevisiones.setBounds(20, 159, 525, 237);
			setTextoRevisiones();
		}
		return txtRevisiones;
	}
	private JButton getBtnEnviarComentariosAutor() {
		if (btnEnviarComentariosAutor == null) {
			btnEnviarComentariosAutor = new JButton("Enviar comentarios al autor");
			btnEnviarComentariosAutor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					visualizarComentariosAutor();					
				}
			});
			btnEnviarComentariosAutor.setBackground(new Color(50, 205, 50));
			btnEnviarComentariosAutor.setForeground(new Color(255, 255, 255));
			btnEnviarComentariosAutor.setBounds(321, 407, 212, 37);
		}
		return btnEnviarComentariosAutor;
	}
	
	private void visualizarComentariosAutor() {
		EditorViewComentariosAutor comentariosAutor = new EditorViewComentariosAutor(revisiones, articulo, this);
		comentariosAutor.setModal(true);
		comentariosAutor.setLocationRelativeTo(this);
		comentariosAutor.setVisible(true);
	}
	
	public void disposeComentariosEditorWindow() {
		this.ventanaAnterior.dispose();
		this.dispose();
	}
}
