package me.plainoldmoose.wardrobe.Work;

import me.plainoldmoose.wardrobe.Wardrobe;
import org.bukkit.entity.Player;

public class DataWork {
    public static boolean ResetAllPlayerWardrobe(Player p) {
        Wardrobe.Page_1.getConfig().set(p.getUniqueId().toString(), (Object)null);
        Wardrobe.Page_2.getConfig().set(p.getUniqueId().toString(), (Object)null);
        Wardrobe.Page_1.saveConfig();
        Wardrobe.Page_2.saveConfig();
        Wardrobe.Page_1.ReloadConfig();
        Wardrobe.Page_2.ReloadConfig();
        return Wardrobe.Page_1.getConfig().getConfigurationSection(p.getUniqueId().toString()) == null && Wardrobe.Page_2.getConfig().getConfigurationSection(p.getUniqueId().toString()) == null;
    }

    public static boolean ResetPagePlayerWardrobe(Player p, String Page) {
        if (Page.equalsIgnoreCase("1")) {
            Wardrobe.Page_1.getConfig().set(p.getUniqueId().toString(), (Object)null);
            Wardrobe.Page_1.saveConfig();
            Wardrobe.Page_1.ReloadConfig();
            return Wardrobe.Page_1.getConfig().getConfigurationSection(p.getUniqueId().toString()) == null;
        } else if (Page.equalsIgnoreCase("2")) {
            Wardrobe.Page_2.getConfig().set(p.getUniqueId().toString(), (Object)null);
            Wardrobe.Page_2.saveConfig();
            Wardrobe.Page_2.ReloadConfig();
            return Wardrobe.Page_2.getConfig().getConfigurationSection(p.getUniqueId().toString()) == null;
        } else {
            return false;
        }
    }

    public static boolean ResetSlotPlayerWardrobe(Player p, String Slot) {
        String SlotPath = "Slot-" + Slot;
        if (Integer.valueOf(Slot) >= 1 && Integer.valueOf(Slot) <= 9) {
            Wardrobe.Page_1.getConfig().set(p.getUniqueId().toString() + ".name", p.getName());
            Wardrobe.Page_1.getConfig().set(p.getUniqueId().toString() + "." + SlotPath + ".Helmet", "none");
            Wardrobe.Page_1.getConfig().set(p.getUniqueId().toString() + "." + SlotPath + ".Chestplate", "none");
            Wardrobe.Page_1.getConfig().set(p.getUniqueId().toString() + "." + SlotPath + ".Leggings", "none");
            Wardrobe.Page_1.getConfig().set(p.getUniqueId().toString() + "." + SlotPath + ".Boots", "none");
            Wardrobe.Page_1.getConfig().set(p.getUniqueId().toString() + "." + SlotPath + ".Button", "Locked");
            Wardrobe.Page_1.saveConfig();
            Wardrobe.Page_1.ReloadConfig();
            return true;
        } else if (Integer.valueOf(Slot) >= 10 && Integer.valueOf(Slot) <= 18) {
            Wardrobe.Page_2.getConfig().set(p.getUniqueId().toString() + ".name", p.getName());
            Wardrobe.Page_2.getConfig().set(p.getUniqueId().toString() + "." + SlotPath + ".Helmet", "none");
            Wardrobe.Page_2.getConfig().set(p.getUniqueId().toString() + "." + SlotPath + ".Chestplate", "none");
            Wardrobe.Page_2.getConfig().set(p.getUniqueId().toString() + "." + SlotPath + ".Leggings", "none");
            Wardrobe.Page_2.getConfig().set(p.getUniqueId().toString() + "." + SlotPath + ".Boots", "none");
            Wardrobe.Page_2.getConfig().set(p.getUniqueId().toString() + "." + SlotPath + ".Button", "Locked");
            Wardrobe.Page_2.saveConfig();
            Wardrobe.Page_2.ReloadConfig();
            return true;
        } else {
            return false;
        }
    }
}