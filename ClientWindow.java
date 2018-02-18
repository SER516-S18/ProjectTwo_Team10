
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientWindow frame = new ClientWindow();
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
	public ClientWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnStartstop = new JButton("Start/Stop");
		btnStartstop.setBackground(new Color(255, 182, 193));
		btnStartstop.setFont(new Font("Courier New", Font.PLAIN, 16));
		btnStartstop.setBounds(604, 16, 159, 29);
		contentPane.add(btnStartstop);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBounds(15, 58, 748, 300);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(433, 16, 157, 38);
		panel.add(panel_1);
		
		JLabel lblHighestValue = new JLabel("Highest Value:");
		lblHighestValue.setFont(new Font("Courier New", Font.PLAIN, 16));
		panel_1.add(lblHighestValue);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 182, 193));
		panel_2.setBounds(433, 70, 157, 38);
		panel.add(panel_2);
		
		JLabel lblLowestValue = new JLabel("Lowest Value:");
		lblLowestValue.setFont(new Font("Courier New", Font.PLAIN, 16));
		panel_2.add(lblLowestValue);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.activeCaption);
		panel_3.setBounds(433, 124, 157, 38);
		panel.add(panel_3);
		
		JLabel lblNewLabel = new JLabel("Average:");
		lblNewLabel.setFont(new Font("Courier New", Font.PLAIN, 16));
		panel_3.add(lblNewLabel);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 182, 193));
		panel_4.setBounds(433, 178, 157, 38);
		panel.add(panel_4);
		
		JLabel lblChannels = new JLabel("Channels:");
		lblChannels.setFont(new Font("Courier New", Font.PLAIN, 16));
		panel_4.add(lblChannels);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.activeCaption);
		panel_5.setBounds(433, 232, 157, 41);
		panel.add(panel_5);
		
		JLabel lblFrequencyhz = new JLabel("Frequency(Hz):");
		lblFrequencyhz.setFont(new Font("Courier New", Font.PLAIN, 16));
		panel_5.add(lblFrequencyhz);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 182, 193));
		panel_6.setBounds(618, 16, 103, 38);
		panel.add(panel_6);
		
		JLabel lblA = new JLabel("");
		lblA.setFont(new Font("Courier New", Font.PLAIN, 16));
		panel_6.add(lblA);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(SystemColor.activeCaption);
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
		comboBox.setBackground(SystemColor.activeCaption);
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
		txtpnConsole.setFont(new Font("Courier New", Font.PLAIN, 16));
		txtpnConsole.setBackground(SystemColor.controlHighlight);
		txtpnConsole.setText("Console:");
		txtpnConsole.setBounds(15, 388, 748, 90);
		contentPane.add(txtpnConsole);

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


    public static void startClient() {
        (new Thread() {
            @Override
            public void run() {
                try {
                   Socket socket = new Socket("localhost", 60010);
                   BufferedWriter out = new BufferedWriter(
                                            new OutputStreamWriter(socket.getOutputStream()));
                   BufferedReader in = new BufferedReader(
                                            new InputStreamReader(socket.getInputStream())); 
                   while (true) {
                       String s = getNoOfChannels().toString();
                       out.write(s);
                       out.newLine();
                       out.flush();
                       System.out.println(in.readLine());
                       Thread.sleep(200);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
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
