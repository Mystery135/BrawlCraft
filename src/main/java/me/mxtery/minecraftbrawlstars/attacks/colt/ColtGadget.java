package me.mxtery.minecraftbrawlstars.attacks.colt;

import me.mxtery.minecraftbrawlstars.Keys;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class ColtGadget {
    public static ItemStack getColtGadget(){
        ItemStack shellyGadget = new ItemStack(Material.LIME_DYE, 3);
        ItemMeta meta = shellyGadget.getItemMeta();
        meta.getPersistentDataContainer().set(Keys.coltGadget, PersistentDataType.INTEGER, 1);
        meta.getPersistentDataContainer().set(Keys.coltGadget, PersistentDataType.INTEGER, 1);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&l[SPEEDLOADER]"));
        shellyGadget.setItemMeta(meta);
        return shellyGadget;
    }
    public static ItemStack getEmptyColtGadget(){
        ItemStack shellyGadget = new ItemStack(Material.GRAY_DYE);
        ItemMeta meta = shellyGadget.getItemMeta();
        meta.getPersistentDataContainer().set(Keys.coltItem, PersistentDataType.INTEGER, 1);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7&l[NO GADGETS LEFT]"));
        shellyGadget.setItemMeta(meta);
        return shellyGadget;
    }

}
