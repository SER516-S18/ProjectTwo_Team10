import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextPane;
import javax.swing.text.html.HTMLDocument;

public class ServerConsole {
	static JTextPane console = ServerGUI.getTextPane();
	private final static Logger LOGGER = Logger.getLogger(ServerGUI.class.getName());

	public static void setErrorMessage(String error) {
		try {
			console.setContentType( "text/html" );
			HTMLDocument doc=(HTMLDocument) console.getStyledDocument();
            doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()),"<span style=\"color:red\">" + new Date() + " " + error + "</span> <br>");
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "Error adding message to console", ex);
		}
	}
	
	public static void setMessage(String message) {
		try {
			console.setContentType( "text/html" );
			HTMLDocument doc=(HTMLDocument) console.getStyledDocument();
			doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()),"<span style=\"color:black\">" + new Date() + " " + message + "</span> <br>");
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "Error adding message to console", ex);
		}
	}
}
