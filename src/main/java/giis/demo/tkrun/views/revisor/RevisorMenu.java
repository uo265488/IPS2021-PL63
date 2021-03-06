package giis.demo.tkrun.views.revisor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import giis.demo.tkrun.controllers.revisor.RevisorController;

public class RevisorMenu extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String id_revisor;
    private JButton btnNewButton;
    private JButton btnNewButton_1;
    private JButton btnNewButton_1_1;
    private RevisorController revisorController;
    private JButton btDebates;
//    /**
//     * Launch the application.
//     */
//    public static void main(String[] args) {
//	try {
//	    RevisorMenu dialog = new RevisorMenu();
//	    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//	    dialog.setVisible(true);
//	} catch (Exception e) {
//	    e.printStackTrace();
//	}
//    }

    /**
     * Create the dialog.
     */
    public RevisorMenu(String id_revisor, RevisorController controller) {
	setTitle("Revisor: " + controller.getRevisorById(id_revisor).getNombre() + ". Menu");
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	this.id_revisor = id_revisor;
	this.revisorController = controller;
	setResizable(false);
	setBounds(100, 100, 401, 360);
	getContentPane().setLayout(null);
	getContentPane().add(getBtnNewButton());
	getContentPane().add(getBtnNewButton_1());
	getContentPane().add(getBtnNewButton_1_1());
	getContentPane().add(getBtDebates());
    }

    private JButton getBtnNewButton() {
	if (btnNewButton == null) {
	    btnNewButton = new JButton("Ver articulos revisados");
	    btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    RevisorAsignadosView rav = new RevisorAsignadosView(revisorController, id_revisor);
		    rav.setVisible(true);
		}
	    });
	    btnNewButton.setBounds(79, 42, 206, 30);
	}
	return btnNewButton;
    }

    private JButton getBtnNewButton_1() {
	if (btnNewButton_1 == null) {
	    btnNewButton_1 = new JButton("Ver articulos pendientes");
	    btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    RevisorDecisionRevisionesEncomendadas vista = new RevisorDecisionRevisionesEncomendadas(
			    revisorController, id_revisor);
		    vista.setVisible(true);
		}
	    });
	    btnNewButton_1.setBounds(79, 114, 206, 30);
	}
	return btnNewButton_1;
    }

    private JButton getBtnNewButton_1_1() {
	if (btnNewButton_1_1 == null) {
	    btnNewButton_1_1 = new JButton("Revisar articulos");
	    btnNewButton_1_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    RevisorView rv = new RevisorView(revisorController, id_revisor);
		    rv.setVisible(true);
		}
	    });
	    btnNewButton_1_1.setBounds(79, 186, 206, 30);
	}
	return btnNewButton_1_1;
    }

    private JButton getBtDebates() {
	if (btDebates == null) {
	    btDebates = new JButton("Debates Actuales");
	    btDebates.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    RevisorViewArticulosEnDebate rvd = new RevisorViewArticulosEnDebate(revisorController, id_revisor);
		    rvd.setModal(true);
		    rvd.setVisible(true);
		}
	    });
	    btDebates.setBounds(79, 250, 206, 30);
	}
	return btDebates;
    }
}
