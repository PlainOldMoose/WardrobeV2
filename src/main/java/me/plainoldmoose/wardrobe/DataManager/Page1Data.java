package me.plainoldmoose.wardrobe.DataManager;

import me.plainoldmoose.wardrobe.Wardrobe;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class Page1Data {
    private Wardrobe plugin;
    private FileConfiguration dataPage1 = null;
    private File Page1 = null;

    public Page1Data(Wardrobe plugin) {
        this.plugin = plugin;
        this.saveDefaultConfig();
    }

    public void ReloadConfig() {
        if (this.Page1 == null) {
            this.Page1 = new File("plugins/Wardrobe/Page1.yml");
        }

        this.dataPage1 = YamlConfiguration.loadConfiguration(this.Page1);
        InputStream defaultStream = this.plugin.getResource("Wardrobe/Page1.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultCongfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataPage1.setDefaults(defaultCongfig);
        }

    }

    public FileConfiguration getConfig() {
        if (this.dataPage1 == null) {
            this.ReloadConfig();
        }

        return this.dataPage1;
    }

    public void saveConfig() {
        if (this.dataPage1 != null && this.Page1 != null) {
            try {
                this.getConfig().save(this.Page1);
            } catch (IOException var2) {
                this.plugin.getLogger().log(Level.SEVERE, "Could not save Data to " + this.Page1, var2);
            }

        }
    }

    public void saveDefaultConfig() {
        if (this.dataPage1 == null) {
            this.Page1 = new File("plugins/Wardrobe/Page1.yml");
        }

        if (!this.Page1.exists()) {
            this.plugin.saveResource("Wardrobe/Page1.yml", false);
        }

    }
}
