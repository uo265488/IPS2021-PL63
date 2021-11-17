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

	/**
	 * Create the frame.
	 */
	public MenuEditor() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 452, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtDisponibles());
		contentPane.add(getBtAsignados());
		contentPane.add(getBtnAbrirDebates());
		contentPane.add(getLblDebates());
		contentPane.add(getBtnCerrarDebates());
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
			btnAbrirDebates.setBounds(49, 187, 154, 34);

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

	}

	private JButton getBtnCerrarDebates() {
		if (btnCerrarDebates == null) {
			btnCerrarDebates = new JButton("Cerrar debates");
			btnCerrarDebates.setBounds(230, 187, 164, 34);
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
}
