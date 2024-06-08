package me.mxtery.minecraftbrawlstars.commands;

import me.mxtery.minecraftbrawlstars.GameState;
import me.mxtery.minecraftbrawlstars.MinecraftBrawlStars;
import me.mxtery.minecraftbrawlstars.instance.Arena;
import me.mxtery.minecraftbrawlstars.kits.Shelly;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaCommand implements CommandExecutor {
    private MinecraftBrawlStars minigame;
    public ArenaCommand(MinecraftBrawlStars minigame){
        this.minigame = minigame;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 1 && args[0].equalsIgnoreCase("list")){
        player.sendMessage(ChatColor.GREEN + "These are the available arenas:");
        for (Arena arena : minigame.getArenaManager().getArenas()){
            player.sendMessage(ChatColor.GREEN + "- " + arena.getId() + "(" + arena.getState().name() + ")");

        }
        }else if (args.length == 1 && args[0].equalsIgnoreCase("leave")){
            Arena arena = minigame.getArenaManager().getArena(player);
            if (arena != null){
                player.sendMessage(ChatColor.RED + "You left the arena!");
                arena.removePlayer(player);
            }else{
                player.sendMessage(ChatColor.RED + "You are not in an arena!");
            }

        }else if (args.length == 2 && args[0].equalsIgnoreCase("join")){
        if (minigame.getArenaManager().getArena(player) !=null){
            player.sendMessage(ChatColor.RED + "You are already playing in an arena!");
            return false;
        }
        int id;
try {
    id = Integer.parseInt(args[1]);
}catch (Exception e){
    player.sendMessage(ChatColor.RED + "You specified an invalid arena ID.");
    return false;

}
if (id >= 0 && id<minigame.getArenaManager().getArenas().size()){
    Arena arena = minigame.getArenaManager().getArena(id);
    if (arena.getState() == GameState.RECRUITING || arena.getState() == GameState.COUNTDOWN){
        player.sendMessage(ChatColor.GREEN + "You are now playing in Arena " + id + ".");
        arena.addPlayer(player);
    }else{
        player.sendMessage(ChatColor.RED + "You cannot join this arena right now.");
    }
}else{
    player.sendMessage(ChatColor.RED + "You specified an invalid arena ID.");
}



        }else{
            player.sendMessage(ChatColor.RED + "Invalid usage! There are the options:");
            player.sendMessage(ChatColor.RED + "- /arena list");
            player.sendMessage(ChatColor.RED + "- /arena leave");
            player.sendMessage(ChatColor.RED + "- /arena join <id>");
        }

        return true;
    }
}
