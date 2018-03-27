package server;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
 * @version 2.0 
 * Server GUI to start/stop the server and enter the value ranges
 */

@SuppressWarnings("serial")
public class ServerGUI extends JFrame{
	private JTextPane indicatorPane;
	private JButton btnStartStop;
	private JLabel lblHighValue;
	private JLabel lblLowValue;
	private JLabel lblFrequency;
	private JTextField txtHighValue;
	private JTextField txtLowValue;
	private JTextField txtFrequency;
	private Indicator ledIndicator;
	private JEditorPane consolePanel;
	private ServerConsole consolePane;
	private JTextPane lblConsole;
	private JScrollPane consoleScroll;
	private int workStatus;
	private Thread socketThread;
	
	private ServerConnector server;

	/**initialize startStop button
	 * add action listener to button to control server start or stop
	 */
	public void initStartStopBTN(){
		btnStartStop = new JButton("stop");
		workStatus = 1;
		btnStartStop.setBackground(new Color(255, 204, 204));
		btnStartStop.setFont(new Font("Courier New", Font.PLAIN, 18));
		btnStartStop.setBounds(436, 13, 192, 34);
		btnStartStop.setBorder(BorderFactory.createLineBorder(Color.black));
		btnStartStop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (workStatus == 0){
					workStatus = 1;
					btnStartStop.setText("stop");
					ledIndicator.update(workStatus);
					int high, low, frequency;
					high = Integer.parseInt(txtHighValue.getText());
					low = Integer.parseInt(txtLowValue.getText());
					frequency = Integer.parseInt(
									txtFrequency.getText());
					server.setValues(high, low, frequency);
					server.start();
				}else {
					workStatus = 0;
					btnStartStop.setText("start");
					ledIndicator.update(workStatus);
					server.stop();
				}
			}
		});

		this.getContentPane().add(btnStartStop);
	}
	
	/**initialize and draw the label of highest value,
	 * lowest value and frequency.
	 */
	public void initLabels(){
		//Highest value label
		lblHighValue = new JLabel("<html>Highest<br>value:</html>");
		lblHighValue.setBounds(345, 13, 133, 61);
		lblHighValue.setBackground(new Color(173, 216, 230));
		lblHighValue.setOpaque(true);
		lblHighValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblHighValue.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblHighValue.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//Lowest value label
		lblLowValue = new JLabel("<html>Lowest<br>value:</html>");
		lblLowValue.setBounds(345, 87, 133, 61);
		lblLowValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblLowValue.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblLowValue.setBorder(BorderFactory.createLineBorder(Color.black));
		lblLowValue.setOpaque(true);
		lblLowValue.setBackground(new Color(255, 204, 204));
		
		//Frequency label
		lblFrequency = new JLabel("<html>Frequency<br>(Hz):</html>");
		lblFrequency.setBounds(345, 161, 133, 61);
		lblFrequency.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrequency.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblFrequency.setBorder(BorderFactory.createLineBorder(Color.black));
		lblFrequency.setBackground(new Color(173, 216, 230));
		lblFrequency.setOpaque(true);
	}
	
	/**initialize and draw the text field of highest value,
	 * lowest value and frequency.
	 */
	public void initTextFields(){
		//Highest value textField
		txtHighValue = new JTextField();
		txtHighValue.setBounds(490, 13, 126, 61);		
		txtHighValue.setBackground(new Color(255, 204, 204));
		txtHighValue.setFont(new Font("Courier New", Font.PLAIN, 18));
		txtHighValue.setBorder(BorderFactory.createLineBorder(Color.black));
		txtHighValue.setHorizontalAlignment(SwingConstants.CENTER);
		txtHighValue.setText("30");
		
		//Lowest value textField
		txtLowValue = new JTextField();
		txtLowValue.setBounds(490, 87, 126, 61);
		txtLowValue.setBackground(new Color(173, 216, 230));
		txtLowValue.setFont(new Font("Courier New", Font.PLAIN, 18));
		txtLowValue.setBorder(BorderFactory.createLineBorder(Color.black));
		txtLowValue.setHorizontalAlignment(SwingConstants.CENTER);
		txtLowValue.setText("0");
		
		//Frequency textField
		txtFrequency = new JTextField();
		txtFrequency.setFont(new Font("Courier New", Font.PLAIN, 18));
		txtFrequency.setBounds(490, 161, 126, 61);
		txtFrequency.setHorizontalAlignment(SwingConstants.CENTER);
		txtFrequency.setBackground(new Color(255, 204, 204));
		txtFrequency.setBorder(BorderFactory.createLineBorder(Color.black));
		txtFrequency.setHorizontalAlignment(SwingConstants.CENTER);
		txtFrequency.setText("3");
	}
	
	//initialize the indicator
	public void initIndicator(){
		indicatorPane = new JTextPane();
		indicatorPane.setBounds(22, 13, 311, 296);
		indicatorPane.setBackground(new Color(255, 204, 204));
		indicatorPane.setFont(new Font("Courier New", Font.PLAIN, 18));
		indicatorPane.setBorder(BorderFactory.createLineBorder(Color.black));
		indicatorPane.setEditable(false);
		
		//Calling the indicator
		ledIndicator = new Indicator(workStatus);
		ledIndicator.setBorder(BorderFactory.createLineBorder(Color.black));
		ledIndicator.setBounds(22, 13, 311, 296);
		indicatorPane.add(ledIndicator);
	}
	
	//initialize the console
	public void initConsole(){
		// Console part 
		lblConsole = new JTextPane();
		lblConsole.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblConsole.setText(" Console: ");
		lblConsole.setOpaque(false);
		lblConsole.setBorder(BorderFactory.createLineBorder(
				new Color(211, 211, 211)));
		
		consolePane = new ServerConsole();
		consolePane.setFont(new Font("Courier New", Font.PLAIN, 18));
		consolePane.setBorder(BorderFactory.createLineBorder(
				new Color(211, 211, 211)));
		consolePane.setEditable(false);
		consolePane.setBackground(new Color(211, 211, 211));
		consolePane.setLayout(new BoxLayout(consolePane, BoxLayout.Y_AXIS));
		consolePane.setEditable(false);
		consolePane.setBackground(new Color(211, 211, 211));
		
		consoleScroll = new JScrollPane(consolePane);
		consoleScroll.setOpaque(false);
		consoleScroll.setBorder(BorderFactory.createLineBorder(	
				new Color(211, 211, 211)));
		consoleScroll.setPreferredSize(new Dimension(600, 118));
		
		consolePanel = new JEditorPane();
		consolePanel.setFont(new Font("Courier New", Font.PLAIN, 18));
		consolePanel.setBackground(new Color(211, 211, 211));
		consolePanel.setBounds(12, 390, 628, 138);
		consolePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		consolePanel.setLayout(new BoxLayout(consolePanel, BoxLayout.PAGE_AXIS));
		consolePanel.setVisible(true);
		
		consolePanel.add(lblConsole);
		consolePanel.add(consoleScroll);
		
		this.getContentPane().add(consolePanel);
	}
	
	// ServerGUI constructor
	public ServerGUI() {
		this.setTitle("Server");
		this.setBounds(100, 100, 670, 588);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(220, 220, 220));
		panel.setBounds(12, 59, 628, 323);
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		initStartStopBTN();
		
		initLabels();
		panel.add(lblHighValue);
		panel.add(lblLowValue);
		panel.add(lblFrequency);

		initTextFields();
		panel.add(txtHighValue);
		panel.add(txtLowValue);
		panel.add(txtFrequency);
		
		initIndicator();
		panel.add(indicatorPane);
		
        initConsole();
        consolePane.setMessage("Server GUI initialized!");
	}

	//start socket thread
	public void startListening(){
		server = new ServerConnector(9090, consolePane);
		socketThread = new Thread(server);
		int high, low, frequency;
		high = Integer.parseInt(txtHighValue.getText());
		low = Integer.parseInt(txtLowValue.getText());
		frequency = Integer.parseInt(
						txtFrequency.getText());
		server.setValues(high, low, frequency);
		socketThread.start();;
	}
	
	// Main function to launch server application
	public static void main(String[] args) {
		ServerGUI serverGui = new ServerGUI();
		serverGui.setVisible(true);
		serverGui.startListening();
	}
}
