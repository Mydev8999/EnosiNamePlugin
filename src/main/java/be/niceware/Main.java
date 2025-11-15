package be.niceware;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class Main extends JavaPlugin {

    public static Set<Player> frozenPlayers = new HashSet<>();

    @Override
    public void onEnable() {
        getLogger().info("Plugin démarré.");
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        this.getCommand("discord").setExecutor(new DiscordCommand());
    }

    public static void freeze(Player p) {
        frozenPlayers.add(p);
        p.sendMessage("§cVous devez entrer votre pseudo Discord !");
        p.sendMessage("§eUtilisez : §6/discord <pseudo>");
    }

    public static void unfreeze(Player p) {
        frozenPlayers.remove(p);
        p.sendMessage("§aMerci ! Vous êtes débloqué.");
    }

    public static boolean isFrozen(Player p) {
        return frozenPlayers.contains(p);
    }
}
