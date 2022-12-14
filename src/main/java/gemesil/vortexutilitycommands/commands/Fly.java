package gemesil.vortexutilitycommands.commands;

import gemesil.vortexlogger.VortexLogger;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {

    private final VortexLogger vortexLogger;

    public Fly(VortexLogger vortexLogger) {
        this.vortexLogger = vortexLogger;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Check if the executor is not a player
        if (!(sender instanceof Player)) {
            vortexLogger.sendAlert("Must be a player to execute this command!");
            return true;
        }

        Player p = (Player) sender;

        // Check if player has permission for command
        if (!p.hasPermission("fly.use_command")) {
            vortexLogger.sendNoPermsMsg(p);
            return true;
        }

        // Set player to fly if he's not already set to that.
        // Otherwise, set him to stop flying.
        p.setAllowFlight(!p.getAllowFlight());

        // Fly mode is turned on
        if (p.getAllowFlight()) {

            vortexLogger.sendActionBar(p, ChatColor.GREEN + "Flying is now enabled");
        }

        // Fly mode is turned off
        else
            vortexLogger.sendActionBar(p, ChatColor.RED + "Flying is now disabled");

        return true;
    }
}
