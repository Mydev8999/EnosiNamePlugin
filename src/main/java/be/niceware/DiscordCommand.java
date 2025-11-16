package be.niceware;

import org.bukkit.Bukkit;
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

        if (args.length != 1) {
            p.sendMessage("§cUtilisation : /discord <pseudoDiscord>. made by niceware");
            return true;
        }

        String discord = args[0];
        String uuid = p.getUniqueId().toString();
        String ingame = p.getName();
        String ip = p.getAddress().getAddress().getHostAddress();

        p.sendMessage("§7Envoi à l’API…");

        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
            boolean ok = ApiSender.sendDiscordInfo(uuid, ingame, discord, ip);

            if (ok) {
                Bukkit.getScheduler().runTask(Main.getInstance(), () -> {
                    p.sendMessage("§aTon Discord et ton IP ont été enregistrés !");
                    Main.validatedPlayers.add(uuid);
                });
            } else {
                Bukkit.getScheduler().runTask(Main.getInstance(), () -> {
                    p.sendMessage("§cErreur : impossible de contacter l’API.");
                });
            }
        });

        return true;
    }
}
