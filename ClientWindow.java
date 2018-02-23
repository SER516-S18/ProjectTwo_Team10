
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
    private static PlotDiagramPanel panel_10;
    private static JLabel panel_6;
    private static JLabel panel_7;
    private static JLabel panel_8;
    private static JLabel panel_9;

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

        panel_6 = new JLabel();
        panel_6.setBackground(new Color(255, 182, 193));
        panel_6.setOpaque(true);
        panel_6.setBounds(618, 16, 103, 38);
        panel.add(panel_6);

        JLabel lblA = new JLabel("");
        lblA.setFont(new Font("Courier New", Font.PLAIN, 16));
        panel_6.add(lblA);

        panel_7 = new JLabel();
        panel_7.setBackground(new Color(173, 216, 230));
        panel_7.setOpaque(true);
        panel_7.setBounds(618, 70, 103, 38);
        panel.add(panel_7);

        JLabel label_1 = new JLabel("");
        label_1.setFont(new Font("Courier New", Font.PLAIN, 16));
        panel_7.add(label_1);

        panel_8 = new JLabel();
        panel_8.setBackground(new Color(255, 182, 193));
        panel_8.setOpaque(true);
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

        panel_9 = new JLabel();
        panel_9.setBackground(new Color(255, 182, 193));
        panel_9.setOpaque(true);
        panel_9.setBounds(618, 232, 103, 41);
        panel.add(panel_9);

        JLabel label_3 = new JLabel("");
        label_3.setFont(new Font("Courier New", Font.PLAIN, 16));
        panel_9.add(label_3);

        panel_10 = new PlotDiagramPanel();
        panel_10.setBackground(new Color(255, 182, 193));
        panel_10.setBounds(10, 16, 406, 257);
        panel.add(panel_10);

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
    }

    public static JTextPane getTextPane() {
        return console;
    }

    /**
     * Action listner for drop down menu
     */
    public void actionPerformed(ActionEvent e) {
        String componentName = e.getActionCommand();
        if (componentName.equals("Start/Stop")) {
            startClient();
        } else {
            JComboBox cb = (JComboBox)e.getSource();
            Integer no = Integer.parseInt((String)cb.getSelectedItem());
            setNoOfChannels(no);
        }
    }

    public static void shrinkTo(ArrayList<Integer> list, int channel) {
        int size = list.size();
        if (channel >= size) return;
        for (int i = channel; i < size; i++) {
            list.remove(list.size() - 1);
        }
    }


    private static void update(List<Integer> list, int h, int l, int av) {
        panel_10.addData(list);
        panel_6.setText(Integer.toString(h));
        panel_7.setText(Integer.toString(l));
        panel_8.setText(Integer.toString(av));
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

                    while (true) {
                        Object object = (new ObjectInputStream(socket.getInputStream())).readObject();
                        arrayList =  (ArrayList<Integer>) object;
                        String s = getNoOfChannels().toString();
                        out.write(s);
                        System.out.println(s);
                        out.newLine();
                        out.flush();
                        shrinkTo(arrayList, Integer.parseInt(s));
                        System.out.println(arrayList);
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
