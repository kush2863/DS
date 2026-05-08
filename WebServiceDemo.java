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




// 
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
public class SimpleWebService {
public static void main(String[] args) throws Exception {
HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
server.createContext("/sum", new SumHandler());
server.setExecutor(null);
server.start();
System.out.println("Web Service started at http://localhost:8000/sum");
System.out.println("Use format: http://localhost:8000/sum?a=10&b=20");
}
static class SumHandler implements HttpHandler {
public void handle(HttpExchange exchange) throws IOException {
String query = exchange.getRequestURI().getQuery();
int a = 0, b = 0;
if (query != null) {
String[] params = query.split("&");
for (String param : params) {
String[] pair = param.split("=");
if (pair[0].equals("a")) {
a = Integer.parseInt(pair[1]);
} else if (pair[0].equals("b")) {
b = Integer.parseInt(pair[1]);
}
}
}
int sum = a + b;
String response = "Sum = " + sum;
exchange.sendResponseHeaders(200, response.length());
OutputStream os = exchange.getResponseBody();
os.write(response.getBytes());
os.close();
}
}
}


//server
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Scanner;
public class ClientApp {
public static void main(String[] args) throws Exception {
Scanner sc = new Scanner(System.in);
System.out.print("Enter first number: ");
int a = sc.nextInt();
System.out.print("Enter second number: ");
int b = sc.nextInt();
String urlString = "http://localhost:8000/sum?a=" + a + "&b=" + b;
URL url = new URL(urlString);
HttpURLConnection con = (HttpURLConnection) url.openConnection();
con.setRequestMethod("GET");
BufferedReader in = new BufferedReader(
new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuilder response = new StringBuilder();
while ((inputLine = in.readLine()) != null) {
response.append(inputLine);
}
in.close();
System.out.println("Response from Web Service: " + response.toString());
sc.close();
}
}