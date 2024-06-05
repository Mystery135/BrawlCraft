package me.mxtery.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

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
}
