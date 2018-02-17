import java.util.Random;
/**
 * @SER516 ProjecTwo_Team10
 * @author huichuan wu
 * @Version 1.0
 * User Story #12: average value in the client side
 */
public class generateAverageValue {
    ArrayList<Integer> values = new ArrayList<>();
    long sum = 0;
    /**
     * @param value the value get from the server
     */
    public int getAverage(int value){
        int res = (sum + value)/values.size().
        return res;
    }
    /**
     * @param valueList  a list of values get from server
     */
    public int getAverage(ArrayList<Integer> valueList){
        int sum1 = 0;
        for(int v : valueList){
            sum1 += v;
        }
        int res = (sum + sum1)/values.size().
        return res;
    }
}
