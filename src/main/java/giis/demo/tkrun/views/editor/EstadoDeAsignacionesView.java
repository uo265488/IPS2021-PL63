
package giis.demo.tkrun.views.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import giis.demo.tkrun.controllers.articulo.ArticuloController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisionEntity;
import giis.demo.tkrun.controllers.revisor.RevisorController;

public class EstadoDeAsignacionesView extends JDialog {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final JPanel contentPanel = new JPanel();
    private JLabel lblPropuestas;
    private JScrollPane scEstados;
    private JTable table;

    private ArticuloController artController = new ArticuloController();
    private RevisorController revController = new RevisorController();
    private JLabel lblReasignacion;
    private JScrollPane scrollPane;
    private JList<ArticuloEntity> listReasignacion;
    private JButton btnReasignar;

    /**
     * Create the dialog.
     */
    public EstadoDeAsignacionesView() {
	setTitle("Estado de las propuestas de revisión:");
	setModal(true);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setBackground(Color.WHITE);
	setResizable(false);
	setBounds(100, 100, 920, 602);
	getContentPane().setLayout(new BorderLayout());
	contentPanel.setBackground(Color.WHITE);
	contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	getContentPane().add(contentPanel, BorderLayout.CENTER);
	contentPanel.setLayout(null);
	contentPanel.add(getLblPropuestas());
	contentPanel.add(getScEstados());
	contentPanel.add(getLblReasignacion());
	contentPanel.add(getScrollPane());
	contentPanel.add(getBtnReasignar());
	{
	    JPanel buttonPane = new JPanel();
	    buttonPane.setBackground(Color.WHITE);
	    buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    getContentPane().add(buttonPane, BorderLayout.SOUTH);
	    {
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
			cerrarVentana();
		    }
		});
		okButton.setBackground(Color.GREEN);
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	    }
	}
    }

    protected void cerrarVentana() {
	this.dispose();

    }

    protected void checkMouseClicked() {
	if (table.isEditing() == false) {
	    JOptionPane.showMessageDialog(this, "You cannot edit the table. ");
	}

    }

    private String[][] fillTableWithData() {
	List<RevisionEntity> revisiones = revController.getAllRevisiones();
	String[][] table = new String[revisiones.size()][3];
	int position = 0;

	for (RevisionEntity r : revisiones) {
	    table[position][0] = revController.getRevisorById(r.getIdRevisor()).toString();
	    table[position][1] = artController.getArticuloById(r.getIdArticulo()).toStringForTable();
	    table[position][2] = r.getEstadoDeLaPropuesta();

	    position++;
	}

	return table;
    }

    private ListModel<ArticuloEntity> getArticulosParaReasignarModel() {
	DefaultListModel<ArticuloEntity> model = new DefaultListModel<ArticuloEntity>();

	for (ArticuloEntity a : artController.getArticulosParaReasignar()) {
	    model.addElement(a);
	}
	return model;
    }

    private JButton getBtnReasignar() {
	if (btnReasignar == null) {
	    btnReasignar = new JButton("Reasignar");
	    btnReasignar.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

		    reasignarArticulo();

		}
	    });
	    btnReasignar.setBackground(Color.GREEN);
	    btnReasignar.setMnemonic('r');
	    btnReasignar.setBounds(718, 394, 116, 40);
	}
	return btnReasignar;
    }

    private JLabel getLblPropuestas() {
	if (lblPropuestas == null) {
	    lblPropuestas = new JLabel("Estado de las propuestas de revisión:");
	    lblPropuestas.setBounds(29, 29, 384, 14);
	}
	return lblPropuestas;
    }

    private JLabel getLblReasignacion() {
	if (lblReasignacion == null) {
	    lblReasignacion = new JLabel("Los siguientes artículos están pendientes de reasignación:");
	    lblReasignacion.setBounds(29, 295, 856, 16);
	}
	return lblReasignacion;
    }

    private JList<ArticuloEntity> getListReasignacion() {
	if (listReasignacion == null) {
	    listReasignacion = new JList<ArticuloEntity>();
	    listReasignacion.setBackground(Color.WHITE);
	    listReasignacion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    listReasignacion.setModel(getArticulosParaReasignarModel());
	}
	return listReasignacion;
    }

    private JScrollPane getScEstados() {
	if (scEstados == null) {
	    scEstados = new JScrollPane();
	    scEstados.setBounds(29, 54, 856, 228);
	    scEstados.setViewportView(getTable());
	}
	return scEstados;
    }

    private JScrollPane getScrollPane() {
	if (scrollPane == null) {
	    scrollPane = new JScrollPane();
	    scrollPane.setBounds(29, 324, 605, 195);
	    scrollPane.setViewportView(getListReasignacion());
	}
	return scrollPane;
    }

    private JTable getTable() {
	if (table == null) {
	    table = new JTable();
	    table.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
		    checkMouseClicked();
		}
	    });
	    table.setModel(
		    new DefaultTableModel(fillTableWithData(), new String[] { "Revisor", "Art\u00EDculo", "Estado" }));
	    table.setFillsViewportHeight(true);
	    table.setBackground(Color.WHITE);
	}
	return table;
    }

    protected void reasignarArticulo() {
	if (listReasignacion.getSelectedIndex() != -1) {
	    EditorAsignarView v = new EditorAsignarView(listReasignacion.getSelectedValue());

	    v.setModal(true);
	    v.setLocationRelativeTo(this);
	    v.setVisible(true);
	} else {
	    JOptionPane.showMessageDialog(this, "Debes seleccionar un articulo para reasignarlo. ");
	}

    }
}
