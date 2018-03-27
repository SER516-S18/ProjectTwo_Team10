package server;

import java.util.Date;
import javax.swing.JTextPane;
import javax.swing.text.html.HTMLDocument;

/**
 * @SER516 ProjecTwo_Team10
 * @author Hari Siddarth V Kesavan
 * @version 1.0
 * To print messages and errors on console in server GUI
*/

@SuppressWarnings("serial")
public class ServerConsole extends JTextPane{
	public void setErrorMessage(String error) {
		try {
			this.setContentType( "text/html" );
			HTMLDocument doc=(HTMLDocument) this.getDocument();
            doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()),
               "[ " + new Date() +  " ] " + "<span style=\"color:red\">"  
                + error + "</span> <br>");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setMessage(String message) {
		try {
			this.setContentType( "text/html" );
			HTMLDocument doc=(HTMLDocument) this.getDocument();
			doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()), 
			  "[ " +  new Date() + " ] " + "<span style=\"color:black\">" 
			   + message + "</span> <br>");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
