import java.io.IOException;
import java.net.Socket;
/**
 * @SER516 ProjecTwo_Team10
 * @author Ruihao Zhou
 * @Version 2.0
 * User Story #4: StartStopButton(Server)
 */


public class startStopButton {
    
    Socket serverSocket = null;
    
    public static void start() {
        try {
            serverSocket = new Socket("localhost", 1516);
            System.out.println("Connected succeffsully");
        } catch (Exception e) {
            e.printStackTrace("Start Now");
        }
    }
    
    public static void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace("Stop Now");
        }
    }
   
}
