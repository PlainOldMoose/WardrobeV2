package me.plainoldmoose.wardrobe.Work;

import me.plainoldmoose.wardrobe.Wardrobe;
import org.bukkit.entity.Player;

/**
 * Utility class for performing various data-related operations for players' wardrobes.
 */
public class DataWork {

    /**
     * Resets all wardrobe data for a specific player.
     *
     * @param p The player whose wardrobe data is to be reset.
     * @return true if the wardrobe data is successfully reset, false otherwise.
     */
    public static boolean resetAllPlayerWardrobe(Player p) {
        // Remove player's wardrobe data from both pages
        Wardrobe.Page_1.getConfig().set(p.getUniqueId().toString(), null);
        Wardrobe.Page_2.getConfig().set(p.getUniqueId().toString(), null);

        // Save and reload configurations
        Wardrobe.Page_1.saveConfig();
        Wardrobe.Page_2.saveConfig();
        Wardrobe.Page_1.reloadConfig();
        Wardrobe.Page_2.reloadConfig();

        // Check if both configurations are now empty
        return Wardrobe.Page_1.getConfig().getConfigurationSection(p.getUniqueId().toString()) == null &&
                Wardrobe.Page_2.getConfig().getConfigurationSection(p.getUniqueId().toString()) == null;
    }

    /**
     * Resets the wardrobe data for a specific player on a particular page.
     *
     * @param p     The player whose wardrobe data is to be reset.
     * @param Page  The page number to reset the wardrobe data on.
     * @return true if the wardrobe data is successfully reset, false otherwise.
     */
    public static boolean resetPagePlayerWardrobe(Player p, String Page) {
        // Check which page to reset
        if (Page.equalsIgnoreCase("1")) {
            // Reset data on Page 1
            Wardrobe.Page_1.getConfig().set(p.getUniqueId().toString(), null);
            Wardrobe.Page_1.saveConfig();
            Wardrobe.Page_1.reloadConfig();
            // Check if the configuration section is now empty
            return Wardrobe.Page_1.getConfig().getConfigurationSection(p.getUniqueId().toString()) == null;
        } else if (Page.equalsIgnoreCase("2")) {
            // Reset data on Page 2
            Wardrobe.Page_2.getConfig().set(p.getUniqueId().toString(), null);
            Wardrobe.Page_2.saveConfig();
            Wardrobe.Page_2.reloadConfig();
            // Check if the configuration section is now empty
            return Wardrobe.Page_2.getConfig().getConfigurationSection(p.getUniqueId().toString()) == null;
        } else {
            return false;
        }
    }

    /**
     * Resets the wardrobe data for a specific player on a particular slot.
     *
     * @param p    The player whose wardrobe data is to be reset.
     * @param Slot The slot number to reset the wardrobe data on.
     * @return true if the wardrobe data is successfully reset, false otherwise.
     */
    public static boolean resetSlotPlayerWardrobe(Player p, String Slot) {
        String SlotPath = "Slot-" + Slot;
        // Check if the slot number is valid
        if (Integer.valueOf(Slot) >= 1 && Integer.valueOf(Slot) <= 9) {
            // Reset data on Page 1 for the specified slot
            Wardrobe.Page_1.getConfig().set(p.getUniqueId().toString() + ".name", p.getName());
            Wardrobe.Page_1.getConfig().set(p.getUniqueId().toString() + "." + SlotPath + ".Helmet", "none");
            Wardrobe.Page_1.getConfig().set(p.getUniqueId().toString() + "." + SlotPath + ".Chestplate", "none");
            Wardrobe.Page_1.getConfig().set(p.getUniqueId().toString() + "." + SlotPath + ".Leggings", "none");
            Wardrobe.Page_1.getConfig().set(p.getUniqueId().toString() + "." + SlotPath + ".Boots", "none");
            Wardrobe.Page_1.getConfig().set(p.getUniqueId().toString() + "." + SlotPath + ".Button", "Locked");
            Wardrobe.Page_1.saveConfig();
            Wardrobe.Page_1.reloadConfig();
            return true;
        } else if (Integer.valueOf(Slot) >= 10 && Integer.valueOf(Slot) <= 18) {
            // Reset data on Page 2 for the specified slot
            Wardrobe.Page_2.getConfig().set(p.getUniqueId().toString() + ".name", p.getName());
            Wardrobe.Page_2.getConfig().set(p.getUniqueId().toString() + "." + SlotPath + ".Helmet", "none");
            Wardrobe.Page_2.getConfig().set(p.getUniqueId().toString() + "." + SlotPath + ".Chestplate", "none");
            Wardrobe.Page_2.getConfig().set(p.getUniqueId().toString() + "." + SlotPath + ".Leggings", "none");
            Wardrobe.Page_2.getConfig().set(p.getUniqueId().toString() + "." + SlotPath + ".Boots", "none");
            Wardrobe.Page_2.getConfig().set(p.getUniqueId().toString() + "." + SlotPath + ".Button", "Locked");
            Wardrobe.Page_2.saveConfig();
            Wardrobe.Page_2.reloadConfig();
            return true;
        } else {
            return false;
        }
    }
}
