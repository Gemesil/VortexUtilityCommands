package gemesil.vortexutilitycommands.objects;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class GuiItem {

    private ItemStack item;

    // - CONSTRUCTOR -
    public GuiItem (Material type, String name, List<String> lore) {
        ItemStack item = new ItemStack(type);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        item.setItemMeta(itemMeta);

        this.item = item;
    }

    // - GET -
    public ItemStack getItem() {
        return this.item;
    }
}
