package me.mxtery.minecraftbrawlstars.instance;

import me.mxtery.minecraftbrawlstars.GameState;
import me.mxtery.minecraftbrawlstars.kits.Shelly;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Game {
    private Arena arena;
    private HashMap<UUID, Integer> points;
    public Game(Arena arena){
        this.arena = arena;
        points = new HashMap<>();
    }
    public void start(){
        arena.setState(GameState.LIVE);
        arena.sendMessage(ChatColor.GREEN + "GAME HAS STARTED! Your objective kill the other player.");

        for (UUID uuid : arena.getPlayers()){
            points.put(uuid, 0);
            Shelly.SET_KIT(Bukkit.getPlayer(uuid));
        }
    }

public void addPoint(Player player){
        int playerPoints = points.get(player.getUniqueId()) + 1;
        if (playerPoints == 1){
            arena.sendMessage(ChatColor.GOLD + player.getName() + " HAS WON! THANKS FOR PLAYING!");
            arena.reset(true, true);
            return;
        }
//        player.sendMessage(ChatColor.GREEN + "+1 POINT!");
        points.replace(player.getUniqueId(), playerPoints);
}

}
