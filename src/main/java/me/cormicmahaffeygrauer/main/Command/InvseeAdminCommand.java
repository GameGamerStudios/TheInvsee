package me.cormicmahaffeygrauer.main.Command;

import me.cormicmahaffeygrauer.main.Invsee;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvseeAdminCommand implements CommandExecutor {
    private Invsee main;
    public InvseeAdminCommand(Invsee main)
    {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command label, String s, String[] args) {
        if (sender instanceof Player)
        {
            Player p = (Player) sender;
            if (args.length == 1)
            {
                if (args[0].equalsIgnoreCase("reload"))
                {
                    main.reloadConfig();
                    p.sendMessage(main.getPrefix() + ChatColor.GREEN + "Plugin reloaded!");
                } else if (args[0].equalsIgnoreCase("help")) {
                    p.sendMessage(ChatColor.YELLOW + "--------Invsee Admin Help-------");
                    p.sendMessage(ChatColor.YELLOW + "/invseeadmin reload - reloads the config");
                    p.sendMessage(ChatColor.YELLOW + "/invseeadmin advancedmode <on/off> - toggles advanced mode");
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("advancedmode"))
                {
                    if (args[1].equalsIgnoreCase("on"))
                    {
                        main.getConfig().set("advanced-mode", true);
                        p.sendMessage(main.getPrefix() + ChatColor.GREEN + "Advanced mode enabled.");
                        main.reloadConfig();
                    } else if (args[1].equalsIgnoreCase("off")) {
                        main.getConfig().set("advanced-mode", false);
                        main.reloadConfig();
                        p.sendMessage(main.getPrefix() + ChatColor.GREEN + "Advanced mode disabled.");
                    }
                }
            } else {
                p.sendMessage(main.getPrefix() + ChatColor.RED + "Invalid usage! /invseeadmin help");
            }
        }
        return true;
    }
}
