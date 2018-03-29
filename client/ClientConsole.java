package client;
import javax.swing.*;
import java.awt.*; 
import java.util.Date;
import java.util.logging.*;
import javax.swing.text.html.HTMLDocument;

/**
  * @SER516  Project two
  * @author  Binbin Yan
  * @version 1.0
  * @since   02/21/2018
  * User Story #13: Console on Client GUI.
  */

public class ClientConsole {
    private final static Logger printing = Logger.getLogger(ClientGUI.class.getName());
	
	/**
      * Get srror string and display it on the console.
      * @param error
      */
    public static void setErrorMessage(String error, JTextPane console) {
        try {
			console.setContentType( "text/html" );
			HTMLDocument disp1 = (HTMLDocument) console.getStyledDocument();
			disp1.insertAfterEnd(disp1.getCharacterElement(disp1.getLength()), "<span style=\"color:red\">" + new Date() + " " + error + "</span> <br>");
        } catch (Exception ex) {
			printing.log(Level.SEVERE, "Error adding message to console", ex);
        }
    }

    /**
      * Get message string and display it on the console.
      * @param message
      */
    public static void setMessage(String message, JTextPane console) {
        try {
			console.setContentType("text/html");
			HTMLDocument disp2 = (HTMLDocument) console.getStyledDocument();
			disp2.insertAfterEnd(disp2.getCharacterElement(disp2.getLength()),"<span style=\"color:black\">" + new Date() + " " + message + "</span> <br>");
        } catch (Exception ex) {
			printing.log(Level.SEVERE, "Error adding message to console", ex);
        }
    }
}
