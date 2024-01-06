package me.cormicmahaffeygrauer.main.Tab;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class InvseeAdminTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {

        List<String> results = new ArrayList<>();

        if (args.length == 1)
        {
            results.add("reload");
            results.add("help");
            results.add("advancedmode");
        } else if (args.length == 2 && args[0].equalsIgnoreCase("advancedmode")) {
            results.add("on");
            results.add("off");
        }

        return results;
    }
}
