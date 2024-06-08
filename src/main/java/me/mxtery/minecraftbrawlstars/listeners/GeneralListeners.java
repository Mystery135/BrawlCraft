package me.mxtery.minecraftbrawlstars.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;

public class GeneralListeners implements Listener {
  @EventHandler
    public void onPlayerPickup(EntityPickupItemEvent event){
        if (event.getEntity() instanceof Player){
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerPickupArrow(PlayerPickupArrowEvent event){
            event.setCancelled(true);
    }
    @EventHandler
    public void onPlayerChangeInv(InventoryClickEvent event){
        event.setCancelled(true);
    }
    @EventHandler
    public void onPlayerChangeSlot(PlayerItemHeldEvent event){
        if (event.getNewSlot() == 0 || event.getNewSlot() == 1 || event.getNewSlot() == 2){
            return;
        }event.setCancelled(true);
    }
@EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
      event.getPlayer().setResourcePack("https://www.dropbox.com/scl/fi/2mzgt3um21yujh718qagm/MCBS.zip?rlkey=42i9xstyxyh3g3f62sbzlkdjv&st=m5prd3ul&dl=1");

}
@EventHandler
    public void onResourceStatus(PlayerResourcePackStatusEvent e){
      if (e.getStatus() != PlayerResourcePackStatusEvent.Status.ACCEPTED && e.getStatus() != PlayerResourcePackStatusEvent.Status.DOWNLOADED
      && e.getStatus() != PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED
      ){
          e.getPlayer().kickPlayer("The resource pack failed to load!" + e.getStatus());

      }
}
@EventHandler
    public void onHungerLose(FoodLevelChangeEvent event){
      event.getEntity().setSaturation(0);
      event.setCancelled(true);
}

}
