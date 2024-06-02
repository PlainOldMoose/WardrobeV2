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
 * Manages the data for wardrobe page 1.
 */
public class Page1Data {
    private final Wardrobe plugin;
    private FileConfiguration dataPage1 = null;
    private File Page1 = null;

    /**
     * Constructor for the Page1Data class.
     *
     * @param plugin the Wardrobe plugin instance
     */
    public Page1Data(Wardrobe plugin) {
        this.plugin = plugin;
        this.saveDefaultConfig();
    }

    /**
     * Reloads the configuration file for page 1.
     */
    public void reloadConfig() {
        if (Page1 == null) {
            Page1 = new File("plugins/Wardrobe/Page1.yml");
        }

        dataPage1 = YamlConfiguration.loadConfiguration(Page1);
        InputStream defaultStream = plugin.getResource("Wardrobe/Page1.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            dataPage1.setDefaults(defaultConfig);
        }
    }

    /**
     * Gets the configuration object for page 1.
     *
     * @return the FileConfiguration object for page 1
     */
    public FileConfiguration getFileConfig() {
        if (dataPage1 == null) {
            reloadConfig();
        }
        return dataPage1;
    }

    public File getConfig() {
        return this.Page1;
    }

    /**
     * Saves the configuration file for page 1.
     */
    public void saveConfig() {
        if (dataPage1 != null && Page1 != null) {
            try {
                getFileConfig().save(Page1);
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "Could not save Data to " + Page1, e);
            }
        }
    }

    /**
     * Saves the default configuration file for page 1 if it doesn't exist.
     */
    public void saveDefaultConfig() {
        if (dataPage1 == null) {
            Page1 = new File("plugins/Wardrobe/Page1.yml");
        }

        if (!Page1.exists()) {
            plugin.saveResource("Wardrobe/Page1.yml", false);
        }
    }
}
