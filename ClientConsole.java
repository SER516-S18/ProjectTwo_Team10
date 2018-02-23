import javax.swing.*;
import java.awt.*; 
import java.util.Date;
import java.util.logging.*;
import javax.swing.text.html.HTMLDocument;
/**
* @SER516-Project 2
* @author  Binbin Yan
* @version 0.9
* @since   02/17/2018
*/
public class ClientConsole{
    
    static JTextPane console =  ClientWindow.getTextPane();
    private final static Logger printing = Logger.getLogger(ClientWindow.class.getName());
    
    public static void setErrorMessage(String error) {
	    try {
		    console.setContentType( "text/html" );
		    HTMLDocument disp1=(HTMLDocument) console.getStyledDocument();
		    disp1.insertAfterEnd(disp1.getCharacterElement(disp1.getLength()),"<span style=\"color:red\">" + new Date() + " " + error + "</span> <br>");
	    } catch (Exception ex) {
		    printing.log(Level.SEVERE, "Error adding message to console", ex);
	    }
    }
	
    public static void setMessage(String message) {
	    try {
		    console.setContentType( "text/html" );
		    HTMLDocument disp2=(HTMLDocument) console.getStyledDocument();
		    disp2.insertAfterEnd(disp2.getCharacterElement(disp2.getLength()),"<span style=\"color:black\">" + new Date() + " " + message + "</span> <br>");
	    } catch (Exception ex) {
		    printing.log(Level.SEVERE, "Error adding message to console", ex);
	    }
    }
}
