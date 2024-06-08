package me.mxtery.minecraftbrawlstars.helpers;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MessageHelper {
//    public static String getPluginName() {
//        return ChatColor.translateAlternateColorCodes('&', "&6&l[&bMobBattle&6&l]&r ");
//    }
    public static void sendPluginMessage(CommandSender sender, String msg) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l[&bBrawlCraft&6&l]&r " + ChatColor.translateAlternateColorCodes('&', msg)));
    }
}
