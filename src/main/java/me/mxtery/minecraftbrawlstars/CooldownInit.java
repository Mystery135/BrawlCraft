package me.mxtery.minecraftbrawlstars;

import me.mxtery.minecraftbrawlstars.helpers.AmmoHelper;
import me.mxtery.minecraftbrawlstars.kits.Shelly;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import static me.mxtery.minecraftbrawlstars.helpers.AmmoHelper.getNumAmmo;

public class CooldownInit implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        new BukkitRunnable() {
            @Override
            public void run() {

                if (!player.isOnline()) {
                    cancel(); // this cancels it when they leave
                }

                if (player.getInventory().getItemInMainHand() == null){
                    return;
                }
                if (!player.getInventory().getItemInMainHand().hasItemMeta()){
                    return;
                }

                 if (player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(Keys.shellyItem, PersistentDataType.INTEGER)) {
                    StringBuilder actionbartext = new StringBuilder("");
                     if (AmmoHelper.getNumSuperToken(player, Shelly.SUPER_TOKEN) >100){
                         player.getInventory().setItem(7, new ItemStack(Shelly.SUPER_TOKEN, 100));
                     }
                    if (Shelly.getShellyNormalCooldown(player) > 0 || getNumAmmo(player, Shelly.AMMO) == 3) {
                        showAmmo(player, actionbartext, true);
                        if (getNumAmmo(player, Shelly.AMMO) == 3){
                            Shelly.getShellyNormalCooldown().put(player.getUniqueId(), System.currentTimeMillis() + Shelly.NORMAL_COOLDOWN);
                        }
                    } else if (getNumAmmo(player, Shelly.AMMO) >= 3){
                        player.getInventory().remove(Shelly.AMMO);
                        player.getInventory().setItem(8, new ItemStack(Shelly.AMMO, 3));
                    }
                    else {
                            int numAmmo = getNumAmmo(player, Shelly.AMMO);
                            if (numAmmo == 0){
                                player.getInventory().setItem(8, new ItemStack(Shelly.AMMO));
                            }else{
                                player.getInventory().addItem(new ItemStack(Shelly.AMMO));
                            }
                            player.playSound(player.getLocation(), "custom:shelly-reload", 3f, 1.0f);

                        showAmmo(player, actionbartext, false);
                        Shelly.getShellyNormalCooldown().put(player.getUniqueId(), System.currentTimeMillis() + Shelly.NORMAL_COOLDOWN);

                    }
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', actionbartext.toString())));

                }//if nothing, do not send null because default mc has things such as "can not sleep; monsters nearby for actionbar

            }
        }.runTaskTimer(MinecraftBrawlStars.getInstance() /*<-- your plugin instance*/, 0, 1L); // again, may be running faster than needed
    }
private void showAmmo(Player player, StringBuilder actionbartext, boolean showPercent){
    for (int i = 0; i< getNumAmmo(player, Shelly.AMMO); i++){
        actionbartext.append("&6█████ ");
    }
    if (showPercent){
        if (getNumAmmo(player, Shelly.AMMO) != 3){
            double fifth = Shelly.NORMAL_COOLDOWN /5000.0;
            int percentDone = 5 - (int) (Shelly.getShellyNormalCooldown(player)/fifth) - 1;
            for (int i = 0; i<percentDone; i++){
                actionbartext.append("&6█");
            }
        }
    }
    actionbartext.append("&e&l SUPER: " + (AmmoHelper.getNumSuperToken(player, Shelly.SUPER_TOKEN) < 100? AmmoHelper.getNumSuperToken(player, Shelly.SUPER_TOKEN) + "%" : "&a&lREADY!"));
}

}
