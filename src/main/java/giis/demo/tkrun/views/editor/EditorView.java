package giis.demo.tkrun.views.editor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.editor.EditorController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisorEntity;
import giis.demo.tkrun.models.dtos.ArticuloDto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class EditorView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private List<RevisorEntity> revisoresDisponibles;
	private List<ArticuloEntity> articulos;
	private EditorController editorController;
	private ArticuloEntity articulo;
	private JComboBox cbArticulos;
	private JButton btnVerComentarios;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EditorView frame = new EditorView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	/**
	 * Create the application.
	 */
	public EditorView() {
		initialize();
	}

	public EditorView(EditorController controller) {
		this.editorController = controller;
		this.revisoresDisponibles = controller.getRevisoresDisponibles();
		this.articulos = controller.getArticulos();
		initialize();
	}
	
	public EditorView(EditorController controller, ArticuloEntity articulo) {
		this.editorController = controller;
		this.revisoresDisponibles = controller.getRevisoresDisponibles();
		this.articulos = controller.getArticulos();
		
		this.articulo = articulo;
		initialize();
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblArticulos = new JLabel("Artículos:");
		lblArticulos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblArticulos.setBounds(69, 56, 201, 41);
		contentPane.add(lblArticulos);
		contentPane.add(getCbArticulos());
		contentPane.add(getBtnVerComentarios());
		
				
	}

	/*private void setComboBoxModel() {
		RevisorEntity[] array = new RevisorEntity[this.revisoresDisponibles.size()];
		for(int i = 0; i < array.length; i++) {
		    array[i] = this.revisoresDisponibles.get(i);
		}		
	}*/
	
	private void setComboBoxArticulos() {
		String[] array = new String[this.articulos.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = this.articulos.get(i).getTitulo() + " - " + this.articulos.get(i).getPrimerAutor();
		}
		cbArticulos.setModel(new DefaultComboBoxModel<String>(array));
		
	}
	private JComboBox getCbArticulos() {
		if (cbArticulos == null) {
			cbArticulos = new JComboBox();
			cbArticulos.setBounds(63, 108, 239, 41);
			setComboBoxArticulos();
		}
		return cbArticulos;
	}
	private JButton getBtnVerComentarios() {
		if (btnVerComentarios == null) {
			btnVerComentarios = new JButton("Ver Comentarios");
			btnVerComentarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ComentariosEditorView comentarios = new ComentariosEditorView((ArticuloEntity) articulos.get(cbArticulos.getSelectedIndex()));
					comentarios.setVisible(true);
				}
			});
			btnVerComentarios.setBounds(156, 173, 146, 23);
		}
		return btnVerComentarios;
	}
}
