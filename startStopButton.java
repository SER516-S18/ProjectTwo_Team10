import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
/**
 * @SER516 ProjecTwo_Team10
 * @author Ruihao Zhou
 * @Version 1.0
 * User Story #4: StartStopButton(Server)
 */


public class startStopButton {
    public static void main(String[] args){
        JFrame frame = new JFrame("Client");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        JPanel panel = new JPanel();
        frame.add(panel);
        JButton button = new JButton("Start/Stop");
        panel.add(button)
    }

    static class Action implements ActionListener{
        public void actionStarted(ActionEvent e){
            JFrame frame2 = new JFrame("Clicked");
            frame2.setVisable(true);
            frame2.setSize(200,200);
            JLabel label = new JLabel("Clicked done")
        }
    }
}