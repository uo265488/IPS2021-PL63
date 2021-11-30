package giis.demo.tkrun.views.editor;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.articulo.ArticuloController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;

public class EditorViewPublicarArticulo extends JDialog {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

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
    private JTextField txtVolumen;
    private ArticuloController artController;
    private ArticuloEntity articulo;
    private JComboBox<LocalDate> cbFechas;

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
    public EditorViewPublicarArticulo(ArticuloEntity articulo) {
	this.artController = new ArticuloController();
	this.articulo = articulo;
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	getContentPane().setBackground(Color.WHITE);
	setBackground(Color.WHITE);
	setTitle("Editor. Publicar articulo");
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
	contentPanel.add(getTxtVolumen());
	contentPanel.add(getCbFechas());
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
	if (cbFechas.getSelectedIndex() == -1) {
	    JOptionPane.showMessageDialog(this, "Debes seleccionar una fecha. ");
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
	    lblFecha = new JLabel("Fecha(DD/MM/AAAA):");
	    lblFecha.setBounds(20, 80, 134, 23);
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
	    txtDOI.setBounds(177, 46, 221, 23);
	    txtDOI.setColumns(10);
	}
	return txtDOI;
    }

    private JTextField getTxtVolumen() {
	if (txtVolumen == null) {
	    txtVolumen = new JTextField();
	    txtVolumen.setBounds(177, 114, 221, 23);
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
	    artController.publicarArticulo(articulo, getTxtDOI().getText(), getCbFechas().getSelectedItem().toString(),
		    Integer.parseInt(getTxtVolumen().getText()));
	    JOptionPane.showMessageDialog(this,
		    "articulo " + articulo.getTitulo() + "-" + articulo.getPrimerAutor() + " publicado correctamente");
	    this.setVisible(false);
	    this.dispose();
	}
    }

    private JComboBox<LocalDate> getCbFechas() {
	if (cbFechas == null) {
	    cbFechas = new JComboBox<LocalDate>();
	    cbFechas.setBounds(177, 80, 221, 23);
	    cbFechas.setModel(generarComboBoxModel());
	}
	return cbFechas;
    }

    private ComboBoxModel<LocalDate> generarComboBoxModel() {
	DefaultComboBoxModel<LocalDate> model = new DefaultComboBoxModel<>();

	// model.addAll(generarFechas());
	List<LocalDate> fechas = generarFechas();
	for (LocalDate fecha : fechas) {
	    model.addElement(fecha);
	}
	return model;
    }

    /**
     * Genera una lista de fechas
     *
     * @return
     */
    private List<LocalDate> generarFechas() {
	LocalDate start = LocalDate.now();
	LocalDate end = LocalDate.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
	return Stream.iterate(start, date -> date.plusDays(1)).limit(ChronoUnit.DAYS.between(start, end))
		.collect(Collectors.toList());
    }
}
