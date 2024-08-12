import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.net.HttpURLConnection.HTTP_OK;

public class HttpExample {

    public static void main(String[] args) {

        try {
//            URL url = new URL("https://majaliwa.tech");
            URL url = new URL("http://localhost:8080/");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("User-Agent", "Chrome");
            // what content-types the server is willing to accept back
            httpURLConnection.setRequestProperty("Accept", "application/json, text/html, text/plain");
            httpURLConnection.setReadTimeout(30_000);

            int responseCode = httpURLConnection.getResponseCode();
            System.out.println("Response code: " + responseCode);

            if (responseCode != HTTP_OK) {
                System.out.println("Error reading url: " + url);
                System.err.println("Error: " + httpURLConnection.getResponseMessage());
                return;
            }
            printContents(httpURLConnection.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printContents(InputStream is) {

        try (BufferedReader inputStream = new BufferedReader(
                new InputStreamReader(is))
        ) {
            String line;
            while ((line = inputStream.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
