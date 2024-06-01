package me.plainoldmoose.wardrobe.DataManager;

import me.plainoldmoose.wardrobe.Wardrobe;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class Config {
    private Wardrobe plugin;
    private FileConfiguration dataConfig = null;
    private File configFile = null;

    public Config(Wardrobe plugin) {
        this.plugin = plugin;
        this.saveDefaultConfig();
    }

    public void ReloadConfig() {
        if (this.configFile == null) {
            this.configFile = new File(this.plugin.getDataFolder(), "config.yml");
        }

        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);
        InputStream defaultStream = this.plugin.getResource("config.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultCongfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultCongfig);
        }

    }

    public FileConfiguration getConfig() {
        if (this.dataConfig == null) {
            this.ReloadConfig();
        }

        return this.dataConfig;
    }

    public void saveConfig() {
        if (this.dataConfig != null && this.configFile != null) {
            try {
                this.getConfig().save(this.configFile);
            } catch (IOException var2) {
                this.plugin.getLogger().log(Level.SEVERE, "Could not save Data to " + this.configFile, var2);
            }

        }
    }

    public void saveDefaultConfig() {
        if (this.dataConfig == null) {
            this.configFile = new File(this.plugin.getDataFolder(), "config.yml");
        }

        if (!this.configFile.exists()) {
            this.plugin.saveResource("config.yml", false);
        }

    }
}