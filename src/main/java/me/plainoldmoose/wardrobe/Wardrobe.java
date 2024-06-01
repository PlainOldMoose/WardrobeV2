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

public class Wardrobe extends JavaPlugin {
    public static Plugin plugin;
    public static Config ConfigData;
    public static Page1Data Page_1;
    public static Page2Data Page_2;

    public void onEnable() {
        DataCreator.CreatePage1();
        DataCreator.CreatePage2();
        plugin = this;
        ConfigData = new Config(this);
        ConfigData.saveDefaultConfig();
        ConfigData.ReloadConfig();
        Page_1 = new Page1Data(this);
        Page_1.saveDefaultConfig();
        Page_2 = new Page2Data(this);
        Page_2.saveDefaultConfig();
        new WardrobeCommand(this);
        this.getCommand("wardrobe").setTabCompleter(new WardrobeTabCompleter());
        new WardrobeListener(this);
        new CheckPlayerGUIListener(this);
    }

    public void onDisable() {
        Iterator var1 = Bukkit.getOnlinePlayers().iterator();

        while(true) {
            Player p;
            do {
                do {
                    if (!var1.hasNext()) {
                        return;
                    }

                    p = (Player)var1.next();
                } while(p.getOpenInventory() == null);
            } while(!p.getOpenInventory().getTitle().equals(WardrobeGUI.Page1Name) && !p.getOpenInventory().getTitle().equals(WardrobeGUI.Page2Name) && !p.getOpenInventory().getTitle().contains("'s Wardrobe (1/2)") && !p.getOpenInventory().getTitle().contains("'s Wardrobe (2/2)"));

            if (p.getItemOnCursor() != null) {
                p.getInventory().addItem(new ItemStack[]{p.getItemOnCursor()});
                p.setItemOnCursor((ItemStack)null);
            }

            p.closeInventory();
        }
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}