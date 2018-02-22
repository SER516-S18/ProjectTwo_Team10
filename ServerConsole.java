import java.util.Date;
import javax.swing.JEditorPane;
import javax.swing.text.html.HTMLDocument;

public class ServerConsole {
	static JEditorPane console = ServerGUI.getTextPane();

	public static void setErrorMessage(String error) {
		try {
			console.setContentType( "text/html" );
			HTMLDocument doc=(HTMLDocument) console.getDocument();
            doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()),"<span style=\"color:red\">" + "[ " + new Date() + " ] " + error + "</span> <br>");
		} catch (Exception e) {
			console.setText("Error printing to Console");
		}
	}
	
	public static void setMessage(String message) {
		try {
			console.setContentType( "text/html" );
			HTMLDocument doc=(HTMLDocument) console.getDocument();
			doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()),"<span style=\"color:black\">" + "[ " + new Date() + " ] " + message + "</span> <br>");
		} catch (Exception e) {
			console.setText("Error printing to Console");
		}
	}
}
