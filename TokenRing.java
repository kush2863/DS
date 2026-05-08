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

import java.util.Scanner;
public class TokenRing {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
int n, token, choice;
System.out.print("Enter number of processes: ");
n = sc.nextInt();
System.out.print("Enter process number which initially has token: ");
token = sc.nextInt();
do {
System.out.print("\nEnter process requesting critical section: ");
int req = sc.nextInt();
System.out.println("\nToken passing:");
while (token != req) {
System.out.println("Token passed from Process " + token + " to
Process " + ((token + 1) % n));
token = (token + 1) % n;
}
System.out.println("\nProcess " + req + " has the token.");
System.out.println("Process " + req + " is ENTERING critical
section...");
System.out.println("Process " + req + " is LEAVING critical
section...");
System.out.println("Token passed from Process " + token + " to Process "
+ ((token + 1) % n));
token = (token + 1) % n;
System.out.print("\nDo you want to continue? (1 for Yes / 0 for No): ");
choice = sc.nextInt();
} while (choice == 1);
sc.close();