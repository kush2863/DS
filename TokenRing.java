import java.util.Arrays;
public class TokenRing {
    public static void main(String[] args) {
        int nodes = 4;
        boolean[] wantsCS = {false, true, false, true}; // Nodes 1 & 3 request CS
        int token = 0; // Token starts at Node 0
        
        System.out.println("--- Token Ring Simulation ---");
        for (int pass = 0; pass < nodes; pass++) {
            System.out.println("Token at Node " + token);
            if (wantsCS[token]) {
                System.out.println("  -> Node " + token + " ENTERS Critical Section");
                System.out.println("  -> Node " + token + " EXITS Critical Section");
                wantsCS[token] = false; // Request fulfilled
            }
            token = (token + 1) % nodes; // Pass token clockwise
        }
        System.out.println("Pending requests: " + Arrays.toString(wantsCS));
    }
}