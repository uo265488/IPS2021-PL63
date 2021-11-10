package giis.demo.tkrun.views.revisor;

import java.awt.Color;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisionEntity;
import giis.demo.tkrun.controllers.entities.RevisorEntity;
import giis.demo.tkrun.controllers.revision.RevisionController;

public class ComentariosDeRevisionView extends JDialog {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblRevisor1;
	private JTextField txtRevisor1;
	private JTextField txtRevisor2;
	private JLabel lblRevisor2;

	private RevisionController controller;
	private ArticuloEntity articulo;
	private RevisorEntity revisor;

	/**
	 * Create the frame.
	 */
	public ComentariosDeRevisionView(RevisionController controller, ArticuloEntity articulo, RevisorEntity revisor) {
		this.controller = controller;
		this.articulo = articulo;
		this.revisor = revisor;

		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		getContentPane().add(getLblRevisor1());
		getContentPane().add(getTxtRevisor1());
		getContentPane().add(getTxtRevisor2());
		getContentPane().add(getLblRevisor2());
		setBackground(Color.WHITE);
		setTitle("Comentarios de revision del artículo:");
		setResizable(false);
		setBounds(100, 100, 635, 357);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setComentarios();

	}

	private JLabel getLblRevisor1() {
		if (lblRevisor1 == null) {
			lblRevisor1 = new JLabel("Revisor 1:");
			lblRevisor1.setLabelFor(getTxtRevisor1());
			lblRevisor1.setBounds(43, 36, 200, 14);
		}
		return lblRevisor1;
	}

	private JLabel getLblRevisor2() {
		if (lblRevisor2 == null) {
			lblRevisor2 = new JLabel("Revisor 2:");
			lblRevisor2.setBounds(43, 169, 200, 14);
		}
		return lblRevisor2;
	}

	private JTextField getTxtRevisor1() {
		if (txtRevisor1 == null) {
			txtRevisor1 = new JTextField();
			txtRevisor1.setBorder(new LineBorder(new Color(171, 173, 179)));
			txtRevisor1.setBackground(Color.WHITE);
			txtRevisor1.setEditable(false);
			txtRevisor1.setBounds(43, 62, 522, 96);
			txtRevisor1.setColumns(10);
		}
		return txtRevisor1;
	}

	private JTextField getTxtRevisor2() {
		if (txtRevisor2 == null) {
			txtRevisor2 = new JTextField();
			txtRevisor2.setEditable(false);
			txtRevisor2.setColumns(10);
			txtRevisor2.setBorder(new LineBorder(new Color(171, 173, 179)));
			txtRevisor2.setBackground(Color.WHITE);
			txtRevisor2.setBounds(43, 195, 522, 96);
		}
		return txtRevisor2;
	}

	/**
	 * Displays los comentarios de revision
	 */
	private void setComentarios() {

		if (controller.checkComentariosDeRevisionEnviados(articulo, revisor)) {
			List<RevisionEntity> revisiones = controller.getComentariosDeRevisionDelArticulo(articulo, revisor);

			txtRevisor1.setText(revisiones.get(0).getComentariosEditor());
			txtRevisor2.setText(revisiones.get(1).getComentariosEditor());

		} else {
			JOptionPane.showMessageDialog(this,
					"No has enviado tus comentarios de revisión, por tanto, no puedes ver los de los demás de forma ançonima. ");
			this.dispose();
		}

	}
}
