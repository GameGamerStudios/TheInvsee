package me.cormicmahaffeygrauer.main.Command;

import com.sun.tools.javac.api.BasicJavacTask;
import me.cormicmahaffeygrauer.main.Invsee;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.io.Console;

public class InvseeCommand implements CommandExecutor {
    private Invsee main;
    public InvseeCommand(Invsee main)
    {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (main.getConfig().getBoolean("advancedmode") == true) {
                main.getInvseeMenu().openInvseeMenu(p);
                return true;
            } else {
                if (args.length == 2) {
                    String player = args[1];
                    if (!Bukkit.getOnlinePlayers().contains(Bukkit.getPlayerExact(player))) {
                        p.sendMessage(main.getPrefix() + ChatColor.RED + player + " is offline!");
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("clear")) {
                        //Clears the players inventory
                        Bukkit.getPlayerExact(player).getInventory().clear();
                        //Sends the player a message
                        p.sendMessage(main.getPrefix() + ChatColor.GREEN + "You cleared " + player + "'s inventory.");
                        if (main.getConfig().getBoolean("log-actions")) {
                            Bukkit.getLogger().info(p.getDisplayName() + " cleared " + player + "'s inventory.");
                        }
                        //Informs the player if needed
                        if (!p.hasPermission("invsee.slient")) {
                            Bukkit.getPlayerExact(player).sendMessage(main.getPrefix() + ChatColor.RED + p.getDisplayName() + " just cleared your inventory!");
                        }
                        if (args[1] == null) {
                            p.sendMessage(main.getPrefix() + ChatColor.RED + "Invalid usage. /invsee help");
                        }
                    } else if (args[0].equalsIgnoreCase("view")) {
                        //Opens the players inventory
                        p.openInventory(Bukkit.getServer().getPlayerExact(player).getInventory());
                        //Sends the player a message
                        p.sendMessage(main.getPrefix() + ChatColor.GREEN + "You are now viewing the inventory of " + player);
                        //Logs the action if needed
                        if (main.getConfig().getBoolean("log-actions")) {
                            Bukkit.getLogger().info(p.getDisplayName() + " is viewing the inventory of " + player);
                        }
                        //Informs the player if needed
                        if (!p.hasPermission("invsee.slient")) {
                            Bukkit.getPlayerExact(player).sendMessage(main.getPrefix() + ChatColor.RED + p.getDisplayName() + " is viewing your inventory! They can move, take/delete, and add/give you items.");
                        }
                        if (args[1] == null) {
                            p.sendMessage(main.getPrefix() + ChatColor.RED + "Invalid usage. /invsee help");
                        }
                    }
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("help")) {
                        p.sendMessage(ChatColor.YELLOW + "------------Invsee Help------------");
                        p.sendMessage(ChatColor.GREEN + "/invsee view <player> - views that players inventory");
                        p.sendMessage(ChatColor.GREEN + "/invsee clear <player> - clears that players inventory");
                    } else {
                        p.sendMessage(main.getPrefix() + ChatColor.RED + "Invalid usage. /invsee help");
                    }
                } else {
                    p.sendMessage(main.getPrefix() + ChatColor.RED + "Invalid usage. /invsee help");
                }
            }
        }
        return true;
    }
}
