package giis.demo.tkrun.views.editor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
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

import giis.demo.tkrun.controllers.editor.EditorController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisionEntity;
import giis.demo.tkrun.controllers.entities.RevisorEntity;
import giis.demo.tkrun.controllers.revision.RevisionController;
import giis.demo.tkrun.views.articulo.VisualizarArticuloView;

public class EditorView extends JDialog {

	private JPanel contentPane;
	private List<RevisorEntity> revisoresDisponibles;
	private EditorController editorController;
	private ArticuloEntity articulo;
	private JButton btnAsignar;
	private JLabel lblRevisores;
	private JLabel lblArticulo;
	private JTextField txtArticulo;
	private JButton btnVisualizar;
	private JScrollPane scrollPane;
	private JList<RevisorEntity> listRevisores;
	private JButton btnComentarios;

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

//	/**
//	 * Create the application.
//	 */
//	public EditorView() {
//		setTitle("Asignación de revisores");
//		setResizable(false);
//		initialize();
//	}
//
//	public EditorView(EditorController controller) {
//		this.editorController = controller;
//		this.revisoresDisponibles = controller.getRevisoresDisponibles();
//		initialize();
//	}

	public EditorView(EditorController controller, ArticuloEntity articulo) {
		setTitle("Asignación de revisores");
		setResizable(false);
		this.editorController = controller;
		this.revisoresDisponibles = controller.getRevisoresDisponibles();

		this.articulo = articulo;
		initialize();
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 603, 344);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnAsignar());
		contentPane.add(getLblRevisores());
		contentPane.add(getLblArticulo());
		contentPane.add(getTxtArticulo());
		contentPane.add(getBtnVisualizar());
		contentPane.add(getScrollPane());
		contentPane.add(getBtnComentarios());

	}

	private JButton getBtnAsignar() {
		if (btnAsignar == null) {
			btnAsignar = new JButton("Asignar");
			btnAsignar.setMnemonic('A');
			btnAsignar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					checkAsignacion();
				}
			});
			btnAsignar.setBackground(Color.GREEN);
			btnAsignar.setBounds(422, 169, 109, 29);
		}
		return btnAsignar;
	}

	protected void checkAsignacion() {
		if (listRevisores.getSelectedValuesList().size() == 3) {
			editorController.asignarRevisoresAlArticulo(getRevisoresSeleccionados(), articulo, "hoy"); // dar opcion de
																										// escoger fecha
			removeSelectedElements();
			
		} else {
			JOptionPane.showMessageDialog(this, "Tienes que seleccionar 3 revisores. ");
		}

	}

	private void removeSelectedElements() {
		DefaultListModel<RevisorEntity> dlm = (DefaultListModel) listRevisores.getModel();
		int count = listRevisores.getSelectedIndices().length;

		for (int i = 0; i < count; i++)
		{
		     dlm.removeElementAt(listRevisores.getSelectedIndex());
		}
		
	}

	private List<RevisorEntity> getRevisoresSeleccionados() {

		return listRevisores.getSelectedValuesList();
	}

	private JLabel getLblRevisores() {
		if (lblRevisores == null) {
			lblRevisores = new JLabel("Selecciona 3 revisores para el artículo:");
			lblRevisores.setDisplayedMnemonic('S');
			lblRevisores.setBounds(53, 142, 324, 14);
		}
		return lblRevisores;
	}

	private JLabel getLblArticulo() {
		if (lblArticulo == null) {
			lblArticulo = new JLabel("Artículo:");
			lblArticulo.setBounds(53, 58, 209, 14);
		}
		return lblArticulo;
	}

	private JTextField getTxtArticulo() {
		if (txtArticulo == null) {
			txtArticulo = new JTextField();
			txtArticulo.setEditable(false);
			txtArticulo.setBackground(Color.WHITE);
			txtArticulo.setBounds(53, 83, 324, 20);
			txtArticulo.setColumns(10);

			txtArticulo.setText(this.articulo.toString());

		}
		return txtArticulo;
	}

	private JButton getBtnVisualizar() {
		if (btnVisualizar == null) {
			btnVisualizar = new JButton("Visualizar");
			btnVisualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					visualizarArticulo();
				}
			});
			btnVisualizar.setMnemonic('V');
			btnVisualizar.setBounds(422, 82, 109, 23);
		}
		return btnVisualizar;
	}

	/**
	 * Muestra la informacion del articulo seleccionado en el comboBox
	 */
	protected void visualizarArticulo() {
		VisualizarArticuloView vistaArticulo = new VisualizarArticuloView(this.articulo);

		vistaArticulo.setModal(true);
		vistaArticulo.setLocationRelativeTo(this);
		vistaArticulo.setVisible(true);

	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBackground(Color.WHITE);
			scrollPane.setBounds(53, 169, 324, 119);
			scrollPane.setViewportView(getListRevisores());
		}
		return scrollPane;
	}
	private JList<RevisorEntity> getListRevisores() {
		if (listRevisores == null) {
			listRevisores = new JList<RevisorEntity>();
			listRevisores.setBackground(Color.WHITE);
			
			listRevisores.setModel(addModel());
		}
		return listRevisores;
	}

	private ListModel<RevisorEntity> addModel() {
	     DefaultListModel<RevisorEntity> model = new DefaultListModel<RevisorEntity>();
	     
	     for ( RevisorEntity ent: this.revisoresDisponibles) {
	    	 model.addElement(ent);
	     }
	     return model;
	}
	private JButton getBtnComentarios() {
		if (btnComentarios == null) {
			btnComentarios = new JButton("Comentarios");
			btnComentarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					visualizarComentariosArticulo();
				}
			});
			btnComentarios.setBounds(422, 116, 109, 20);
		}
		return btnComentarios;
	}
	
	private void visualizarComentariosArticulo() {
		RevisionController revController = new RevisionController();
		List<RevisionEntity> revisiones = revController.getRevisionesDelArticulo(articulo);
		if (revisiones.size() > 0 && articulo.getEstado().contentEquals(ArticuloEntity.CON_EL_EDITOR)) {
			EditorViewComentariosArticulo comentarios = new EditorViewComentariosArticulo(articulo);
			comentarios.setModal(true);
			comentarios.setLocationRelativeTo(this);
			comentarios.setVisible(true);
			
		}
		else 
			JOptionPane.showMessageDialog(rootPane, "No hay revisiones disponibles de este articulo");
	}
}
