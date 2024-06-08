package me.mxtery.minecraftbrawlstars.attacks.shelly;

import me.mxtery.minecraftbrawlstars.Keys;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class ShellySuper implements Listener {
    public static ItemStack getShellySuper(){
        ItemStack shellySuper = new ItemStack(Material.CROSSBOW);
        ItemMeta meta = shellySuper.getItemMeta();
        meta.addEnchant(Enchantment.QUICK_CHARGE, 5, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        meta.getPersistentDataContainer().set(Keys.shellySuper, PersistentDataType.INTEGER, 1);
        meta.getPersistentDataContainer().set(Keys.shellyItem, PersistentDataType.INTEGER, 1);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&l[SUPER SHELL]"));
        shellySuper.setItemMeta(meta);
        return shellySuper;
    }


}
