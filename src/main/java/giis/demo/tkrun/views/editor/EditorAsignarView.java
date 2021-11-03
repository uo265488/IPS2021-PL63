package giis.demo.tkrun.views.editor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import giis.demo.tkrun.controllers.editor.EditorController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisorEntity;
import giis.demo.tkrun.views.articulo.VisualizarArticuloView;

public class EditorAsignarView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAsignar;
	private JLabel lblRevisoresDisponibles;
	private JLabel lblArticulo;
	private JTextField txtArticulo;
	private JButton btnVisualizar;
	private JScrollPane scDisponibles;
	private JList<RevisorEntity> listDisponibles;
	private JButton btnRechazar;
	private JScrollPane scSugeridos;
	private JList<RevisorEntity> listSugeridos;
	private JButton btnAñadirRevisor;
	private JScrollPane scAsignados;
	private JList<RevisorEntity> listAsignados;
	private JLabel lblAsignados;
	private JLabel lblRevisoresSugeridos;

	private EditorController editorController = new EditorController();
	private List<RevisorEntity> revisoresDisponibles;
	private ArticuloEntity articulo;
	private List<RevisorEntity> revisoresSugeridos;
	private List<RevisorEntity> revisoresAsignados;
	private JLabel lblFecha;
	private JComboBox<LocalDate> cbFechas;

	public EditorAsignarView(ArticuloEntity articulo) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentana();
			}
		});
		setTitle("Asignación de revisores");
		setResizable(false);

		this.articulo = articulo;
		this.revisoresDisponibles = editorController.getRevisoresDisponibles();
		this.revisoresSugeridos = editorController.getRevisoresSugeridos(articulo);
		this.revisoresAsignados = editorController.getRevisoresAsignados(articulo);

		initialize();
	}

	protected void cerrarVentana() {
		this.dispose();
		
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 895, 470);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnAsignar());
		contentPane.add(getLblRevisoresDisponibles());
		contentPane.add(getLblArticulo());
		contentPane.add(getTxtArticulo());
		contentPane.add(getBtnVisualizar());
		contentPane.add(getScDisponibles());
		contentPane.add(getBtnRechazar());
		contentPane.add(getScSugeridos());
		contentPane.add(getBtnAñadirRevisor());
		contentPane.add(getScAsignados());
		contentPane.add(getLblAsignados());
		contentPane.add(getLblRevisoresSugeridos());
		contentPane.add(getLblFecha());
		contentPane.add(getCbFechas());


	}

	private JButton getBtnAsignar() {
		if (btnAsignar == null) {
			btnAsignar = new JButton("Asignar");
			btnAsignar.setMnemonic('s');
			btnAsignar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					checkAsignacion();
				}
			});
			btnAsignar.setBackground(Color.GREEN);
			btnAsignar.setBounds(484, 360, 91, 45);
		}
		return btnAsignar;
	}

	protected void checkAsignacion() {
		if (editorController.getNumeroDeRevisoresAsignados(articulo) < 3) {
			if (editorController.checkArticuloParaAsignar(articulo)) {
				editorController.asignarRevisor(listDisponibles.getSelectedValue(), articulo, cbFechas.getSelectedItem().toString());
				removeSelectedElements();

			} else {
				JOptionPane.showMessageDialog(this, "El estado del articulo no es con el editor. ");
			}
		}
		JOptionPane.showMessageDialog(this, "Ya se ha asignado el numero maximo de revisores: 3. ");

	}

	private void removeSelectedElements() {
		DefaultListModel<RevisorEntity> dlm = (DefaultListModel) listDisponibles.getModel();
		int count = listDisponibles.getSelectedIndices().length;

		for (int i = 0; i < count; i++) {
			dlm.removeElementAt(listDisponibles.getSelectedIndex());
		}

	}

	private JLabel getLblRevisoresDisponibles() {
		if (lblRevisoresDisponibles == null) {
			lblRevisoresDisponibles = new JLabel("Revisores disponibles para el artículo:");
			lblRevisoresDisponibles.setDisplayedMnemonic('S');
			lblRevisoresDisponibles.setBounds(318, 133, 213, 14);
		}
		return lblRevisoresDisponibles;
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
			txtArticulo.setBounds(53, 83, 478, 20);
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
			btnVisualizar.setBounds(541, 82, 109, 23);
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

	private JScrollPane getScDisponibles() {
		if (scDisponibles == null) {
			scDisponibles = new JScrollPane();
			scDisponibles.setBackground(Color.WHITE);
			scDisponibles.setBounds(318, 158, 257, 192);
			scDisponibles.setViewportView(getListDisponibles());
			scDisponibles.setVisible(true);
		}
		return scDisponibles;
	}

	private JList<RevisorEntity> getListDisponibles() {
		if (listDisponibles == null) {
			listDisponibles = new JList<RevisorEntity>();
			listDisponibles.setBorder(new LineBorder(new Color(0, 0, 0)));
			listDisponibles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listDisponibles.setBackground(Color.WHITE);

			listDisponibles.setModel(addModel(revisoresDisponibles));
		}
		return listDisponibles;
	}

	private ListModel<RevisorEntity> addModel(List<RevisorEntity> list) {
		DefaultListModel<RevisorEntity> model = new DefaultListModel<RevisorEntity>();

		for (RevisorEntity ent : list) {
			model.addElement(ent);
		}
		return model;
	}

	private JButton getBtnRechazar() {
		if (btnRechazar == null) {
			btnRechazar = new JButton("Rechazar");
			btnRechazar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//					rechazarArticulo();
				}
			});
			btnRechazar.setBackground(Color.RED);
			btnRechazar.setBounds(698, 77, 109, 32);
		}
		return btnRechazar;
	}

