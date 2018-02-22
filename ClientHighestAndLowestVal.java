import java.util.List;

/**
  * @SER516 ProjecTwo_Team10
  * @author Zitong Wei
  * @Version 1.0
  * User Story #10: Get highest and lowest val.
  */

public class ClientHighestAndLowestVal.java {
    private static int highestVal;
    private static int lowestVal;

    static {
        highestVal = 0;
        lowestVal = 0;
        averageVal = 0;
    }

    private ClientHighestAndLowestVal.java() {}

    /**
      * Read an integer list and update the highest, lowest value.
      * @param list
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
}
