package server;

import java.util.Date;
import javax.swing.JEditorPane;
import javax.swing.text.html.HTMLDocument;

/**
 * @SER516 ProjecTwo_Team10
 * @author Hari Siddarth V Kesavan
 * @version 1.0
 * To print messages and errors on console in server GUI
*/

public class ServerConsole {
	static JEditorPane console = ServerGUI.getTextPane();

	public static void setErrorMessage(String error) {
		try {
			console.setContentType( "text/html" );
			HTMLDocument doc=(HTMLDocument) console.getDocument();
            doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()),
               "[ " + new Date() +  " ] " + "<span style=\"color:red\">"  
                + error + "</span> <br>");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setMessage(String message) {
		try {
			console.setContentType( "text/html" );
			HTMLDocument doc=(HTMLDocument) console.getDocument();
			doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()), 
			  "[ " +  new Date() + " ] " + "<span style=\"color:black\">" 
			   + message + "</span> <br>");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