//	protected void rechazarArticulo() {
//		if (editorController.checkArticuloParaAsignar(articulo)) {
//			editorController.rechazarDefinitivimenteArticulo(articulo);
//			cerrarVentana();
//
//		} else {
//			JOptionPane.showMessageDialog(this, "El articulo ya ha sido asignado o ha sido rechazado ");
//			cerrarVentana();
//		}
//
//	}

	private JScrollPane getScSugeridos() {
		if (scSugeridos == null) {
			scSugeridos = new JScrollPane();
			scSugeridos.setBorder(new LineBorder(new Color(130, 135, 144)));
			scSugeridos.setBackground(Color.WHITE);
			scSugeridos.setBounds(53, 157, 245, 192);
			scSugeridos.setViewportView(getListSugeridos());
		}
		return scSugeridos;
	}

	private JList<RevisorEntity> getListSugeridos() {
		if (listSugeridos == null) {
			listSugeridos = new JList<RevisorEntity>();
			listSugeridos.setBorder(new LineBorder(new Color(0, 0, 0)));
			listSugeridos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listSugeridos.setBackground(Color.WHITE);

			listDisponibles.setModel(addModel(this.revisoresSugeridos));
		}
		return listSugeridos;
	}

	private JButton getBtnAñadirRevisor() {
		if (btnAñadirRevisor == null) {
			btnAñadirRevisor = new JButton("Añadir revisor");
			btnAñadirRevisor.setBackground(Color.GREEN);
			btnAñadirRevisor.setMnemonic('ñ');
			btnAñadirRevisor.setBounds(53, 360, 245, 45);
		}
		return btnAñadirRevisor;
	}

	private JScrollPane getScAsignados() {
		if (scAsignados == null) {
			scAsignados = new JScrollPane();
			scAsignados.setBackground(Color.WHITE);
			scAsignados.setBounds(595, 158, 245, 192);
			scAsignados.setViewportView(getListAsignados());
		}
		return scAsignados;
	}

	private JList<RevisorEntity> getListAsignados() {
		if (listAsignados == null) {
			listAsignados = new JList<RevisorEntity>();
			listAsignados.setBorder(new LineBorder(new Color(0, 0, 0)));
			listAsignados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listAsignados.setBackground(Color.WHITE);

			listDisponibles.setModel(addModel(this.revisoresAsignados));
		}
		return listAsignados;
	}

	private JLabel getLblAsignados() {
		if (lblAsignados == null) {
			lblAsignados = new JLabel("Revisores asignados:");
			lblAsignados.setDisplayedMnemonic('S');
			lblAsignados.setBounds(594, 133, 213, 14);
		}
		return lblAsignados;
	}

	private JLabel getLblRevisoresSugeridos() {
		if (lblRevisoresSugeridos == null) {
			lblRevisoresSugeridos = new JLabel("Revisores sugeridos por el autor:");
			lblRevisoresSugeridos.setDisplayedMnemonic('S');
			lblRevisoresSugeridos.setBounds(53, 133, 213, 14);
		}
		return lblRevisoresSugeridos;
	}
	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha límite:");
			lblFecha.setLabelFor(getCbFechas());
			lblFecha.setDisplayedMnemonic('F');
			lblFecha.setBounds(318, 360, 83, 14);
		}
		return lblFecha;
	}
	private JComboBox<LocalDate> getCbFechas() {
		if (cbFechas == null) {
			cbFechas = new JComboBox<LocalDate>();
			cbFechas.setBounds(318, 382, 156, 23);
			
			cbFechas.setModel(generarComboBoxModel());
			
		}
		return cbFechas;
	}

	private ComboBoxModel<LocalDate> generarComboBoxModel() {
		DefaultComboBoxModel<LocalDate> model= new DefaultComboBoxModel<>();
		
		model.addAll(generarFechas());
		
		return model;
	}

	/**
	 * Genera una lista de fechas
	 * @return
	 */
	private List<LocalDate> generarFechas() {
		LocalDate start = LocalDate.now();
		LocalDate end = LocalDate.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
		return Stream.iterate(start, date -> date.plusDays(1))
		    .limit(ChronoUnit.DAYS.between(start, end))
		    .collect(Collectors.toList()); 
	}
}
