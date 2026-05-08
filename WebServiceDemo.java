import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WebServiceDemo {
    public static void main(String[] args) throws Exception {
        // 1. Start HTTP Server
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/data", exchange -> {
            String payload = "{\"status\":\"active\", \"nodes\":4}";
            exchange.sendResponseHeaders(200, payload.getBytes().length);
            exchange.getResponseBody().write(payload.getBytes());
            exchange.close();
        });
        server.setExecutor(null); // Uses default thread pool
        server.start();
        System.out.println("Service running: http://localhost:8080/api/data");

        // 2. Distributed Client Consumer
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/data"))
                .header("Content-Type", "application/json")
                .GET().build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Client consumed: " + response.body());
        
        server.stop(0); // Graceful shutdown
    }
}