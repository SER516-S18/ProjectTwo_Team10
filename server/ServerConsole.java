package server;

import java.util.Date;
import javax.swing.JEditorPane;
import javax.swing.text.html.HTMLDocument;

/**
* SER 516 - Lab 2
* @author Hari Siddarth V Kesavan
* @version 0.4
* ServerConsole is the class to print messages and errors on console in server
*/

public class ServerConsole {
	static JEditorPane console = ServerGUI.getTextPane();

	public static void setErrorMessage(String error) {
		try {
			console.setContentType( "text/html" );
			HTMLDocument doc=(HTMLDocument) console.getDocument();
            		doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()),"[ " + new Date() +  " ] " + "<span style=\"color:red\">"  + error + "</span> <br>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setMessage(String message) {
		try {
			console.setContentType( "text/html" );
			HTMLDocument doc=(HTMLDocument) console.getDocument();
			doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()), "[ " +  new Date() + " ] " + "<span style=\"color:black\">"  + message + "</span> <br>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
