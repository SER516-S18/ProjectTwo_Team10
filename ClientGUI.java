package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JEditorPane;
import java.awt.SystemColor;

public class ClientGUI {

	private JFrame client;
	private JTextField textHighestValue;
	private JTextField textAverage;
	private JTextField textFrequency;
	private JTextField textLowestValue;
	private static Socket clientSocket;
	private boolean isClientStarted = false;

	//Main method to call client GUI application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGUI window = new ClientGUI();
					window.client.setVisible(true);
					
					//connection code
					clientSocket = new Socket("127.0.0.1", 1010);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Default constructor for Client GUI
	public ClientGUI() {
		initialize();
	}

	//Client GUI components
	private void initialize() {
		client = new JFrame();
		client.setTitle("Client");
		client.setBounds(100, 100, 783, 646);
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.getContentPane().setLayout(null);
		
		JButton startStop = new JButton("start / stop");
		startStop.setBackground(Color.PINK);
		startStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clientStartStop(isClientStarted);
			}
		});
		startStop.setFont(new Font("Courier New", Font.PLAIN, 16));
		startStop.setBounds(565, 13, 188, 36);
		startStop.setBorder(BorderFactory.createLineBorder(Color.black));
		client.getContentPane().add(startStop);
		
		JLabel labelHighestValue = new JLabel("Highest value:");
		labelHighestValue.setHorizontalAlignment(SwingConstants.CENTER);
		labelHighestValue.setFont(new Font("Courier New", Font.PLAIN, 16));
		labelHighestValue.setBackground(SystemColor.activeCaption);
		labelHighestValue.setBounds(459, 92, 154, 51);
		labelHighestValue.setBorder(BorderFactory.createLineBorder(Color.black));
		labelHighestValue.setOpaque(true);
		client.getContentPane().add(labelHighestValue);
		
		JLabel labelAverageValue = new JLabel("Lowest value:");
		labelAverageValue.setHorizontalAlignment(SwingConstants.CENTER);
		labelAverageValue.setFont(new Font("Courier New", Font.PLAIN, 16));
		labelAverageValue.setBorder(BorderFactory.createLineBorder(Color.black));
		labelAverageValue.setBackground(Color.PINK);
		labelAverageValue.setOpaque(true);
		labelAverageValue.setBounds(459, 156, 154, 51);
		client.getContentPane().add(labelAverageValue);
		
		JLabel labelAverage = new JLabel("Average:");
		labelAverage.setHorizontalAlignment(SwingConstants.CENTER);
		labelAverage.setFont(new Font("Courier New", Font.PLAIN, 16));
		labelAverage.setBorder(BorderFactory.createLineBorder(Color.black));
		labelAverage.setBackground(SystemColor.activeCaption);
		labelAverage.setBounds(459, 220, 154, 51);
		labelAverage.setOpaque(true);
		client.getContentPane().add(labelAverage);
		
		JLabel labelChannels = new JLabel("Channels:");
		labelChannels.setHorizontalAlignment(SwingConstants.CENTER);
		labelChannels.setFont(new Font("Courier New", Font.PLAIN, 16));
		labelChannels.setBorder(BorderFactory.createLineBorder(Color.black));
		labelChannels.setBackground(Color.PINK);
		labelChannels.setBounds(459, 284, 154, 51);
		labelChannels.setOpaque(true);
		client.getContentPane().add(labelChannels);
		
		JLabel labelFrequency = new JLabel("Frequency (Hz):");
		labelFrequency.setHorizontalAlignment(SwingConstants.CENTER);
		labelFrequency.setFont(new Font("Courier New", Font.PLAIN, 16));
		labelFrequency.setBorder(BorderFactory.createLineBorder(Color.black));
		labelFrequency.setBackground(SystemColor.activeCaption);
		labelFrequency.setBounds(459, 348, 154, 51);
		labelFrequency.setOpaque(true);
		client.getContentPane().add(labelFrequency);
		
		textHighestValue = new JTextField();
		textHighestValue.setFont(new Font("Courier New", Font.PLAIN, 16));
		textHighestValue.setHorizontalAlignment(SwingConstants.CENTER);
		textHighestValue.setBackground(Color.PINK);
		textHighestValue.setBounds(625, 92, 116, 51);
		textHighestValue.setEditable(false);
		textHighestValue.setBorder(BorderFactory.createLineBorder(Color.black));
		client.getContentPane().add(textHighestValue);
		textHighestValue.setColumns(10);
		
		textAverage = new JTextField();
		textAverage.setHorizontalAlignment(SwingConstants.CENTER);
		textAverage.setFont(new Font("Courier New", Font.PLAIN, 16));
		textAverage.setColumns(10);
		textAverage.setBorder(BorderFactory.createLineBorder(Color.black));
		textAverage.setBackground(Color.PINK);
		textAverage.setEditable(false);
		textAverage.setBounds(625, 220, 116, 51);
		client.getContentPane().add(textAverage);
		
		textFrequency = new JTextField();
		textFrequency.setHorizontalAlignment(SwingConstants.CENTER);
		textFrequency.setFont(new Font("Courier New", Font.PLAIN, 16));
		textFrequency.setColumns(10);
		textFrequency.setBorder(BorderFactory.createLineBorder(Color.black));
		textFrequency.setBackground(Color.PINK);
		textFrequency.setBounds(625, 348, 116, 51);
		client.getContentPane().add(textFrequency);
		
		textLowestValue = new JTextField();
		textLowestValue.setHorizontalAlignment(SwingConstants.CENTER);
		textLowestValue.setFont(new Font("Courier New", Font.PLAIN, 16));
		textLowestValue.setColumns(10);
		textLowestValue.setBorder(BorderFactory.createLineBorder(Color.black));
		textLowestValue.setBackground(SystemColor.activeCaption);
		textLowestValue.setBounds(625, 156, 117, 51);
		textLowestValue.setEditable(false);
		client.getContentPane().add(textLowestValue);
		
		JComboBox channels = new JComboBox();
		channels.setFont(new Font("Courier New", Font.PLAIN, 16));
		channels.setBackground(SystemColor.activeCaption);
		channels.setBorder(BorderFactory.createLineBorder(Color.black));
		channels.setBounds(625, 284, 120, 51);
		channels.setOpaque(true);
		client.getContentPane().add(channels);
		
		JPanel plotPanel = new JPanel();
		plotPanel.setBackground(Color.PINK);
		plotPanel.setBounds(27, 93, 420, 306);
		plotPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		client.getContentPane().add(plotPanel);
		
		JEditorPane consolePane = new JEditorPane();
		consolePane.setBackground(Color.LIGHT_GRAY);
		consolePane.setText("Console:");
		consolePane.setFont(new Font("Courier New", Font.PLAIN, 16));
		consolePane.setBounds(12, 427, 741, 159);
		consolePane.setBorder(BorderFactory.createLineBorder(Color.black));
		client.getContentPane().add(consolePane);
		
		JPanel borderPanel = new JPanel();
		borderPanel.setBackground(Color.LIGHT_GRAY);
		borderPanel.setBounds(12, 67, 741, 347);
		borderPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		client.getContentPane().add(borderPanel);
		
	}
	
	public void clientStartStop(boolean clientStart) {
		if(!clientStart) {
			this.isClientStarted = true;			
		}
		else {
			this.isClientStarted = false;
		}
	}
}
