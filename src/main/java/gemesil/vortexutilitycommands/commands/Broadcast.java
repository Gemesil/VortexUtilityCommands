package gemesil.vortexutilitycommands.commands;

import gemesil.vortexutilitycommands.VortexUtilityCommands;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Broadcast implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("broadcast.use_command")) {

            if (sender instanceof Player)
                // Check if player has permission for command
                VortexUtilityCommands.getVortexLogger().sendNoPermsMsg((Player) sender);

            // If console
            else
                VortexUtilityCommands.getVortexLogger().sendAlert("You don't have permission to use that command!");

            return true;
        }

        if (args.length > 0) {

            // Concat all args into one message
            StringBuilder messageToBroadcast = new StringBuilder();

            for (int i = 0; i < args.length; i++) {
                messageToBroadcast.append(args[i]);

                // Add space between words
                if (i < args.length - 1)
                    messageToBroadcast.append(" ");
            }

            // Send the message to the entire server
            VortexUtilityCommands.getVortexLogger().sendBroadcast(ChatColor.DARK_PURPLE + messageToBroadcast.toString(), true);

            return true;
        }

        // No message was entered
        else
            return false;
    }
}
