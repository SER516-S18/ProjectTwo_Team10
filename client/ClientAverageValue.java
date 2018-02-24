package client;
import java.util.ArrayList;
import java.util.List;

/**
 * @SER516 ProjecTwo_Team10
 * @author huichuan wu
 * @Version 2.0
 * User Story #12: average value in the client side
 */
public class ClientAverageValue {
    private static int average = 0;
    private static int sum = 0;
    private static int num = 0;
    /**
     * make the average a singleton 
     */
    static ArrayList <Integer> avgValues = new ArrayList<Integer>();
    private ClientAverageValue(){}
    /**
     * @param valueList is value list in the client that contains all values
     */
    public static void calculateAverage(List<Integer> valueList){
        for(int v : valueList){
            avgValues.add(v);
            sum += v;
        }
        average = (int)(sum/avgValues.size());
    }
    /**
     * @return the averageVal
     */
    public static int getAverage(){
        return average;
    }
}
