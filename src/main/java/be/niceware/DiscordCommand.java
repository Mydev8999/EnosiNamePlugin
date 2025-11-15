package be.niceware;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiscordCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Commande réservée aux joueurs.");
            return true;
        }

        Player p = (Player) sender;

        if (!Main.isFrozen(p)) {
            p.sendMessage("§aVous êtes déjà enregistré.");
            return true;
        }

        if (args.length != 1) {
            p.sendMessage("§cUtilisation : /discord <pseudo>");
            return true;
        }

        String discord = args[0];

        ApiSender.sendToApi(p.getUniqueId().toString(), discord);

        Main.unfreeze(p);
        return true;
    }
}
