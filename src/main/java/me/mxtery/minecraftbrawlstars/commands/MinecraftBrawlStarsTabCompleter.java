package me.mxtery.minecraftbrawlstars.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinecraftBrawlStarsTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 1){

            return StringUtil.copyPartialMatches(args[0], Arrays.asList("kitselect", "kit"), new ArrayList<>());
        }
        else if (args.length == 2){
            List<String> players = new ArrayList<>();
            for (Player player : Bukkit.getOnlinePlayers()) {
                players.add(player.getName());
            }
            return StringUtil.copyPartialMatches(args[1], players, new ArrayList<>());
        }
       else if (args.length == 3){
            return StringUtil.copyPartialMatches(args[2], Arrays.asList("Shelly"), new ArrayList<>());
        }
        return Collections.singletonList("");
    }
}
