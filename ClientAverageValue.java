import java.util.List;

/**
 * @SER516 ProjecTwo_Team10
 * @author huichuan wu
 * @Version 2.0
 * User Story #12: average value in the client side
 */
public class ClientAverageValue {
    private static int average = 0;
    /**
     * make the average a singleton 
     */
    private ClientAverageValue(){}
    /**
     * @param valueList is value list in the client that contains all values
     */
    public static void calculateAverage(List<Integer> valueList){
        long sum = 0;
        for(int v : valueList){
            sum += v;
        }
        average = (int)(sum/valueList.size());
    }
    /**
     * @return the averageVal
     */
    public static int getAverage(){
        return average;
    }
}