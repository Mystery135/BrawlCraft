package me.mxtery.minecraftbrawlstars.attacks.shelly;

import me.mxtery.minecraftbrawlstars.Keys;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class ShellyGadget {
    public static ItemStack getShellyGadget(){
        ItemStack shellyGadget = new ItemStack(Material.LIME_DYE, 3);
        ItemMeta meta = shellyGadget.getItemMeta();
        meta.getPersistentDataContainer().set(Keys.shellyGadget, PersistentDataType.INTEGER, 1);
        meta.getPersistentDataContainer().set(Keys.shellyItem, PersistentDataType.INTEGER, 1);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&l[CLAY PIGEONS]"));
        shellyGadget.setItemMeta(meta);
        return shellyGadget;
    }
    public static ItemStack getEmptyShellyGadget(){
        ItemStack shellyGadget = new ItemStack(Material.GRAY_DYE);
        ItemMeta meta = shellyGadget.getItemMeta();
        meta.getPersistentDataContainer().set(Keys.shellyItem, PersistentDataType.INTEGER, 1);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7&l[NO GADGETS LEFT]"));
        shellyGadget.setItemMeta(meta);
        return shellyGadget;
    }

}
