package be.niceware;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.bukkit.Bukkit;
import net.md_5.bungee.api.ChatColor;

public class ApiSender {

    public static boolean sendDiscordInfo(String uuid, String ingame, String discord, String ip) {
        try {
            URL url = new URL("http://127.0.0.1:5000/link");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            // Strip des couleurs du MOTD
            String cleanMotd = ChatColor.stripColor(Bukkit.getServer().getMotd());

            // On garde UNIQUEMENT "ENOSI LABS"
            String serverName = "ENOSI LABS";

            // Optionnel si tu veux détecter
            if (cleanMotd.contains("ENOSI LABS")) {
                serverName = "ENOSI LABS";
            }

            String json = String.format(
                "{\"uuid\":\"%s\",\"ingame\":\"%s\",\"discord\":\"%s\",\"ip\":\"%s\",\"server\":\"%s\"}",
                uuid, ingame, discord, ip, serverName
            );

            try (OutputStream os = con.getOutputStream()) {
                os.write(json.getBytes(StandardCharsets.UTF_8));
            }

            int code = con.getResponseCode();
            return code == 200;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
