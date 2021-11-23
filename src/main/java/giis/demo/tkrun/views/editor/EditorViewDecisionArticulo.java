package giis.demo.tkrun.views.editor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.articulo.ArticuloController;
import giis.demo.tkrun.controllers.editor.EditorController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.views.articulo.VisualizarArticuloView;
import javax.swing.ListSelectionModel;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.ButtonGroup;

public class EditorViewDecisionArticulo extends JDialog {

	/**
	 * 
	 */
	private static final String ACEPTAR = "Aceptar";
	private static final String ACEPTAR_CAMBIOS_MENORES = "Aceptar con cambios menores";
	private static final String ACEPTAR_CAMBIOS_MAYORES = "Aceptar con cambios mayores";
	private static final String RECHAZAR = "Rechazar";

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbTitulo;
	private JLabel lbArticulo;
	private EditorController controller;
	private ArticuloController articuloCont;
	private List<ArticuloEntity> articulos = new ArrayList<ArticuloEntity>();
	private JButton btComentarios;
	private JScrollPane scrollPane;
	private JList<ArticuloEntity> listCambiosMenores;
	private DefaultListModel<ArticuloEntity> modeloLista;
	private JButton btArticulo;
	private JLabel lbComentarios;
	private JScrollPane scrollPane_1;
	private JTextPane txComentarios;
	private JLabel lbDecision;
	private JRadioButton rdBtnAceptar;
	private JRadioButton rdBtnAceptarConCambiosMenores;
	private JRadioButton rdBtnAceptarConCambiosMayores;
	private JRadioButton rdBtnrechazar;
	private JButton btDecision;
	private ButtonGroup decisiones;

	/**
	 * Create the frame.
	 */
	public EditorViewDecisionArticulo(EditorController controller) {
		setTitle("Vista Editor: Decisión Artículos");
		this.controller = controller;
		this.articuloCont = new ArticuloController();
		inicialice();
	}

