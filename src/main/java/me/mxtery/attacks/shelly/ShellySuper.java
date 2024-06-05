package me.mxtery.attacks.shelly;

import me.mxtery.Keys;
import me.mxtery.helpers.AmmoHelper;
import me.mxtery.kits.Shelly;
import me.mxtery.minecraftbrawlstars.MinecraftBrawlStars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
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
    @EventHandler
    public void onShellySuper(PlayerInteractEvent event){
        if (event.getItem() == null){return;}
        if (!event.getItem().hasItemMeta()){return;};
        if (event.getItem().getType() != Material.CROSSBOW){
            return;
        }
        if (!event.getItem().getItemMeta().getPersistentDataContainer().has(Keys.shellySuper)){
            return;
        }
        Player player = event.getPlayer();
        if (AmmoHelper.getNumSuperToken(player, Shelly.SUPER_TOKEN) < 100){
            event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), "custom:shelly-no-ammo", 1.0f, 1.0f);
            return;
        }

        event.setCancelled(true);
        //clay pigeons: -2, 3,1 and increase velocity
        //norm: -10, 11, 5
        for (int i = -20; i <= 20; i += 5){
            Snowball snowball = player.launchProjectile(Snowball.class);
            snowball.setGravity(false);
            snowball.setVelocity((player.getLocation().getDirection().multiply(1.2f).rotateAroundY(Math.toRadians(i))));
            snowball.setCustomName("Shelly Super Shell");
            snowball.setItem(new ItemStack(Shelly.SUPER_TOKEN));
            Bukkit.getScheduler().runTaskLater(MinecraftBrawlStars.getInstance(), new Runnable() {
                @Override
                public void run() {
                    snowball.remove();
                }
            }, 9);
        }
        event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), "custom:shelly-super", 1.0f, 1.0f);
        AmmoHelper.removeAllSuperTokens(event.getPlayer());
    }

}
