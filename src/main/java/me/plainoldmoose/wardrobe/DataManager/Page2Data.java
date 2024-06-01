package me.plainoldmoose.wardrobe.DataManager;

import me.plainoldmoose.wardrobe.Wardrobe;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class Page2Data {
    private Wardrobe plugin;
    private FileConfiguration dataPage2 = null;
    private File Page2 = null;

    public Page2Data(Wardrobe plugin) {
        this.plugin = plugin;
        this.saveDefaultConfig();
    }

    public void ReloadConfig() {
        if (this.Page2 == null) {
            this.Page2 = new File("plugins/Wardrobe/Page2.yml");
        }

        this.dataPage2 = YamlConfiguration.loadConfiguration(this.Page2);
        InputStream defaultStream = this.plugin.getResource("Wardrobe/Page2.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultCongfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataPage2.setDefaults(defaultCongfig);
        }

    }

    public FileConfiguration getConfig() {
        if (this.dataPage2 == null) {
            this.ReloadConfig();
        }

        return this.dataPage2;
    }

    public void saveConfig() {
        if (this.dataPage2 != null && this.Page2 != null) {
            try {
                this.getConfig().save(this.Page2);
            } catch (IOException var2) {
                this.plugin.getLogger().log(Level.SEVERE, "Could not save Data to " + this.Page2, var2);
            }

        }
    }

    public void saveDefaultConfig() {
        if (this.dataPage2 == null) {
            this.Page2 = new File("plugins/Wardrobe/Page2.yml");
        }

        if (!this.Page2.exists()) {
            this.plugin.saveResource("Wardrobe/Page2.yml", false);
        }

    }
}