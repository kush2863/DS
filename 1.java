// RemoteService.java
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface RemoteService extends Remote {
    String processRequest(String msg) throws RemoteException;
}

// Server.java
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
public class Server implements RemoteService {
    public String processRequest(String msg) {
        return "Server processed: " + msg.toUpperCase();
    }
    public static void main(String[] args) throws Exception {
        Server obj = new Server();
        RemoteService stub = (RemoteService) UnicastRemoteObject.exportObject(obj, 0);
        Registry reg = LocateRegistry.createRegistry(1099);
        reg.rebind("TaskService", stub);
        System.out.println("RMI Server running...");
    }
}

// Client.java
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class Client {
    public static void main(String[] args) throws Exception {
        Registry reg = LocateRegistry.getRegistry("localhost", 1099);
        RemoteService stub = (RemoteService) reg.lookup("TaskService");
        System.out.println(stub.processRequest("hello distributed system"));
    }
}