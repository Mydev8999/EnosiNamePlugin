package be.niceware;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;

public class Main extends JavaPlugin implements Listener {

    public static Main instance;
    public static HashSet<String> validatedPlayers = new HashSet<>();

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        getServer().getPluginManager().registerEvents(this, this);
        getCommand("discord").setExecutor(new DiscordCommand());

        getLogger().info("Plugin démarré ! made by niceware");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin arrêté. made by niceware");
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (!validatedPlayers.contains(p.getUniqueId().toString())) {
            e.setTo(e.getFrom()); // freeze
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if (!validatedPlayers.contains(p.getUniqueId().toString())) {
            p.sendMessage("§cTu dois entrer ton Discord : §e/discord <pseudo> made by niceware");
        }
    }
}
