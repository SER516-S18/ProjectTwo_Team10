package server;


import java.util.ArrayList;
import java.util.Random;

/**
 * @SER516 ProjecTwo_Team10
 * @author Prasanth Venugopal
 * @Version 1.0
 * Generation of random values on server with given range
 */

public class GenerateRandomNumbers {
	int highestNumberPossible,
        lowestNumberPossible,
        channel;
	
	//Parameterized constructor
	public GenerateRandomNumbers(int high, int low,  int channel){	
		this.highestNumberPossible = high;
		this.lowestNumberPossible = low;
		this.channel = channel;
	}
	
	//Method to generate random values
	public ArrayList<Integer> RandomNumberFunction() {
		Random random = new Random();
		ArrayList<Integer> al = new ArrayList<Integer>();
        		for(int i = 0; i < channel;i++){
        			int randomNumber = random.nextInt(highestNumberPossible - lowestNumberPossible) + lowestNumberPossible;
        			al.add(randomNumber);
        		}   
        		return al;
	}
}
