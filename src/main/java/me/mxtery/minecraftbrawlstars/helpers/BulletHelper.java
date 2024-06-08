package me.mxtery.minecraftbrawlstars.helpers;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class BulletHelper {
    public static boolean breakBlock(Block block){
        if (block.getType().getHardness() > 0 && block.getType().getHardness() < 30){
            block.setType(Material.AIR);
            return true;
        }else{
            return false;
        }
    }
}
