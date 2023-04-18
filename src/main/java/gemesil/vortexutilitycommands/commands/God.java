package gemesil.vortexutilitycommands.commands;

import gemesil.vortexlogger.VortexLogger;
import gemesil.vortexutilitycommands.VortexUtilityCommands;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class God implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Check if the executor is not a player
        if (!(sender instanceof Player)) {
            VortexUtilityCommands.getVortexLogger().sendAlert("Must be a player to execute this command!");
            return true;
        }

        Player p = (Player) sender;

        // Check if player has permission for command
        if (!p.hasPermission("god.use_command")) {
            VortexUtilityCommands.getVortexLogger().sendNoPermsMsg(p);
            return true;
        }

        // If /god info command was called
        if (args.length > 0) {
            if (args[0].equals("info")) {

                // Show current god status in chat
                if (p.isInvulnerable())
                    VortexUtilityCommands.getVortexLogger().sendChat(p, "GOD mode is currently enabled", true);

                else
                    VortexUtilityCommands.getVortexLogger().sendChat(p, "GOD mode is currently disabled", true);

                return true;
            }
        }

        // Set player to stop taking damage if he's not already set to that.
        // Otherwise, set him to stop taking damage.
        p.setInvulnerable(!p.isInvulnerable());

        // God mode is turned on
        if (p.isInvulnerable()) {

            // Set health and hunger to max
            if (!p.isDead()) {

                // Set health to full
                p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());

                // Set food to full
                p.setFoodLevel(20);
            }

            VortexUtilityCommands.getVortexLogger().sendActionBar(p, ChatColor.GREEN + "Entered GOD mode");
        }

        // God mode is turned off
        else
            VortexUtilityCommands.getVortexLogger().sendActionBar(p, "Back to being just another mortal..");

        return true;
    }
}
