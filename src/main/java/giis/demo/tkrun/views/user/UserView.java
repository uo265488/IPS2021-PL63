package giis.demo.tkrun.views.user;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.controllers.autor.AutorController;
import giis.demo.tkrun.controllers.editor.EditorController;
import giis.demo.tkrun.controllers.entities.UserEntity;
import giis.demo.tkrun.controllers.revisor.RevisorController;
import giis.demo.tkrun.controllers.user.UserController;
import giis.demo.tkrun.views.editor.MenuEditor;

public class UserView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private UserController userController;
	private List<UserEntity> users;
	private JLabel lbLogin;
	private JComboBox<UserEntity> cbUsers;
	private JButton btnLogin;
	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserView frame = new UserView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public UserView() {
		setResizable(false);
		initialize();
	}
	
	public UserView(UserController controller) {
		this.userController = controller;
		this.users = userController.getUsers();
		initialize();
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbLogin());
		contentPane.add(getCbUsers());
		contentPane.add(getBtnLogin());
	}

	private JLabel getLbLogin() {
		if (lbLogin == null) {
			lbLogin = new JLabel("Usuarios disponibles para iniciar sesion:");
			lbLogin.setBounds(68, 32, 294, 14);
			lbLogin.setDisplayedMnemonic('U');
			lbLogin.setLabelFor(getCbUsers());
		}
		return lbLogin;
	}
	private JComboBox<UserEntity> getCbUsers() {
		if (cbUsers == null) {
			cbUsers = new JComboBox<UserEntity>();
			cbUsers.setBounds(68, 57, 363, 161);
			setComboBoxModel();
		}
		return cbUsers;
	}
	private JButton getBtnLogin() {
		if (btnLogin == null) {
			btnLogin = new JButton("Iniciar sesión");
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					login();
				}
			});
			btnLogin.setBounds(117, 229, 264, 28);
			btnLogin.setForeground(Color.WHITE);
			btnLogin.setBackground(new Color(0, 128, 0));
			btnLogin.setMnemonic('i');
		}
		return btnLogin;
	}
	
	private void setComboBoxModel() {
		UserEntity[] usersModel = new UserEntity[this.users.size()];
		for(int i = 0; i< usersModel.length; i++) {
			usersModel[i] = users.get(i);
		}
		
		getCbUsers().setModel(new DefaultComboBoxModel<UserEntity>(usersModel));
	}
	
	private void login() {
		UserEntity userEntity = (UserEntity) getCbUsers().getSelectedItem();
		
		if(userEntity.getType().toLowerCase().equals("autor")) {
			launchAutor();
		}
		else if(userEntity.getType().toLowerCase().equals("revisor")) {
			launchRevisor(userEntity.getIdUser());
		}else {
			launchEditor();
		}
	}
	
	private void launchAutor() {
		new AutorController();
	}
	
	private void launchRevisor(int idRevisor) {
		new RevisorController(idRevisor);
	}
	
	private void launchEditor() {
		MenuEditor menu = new MenuEditor(new EditorController());
		menu.setVisible(true);
		//new EditorController();
	}
}
