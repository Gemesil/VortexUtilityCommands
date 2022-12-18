package gemesil.vortexutilitycommands.commands;

import gemesil.vortexutilitycommands.VortexUtilityCommands;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Op implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // If a player name was entered
        if (args.length > 0) {

            // Check if sender has permission for command and is a console
            if (sender.hasPermission("op.use_command") && !(sender instanceof Player)) {

                // Execute op command
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "minecraft:op " + args[0]);
                return true;
            }

            // If the sender is a player or without permissions
            else {

                // Cast to player
                Player p = (Player) sender;

                // Troll players that try to use OP with the CentOS not in sudoers message
                VortexUtilityCommands.getVortexLogger().sendChat(p, args[0] + " is not in the sudoers file. This incident will be reported.", true);

                return true;
            }
        }

        // If a player name wasn't entered
        return false;
    }
}
