package me.mxtery.minecraftbrawlstars.kits;

import me.mxtery.minecraftbrawlstars.Keys;
import me.mxtery.minecraftbrawlstars.attacks.shelly.ShellyNormal;
import me.mxtery.minecraftbrawlstars.attacks.shelly.ShellySuper;
import me.mxtery.minecraftbrawlstars.helpers.*;
import me.mxtery.minecraftbrawlstars.MinecraftBrawlStars;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Shelly implements Listener {
    public static List<String> SHELLY_PLAYERS = new ArrayList<>();
    public static String NAME = "SHELLY";
    public static Material AMMO = Material.HEART_OF_THE_SEA;
    public static Material SUPER_TOKEN = Material.ENDER_EYE;
    public static int NORMAL_COOLDOWN = 1500;
    public static double DAMAGE_PER_NORMAL_SHELL = 2;
    public static double DAMAGE_PER_SUPER_SHELL = 2.5;
    public static void SET_KIT(Player player){
        player.getInventory().clear();
        player.getInventory().setItem(0, ShellyNormal.getShellyNormal());
        player.getInventory().setItem(1, ShellySuper.getShellySuper());
        SkinHelper.setSkin(player,
                "eyJ0aW1lc3RhbXAiOjE1NzY1NDAwNzU3MjcsInByb2ZpbGVJZCI6IjE5MjUyMWI0ZWZkYjQyNWM4OTMxZjAyYTg0OTZlMTFiIiwicHJvZmlsZU5hbWUiOiJTZXJpYWxpemFibGUiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzg2Y2M2Y2YyY2M2MTIyNTU3MGVkMmQyMjc4Y2M3ZjMwNTViOWEzYWU3MThmYjgyNGE0YTVjMjIzOWM3Njk3M2EiLCJtZXRhZGF0YSI6eyJtb2RlbCI6InNsaW0ifX19fQ==",
                "QuMl4kpNAvutkL0rsU5Kknvw2nP+aKV/CK0GBrF+/fz5sz/eB7dVk1+oXz7kyzTotcq40WpatOMDX9+JUvsxzjS1PKqLjX7xcMbMHqDTKVxYwWXLrV3zknSXKhQyfVLgHjxuRYDgUfQUCAXxuZubDNnBMPGxJhRiDHvk8Y5wju4xf3qFJq19mZxGJasLWxvULbehUKm5TJaKvZZemL+Ry9IA+IXMXGsq/vyNGQ4XKvGuqGXY2N7Vu8+3ALCm24Gt4leaU2wvwJyM0Zuly91HEBWG32yxM5tZ1VhUNZRPtWrHlctjfhjSxEXa6ZKGo5vCmCoYUvS/H2SsLndd3CV0p8iJkaVR1V2TGe1iyopgDyHr5/4V8oZjzOZww1rxJJ6Wg4txCKWtgOrAVWR6Z1KU3y73zJxaGUmxnnYTaWozhw4/tD5ko/8nUxh1wzsz3qq5MI8j5GqAvLiVGxGZC4dsVPPHTq1beQJSbP1CV59whsPH7SeeZ27JnaInojwmaulQoR/o0uuqb416pv7ZHTBG68VsTmBDcAvZYvD67FRwzGMPee8IOyt+xTbpcUNbJRhxdWqXRZ+3BWIdr7nv8wmUVfB9nWdAFs7W/hYEJeDyXufbEizYC/XZ2hs48JymP0ywP4USq4/E1bm0Rakm2c/+t1AAv2BdEC7wxT3eHSv5EA4="
                );
        MessageHelper.sendPluginMessage(player, "&aYour kit has been set to &e&lSHELLY&a!");
        player.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 5);
        Shelly.SHELLY_PLAYERS.add(player.getUniqueId().toString());
        MinecraftBrawlStars.getInstance().getConfig().set("Shelly", Shelly.SHELLY_PLAYERS);
        MinecraftBrawlStars.getInstance().getConfig().set(player.getUniqueId().toString(), Shelly.NAME);
        MinecraftBrawlStars.getInstance().saveConfig();
    }
    private void doShellySuper(Player player){
        if (AmmoHelper.getNumSuperToken(player, Shelly.SUPER_TOKEN) < 100){
            player.getWorld().playSound(player.getLocation(), "custom:shelly-no-ammo", 1.0f, 1.0f);
            return;
        }
        for (int i = -20; i <= 20; i += 5){
            Snowball snowball = player.launchProjectile(Snowball.class);
            snowball.setGravity(false);
            snowball.setVelocity((player.getLocation().getDirection().multiply(1.5f).rotateAroundY(Math.toRadians(i))));
            snowball.setCustomName("Shelly Super Shell");
            snowball.setItem(new ItemStack(Shelly.SUPER_TOKEN));
            Bukkit.getScheduler().runTaskLater(MinecraftBrawlStars.getInstance(), new Runnable() {
                @Override
                public void run() {
                    snowball.remove();
                }
            }, 9);
        }
        player.getWorld().playSound(player.getLocation(), "custom:shelly-super", 1.0f, 1.0f);
        if (!MinecraftBrawlStars.RECENTLY_PLAYED_VOICE_LINES.contains(player.getUniqueId())){
            int rand = (int) (Math.random()*4+1);
            VoicelineHelper.playVoiceLine(player,"custom:shelly-ulti-" + rand);
        }
        AmmoHelper.removeAllSuperTokens(player);

    }
    private static HashMap<UUID, Long> shellyNormalCooldowns = new HashMap<>();
    public static HashMap<UUID, Long> getShellyNormalCooldown() {
        return shellyNormalCooldowns;
    }
    public static double getShellyNormalCooldown(Player player){
        if (!shellyNormalCooldowns.containsKey(player.getUniqueId())) {
            return -1;
        }
        double timeleft = ((double) shellyNormalCooldowns.get(player.getUniqueId()) / 1000 - (double) System.currentTimeMillis() / 1000);
        timeleft = Math.round(timeleft*100.0)/100.0;
        return timeleft;
    }
    @EventHandler
    public void onShellyShoot(PlayerInteractEvent event){
        if (event.getItem() == null){return;}
        if (event.getHand() != EquipmentSlot.HAND){return;}
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK){
            return;
        }
        if (!event.getItem().hasItemMeta()){return;};
        if (event.getItem().getType() != Material.CROSSBOW){
            return;
        }
        if (!event.getItem().getItemMeta().getPersistentDataContainer().has(Keys.shellyNormal)){
            return;
        }
        Player player = event.getPlayer();
        event.setCancelled(true);
        doShellyNormalAttack(player);
    }
    @EventHandler
    public void onShellyHit(ProjectileHitEvent event){
        if (event.getEntity().getName().equals("Shelly Shell") || event.getEntity().getName().equals("Shelly Super Shell")){
            if (event.getHitEntity() instanceof LivingEntity){
                LivingEntity livingEntity = (LivingEntity) event.getHitEntity();
                if (event.getEntity().getName().equals("Shelly Super Shell")){
                    event.setCancelled(true);
                    if (event.getEntity().getShooter() instanceof Entity){
                        livingEntity.damage(Shelly.DAMAGE_PER_SUPER_SHELL, DamageSource.builder(DamageType.ARROW).withCausingEntity((Entity) event.getEntity().getShooter()).build()); //TODO: SEE IF DEATH MESSAGE WORKS!
                    }else{
                        livingEntity.damage(Shelly.DAMAGE_PER_SUPER_SHELL);
                    }

                    livingEntity.setVelocity(event.getEntity().getVelocity().add(livingEntity.getLocation().toVector().subtract(event.getEntity().getLocation().toVector()).normalize().multiply(2f)));
                }else{
                    livingEntity.damage(Shelly.DAMAGE_PER_NORMAL_SHELL, DamageSource.builder(DamageType.ARROW).withCausingEntity((Entity) event.getEntity().getShooter()).build());
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
                if (event.getEntity().getName().equals("Shelly Super Shell")){
                    BulletHelper.breakBlock(event.getHitBlock());
                }
            }
        }

    }
    private void doShellyNormalAttack(Player player){
        if (AmmoHelper.getNumAmmo(player, Shelly.AMMO) == 0){
            player.getWorld().playSound(player.getLocation(), "custom:shelly-no-ammo", 1.0f, 1.0f);
            return;
        }

        player.getInventory().setItem(8, new ItemStack(Shelly.AMMO, AmmoHelper.getNumAmmo(player, Shelly.AMMO)-1));
        if (AmmoHelper.getNumAmmo(player, Shelly.AMMO) == 0){
            player.getWorld().playSound(player.getLocation(), "custom:last-ammo", 1.0f, 1.0f);
        }
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
        player.getWorld().playSound(player.getLocation(), "custom:shelly-attack", 1.0f, 1.0f);
    }
    @EventHandler
    public void onShellySuper(PlayerInteractEvent event){
        if (event.getItem() == null){return;}
        if (event.getHand() != EquipmentSlot.HAND){return;}
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK){
            return;
        }
        if (!event.getItem().hasItemMeta()){return;};
        if (event.getItem().getType() != Material.CROSSBOW){
            return;
        }
        if (!event.getItem().getItemMeta().getPersistentDataContainer().has(Keys.shellySuper)){
            return;
        }
        Player player = event.getPlayer();
        event.setCancelled(true);
        doShellySuper(player);
    }
    @EventHandler
    public void onShellyHurt(EntityDamageEvent event){
        if (!(event.getEntity() instanceof Player)){
            return;
        }
        Player player = (Player) event.getEntity();
        if (!SHELLY_PLAYERS.contains(player.getUniqueId().toString())){
            return;
        }
        int rand = (int) (Math.random()*5+1);
        VoicelineHelper.playVoiceLine(player, "custom:shelly-hurt-"+ rand);
    }
    @EventHandler
    public void onShellyDie(EntityDeathEvent event){
        if (!(event.getEntity() instanceof Player)){
            return;
        }
        Player player = (Player) event.getEntity();
        if (!SHELLY_PLAYERS.contains(player.getUniqueId().toString())){
            return;
        }
        int rand = (int) (Math.random()*4+1);
        player.playSound(player.getLocation(), "custom:shelly-die-" + rand, 1, 1);
    }

}
