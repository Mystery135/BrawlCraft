package me.mxtery.minecraftbrawlstars.helpers;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.mxtery.minecraftbrawlstars.MinecraftBrawlStars;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

public class SkinHelper {
    public static void setSkin(Player player, String value, String signature){
        EntityPlayer entityPlayer = ((CraftPlayer)player).getHandle();
        GameProfile gameProfile = entityPlayer.getBukkitEntity().getProfile();
        gameProfile.getProperties().removeAll("textures");
        gameProfile.getProperties().put("textures", new Property("textures",
                value,
                signature));
        Bukkit.getOnlinePlayers().forEach(p -> p.hidePlayer(player));
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(p -> p.showPlayer(player));

            }
        }.runTaskLater(MinecraftBrawlStars.getInstance(), 5);

    }
}
