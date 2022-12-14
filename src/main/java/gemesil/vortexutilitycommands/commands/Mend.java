package gemesil.vortexutilitycommands.commands;

import gemesil.vortexlogger.VortexLogger;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Mend implements CommandExecutor {

    private final VortexLogger vortexLogger;

    public Mend(VortexLogger vortexLogger) {
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
        if (!p.hasPermission("mend.use_command")) {
            vortexLogger.sendNoPermsMsg(p);
            return true;
        }

        // If player is alive, set player to full health
        if (!p.isDead()) {

            // Set health to full
            p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());

            // Set food to full
            p.setFoodLevel(20);
        }

        // Show up action bar indication + play sound
        vortexLogger.sendActionBar(p, ChatColor.GREEN + "Mended self, so very refreshing!");

        return true;
    }
}
