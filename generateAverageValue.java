import java.util.ArrayList;
/**
 * @SER516 ProjecTwo_Team10
 * @author huichuan wu
 * @Version 1.0
 * User Story #12: average value in the client side
 */

public class generateAverageValue {
    ArrayList<Integer> valueList = new ArrayList<Integer>();
    public int calculateAverage(){
        long sum = 0;
        for(int v : valueList){
            sum += v;
        }
        long average = sum/valueList.size();
        return (int)average;
    }
}
