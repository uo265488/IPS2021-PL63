package giis.demo.tkrun.views.revisor;

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

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisionEntity;
import giis.demo.tkrun.controllers.revisor.RevisorController;
import giis.demo.tkrun.models.dtos.RevisionDto;
import giis.demo.tkrun.views.articulo.DetallesMasImportantesArticulo;
import giis.demo.util.DtoMapper;

public class RevisorDecisionRevisionesEncomendadas extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lbTitulo;
    private JLabel lbId;
    private JButton btVisuzlizar;
    private JScrollPane scrollPane;
    private JList<ArticuloEntity> lista;
    private DefaultListModel<ArticuloEntity> modeloLista;
    private JButton btDetalles;
    private List<ArticuloEntity> articulos = new ArrayList<>();
    private RevisorController controller;
    private JButton btAceptar;
    private JButton btRechazar;
    private String idRev;

    /**
     * Create the frame.
     */
    public RevisorDecisionRevisionesEncomendadas(RevisorController controller, String idRev) {
	this.controller = controller;
	this.idRev = idRev;
	setTitle("Revisor: " + controller.getRevisorById(idRev).getNombre() + ". Revisiones encomendadas");
	inicialice();
    }

    /**
     * Create the frame.
     */
    public void inicialice() {
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setResizable(false);
	setBounds(100, 100, 953, 532);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getLbTitulo());
	contentPane.add(getLbId());
	contentPane.add(getBtVisuzlizar());
	contentPane.add(getScrollPane());
	contentPane.add(getBtDetalles());
	contentPane.add(getBtAceptar());
	contentPane.add(getBtRechazar());
	setVisible(true);
    }

    private JLabel getLbTitulo() {
	if (lbTitulo == null) {
	    lbTitulo = new JLabel("Vista Revisor: Decisión encargos:");
	    lbTitulo.setFont(new Font("Tahoma", Font.ITALIC, 21));
	    lbTitulo.setBounds(285, 24, 327, 26);
	}
	return lbTitulo;
    }

    private JLabel getLbId() {
	if (lbId == null) {
	    lbId = new JLabel("Revisor:         " + controller.getRevisorById(idRev).toString());
	    lbId.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lbId.setBounds(76, 83, 461, 26);
	}
	return lbId;
    }

    private JButton getBtVisuzlizar() {
	if (btVisuzlizar == null) {
	    btVisuzlizar = new JButton("Ver artículos");
	    btVisuzlizar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    try {
			articulos.clear();
			articulos = controller.getArticulosSinResponder(idRev);
			rellenarLista();
		    } catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Debe introducir un id con solo números", "Error de Id",
				JOptionPane.ERROR_MESSAGE);
		    }
		}
	    });
	    btVisuzlizar.setBackground(new Color(240, 230, 140));
	    btVisuzlizar.setForeground(new Color(0, 0, 0));
	    btVisuzlizar.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    btVisuzlizar.setBounds(598, 88, 130, 23);
	}
	return btVisuzlizar;
    }

    private void rellenarLista() {
	modeloLista.clear();
	for (ArticuloEntity art : articulos)
	    modeloLista.addElement(art);
    }

    private JScrollPane getScrollPane() {
	if (scrollPane == null) {
	    scrollPane = new JScrollPane();
	    scrollPane.setBounds(76, 147, 461, 312);
	    scrollPane.setViewportView(getLista());
	}
	return scrollPane;
    }

    private JList<ArticuloEntity> getLista() {
	if (lista == null) {
	    modeloLista = new DefaultListModel<>();
	    lista = new JList<ArticuloEntity>(modeloLista);
	}
	return lista;
    }

    private JButton getBtDetalles() {
	if (btDetalles == null) {
	    btDetalles = new JButton("Detalles del Artículo");
	    btDetalles.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    if (lista.getSelectedValuesList().size() > 1)
			JOptionPane.showMessageDialog(null, "Debe seleccionar solo un artículo",
				"Error al seleccionar artículos", JOptionPane.ERROR_MESSAGE);
		    else if (lista.getSelectedValuesList().size() == 0)
			JOptionPane.showMessageDialog(null, "Seleccione un artículo para poder ver sus detalles",
				"Error al seleccionar artículos", JOptionPane.ERROR_MESSAGE);
		    else {
			ArticuloEntity art = lista.getSelectedValue();
			mostrarDetallesArticulo(art);
		    }
		}
	    });
	    btDetalles.setBackground(new Color(102, 205, 170));
	    btDetalles.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    btDetalles.setBounds(547, 275, 180, 34);
	}
	return btDetalles;
    }

    private void mostrarDetallesArticulo(ArticuloEntity art) {
	RevisionDto fecha = controller.getFecha(idRev, art.getIdArticulo());
	DetallesMasImportantesArticulo vista = new DetallesMasImportantesArticulo(art, fecha.getFecha());
	vista.setModal(true);
	vista.setVisible(true);
    }

    private JButton getBtAceptar() {
	if (btAceptar == null) {
	    btAceptar = new JButton("Aceptar Artículo");
	    btAceptar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    if (lista.getSelectedValuesList().size() > 1)
			JOptionPane.showMessageDialog(null, "Debe seleccionar solo un artículo",
				"Error al seleccionar artículos", JOptionPane.ERROR_MESSAGE);
		    else if (lista.getSelectedValuesList().size() == 0)
			JOptionPane.showMessageDialog(null, "Seleccione un artículo para poder aceptarlo",
				"Error al seleccionar artículos", JOptionPane.ERROR_MESSAGE);
		    else {
			ArticuloEntity art = lista.getSelectedValue();
			controller.decisionArticulo(idRev, art.getIdArticulo(), true);
			List<RevisionEntity> revisionesAceptadas = controller
				.getArticulosAceptados(art.getIdArticulo());
			if (revisionesAceptadas.size() == 3) {
			    art.setEstado(ArticuloEntity.EN_REVISION);
			    controller.updateArticulo(DtoMapper.toArticuloDto(art));
			}
			articulos.remove(art);
			rellenarLista();
			JOptionPane.showMessageDialog(null, "Ha aceptado la revisión del artículo " + art.getTitulo(),
				"Acepta la revisión", JOptionPane.INFORMATION_MESSAGE);
		    }
		}
	    });
	    btAceptar.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    btAceptar.setBackground(new Color(34, 139, 34));
	    btAceptar.setBounds(548, 425, 180, 34);
	}
	return btAceptar;
    }

    private JButton getBtRechazar() {
	if (btRechazar == null) {
	    btRechazar = new JButton("Rechazar Artículo");
	    btRechazar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    if (lista.getSelectedValuesList().size() > 1)
			JOptionPane.showMessageDialog(null, "Debe seleccionar solo un artículo",
				"Error al seleccionar artículos", JOptionPane.ERROR_MESSAGE);
		    else if (lista.getSelectedValuesList().size() == 0)
			JOptionPane.showMessageDialog(null, "Seleccione un artículo para poder rechazarlo",
				"Error al seleccionar artículos", JOptionPane.ERROR_MESSAGE);
		    else {
			ArticuloEntity art = lista.getSelectedValue();
			controller.decisionArticulo(idRev, art.getIdArticulo(), false);
			art.setEstado(ArticuloEntity.CON_EL_EDITOR);
			controller.updateArticulo(DtoMapper.toArticuloDto(art));
			articulos.remove(art);
			rellenarLista();
			JOptionPane.showMessageDialog(null, "Ha rechazado la revisión del artículo " + art.getTitulo(),
				"Rechazo de revisión", JOptionPane.INFORMATION_MESSAGE);
		    }
		}
	    });
	    btRechazar.setForeground(new Color(255, 255, 255));
	    btRechazar.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    btRechazar.setBackground(new Color(165, 42, 42));
	    btRechazar.setBounds(738, 425, 180, 34);
	}
	return btRechazar;
    }
}
