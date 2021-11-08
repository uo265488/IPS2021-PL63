package giis.demo.tkrun.views.editor;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.articulo.ArticuloController;
import giis.demo.tkrun.controllers.editor.EditorController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisionEntity;
import giis.demo.tkrun.models.articulo.ArticuloModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditorViewEnviarComentarios extends JDialog {
	
	private static final String PUBLICAR = "Publicar";
	private static final String ACEPTAR = "Aceptar";
	private static final String ACEPTAR_CAMBIOS_MENORES = "Aceptar con cambios menores";
	private static final String ACEPTAR_CAMBIOS_MAYORES = "Aceptar con cambios mayores";
	private static final String RECHAZAR = "Rechazar";

	private final JPanel contentPanel = new JPanel();
	private List<RevisionEntity> revisiones;
	private ArticuloEntity articulo;
	private ArticuloController artController;
	private JLabel lblComentariosAutor;
	private JTextArea txtComentariosRevisiones;
	private JLabel lblDecision;
	private JRadioButton rdBtnAceptarCambiosMayores;
	private JRadioButton rdBtnAceptar;
	private JRadioButton rdBtnRechazar;
	private JTextArea txtComentarios;
	private JLabel lblComentariosDecision;
	private EditorViewVerComentarios comentariosView;
	private ButtonGroup decisiones;
	private JRadioButton rdBtnAceptarCambiosMenores;
	private EditorController controller;


	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			EditorViewComentariosAutor dialog = new EditorViewComentariosAutor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public EditorViewEnviarComentarios( ArticuloEntity articulo, EditorController controller, EditorViewVerComentarios comentariosView) {
		getContentPane().setBackground(Color.WHITE);
		this.revisiones = controller.getRevisionesFiltradas(articulo.getIdArticulo(), (articulo.getVecesRevisado() +1));
		this.articulo = articulo;
		this.controller = controller;
		this.artController = new ArticuloController();
		this.comentariosView = comentariosView;
		setTitle("Enviar comentarios al autor");
		setBounds(100, 100, 680, 509);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 664, 400);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		contentPanel.add(getLblComentariosAutor());
		contentPanel.add(getTxtComentariosRevisiones());
		contentPanel.add(getLblDecision());
		contentPanel.add(getRdBtnAceptarCambiosMayores());
		contentPanel.add(getRdBtnAceptar());
		contentPanel.add(getRdBtnRechazar());
		agruparButtons();
		
		contentPanel.add(getTxtComentarios());
		contentPanel.add(getLblComentariosDecision());
		contentPanel.add(getRdBtnAceptarCambiosMenores());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 411, 654, 48);
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton btnEnviar = new JButton("Enviar");
				btnEnviar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						enviarDecision();
					}
				});
				btnEnviar.setForeground(new Color(255, 255, 255));
				btnEnviar.setBackground(new Color(50, 205, 50));
				btnEnviar.setActionCommand("OK");
				buttonPane.add(btnEnviar);
				getRootPane().setDefaultButton(btnEnviar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setForeground(new Color(255, 255, 255));
				btnCancelar.setBackground(new Color(255, 0, 0));
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
	
	/**
	 * Envia la carta de decision con la decision y revisiones al autor si el articulo es aceptado o rechazado,
	 * o publica el articulo directamente 
	 * Dependiendo de la opcion seleccionada cambia el estado del articulo a PUBLICADO, ACEPTADO o RECHAZADO
	 */
	private void enviarDecision() {
		switch (decisiones.getSelection().getActionCommand()) {
		case PUBLICAR: {publicarArticulo(); break;}
		case ACEPTAR_CAMBIOS_MENORES:{enviarCartaDecision(ArticuloEntity.ACEPTADO_CAMBIOS_MENORES); break;}
		case ACEPTAR_CAMBIOS_MAYORES:{enviarCartaDecision(ArticuloEntity.ACEPTADO_CAMBIOS_MAYORES); break;}
		case ACEPTAR: {enviarCartaDecision(ArticuloEntity.ACEPTADO); break;}
		case RECHAZAR: { enviarCartaDecision(ArticuloEntity.RECHAZADO); break;}
		}
	}
	
	private void publicarArticulo() {
		EditorViewPublicarArticulo ventanaPublicarArticulo = new EditorViewPublicarArticulo(this, this.articulo, controller);
		ventanaPublicarArticulo.setModal(true);
		ventanaPublicarArticulo.setLocationRelativeTo(this);
		ventanaPublicarArticulo.setVisible(true);
	}
	
	private void enviarCartaDecision(String nuevoEstado) {
		artController.enviarDecision(articulo, nuevoEstado);
		JOptionPane.showMessageDialog(this, articulo.getCartaDecision() + " enviada correctamente");
		this.setVisible(false);
		comentariosView.setVisible(false);
		this.dispose();
		comentariosView.dispose();
	}
	private JLabel getLblComentariosAutor() {
		if (lblComentariosAutor == null) {
			lblComentariosAutor = new JLabel("Comentarios al autor:");
			lblComentariosAutor.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblComentariosAutor.setBounds(10, 11, 152, 29);
		}
		return lblComentariosAutor;
	}
	private JTextArea getTxtComentariosRevisiones() {
		if (txtComentariosRevisiones == null) {
			txtComentariosRevisiones = new JTextArea();
			txtComentariosRevisiones.setBorder(new LineBorder(new Color(0, 0, 0)));
			txtComentariosRevisiones.setEditable(false);
			txtComentariosRevisiones.setBounds(10, 42, 644, 201);
			setTxtComentariosRevisores();
		}
		return txtComentariosRevisiones;
	}
	
	private void setTxtComentariosRevisores() {
		String text = "";
		int iterator = 1;
		for (RevisionEntity rev : revisiones) {
			text += "Revision " + iterator + ": \n" + rev.getComentariosAutor() +"\n\n";
		}
		getTxtComentariosRevisiones().setText(text);
	}
	private JLabel getLblDecision() {
		if (lblDecision == null) {
			lblDecision = new JLabel("Decision:");
			lblDecision.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblDecision.setBounds(10, 254, 141, 19);
		}
		return lblDecision;
	}
	private JRadioButton getRdBtnAceptarCambiosMayores() {
		if (rdBtnAceptarCambiosMayores == null) {
			rdBtnAceptarCambiosMayores = new JRadioButton("Aceptar con cambios mayores");
			rdBtnAceptarCambiosMayores.setActionCommand(ACEPTAR_CAMBIOS_MAYORES);
			rdBtnAceptarCambiosMayores.setBackground(Color.WHITE);
			rdBtnAceptarCambiosMayores.setBounds(319, 274, 207, 23);
			rdBtnAceptarCambiosMayores.setEnabled(isPrimeraRevision());
		}
		return rdBtnAceptarCambiosMayores;
	}
	private JRadioButton getRdBtnAceptar() {
		if (rdBtnAceptar == null) {
			rdBtnAceptar = new JRadioButton(ACEPTAR);
			rdBtnAceptar.setSelected(true);
			rdBtnAceptar.setActionCommand(ACEPTAR);
			rdBtnAceptar.setBackground(Color.WHITE);
			rdBtnAceptar.setBounds(20, 274, 83, 23);
		}
		return rdBtnAceptar;
	}

	private JRadioButton getRdBtnRechazar() {
		if (rdBtnRechazar == null) {
			rdBtnRechazar = new JRadioButton(RECHAZAR);
			rdBtnRechazar.setActionCommand(RECHAZAR);
			rdBtnRechazar.setBackground(Color.WHITE);
			rdBtnRechazar.setBounds(528, 274, 92, 23);
		}
		return rdBtnRechazar;
	}
	
	private void  agruparButtons() {
		decisiones = new ButtonGroup();
		decisiones.add(getRdBtnAceptarCambiosMayores());
		decisiones.add(getRdBtnAceptar());
		decisiones.add(getRdBtnRechazar());
	}
	private JTextArea getTxtComentarios() {
		if (txtComentarios == null) {
			txtComentarios = new JTextArea();
			txtComentarios.setBorder(new LineBorder(new Color(0, 0, 0)));
			txtComentarios.setBounds(10, 331, 644, 58);
		}
		return txtComentarios;
	}
	private JLabel getLblComentariosDecision() {
		if (lblComentariosDecision == null) {
			lblComentariosDecision = new JLabel("Comentarios de la decision:");
			lblComentariosDecision.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblComentariosDecision.setBounds(10, 304, 186, 19);
		}
		return lblComentariosDecision;
	}
	
	public void disposeComentarioAutorWindow() {
		this.comentariosView.dispose();
		this.dispose();
	}
	private boolean isPrimeraRevision() {
		for (RevisionEntity revision : revisiones) {
			if (revision.getNumeroRevision() > 1) {
				return false;
			}
		}
		return true;
	}
	private JRadioButton getRdBtnAceptarCambiosMenores() {
		if (rdBtnAceptarCambiosMenores == null) {
			rdBtnAceptarCambiosMenores = new JRadioButton("Aceptar con cambios menores");
			rdBtnAceptarCambiosMenores.setBackground(Color.WHITE);
			rdBtnAceptarCambiosMenores.setActionCommand(ACEPTAR_CAMBIOS_MENORES);
			rdBtnAceptarCambiosMenores.setBounds(110, 274, 207, 23);
			rdBtnAceptarCambiosMenores.setEnabled(isPrimeraRevision());
		}
		return rdBtnAceptarCambiosMenores;
	}
}
