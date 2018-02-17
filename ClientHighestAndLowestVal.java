/**
 * Class about highest and lowest value on the client side.
 * 
 * Since Client GUI does not start working. I will just upload
 * this POJO temporarily. It may be updated in future.
 */

public class ClientHightestAndLowestVal {
    private int highestVal;
    private int lowestVal;

    public ClientHightestAndLowestVal() {
        highestVal = 0;
        lowestVal = 0;
    }

    public ClientHightestAndLowestVal(int h, int l) {
        highestVal = h;
        lowestVal = l;
    }

    /**
     * @param highestVal the highestVal to set
     */
    public void setHighestVal(int highestVal) {
        this.highestVal = highestVal;
    }

    /**
     * @return the highestVal
     */
    public int getHighestVal() {
        return highestVal;
    }

    /**
     * @param lowestVal the lowestVal to set
     */
    public void setLowestVal(int lowestVal) {
        this.lowestVal = lowestVal;
    }
    /**
     * @return the lowestVal
     */
    public int getLowestVal() {
        return lowestVal;
    }
}