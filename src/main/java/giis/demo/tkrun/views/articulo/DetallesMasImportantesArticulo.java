package giis.demo.tkrun.views.articulo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetallesMasImportantesArticulo extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private ArticuloEntity articulo;
	private JTextPane txResumen;
	private JLabel lbPalabrasClave;
	private JScrollPane scrollPane_1;
	private JTextPane txPalabrasClave;
	private JButton btFinalizarVista;
	@SuppressWarnings("unused")
	private String fecha;

	/**
	 * Create the dialog.
	 */
	public DetallesMasImportantesArticulo(ArticuloEntity art, String fecha) {
		setTitle("Detalles Artículo Revisor");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.fecha = fecha;
		this.articulo = art;
		setBounds(100, 100, 685, 539);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbTitulo = new JLabel("Título: ");
			lbTitulo.setForeground(new Color(0, 0, 128));
			lbTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lbTitulo.setBounds(12, 72, 109, 25);
			contentPanel.add(lbTitulo);
		}
		{
			JLabel lbEncabezado = new JLabel("Detalles Artículo:");
			lbEncabezado.setFont(new Font("Tahoma", Font.ITALIC, 21));
			lbEncabezado.setBounds(234, 11, 179, 25);
			contentPanel.add(lbEncabezado);
		}
		{
			JLabel lnTituloContenido = new JLabel(articulo.getTitulo());
			lnTituloContenido.setBackground(new Color(255, 248, 220));
			lnTituloContenido.setFont(new Font("Tahoma", Font.BOLD, 15));
			lnTituloContenido.setBounds(151, 72, 508, 22);
			contentPanel.add(lnTituloContenido);
		}
		{
			JLabel lbResumen = new JLabel("Resumen:");
			lbResumen.setForeground(new Color(0, 0, 128));
			lbResumen.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lbResumen.setBounds(12, 108, 109, 25);
			contentPanel.add(lbResumen);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(151, 108, 508, 151);
			contentPanel.add(scrollPane);
			scrollPane.setViewportView(getTxResumen());
		}
		contentPanel.add(getLbPalabrasClave());
		contentPanel.add(getScrollPane_1());
		contentPanel.add(getBtFinalizarVista());
		{
			JLabel lbFecha = new JLabel("Fecha Máxima: ");
			lbFecha.setForeground(new Color(0, 0, 128));
			lbFecha.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lbFecha.setBounds(12, 376, 129, 25);
			contentPanel.add(lbFecha);
		}
		{
			JLabel lbFechaContenido = new JLabel(fecha);
			lbFechaContenido.setFont(new Font("Tahoma", Font.BOLD, 15));
			lbFechaContenido.setBackground(new Color(255, 248, 220));
			lbFechaContenido.setBounds(151, 376, 508, 22);
			contentPanel.add(lbFechaContenido);
		}
	}
	private JTextPane getTxResumen() {
		if (txResumen == null) {
			txResumen = new JTextPane();
			txResumen.setText(articulo.getResumen());
			txResumen.setBackground(new Color(255, 248, 220));
			txResumen.setFont(new Font("Tahoma", Font.BOLD, 13));
			txResumen.setEditable(false);
		}
		return txResumen;
	}
	private JLabel getLbPalabrasClave() {
		if (lbPalabrasClave == null) {
			lbPalabrasClave = new JLabel("Palabras Clave:");
			lbPalabrasClave.setForeground(new Color(0, 0, 128));
			lbPalabrasClave.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lbPalabrasClave.setBounds(12, 275, 129, 25);
		}
		return lbPalabrasClave;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(151, 270, 508, 77);
			scrollPane_1.setViewportView(getTxPalabrasClave());
		}
		return scrollPane_1;
	}
	private JTextPane getTxPalabrasClave() {
		if (txPalabrasClave == null) {
			txPalabrasClave = new JTextPane();
			txPalabrasClave.setText(articulo.getPalabrasClave());
			txPalabrasClave.setFont(new Font("Tahoma", Font.BOLD, 13));
			txPalabrasClave.setEditable(false);
			txPalabrasClave.setBackground(new Color(255, 248, 220));
		}
		return txPalabrasClave;
	}
	private JButton getBtFinalizarVista() {
		if (btFinalizarVista == null) {
			btFinalizarVista = new JButton("Finalizar Vista");
			btFinalizarVista.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btFinalizarVista.setBackground(new Color(139, 0, 0));
			btFinalizarVista.setForeground(new Color(255, 255, 255));
			btFinalizarVista.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btFinalizarVista.setBounds(530, 466, 129, 23);
		}
		return btFinalizarVista;
	}
}
