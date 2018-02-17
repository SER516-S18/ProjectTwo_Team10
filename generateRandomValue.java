import java.util.Random;
/**
 * @SER516 ProjecTwo_Team10
 * @author huichuan wu
 * @Version 1.0
 * User Story #1: random value in the server side
 */
public class generateRandomValue {
    /**
     * @param low the lowestVal to set
     * @param high the highestVal to set
     */
    public int getRandom(int low, int high){
        Random random = new Random();
        int res = random.nextInt(high)%(high-low+1) + low;
        return res;
    }
}
