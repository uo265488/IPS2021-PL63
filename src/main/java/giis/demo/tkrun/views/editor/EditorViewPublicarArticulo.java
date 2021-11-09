package giis.demo.tkrun.views.editor;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.articulo.ArticuloController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;

public class EditorViewPublicarArticulo extends JDialog {

	private static boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	private final JPanel contentPanel = new JPanel();
	private JLabel lblDOI;
	private JTextField txtDOI;
	private JLabel lblFecha;
	private JLabel lblVolumen;
	private JTextField txtFecha;
	private JTextField txtVolumen;
	private ArticuloController artController;
	private ArticuloEntity articulo;

	private EditorViewComentariosAutor ventanaAnterior;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { try { EditorViewPublicarArticulo
	 * dialog = new EditorViewPublicarArticulo();
	 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }/*
	 * 
	 * /** Create the dialog.
	 */
	public EditorViewPublicarArticulo(EditorViewComentariosAutor ventanaAnterior, ArticuloEntity articulo) {
		this.artController = new ArticuloController();
		this.ventanaAnterior = ventanaAnterior;
		this.articulo = articulo;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setTitle("Publicar articulo");
		setBounds(100, 100, 525, 364);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 509, 264);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		contentPanel.add(getLblDOI());
		contentPanel.add(getTxtDOI());
		contentPanel.add(getLblFecha());
		contentPanel.add(getLblVolumen());
		contentPanel.add(getTxtFecha());
		contentPanel.add(getTxtVolumen());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 282, 509, 43);
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton btnPublicar = new JButton("Publicar");
				btnPublicar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						publicarArticulo();
					}
				});
				btnPublicar.setBackground(new Color(50, 205, 50));
				btnPublicar.setForeground(Color.WHITE);
				btnPublicar.setActionCommand("OK");
				buttonPane.add(btnPublicar);
				getRootPane().setDefaultButton(btnPublicar);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setForeground(new Color(255, 255, 255));
				cancelButton.setBackground(new Color(255, 0, 0));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private boolean checkTextFields() {
		if (getTxtDOI().getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "DOI invalido");
			return false;
		}
		if (getTxtFecha().getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Fecha invalida");
			return false;
		}
		if (!isValid(getTxtVolumen().getText().trim())) {
			JOptionPane.showMessageDialog(this, "Volumen invalido");
			return false;
		}
		return true;
	}

	private JLabel getLblDOI() {
		if (lblDOI == null) {
			lblDOI = new JLabel("DOI:");
			lblDOI.setBounds(20, 46, 54, 23);
		}
		return lblDOI;
	}

	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha:");
			lblFecha.setBounds(20, 80, 80, 23);
		}
		return lblFecha;
	}

	private JLabel getLblVolumen() {
		if (lblVolumen == null) {
			lblVolumen = new JLabel("Volumen:");
			lblVolumen.setBounds(10, 114, 90, 23);
		}
		return lblVolumen;
	}

	private JTextField getTxtDOI() {
		if (txtDOI == null) {
			txtDOI = new JTextField();
			txtDOI.setBounds(120, 46, 221, 23);
			txtDOI.setColumns(10);
		}
		return txtDOI;
	}

	private JTextField getTxtFecha() {
		if (txtFecha == null) {
			txtFecha = new JTextField();
			txtFecha.setBounds(120, 80, 221, 23);
			txtFecha.setColumns(10);
		}
		return txtFecha;
	}

	private JTextField getTxtVolumen() {
		if (txtVolumen == null) {
			txtVolumen = new JTextField();
			txtVolumen.setBounds(120, 115, 221, 23);
			txtVolumen.setColumns(10);
		}
		return txtVolumen;
	}

	private boolean isValid(String number) {
		if (isNumeric(number)) {
			if (Integer.parseInt(number) < 0) {
				return false;
			}
			return true;
		}
		return false;
	}

	private void publicarArticulo() {
		if (checkTextFields()) {
			artController.publicarArticulo(articulo, getTxtDOI().getText(), getTxtFecha().getText(),
					Integer.parseInt(getTxtVolumen().getText()));
			JOptionPane.showMessageDialog(this,
					"articulo " + articulo.getTitulo() + "-" + articulo.getPrimerAutor() + " publicado correctamente");
			this.setVisible(false);
			this.ventanaAnterior.disposeComentarioAutorWindow();
			this.dispose();
		}
	}
}
