package me.mxtery.minecraftbrawlstars.helpers;

import me.mxtery.minecraftbrawlstars.kits.Shelly;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AmmoHelper {
    public static int getNumAmmo(Player player, Material ammo){
        int numAmmo = 0;
        for (ItemStack stack : player.getInventory()){
            if (stack == null){continue;}
            if (stack.getType() == ammo){
                numAmmo = numAmmo + stack.getAmount();
            }
        }
        return numAmmo;
    }
    public static int getNumSuperToken(Player player, Material superToken){
        int numSuperTokens = 0;
        for (ItemStack stack : player.getInventory()){
            if (stack == null){continue;}
            if (stack.getType() == superToken){
                numSuperTokens = numSuperTokens + stack.getAmount();
            }
        }
        return numSuperTokens;
    }
    public static void addSuperToken(Player player, Material superToken, int amount){
        int numTokens = getNumSuperToken(player, superToken);
        if (numTokens == 0){
            player.getInventory().setItem(7, new ItemStack(superToken, amount));
            if (getNumAmmo(player, superToken) >= 100){
                player.playSound(player, "custom:super-charged", 4.0f, 1.0f);
            }
        }else if (numTokens >= 100){
            player.getInventory().setItem(7, new ItemStack(superToken, 100));
        }else{
            player.getInventory().setItem(7, new ItemStack(superToken, getNumSuperToken(player, superToken) + amount));
            if (getNumAmmo(player, superToken) >= 100){
                player.playSound(player, "custom:super-charged", 10.0f, 1.0f);
            }
        }

    }
    public static void removeAllSuperTokens(Player player){
        player.getInventory().setItem(7, new ItemStack(Material.AIR));
    }
    public static void removeAmmo(Player player, Material ammo){
        player.getInventory().setItem(8, new ItemStack(ammo, AmmoHelper.getNumAmmo(player, ammo)-1));
    }
    public static void removeGadget(Player player){
        player.getInventory().getItem(2).setAmount(player.getInventory().getItem(2).getAmount()-1);
    }
}
