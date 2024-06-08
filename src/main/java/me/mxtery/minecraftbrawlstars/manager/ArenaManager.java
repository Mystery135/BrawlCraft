package me.mxtery.minecraftbrawlstars.manager;

import me.mxtery.minecraftbrawlstars.MinecraftBrawlStars;
import me.mxtery.minecraftbrawlstars.instance.Arena;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager {
    private List<Arena> arenas = new ArrayList<>();
    public ArenaManager(MinecraftBrawlStars minigame){
        FileConfiguration config = minigame.getConfig();
        for (String s : config.getConfigurationSection("arenas.").getKeys(false)){
            arenas.add(new Arena(minigame, Integer.parseInt(s),new Location(
                    Bukkit.getWorld(config.getString("arenas." + s + ".world")),
                    config.getDouble("arenas." + s + ".x"),
                    config.getDouble("arenas." + s + ".y"),
                    config.getDouble("arenas." + s + ".z"),
                    (float)config.getDouble("arenas." + s + ".yaw"),
                    (float) config.getDouble("arenas." + s + ".pitch")
            ), new Location(
                    Bukkit.getWorld(config.getString("arenas." + s + ".world")),
                    config.getDouble("arenas." + s + ".x1"),
                    config.getDouble("arenas." + s + ".y1"),
                    config.getDouble("arenas." + s + ".z1"),
                    (float)config.getDouble("arenas." + s + ".yaw1"),
                    (float) config.getDouble("arenas." + s + ".pitch1")
            )));
        }
    }
    public List<Arena> getArenas(){
        return arenas;
    }
    public Arena getArena(Player player){
        for (Arena arena : arenas){
            if (arena.getPlayers().contains(player.getUniqueId())){
                return arena;
            }
        }
        return null;
    }
    public Arena getArena(int id){
        for (Arena arena : arenas){
            if (arena.getId() == id){
                return arena;
            }
        }
        return null;
    }
}
