import java.util.List;

/**
 * Class about highest and lowest value on the client side.
 */

public class ClientHighestAndLowestVal {
    private static int highestVal;
    private static int lowestVal;

    static {
        highestVal = 0;
        lowestVal = 0;
    }

    /**
      * Pure static class
      */
    private ClientHighestAndLowestVal() {}

    /**
      * Read an integer list and update the highest and lowest value
      * @param List<Integer>
      */
    public static void readList(List<Integer> list) {
        for (int i : list) {
            highestVal = i > highestVal ? i : highestVal;
            lowestVal = i < lowestVal ? i : lowestVal;
        }
    }

    /**
     * @return the highestVal
     */
    public static int getHighestVal() {
        return highestVal;
    }

    /**
     * @return the lowestVal
     */
    public static int getLowestVal() {
        return lowestVal;
    }
    /**
     * @return the average value
     */
    public int calculateAverage(ArrayList<Integer> valueList){
        long sum = 0;
        for(int v : valueList){
            sum += v;
        }
        long average = sum/valueList.size();
        return (int)average;
    }
}
