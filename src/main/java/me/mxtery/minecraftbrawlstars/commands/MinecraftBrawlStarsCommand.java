package me.mxtery.minecraftbrawlstars.commands;

import me.mxtery.minecraftbrawlstars.helpers.MessageHelper;
import me.mxtery.minecraftbrawlstars.kits.Shelly;
import me.mxtery.minecraftbrawlstars.MinecraftBrawlStars;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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
                        Shelly.SET_KIT(player);
                    }
                }
                break;
            }
        }
        return true;
    }
}
