package me.plainoldmoose.wardrobe;

import me.plainoldmoose.wardrobe.Command.WardrobeCommand;
import me.plainoldmoose.wardrobe.Command.WardrobeTabCompleter;
import me.plainoldmoose.wardrobe.DataManager.Config;
import me.plainoldmoose.wardrobe.DataManager.DataCreator;
import me.plainoldmoose.wardrobe.DataManager.Page1Data;
import me.plainoldmoose.wardrobe.DataManager.Page2Data;
import me.plainoldmoose.wardrobe.GUI.WardrobeGUI;
import me.plainoldmoose.wardrobe.Listener.CheckPlayerGUIListener;
import me.plainoldmoose.wardrobe.Listener.WardrobeListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Iterator;

/**
 * Main class for the Wardrobe plugin.
 */
public class Wardrobe extends JavaPlugin {
    // Static fields to hold plugin instance, configuration data, and page data
    public static Plugin plugin;
    public static Config ConfigData;
    public static Page1Data Page_1;
    public static Page2Data Page_2;

    /**
     * Called when the plugin is enabled.
     */
    @Override
    public void onEnable() {
        // Initialize page data
        DataCreator.CreatePage1();
        DataCreator.CreatePage2();

        // Set plugin instance
        plugin = this;

        // Initialize configuration data
        ConfigData = new Config(this);
        ConfigData.saveDefaultConfig();
        ConfigData.ReloadConfig();

        // Initialize page 1 data
        Page_1 = new Page1Data(this);
        Page_1.saveDefaultConfig();

        // Initialize page 2 data
        Page_2 = new Page2Data(this);
        Page_2.saveDefaultConfig();

        // Register commands
        new WardrobeCommand(this);

        // Set tab completer for wardrobe command
        this.getCommand("wardrobe").setTabCompleter(new WardrobeTabCompleter());

        // Register listeners
        new WardrobeListener(this);
        new CheckPlayerGUIListener(this);
    }

    /**
     * Called when the plugin is disabled.
     */
    @Override
    public void onDisable() {
        // Iterate through online players
        Iterator var1 = Bukkit.getOnlinePlayers().iterator();
        while (var1.hasNext()) {
            Player p = (Player) var1.next();

            // Check if player has an open inventory related to wardrobe
            if (p.getOpenInventory() != null && (
                    p.getOpenInventory().getTitle().equals(WardrobeGUI.Page1Name) ||
                            p.getOpenInventory().getTitle().equals(WardrobeGUI.Page2Name) ||
                            p.getOpenInventory().getTitle().contains("Wardrobe (1/2)") ||
                            p.getOpenInventory().getTitle().contains("Wardrobe (2/2)"))) {

                // If player has an item on cursor, return it to inventory
                if (p.getItemOnCursor() != null) {
                    p.getInventory().addItem(new ItemStack[]{p.getItemOnCursor()});
                    p.setItemOnCursor((ItemStack) null);
                }

                // Close player's inventory
                p.closeInventory();
            }
        }
    }

    /**
     * Gets the plugin instance.
     *
     * @return The plugin instance.
     */
    public static Plugin getPlugin() {
        return plugin;
    }
}
