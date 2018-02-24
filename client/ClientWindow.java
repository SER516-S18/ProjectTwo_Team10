package client;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class ClientWindow extends JFrame implements ActionListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	static JTextPane console = new JTextPane();
	private static PlotDiagramPanel diagramPanel;
	private static JLabel highestValLabel;
	private static JLabel lowestValLabel;
	private static JLabel averageValLabel;
	private JTextField textFieldFrequency;
	private static int clientState;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientWindow clientwindow = new ClientWindow();
					ClientConsole.setMessage("Client Start");
					clientwindow.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		frame = new JFrame();
		frame.setTitle("Client");
		frame.setBounds(100, 100, 800, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

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

		highestValLabel = new JLabel();
		highestValLabel.setBackground(new Color(255, 182, 193));
		highestValLabel.setOpaque(true);
		highestValLabel.setBounds(618, 16, 103, 38);
		mainPanel.add(highestValLabel);

		JLabel lblA = new JLabel("");
		lblA.setFont(new Font("Courier New", Font.PLAIN, 16));
		highestValLabel.add(lblA);

		lowestValLabel = new JLabel();
		lowestValLabel.setBackground(new Color(173, 216, 230));
		lowestValLabel.setOpaque(true);
		ClientWindow.lowestValLabel.setBounds(618, 70, 103, 38);
		mainPanel.add(ClientWindow.lowestValLabel);

		JLabel label_1 = new JLabel("");
		label_1.setFont(new Font("Courier New", Font.PLAIN, 16));
		ClientWindow.lowestValLabel.add(label_1);

		averageValLabel = new JLabel();
		averageValLabel.setBackground(new Color(255, 182, 193));
		averageValLabel.setOpaque(true);
		averageValLabel.setBounds(618, 124, 103, 38);
		mainPanel.add(averageValLabel);

		JLabel label_2 = new JLabel("");
		label_2.setFont(new Font("Courier New", Font.PLAIN, 16));
		averageValLabel.add(label_2);

		String[] noChannels = {"1", "2", "3", "4", "5"};
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboBox = new JComboBox(noChannels);
		setNoOfChannels(1);
		comboBox.addActionListener(this);
		comboBox.setFont(new Font("Courier New", Font.PLAIN, 16));
		comboBox.setBackground(new Color(173, 216, 230));
		comboBox.setBounds(618, 190, 103, 26);
		mainPanel.add(comboBox);

		textFieldFrequency = new JTextField();
		textFieldFrequency.setFont(new Font("Courier New", Font.PLAIN, 20));
		textFieldFrequency.setBackground(new Color(255, 182, 193));
		textFieldFrequency.setBounds(618, 232, 103, 41);
		mainPanel.add(textFieldFrequency);
		textFieldFrequency.setColumns(10);

		diagramPanel = new PlotDiagramPanel();
		diagramPanel.setBackground(new Color(255, 182, 193));
		diagramPanel.setBounds(10, 16, 406, 257);
		mainPanel.add(diagramPanel);

		JEditorPane consolePanel = new JEditorPane();
		console.setFont(new Font("Courier New", Font.PLAIN, 18));
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
		console.setEditable(false);
		console.setBorder(BorderFactory.createLineBorder(new Color(211, 211, 211)));
		console.setBackground(new Color(211, 211, 211));
		console.setLayout(new BoxLayout(console, BoxLayout.Y_AXIS));
		console.setEditable(false);
		console.setBackground(new Color(211, 211, 211));
		frame.getContentPane().add(consolePanel);
		startClient();
	}

	public static JTextPane getTextPane() {
		return console;
	}

	/**
	 * Action listner for drop down menu
	 */
	public void actionPerformed(ActionEvent e) {
		if (getClientState() == 0) {
			setClientState(1);
			@SuppressWarnings("rawtypes")
			JComboBox cb = (JComboBox)e.getSource();
			Integer no = Integer.parseInt((String)cb.getSelectedItem());
			setNoOfChannels(no);
			
		} else if (getClientState() == 1) {
			setClientState(0);
		}
	}

	public static void shrinkTo(ArrayList<Integer> list, int channel) {
		int size = list.size();
		if (channel >= size) return;
		for (int i = channel; i < size; i++) {
			list.remove(list.size() - 1);
		}
	}

	/**
	 * Update the date received.
	 */
	private static void update(List<Integer> list, int h, int l, int av) {
		diagramPanel.addData(list);
		highestValLabel.setText(Integer.toString(h));
		lowestValLabel.setText(Integer.toString(l));
		averageValLabel.setText(Integer.toString(av));
	}


	
	synchronized public static int getClientState() {
		return clientState;
	}
	
	synchronized public static void setClientState(int state) {
		clientState = state;
	}

	public static void startClient() {
		(new Thread() {
			@SuppressWarnings({ "unchecked", "resource" })
			@Override
			public void run() {
				try {
					Socket socket = new Socket("localhost", 9090);
					ArrayList<Integer> arrayList = new ArrayList<Integer>();
					BufferedWriter out = new BufferedWriter(
							new OutputStreamWriter(socket.getOutputStream()));

					while (true) {
						Object object = (new ObjectInputStream
								 (socket.getInputStream())).readObject();
						arrayList =  (ArrayList<Integer>) object;
						String s = getNoOfChannels().toString();
						out.write(s);
						out.newLine();
						out.flush();
						shrinkTo(arrayList, Integer.parseInt(s));
						while (getClientState() == 0)
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						ClientHighestAndLowestVal.readList(arrayList);
						ClientAverageValue.calculateAverage(arrayList);
						int highest = ClientHighestAndLowestVal.getHighestVal();
						int lowest = ClientHighestAndLowestVal.getLowestVal();
						int avg = ClientAverageValue.getAverage();
						update(arrayList, highest, lowest, avg);
						arrayList.clear();
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	synchronized static Integer getNoOfChannels() {
		return noOfChannels;
	}

	synchronized static void setNoOfChannels(Integer x) {
		noOfChannels = x;
	}

	private static Integer noOfChannels;
}
