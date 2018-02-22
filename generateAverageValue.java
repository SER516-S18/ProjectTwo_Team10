import java.util.ArrayList;
/**
 * @SER516 ProjecTwo_Team10
 * @author huichuan wu
 * @Version 1.0
 * User Story #12: average value in the client side
 */
public class generateAverageValue {
    /**
     * @param valueList  a list of values get from server
     */
    public int getAverage(ArrayList<Integer> valueList){
        long sum = 0;
        for(int v : valueList){
            sum += v;
            values.add(v);
        }
        long res = sum/values.size();
        return (int)res;
    }
}
