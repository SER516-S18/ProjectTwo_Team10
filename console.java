import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
import java.awt.Dimension;

/**
* @author  Binbin Yan
* @version 0.9
* @since   02/17/2018
*/
public class console extends JPanel{
    
    
    Font f = new Font("Serif", Font.BOLD, 15);
    
    /**
    * Constructor.
    */
    public console (){
        
        getpanel();
        
    }
    
    /**
    * Set panel's property.
    * background is white.
    */
    public void getpanel (){
        setSize(new Dimension(500,100));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); 
        setBackground(Color.WHITE); 
    }
    
    /**
    * Set Label
    * Add Label
    */
    public void getstart(){
        JLabel start = new JLabel("start button is clicked"); 
        start.setFont(f); 
        add(start);  
    }
    
    public void getstop(){
        JLabel stop = new JLabel("stop button is clicked"); 
        stop.setFont(f); 
        add(stop);  
    }
    
    public void getHvalue(){
        JLabel Hvalue = new JLabel("Highest value is set"); 
        Hvalue.setFont(f); 
        add(Hvalue);  
    }
    
    public void getLvalue(){
        JLabel Lvalue = new JLabel("Lowest value is set"); 
        Lvalue.setFont(f); 
        add(Lvalue);  
    }
    
    public void getAverage(){
        JLabel Average = new JLabel("Average value is set"); 
        Average.setFont(f); 
        add(Average);
    }
    
    public void getChannel(){
        JLabel Channel = new JLabel("Channel is selected"); 
        Channel.setFont(f); 
        add(Channel);
    }
    
    public void getFrequency(){
        JLabel Frequency = new JLabel("Frequency is set"); 
        Frequency.setFont(f); 
        add(Frequency);
    }
    
    /**
    * Main method
    * To test panel
    public static void main(String[] args){
        JFrame f=new JFrame();
        f.setSize(200,200);    
        f.setLayout(null); 
        f.setVisible(true); 
        console p = new console();
        f.add(p);
    }
    **/
    
    
    
    
    
}