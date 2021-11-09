package giis.demo.tkrun.views.editor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.editor.EditorController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisorEntity;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditorAsignarFechasSegundaRevision extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox cbRevisor1;
	private JComboBox cbRevisor2;
	private JComboBox cbRevisor3;
	private JLabel lblRevisor1;
	private JLabel lblRevisor2;
	private JLabel lblRevisor3;
	private EditorController controller;
	private ArticuloEntity articulo;
	private RevisorEntity revisor1;
	private RevisorEntity revisor2;
	private RevisorEntity revisor3;
	private JLabel lblTitle;

	/**
	 * Create the dialog.
	 */
	public EditorAsignarFechasSegundaRevision(EditorController controller, ArticuloEntity articulo, RevisorEntity revisor1, RevisorEntity revisor2, RevisorEntity revisor3) {
		setTitle("Asignar fechas a los revisores");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.controller =  controller;
		this.articulo = articulo;
		this.revisor1 = revisor1;
		this.revisor2 = revisor2;
		this.revisor3 = revisor3;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getCbRevisor1());
		contentPanel.add(getCbRevisor2());
		contentPanel.add(getCbRevisor3());
		contentPanel.add(getLblRevisor1());
		contentPanel.add(getLblRevisor2());
		contentPanel.add(getLblRevisor3());
		contentPanel.add(getLblTitle());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAsignar = new JButton("Asignar");
				btnAsignar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						checkAsignacion();
					}
				});
				buttonPane.add(btnAsignar);
				getRootPane().setDefaultButton(btnAsignar);
			}
		}
	}
	
	private ComboBoxModel<LocalDate> generarComboBoxModel() {
		DefaultComboBoxModel<LocalDate> model = new DefaultComboBoxModel<>();

		addAllFechas(model);

		return model;
	}
	
	private void addAllFechas(DefaultComboBoxModel<LocalDate> model) {
		List<LocalDate> allFechas = generarFechas();
		for (LocalDate fecha : allFechas) {
			model.addElement(fecha);
		}
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
	private JComboBox getCbRevisor1() {
		if (cbRevisor1 == null) {
			cbRevisor1 = new JComboBox();
			cbRevisor1.setModel(generarComboBoxModel());
			cbRevisor1.setBounds(10, 57, 134, 26);
		}
		return cbRevisor1;
	}
	private JComboBox getCbRevisor2() {
		if (cbRevisor2 == null) {
			cbRevisor2 = new JComboBox();
			cbRevisor2.setModel(generarComboBoxModel());
			cbRevisor2.setBounds(10, 112, 134, 26);
		}
		return cbRevisor2;
	}
	private JComboBox getCbRevisor3() {
		if (cbRevisor3 == null) {
			cbRevisor3 = new JComboBox();
			cbRevisor3.setModel(generarComboBoxModel());
			cbRevisor3.setBounds(10, 165, 134, 26);
		}
		return cbRevisor3;
	}
	private JLabel getLblRevisor1() {
		if (lblRevisor1 == null) {
			lblRevisor1 = new JLabel("Revisor 1");
			lblRevisor1.setBounds(171, 57, 200, 26);
		}
		return lblRevisor1;
	}
	private JLabel getLblRevisor2() {
		if (lblRevisor2 == null) {
			lblRevisor2 = new JLabel("Revisor 2");
			lblRevisor2.setBounds(171, 112, 200, 26);
		}
		return lblRevisor2;
	}
	private JLabel getLblRevisor3() {
		if (lblRevisor3 == null) {
			lblRevisor3 = new JLabel("Revisor 3");
			lblRevisor3.setBounds(171, 165, 200, 26);
		}
		return lblRevisor3;
	}
	
	protected void checkAsignacion() {
				if (getCbRevisor1().getSelectedIndex() != -1 && getCbRevisor1().getSelectedIndex() != -1 && getCbRevisor1().getSelectedIndex() != -1) {
					String fecha1 = getCbRevisor1().getSelectedItem().toString();
					controller.generarSegundaRevision(articulo.getIdArticulo(), revisor1.getId(), fecha1);
					String fecha2 = getCbRevisor2().getSelectedItem().toString();
					controller.generarSegundaRevision(articulo.getIdArticulo(), revisor2.getId(), fecha2);
					String fecha3 = getCbRevisor3().getSelectedItem().toString();
					controller.generarSegundaRevision(articulo.getIdArticulo(), revisor3.getId(), fecha3);
					JOptionPane.showMessageDialog(this, "Las fechas fueron asignadas correctamente");
					this.setVisible(false);
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Todos los revisores deben tener una fecha asignada ");
				}

	}
	private JLabel getLblTitle() {
		if (lblTitle == null) {
			lblTitle = new JLabel("Seleccione las fechas para las nuevas revisiones:");
			lblTitle.setBounds(10, 11, 342, 26);
		}
		return lblTitle;
	}
}

