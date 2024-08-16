package server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_OK;

public class SimpleHttpServer {

    private static long visitorCounter = 0;

    public static void main(String[] args) {
        try {
            // backlog = max number of queued up incoming connections allowed on the listening socket
            HttpServer server = HttpServer.create(new InetSocketAddress(5000), 0);
            server.createContext("/", exchange -> {
                String requestMethod = exchange.getRequestMethod();
                System.out.println("Request method: " + requestMethod);

                String data = new String(exchange.getRequestBody().readAllBytes());
                System.out.println("Body data: " + data);

                var parameters = parseParameters(data);
                System.out.println(parameters);
                exchange.getRequestHeaders().entrySet().forEach(System.out::println);

                if (requestMethod.equals("POST")) visitorCounter++;

                var bytes = getBytes(parameters);
                exchange.sendResponseHeaders(HTTP_OK, bytes.length);
                exchange.getResponseBody().write(bytes);
                exchange.close();
            });
            server.start();
            System.out.println("Server is listening on port: 5000");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] getBytes(Map<String, String> parameters) {
        var firstName = parameters.get("first");
        var lastName = parameters.get("last");
        String res = """
                <html>
                    <body>
                        <h1>Hello World from My Http Server</h1>
                        <p>Number of Visitors who signed up = %d<p>
                        <form method="post">
                            <label for="firstName">First Name:</label>
                            <input type="text" name="firstName" value="%s" id="firstName">
                            <br>
                            <label for="lastName">Last Name:</label>
                            <input type="text" name="lastName" value="%s" id="lastName">
                            <br>
                            <input type="submit" value="Submit">
                        </form>
                    </body>
                </html>
                """.formatted(visitorCounter, firstName == null ? "" : firstName, lastName == null ? "" : lastName) ;

        return res.getBytes();
    }

    private static Map<String, String> parseParameters(String requestBody) {
        Map<String, String> parameters = new HashMap<>();
        String[] pairs = requestBody.split("&");
        for (var pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                parameters.put(keyValue[0], keyValue[1]);
            }
        }
        return parameters;
    }
}
