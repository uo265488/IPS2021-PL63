package giis.demo.tkrun.views.autor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.autor.AutorController;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuAutor extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btCrearArticulo;
	private JButton btEstado;
	private AutorController controller;

	/**
	 * Create the frame.
	 */
	public MenuAutor(AutorController controller) {
		this.controller = controller;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtCrearArticulo());
		contentPane.add(getBtEstado());
	}

	private JButton getBtCrearArticulo() {
		if (btCrearArticulo == null) {
			btCrearArticulo = new JButton("Crear/Editar Articulo");
			btCrearArticulo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarCrear();
				}
			});
			btCrearArticulo.setBounds(123, 57, 164, 31);
		}
		return btCrearArticulo;
	}
	private void mostrarCrear() {
		AutorCreacionView ver = new AutorCreacionView(controller);
		ver.setVisible(true);
		ver.setModal(true);
	}
	private void mostrarEstado() {
		AutorView ver = new AutorView(controller);
		ver.setVisible(true);
		ver.setModal(true);
	}
	private JButton getBtEstado() {
		if (btEstado == null) {
			btEstado = new JButton("Ver estado / Enviar Version Definitiva");
			btEstado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarEstado();
				}
			});
			btEstado.setBounds(89, 111, 244, 31);
		}
		return btEstado;
	}
}
