package me.mxtery.minecraftbrawlstars;

import me.mxtery.minecraftbrawlstars.MinecraftBrawlStars;
import org.bukkit.NamespacedKey;

public class Keys {
    public static NamespacedKey shellyNormal;
    public static NamespacedKey shellySuper;
    public static NamespacedKey shellyGadget;
    public static NamespacedKey shellyItem;

    public static NamespacedKey coltNormal;
    public static NamespacedKey coltSuper;
    public static NamespacedKey coltGadget;
    public static NamespacedKey coltItem;

    public static void init(MinecraftBrawlStars plugin){
        shellyNormal = new NamespacedKey(plugin, "shellyNormal");
        shellySuper = new NamespacedKey(plugin, "shellySuper");
        shellyGadget = new NamespacedKey(plugin, "shellyGadget");
        shellyItem = new NamespacedKey(plugin, "shellyItem");
        
        coltNormal = new NamespacedKey(plugin, "coltNormal");
        coltSuper = new NamespacedKey(plugin, "coltSuper");
        coltGadget = new NamespacedKey(plugin, "coltGadget");
        coltItem = new NamespacedKey(plugin, "coltItem");
    }
}
