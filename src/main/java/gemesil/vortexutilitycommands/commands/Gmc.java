package gemesil.vortexutilitycommands.commands;

import gemesil.vortexlogger.VortexLogger;
import gemesil.vortexutilitycommands.VortexUtilityCommands;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gmc implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Check if the executor is not a player
        if (!(sender instanceof Player)) {
            VortexUtilityCommands.getVortexLogger().sendAlert("Must be a player to execute this command!");
            return true;
        }

        // Convert sender to player
        Player p = (Player) sender;

        // Check if player has permission for command
        if (!p.hasPermission("gmc.use_command")) {
            VortexUtilityCommands.getVortexLogger().sendNoPermsMsg(p);
            return true;
        }

        // Set player to creative
        if (p.getGameMode() != GameMode.CREATIVE) {

            // Set to creative
            p.setGameMode(GameMode.CREATIVE);

            // Actionbar + sound indicators
            VortexUtilityCommands.getVortexLogger().sendActionBar(p, ChatColor.GREEN + "Game mode is now set to CREATIVE");
        }

        // Already in creative, indicate that to the player
        else {
            // Actionbar + sound indicators
            VortexUtilityCommands.getVortexLogger().sendActionBar(p, ChatColor.RED + "You're already in CREATIVE");
        }

        return true;
    }
}
