import java.util.Arrays;
public class BerkeleyAlgorithm {
    public static void main(String[] args) {
        int[] clocks = {100, 108, 92, 105}; // Simulated node times
        System.out.println("Initial clocks: " + Arrays.toString(clocks));
        
        int sum = 0;
        for (int t : clocks) sum += t;
        int masterAvg = sum / clocks.length; // Master computes average
        System.out.println("Master average: " + masterAvg);
        
        for (int i = 0; i < clocks.length; i++) {
            int offset = masterAvg - clocks[i]; // Calculate adjustment
            clocks[i] += offset; // Apply synchronization
            System.out.println("Node " + i + " adjusted by " + offset);
        }
        System.out.println("Synchronized: " + Arrays.toString(clocks));
    }
}