package me.cormicmahaffeygrauer.main.Command;

import me.cormicmahaffeygrauer.main.Invsee;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderseeCommand implements CommandExecutor {
    private Invsee main;
    public EnderseeCommand(Invsee main)
    {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command label, String s, String[] args) {

        if (sender instanceof Player)
        {
            Player p = (Player) sender;

            if (main.getConfig().getBoolean("advancedmode") == true) {
                main.getEnderseeMenu().openEnderseeMenu(p);
                return true;
            } else {
                if (args.length == 2) {
                    String player = args[1];

                    //Checks if the player is offline
                    if (!Bukkit.getOnlinePlayers().contains(Bukkit.getPlayerExact(player)))
                    {
                        p.sendMessage(main.getPrefix() + ChatColor.RED + player + " is offline!");
                        return true;
                    }

                    if (args[0].equalsIgnoreCase("view"))
                        //Opens the players inventory
                        p.openInventory(Bukkit.getServer().getPlayerExact(player).getEnderChest());
                    //Sends the player a message
                    p.sendMessage(main.getPrefix() + ChatColor.GREEN + "You are now viewing the ender chest of " + player);
                    //Logs the action if needed
                    if (main.getConfig().getBoolean("log-actions")) {
                        Bukkit.getLogger().info(p.getDisplayName() + " is viewing the ender chest of " + player);
                    }
                    //Informs the player if needed
                    if (!p.hasPermission("invsee.slient")) {
                        Bukkit.getPlayerExact(player).sendMessage(main.getPrefix() + ChatColor.RED + p.getDisplayName() + " is viewing your ender chest! They can move, take/delete, and add/give you items.");
                    }
                    if (args[1] == null) {
                        p.sendMessage(main.getPrefix() + ChatColor.RED + "Invalid usage. /endersee help");
                    } else if (args[0].equalsIgnoreCase("clear")) {
                        //Clears the players inventory
                        Bukkit.getPlayerExact(player).getEnderChest().clear();
                        //Sends the player a message
                        p.sendMessage(main.getPrefix() + ChatColor.GREEN + "You cleared " + player + "'s ender chest.");
                        if (main.getConfig().getBoolean("log-actions"))
                        {
                            Bukkit.getLogger().info(p.getDisplayName() + " cleared " + player + "'s ender chest.");
                        }
                        //Informs the player if needed
                        if (!p.hasPermission("invsee.slient"))
                        {
                            Bukkit.getPlayerExact(player).sendMessage(main.getPrefix() + ChatColor.RED + p.getDisplayName() + " just cleared your ender chest!");
                        }
                        if (args[1] == null)
                        {
                            p.sendMessage(main.getPrefix() + ChatColor.RED + "Invalid usage. /endersee help");
                        }
                    }
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("help"))
                    {
                        p.sendMessage(ChatColor.YELLOW + "------------Endersee Help------------");
                        p.sendMessage(ChatColor.GREEN + "/endersee view <player> - views that players ender chest");
                        p.sendMessage(ChatColor.GREEN + "/endersee clear <player> - clears that players ender chest");
                    }
                } else {
                    p.sendMessage(main.getPrefix() + ChatColor.RED + "Invalid usage! /endersee help");
                }
            }
        }
        return true;
    }
}
