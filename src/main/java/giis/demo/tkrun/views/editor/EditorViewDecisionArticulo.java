package giis.demo.tkrun.views.editor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.editor.EditorController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class EditorViewDecisionArticulo extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbTitulo;
	private JLabel lbArticulo;
	private JComboBox<ArticuloEntity> chArticulos;
	private JButton btAceptar;
	private JButton btRechazar;
	private JButton btCargar;
	private EditorController controller;
	private List<ArticuloEntity> articulos = new ArrayList<ArticuloEntity>();
	private JButton btComentarios;

	/**
	 * Create the frame.
	 */
	public EditorViewDecisionArticulo(EditorController controller) {
		this.controller = controller;
		inicialice();
	}
	
	private void inicialice() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 916, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbTitulo());
		contentPane.add(getLbArticulo());
		contentPane.add(getChArticulos());
		contentPane.add(getBtAceptar());
		contentPane.add(getBtRechazar());
		contentPane.add(getBtCargar());
		contentPane.add(getBtComentarios());
		setVisible(true);
		setResizable(false);
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
			lbArticulo.setBounds(75, 107, 207, 24);
		}
		return lbArticulo;
	}
	private JComboBox<ArticuloEntity> getChArticulos() {
		if (chArticulos == null) {
			chArticulos = new JComboBox<ArticuloEntity>();
			chArticulos.setBounds(294, 112, 390, 22);
		}
		return chArticulos;
	}
	private JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Aceptar");
			btAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getChArticulos().getItemCount() > 0) {
						ArticuloEntity art = (ArticuloEntity) getChArticulos().getSelectedItem();
						controller.aceptarArticulo(art);
					}
					articulos.clear();
					rellenarComboBox();
				}
			});
			btAceptar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btAceptar.setBackground(new Color(95, 158, 160));
			btAceptar.setForeground(new Color(0, 0, 0));
			btAceptar.setBounds(258, 209, 106, 23);
		}
		return btAceptar;
	}
	private JButton getBtRechazar() {
		if (btRechazar == null) {
			btRechazar = new JButton("Rechazar");
			btRechazar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getChArticulos().getItemCount() > 0) {
						ArticuloEntity art = (ArticuloEntity) getChArticulos().getSelectedItem();
						controller.rechazarArticulo(art);
					}
					articulos.clear();
					rellenarComboBox();
				}
			});
			btRechazar.setForeground(new Color(255, 255, 255));
			btRechazar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btRechazar.setBackground(new Color(165, 42, 42));
			btRechazar.setBounds(422, 209, 106, 23);
		}
		return btRechazar;
	}
	private JButton getBtCargar() {
		if (btCargar == null) {
			btCargar = new JButton("Cargar Articulos");
			btCargar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					articulos = controller.getArticulosTomarDecision();
					rellenarComboBox();
				}
			});
			btCargar.setForeground(Color.BLACK);
			btCargar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btCargar.setBackground(new Color(100, 149, 237));
			btCargar.setBounds(705, 110, 148, 23);
		}
		return btCargar;
	}
	
	private void rellenarComboBox() {
		ArticuloEntity[] vector = new ArticuloEntity[0];
		if (this.articulos.size() > 0) {
			vector = new ArticuloEntity[this.articulos.size()];
			for (int i = 0; i < vector.length; i++) {
				vector[i] = this.articulos.get(i);
			}
		}
		getChArticulos().setModel(new DefaultComboBoxModel<ArticuloEntity>(vector));
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
		if(getChArticulos().getItemCount() > 0) {
			ArticuloEntity art = (ArticuloEntity) getChArticulos().getSelectedItem();
			EditorViewComentariosArticulo ven = new EditorViewComentariosArticulo(art);
			ven.setVisible(true);
			ven.setModal(true);
		}
	}
}

