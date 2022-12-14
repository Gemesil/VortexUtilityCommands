package gemesil.vortexutilitycommands.commands;

import gemesil.vortexlogger.VortexLogger;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class God implements CommandExecutor {

    private final VortexLogger vortexLogger;

    public God(VortexLogger vortexLogger) {
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
        if (!p.hasPermission("god.use_command")) {
            vortexLogger.sendNoPermsMsg(p);
            return true;
        }

        // If /god info command was called
        if (args.length > 0) {
            if (args[0].equals("info")) {

                // Show current god status in chat
                if (p.isInvulnerable())
                    vortexLogger.sendChat(p, "GOD mode is currently enabled", true);

                else
                    vortexLogger.sendChat(p, "GOD mode is currently disabled", true);

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
                p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());

                // Set food to full
                p.setFoodLevel(20);
            }

            vortexLogger.sendActionBar(p, ChatColor.GREEN + "Entered GOD mode");
        }

        // God mode is turned off
        else
            vortexLogger.sendActionBar(p, "Back to being just another mortal..");

        return true;
    }
}
