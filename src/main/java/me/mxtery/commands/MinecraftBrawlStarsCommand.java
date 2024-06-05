package me.mxtery.commands;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.mxtery.attacks.shelly.ShellySuper;
import me.mxtery.helpers.MessageHelper;
import me.mxtery.attacks.shelly.ShellyNormal;
import me.mxtery.kits.Shelly;
import me.mxtery.minecraftbrawlstars.MinecraftBrawlStars;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class MinecraftBrawlStarsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(args.length < 1){
            MessageHelper.sendPluginMessage(sender, "&cInvalid arguments!");
        }
        switch (args[0].toUpperCase()){
            case "KIT": {
                if (!(sender instanceof Player)){
                    MessageHelper.sendPluginMessage(sender, "&cOnly players can execute this command!");
                    return true;
                }
                Player player = (Player) sender;
                MessageHelper.sendPluginMessage(sender, "&aYour kit is " + MinecraftBrawlStars.getInstance().getConfig().get(player.getUniqueId().toString()) + "!");
                player.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 5);
                break;
            }
            case "KITSELECT": {
                if (Bukkit.getPlayer(args[1]) == null){
                    MessageHelper.sendPluginMessage(sender, "&cInvalid player!");
                    return true;
                }
                Player player = Bukkit.getPlayer(args[1]);
                switch (args[2].toUpperCase()){
                    case "SHELLY":{
                        assert player != null;
                        player.getInventory().clear();
                        player.getInventory().setItem(0, ShellyNormal.getShellyNormal());
                        player.getInventory().setItem(1, ShellySuper.getShellySuper());
                        setSkin2(player);
                        MessageHelper.sendPluginMessage(player, "&aYour kit has been set to &e&lSHELLY&a!");
                        player.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 5);
                        Shelly.SHELLY_PLAYERS.add(player.getUniqueId().toString());
                        MinecraftBrawlStars.getInstance().getConfig().set("Shelly", Shelly.SHELLY_PLAYERS);
                        MinecraftBrawlStars.getInstance().getConfig().set(player.getUniqueId().toString(), Shelly.NAME);
                        MinecraftBrawlStars.getInstance().saveConfig();
                    }
                }
                break;
            }
        }



        return true;
    }
    private void setSkin2(Player player){
        EntityPlayer entityPlayer = ((CraftPlayer)player).getHandle();
        GameProfile gameProfile = entityPlayer.getBukkitEntity().getProfile();
        gameProfile.getProperties().removeAll("textures");
        gameProfile.getProperties().put("textures", new Property("textures", "eyJ0aW1lc3RhbXAiOjE1NzY1NDAwNzU3MjcsInByb2ZpbGVJZCI6IjE5MjUyMWI0ZWZkYjQyNWM4OTMxZjAyYTg0OTZlMTFiIiwicHJvZmlsZU5hbWUiOiJTZXJpYWxpemFibGUiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzg2Y2M2Y2YyY2M2MTIyNTU3MGVkMmQyMjc4Y2M3ZjMwNTViOWEzYWU3MThmYjgyNGE0YTVjMjIzOWM3Njk3M2EiLCJtZXRhZGF0YSI6eyJtb2RlbCI6InNsaW0ifX19fQ==", "QuMl4kpNAvutkL0rsU5Kknvw2nP+aKV/CK0GBrF+/fz5sz/eB7dVk1+oXz7kyzTotcq40WpatOMDX9+JUvsxzjS1PKqLjX7xcMbMHqDTKVxYwWXLrV3zknSXKhQyfVLgHjxuRYDgUfQUCAXxuZubDNnBMPGxJhRiDHvk8Y5wju4xf3qFJq19mZxGJasLWxvULbehUKm5TJaKvZZemL+Ry9IA+IXMXGsq/vyNGQ4XKvGuqGXY2N7Vu8+3ALCm24Gt4leaU2wvwJyM0Zuly91HEBWG32yxM5tZ1VhUNZRPtWrHlctjfhjSxEXa6ZKGo5vCmCoYUvS/H2SsLndd3CV0p8iJkaVR1V2TGe1iyopgDyHr5/4V8oZjzOZww1rxJJ6Wg4txCKWtgOrAVWR6Z1KU3y73zJxaGUmxnnYTaWozhw4/tD5ko/8nUxh1wzsz3qq5MI8j5GqAvLiVGxGZC4dsVPPHTq1beQJSbP1CV59whsPH7SeeZ27JnaInojwmaulQoR/o0uuqb416pv7ZHTBG68VsTmBDcAvZYvD67FRwzGMPee8IOyt+xTbpcUNbJRhxdWqXRZ+3BWIdr7nv8wmUVfB9nWdAFs7W/hYEJeDyXufbEizYC/XZ2hs48JymP0ywP4USq4/E1bm0Rakm2c/+t1AAv2BdEC7wxT3eHSv5EA4="));
        Bukkit.getOnlinePlayers().forEach(p -> p.hidePlayer(player));
        Bukkit.getOnlinePlayers().forEach(p -> p.showPlayer(player));
    }
}
