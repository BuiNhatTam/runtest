package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class FrmLogin extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4545136826899045109L;
	/**
	 * 
	 */
	private JPanel contentPane;
	private JTextField tfTaiKhoan;
	private JPasswordField tfMatKhau;
	private JButton btnDangNhap;
	private JButton btnThoat;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmLogin() {
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1936, 1098);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(1099, 250, 696, 413);
		contentPane.add(panel);
		
		JLabel lblTaiKhoan = new JLabel("Tài Khoản");
		lblTaiKhoan.setBackground(new Color(255, 255, 255));
		lblTaiKhoan.setForeground(Color.BLACK);
		lblTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTaiKhoan.setBounds(99, 153, 100, 40);
		panel.add(lblTaiKhoan);
		
		JLabel lblMatKhau = new JLabel("Mật Khẩu");
		lblMatKhau.setBackground(new Color(255, 255, 255));
		lblMatKhau.setForeground(Color.BLACK);
		lblMatKhau.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMatKhau.setBounds(99, 211, 100, 40);
		panel.add(lblMatKhau);
		
		tfTaiKhoan = new JTextField();
		tfTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfTaiKhoan.setColumns(10);
		tfTaiKhoan.setBounds(209, 154, 300, 40);
		panel.add(tfTaiKhoan);
		
		btnDangNhap = new JButton("ĐĂNG NHẬP");
		btnDangNhap.setBackground(new Color(0, 255, 0));
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDangNhap.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDangNhap.setBounds(209, 285, 165, 45);
		panel.add(btnDangNhap);
		
		btnThoat = new JButton("THOÁT");
		btnThoat.setBackground(new Color(255, 0, 0));
		btnThoat.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnThoat.setBounds(399, 285, 115, 45);
		panel.add(btnThoat);
		
		tfMatKhau = new JPasswordField();
		tfMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfMatKhau.setBounds(209, 212, 300, 40);
		panel.add(tfMatKhau);
		
		JLabel lblDangNhap = new JLabel("ĐĂNG NHẬP");
		lblDangNhap.setForeground(Color.BLACK);
		lblDangNhap.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblDangNhap.setBounds(226, 47, 207, 47);
		panel.add(lblDangNhap);
		
		JLabel lblBackGroundLogin = new JLabel("");
		lblBackGroundLogin.setBounds(44, 175, 961, 553);
		contentPane.add(lblBackGroundLogin);
		lblBackGroundLogin.setIcon(new ImageIcon(FrmLogin.class.getResource("/img/61897a35583a9b51db018d3e_MartinPublicSeating-97560-Importance-School-Library-blogbanner1.png")));
		
		btnDangNhap.addActionListener(this);
		btnDangNhap.requestFocus();
		btnThoat.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		FrmMainMenu mainMenu = new FrmMainMenu();
		Object o = e.getSource();
		if (o.equals(btnDangNhap)) {
			String taiKhoan = tfTaiKhoan.getText();
			String matKhau = new String(tfMatKhau.getPassword());
			if (taiKhoan.equals("123") && matKhau.equals("123")) {
				this.setVisible(false);
				mainMenu.setVisible(true);
			} else if (taiKhoan.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập tài khoản", taiKhoan, JOptionPane.ERROR_MESSAGE);
			} else if (matKhau.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu", matKhau, JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Tên tài khoản hoặc mật khẩu không chính xác", matKhau, JOptionPane.ERROR_MESSAGE);
			}
		} else if (o.equals(btnThoat)) {
			if (JOptionPane.showConfirmDialog(mainMenu, "Bạn có muốn thoát ?", "THOÁT",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
				System.exit(0);
			}
		}
	}
}
