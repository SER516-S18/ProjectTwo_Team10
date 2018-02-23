
import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Panel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class ClientWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientWindow clientwindow = new ClientWindow();
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
		frame.getContentPane().add(startStop);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 220, 220));
		panel.setBounds(15, 58, 748, 300);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(173, 216, 230));
		panel_1.setBounds(433, 16, 157, 38);
		panel.add(panel_1);

		JLabel highestValue = new JLabel("Highest Value:");
		highestValue.setFont(new Font("Courier New", Font.PLAIN, 16));
		panel_1.add(highestValue);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 182, 193));
		panel_2.setBounds(433, 70, 157, 38);
		panel.add(panel_2);

		JLabel lowestValue = new JLabel("Lowest Value:");
		lowestValue.setFont(new Font("Courier New", Font.PLAIN, 16));
		panel_2.add(lowestValue);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(173, 216, 230));
		panel_3.setBounds(433, 124, 157, 38);
		panel.add(panel_3);

		JLabel average = new JLabel("Average:");
		average.setFont(new Font("Courier New", Font.PLAIN, 16));
		panel_3.add(average);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 182, 193));
		panel_4.setBounds(433, 178, 157, 38);
		panel.add(panel_4);

		JLabel channels = new JLabel("Channels:");
		channels.setFont(new Font("Courier New", Font.PLAIN, 16));
		panel_4.add(channels);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(173, 216, 230));
		panel_5.setBounds(433, 232, 157, 41);
		panel.add(panel_5);

		JLabel frequencyHZ = new JLabel("Frequency(Hz):");
		frequencyHZ.setFont(new Font("Courier New", Font.PLAIN, 16));
		panel_5.add(frequencyHZ);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 182, 193));
		panel_6.setBounds(618, 16, 103, 38);
		panel.add(panel_6);

		JLabel lblA = new JLabel("");
		lblA.setFont(new Font("Courier New", Font.PLAIN, 16));
		panel_6.add(lblA);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(173, 216, 230));
		panel_7.setBounds(618, 70, 103, 38);
		panel.add(panel_7);

		JLabel label_1 = new JLabel("");
		label_1.setFont(new Font("Courier New", Font.PLAIN, 16));
		panel_7.add(label_1);

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(255, 182, 193));
		panel_8.setBounds(618, 124, 103, 38);
		panel.add(panel_8);

		JLabel label_2 = new JLabel("");
		label_2.setFont(new Font("Courier New", Font.PLAIN, 16));
		panel_8.add(label_2);

		String[] noChannels = {"1", "2", "3", "4", "5"};    
		JComboBox comboBox = new JComboBox(noChannels);
		setNoOfChannels(1);
		comboBox.addActionListener(this);
		comboBox.setFont(new Font("Courier New", Font.PLAIN, 16));
		comboBox.setBackground(new Color(173, 216, 230));
		comboBox.setBounds(618, 190, 103, 26);
		panel.add(comboBox);

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(255, 182, 193));
		panel_9.setBounds(618, 232, 103, 41);
		panel.add(panel_9);

		JLabel label_3 = new JLabel("");
		label_3.setFont(new Font("Courier New", Font.PLAIN, 16));
		panel_9.add(label_3);

		Panel panel_10 = new Panel();
		panel_10.setBackground(new Color(255, 182, 193));
		panel_10.setBounds(10, 16, 406, 257);
		panel.add(panel_10);

		JTextPane txtpnConsole = new JTextPane();
		//txtpnConsole.setFont(new Font("Courier New", Font.PLAIN, 16));
		txtpnConsole.setBackground(SystemColor.controlHighlight);
		txtpnConsole.setText("Console:");
		txtpnConsole.setBounds(15, 388, 748, 90);
		txtpnConsole.setEditable(false);
		frame.getContentPane().add(txtpnConsole);

		startClient();
	}

	/**
	 * Action listner for drop down menu
	 */
	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox)e.getSource();
		Integer no = Integer.parseInt((String)cb.getSelectedItem());
		setNoOfChannels(no);
	}

	public static void shrinkTo(ArrayList<Integer> list, int channel) {
		int size = list.size();
		if (channel >= size) return;
		for (int i = channel; i < size; i++) {
			list.remove(list.size() - 1);
		}
	}


	public static void startClient() {
		(new Thread() {
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				try {
					Socket socket = new Socket("localhost", 9090);
					ArrayList<Integer> arrayList = new ArrayList<Integer>();
					BufferedWriter out = new BufferedWriter(
							new OutputStreamWriter(socket.getOutputStream()));
					ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());

					while (true) {
						Object object = objectInput.readObject();
						arrayList =  (ArrayList<Integer>) object;
						String s = getNoOfChannels().toString();
						out.write(s);
						System.out.println(s);
						out.newLine();
						out.flush();
						shrinkTo(arrayList, Integer.parseInt(s));
						System.out.println(arrayList);
						arrayList.clear();
						Thread.sleep(1000);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
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
