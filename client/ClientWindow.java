package client;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class ClientWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextPane console;
	private PlotDiagramPanel diagramPanel;
	private JLabel highestValLabel;
	private JLabel lowestValLabel;
	private JLabel averageValLabel;
	private JTextField textFieldFrequency;
	private int clientState = 1;
	private Socket socket;
	private int noOfChannels;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ClientWindow clientwindow = new ClientWindow();
	}

	private void initUIComponents() {
		frame = new JFrame();
		frame.setTitle("Client");
		frame.setBounds(100, 100, 800, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		diagramPanel = new PlotDiagramPanel();
		diagramPanel.setBackground(new Color(255, 182, 193));
		diagramPanel.setBounds(10, 16, 406, 257);

		highestValLabel = new JLabel();
		highestValLabel.setBackground(new Color(255, 182, 193));
		highestValLabel.setOpaque(true);
		highestValLabel.setBounds(618, 16, 103, 38);

		lowestValLabel = new JLabel();
		lowestValLabel.setBackground(new Color(173, 216, 230));
		lowestValLabel.setOpaque(true);
		lowestValLabel.setBounds(618, 70, 103, 38);

		averageValLabel = new JLabel();
		averageValLabel.setBackground(new Color(255, 182, 193));
		averageValLabel.setOpaque(true);
		averageValLabel.setBounds(618, 124, 103, 38);

		textFieldFrequency = new JTextField();
		textFieldFrequency.setFont(new Font("Courier New", Font.PLAIN, 20));
		textFieldFrequency.setBackground(new Color(255, 182, 193));
		textFieldFrequency.setBounds(618, 232, 103, 41);
		textFieldFrequency.setColumns(10);

		console = new JTextPane();
		console.setEditable(false);
		console.setBorder(BorderFactory.createLineBorder(new Color(211, 211, 211)));
		console.setBackground(new Color(211, 211, 211));
		console.setLayout(new BoxLayout(console, BoxLayout.Y_AXIS));
		console.setEditable(false);
		console.setBackground(new Color(211, 211, 211));
		console.setFont(new Font("Courier New", Font.PLAIN, 18));		
		ClientConsole.setMessage("Client Start", console);
	}

	/**
	 * Create the frame.
	 */
	public ClientWindow() {
		initUIComponents();
		JButton startStop = new JButton("Start/Stop");
		startStop.setBackground(new Color(255, 182, 193));
		startStop.setFont(new Font("Courier New", Font.PLAIN, 16));
		startStop.setBounds(604, 16, 159, 29);
		startStop.addActionListener(this);
		frame.getContentPane().add(startStop);

		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(220, 220, 220));
		mainPanel.setBounds(15, 58, 748, 300);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);

		JPanel highestValNamePanel = new JPanel();
		highestValNamePanel.setBackground(new Color(173, 216, 230));
		highestValNamePanel.setBounds(433, 16, 157, 38);
		mainPanel.add(highestValNamePanel);

		JLabel highestValNameLabel = new JLabel("Highest Value:");
		highestValNameLabel.setFont(new Font("Courier New", Font.PLAIN, 16));
		highestValNamePanel.add(highestValNameLabel);

		JPanel lowestValNamePanel = new JPanel();
		lowestValNamePanel.setBackground(new Color(255, 182, 193));
		lowestValNamePanel.setBounds(433, 70, 157, 38);
		mainPanel.add(lowestValNamePanel);

		JLabel lowestValNameLabel = new JLabel("Lowest Value:");
		lowestValNameLabel.setFont(new Font("Courier New", Font.PLAIN, 16));
		lowestValNamePanel.add(lowestValNameLabel);

		JPanel averageValNamePanel = new JPanel();
		averageValNamePanel.setBackground(new Color(173, 216, 230));
		averageValNamePanel.setBounds(433, 124, 157, 38);
		mainPanel.add(averageValNamePanel);

		JLabel averageValNameLabel = new JLabel("Average:");
		averageValNameLabel.setFont(new Font("Courier New", Font.PLAIN, 16));
		averageValNamePanel.add(averageValNameLabel);

		JPanel channelNamePanel = new JPanel();
		channelNamePanel.setBackground(new Color(255, 182, 193));
		channelNamePanel.setBounds(433, 178, 157, 38);
		mainPanel.add(channelNamePanel);

		JLabel channelNameLabel = new JLabel("Channels:");
		channelNameLabel.setFont(new Font("Courier New", Font.PLAIN, 16));
		channelNamePanel.add(channelNameLabel);

		JPanel freqNamePanel = new JPanel();
		freqNamePanel.setBackground(new Color(173, 216, 230));
		freqNamePanel.setBounds(433, 232, 157, 41);
		mainPanel.add(freqNamePanel);

		JLabel freqNameLabel = new JLabel("Frequency(Hz):");
		freqNameLabel.setFont(new Font("Courier New", Font.PLAIN, 16));
		freqNamePanel.add(freqNameLabel);

		mainPanel.add(highestValLabel);
		mainPanel.add(lowestValLabel);
		mainPanel.add(averageValLabel);
		
		// JLabel lblA = new JLabel("");
		// lblA.setFont(new Font("Courier New", Font.PLAIN, 16));
		// highestValLabel.add(lblA);

		// JLabel label_1 = new JLabel("");
		// label_1.setFont(new Font("Courier New", Font.PLAIN, 16));
		// lowestValLabel.add(label_1);
		
		// JLabel label_2 = new JLabel("");
		// label_2.setFont(new Font("Courier New", Font.PLAIN, 16));
		// averageValLabel.add(label_2);

		String[] noChannels = {"1", "2", "3", "4", "5"};
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboBox = new JComboBox(noChannels);
		setNoOfChannels(5);
		comboBox.addActionListener(this);
		comboBox.setFont(new Font("Courier New", Font.PLAIN, 16));
		comboBox.setBackground(new Color(173, 216, 230));
		comboBox.setBounds(618, 190, 103, 26);
		mainPanel.add(comboBox);
		mainPanel.add(textFieldFrequency);
		mainPanel.add(diagramPanel);

		JEditorPane consolePanel = new JEditorPane();
		JScrollPane ScrollBar = new JScrollPane(console);
		JTextPane lblConsole = new JTextPane();

		consolePanel.setFont(new Font("Courier New", Font.PLAIN, 18));
		consolePanel.setBackground(new Color(211, 211, 211));
		consolePanel.setBounds(15, 388,748,90);
		consolePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		consolePanel.setLayout(new BoxLayout(consolePanel, BoxLayout.PAGE_AXIS));
		consolePanel.setVisible(true);

		lblConsole.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblConsole.setText("Console: ");
		lblConsole.setOpaque(false);
		lblConsole.setBorder(BorderFactory.createLineBorder(new Color(211, 211, 211)));

		ScrollBar.setOpaque(false);
		ScrollBar.setBorder(BorderFactory.createLineBorder(new Color(211, 211, 211)));
		ScrollBar.setPreferredSize(new Dimension(600, 118));

		consolePanel.add(lblConsole);
		consolePanel.add(ScrollBar);
		
		frame.getContentPane().add(consolePanel);
		frame.setVisible(true);
	}

	/**
	 * Action listner for drop down menu
	 */
	public void actionPerformed(ActionEvent e) {
		if (getClientState() == 0) {
			setClientState(1);
			@SuppressWarnings("rawtypes")
			JComboBox cb = (JComboBox) e.getSource();
			Integer no = Integer.parseInt((String) cb.getSelectedItem());
			setNoOfChannels(no);		
		} else if (getClientState() == 1) {
			setClientState(0);
			startClient();	
		}

	}

	public void shrinkTo(ArrayList<Integer> list, int channel) {
		int size = list.size();
		if (channel >= size) return;
		for (int i = channel; i < size; i++) {
			list.remove(list.size() - 1);
		}
		
	}

	/**
	 * Update the date received.
	 */
	private void update(List<Integer> list, int h, int l, int av) {
		diagramPanel.addData(list);
		highestValLabel.setText(Integer.toString(h));
		lowestValLabel.setText(Integer.toString(l));
		averageValLabel.setText(Integer.toString(av));
	}

	synchronized public int getClientState() {
		return clientState;
	}
	
	synchronized public void setClientState(int state) {
		clientState = state;
	}
	
	public void startClient() {
		(new Thread() {
			@SuppressWarnings({ "unchecked", "resource" })
			@Override
			public void run() {
				while (true) {
					try {
						socket = new Socket("localhost", 9090);
						ArrayList<Integer> arrayList = new ArrayList<Integer>();
						BufferedWriter out = new BufferedWriter(
								new OutputStreamWriter(socket.getOutputStream()));
						BufferedReader in = new BufferedReader(
								new InputStreamReader(socket.getInputStream()));

						Object object = (new ObjectInputStream
								(socket.getInputStream())).readObject();

						arrayList =  (ArrayList<Integer>) object;
						ClientConsole.setMessage(arrayList.toString(), console);
						while (arrayList.size() > 0) {
							if (clientState == 1){
								String s = Integer.toString(getNoOfChannels());
								out.write(s);
								out.newLine();
								out.flush();
								shrinkTo(arrayList, Integer.parseInt(s));
								ClientHighestAndLowestVal.readList(arrayList);
								ClientAverageValue.calculateAverage(arrayList);
								int highest = ClientHighestAndLowestVal.getHighestVal();
								int lowest = ClientHighestAndLowestVal.getLowestVal();
								int avg = ClientAverageValue.getAverage();
								update(arrayList, highest, lowest, avg);
							}

							arrayList.clear();
							object = (new ObjectInputStream(socket.getInputStream())).readObject();
							arrayList = (ArrayList<Integer>) object;
						}

					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}

				}

			}
			
		}).start();
		
	}
	
	protected void finalize() {
		try {
			socket.close();
			System.out.println("fuck");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	synchronized int getNoOfChannels() {
		return noOfChannels;
	}

	synchronized void setNoOfChannels(int x) {
		noOfChannels = x;
	}

}
