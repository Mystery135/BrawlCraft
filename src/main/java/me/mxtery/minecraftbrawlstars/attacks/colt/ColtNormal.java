package me.mxtery.minecraftbrawlstars.attacks.colt;
import me.mxtery.minecraftbrawlstars.Keys;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class ColtNormal {

    public static ItemStack getColtNormal(){
        ItemStack shellyNormal = new ItemStack(Material.CROSSBOW);
        ItemMeta meta = shellyNormal.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        meta.getPersistentDataContainer().set(Keys.shellyNormal, PersistentDataType.INTEGER, 1);
        meta.getPersistentDataContainer().set(Keys.shellyItem, PersistentDataType.INTEGER, 1);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&l[SIX-SHOOTERS]"));
        shellyNormal.setItemMeta(meta);
        return shellyNormal;
    }

}
