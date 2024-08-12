import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.net.HttpURLConnection.HTTP_OK;

public class HttpExamplePost {

    public static void main(String[] args) {

        try {
//            URL url = new URL("https://majaliwa.tech");
            URL url = new URL("http://localhost:5000/");
            var httpURLConnection = getHttpURLConnection(url);

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

    private static HttpURLConnection getHttpURLConnection(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("User-Agent", "Chrome");
        // what content-types the server is willing to accept back
        httpURLConnection.setRequestProperty("Accept", "application/json, text/html, text/plain");
        httpURLConnection.setReadTimeout(30_000);

        // add info specific to posting data
        httpURLConnection.setDoOutput(true); // have data sent to the server
        // the content is formatted in a very specific way
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        String parameters = "first=Joe&last=Smith";
        int length = parameters.getBytes().length;
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(length));
        // populate the req body with the parameter data
        DataOutputStream output = new DataOutputStream(httpURLConnection.getOutputStream());
        output.writeBytes(parameters);
        output.flush(); // always flush the outputstream and close it
        output.close();
        return httpURLConnection;
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
