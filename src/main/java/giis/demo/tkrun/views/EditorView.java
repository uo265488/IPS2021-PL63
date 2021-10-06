package giis.demo.tkrun.views;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.EditorController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisorEntity;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class EditorView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<RevisorEntity> comboBox;
	private List<RevisorEntity> revisoresDisponibles;
	private EditorController editorController;
	private ArticuloEntity articulo;
	private JButton btnAsignar;

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
		initialize();
	}
	
	public EditorView(EditorController controller, ArticuloEntity articulo) {
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 309);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getComboBox());
		contentPane.add(getBtnAsignar());
		
		
	}

	private JComboBox<RevisorEntity> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<RevisorEntity>();
			comboBox.setBounds(78, 13, 253, 69);
			setComboBoxModel();
			
		}
		return comboBox;
	}

	private void setComboBoxModel() {
		RevisorEntity[] array = new RevisorEntity[this.revisoresDisponibles.size()];
		for(int i = 0; i < array.length; i++) {
		    array[i] = this.revisoresDisponibles.get(i);
		}
		comboBox.setModel(new DefaultComboBoxModel<RevisorEntity>(array));
		
	}
	private JButton getBtnAsignar() {
		if (btnAsignar == null) {
			btnAsignar = new JButton("Asignar");
			btnAsignar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					checkAsignacion();
				}
			});
			btnAsignar.setBackground(Color.GREEN);
			btnAsignar.setBounds(274, 173, 97, 25);
		}
		return btnAsignar;
	}

	protected void checkAsignacion() {
		if (comboBox.getSelectedObjects().length == 3 ) {
			editorController.asignarRevisoresAlArticulo(getRevisoresSeleccionados(), articulo, "hoy"); //dar opcion de escoger fecha
		} else {
			JOptionPane.showMessageDialog(this, "Tienes que seleccionar 3 revisores. ");
		}
		
	}

	private List<RevisorEntity> getRevisoresSeleccionados() {
		List<RevisorEntity> list = new ArrayList<>();
		for (Object obj : comboBox.getSelectedObjects()) {
			list.add((RevisorEntity)obj);
		}
		return list;
	}
}
