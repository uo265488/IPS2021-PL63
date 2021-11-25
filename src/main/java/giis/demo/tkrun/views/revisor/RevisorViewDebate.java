package giis.demo.tkrun.views.revisor;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class RevisorViewDebate extends JFrame {

    private JPanel contentPane;
    private JLabel lbMensajes;
    private JScrollPane scrollPane;
    private JTextArea txMensajes;
    private JScrollPane scrollPane_1;
    private JLabel lbEnvio;
    private JButton btEnviar;
    private JLabel lbTitulo;
    private JTextArea txMensajeEnvio;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    RevisorViewDebate frame = new RevisorViewDebate();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     */
    public RevisorViewDebate() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 600, 483);
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
			scrollPane.setBounds(10, 66, 564, 269);
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
			scrollPane_1.setBounds(10, 365, 384, 68);
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
				    getTxMensajeEnvio().setText("Revisor [" + LocalDateTime.now().toString() + "]" + getTxMensajeEnvio().getText());
				    if(getTxMensajeEnvio().getText().length() > 140) {
					JOptionPane.showMessageDialog(null, "Demasiado largo", "Error de tamaño", JOptionPane.ERROR_MESSAGE);
				    }
				    else {
					//controller.enviarMensaje(getTxMensajeEnvio().getText());
					getTxMensajeEnvio().setText("");
					JOptionPane.showMessageDialog(null, "Mensaje enviado");
				    }
				}
			});
			btEnviar.setBackground(new Color(143, 188, 143));
			btEnviar.setBounds(404, 386, 162, 23);
		}
		return btEnviar;
	}
	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("Debate del Artículo: anticulo q sea");
			lbTitulo.setFont(new Font("Tahoma", Font.ITALIC, 17));
			lbTitulo.setBounds(168, 11, 272, 32);
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
