package giis.demo.tkrun.views.revisor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.revisor.RevisorController;

public class RevisorAsignadosView extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private RevisorController revisorController;
    private List<ArticuloEntity> articulosAsignados;
    private JLabel lbArticulosAsignados;
    private JComboBox<ArticuloEntity> cmBoxArticulosAsigados;
    private JButton btnRevisar;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EditorAsignadosView frame = new EditorAsignadosView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

    public RevisorAsignadosView(RevisorController revisorController, int idRevisor) {
	setResizable(false);
	setTitle("Revisor. Articulos asignados");
	this.revisorController = revisorController;
	articulosAsignados = this.revisorController.getArticulosAsignados(idRevisor);
	initialize();
    }

    /**
     * Create the frame.
     */
    public void initialize() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 413, 228);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getLbArticulosAsignados());
	contentPane.add(getCmBoxArticulosAsigados());
	contentPane.add(getBtnRevisar());
    }

    private JLabel getLbArticulosAsignados() {
	if (lbArticulosAsignados == null) {
	    lbArticulosAsignados = new JLabel("Articulos asignados para revisar:");
	    lbArticulosAsignados.setLabelFor(getCmBoxArticulosAsigados());
	    lbArticulosAsignados.setDisplayedMnemonic('A');
	    lbArticulosAsignados.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lbArticulosAsignados.setBounds(38, 49, 205, 25);
	}
	return lbArticulosAsignados;
    }

    private JComboBox<ArticuloEntity> getCmBoxArticulosAsigados() {
	if (cmBoxArticulosAsigados == null) {
	    cmBoxArticulosAsigados = new JComboBox<ArticuloEntity>();
	    cmBoxArticulosAsigados.setBounds(38, 85, 205, 25);
	    setComboBoxModel();
	}
	return cmBoxArticulosAsigados;
    }

    private JButton getBtnRevisar() {
	if (btnRevisar == null) {
	    btnRevisar = new JButton("Revisar");
	    btnRevisar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	    });
	    btnRevisar.setForeground(new Color(255, 255, 255));
	    btnRevisar.setBackground(new Color(0, 100, 0));
	    btnRevisar.setMnemonic('R');
	    btnRevisar.setBounds(284, 86, 89, 23);
	}
	return btnRevisar;
    }

    private void setComboBoxModel() {
	ArticuloEntity[] articulosEntity = new ArticuloEntity[articulosAsignados.size()];
	for (int i = 0; i < articulosEntity.length; i++) {
	    articulosEntity[i] = articulosAsignados.get(i);
	}

<<<<<<< HEAD
	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 413, 228);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbArticulosAsignados());
		contentPane.add(getCmBoxArticulosAsigados());
		contentPane.add(getBtnRevisar());
	}

	private JLabel getLbArticulosAsignados() {
		if (lbArticulosAsignados == null) {
			lbArticulosAsignados = new JLabel("Articulos asignados para revisar:");
			lbArticulosAsignados.setLabelFor(getCmBoxArticulosAsigados());
			lbArticulosAsignados.setDisplayedMnemonic('A');
			lbArticulosAsignados.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lbArticulosAsignados.setBounds(38, 49, 205, 25);
		}
		return lbArticulosAsignados;
	}
	private JComboBox<ArticuloEntity> getCmBoxArticulosAsigados() {
		if (cmBoxArticulosAsigados == null) {
			cmBoxArticulosAsigados = new JComboBox<ArticuloEntity>();
			cmBoxArticulosAsigados.setBounds(38, 85, 205, 25);
			setComboBoxModel();
		}
		return cmBoxArticulosAsigados;
	}
	private JButton getBtnRevisar() {
		if (btnRevisar == null) {
			btnRevisar = new JButton("Revisar");
			btnRevisar.setForeground(new Color(255, 255, 255));
			btnRevisar.setBackground(new Color(0, 100, 0));
			btnRevisar.setMnemonic('R');
			btnRevisar.setBounds(284, 86, 89, 23);
		}
		return btnRevisar;
	}
	
	private void setComboBoxModel() {
		ArticuloEntity[] articulosEntity = new ArticuloEntity[articulosAsignados.size()]; 
		for(int i = 0; i < articulosEntity.length; i++) {
			articulosEntity[i] = articulosAsignados.get(i);
		}
		
		getCmBoxArticulosAsigados().setModel(new DefaultComboBoxModel<ArticuloEntity>(articulosEntity));
	}
=======
	getCmBoxArticulosAsigados().setModel(new DefaultComboBoxModel<ArticuloEntity>(articulosEntity));
    }
>>>>>>> branch 'master' of https://github.com/uo265488/IPS2021-PL63.git
}
