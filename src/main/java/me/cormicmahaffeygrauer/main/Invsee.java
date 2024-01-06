package me.cormicmahaffeygrauer.main;

import me.cormicmahaffeygrauer.main.Command.EnderseeCommand;
import me.cormicmahaffeygrauer.main.Command.InvseeAdminCommand;
import me.cormicmahaffeygrauer.main.Command.InvseeCommand;
import me.cormicmahaffeygrauer.main.GUI.EnderseeMenu;
import me.cormicmahaffeygrauer.main.GUI.InvseeMenu;
import me.cormicmahaffeygrauer.main.GUIListener.EnderseeMenuListener;
import me.cormicmahaffeygrauer.main.GUIListener.InvseeMenuListener;
import me.cormicmahaffeygrauer.main.Listener.DevBuildListener;
import me.cormicmahaffeygrauer.main.Tab.EnderseeTab;
import me.cormicmahaffeygrauer.main.Tab.InvseeAdminTab;
import me.cormicmahaffeygrauer.main.Tab.InvseeTab;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bstats.bukkit.Metrics;

public final class Invsee extends JavaPlugin {

    private InvseeMenu invseeMenu;
    private EnderseeMenu enderseeMenu;

    @Override
    public void onEnable() {
        //BSTATS STUFF
        Metrics metrics = new Metrics(this, 20669);

        invseeMenu = new InvseeMenu();
        enderseeMenu = new EnderseeMenu();

        /* Put in comments unless it's a dev build
        Bukkit.getLogger().warning(getPrefix() + "You are using a dev build!");
        Bukkit.getLogger().warning(getPrefix() + "It is highly recommended to use a stable build.");
        getServer().getPluginManager().registerEvents(new DevBuildListener(this), this);
        Put in comments unless it's a dev build */

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getServer().getPluginCommand("invsee").setExecutor(new InvseeCommand(this));
        getServer().getPluginCommand("invseeadmin").setExecutor(new InvseeAdminCommand(this));
        getServer().getPluginCommand("endersee").setExecutor(new EnderseeCommand(this));

        getServer().getPluginCommand("invseeadmin").setTabCompleter(new InvseeAdminTab());
        getServer().getPluginCommand("invsee").setTabCompleter(new InvseeTab(this));
        getServer().getPluginCommand("endersee").setTabCompleter(new EnderseeTab(this));

        getServer().getPluginManager().registerEvents(new InvseeMenuListener(this), this);
        getServer().getPluginManager().registerEvents(new EnderseeMenuListener(this), this);
    }

    public String getPrefix()
    {
        return ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix"));
    }
    public InvseeMenu getInvseeMenu() { return invseeMenu; }
    public EnderseeMenu getEnderseeMenu() { return enderseeMenu; }
}
