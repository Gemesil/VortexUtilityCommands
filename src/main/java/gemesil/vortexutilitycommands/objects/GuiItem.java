package gemesil.vortexutilitycommands.objects;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class GuiItem {

    private ItemStack item;

    // - CONSTRUCTOR -
    public GuiItem (Material type, String name, List<String> lore) {
        ItemStack item = new ItemStack(type);

        item.getItemMeta().setDisplayName(name);
        item.getItemMeta().setLore(lore);
        item.getItemMeta().addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        this.item = item;
    }

    // - GET -
    public ItemStack getItem() {
        return this.item;
    }
}
