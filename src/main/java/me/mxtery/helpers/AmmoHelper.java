package me.mxtery.helpers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ArrowHelper {
    public static int getNumArrows(Player player){
        int numArrows = 0;
        for (ItemStack stack : player.getInventory()){
            if (stack == null){continue;}
            if (stack.getType() == Material.ARROW){
                numArrows = numArrows + stack.getAmount();
            }
        }
        return numArrows;
    }
}
