package me.cormicmahaffeygrauer.main.Tab;

import me.cormicmahaffeygrauer.main.Invsee;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EnderseeTab implements TabCompleter {
    private Invsee main;
    public EnderseeTab(Invsee main)
    {
        this.main = main;
    }
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {

        List<String> results = new ArrayList<>();

        if (main.getConfig().getBoolean("advancedmode") == false)
        {
            if (args.length == 1)
            {
                results.add("help");
                results.add("clear");
                results.add("view");
            } else if (args.length == 2) {
                for (Player player : Bukkit.getOnlinePlayers())
                {
                    results.add(player.getDisplayName());
                }
            }
        }

        return results;
    }
}
