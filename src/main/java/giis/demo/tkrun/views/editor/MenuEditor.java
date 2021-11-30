package giis.demo.tkrun.views.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.editor.EditorController;

public class MenuEditor extends JDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btDisponibles;
	private JButton btAsignados;
	private EditorController controller = new EditorController();
	private JButton btnAbrirDebates;
	private JLabel lblDebates;
	private JButton btnCerrarDebates;
	private JButton btEnDebate;
	private JButton btPublicar;

	/**
	 * Create the frame.
	 */
	public MenuEditor() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 467, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtDisponibles());
		contentPane.add(getBtAsignados());
		contentPane.add(getBtnAbrirDebates());
		contentPane.add(getLblDebates());
		contentPane.add(getBtnCerrarDebates());
		contentPane.add(getBtEnDebate());
		contentPane.add(getBtPublicar());
	}

	private JButton getBtAsignados() {
		if (btAsignados == null) {
			btAsignados = new JButton("Articulos Asignados");
			btAsignados.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mostrarAsignados();
				}
			});
			btAsignados.setBounds(49, 119, 345, 34);

		}
		return btAsignados;
	}

	private JButton getBtDisponibles() {
		if (btDisponibles == null) {
			btDisponibles = new JButton("Articulos Disponibles");
			btDisponibles.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mostrarDisponibles();
				}
			});
			btDisponibles.setBounds(49, 61, 345, 34);

		}
		return btDisponibles;
	}

	private JButton getBtnAbrirDebates() {
		if (btnAbrirDebates == null) {
			btnAbrirDebates = new JButton("Abrir debates");
			btnAbrirDebates.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					displayVentanaAbrirDebate();
				}
			});
			btnAbrirDebates.setBounds(49, 189, 164, 34);

		}
		return btnAbrirDebates;
	}

	/**
	 * Muestra la ventana de abrir debate
	 */
	private void displayVentanaAbrirDebate() {
		EditorAbrirDebateView vista = new EditorAbrirDebateView();
		vista.setLocationRelativeTo(this);
		vista.setVisible(true);
		vista.setModal(false);

	}

	private JButton getBtnCerrarDebates() {
		if (btnCerrarDebates == null) {
			btnCerrarDebates = new JButton("Cerrar debates");
			btnCerrarDebates.setBounds(229, 189, 164, 34);
		}
		return btnCerrarDebates;
	}

	private JLabel getLblDebates() {
		if (lblDebates == null) {
			lblDebates = new JLabel("Debates:");
			lblDebates.setBounds(49, 164, 345, 14);
		}
		return lblDebates;
	}

	private void mostrarAsignados() {
		EditorViewDecisionArticulo ven = new EditorViewDecisionArticulo(controller);
		ven.setVisible(true);
		// ven.setModal(true);
	}

	private void mostrarDisponibles() {
		EditorPrincipalView ven = new EditorPrincipalView(controller);
		ven.setVisible(true);
		// ven.setModal(true);
	}

	private JButton getBtEnDebate() {
		if (btEnDebate == null) {
			btEnDebate = new JButton("Articulos en Debate");
			btEnDebate.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					mostrarEnDebate();
				}
			});
			btEnDebate.setBounds(49, 241, 164, 34);
		}
		return btEnDebate;
	}

	private void mostrarEnDebate() {
		EditorViewArticulosEnDebate ven = new EditorViewArticulosEnDebate(controller);
		ven.setVisible(true);
		// ven.setModal(true);
	}

	private JButton getBtPublicar() {
		if (btPublicar == null) {
			btPublicar = new JButton("Articulos para publicar");
			btPublicar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mostrarParaPublicar();
				}
			});
			btPublicar.setBounds(229, 241, 165, 34);
		}
		return btPublicar;
	}

	private void mostrarParaPublicar() {
		EditorViewArticulosParaPublicar ven = new EditorViewArticulosParaPublicar(controller);
		ven.setVisible(true);
	}
}
