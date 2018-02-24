package server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @SER516 ProjecTwo_Team10
 * @author Kanchan Wakchaure 
 *         Vikram Wathodkar
 * @version 1.0 
 * Server GUI to start/stop the server and enter the value ranges
 */

public class ServerGUI implements ActionListener {

	// Server components
	private JFrame frmServer;
	private static ServerSocket serverSocket;
	private static int serverState = 0;
	static JTextField txtHighValue = new JTextField();
	static JTextField txtLowValue = new JTextField();
	static JTextField txtFrequency = new JTextField();
	static JTextPane consolePane = new JTextPane();
	static JTextPane indicatorPane = new JTextPane();
	static Indicator ledIndicator = new Indicator(serverState);
	static Socket socket;

	// Server constructor
	public ServerGUI() {

		frmServer = new JFrame();
		frmServer.setTitle("Server");
		frmServer.setBounds(100, 100, 670, 588);
		frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServer.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(220, 220, 220));
		panel.setBounds(12, 59, 628, 323);
		frmServer.getContentPane().add(panel);
		panel.setLayout(null);

		//startStop button
		JButton btnStartStop = new JButton("start / stop");
		btnStartStop.setBackground(new Color(255, 204, 204));
		btnStartStop.setFont(new Font("Courier New", Font.PLAIN, 18));
		btnStartStop.setBounds(436, 13, 192, 34);
		btnStartStop.setBorder(BorderFactory.createLineBorder(Color.black));
		btnStartStop.addActionListener(this);

		frmServer.getContentPane().add(btnStartStop);

		//Highest value label
		JLabel lblHighValue = new JLabel("<html>Highest<br>value:</html>");
		lblHighValue.setBounds(345, 13, 133, 61);
		panel.add(lblHighValue);
		lblHighValue.setBackground(new Color(173, 216, 230));
		lblHighValue.setOpaque(true);
		lblHighValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblHighValue.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblHighValue.setBorder(BorderFactory.createLineBorder(Color.black));

		//Lowest value label
		JLabel lblLowValue = new JLabel("<html>Lowest<br>value:</html>");
		lblLowValue.setBounds(345, 87, 133, 61);
		panel.add(lblLowValue);
		lblLowValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblLowValue.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblLowValue.setBorder(BorderFactory.createLineBorder(Color.black));
		lblLowValue.setOpaque(true);
		lblLowValue.setBackground(new Color(255, 204, 204));

		//Frequency label
		JLabel lblFrequency = new JLabel("<html>Frequency<br>(Hz):</html>");
		lblFrequency.setBounds(345, 161, 133, 61);
		panel.add(lblFrequency);
		lblFrequency.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrequency.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblFrequency.setBorder(BorderFactory.createLineBorder(Color.black));
		lblFrequency.setBackground(new Color(173, 216, 230));
		lblFrequency.setOpaque(true);
		txtHighValue.setHorizontalAlignment(SwingConstants.CENTER);

		//Highest value textField
		txtHighValue.setBounds(490, 13, 126, 61);		
		txtHighValue.setBackground(new Color(255, 204, 204));
		txtHighValue.setFont(new Font("Courier New", Font.PLAIN, 18));
		txtHighValue.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(txtHighValue);
		txtLowValue.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Lowest value textField
		txtLowValue.setBounds(490, 87, 126, 61);
		panel.add(txtLowValue);
		txtLowValue.setBackground(new Color(173, 216, 230));
		txtLowValue.setFont(new Font("Courier New", Font.PLAIN, 18));
		txtLowValue.setBorder(BorderFactory.createLineBorder(Color.black));

		//Frequency textField
		txtFrequency.setFont(new Font("Courier New", Font.PLAIN, 18));
		txtFrequency.setBounds(490, 161, 126, 61);
		panel.add(txtFrequency);
		txtFrequency.setHorizontalAlignment(SwingConstants.CENTER);
		txtFrequency.setBackground(new Color(255, 204, 204));
		txtFrequency.setBorder(BorderFactory.createLineBorder(Color.black));

