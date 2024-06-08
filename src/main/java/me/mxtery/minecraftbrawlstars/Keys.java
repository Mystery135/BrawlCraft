package me.mxtery.minecraftbrawlstars;

import me.mxtery.minecraftbrawlstars.MinecraftBrawlStars;
import org.bukkit.NamespacedKey;

public class Keys {
    public static NamespacedKey shellyNormal;
    public static NamespacedKey shellySuper;
    public static NamespacedKey shellyGadget;
    public static NamespacedKey shellyItem;

    public static void init(MinecraftBrawlStars plugin){
        shellyNormal = new NamespacedKey(plugin, "shellyNormal");
        shellySuper = new NamespacedKey(plugin, "shellySuper");
        shellyGadget = new NamespacedKey(plugin, "shellyGadget");
        shellyItem = new NamespacedKey(plugin, "shellyItem");
    }
}
