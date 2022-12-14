package gemesil.vortexutilitycommands.commands;

import gemesil.vortexlogger.VortexLogger;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gms implements CommandExecutor {

    private final VortexLogger vortexLogger;

    public Gms(VortexLogger vortexLogger) {
        this.vortexLogger = vortexLogger;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Check if the executor is not a player
        if (!(sender instanceof Player)) {
            vortexLogger.sendAlert("Must be a player to execute this command!");
            return true;
        }

        // Convert sender to player
        Player p = (Player) sender;

        // Check if player has permission for command
        if (!p.hasPermission("gms.use_command")) {
            vortexLogger.sendNoPermsMsg(p);
            return true;
        }

        // Set player to survival
        if (p.getGameMode() != GameMode.SURVIVAL) {

            // Set to creative
            p.setGameMode(GameMode.SURVIVAL);

            // Actionbar + sound indicators
            vortexLogger.sendActionBar(p, ChatColor.GREEN + "Game mode is now set to SURVIVAL");
        }

        // Already in survival, indicate that to the player
        else {
            // Actionbar + sound indicators
            vortexLogger.sendActionBar(p, ChatColor.RED + "You're already in SURVIVAL");
        }

        return true;
    }
}
