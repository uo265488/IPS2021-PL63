package giis.demo.tkrun.views.editor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.editor.EditorController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.MensajeEntity;

public class EditorDebateView extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JLabel lblMensajes;
    private JScrollPane spMensajes;
    private JTextArea txtMensajes;
    private JScrollPane spEnviarMensajes;
    private JLabel lblEnvio;
    private JButton btnEnviar;
    private JLabel lblTitulo;
    private JTextArea txtMensajeEnvio;
    private ArticuloEntity articulo;
    private EditorController controller;

    /**
     * Create the frame.
     */
    public EditorDebateView(ArticuloEntity articulo, EditorController controller) {
    this.articulo = articulo;
    this.controller = controller;
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 600, 483);
	contentPane = new JPanel();
	contentPane.setBackground(Color.WHITE);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getLblMensajes());
	contentPane.add(getSpMensajes());
	contentPane.add(getSpEnviarMensajes());
	contentPane.add(getLblEnvio());
	contentPane.add(getBtnEnviar());
	contentPane.add(getLblTitulo());
    }
    
    private void setTxtMensajes() {
    	List<MensajeEntity> mensajes = controller.getMensajesDebate(articulo.getIdArticulo());
    	String text = "";
    	for (MensajeEntity mensaje : mensajes) {
    		text+= mensaje.getMensaje() + "\n";
    	}
    	getTxtMensajes().setText(text);
    }
	private JLabel getLblMensajes() {
		if (lblMensajes == null) {
			lblMensajes = new JLabel("Mensajes del debate:");
			lblMensajes.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblMensajes.setBounds(10, 41, 162, 23);
		}
		return lblMensajes;
	}
	private JScrollPane getSpMensajes() {
		if (spMensajes == null) {
			spMensajes = new JScrollPane();
			spMensajes.setBounds(10, 66, 564, 269);
			spMensajes.setViewportView(getTxtMensajes());
		}
		return spMensajes;
	}
	private JTextArea getTxtMensajes() {
		if (txtMensajes == null) {
			txtMensajes = new JTextArea();
			txtMensajes.setFont(new Font("Monospaced", Font.PLAIN, 15));
			txtMensajes.setBackground(new Color(204, 255, 204));
			setTxtMensajes();
			txtMensajes.setEditable(false);
		}
		return txtMensajes;
	}
	private JScrollPane getSpEnviarMensajes() {
		if (spEnviarMensajes == null) {
			spEnviarMensajes = new JScrollPane();
			spEnviarMensajes.setBounds(10, 365, 384, 68);
			spEnviarMensajes.setViewportView(getTxtMensajeEnvio());
		}
		return spEnviarMensajes;
	}
	private JLabel getLblEnvio() {
		if (lblEnvio == null) {
			lblEnvio = new JLabel("Escriba su mensaje:");
			lblEnvio.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblEnvio.setBounds(10, 340, 147, 23);
		}
		return lblEnvio;
	}
	private JButton getBtnEnviar() {
		if (btnEnviar == null) {
			btnEnviar = new JButton("Enviar Mensaje");
			btnEnviar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					LocalDateTime fecha = LocalDateTime.now();
					DateTimeFormatter isoFecha = DateTimeFormatter.ISO_LOCAL_DATE;
					DateTimeFormatter isoHora = DateTimeFormatter.ISO_LOCAL_TIME;
					String mensaje = "Editor ["  + fecha.format(isoFecha) + " - " + fecha.format(isoHora) + "] - " + getTxtMensajeEnvio().getText();
				    if(mensaje.length() > 140) {
					JOptionPane.showMessageDialog(null, "Demasiado largo", "Error de tamaño", JOptionPane.ERROR_MESSAGE);
				    }
				    else if (getTxtMensajeEnvio().getText().trim().equals("")) {
				    	JOptionPane.showMessageDialog(null, "Por favor introduce un mensaje", "Error de tamaño", JOptionPane.ERROR_MESSAGE);
				    }
				    else {
					controller.enviarMensaje(articulo.getIdArticulo(), mensaje);
					getTxtMensajeEnvio().setText("");
					JOptionPane.showMessageDialog(null, "Mensaje enviado");
					setTxtMensajes();
				    }
				}
			});
			btnEnviar.setBackground(new Color(143, 188, 143));
			btnEnviar.setBounds(404, 386, 162, 23);
		}
		return btnEnviar;
	}
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Debate del Artículo: " + articulo.getTitulo());
			lblTitulo.setFont(new Font("Tahoma", Font.ITALIC, 17));
			lblTitulo.setBounds(168, 11, 272, 32);
		}
		return lblTitulo;
	}
	private JTextArea getTxtMensajeEnvio() {
		if (txtMensajeEnvio == null) {
			txtMensajeEnvio = new JTextArea();
		}
		return txtMensajeEnvio;
	}
}
