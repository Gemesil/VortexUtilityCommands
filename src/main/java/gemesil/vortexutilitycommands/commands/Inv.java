package gemesil.vortexutilitycommands.commands;

import gemesil.vortexlogger.VortexLogger;
import gemesil.vortexutilitycommands.objects.GuiItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.lang.reflect.Array;
import java.util.Arrays;

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
            if (args[0].equals(currentOnlinePlayer.getName())) {

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
        PlayerInventory targetInventory = targetPlayer.getInventory();

        // We would like to display their inventory to the commandSender
        // So we will create an inventory-like GUI and copy the contents from targetInventory
        invGUI = Bukkit.createInventory(targetPlayer, 54, targetPlayer.getName() + "'s Inventory");

        // GUI for armor slots
        invGUI.setItem(45,
            new GuiItem(
                Material.PAPER,
                ChatColor.LIGHT_PURPLE + "Armor",
                Arrays.asList(
                    "Armor that " + targetPlayer.getName() + " is wearing."
                )
            ).getItem()
        );

        // GUI for 2nd Hand item
        invGUI.setItem(36,
                new GuiItem(
                        Material.PAPER,
                        ChatColor.LIGHT_PURPLE + "Armor",
                        Arrays.asList(
                                "Armor that " + targetPlayer.getName() + " is wearing."
                                )
                ).getItem()
        );

        // Add the items from targetInventory to our fake inventory GUI
        for (int i = 0; i < targetInventory.getSize(); i++) {

            // When we have reached the armor and 2nd hand slots
            if (i > 35) {
                // Move them one row under
                invGUI.setItem(i + 10, targetInventory.getItem(i));
            }

            else
                invGUI.setItem(i, targetInventory.getItem(i));
        }

        // Check if the targetPlayer has an item in their 2nd hand
        if (!targetInventory.getItemInOffHand().equals(new ItemStack(Material.AIR))) {

            // TODO check 2nd item slot
            targetInventory.getHeldItemSlot();
        }

        // Display inventroy in GUI for command sender
        p.openInventory(invGUI);

        // Any item command sender touches in GUI updates inventory of player

        return true;
    }

    @EventHandler
    public void onInventoryClick (InventoryClickEvent e) {

        // If the inventory is not our custom inventory, it doesnt matter
        if (!e.getInventory().equals(invGUI)) return;

        // Dont let the player move anything in the inventory
        e.setCancelled(true);
    }
}
