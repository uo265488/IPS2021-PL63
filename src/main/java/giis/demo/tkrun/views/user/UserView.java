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
import giis.demo.tkrun.controllers.entities.UserEntity;
import giis.demo.tkrun.controllers.revisor.RevisorController;
import giis.demo.tkrun.controllers.user.UserController;
import giis.demo.tkrun.views.autor.MenuAutor;
import giis.demo.tkrun.views.editor.MenuEditor;
import giis.demo.tkrun.views.revisor.RevisorMenu;

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
    private MenuEditor principalView;
    private JButton btnActualizar;

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

    private JButton getBtnLogin() {
	if (btnLogin == null) {
	    btnLogin = new JButton("Iniciar sesión");
	    btnLogin.addActionListener(new ActionListener() {
		@Override
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

    private JComboBox<UserEntity> getCbUsers() {
	if (cbUsers == null) {
	    cbUsers = new JComboBox<UserEntity>();
	    cbUsers.setBounds(68, 57, 363, 161);
	    setComboBoxModel();
	}
	return cbUsers;
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

    /**
     * Create the frame.
     */
    public void initialize() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 603, 328);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getLbLogin());
	contentPane.add(getCbUsers());
	contentPane.add(getBtnLogin());
	contentPane.add(getBtnActualizar());
	setTitle("Revista cientítica. Logging");
	setResizable(false);
	setLocationRelativeTo(null);
    }

    private void launchAutor(String idAutor) {
	MenuAutor ma = new MenuAutor(new AutorController(idAutor), idAutor);
	ma.setVisible(true);
	ma.setLocationRelativeTo(this);
    }

    private void launchEditor() {
	this.principalView = new MenuEditor();
	this.principalView.setVisible(true);
	this.principalView.setLocationRelativeTo(this);
	// this.principalView.setModal(true);

    }

    private void launchRevisor(String idRevisor) {
	RevisorMenu rm = new RevisorMenu(idRevisor, new RevisorController());
	rm.setVisible(true);
	rm.setLocationRelativeTo(this);
    }

    private void login() {
	UserEntity userEntity = (UserEntity) getCbUsers().getSelectedItem();

	if (userEntity.getType().toLowerCase().equals("autor")) {
	    launchAutor(userEntity.getIdUser());
	} else if (userEntity.getType().toLowerCase().equals("revisor")) {
	    launchRevisor(userEntity.getIdUser());
	} else {
	    launchEditor();
	}
    }

    private void setComboBoxModel() {
	UserEntity[] usersModel = new UserEntity[this.users.size()];
	for (int i = 0; i < usersModel.length; i++) {
	    usersModel[i] = users.get(i);
	}

	getCbUsers().setModel(new DefaultComboBoxModel<UserEntity>(usersModel));
    }

    private JButton getBtnActualizar() {
	if (btnActualizar == null) {
	    btnActualizar = new JButton("Actualizar");
	    btnActualizar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    users = userController.getUsers();
		    setComboBoxModel();
		}
	    });
	    btnActualizar.setBounds(462, 57, 99, 21);
	}
	return btnActualizar;
    }
}
