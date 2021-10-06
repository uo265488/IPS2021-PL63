package giis.demo.tkrun.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.AutorController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.models.autor.AutorModel;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AutorView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbAutor;
	private JLabel lbId;
	private JTextField txId;
	private JScrollPane scrollPane;
	private JTextPane tpArticulos;
	private List<ArticuloEntity> articulosDelEditor = new ArrayList<ArticuloEntity>();
	private AutorController controller;
	private JButton btConfirmar;

	///**
	// * Launch the application.
	 //*/
	//public static void main(String[] args) {
	//	EventQueue.invokeLater(new Runnable() {
		//	public void run() {
		//		try {
		//			AutorView frame = new AutorView();
		//			frame.setVisible(true);
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//		}
		//	}
		//});
	//}

	/**
	 * Create the frame.
	 */
	public AutorView(AutorController controller) {
		this.controller = controller;
		inicialize();
	}
	
	private void inicialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 479);
		setTitle("Vista de Autor");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbAutor());
		contentPane.add(getLbId());
		contentPane.add(getTxId());
		contentPane.add(getScrollPane());
		contentPane.add(getBtConfirmar());
		setVisible(true);
		setResizable(false);
	}
	private JLabel getLbAutor() {
		if (lbAutor == null) {
			lbAutor = new JLabel("Vista Autor");
			lbAutor.setFont(new Font("Tahoma", Font.ITALIC, 25));
			lbAutor.setBounds(238, 11, 178, 33);
		}
		return lbAutor;
	}
	private JLabel getLbId() {
		if (lbId == null) {
			lbId = new JLabel("Meta su identificador para ver sus artículos:");
			lbId.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lbId.setBounds(10, 77, 298, 22);
		}
		return lbId;
	}
	private JTextField getTxId() {
		if (txId == null) {
			txId = new JTextField();
			txId.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txId.setBounds(318, 78, 191, 20);
			txId.setColumns(10);
		}
		return txId;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 110, 657, 319);
			scrollPane.setViewportView(getTpArticulos());
		}
		return scrollPane;
	}
	private JTextPane getTpArticulos() {
		if (tpArticulos == null) {
			tpArticulos = new JTextPane();
		}
		return tpArticulos;
	}
	private JButton getBtConfirmar() {
		if (btConfirmar == null) {
			btConfirmar = new JButton("Visualizar Artículos");
			btConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int idAutor = Integer.parseInt(getTxId().getText());
					articulosDelEditor = controller.getArticulosPropios(idAutor);
					mostrarArticulos();
				}
			});
			btConfirmar.setForeground(new Color(255, 255, 255));
			btConfirmar.setBackground(new Color(47, 79, 79));
			btConfirmar.setBounds(519, 77, 148, 23);
		}
		return btConfirmar;
	}
	
	private void mostrarArticulos() {
		getTpArticulos().setText("");
		for(int i = 0; i < articulosDelEditor.size(); i++) {
			String str = "- Título: " + articulosDelEditor.get(i).getTitulo() + " Primer Autor: " + articulosDelEditor.get(i).getPrimerAutor() + " Estado: " + articulosDelEditor.get(i).getEstado() + "\n";
			getTpArticulos().setText(getTpArticulos().getText() + str);
		}
		if(articulosDelEditor.size() == 0)
			getTpArticulos().setText("No hay artículos en la base de datos para este identificador");
	}
}
