package giis.demo.tkrun.views.revisor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.revisor.RevisorController;
import giis.demo.tkrun.models.dtos.MensajeDto;

public class RevisorViewDebate extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lbMensajes;
    private JScrollPane scrollPane;
    private JTextArea txMensajes;
    private JScrollPane scrollPane_1;
    private JLabel lbEnvio;
    private JButton btEnviar;
    private JLabel lbTitulo;
    private JTextArea txMensajeEnvio;
    private ArticuloEntity articulo;
    private RevisorController controller;
    private String idDebate;
    private List<MensajeDto> mensajes;

    /**
     * Create the frame.
     */
    public RevisorViewDebate(ArticuloEntity articulo) {
	this.articulo = articulo;
	this.controller = new RevisorController();
	idDebate = controller.idDebate(articulo.getIdArticulo());
	actualizarMensajes();
	inicialice();
    }
    private void actualizarMensajes() {
	mensajes = controller.mensajesDebate(idDebate);
	getTxMensajes().setText("");
	if(mensajes.size() == 0)
	    getTxMensajes().setText("No hay ningún mensaje en el debate todavía");
	else {
	    for(MensajeDto mensaje : mensajes)
		getTxMensajes().setText(getTxMensajes().getText() + mensaje.getMensaje() + "\n");
	}
    }
    private void inicialice() {
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 806, 483);
	contentPane = new JPanel();
	contentPane.setBackground(Color.WHITE);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getLbMensajes());
	contentPane.add(getScrollPane());
	contentPane.add(getScrollPane_1());
	contentPane.add(getLbEnvio());
	contentPane.add(getBtEnviar());
	contentPane.add(getLbTitulo());
    }
	private JLabel getLbMensajes() {
		if (lbMensajes == null) {
			lbMensajes = new JLabel("Mensajes del debate:");
			lbMensajes.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lbMensajes.setBounds(10, 41, 162, 23);
		}
		return lbMensajes;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 66, 770, 269);
			scrollPane.setViewportView(getTxMensajes());
		}
		return scrollPane;
	}
	private JTextArea getTxMensajes() {
		if (txMensajes == null) {
			txMensajes = new JTextArea();
			txMensajes.setFont(new Font("Monospaced", Font.PLAIN, 15));
			txMensajes.setBackground(new Color(204, 255, 204));
			txMensajes.setText("No hay mensajes todavía en el debate.");
			txMensajes.setEditable(false);
		}
		return txMensajes;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(10, 365, 598, 68);
			scrollPane_1.setViewportView(getTxMensajeEnvio());
		}
		return scrollPane_1;
	}
	private JLabel getLbEnvio() {
		if (lbEnvio == null) {
			lbEnvio = new JLabel("Escriba su mensaje:");
			lbEnvio.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lbEnvio.setBounds(10, 340, 147, 23);
		}
		return lbEnvio;
	}
	private JButton getBtEnviar() {
		if (btEnviar == null) {
			btEnviar = new JButton("Enviar Mensaje");
			btEnviar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    if(getTxMensajeEnvio().getText().length() > 120) {
					JOptionPane.showMessageDialog(null, "Demasiado largo", "Error de tamaño", JOptionPane.ERROR_MESSAGE);
				    }
				    else {
					LocalDateTime fecha = LocalDateTime.now();
					String minutos = "";
					String hora = "";
					if(fecha.getMinute() < 10)
					    minutos = "0" + fecha.getMinute();
					else
					    minutos = "" + fecha.getMinute();
					if(fecha.getHour() < 10)
					    hora = "0" + fecha.getHour();
					else
					    hora = "" + fecha.getHour();
					getTxMensajeEnvio().setText("Revisor [" + fecha.toLocalDate() + " - " + hora + ":" + minutos + "] - " + getTxMensajeEnvio().getText());
					controller.envioMensaje(idDebate, getTxMensajeEnvio().getText());
					getTxMensajeEnvio().setText("");
					JOptionPane.showMessageDialog(null, "Mensaje enviado correctamente");
					actualizarMensajes();
				    }
				}
			});
			btEnviar.setBackground(new Color(143, 188, 143));
			btEnviar.setBounds(618, 387, 162, 23);
		}
		return btEnviar;
	}
	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("Debate del Artículo: " + articulo.getTitulo());
			lbTitulo.setFont(new Font("Tahoma", Font.ITALIC, 17));
			lbTitulo.setBounds(10, 11, 556, 32);
		}
		return lbTitulo;
	}
	private JTextArea getTxMensajeEnvio() {
		if (txMensajeEnvio == null) {
			txMensajeEnvio = new JTextArea();
		}
		return txMensajeEnvio;
	}
}
