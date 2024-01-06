package me.cormicmahaffeygrauer.main.GUI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class InvseeMenu {
    public Inventory openInvseeMenu(Player player)
    {
        Inventory menu = Bukkit.createInventory(player, 27, "Invsee Menu");

        ItemStack view = new ItemStack(Material.CHEST);
        ItemMeta viewMeta = view.getItemMeta();
        viewMeta.setDisplayName(ChatColor.GREEN + "View Inventory");
        viewMeta.setLore(Arrays.asList
                (ChatColor.YELLOW + "Click to view a players inventory.",
                ChatColor.YELLOW + "You will be asked to input the players username."));
        view.setItemMeta(viewMeta);

        ItemStack clear = new ItemStack(Material.BARRIER);
        ItemMeta clearMeta = clear.getItemMeta();
        clearMeta.setDisplayName(ChatColor.GREEN + "Clear Inventory");
        clearMeta.setLore(Arrays.asList(
                ChatColor.YELLOW + "Click to clear a players inventory.",
                ChatColor.YELLOW + "You will be asked to input the players username."));
        clear.setItemMeta(clearMeta);

        menu.setItem(12, view);
        menu.setItem(14, clear);

        player.openInventory(menu);
        return menu;
    }
}
