package client;
import javax.swing.*;

/**
 * Reads frequency from the GUI
 * @author RaghavendraPrasadVemula
 * @version 1.0
 */

public class ClientFrequency{

    final int defaultFrequency = 1;

    /**
     * Takes input field for frequency and returns it if valid, or returns default value
     * @param frequency
     */

    public int getFrequency(JTextPane frequency){

        int frequencyValue = defaultFrequency;
        if(frequency.getText() != ""){
            try{
                frequencyValue = Integer.parseInt(frequency.getText());
            } catch(Exception e){
                System.out.println("Non-integer value entered for frequency, default used.");
            }
        }

        return frequencyValue;
    }

}