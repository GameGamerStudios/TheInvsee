package me.cormicmahaffeygrauer.main.GUIListener;

import me.cormicmahaffeygrauer.main.Invsee;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class EnderseeMenuListener implements Listener {

    private Invsee main;
    List<UUID> eviewing = new ArrayList<>();
    List<UUID> eclearing = new ArrayList<>();
    public EnderseeMenuListener(Invsee main)
    {
        this.main = main;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e)
    {
        if (e.getView().getTitle().equals("Endersee Menu") && e.getCurrentItem() != null)
        {
            Player p = (Player) e.getWhoClicked();
            e.setCancelled(true);

            switch (e.getRawSlot())
            {
                case 12:
                    p.closeInventory();
                    p.sendMessage(main.getPrefix() + ChatColor.YELLOW + "Enter the username of the player's ender chest you want to view.");
                    eviewing.add(p.getUniqueId());
                    break;
                case 14:
                    p.closeInventory();
                    p.sendMessage(main.getPrefix() + ChatColor.YELLOW + "Enter the username of the player's ender chest you want to clear.");
                    eclearing.add(p.getUniqueId());
                    break;
            }
        }
    }

    @EventHandler
    public void onChat(PlayerChatEvent e)
    {
        Player p = e.getPlayer();
        String player = e.getMessage();

        if (eviewing.contains(p.getUniqueId()))
        {
            eviewing.remove(p.getUniqueId());
            //Checks if the player is offline.
            if (!Bukkit.getOnlinePlayers().contains(Bukkit.getPlayerExact(player)))
            {
                p.sendMessage(main.getPrefix() + ChatColor.RED + player + " is offline!");
                e.setCancelled(true);
                return;
            }
            p.openInventory(Bukkit.getPlayer(player).getEnderChest());
            p.sendMessage(main.getPrefix() + ChatColor.GREEN + "You cleared " + player + "'s ender chest.");
            //Logs the action if needed.
            if (main.getConfig().getBoolean("log-actions")) {
                Bukkit.getLogger().info(p.getDisplayName() + " is viewing the ender chest of " + player);
            }
            //Informs the player if needed.
            if (!p.hasPermission("invsee.slient")) {
                Bukkit.getPlayerExact(player).sendMessage(main.getPrefix() + ChatColor.RED + p.getDisplayName() + " is viewing your ender chest! They can move, take/delete, and add/give you items.");
            }
            e.setCancelled(true);
        } else if (eclearing.contains(p.getUniqueId())) {
            eclearing.remove(p.getUniqueId());
            //Checks if the player is offline.
            if (!Bukkit.getOnlinePlayers().contains(Bukkit.getPlayerExact(player)))
            {
                p.sendMessage(main.getPrefix() + ChatColor.RED + player + " is offline!");
                e.setCancelled(true);
                return;
            }
            Bukkit.getPlayerExact(player).getEnderChest().clear();
            p.sendMessage(main.getPrefix() + ChatColor.GREEN + "You cleared " + player + "'s ender chest.");
            //Logs the action if needed.
            if (main.getConfig().getBoolean("log-actions")) {
                Bukkit.getLogger().info(p.getDisplayName() + " cleared " + player + "'s ender chest.");
            }
            //Informs the player if needed.
            if (!p.hasPermission("invsee.slient")) {
                Bukkit.getPlayerExact(player).sendMessage(main.getPrefix() + ChatColor.RED + p.getDisplayName() + " just cleared your ender chest!");
            }
            e.setCancelled(true);
        }
    }
}
