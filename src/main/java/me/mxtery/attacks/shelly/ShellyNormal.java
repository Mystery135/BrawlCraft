package me.mxtery.items;
import me.mxtery.Keys;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class ShellyNormal {

    public static ItemStack getShellyNormal(){
        ItemStack shellyNormal = new ItemStack(Material.CROSSBOW);
        ItemMeta meta = shellyNormal.getItemMeta();
        meta.addEnchant(Enchantment.QUICK_CHARGE, 5, true);
        meta.addEnchant(Enchantment.PIERCING, 5, true);
        meta.addEnchant(Enchantment.MULTISHOT, 5, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        meta.getPersistentDataContainer().set(Keys.shellyNormal, PersistentDataType.INTEGER, 1);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&l[BUCKSHOT]"));
        shellyNormal.setItemMeta(meta);
        return shellyNormal;
    }
}
