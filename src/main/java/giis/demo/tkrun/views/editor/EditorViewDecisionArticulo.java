package giis.demo.tkrun.views.editor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.editor.EditorController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class EditorViewDecisionArticulo extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbTitulo;
	private JLabel lbArticulo;
	private JButton btAceptar;
	private JButton btRechazar;
	private EditorController controller;
	private List<ArticuloEntity> articulos = new ArrayList<ArticuloEntity>();
	private JButton btComentarios;
	private JScrollPane scrollPane;
	private JList<ArticuloEntity> listCambiosMenores;
	private DefaultListModel<ArticuloEntity> modeloLista;

	/**
	 * Create the frame.
	 */
	public EditorViewDecisionArticulo(EditorController controller) {
		this.controller = controller;
		inicialice();
	}
	
	private void inicialice() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 916, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbTitulo());
		contentPane.add(getLbArticulo());
		contentPane.add(getBtAceptar());
		contentPane.add(getBtRechazar());
		contentPane.add(getBtComentarios());
		contentPane.add(getScrollPane());
		setVisible(true);
		setResizable(false);
		articulos = controller.getArticulosTomarDecision();
		rellenarLista();
	}
	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("Vista Editor, Decisión Artículo:");
			lbTitulo.setFont(new Font("Tahoma", Font.ITALIC, 25));
			lbTitulo.setBounds(294, 21, 416, 24);
		}
		return lbTitulo;
	}
	private JLabel getLbArticulo() {
		if (lbArticulo == null) {
			lbArticulo = new JLabel("Seleccione el artículo:");
			lbArticulo.setFont(new Font("Tahoma", Font.PLAIN, 19));
			lbArticulo.setBounds(37, 90, 207, 24);
		}
		return lbArticulo;
	}
	private JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Aceptar");
			btAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(listCambiosMenores.getSelectedValuesList().size() > 1)
						JOptionPane.showMessageDialog(null, "Debe seleccionar solo un artículo", "Error al seleccionar artículos", JOptionPane.ERROR_MESSAGE);
					else if(listCambiosMenores.getSelectedValuesList().size() == 0)
						JOptionPane.showMessageDialog(null, "Seleccione un artículo para poder revisarlo", "Error al seleccionar artículos", JOptionPane.ERROR_MESSAGE);
					else {
						ArticuloEntity art = listCambiosMenores.getSelectedValue();
						controller.aceptarArticulo(art);
					}
				}
			});
			btAceptar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btAceptar.setBackground(new Color(95, 158, 160));
			btAceptar.setForeground(new Color(0, 0, 0));
			btAceptar.setBounds(547, 283, 106, 23);
		}
		return btAceptar;
	}
	private JButton getBtRechazar() {
		if (btRechazar == null) {
			btRechazar = new JButton("Rechazar");
			btRechazar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(listCambiosMenores.getSelectedValuesList().size() > 1)
						JOptionPane.showMessageDialog(null, "Debe seleccionar solo un artículo", "Error al seleccionar artículos", JOptionPane.ERROR_MESSAGE);
					else if(listCambiosMenores.getSelectedValuesList().size() == 0)
						JOptionPane.showMessageDialog(null, "Seleccione un artículo para poder revisarlo", "Error al seleccionar artículos", JOptionPane.ERROR_MESSAGE);
					else {
						ArticuloEntity art = listCambiosMenores.getSelectedValue();
						controller.rechazarArticulo(art);
					}
				}
			});
			btRechazar.setForeground(new Color(255, 255, 255));
			btRechazar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btRechazar.setBackground(new Color(165, 42, 42));
			btRechazar.setBounds(547, 348, 106, 23);
		}
		return btRechazar;
	}
	
	private void rellenarLista() {
		modeloLista.clear();
		for(ArticuloEntity art : articulos)
			modeloLista.addElement(art);
	}
	private JButton getBtComentarios() {
		if (btComentarios == null) {
			btComentarios = new JButton("Comentarios");
			btComentarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarComentarios();
				}
			});
			btComentarios.setForeground(Color.BLACK);
			btComentarios.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btComentarios.setBackground(new Color(100, 149, 237));
			btComentarios.setBounds(705, 144, 148, 23);
		}
		return btComentarios;
	}
	
	private void mostrarComentarios() {
		if(listCambiosMenores.getSelectedValuesList().size() > 1)
			JOptionPane.showMessageDialog(null, "Debe seleccionar solo un artículo", "Error al seleccionar artículos", JOptionPane.ERROR_MESSAGE);
		else if(listCambiosMenores.getSelectedValuesList().size() == 0)
			JOptionPane.showMessageDialog(null, "Seleccione un artículo para poder revisarlo", "Error al seleccionar artículos", JOptionPane.ERROR_MESSAGE);
		else {
			ArticuloEntity art = listCambiosMenores.getSelectedValue();
			EditorViewComentariosArticulo ven = new EditorViewComentariosArticulo(art, controller);
			ven.setVisible(true);
			ven.setModal(true);
		}
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(37, 125, 500, 312);
			scrollPane.setViewportView(getListCambiosMenores());
		}
		return scrollPane;
	}
	private JList getListCambiosMenores() {
		if (listCambiosMenores == null) {
			modeloLista = new DefaultListModel<>();
			listCambiosMenores = new JList<ArticuloEntity>(modeloLista);
		}
		return listCambiosMenores;
	}
}

