package client;

import java.net.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;

/**
 * @SER516 ProjecTwo_Team10
 * @author Lei Zhang
 * @version 2.0 
 * Connector to clients using socket
 */

public class ClientConnector implements Runnable{
	private Socket socket = null;
	private DataInputStream in = null;
	private ClientConsole console;
	private int high, low, frequency;
	private Timer timer;
	boolean stopped;
	private ObjectOutputStream objectOutput;
	private boolean listen = true;
	private int port;
	
	//constructor with port
	public ClientConnector(int port, ClientConsole console){
		this.console = console;
		this.port = port;
		console.setMessage("connecting to server");
	}
	/**set the highest ,lowest value and frequency
	 * @param high indicates the higher bound
	 * @param low indicates the lower bound
	 * @param frequency indicates the frequency of generate numbers
	 */
	//TODO: change this to get values from the server message
	public void setValues(int high, int low, int frequency){
		this.high = high;
		this.low = low;
		this.frequency = frequency;
		return;
	}
	
	//starts client socket and try to connect the server
	public void run(){
		while (listen){
			try{							
				socket = new Socket("localhost", 9090);
				ArrayList<Integer> arrayList = new ArrayList<Integer>();
				BufferedWriter out = new BufferedWriter(
						new OutputStreamWriter(socket.getOutputStream()));
				BufferedReader in = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));

				Object object = (new ObjectInputStream
						(socket.getInputStream())).readObject();

				//TODO: logic control
				arrayList =  (ArrayList<Integer>) object;
				console.setMessage(arrayList.toString());
				while (arrayList.size() > 0) {
					
						ClientHighestAndLowestVal.readList(arrayList);
						ClientAverageValue.calculateAverage(arrayList);
						int highest = ClientHighestAndLowestVal.getHighestVal();
						int lowest = ClientHighestAndLowestVal.getLowestVal();
						int avg = ClientAverageValue.getAverage();

					arrayList.clear();
					object = (new ObjectInputStream(socket.getInputStream())).readObject();
					arrayList = (ArrayList<Integer>) object;
				}
			}catch(IOException i){
				console.setMessage("socket disconnected");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void changeChannel(){
		//TODO: change the number of channels and send the number of channel as message to server
	}
	
	public void stop(){
		//TODO: button start
	}
	
	public void start(){
		//TODO: button stop
	}
}
