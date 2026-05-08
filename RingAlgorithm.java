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

import java.util.Scanner;
public class Ring {
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
System.out.println("\nElection message passing in ring:");
int maxId = initiator;
int current = initiator;
do {
int next = (current % n) + 1;
while (active[next] == 0) {
next = (next % n) + 1;
}