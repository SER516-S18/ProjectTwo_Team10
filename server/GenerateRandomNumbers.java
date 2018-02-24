package server;


import java.util.ArrayList;
import java.util.Random;

/**
 *The Generate Random Numbers class basically generates Random numbers between 
 * the lowest value and the highest value provided
 * @SER516 ProjecTwo_Team10
 * @author Prasanth Venugopal
 * @Version 1.0 
 */

public class GenerateRandomNumbers {
	int highestNumberPossible,
        lowestNumberPossible,
        channel;
	
	/* Initializes to store values to generate random values
	 *@param high Random Number Higher range Limit
	 *@param low Random Number Lower range limit
	 *@param channel To generate channel number of Random Number
	*/
	public GenerateRandomNumbers(int high, int low,  int channel){	
		this.highestNumberPossible = high;
		this.lowestNumberPossible = low;
		this.channel = channel;
	}
	
	/* Method to generate random values
	 *@return arrayList
	*/
	public ArrayList<Integer> RandomNumberFunction() {
		Random random = new Random();
		ArrayList<Integer> randomValueList = new ArrayList<Integer>();
        		for(int i = 0; i < channel;i++){
        			int randomNumber = random.nextInt(highestNumberPossible - lowestNumberPossible) 
					+ lowestNumberPossible;
        			randomValueList.add(randomNumber);
        		}   
        		return randomValueList;
	}
}
