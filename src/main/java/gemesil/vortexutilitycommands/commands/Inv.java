package gemesil.vortexutilitycommands.commands;

import gemesil.vortexlogger.VortexLogger;
import gemesil.vortexutilitycommands.VortexUtilityCommands;
import gemesil.vortexutilitycommands.objects.GuiItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
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

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Check if the executor is not a player
        if (!(sender instanceof Player)) {
            VortexUtilityCommands.getVortexLogger().sendAlert("Must be a player to execute this command!");
            return true;
        }

        Player p = (Player) sender;

        // Check if player has permission for command
        if (!p.hasPermission("inv.use_command")) {
            VortexUtilityCommands.getVortexLogger().sendNoPermsMsg(p);
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
            VortexUtilityCommands.getVortexLogger().sendChat(p, "Player " + args[0] + " is either not online, or doesn't actually exist!", true);
            return true;
        }

        // Now we know our player is online
        // Lets get their inventory
        PlayerInventory targetInventory = targetPlayer.getInventory();

        // We would like to display their inventory to the commandSender
        // So we will create an inventory-like GUI and copy the contents from targetInventory
        invGUI = Bukkit.createInventory(targetPlayer, 54, targetPlayer.getName() + "'s Inventory");

        // Add some custom items (Armor & Currently Held paper indicators)
        setCustomItems(targetPlayer);

        // Fill row with glass panes
        fillRowWithItem(4, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));

        // Copy the inventory of our target player to our fake inventory GUI
        copyInventory(targetInventory);

        // If holding an item in main hand -> set it at invGUI slot
        if (!targetInventory.getItemInMainHand().equals(new ItemStack(Material.AIR))) {
            invGUI.setItem(52, targetInventory.getItemInMainHand());
        }

        // If holding an item in 2nd hand -> set it at invGUI slot
        if (!targetInventory.getItemInOffHand().equals(new ItemStack(Material.AIR))) {
            invGUI.setItem(51, targetInventory.getItemInOffHand());
        }

        // Generally, always set slot 50 to be a "separator"
        invGUI.setItem(50, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));

        // Display inventory in GUI for command sender
        p.openInventory(invGUI);
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 0.6f, 1.5f);

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

    public void setCustomItems(Player targetPlayer) {

        // GUI item for currently held items
        invGUI.setItem(53,
                new GuiItem(
                        Material.PAPER,
                        ChatColor.LIGHT_PURPLE + "Items in hands",
                        Arrays.asList(
                                ChatColor.GRAY + "The items that " + targetPlayer.getName() + " is holding."
                        )
                ).getItem()
        );

        // GUI item for armor slots
        invGUI.setItem(45,
                new GuiItem(
                        Material.PAPER,
                        ChatColor.LIGHT_PURPLE + "Armor",
                        Arrays.asList(
                                ChatColor.GRAY + "Armor that " + targetPlayer.getName() + " is wearing."
                        )
                ).getItem()
        );
    }

    public void fillRowWithItem(Integer row, ItemStack item) {

        int rowIndex = row * 9;

        // Check if we aren't going over the size of our inventory
        if (invGUI.getSize() >= rowIndex + 9)

            // Set the item on all the slots in the row
            for (int i = rowIndex; i < rowIndex + 9; i++) {
                invGUI.setItem(i, item);
            }

        else
            VortexUtilityCommands.getVortexLogger().sendAlert("ERROR Row goes over bounds of inventory!");
    }

    public void copyInventory(PlayerInventory inventoryToCopy) {

        for (int i = 0; i < inventoryToCopy.getSize(); i++) {

            // When we have reached slots that contain the armor of the player
            if (i > 35) {

                // Move them one row under
                //targetPlayer.
                invGUI.setItem(i + 10, inventoryToCopy.getItem(i));
            }

            else
                invGUI.setItem(i, inventoryToCopy.getItem(i));
        }
    }
}