	private void inicialice() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1254, 614);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbTitulo());
		contentPane.add(getLbArticulo());
		contentPane.add(getBtComentarios());
		contentPane.add(getScrollPane());
		contentPane.add(getBtArticulo());
		contentPane.add(getLbComentarios());
		contentPane.add(getScrollPane_1());
		contentPane.add(getLbDecision());
		contentPane.add(getRdBtnAceptar());
		contentPane.add(getRdBtnAceptarConCambiosMenores());
		contentPane.add(getRdBtnAceptarConCambiosMayores());
		contentPane.add(getRdBtnrechazar());
		contentPane.add(getBtDecision());
		setVisible(true);
		setResizable(false);
		articulos = controller.getArticulosTomarDecision();
		rellenarLista();
		agruparButtons();
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

	private void rellenarLista() {
		modeloLista.clear();
		for (ArticuloEntity art : articulos)
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
			btComentarios.setBounds(839, 145, 163, 23);
		}
		return btComentarios;
	}

	private void mostrarComentarios() {
		if (listCambiosMenores.getSelectedValuesList().size() > 1)
			JOptionPane.showMessageDialog(null, "Debe seleccionar solo un artículo", "Error al seleccionar artículos",
					JOptionPane.ERROR_MESSAGE);
		else if (listCambiosMenores.getSelectedValuesList().size() == 0)
			JOptionPane.showMessageDialog(null, "Seleccione un artículo para poder revisarlo",
					"Error al seleccionar artículos", JOptionPane.ERROR_MESSAGE);
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
			scrollPane.setBounds(37, 125, 582, 439);
			scrollPane.setViewportView(getListCambiosMenores());
		}
		return scrollPane;
	}

	private JList getListCambiosMenores() {
		if (listCambiosMenores == null) {
			modeloLista = new DefaultListModel<>();
			listCambiosMenores = new JList<ArticuloEntity>(modeloLista);
			listCambiosMenores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return listCambiosMenores;
	}

	private JButton getBtArticulo() {
		if (btArticulo == null) {
			btArticulo = new JButton("Visualizar Articulo");
			btArticulo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (listCambiosMenores.getSelectedValuesList().size() > 1)
						JOptionPane.showMessageDialog(null, "Debe seleccionar solo un artículo",
								"Error al seleccionar artículos", JOptionPane.ERROR_MESSAGE);
					else if (listCambiosMenores.getSelectedValuesList().size() == 0)
						JOptionPane.showMessageDialog(null, "Seleccione un artículo para poder revisarlo",
								"Error al seleccionar artículos", JOptionPane.ERROR_MESSAGE);
					else {
						ArticuloEntity art = listCambiosMenores.getSelectedValue();
						VisualizarArticuloView vista = new VisualizarArticuloView(art);
						vista.setVisible(true);
						vista.setModal(true);
					}
				}
			});
			btArticulo.setForeground(Color.BLACK);
			btArticulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btArticulo.setBackground(new Color(255, 228, 196));
			btArticulo.setBounds(629, 145, 163, 23);
		}
		return btArticulo;
	}

	private JLabel getLbComentarios() {
		if (lbComentarios == null) {
			lbComentarios = new JLabel("Comentarios al autor:");
			lbComentarios.setFont(new Font("Tahoma", Font.BOLD, 17));
			lbComentarios.setBounds(629, 191, 221, 24);
		}
		return lbComentarios;
	}

	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(629, 226, 599, 182);
			scrollPane_1.setViewportView(getTxComentarios());
		}
		return scrollPane_1;
	}

	private JTextPane getTxComentarios() {
		if (txComentarios == null) {
			txComentarios = new JTextPane();
		}
		return txComentarios;
	}

	private JLabel getLbDecision() {
		if (lbDecision == null) {
			lbDecision = new JLabel("Comentarios al autor:");
			lbDecision.setFont(new Font("Tahoma", Font.BOLD, 17));
			lbDecision.setBounds(629, 419, 221, 24);
		}
		return lbDecision;
	}

	private JRadioButton getRdBtnAceptar() {
		if (rdBtnAceptar == null) {
			rdBtnAceptar = new JRadioButton("Aceptar");
			//decisiones.add(rdBtnAceptar);
			rdBtnAceptar.setSelected(true);
			rdBtnAceptar.setBackground(UIManager.getColor("Button.background"));
			rdBtnAceptar.setActionCommand(ACEPTAR);
			rdBtnAceptar.setBounds(639, 463, 71, 23);
		}
		return rdBtnAceptar;
	}

	private JRadioButton getRdBtnAceptarConCambiosMenores() {
		if (rdBtnAceptarConCambiosMenores == null) {
			rdBtnAceptarConCambiosMenores = new JRadioButton("Aceptar con cambios menores");
			//decisiones.add(rdBtnAceptarConCambiosMenores);
			rdBtnAceptarConCambiosMenores.setBackground(UIManager.getColor("Button.background"));
			rdBtnAceptarConCambiosMenores.setActionCommand(ACEPTAR_CAMBIOS_MENORES);
			rdBtnAceptarConCambiosMenores.setBounds(717, 463, 203, 23);
		}
		return rdBtnAceptarConCambiosMenores;
	}

	private JRadioButton getRdBtnAceptarConCambiosMayores() {
		if (rdBtnAceptarConCambiosMayores == null) {
			rdBtnAceptarConCambiosMayores = new JRadioButton("Aceptar con cambios mayores");
			//decisiones.add(rdBtnAceptarConCambiosMayores);
			rdBtnAceptarConCambiosMayores.setBackground(SystemColor.menu);
			rdBtnAceptarConCambiosMayores.setActionCommand(ACEPTAR_CAMBIOS_MAYORES);
			rdBtnAceptarConCambiosMayores.setBounds(922, 463, 213, 23);
		}
		return rdBtnAceptarConCambiosMayores;
	}

	private JRadioButton getRdBtnrechazar() {
		if (rdBtnrechazar == null) {
			rdBtnrechazar = new JRadioButton("Rechazar");
			//decisiones.add(rdBtnrechazar);
			rdBtnrechazar.setBackground(SystemColor.menu);
			rdBtnrechazar.setActionCommand(RECHAZAR);
			rdBtnrechazar.setBounds(1137, 463, 91, 23);
		}
		return rdBtnrechazar;
	}

	private JButton getBtDecision() {
		if (btDecision == null) {
			btDecision = new JButton("Tomar Decision");
			btDecision.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tomarDecision();
				}
			});
			btDecision.setForeground(Color.BLACK);
			btDecision.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btDecision.setBackground(new Color(60, 179, 113));
			btDecision.setBounds(839, 509, 163, 23);
		}
		return btDecision;
	}

	private void tomarDecision() {
		if (listCambiosMenores.getSelectedValuesList().size() > 1)
			JOptionPane.showMessageDialog(null, "Debe seleccionar solo un artículo", "Error al seleccionar artículos",
					JOptionPane.ERROR_MESSAGE);
		else if (listCambiosMenores.getSelectedValuesList().size() == 0)
			JOptionPane.showMessageDialog(null, "Seleccione un artículo para poder revisarlo",
					"Error al seleccionar artículos", JOptionPane.ERROR_MESSAGE);
		
		else {
			ArticuloEntity articulo = listCambiosMenores.getSelectedValue();
			switch (decisiones.getSelection().getActionCommand()) {
			case ACEPTAR_CAMBIOS_MENORES: {
				if(articulo.getEstado().equals(ArticuloEntity.ACEPTADO_CAMBIOS_MENORES))
					JOptionPane.showMessageDialog(null, "Para un artículo con cambios menores solo se puede aceptar o rechazar",
							"Error al tomar decisión", JOptionPane.ERROR_MESSAGE);
				else {
					enviarCartaDecision(articulo, ArticuloEntity.ACEPTADO_CAMBIOS_MENORES);
					JOptionPane.showMessageDialog(this, "El estado del artículo ahora es: " + ArticuloEntity.ACEPTADO_CAMBIOS_MENORES);
					modeloLista.removeElement(articulo);
				}
				break;
			}
			case ACEPTAR_CAMBIOS_MAYORES: {
				// crearVentanaAsignarFechas();
				if(articulo.getEstado().equals(ArticuloEntity.ACEPTADO_CAMBIOS_MENORES))
					JOptionPane.showMessageDialog(null, "Para un artículo con cambios menores solo se puede aceptar o rechazar",
							"Error al tomar decisión", JOptionPane.ERROR_MESSAGE);
				else {
					enviarCartaDecision(articulo, ArticuloEntity.ACEPTADO_CAMBIOS_MAYORES);
					JOptionPane.showMessageDialog(this, "El estado del artículo ahora es: " + ArticuloEntity.ACEPTADO_CAMBIOS_MAYORES);
					modeloLista.removeElement(articulo);
				}
				break;
			}
			case ACEPTAR: {
				enviarCartaDecision(articulo, ArticuloEntity.ACEPTADO);
				JOptionPane.showMessageDialog(this, "El estado del artículo ahora es: " + ArticuloEntity.ACEPTADO);
				modeloLista.removeElement(articulo);
				break;
			}
			case RECHAZAR: {
				// crearVentanaAsignarFechas();
				enviarCartaDecision(articulo, ArticuloEntity.RECHAZADO);
				JOptionPane.showMessageDialog(this, "El estado del artículo ahora es: " + ArticuloEntity.RECHAZADO);
				modeloLista.removeElement(articulo);
				break;
			}
			}
		}
	}

	private void enviarCartaDecision(ArticuloEntity art, String nuevoEstado) {
			articuloCont.enviarDecision(art, nuevoEstado);
			//this.setVisible(false);
			//this.dispose();
	}
	
	private void agruparButtons() {
		decisiones = new ButtonGroup();
		decisiones.add(getRdBtnAceptarConCambiosMayores());
		decisiones.add(getRdBtnAceptarConCambiosMenores());
		decisiones.add(getRdBtnAceptar());
		decisiones.add(getRdBtnrechazar());
	    }
}
