package gemesil.vortexutilitycommands.commands;

import gemesil.vortexutilitycommands.VortexUtilityCommands;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Msg implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Check if the executor is not a player
        if (!(sender instanceof Player)) {
            VortexUtilityCommands.getVortexLogger().sendAlert("Must be a player to execute this command!");
            return true;
        }

        Player p = (Player) sender;

        // Check if player has permission for command
        if (!p.hasPermission("msg.use_command")) {
            VortexUtilityCommands.getVortexLogger().sendNoPermsMsg(p);
            return true;
        }

        // If player name and message were entered
        if (args.length > 1) {

            // Check if player is online
            Player targetPlayer = null;

            // Check if that player is currently online
            for (Player currentOnlinePlayer : Bukkit.getServer().getOnlinePlayers()) {

                // If the player who the commandSender is trying to get is online
                if (args[0].equals(currentOnlinePlayer.getName())) {

                    // Get the player object and exit the loop
                    targetPlayer = currentOnlinePlayer;
                    break;
                }
            }

            // When our target wasn't found
            if (targetPlayer == null) {

                // Alert the command sender the player wasn't found
                VortexUtilityCommands.getVortexLogger().sendChat(p, "Player " + args[0] + " is either not online, or doesn't actually exist!", true);
                return true;
            }

            // Concat all words into one message
            StringBuilder message = new StringBuilder();

            for (int i = 1; i < args.length; i++) {
                message.append(args[i]);

                // Add space between words
                if (i < args.length - 1)
                    message.append(" ");
            }

            // Send the message to the target player
            VortexUtilityCommands.getVortexLogger().sendChat(targetPlayer, "Whisper from " + p.getDisplayName() + ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + message.toString());

            // Indicate to the commandSender that the message was sent
            VortexUtilityCommands.getVortexLogger().sendChat(p, "Sent whisper to " + targetPlayer.getDisplayName(), true);

            return true;
        }

        // No player name / message were entered
        else
            return false;
    }
}
