package giis.demo.tkrun.views.editor;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import giis.demo.tkrun.controllers.articulo.ArticuloController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;

public class EditorAbrirDebateView extends JDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private JScrollPane scDebates;
	private JList<ArticuloEntity> listArticulosDebatibles;
	private ArticuloController articulosController = new ArticuloController();

	/**
	 * Create the dialog.
	 */
	public EditorAbrirDebateView() {
		setTitle("Editor - Abrir debates");
		setResizable(false);
		setBackground(Color.WHITE);
		setBounds(100, 100, 522, 366);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblArticulosDebatibles = new JLabel(
					"Artículos que cumplen los requisitos para entrar en periodo de debate:");
			lblArticulosDebatibles.setBounds(38, 35, 438, 14);
			contentPanel.add(lblArticulosDebatibles);
		}
		contentPanel.add(getScDebates());
		{
			JButton btnAbrirDebate = new JButton("Abrir debate");
			btnAbrirDebate.setBounds(38, 278, 438, 33);
			contentPanel.add(btnAbrirDebate);
		}
		{
			JLabel lblFecha = new JLabel("Fecha cierre del debate:");
			lblFecha.setBounds(38, 232, 168, 14);
			contentPanel.add(lblFecha);
		}
		{
			JComboBox<LocalDate> comboBox = new JComboBox<>();
			comboBox.setBounds(235, 229, 241, 20);
			comboBox.setModel(generarComboBoxModel());
			contentPanel.add(comboBox);
		}
	}

	private JList<ArticuloEntity> getListArticulosDebatibles() {
		listArticulosDebatibles = new JList<ArticuloEntity>();
		listArticulosDebatibles.setBorder(new LineBorder(new Color(0, 0, 0)));
		listArticulosDebatibles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listArticulosDebatibles.setBackground(Color.WHITE);

		DefaultListModel<ArticuloEntity> model = new DefaultListModel<ArticuloEntity>();

		for (ArticuloEntity ent : articulosController.getArticulosDebatibles()) {
			model.addElement(ent);
		}

		listArticulosDebatibles.setModel(model);
		listArticulosDebatibles.setSelectedIndex(0);

		return listArticulosDebatibles;
	}

	private JScrollPane getScDebates() {
		if (scDebates == null) {
			scDebates = new JScrollPane();
			scDebates.setBounds(38, 60, 438, 150);
			scDebates.setViewportView(getListArticulosDebatibles());
		}
		return scDebates;
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

}
