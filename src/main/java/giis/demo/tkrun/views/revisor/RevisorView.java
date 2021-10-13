package giis.demo.tkrun.views.revisor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisionEntity;
import giis.demo.tkrun.controllers.revisor.RevisorController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class RevisorView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbRevision;
	private JLabel lbId;
	private JTextField txId;
	private JLabel lbArticulo;
	private JComboBox<ArticuloEntity> chArticulos;
	private JButton btSeleccionar;
	private JLabel lbComAutor;
	private JScrollPane scrollPane;
	private JTextPane txAutor;
	private JLabel lbComEditor;
	private JScrollPane scrollPane_1;
	private JTextPane txEditor;
	private JLabel lbDecision;
	private JComboBox<String> chDecision;
	private JButton btEnviar;
	private JButton btGuardarCambios;
	private JButton btVerArticulos;
	private RevisorController controller;
	private List<ArticuloEntity> articulosSinRevisar = new ArrayList<ArticuloEntity>();
	private String idArt;
	private RevisionEntity articuloRevisando;
	private JButton btCancelar;

	/**
	 * Create the frame.
	 */
	public RevisorView(RevisorController controller) {
		this.controller = controller;
		inicialice();
	}
	
	private void inicialice() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 757, 539);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbRevision());
		contentPane.add(getLbId());
		contentPane.add(getTxId());
		contentPane.add(getLbArticulo());
		contentPane.add(getChArticulos());
		contentPane.add(getBtSeleccionar());
		contentPane.add(getLbComAutor());
		contentPane.add(getScrollPane());
		contentPane.add(getLbComEditor());
		contentPane.add(getScrollPane_1());
		contentPane.add(getLbDecision());
		contentPane.add(getChDecision());
		contentPane.add(getBtEnviar());
		contentPane.add(getBtGuardarCambios());
		contentPane.add(getBtVerArticulos());
		contentPane.add(getBtCancelar());
		setVisible(true);
		setResizable(false);
	}
	
	private JLabel getLbRevision() {
		if (lbRevision == null) {
			lbRevision = new JLabel("Vista Revisor:");
			lbRevision.setFont(new Font("Tahoma", Font.ITALIC, 25));
			lbRevision.setBounds(285, 11, 175, 30);
		}
		return lbRevision;
	}
	private JLabel getLbId() {
		if (lbId == null) {
			lbId = new JLabel("Introduzca su ID para ver sus artículos:");
			lbId.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lbId.setBounds(123, 56, 266, 24);
		}
		return lbId;
	}
	private JTextField getTxId() {
		if (txId == null) {
			txId = new JTextField();
			txId.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txId.setBounds(389, 60, 119, 20);
			txId.setColumns(10);
		}
		return txId;
	}
	private JLabel getLbArticulo() {
		if (lbArticulo == null) {
			lbArticulo = new JLabel("Seleccione artículo:");
			lbArticulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lbArticulo.setBounds(123, 91, 132, 24);
		}
		return lbArticulo;
	}
	private JComboBox<ArticuloEntity> getChArticulos() {
		if (chArticulos == null) {
			chArticulos = new JComboBox<ArticuloEntity>();
			chArticulos.setBounds(265, 91, 243, 22);
		}
		return chArticulos;
	}
	private JButton getBtSeleccionar() {
		if (btSeleccionar == null) {
			btSeleccionar = new JButton("Seleccionar");
			btSeleccionar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ArticuloEntity art = (ArticuloEntity) chArticulos.getSelectedItem();
					if(art != null) {
						idArt = art.getIdArticulo();
						if(idArt != null) {
							getTxId().setEnabled(false);
							articuloRevisando = controller.getArticulosSinRevisar(Integer.parseInt(getTxId().getText()), Integer.parseInt(idArt));
							getTxAutor().setText(articuloRevisando.getComentariosAutor());
							getTxEditor().setText(articuloRevisando.getComentariosEditor());
							getBtEnviar().setEnabled(true);
							getBtGuardarCambios().setEnabled(true);
						}
					}
				}
			});
			btSeleccionar.setBackground(new Color(165, 42, 42));
			btSeleccionar.setForeground(new Color(255, 255, 255));
			btSeleccionar.setBounds(515, 91, 132, 23);
		}
		return btSeleccionar;
	}
	private JLabel getLbComAutor() {
		if (lbComAutor == null) {
			lbComAutor = new JLabel("Comentarios Autor:");
			lbComAutor.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lbComAutor.setBounds(10, 161, 132, 24);
		}
		return lbComAutor;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(152, 161, 280, 79);
			scrollPane.setViewportView(getTxAutor());
		}
		return scrollPane;
	}
	private JTextPane getTxAutor() {
		if (txAutor == null) {
			txAutor = new JTextPane();
		}
		return txAutor;
	}
	private JLabel getLbComEditor() {
		if (lbComEditor == null) {
			lbComEditor = new JLabel("Comentarios Editor:");
			lbComEditor.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lbComEditor.setBounds(10, 257, 132, 24);
		}
		return lbComEditor;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(152, 257, 280, 79);
			scrollPane_1.setViewportView(getTxEditor());
		}
		return scrollPane_1;
	}
	private JTextPane getTxEditor() {
		if (txEditor == null) {
			txEditor = new JTextPane();
		}
		return txEditor;
	}
	private JLabel getLbDecision() {
		if (lbDecision == null) {
			lbDecision = new JLabel("Decisión Propuesta:");
			lbDecision.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lbDecision.setBounds(10, 362, 132, 24);
		}
		return lbDecision;
	}
	private JComboBox<String> getChDecision() {
		if (chDecision == null) {
			chDecision = new JComboBox<String>();
			chDecision.setModel(new DefaultComboBoxModel<String>(new String[] {"altamente recomendable aceptar", "aceptado", "poco recomendable aceptar", "rechazar"}));
			chDecision.setBounds(152, 365, 280, 22);
		}
		return chDecision;
	}
	private JButton getBtEnviar() {
		if (btEnviar == null) {
			btEnviar = new JButton("Enviar Revisión al editor");
			btEnviar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(idArt != null && pasaCondiciones()) {
						controller.actualizarRevision(getTxAutor().getText(), getTxEditor().getText(), 
								(String)getChDecision().getSelectedItem(), true, Integer.parseInt(getTxId().getText()), Integer.parseInt(idArt));
						limpiar();
						getBtEnviar().setEnabled(false);
						getBtGuardarCambios().setEnabled(false);
					}
				}
			});
			btEnviar.setEnabled(false);
			btEnviar.setForeground(new Color(255, 255, 255));
			btEnviar.setBackground(new Color(34, 139, 34));
			btEnviar.setBounds(10, 421, 209, 23);
		}
		return btEnviar;
	}
	
	private boolean pasaCondiciones() {
		if(getTxAutor().getText().length() > 100) {
			JOptionPane.showMessageDialog(null, "Debe reducir los comentarios al autor");
			return false;
		}
		if(getTxEditor().getText().length() > 100) {
			JOptionPane.showMessageDialog(null, "Debe reducir los comentarios al editor");
			return false;
		}
		return true;
	}
	
	private void limpiar() {
		getTxId().setEnabled(true);
		articulosSinRevisar.clear();
		rellenarComboBox();
		getTxAutor().setText("");
		getTxEditor().setText("");
		getTxId().setText("");
	}
	
	private JButton getBtGuardarCambios() {
		if (btGuardarCambios == null) {
			btGuardarCambios = new JButton("Guardar Cambios");
			btGuardarCambios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(idArt != null && pasaCondiciones()) {
						controller.actualizarRevision(getTxAutor().getText(), getTxEditor().getText(), 
								(String)getChDecision().getSelectedItem(), false, Integer.parseInt(getTxId().getText()), Integer.parseInt(idArt));
						limpiar();
						getBtEnviar().setEnabled(false);
						getBtGuardarCambios().setEnabled(false);
					}
				}
			});
			btGuardarCambios.setEnabled(false);
			btGuardarCambios.setForeground(new Color(255, 255, 255));
			btGuardarCambios.setBackground(new Color(0, 0, 139));
			btGuardarCambios.setBounds(223, 421, 209, 23);
		}
		return btGuardarCambios;
	}
	private JButton getBtVerArticulos() {
		if (btVerArticulos == null) {
			btVerArticulos = new JButton("Ver Artículos");
			btVerArticulos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					articulosSinRevisar.clear();
					idArt = null;
					if(getTxId().getText().strip().length() > 0)
						articulosSinRevisar = controller.getTituloArticulosSinRevisar(Integer.parseInt(getTxId().getText()));
					rellenarComboBox();
				}
			});
			btVerArticulos.setForeground(Color.WHITE);
			btVerArticulos.setBackground(new Color(165, 42, 42));
			btVerArticulos.setBounds(515, 59, 132, 23);
		}
		return btVerArticulos;
	}
	
	private void rellenarComboBox() {
		ArticuloEntity[] vector = new ArticuloEntity[0];
		if (this.articulosSinRevisar.size() > 0) {
			vector = new ArticuloEntity[this.articulosSinRevisar.size()];
			for (int i = 0; i < vector.length; i++) {
				vector[i] = this.articulosSinRevisar.get(i);
			}
		}
		getChArticulos().setModel(new DefaultComboBoxModel<ArticuloEntity>(vector));
	}
	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("Cancelar");
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					limpiar();
					getBtEnviar().setEnabled(false);
					getBtGuardarCambios().setEnabled(false);
				}
			});
			btCancelar.setForeground(Color.WHITE);
			btCancelar.setBackground(new Color(220, 20, 60));
			btCancelar.setBounds(132, 455, 185, 23);
		}
		return btCancelar;
	}
}
