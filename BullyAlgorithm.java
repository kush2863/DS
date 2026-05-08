public class BullyAlgorithm {
    static boolean[] alive = {true, true, false, true, false}; // 0-4, nodes 2 & 4 down
    public static void main(String[] args) {
        int initiator = 1;
        System.out.println("Node " + initiator + " detects failure & starts election");
        
        int newLeader = -1;
        for (int i = initiator + 1; i < alive.length; i++) {
            if (alive[i]) {
                System.out.println("Node " + i + " responds (higher ID alive)");
                newLeader = i; // Highest responding node takes over
            }
        }
        
        if (newLeader == -1) {
            System.out.println("No higher response. Node " + initiator + " WINS");
        } else {
            System.out.println("Node " + newLeader + " announces itself as COORDINATOR");
        }
    }
}