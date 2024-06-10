package me.mxtery.minecraftbrawlstars.listeners;

import me.mxtery.minecraftbrawlstars.CooldownInit;
import me.mxtery.minecraftbrawlstars.GameState;
import me.mxtery.minecraftbrawlstars.MinecraftBrawlStars;
import me.mxtery.minecraftbrawlstars.instance.Arena;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class GameListener implements Listener {
private MinecraftBrawlStars mingame;

public GameListener (MinecraftBrawlStars minigame){
    this.mingame = minigame;
}


//    @EventHandler
//    public void onBlockBreak(BlockBreakEvent event){
//    Arena arena =mingame.getArenaManager().getArena(event.getPlayer());
//    if (arena == null || arena.getState() != GameState.LIVE){
//        return;
//    }
//        arena.getGame().addPoint(event.getPlayer());
//    }
    @EventHandler
    public void onMove(PlayerMoveEvent event){
    Player player = event.getPlayer();
        Arena arena =mingame.getArenaManager().getArena(player);
        if (arena == null || arena.getState() == GameState.LIVE){
            return;
        }
        event.setCancelled(true);
    }
    @EventHandler
    public void onPVP(EntityDamageEvent event){
    if (!(event.getEntity() instanceof Player)){
        return;
    }
    Player player = (Player) event.getEntity();
        Arena arena =mingame.getArenaManager().getArena(player);
        if (arena == null || arena.getState() == GameState.LIVE){
            CooldownInit.playerToNextHeal.put(player.getUniqueId(), 0);
            return;
        }
        event.setCancelled(true);

    }
    @EventHandler
    public void onPlayerDeath(EntityDamageByEntityEvent event){
        if (!(event.getEntity() instanceof Player)){
            return;
        }
        if (!(event.getDamager() instanceof Player)){
            return;
        }

        Player hitter = (Player) event.getDamager();
        Player player = (Player) event.getEntity();
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!player.isDead()){
                    return;
                }

                Arena arena =mingame.getArenaManager().getArena(player);
                if (arena == null || arena.getState() != GameState.LIVE){
                    return;
                }
                arena.getGame().addPoint(hitter);
            }
        }.runTaskLater(MinecraftBrawlStars.getInstance(), 1 /*<-- the delay */);

    }

}
