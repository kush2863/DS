public class RingAlgorithm {
    public static void main(String[] args) {
        int[] ring = {3, 1, 4, 2}; // Logical ring order
        int n = ring.length;
        int startIdx = 0;
        int maxId = ring[startIdx];
        
        System.out.println("Ring election initiated by Node " + maxId);
        for (int i = 1; i <= n; i++) {
            int currIdx = (startIdx + i) % n;
            int nodeId = ring[currIdx];
            System.out.println("Message reaches Node " + nodeId);
            if (nodeId > maxId) maxId = nodeId; // Track highest ID seen
        }
        System.out.println("Election complete. Leader: Node " + maxId);
    }
}