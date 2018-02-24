package server;


import java.util.ArrayList;
import java.util.Random;

public class GenerateRandomNumbers {
	/**
	 * @SER516 ProjecTwo_Team10
	 * @author Prasanth Venugopal
	 * @Version 1.0
	 * User Story #3: Generation of Random values 
	 */
	int highestNumberPossible;
	int lowestNumberPossible;
	int Channel;
	Random random = new Random();
	public GenerateRandomNumbers(int high, int low,  int channel){	
		 this.highestNumberPossible = high;
		 this.lowestNumberPossible = low;
		 this.Channel = channel;
	}
	public  ArrayList<Integer> RandomNumberFunction() {
		    	ArrayList<Integer> al = new ArrayList<Integer>();
        		for(int i = 0; i < Channel;i++){
        			int randomNumber = random.nextInt(highestNumberPossible - lowestNumberPossible) + lowestNumberPossible;
        			al.add(randomNumber);
        		}   
        		return al;
	}
}
