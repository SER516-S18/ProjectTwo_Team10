package server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.UIManager;

import server.ServerGUI;

import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JEditorPane;
import java.awt.SystemColor;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class ServerGUI {

	private JFrame server;
	private JTextField textHighestValue;
	private JTextField textFrequency;
	private JTextField textLowestValue;
	private JPanel indicatorPanel;
	private boolean isServerStarted = false; 
	private static ServerSocket serverSocket;
	private static Socket clientSocket;

	//Main method to call sever GUI application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerGUI window = new ServerGUI();
					window.server.setVisible(true);
					
					//connection code
					while (serverSocket.isBound() && !serverSocket.isClosed()) {
						serverSocket = new ServerSocket(1010);
						clientSocket = serverSocket.accept();
					}
				serverSocket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Default constructor for Server GUI
	public ServerGUI() {
		initialize();
	}

	//Server GUI components
	private void initialize() {
		server = new JFrame();
		server.setTitle("Server");
		server.setBounds(100, 100, 783, 646);
		server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		server.getContentPane().setLayout(null);
		
		JButton startStop = new JButton("start / stop");
		startStop.setBackground(Color.PINK);
		startStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				serverStartStop(isServerStarted);
			}
		});
		startStop.setFont(new Font("Courier New", Font.PLAIN, 16));
		startStop.setBounds(565, 13, 188, 36);
		startStop.setBorder(BorderFactory.createLineBorder(Color.black));
		server.getContentPane().add(startStop);
		
		JLabel labelHighestValue = new JLabel("Highest value:");
		labelHighestValue.setHorizontalAlignment(SwingConstants.CENTER);
		labelHighestValue.setFont(new Font("Courier New", Font.PLAIN, 16));
		labelHighestValue.setBackground(SystemColor.activeCaption);
		labelHighestValue.setBounds(459, 92, 154, 51);
		labelHighestValue.setBorder(BorderFactory.createLineBorder(Color.black));
		labelHighestValue.setOpaque(true);
		server.getContentPane().add(labelHighestValue);
		
		JLabel labelAverageValue = new JLabel("Lowest value:");
		labelAverageValue.setHorizontalAlignment(SwingConstants.CENTER);
		labelAverageValue.setFont(new Font("Courier New", Font.PLAIN, 16));
		labelAverageValue.setBorder(BorderFactory.createLineBorder(Color.black));
		labelAverageValue.setBackground(Color.PINK);
		labelAverageValue.setOpaque(true);
		labelAverageValue.setBounds(459, 156, 154, 51);
		server.getContentPane().add(labelAverageValue);
		
		JLabel labelFrequency = new JLabel("Frequency (Hz):");
		labelFrequency.setHorizontalAlignment(SwingConstants.CENTER);
		labelFrequency.setFont(new Font("Courier New", Font.PLAIN, 16));
		labelFrequency.setBorder(BorderFactory.createLineBorder(Color.black));
		labelFrequency.setBackground(SystemColor.activeCaption);
		labelFrequency.setBounds(459, 220, 154, 51);
		labelFrequency.setOpaque(true);
		server.getContentPane().add(labelFrequency);
		
		textHighestValue = new JTextField();
		textHighestValue.setFont(new Font("Courier New", Font.PLAIN, 16));
		textHighestValue.setHorizontalAlignment(SwingConstants.CENTER);
		textHighestValue.setBackground(Color.PINK);
		textHighestValue.setBounds(625, 92, 116, 51);
		textHighestValue.setEditable(true);
		textHighestValue.setBorder(BorderFactory.createLineBorder(Color.black));
		server.getContentPane().add(textHighestValue);
		textHighestValue.setColumns(10);
		
		textFrequency = new JTextField();
		textFrequency.setHorizontalAlignment(SwingConstants.CENTER);
		textFrequency.setFont(new Font("Courier New", Font.PLAIN, 16));
		textFrequency.setColumns(10);
		textFrequency.setBorder(BorderFactory.createLineBorder(Color.black));
		textFrequency.setBackground(Color.PINK);
		textFrequency.setBounds(625, 220, 116, 51);
		server.getContentPane().add(textFrequency);
		
		textLowestValue = new JTextField();
		textLowestValue.setHorizontalAlignment(SwingConstants.CENTER);
		textLowestValue.setFont(new Font("Courier New", Font.PLAIN, 16));
		textLowestValue.setColumns(10);
		textLowestValue.setBorder(BorderFactory.createLineBorder(Color.black));
		textLowestValue.setBackground(SystemColor.activeCaption);
		textLowestValue.setBounds(625, 156, 117, 51);
		textLowestValue.setEditable(true);
		server.getContentPane().add(textLowestValue);
		
		indicatorPanel = new JPanel();
		indicatorPanel.setBackground(Color.PINK);
		indicatorPanel.setBounds(27, 93, 420, 306);
		indicatorPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		server.getContentPane().add(indicatorPanel);
		
		JEditorPane consolePane = new JEditorPane();
		consolePane.setBackground(Color.LIGHT_GRAY);
		consolePane.setText("Console:");
		consolePane.setFont(new Font("Courier New", Font.PLAIN, 16));
		consolePane.setBounds(12, 427, 741, 159);
		consolePane.setBorder(BorderFactory.createLineBorder(Color.black));
		server.getContentPane().add(consolePane);
		
		JPanel borderPanel = new JPanel();
		borderPanel.setBackground(Color.LIGHT_GRAY);
		borderPanel.setBounds(12, 72, 741, 342);
		borderPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		server.getContentPane().add(borderPanel);
		
	}
	
	//Action performed on server start/stop button
	public void serverStartStop(boolean serverStart) {
		if(!serverStart) {
			this.isServerStarted = true;
			this.indicatorPanel.setBackground(Color.GREEN);
		}
		else {
			this.isServerStarted = false;
			this.indicatorPanel.setBackground(Color.PINK);
		}
	}
}
