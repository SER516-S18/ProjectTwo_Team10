import java.util.List;

/**
 * Class about highest and lowest value on the client side.
 */

public class ClientValCalculator {
    private static int highestVal;
    private static int lowestVal;
    private static int averageVal;

    static {
        highestVal = 0;
        lowestVal = 0;
        averageVal = 0;
    }

    private ClientValCalculator() {}

    /**
      * Read an integer list and update the highest and lowest value
      * @param List<Integer>
      */
    public static void readList(List<Integer> list) {
        long sum = 0;
        for (int i : list) {
            highestVal = i > highestVal ? i : highestVal;
            lowestVal = i < lowestVal ? i : lowestVal;
            sum += i;
        }

        averageVal = (int) (sum / list.size());
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
     * @return the averageVal
     */
    public static int getAverageVal() {
        return averageVal;
    }
}