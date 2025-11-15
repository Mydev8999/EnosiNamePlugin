package be.niceware;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Main.freeze(e.getPlayer());
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (Main.isFrozen(e.getPlayer())) {
            // Le joueur re-téléporté à sa position : effect freeze
            e.setTo(e.getFrom());
        }
    }
}
