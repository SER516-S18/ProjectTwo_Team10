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
    public static void main(String[] args) {
        List<Integer> valueList = new ArrayList<>();
        valueList.add(3);
        valueList.add(5);
        valueList.add(3);
        valueList.add(4);
        valueList.add(5);
        calculateAverage(valueList);
        System.out.println(getAverage());
        for(int i : valueList){
            System.out.println("+" + i);
        }

    }
}
