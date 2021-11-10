package giis.demo.tkrun.views.revisor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.entities.RevisionEntity;

public class RevisionAnteriorView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbEncabezado;
	private JLabel lbComentariosAutor;
	private JScrollPane scrollPane;
	private JTextArea txAutor;
	private JLabel lbComentariosEditor;
	private JScrollPane scrollPane_1;
	private JTextArea txEditor;
	private JLabel lbDecision;
	private JTextField txDecision;
	private JButton btFinalizar;
	private RevisionEntity revision;

	/**
	 * Create the frame.
	 */
	public RevisionAnteriorView(RevisionEntity revision) {
		this.revision = revision;
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 882, 568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbEncabezado());
		contentPane.add(getLbComentariosAutor());
		contentPane.add(getScrollPane());
		contentPane.add(getLbComentariosEditor());
		contentPane.add(getScrollPane_1());
		contentPane.add(getLbDecision());
		contentPane.add(getTxDecision());
		contentPane.add(getBtFinalizar());
	}
	private JLabel getLbEncabezado() {
		if (lbEncabezado == null) {
			lbEncabezado = new JLabel("Vista Revisor: Revisión anterior");
			lbEncabezado.setFont(new Font("Tahoma", Font.ITALIC, 21));
			lbEncabezado.setBounds(268, 22, 367, 26);
		}
		return lbEncabezado;
	}
	private JLabel getLbComentariosAutor() {
		if (lbComentariosAutor == null) {
			lbComentariosAutor = new JLabel("Comentarios Autor:");
			lbComentariosAutor.setForeground(new Color(0, 0, 139));
			lbComentariosAutor.setFont(new Font("Tahoma", Font.BOLD, 15));
			lbComentariosAutor.setBounds(95, 94, 153, 26);
		}
		return lbComentariosAutor;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(258, 96, 591, 115);
			scrollPane.setViewportView(getTxAutor());
		}
		return scrollPane;
	}
	private JTextArea getTxAutor() {
		if (txAutor == null) {
			txAutor = new JTextArea();
			txAutor.setEditable(false);
			txAutor.setFont(new Font("Monospaced", Font.PLAIN, 17));
			txAutor.setText(revision.getComentariosAutor());
			txAutor.setBackground(new Color(240, 255, 255));
		}
		return txAutor;
	}
	private JLabel getLbComentariosEditor() {
		if (lbComentariosEditor == null) {
			lbComentariosEditor = new JLabel("Comentarios Editor:");
			lbComentariosEditor.setForeground(new Color(0, 0, 139));
			lbComentariosEditor.setFont(new Font("Tahoma", Font.BOLD, 15));
			lbComentariosEditor.setBounds(95, 222, 153, 26);
		}
		return lbComentariosEditor;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(258, 222, 591, 115);
			scrollPane_1.setViewportView(getTxEditor());
		}
		return scrollPane_1;
	}
	private JTextArea getTxEditor() {
		if (txEditor == null) {
			txEditor = new JTextArea();
			txEditor.setEditable(false);
			txEditor.setFont(new Font("Monospaced", Font.PLAIN, 17));
			txEditor.setText(revision.getComentariosEditor());
			txEditor.setBackground(new Color(240, 255, 255));
		}
		return txEditor;
	}
	private JLabel getLbDecision() {
		if (lbDecision == null) {
			lbDecision = new JLabel("Decisión Propuesta:");
			lbDecision.setForeground(new Color(0, 0, 139));
			lbDecision.setFont(new Font("Tahoma", Font.BOLD, 15));
			lbDecision.setBounds(95, 368, 153, 26);
		}
		return lbDecision;
	}
	private JTextField getTxDecision() {
		if (txDecision == null) {
			txDecision = new JTextField();
			txDecision.setEditable(false);
			txDecision.setText(revision.getDecision());
			txDecision.setBackground(new Color(240, 255, 255));
			txDecision.setFont(new Font("Monospaced", Font.PLAIN, 17));
			txDecision.setBounds(258, 373, 591, 31);
			txDecision.setColumns(10);
		}
		return txDecision;
	}
	private JButton getBtFinalizar() {
		if (btFinalizar == null) {
			btFinalizar = new JButton("Finalizar revisión anterior");
			btFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btFinalizar.setForeground(new Color(255, 255, 255));
			btFinalizar.setBackground(new Color(139, 0, 0));
			btFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btFinalizar.setBounds(657, 495, 199, 23);
		}
		return btFinalizar;
	}
}
