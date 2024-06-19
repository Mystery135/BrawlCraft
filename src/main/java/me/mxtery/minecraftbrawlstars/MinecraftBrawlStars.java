package me.mxtery.minecraftbrawlstars;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.mxtery.minecraftbrawlstars.commands.ArenaCommand;
import me.mxtery.minecraftbrawlstars.commands.MinecraftBrawlStarsCommand;
import me.mxtery.minecraftbrawlstars.commands.MinecraftBrawlStarsTabCompleter;
import me.mxtery.minecraftbrawlstars.kits.Colt;
import me.mxtery.minecraftbrawlstars.kits.Shelly;
import me.mxtery.minecraftbrawlstars.listeners.ConnectListener;
import me.mxtery.minecraftbrawlstars.listeners.GameListener;
import me.mxtery.minecraftbrawlstars.listeners.GeneralListeners;
import me.mxtery.minecraftbrawlstars.manager.ArenaManager;
import me.mxtery.minecraftbrawlstars.manager.ConfigManager;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class MinecraftBrawlStars extends JavaPlugin implements Listener {
    private ArenaManager arenaManager;
    private static MinecraftBrawlStars plugin;
    public static MinecraftBrawlStars getInstance(){
        return plugin;
    }
    public static List<UUID> RECENTLY_PLAYED_VOICE_LINES = new ArrayList<>();
    public static HashMap<UUID, Integer> PLAYER_TO_GADGET_REMAINING = new HashMap();
    public ArenaManager getArenaManager() {
        return arenaManager;
    }

    @Override
    public void onEnable() {
        plugin = this;
        ConfigManager.setupConfig(this);
        arenaManager = new ArenaManager(this);
        Bukkit.getPluginManager().registerEvents(new ConnectListener(this), this);
        Bukkit.getPluginManager().registerEvents(new GameListener(this), this);
        getCommand("arena").setExecutor(new ArenaCommand(this));

        Keys.init(this);
        getCommand("minecraftbrawlstars").setExecutor(new MinecraftBrawlStarsCommand());
        getCommand("minecraftbrawlstars").setTabCompleter(new MinecraftBrawlStarsTabCompleter());
        getServer().getPluginManager().registerEvents(this, this);
//        getServer().getPluginManager().registerEvents(new ShellyNormal(), this);
//        getServer().getPluginManager().registerEvents(new ShellySuper(), this);
//        getServer().getPluginManager().registerEvents(new ShellyGadget(), this);
        getServer().getPluginManager().registerEvents(new Shelly(), this);
        getServer().getPluginManager().registerEvents(new Colt(), this);
        getServer().getPluginManager().registerEvents(new CooldownInit(), this);
        getServer().getPluginManager().registerEvents(new GeneralListeners(), this);

        if (getConfig().getList("Shelly") != null){
            Shelly.SHELLY_PLAYERS = (List<String>) getConfig().getList("Shelly");
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        if (Shelly.SHELLY_PLAYERS.contains(event.getPlayer().getUniqueId().toString())){
            setSkin2(event.getPlayer());
        }
    }

    private void setSkin2(Player player){
        EntityPlayer entityPlayer = ((CraftPlayer)player).getHandle();
        GameProfile gameProfile = entityPlayer.getBukkitEntity().getProfile();
        gameProfile.getProperties().removeAll("textures");
        gameProfile.getProperties().put("textures", new Property("textures", "eyJ0aW1lc3RhbXAiOjE1NzY1NDAwNzU3MjcsInByb2ZpbGVJZCI6IjE5MjUyMWI0ZWZkYjQyNWM4OTMxZjAyYTg0OTZlMTFiIiwicHJvZmlsZU5hbWUiOiJTZXJpYWxpemFibGUiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzg2Y2M2Y2YyY2M2MTIyNTU3MGVkMmQyMjc4Y2M3ZjMwNTViOWEzYWU3MThmYjgyNGE0YTVjMjIzOWM3Njk3M2EiLCJtZXRhZGF0YSI6eyJtb2RlbCI6InNsaW0ifX19fQ==", "QuMl4kpNAvutkL0rsU5Kknvw2nP+aKV/CK0GBrF+/fz5sz/eB7dVk1+oXz7kyzTotcq40WpatOMDX9+JUvsxzjS1PKqLjX7xcMbMHqDTKVxYwWXLrV3zknSXKhQyfVLgHjxuRYDgUfQUCAXxuZubDNnBMPGxJhRiDHvk8Y5wju4xf3qFJq19mZxGJasLWxvULbehUKm5TJaKvZZemL+Ry9IA+IXMXGsq/vyNGQ4XKvGuqGXY2N7Vu8+3ALCm24Gt4leaU2wvwJyM0Zuly91HEBWG32yxM5tZ1VhUNZRPtWrHlctjfhjSxEXa6ZKGo5vCmCoYUvS/H2SsLndd3CV0p8iJkaVR1V2TGe1iyopgDyHr5/4V8oZjzOZww1rxJJ6Wg4txCKWtgOrAVWR6Z1KU3y73zJxaGUmxnnYTaWozhw4/tD5ko/8nUxh1wzsz3qq5MI8j5GqAvLiVGxGZC4dsVPPHTq1beQJSbP1CV59whsPH7SeeZ27JnaInojwmaulQoR/o0uuqb416pv7ZHTBG68VsTmBDcAvZYvD67FRwzGMPee8IOyt+xTbpcUNbJRhxdWqXRZ+3BWIdr7nv8wmUVfB9nWdAFs7W/hYEJeDyXufbEizYC/XZ2hs48JymP0ywP4USq4/E1bm0Rakm2c/+t1AAv2BdEC7wxT3eHSv5EA4="));
    }
}
