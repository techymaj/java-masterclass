import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.stream.Stream;

import static java.net.HttpURLConnection.HTTP_OK;

public class HttpClientGET {

    public static void main(String[] args) {

        try {
            URL url = new URL("http://localhost:5000/");
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(url.toURI())
                    // what content-types the server is willing to accept back
                    .header("User-Agent", "Chrome")
                    .headers("Accept", "application/json, text/html")
                    .timeout(Duration.ofSeconds(30))
                    .build(); // returns the build instance

            HttpResponse<Stream<String>> response = client.send(request,
                    HttpResponse.BodyHandlers.ofLines());

            if (response.statusCode() != HTTP_OK) {
                System.out.println("Error reading webpage" + url);
                return;
            }
            response.body()
                    .filter(s -> s.contains("<h1>"))
                    .map(s -> s.replaceAll("<[^>]*>", "").strip())
                    .forEach(System.out::println);
        } catch (IOException | URISyntaxException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}