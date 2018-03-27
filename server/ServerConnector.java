package server;

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

public class ServerConnector implements Runnable{
	private Socket socket = null;
	private ServerSocket server = null;
	private DataInputStream in = null;
	private GenerateRandomNumbers grn;
	private ServerConsole console;
	private int high, low, frequency;
	private Timer timer;
	boolean stopped;
	private ObjectOutputStream objectOutput;
	
	//constructor with port
	public ServerConnector(int port, ServerConsole console){
		try {
			this.console = console;
			server = new ServerSocket(port);
			console.setMessage("Server started");
			timer = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			if (this.console==null){
				e.printStackTrace();
			}else{
				this.console.setErrorMessage(e.toString());
			}
		}
	}
	/**set the highest ,lowest value and frequency
	 * @param high indicates the higher bound
	 * @param low indicates the lower bound
	 * @param frequency indicates the frequency of generate numbers
	 */
	public void setValues(int high, int low, int frequency){
		this.high = high;
		this.low = low;
		this.frequency = frequency;
		grn = new GenerateRandomNumbers(high, low, 5);
	}
	
	//starts server and waits for a connection
	public void run(){
		try{			
			console.setMessage("Waiting for a client ...");
			
			socket = server.accept();
			stopped = false;
			console.setMessage("Client accepted");
			
			// takes input from the client socket
			in = new DataInputStream(
					new BufferedInputStream(socket.getInputStream()));

			
			sendNum();
			String inputLine;
			//when receive stop message then stop the server
			while ((inputLine = in.readLine()) != null) {
				if ("stop".equals(inputLine)){
					timer.cancel();
					timer = null;
					console.setMessage("client has stopped receiving");
				} else if ("start".equals(inputLine)){
					sendNum();
				}
			}
		}catch(IOException i){
			console.setMessage("socket disconnected");
			timer.cancel();
			timer = null;
			run();
		}
	}
	
	private void sendNum(){
		if (timer!=null) return;
		timer = new Timer();
        TimerTask genNum = new TimerTask() {
            public void run() {
            	ArrayList<Integer> arrayList = grn.RandomNumberFunction();
				
				if (!stopped){
					try {
						objectOutput = new 
								ObjectOutputStream(socket.getOutputStream());
						objectOutput.writeObject(arrayList);
					} catch (IOException e) {
						console.setErrorMessage(e.toString());
					}
				}
            }
        };
        timer.scheduleAtFixedRate(genNum, 1000 / frequency, 1000 / frequency);
	}
	
	public void stop(){
		stopped = true;
		console.setMessage("generate number stop");
	}
	
	public void start(){
		stopped = false;
		console.setMessage("generate number start");
	}
	
	public void destory(){
		if (timer!=null) timer.cancel();
		stopped = true;
		try {
			console.setMessage("server stopped");
			socket.close();
		} catch (IOException e) {
			console.setErrorMessage(e.toString());
		}
	}
}
