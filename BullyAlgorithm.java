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

//import java.util.Scanner;
public class Bully {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n, initiator;
    int[] active = new int[20];
    System.out.print("Enter number of processes: ");
    n = sc.nextInt();
    System.out.println("Enter status of each process (1 = active, 0 =
    failed):");
    for (int i = 1; i <= n; i++) {
    System.out.print("Process " + i + ": ");
    active[i] = sc.nextInt();
    start.");
    }
    System.out.print("Enter process that initiates election: ");
    initiator = sc.nextInt();
    if (active[initiator] == 0) {
    System.out.println("Initiator process is failed. Election cannot
    return;
    }
    System.out.println("\nElection started by Process " + initiator);
    int coordinator = initiator;
    for (int i = initiator + 1; i <= n; i++) {
    if (active[i] == 1) {
    System.out.println("Process " + initiator + " sends ELECTION message
    to Process " + i);
    System.out.println("Process " + i + " responds OK");
    coordinator = i;
    }
    }