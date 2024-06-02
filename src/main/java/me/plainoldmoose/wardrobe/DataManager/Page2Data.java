package me.plainoldmoose.wardrobe.DataManager;

import me.plainoldmoose.wardrobe.Wardrobe;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

/**
 * Manages the data for wardrobe page 2.
 */
public class Page2Data {
    private final Wardrobe plugin;
    private FileConfiguration dataPage2 = null;
    private File Page2 = null;

    /**
     * Constructor for the Page2Data class.
     *
     * @param plugin the Wardrobe plugin instance
     */
    public Page2Data(Wardrobe plugin) {
        this.plugin = plugin;
        this.saveDefaultConfig();
    }

    /**
     * Reloads the configuration file for page 2.
     */
    public void reloadConfig() {
        if (Page2 == null) {
            Page2 = new File("plugins/Wardrobe/Page2.yml");
        }

        dataPage2 = YamlConfiguration.loadConfiguration(Page2);
        InputStream defaultStream = plugin.getResource("Wardrobe/Page2.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            dataPage2.setDefaults(defaultConfig);
        }
    }

    /**
     * Gets the configuration object for page 2.
     *
     * @return the FileConfiguration object for page 2
     */
    public FileConfiguration getFileConfig() {
        if (dataPage2 == null) {
            reloadConfig();
        }
        return dataPage2;
    }

    public File getConfig() {
        return this.Page2;
    }

    /**
     * Saves the configuration file for page 2.
     */
    public void saveConfig() {
        if (dataPage2 != null && Page2 != null) {
            try {
                getFileConfig().save(Page2);
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "Could not save Data to " + Page2, e);
            }
        }
    }

    /**
     * Saves the default configuration file for page 2 if it doesn't exist.
     */
    public void saveDefaultConfig() {
        if (dataPage2 == null) {
            Page2 = new File("plugins/Wardrobe/Page2.yml");
        }

        if (!Page2.exists()) {
            plugin.saveResource("Wardrobe/Page2.yml", false);
        }
    }
}
