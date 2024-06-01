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
 * Handles loading, saving, and accessing configuration data.
 */
public class Config {
    private final Wardrobe plugin;
    private FileConfiguration dataConfig = null;
    private File configFile = null;

    /**
     * Constructor for the Config class.
     *
     * @param plugin the Wardrobe plugin instance
     */
    public Config(Wardrobe plugin) {
        this.plugin = plugin;
        this.saveDefaultConfig();
    }

    /**
     * Reloads the configuration file.
     */
    public void reloadConfig() {
        if (configFile == null) {
            configFile = new File(plugin.getDataFolder(), "config.yml");
        }

        dataConfig = YamlConfiguration.loadConfiguration(configFile);
        InputStream defaultStream = plugin.getResource("config.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            dataConfig.setDefaults(defaultConfig);
        }
    }

    /**
     * Gets the configuration object.
     *
     * @return the FileConfiguration object
     */
    public FileConfiguration getConfig() {
        if (dataConfig == null) {
            reloadConfig();
        }
        return dataConfig;
    }

    /**
     * Saves the configuration file.
     */
    public void saveConfig() {
        if (dataConfig != null && configFile != null) {
            try {
                getConfig().save(configFile);
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "Could not save data to " + configFile, e);
            }
        }
    }

    /**
     * Saves the default configuration file if it doesn't exist.
     */
    public void saveDefaultConfig() {
        if (dataConfig == null) {
            configFile = new File(plugin.getDataFolder(), "config.yml");
        }

        if (!configFile.exists()) {
            plugin.saveResource("config.yml", false);
        }
    }
}
