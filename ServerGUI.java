import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 * @SER516 ProjecTwo_Team10
 * @author Kanchan Wakchaure
 * @Version 1.0
 * User Story #14: Server GUI
 */

public class ServerGUI {

	private JFrame frmServer;
	private JTextField textField;
	static JTextPane textPane = new JTextPane();
	static JTextPane textPane_1 = new JTextPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerGUI window = new ServerGUI();
					window.frmServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ServerGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmServer = new JFrame();
		frmServer.setTitle("Server");
		frmServer.setBounds(100, 100, 670, 588);
		frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServer.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("start / stop");
		btnNewButton.setBackground(new Color(255, 204, 204));
		btnNewButton.setFont(new Font("Courier New", Font.PLAIN, 18));
		btnNewButton.setBounds(436, 13, 192, 34);
		btnNewButton.setBorder(BorderFactory.createLineBorder(Color.black));
		frmServer.getContentPane().add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(220, 220, 220));
		panel.setBounds(12, 59, 628, 323);
		frmServer.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>Highest<br>value:</html>");
		lblNewLabel.setBounds(345, 13, 133, 61);
		panel.add(lblNewLabel);
		lblNewLabel.setBackground(new Color(173, 216, 230));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblNewLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel lbllowestvalue = new JLabel("<html>Lowest<br>value:</html>");
		lbllowestvalue.setBounds(345, 87, 133, 61);
		panel.add(lbllowestvalue);
		lbllowestvalue.setHorizontalAlignment(SwingConstants.CENTER);
		lbllowestvalue.setFont(new Font("Courier New", Font.PLAIN, 18));
		lbllowestvalue.setBorder(BorderFactory.createLineBorder(Color.black));
		lbllowestvalue.setOpaque(true);
		lbllowestvalue.setBackground(new Color(255, 204, 204));
		
		JLabel lblfrequencyhz = new JLabel("<html>Frequency<br>(Hz):</html>");
		lblfrequencyhz.setBounds(345, 161, 133, 61);
		panel.add(lblfrequencyhz);
		lblfrequencyhz.setHorizontalAlignment(SwingConstants.CENTER);
		lblfrequencyhz.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblfrequencyhz.setBorder(BorderFactory.createLineBorder(Color.black));
		lblfrequencyhz.setBackground(new Color(173, 216, 230));
		lblfrequencyhz.setOpaque(true);
		
		textPane.setBounds(490, 13, 126, 61);
		panel.add(textPane);
		textPane.setBackground(new Color(255, 204, 204));
		textPane.setFont(new Font("Courier New", Font.PLAIN, 18));
		textPane.setBorder(BorderFactory.createLineBorder(Color.black));
		
		textPane_1.setBounds(490, 87, 126, 61);
		panel.add(textPane_1);
		textPane_1.setBackground(new Color(173, 216, 230));
		textPane_1.setFont(new Font("Courier New", Font.PLAIN, 18));
		textPane_1.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setBounds(22, 13, 311, 296);
		panel.add(textPane_3);
		textPane_3.setBackground(new Color(255, 204, 204));
		textPane_3.setFont(new Font("Courier New", Font.PLAIN, 18));
		textPane_3.setBorder(BorderFactory.createLineBorder(Color.black));
		
		textField = new JTextField();
		textField.setBounds(490, 161, 126, 61);
		panel.add(textField);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Courier New", Font.PLAIN, 18));
		textField.setBackground(new Color(255, 204, 204));
		textField.setBorder(BorderFactory.createLineBorder(Color.black));
		textField.setColumns(10);
		
		JTextPane txtpnConsole = new JTextPane();
		txtpnConsole.setText("Console:");
		txtpnConsole.setFont(new Font("Courier New", Font.PLAIN, 18));
		txtpnConsole.setBackground(new Color(211, 211, 211));
		txtpnConsole.setBounds(12, 390, 628, 138);
		txtpnConsole.setBorder(BorderFactory.createLineBorder(Color.black));
		frmServer.getContentPane().add(txtpnConsole);
	}
}