		//Indicator panel for server status
		indicatorPane.setBounds(22, 13, 311, 296);
		panel.add(indicatorPane);
		indicatorPane.setBackground(new Color(255, 204, 204));
		indicatorPane.setFont(new Font("Courier New", Font.PLAIN, 18));
		indicatorPane.setBorder(BorderFactory.createLineBorder(Color.black));
		indicatorPane.setEditable(false);

		//Calling the indicator
		ledIndicator.setBorder(BorderFactory.createLineBorder(Color.black));
		ledIndicator.setBounds(22, 13, 311, 296);
		indicatorPane.add(ledIndicator);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
                // Console part 
		JEditorPane consolePanel = new JEditorPane();
		consolePane.setFont(new Font("Courier New", Font.PLAIN, 18));
		consolePanel.setFont(new Font("Courier New", Font.PLAIN, 18));
		consolePanel.setBackground(new Color(211, 211, 211));
		consolePanel.setBounds(12, 390, 628, 138);
		consolePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		consolePanel.setLayout(new BoxLayout(consolePanel, BoxLayout.PAGE_AXIS));
		consolePanel.setVisible(true);
		
		JTextPane lblConsole = new JTextPane();
		lblConsole.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblConsole.setText(" Console: ");
		lblConsole.setOpaque(false);
		lblConsole.setBorder(BorderFactory.createLineBorder(
		new Color(211, 211, 211)));

		JScrollPane consoleScroll = new JScrollPane(consolePane);
		consoleScroll.setOpaque(false);
		consoleScroll.setBorder(BorderFactory.createLineBorder(	
		new Color(211, 211, 211)));
		consoleScroll.setPreferredSize(new Dimension(600, 118));

		consolePanel.add(lblConsole);
		consolePanel.add(consoleScroll);

		frmServer.getContentPane().add(consolePanel);

		consolePane.setBorder(BorderFactory.createLineBorder(
		new Color(211, 211, 211)));
		consolePane.setEditable(false);
		consolePane.setBackground(new Color(211, 211, 211));
		consolePane.setLayout(new BoxLayout(consolePane, BoxLayout.Y_AXIS));
		consolePane.setEditable(false);
		consolePane.setBackground(new Color(211, 211, 211));
	}

	public static JTextPane getTextPane() {
		return consolePane;
	}
       
    // Start and stop server actions  
	public void actionPerformed(ActionEvent e) {
		if (getServerState() == 0) {
			setServerState(1);
			ledIndicator.update(getServerState());
		} else if (getServerState() == 1) {
			setServerState(0);
			ledIndicator.update(getServerState());
		}
	}

	//Getter method for serverState
	synchronized public static int getServerState() {
		return serverState;
	}
	
	//Setter method for serverState
	synchronized public static void setServerState(int state) {
		serverState = state;
	}
	
	// Main function to launch server application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerGUI window = new ServerGUI();
					window.frmServer.setVisible(true);
					startServerThread();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Method to start the connection between server and client
	public static void startServerThread() {
		Thread connect = (new Thread() {
            @Override
            public void run() {
        		int high, low, frequency;
                try {
                	serverSocket = new ServerSocket(9090);
					socket = serverSocket.accept();
					high = Integer.parseInt(ServerGUI.txtHighValue.getText());
					low = Integer.parseInt(ServerGUI.txtLowValue.getText());
					frequency = Integer.parseInt(
									ServerGUI.txtFrequency.getText());
					GenerateRandomNumbers grn = 
									new GenerateRandomNumbers(high, low,5);
					while (true) {
						while (getServerState() == 0)
							Thread.sleep(1000);
						ArrayList<Integer> arrayList = grn.RandomNumberFunction();
						ObjectOutputStream objectOutput = new 
								ObjectOutputStream(socket.getOutputStream());
						objectOutput.writeObject(arrayList);
						Thread.sleep(1000 / frequency);
						arrayList.clear();
					}
                } 
                catch (Exception e) {
                	e.printStackTrace();
				}
			}
		});
		connect.start();
	}

}
