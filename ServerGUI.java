import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
 * Server Class: Implement server functionality
 * 
 * @SER516 ProjecTwo_Team10
 * @author Kanchan Wakchaure and Vikram Wathodkar
 * @Version 1.0 Server GUI to start/stop the server and enter the value ranges
 */

public class ServerGUI implements ActionListener {

	/* Server properties */
	private JFrame frmServer;
	private static ServerSocket serverSocket;
	private static int serverState = 0;
	static JTextPane txtHighValue = new JTextPane();
	static JTextPane txtLowValue = new JTextPane();
	static JTextPane consolePane = new JTextPane();
	static JTextPane indicatorPane = new JTextPane();
	static JTextField txtFrequency = new JTextField();
	static Indicator ledIndicator = new Indicator(serverState);
	static Socket socket;

	/* Server constructor */
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

		JButton btnStartStop = new JButton("start / stop");
		btnStartStop.setBackground(new Color(255, 204, 204));
		btnStartStop.setFont(new Font("Courier New", Font.PLAIN, 18));
		btnStartStop.setBounds(436, 13, 192, 34);
		btnStartStop.setBorder(BorderFactory.createLineBorder(Color.black));
		btnStartStop.addActionListener(this);

		frmServer.getContentPane().add(btnStartStop);

		JLabel lblHighValue = new JLabel("<html>Highest<br>value:</html>");
		lblHighValue.setBounds(345, 13, 133, 61);
		panel.add(lblHighValue);
		lblHighValue.setBackground(new Color(173, 216, 230));
		lblHighValue.setOpaque(true);
		lblHighValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblHighValue.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblHighValue.setBorder(BorderFactory.createLineBorder(Color.black));

		JLabel lblLowValue = new JLabel("<html>Lowest<br>value:</html>");
		lblLowValue.setBounds(345, 87, 133, 61);
		panel.add(lblLowValue);
		lblLowValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblLowValue.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblLowValue.setBorder(BorderFactory.createLineBorder(Color.black));
		lblLowValue.setOpaque(true);
		lblLowValue.setBackground(new Color(255, 204, 204));

		JLabel lblFrequency = new JLabel("<html>Frequency<br>(Hz):</html>");
		lblFrequency.setBounds(345, 161, 133, 61);
		panel.add(lblFrequency);
		lblFrequency.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrequency.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblFrequency.setBorder(BorderFactory.createLineBorder(Color.black));
		lblFrequency.setBackground(new Color(173, 216, 230));
		lblFrequency.setOpaque(true);

		txtHighValue.setBounds(490, 13, 126, 61);
		panel.add(txtHighValue);
		txtHighValue.setBackground(new Color(255, 204, 204));
		txtHighValue.setFont(new Font("Courier New", Font.PLAIN, 18));
		txtHighValue.setBorder(BorderFactory.createLineBorder(Color.black));

		txtLowValue.setBounds(490, 87, 126, 61);
		panel.add(txtLowValue);
		txtLowValue.setBackground(new Color(173, 216, 230));
		txtLowValue.setFont(new Font("Courier New", Font.PLAIN, 18));
		txtLowValue.setBorder(BorderFactory.createLineBorder(Color.black));

		txtFrequency.setFont(new Font("Courier New", Font.PLAIN, 18));
		txtFrequency.setBounds(490, 161, 126, 61);
		panel.add(txtFrequency);
		txtFrequency.setHorizontalAlignment(SwingConstants.CENTER);
		txtFrequency.setBackground(new Color(255, 204, 204));
		txtFrequency.setBorder(BorderFactory.createLineBorder(Color.black));

		indicatorPane.setBounds(22, 13, 311, 296);
		panel.add(indicatorPane);
		indicatorPane.setBackground(new Color(255, 204, 204));
		indicatorPane.setFont(new Font("Courier New", Font.PLAIN, 18));
		indicatorPane.setBorder(BorderFactory.createLineBorder(Color.black));
		indicatorPane.setEditable(false);

		// Calling the indicator
		ledIndicator.setBorder(BorderFactory.createLineBorder(Color.black));
		ledIndicator.setBounds(22, 13, 311, 296);
		indicatorPane.add(ledIndicator);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		JEditorPane consolePanel = new JEditorPane();
		consolePane.setFont(new Font("Courier New", Font.PLAIN, 18));
		JScrollPane consoleScroll = new JScrollPane(consolePane);
		JTextPane lblConsole = new JTextPane();

		consolePanel.setFont(new Font("Courier New", Font.PLAIN, 18));
		consolePanel.setBackground(new Color(211, 211, 211));
		consolePanel.setBounds(12, 390, 628, 138);
		consolePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		consolePanel.setLayout(new BoxLayout(consolePanel, BoxLayout.PAGE_AXIS));
		consolePanel.setVisible(true);

		lblConsole.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblConsole.setText(" Console: ");
		lblConsole.setOpaque(false);
		lblConsole.setBorder(BorderFactory.createLineBorder(new Color(211, 211, 211)));

		consoleScroll.setOpaque(false);
		consoleScroll.setBorder(BorderFactory.createLineBorder(new Color(211, 211, 211)));
		consoleScroll.setPreferredSize(new Dimension(600, 118));

		consolePanel.add(lblConsole);
		consolePanel.add(consoleScroll);

		consolePane.setBorder(BorderFactory.createLineBorder(new Color(211, 211, 211)));
		consolePane.setEditable(false);
		consolePane.setBackground(new Color(211, 211, 211));
		consolePane.setLayout(new BoxLayout(consolePane, BoxLayout.Y_AXIS));
		consolePane.setEditable(false);
		consolePane.setBackground(new Color(211, 211, 211));

		frmServer.getContentPane().add(consolePanel);
	}

	public static JTextPane getTextPane() {
		return consolePane;
	}
       
	/* Start and stop Server */ 
	public void actionPerformed(ActionEvent e) {
		if (getServerState() == 0) {
			setServerState(1);
			ledIndicator.update(getServerState());
		} else if (getServerState() == 1) {
			setServerState(0);
			ledIndicator.update(getServerState());
		}
	}

	synchronized public static int getServerState() {
		return serverState;
	}
	
	synchronized public static void setServerState(int state) {
		serverState = state;
	}
	
	/* Main function to launch the application */
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

	/**
	 * This function will start server thread
	 * which will communicate with the client
	 */
	public static void startServerThread() {
		(new Thread() {
            @Override
            public void run() {
                try {
                	serverSocket = new ServerSocket(9090);
					socket = serverSocket.accept();
					GenerateRandomNumbers grn = new GenerateRandomNumbers(1000, 20, 3);
					while (true) {
						while (getServerState() == 0)
							Thread.sleep(1000);
						ArrayList<Integer> arrayList = grn.RandomNumberFunction();
						System.out.print(arrayList);
						ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
						objectOutput.writeObject(arrayList);
						System.out.println(arrayList);
						Thread.sleep(1000 / 3);
						arrayList.clear();
					}
                } catch (Exception e) {
                	e.printStackTrace();
				}
			}
		}).start();
	}
/*
			} catch (IOException e) {
				e.printStackTrace();
			}
			int frequency, low, high;
			String channel = null;
			// ObjectOutputStream objectOutput = null;
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				channel = in.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// high = Integer.parseInt(ServerGUI.txtHighValue.getText());
			// low = Integer.parseInt(ServerGUI.txtLowValue.getText());
			// frequency = Integer.parseInt(ServerGUI.txtFrequency.getText());
		}
	}
*/
}
