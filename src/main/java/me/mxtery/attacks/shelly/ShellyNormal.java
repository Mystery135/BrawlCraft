package me.mxtery.attacks.shelly;
import me.mxtery.Keys;
import me.mxtery.helpers.AmmoHelper;
import me.mxtery.kits.Shelly;
import me.mxtery.minecraftbrawlstars.MinecraftBrawlStars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.UUID;

public class ShellyNormal implements Listener {

    public static ItemStack getShellyNormal(){
        ItemStack shellyNormal = new ItemStack(Material.CROSSBOW);
        ItemMeta meta = shellyNormal.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        meta.getPersistentDataContainer().set(Keys.shellyNormal, PersistentDataType.INTEGER, 1);
        meta.getPersistentDataContainer().set(Keys.shellyItem, PersistentDataType.INTEGER, 1);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&l[BUCKSHOT]"));
        shellyNormal.setItemMeta(meta);
        return shellyNormal;
    }
    private static HashMap<UUID, Long> shellyCooldowns = new HashMap<>();


    public static HashMap<UUID, Long> getShellyCooldowns() {
        return shellyCooldowns;
    }


    public static double getShellyCooldowns(Player player){
        if (!shellyCooldowns.containsKey(player.getUniqueId())) {
            return -1;
        }
        double timeleft = ((double) shellyCooldowns.get(player.getUniqueId()) / 1000 - (double) System.currentTimeMillis() / 1000);
        timeleft = Math.round(timeleft*100.0)/100.0;
        return timeleft;
    }

    @EventHandler
    public void onShellyShoot(PlayerInteractEvent event){
        if (event.getItem() == null){return;}
        if (!event.getItem().hasItemMeta()){return;};
        if (event.getItem().getType() != Material.CROSSBOW){
            return;
        }
        if (!event.getItem().getItemMeta().getPersistentDataContainer().has(Keys.shellyNormal)){
            return;
        }
        Player player = event.getPlayer();
        if (AmmoHelper.getNumAmmo(player, Shelly.AMMO) == 0){
            event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), "custom:shelly-no-ammo", 1.0f, 1.0f);
            return;
        }

        event.setCancelled(true);
        player.getInventory().setItem(8, new ItemStack(Shelly.AMMO, AmmoHelper.getNumAmmo(player, Shelly.AMMO)-1));
        //clay pigeons: -2, 3,1 and increase velocity
        //norm: -10, 11, 5
        for (int i = -12; i <= 13; i += 5){
            if (i == 0) {
                continue;
            }
            Snowball snowball = player.launchProjectile(Snowball.class);
            snowball.setGravity(false);
            snowball.setVelocity((player.getLocation().getDirection().multiply(1.5f).rotateAroundY(Math.toRadians(i))));
            snowball.setCustomName("Shelly Shell");
            snowball.setItem(new ItemStack(Shelly.AMMO));
            Bukkit.getScheduler().runTaskLater(MinecraftBrawlStars.getInstance(), new Runnable() {
                @Override
                public void run() {
                    snowball.remove();
                }
            }, 9);
        }
        event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), "custom:shelly-attack", 1.0f, 1.0f);
    }
    @EventHandler
    public void onShellyHit(ProjectileHitEvent event){
        if (event.getEntity().getName().equals("Shelly Shell") || event.getEntity().getName().equals("Shelly Super Shell")){
            if (event.getHitEntity() instanceof LivingEntity){
                LivingEntity livingEntity = (LivingEntity) event.getHitEntity();
                if (event.getEntity().getName().equals("Shelly Super Shell")){
                    event.setCancelled(true);
                    if (event.getEntity().getShooter() instanceof Entity){
                        livingEntity.damage(2.5f, (Entity) event.getEntity().getShooter());
                    }else{
                        livingEntity.damage(2.5f);
                    }

                    livingEntity.setVelocity(event.getEntity().getVelocity().add(livingEntity.getLocation().toVector().subtract(event.getEntity().getLocation().toVector()).normalize().multiply(2f)));
                }else{
                    livingEntity.damage(2, livingEntity);
                }

                livingEntity.setNoDamageTicks(0);
                if (event.getEntity().getShooter() instanceof Player){
                    ((Player)event.getEntity().getShooter()).playSound(event.getHitEntity().getLocation(), "custom:shelly-hit", 1.0f, 1.0f);
                }
                if (event.getEntity().getShooter() instanceof Player){
                    Player player = (Player) event.getEntity().getShooter();
                    if (event.getEntity().getName().equals("Shelly Super Shell")){
                        AmmoHelper.addSuperToken(player, Shelly.SUPER_TOKEN, 5);
                    }else{
                        AmmoHelper.addSuperToken(player, Shelly.SUPER_TOKEN, 10);
                    }

                }
            }
            if (event.getHitBlock() != null){
                if (event.getHitBlock().getType().name().toLowerCase().contains("leaves") ||
                        event.getHitBlock().getType().name().toLowerCase().contains("grass")){
                    event.getHitBlock().getWorld().playSound(event.getHitBlock().getLocation(), "custom:shelly-hit-grass", 0.2f, 1.0f);
                }else{
                    event.getHitBlock().getWorld().playSound(event.getHitBlock().getLocation(), "custom:shelly-hit-wall", 0.6f, 1.0f);
                }
                if (event.getEntity().getName().equals("Shelly Super Shell")){event.getHitBlock().setType(Material.AIR);}
            }
        }

    }
}
