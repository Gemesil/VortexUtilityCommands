package gemesil.vortexutilitycommands.commands;

import gemesil.vortexlogger.VortexLogger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Inv implements CommandExecutor, Listener {

    private Inventory invGUI;
    private final VortexLogger vortexLogger;

    public Inv(VortexLogger vortexLogger) {
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
        if (!p.hasPermission("inv.use_command")) {
            vortexLogger.sendNoPermsMsg(p);
            return true;
        }

        // Check if a player name was entered after the command (ex: /inv Gemesil)
        if (args.length < 1) return false;

        Player targetPlayer = null;

        // Check if that player is currently online
        for (Player currentOnlinePlayer : Bukkit.getServer().getOnlinePlayers()) {

            // If the player who the commandSender is trying to get is online
            if (args[0].equals(targetPlayer.getName())) {

                // Get the player object and exit the loop
                targetPlayer = currentOnlinePlayer;
                break;
            }
        }

        // When our target wasnt found
        if (targetPlayer == null) {

            // Alert the command sender they suck
            vortexLogger.sendChat(p, "Player " + args[0] + " is either not online, or doesn't actually exist!", true);
            return true;
        }

        // Now we know our player is online
        // Lets get their inventory
        Inventory targetInventory = targetPlayer.getInventory();

        // We would like to display their inventory to the commandSender
        // So we will create an inventory-like GUI and copy the contents from targetInventory
        invGUI = Bukkit.createInventory(targetPlayer, targetInventory.getSize(), targetPlayer.getName() + "'s Inventory");


        // Add the items from targetInventory to our fake inventory GUI
//        for (ItemStack currentItem : targetInventory.getContents()) {
//
//        }

        // Display inventroy in GUI for command sender

        // Any item command sender touches in GUI updates inventory of player

        return true;
    }

    @EventHandler
    public void onInventoryClick (InventoryClickEvent e) {

        // If the inventory is not of our target it doesnt matter
        if (!e.getInventory().equals(invGUI)) return;

        // Prevent the click
        e.setCancelled(true);
    }
}
