package me.cormicmahaffeygrauer.main.Listener;

import me.cormicmahaffeygrauer.main.Invsee;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class DevBuildListener implements Listener {
    private Invsee main;
    public DevBuildListener(Invsee main)
    {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        if (e.getPlayer().hasPermission("invsee.admin"))
        {
            e.getPlayer().sendMessage(main.getPrefix() + ChatColor.YELLOW + "You are using a dev version! Consider upgrading to a newer version for a stable performance.");
        }
    }
}
