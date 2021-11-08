package giis.demo.util;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import giis.demo.tkrun.controllers.autor.AutorController;
import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.revisor.RevisorController;
import giis.demo.tkrun.views.autor.AutorView;
import giis.demo.tkrun.views.revisor.RevisorView;




/**
 * Punto de entrada principal que incluye botones para la ejecucion de las pantallas 
 * de las aplicaciones de ejemplo  
 * y acciones de inicializacion de la base de datos
 * No sigue MVC pues es solamente temporal para que durante el desarrollo se tenga posibilidad
 * de realizar acciones de inicializacion
 */
public class SwingMain {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { //NOSONAR codigo autogenerado
			public void run() {
				try {
					SwingMain window = new SwingMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace(); //NOSONAR codigo autogenerado
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SwingMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Main");
		frame.setBounds(0, 0, 287, 185);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnEjecutarTkrun = new JButton("Ejecutar giis.demo.tkrun");
		btnEjecutarTkrun.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				//new EditorController(generarArticulo());
				//new RevisorView(new RevisorController(1));
				new AutorView(new AutorController());
				//new EditorViewDecisionArticulo(new EditorController());
				//new UserController();
			}
		});
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.getContentPane().add(btnEjecutarTkrun);
		
			
		JButton btnInicializarBaseDeDatos = new JButton("Inicializar Base de Datos en Blanco");
		btnInicializarBaseDeDatos.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				Database db=new Database();
				db.createDatabase(false);
			}
		});
		frame.getContentPane().add(btnInicializarBaseDeDatos);
			
		JButton btnCargarDatosIniciales = new JButton("Cargar Datos Iniciales para Pruebas");
		btnCargarDatosIniciales.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				Database db=new Database();
				db.createDatabase(false);
				db.loadDatabase();
			}
		});
		frame.getContentPane().add(btnCargarDatosIniciales);
	}

	protected ArticuloEntity generarArticulo() {
		
		ArticuloEntity articulo = new ArticuloEntity();
		articulo.setTitulo("Articulo sobre la naturaleza xd");
		articulo.setPrimerAutor("Oscar");
		articulo.setOtrosAutores("Dtoke y papo");
		articulo.setCartaPresentacion("cartadepresentacion.txt");
		articulo.setCV("CVdeOscar.css");
		articulo.setFicheroFuente("ficheroguente.com");
		articulo.setFirma(true);
		articulo.setPalabrasClave("Queso - Fernando - Teclado");
		articulo.setResumen("Cada PL se dividirá en equipos, de 4 alumnos. Los alumnos formarán sus propios equipos,\r\n" + 
				"aunque el profesor podrá realizar cambios para que se cumplan las cifras anteriores, o cuando\r\n" + 
				"haya bajas o nuevas incorporaciones");
		articulo.setIdArticulo(1);
		
		return articulo;
	}

	public JFrame getFrame() { return this.frame; }
	
}
