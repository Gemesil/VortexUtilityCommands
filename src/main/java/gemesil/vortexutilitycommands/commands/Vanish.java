package gemesil.vortexutilitycommands.commands;

import gemesil.vortexutilitycommands.VortexUtilityCommands;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;

public class Vanish implements CommandExecutor, Listener {

    public ArrayList<Player> playersInVanish = new ArrayList<>();
    // TODO make db for players in vanish so it doesnt reset on leave

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Check if the executor is not a player
        if (!(sender instanceof Player)) {
            VortexUtilityCommands.getVortexLogger().sendAlert("Must be a player to execute this command!");
            return true;
        }

        Player p = (Player) sender;

        // Check if player has permission for command
        if (!p.hasPermission("vanish.use_command")) {
            VortexUtilityCommands.getVortexLogger().sendNoPermsMsg(p);
            return true;
        }

        // Command "/vanish info" or "/v info" was called
        if (args.length > 0) {
            if (args[0].equals("info")) {

                // Show current vanish status in chat
                if (playersInVanish.contains(p))
                    VortexUtilityCommands.getVortexLogger().sendChat(p, "Vanish mode is currently enabled", true);

                else
                    VortexUtilityCommands.getVortexLogger().sendChat(p, "Vanish mode is currently disabled", true);

                return true;
            }
        }

        // Command "/vanish" or "/v" was used without any other arguments
        else {

            // Enter vanish mode
            if (!playersInVanish.contains(p))
                enterVanish(p, true);

                // Leave vanish mode
            else
                exitVanish(p, true);
        }

        return true;
    }

    // Used in order to enter vanish
    public void enterVanish(Player p, boolean sendMsg) {

        // Hide the command executor from other online players
        for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers())

            // If the player is not allowed to see vanished players, hide the player that is in vanish
            if (!onlinePlayer.hasPermission("vanish.see_vanished_players"))
                onlinePlayer.hidePlayer(VortexUtilityCommands.getPlugin(), p);

        // Add the command executor to the players in vanish list
        playersInVanish.add(p);

        if (sendMsg)
            // Show actionbar message to indicate vanish mode
            VortexUtilityCommands.getVortexLogger().sendActionBar(p, ChatColor.GREEN + "Now in Vanish mode");
    }

    // Used in order to exit vanish
    public void exitVanish(Player p, boolean sendMsg) {

        // Show the command executor to other online players
        for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers())

            if (!onlinePlayer.hasPermission("vanish.see_vanished_players"))
                onlinePlayer.showPlayer(VortexUtilityCommands.getPlugin(), p);

        playersInVanish.remove(p);

        if (sendMsg)
            // Show actionbar message to indicate vanish mode
            VortexUtilityCommands.getVortexLogger().sendActionBar(p, "No longer in Vanish mode");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        // Go over all players in vanish (except the player joining), hide them from player that just joined
        for (Player p : playersInVanish)

            if (e.getPlayer() != p)
                e.getPlayer().hidePlayer(VortexUtilityCommands.getPlugin(), p);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {

        // Go over all players in vanish (except the player leaving), show them to the player that just left
        for (Player p : playersInVanish)

            if (e.getPlayer() != p)
                e.getPlayer().showPlayer(VortexUtilityCommands.getPlugin(), p);
    }
}
