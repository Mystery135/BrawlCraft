package me.mxtery.minecraftbrawlstars.listeners;

import me.mxtery.minecraftbrawlstars.MinecraftBrawlStars;
import me.mxtery.minecraftbrawlstars.instance.Arena;
import me.mxtery.minecraftbrawlstars.manager.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectListener implements Listener {
private MinecraftBrawlStars minigame;

public ConnectListener(MinecraftBrawlStars minigame){
    this.minigame = minigame;
}

    @EventHandler
public void onJoin(PlayerJoinEvent event){
    event.getPlayer().teleport(ConfigManager.getLobbySpawn());
}
    @EventHandler
public void onQuit(PlayerQuitEvent event){
Arena arena = minigame.getArenaManager().getArena(event.getPlayer());
if (arena == null){
    return;
}
arena.removePlayer(event.getPlayer());
}

}
