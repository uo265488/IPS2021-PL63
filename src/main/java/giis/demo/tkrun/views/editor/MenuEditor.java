package giis.demo.tkrun.views.editor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.editor.EditorController;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuEditor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btDisponibles;
	private JButton btAsignados;
	private EditorController controller;

	/**
	 * Create the frame.
	 */
	public MenuEditor(EditorController controller) {
		this.controller = controller;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtDisponibles());
		contentPane.add(getBtAsignados());
	}

	private JButton getBtDisponibles() {
		if (btDisponibles == null) {
			btDisponibles = new JButton("Articulos Disponibles");
			btDisponibles.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarDisponibles();
				}
			});
			btDisponibles.setBounds(115, 61, 182, 34);
		}
		return btDisponibles;
	}
	private void mostrarDisponibles() {
		EditorPrincipalView ven = new EditorPrincipalView(controller);
		ven.setVisible(true);
		ven.setModal(true);
	}
	private JButton getBtAsignados() {
		if (btAsignados == null) {
			btAsignados = new JButton("Articulos Asignados");
			btAsignados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarAsignados();
				}
			});
			btAsignados.setBounds(115, 119, 182, 34);
		}
		return btAsignados;
	}
	private void mostrarAsignados() {
		EditorViewDecisionArticulo ven = new EditorViewDecisionArticulo(controller);
		ven.setVisible(true);
//		ven.setModal(true);
		ven.setLocationRelativeTo(this);
	}
}
