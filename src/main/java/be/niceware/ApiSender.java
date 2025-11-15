package be.niceware;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiSender {

    public static void sendToApi(String uuid, String discord) {
        try {
            URL url = new URL("https://ton-api.com/endpoint"); // ← ton lien API
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            String json = "{ \"uuid\": \"" + uuid + "\", \"discord\": \"" + discord + "\" }";

            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes());
            }

            conn.getInputStream().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
