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
	private ArticuloEntity articulo;
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
		this.revisoresAsignados = editorController.getRevisoresAsignados(articulo);

		initialize();
	}

	private void actualizarListas() {

		scAsignados.setViewportView(getListAsignados());
		scSugeridos.setViewportView(getListSugeridos());
		scDisponibles.setViewportView(getListDisponibles());

		scAsignados.updateUI();
		scSugeridos.updateUI();
		scDisponibles.updateUI();
	}

	/**
	 * Añade el revisor selecionado a la lista de revisores
	 */
	protected void añadirRevisor() {
		if (listSugeridos.getSelectedIndex() != -1) {
			if (editorController.añadirRevisorAListaDeRevisores(listSugeridos.getSelectedValue())) {

				actualizarListas();
				JOptionPane.showMessageDialog(this, "El revisor ha sido añadido a la lista de revisores disponibles. ");
			} else {
				JOptionPane.showMessageDialog(this,
						"El revisor seleccionado ya pertenece a la lista de revisores disponibles. ");
			}

		} else {
			JOptionPane.showMessageDialog(this, "Debes seleccionar un revisor. ");
		}

	}

	protected void cerrarVentana() {
		this.dispose();

	}

	protected void checkAsignacion() {
		if (listAsignados.getModel().getSize() < 3) {
			if (editorController.checkArticuloParaAsignar(articulo)) {
				if (cbFechas.getSelectedIndex() != -1) {
					editorController.asignarRevisor(listDisponibles.getSelectedValue(), articulo,
							cbFechas.getSelectedItem().toString());
					actualizarListas();
					btnRechazar.setEnabled(false);

				} else {
					JOptionPane.showMessageDialog(this, "Debes seleccionar una fecha. ");
				}
			} else {
				JOptionPane.showMessageDialog(this, "El estado del articulo no es con el editor. ");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Ya se ha asignado el numero maximo de revisores: 3. ");
		}

	}

	private ComboBoxModel<LocalDate> generarComboBoxModel() {
		DefaultComboBoxModel<LocalDate> model = new DefaultComboBoxModel<>();

		model.addAll(generarFechas());

		return model;
	}

	/**
	 * Genera una lista de fechas
	 *
	 * @return
	 */
	private List<LocalDate> generarFechas() {
		LocalDate start = LocalDate.now();
		LocalDate end = LocalDate.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
		return Stream.iterate(start, date -> date.plusDays(1)).limit(ChronoUnit.DAYS.between(start, end))
				.collect(Collectors.toList());
	}

	private JButton getBtnAñadirRevisor() {
		if (btnAñadirRevisor == null) {
			btnAñadirRevisor = new JButton("Añadir revisor");
			btnAñadirRevisor.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					añadirRevisor();
				}
			});
			btnAñadirRevisor.setBackground(Color.GREEN);
			btnAñadirRevisor.setMnemonic('ñ');
			btnAñadirRevisor.setBounds(53, 380, 266, 45);

		}
		return btnAñadirRevisor;
	}

	private JButton getBtnAsignar() {
		if (btnAsignar == null) {
			btnAsignar = new JButton("Asignar");
			btnAsignar.setMnemonic('s');
			btnAsignar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					checkAsignacion();
				}
			});
			btnAsignar.setBackground(Color.GREEN);
			btnAsignar.setBounds(523, 380, 91, 45);
		}
		return btnAsignar;
	}

	private JButton getBtnRechazar() {
		if (btnRechazar == null) {
			btnRechazar = new JButton("Rechazar artículo");
			btnRechazar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					rechazarArticulo();
				}
			});
			btnRechazar.setBackground(Color.RED);
			btnRechazar.setBounds(642, 77, 271, 32);

			if (revisoresAsignados.size() > 0) {
				btnRechazar.setEnabled(false);
			}
		}
		return btnRechazar;
	}

	private JButton getBtnVisualizar() {
		if (btnVisualizar == null) {
			btnVisualizar = new JButton("Visualizar");
			btnVisualizar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					visualizarArticulo();
				}
			});
			btnVisualizar.setMnemonic('V');
			btnVisualizar.setBounds(505, 82, 109, 23);
		}
		return btnVisualizar;
	}

	private JComboBox<LocalDate> getCbFechas() {
		if (cbFechas == null) {
			cbFechas = new JComboBox<LocalDate>();
			cbFechas.setBounds(348, 402, 163, 23);

			cbFechas.setModel(generarComboBoxModel());

		}
		return cbFechas;
	}

	private JLabel getLblArticulo() {
		if (lblArticulo == null) {
			lblArticulo = new JLabel("Artículo:");
			lblArticulo.setBounds(53, 58, 209, 14);
		}
		return lblArticulo;
	}

	private JLabel getLblAsignados() {
		if (lblAsignados == null) {
			lblAsignados = new JLabel("Propuestas de asignación:");
			lblAsignados.setDisplayedMnemonic('S');
			lblAsignados.setBounds(642, 145, 246, 14);
		}
		return lblAsignados;
	}

	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha límite:");
			lblFecha.setLabelFor(getCbFechas());
			lblFecha.setDisplayedMnemonic('F');
			lblFecha.setBounds(348, 380, 93, 14);
		}
		return lblFecha;
	}

	private JLabel getLblRevisoresDisponibles() {
		if (lblRevisoresDisponibles == null) {
			lblRevisoresDisponibles = new JLabel("Revisores disponibles para el artículo:");
			lblRevisoresDisponibles.setDisplayedMnemonic('S');
			lblRevisoresDisponibles.setBounds(348, 148, 257, 14);
		}
		return lblRevisoresDisponibles;
	}

	private JLabel getLblRevisoresSugeridos() {
		if (lblRevisoresSugeridos == null) {
			lblRevisoresSugeridos = new JLabel("Revisores sugeridos por el autor:");
			lblRevisoresSugeridos.setDisplayedMnemonic('S');
			lblRevisoresSugeridos.setBounds(49, 148, 213, 14);
		}
		return lblRevisoresSugeridos;
	}

	private JList<RevisorEntity> getListAsignados() {
		listAsignados = new JList<RevisorEntity>();
		listAsignados.setBorder(new LineBorder(new Color(0, 0, 0)));
		listAsignados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listAsignados.setBackground(Color.WHITE);

		DefaultListModel<RevisorEntity> model = new DefaultListModel<RevisorEntity>();

		for (RevisorEntity ent : editorController.getRevisoresAsignados(articulo)) {
			model.addElement(ent);
		}

		listAsignados.setModel(model);

		return listAsignados;
	}

	private JList<RevisorEntity> getListDisponibles() {
		listDisponibles = new JList<RevisorEntity>();
		listDisponibles.setBorder(new LineBorder(new Color(0, 0, 0)));
		listDisponibles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listDisponibles.setBackground(Color.WHITE);

		DefaultListModel<RevisorEntity> model = new DefaultListModel<RevisorEntity>();

		for (RevisorEntity ent : editorController.getRevisoresDisponibles(articulo)) {
			model.addElement(ent);
		}

		listDisponibles.setModel(model);
		listDisponibles.setSelectedIndex(0);

		return listDisponibles;
	}

	private JList<RevisorEntity> getListSugeridos() {

		listSugeridos = new JList<RevisorEntity>();
		listSugeridos.setBorder(new LineBorder(new Color(0, 0, 0)));
		listSugeridos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSugeridos.setBackground(Color.WHITE);

		DefaultListModel<RevisorEntity> model = new DefaultListModel<RevisorEntity>();

		for (RevisorEntity ent : editorController.getRevisoresSugeridos(articulo)) {
			model.addElement(ent);

			// TESTEO
			System.out.print(ent.toString());
		}

		listSugeridos.setModel(model);
		listSugeridos.setSelectedIndex(0);

		return listSugeridos;
	}

	private JScrollPane getScAsignados() {

		scAsignados = new JScrollPane();
		scAsignados.setBackground(Color.WHITE);
		scAsignados.setBounds(642, 175, 271, 192);
		scAsignados.setViewportView(getListAsignados());

		return scAsignados;
	}

	private JScrollPane getScDisponibles() {

		scDisponibles = new JScrollPane();
		scDisponibles.setBackground(Color.WHITE);
		scDisponibles.setBounds(348, 175, 271, 192);
		scDisponibles.setViewportView(getListDisponibles());

		return scDisponibles;
	}

	private JScrollPane getScSugeridos() {

		scSugeridos = new JScrollPane();
		scSugeridos.setBorder(new LineBorder(new Color(130, 135, 144)));
		scSugeridos.setBackground(Color.WHITE);
		scSugeridos.setBounds(53, 175, 266, 192);
		scSugeridos.setViewportView(getListSugeridos());

		return scSugeridos;
	}

	private JTextField getTxtArticulo() {
		if (txtArticulo == null) {
			txtArticulo = new JTextField();
			txtArticulo.setEditable(false);
			txtArticulo.setBackground(Color.WHITE);
			txtArticulo.setBounds(53, 83, 439, 20);
			txtArticulo.setColumns(10);

			txtArticulo.setText(this.articulo.toString());

		}
		return txtArticulo;
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 948, 494);
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

	protected void rechazarArticulo() {
		if (editorController.checkArticuloParaAsignar(articulo)) {
			editorController.rechazarArticulo(articulo);
			btnRechazar.setEnabled(false);

		} else {
			JOptionPane.showMessageDialog(this, "El articulo ya ha sido asignado o ha sido rechazado ");

		}

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
}
