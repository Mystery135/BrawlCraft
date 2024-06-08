package me.mxtery.minecraftbrawlstars.helpers;

import me.mxtery.minecraftbrawlstars.MinecraftBrawlStars;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class VoicelineHelper {
    public static boolean playVoiceLine(Player player, String sound){return playVoiceLine(player, sound, 1, 1);}
    public static boolean playVoiceLine(Player player, String sound, float vol, float pitch){
        if (MinecraftBrawlStars.RECENTLY_PLAYED_VOICE_LINES.contains(player.getUniqueId())){
            return false;
        }
        player.getWorld().playSound(player.getLocation(), sound, vol, pitch);
        MinecraftBrawlStars.RECENTLY_PLAYED_VOICE_LINES.add(player.getUniqueId());
        new BukkitRunnable() {
            @Override
            public void run() {
                MinecraftBrawlStars.RECENTLY_PLAYED_VOICE_LINES.remove(player.getUniqueId());
            }
        }.runTaskLater(MinecraftBrawlStars.getInstance(), 20L * 5L /*<-- the delay */);
        return true;
    }
}
