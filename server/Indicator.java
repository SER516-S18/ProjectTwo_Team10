package server;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @SER516 ProjecTwo_Team10
 * @author Divya Yadamreddi	
 * @version	1.0
 * This class is an indicator on the server side that shows that the server
 * is working.The lights blinks in green if the server is working and blinks
 * in red if the server is not working.
 */

public class Indicator extends JPanel{

    private static final long serialVersionUID = 1L;
    private Color displayColor, status, backgroundColor;

    //Initialize the Light on the panel.
    Indicator(int error) {
        displayColor = getBackground();
        backgroundColor = getBackground();
        update(error);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            boolean hidden = false;
            @Override
            public void run() {
                if (hidden) {
                    setColor(status);
                } else {
                    setColor(backgroundColor);
                }
                hidden = !hidden;
            }
        }, 1000, 300);
    }

    @Override
    public void paint(Graphics g) {
        if (g==null) return;
        Graphics2D ledIndicator = (Graphics2D) g;
        ledIndicator.setColor(displayColor);
        ledIndicator.fillOval(50, 50, 175, 175);
    }

    private void setColor(Color color){
        displayColor = color;
        this.paint(this.getGraphics());
    }

  //Sets the color based on the error code
    public void update(int status) {
        if (status == 0) {
            this.status=  Color.GREEN;
        } else {
            this.status = Color.RED;
        }
    }
}
