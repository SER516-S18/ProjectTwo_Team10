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

    private ClientHighestAndLowestVal() {}

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
}
