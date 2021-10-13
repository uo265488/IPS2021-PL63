package giis.demo.tkrun.views.editor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ComentariosEditorView extends JFrame {

	private JPanel contentPane;
	private ArticuloEntity articulo;
	private JLabel lblTituloArticulo;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComentariosEditorView frame = new ComentariosEditorView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public ComentariosEditorView(ArticuloEntity articulo) {
		this.articulo = articulo;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblTituloArticulo());
		contentPane.add(getTextArea());
	}
	private JLabel getLblTituloArticulo() {
		if (lblTituloArticulo == null) {
			lblTituloArticulo = new JLabel("");
			lblTituloArticulo.setBounds(10, 11, 157, 34);
			lblTituloArticulo.setText(articulo.getTitulo());
		}
		return lblTituloArticulo;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setEditable(false);
			textArea.setBounds(20, 56, 289, 161);
		}
		return textArea;
	}
}
