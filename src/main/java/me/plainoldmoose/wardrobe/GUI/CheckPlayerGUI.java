package me.plainoldmoose.wardrobe.GUI;

import me.plainoldmoose.wardrobe.Wardrobe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Iterator;

public class CheckPlayerGUI {
    public static String Ver = Bukkit.getServer().getVersion();
    public static String PlayerName = "";
    public static String Path = "";
    public static String CheckPage1Name = "";
    public static String CheckPage2Name = "";
    public static Inventory CheckPage1;
    public static Inventory CheckPage2;
    public static boolean onOpen = false;
    public static Player CheckPlayerMain = null;

    public static boolean CheckName(String PlayerCurrentName) {
        PlayerName = PlayerCurrentName;
        Iterator var1 = Wardrobe.Page_1.getConfig().getConfigurationSection("").getKeys(false).iterator();

        String path;
        do {
            if (!var1.hasNext()) {
                return false;
            }

            path = (String)var1.next();
        } while(!Wardrobe.Page_1.getConfig().getString(path + ".name").contains(PlayerCurrentName));

        Path = path;
        return true;
    }

    public static void CheckGUI1(Player p) {
        Player CheckPlayer = Bukkit.getPlayer(Path);
        if (CheckPlayer == null) {
            Iterator var2 = Bukkit.getOnlinePlayers().iterator();

            while(var2.hasNext()) {
                Player CheckPlayer2 = (Player)var2.next();
                if (CheckPlayer2.getUniqueId().toString().contains(Path)) {
                    CheckPlayer = CheckPlayer2;
                    CheckPlayerMain = CheckPlayer2;
                    break;
                }
            }
        }

        if (CheckPlayer.getOpenInventory() != null && (CheckPlayer.getOpenInventory().getTitle().equals(WardrobeGUI.Page1Name) || CheckPlayer.getOpenInventory().getTitle().equals(WardrobeGUI.Page2Name))) {
            p.getInventory().addItem(new ItemStack[]{p.getItemOnCursor()});
            p.setItemOnCursor((ItemStack)null);
            CheckPlayer.closeInventory();
        }

        onOpen = true;
        String Name = PlayerName + "'s Wardrobe (1/2)";
        WardrobeGUI.Page1Name = Name;
        Inventory CheckWardrobePage1 = Bukkit.createInventory(p, 54, Name);
        CheckPage1Name = Name;
        ItemStack Background = new ItemStack(Material.DIRT);
        ItemMeta BackgroundMeta = Background.getItemMeta();
        String Mat;
        if (!Ver.contains("1.8") && !Ver.contains("1.9") && !Ver.contains("1.10") && !Ver.contains("1.11") && !Ver.contains("1.12")) {
            if (Ver.contains("1.13") || Ver.contains("1.14") || Ver.contains("1.15") || Ver.contains("1.16") || Ver.contains("1.17") || Ver.contains("1.18") || Ver.contains("1.19") || Ver.contains("1.20")) {
                Mat = "BLACK_STAINED_GLASS_PANE";
                Background.setType(Material.valueOf(Mat));
            }
        } else {
            Mat = "STAINED_GLASS_PANE";
            Background.setType(Material.valueOf(Mat));
            Background.setDurability((short)15);
        }

        BackgroundMeta.setDisplayName(" ");
        Background.setItemMeta(BackgroundMeta);

        for(int i = 45; i <= 53; ++i) {
            CheckWardrobePage1.setItem(i, Background);
        }

        WardrobeGUI.Page1 = CheckWardrobePage1;
        WardrobeGUI.CreateBaseBackground(CheckWardrobePage1);
        WardrobeGUI.CreateAvaiableSlotBackground(CheckWardrobePage1, Name, CheckPlayer);
        p.openInventory(CheckWardrobePage1);
    }

    public static void CheckGUI2(Player p) {
        Player CheckPlayer = Bukkit.getPlayer(Path);
        if (CheckPlayer == null) {
            Iterator var2 = Bukkit.getOnlinePlayers().iterator();

            while(var2.hasNext()) {
                Player CheckPlayer2 = (Player)var2.next();
                if (CheckPlayer2.getUniqueId().toString().contains(Path)) {
                    CheckPlayer = CheckPlayer2;
                    CheckPlayerMain = CheckPlayer2;
                    break;
                }
            }
        }

        if (CheckPlayer.getOpenInventory() != null && (CheckPlayer.getOpenInventory().getTitle().equals(WardrobeGUI.Page1Name) || CheckPlayer.getOpenInventory().getTitle().equals(WardrobeGUI.Page2Name))) {
            p.getInventory().addItem(new ItemStack[]{p.getItemOnCursor()});
            p.setItemOnCursor((ItemStack)null);
            CheckPlayer.closeInventory();
        }

        onOpen = true;
        String Name = PlayerName + "'s Wardrobe (2/2)";
        WardrobeGUI.Page1Name = Name;
        Inventory CheckWardrobePage2 = Bukkit.createInventory(p, 54, Name);
        CheckPage2Name = Name;
        ItemStack Background = new ItemStack(Material.DIRT);
        ItemMeta BackgroundMeta = Background.getItemMeta();
        String Mat;
        if (!Ver.contains("1.8") && !Ver.contains("1.9") && !Ver.contains("1.10") && !Ver.contains("1.11") && !Ver.contains("1.12")) {
            if (Ver.contains("1.13") || Ver.contains("1.14") || Ver.contains("1.15") || Ver.contains("1.16") || Ver.contains("1.17") || Ver.contains("1.18") || Ver.contains("1.19") || Ver.contains("1.20")) {
                Mat = "BLACK_STAINED_GLASS_PANE";
                Background.setType(Material.valueOf(Mat));
            }
        } else {
            Mat = "STAINED_GLASS_PANE";
            Background.setType(Material.valueOf(Mat));
            Background.setDurability((short)15);
        }

        BackgroundMeta.setDisplayName(" ");
        Background.setItemMeta(BackgroundMeta);

        for(int i = 45; i <= 53; ++i) {
            CheckWardrobePage2.setItem(i, Background);
        }

        WardrobeGUI.Page2 = CheckWardrobePage2;
        WardrobeGUI.CreateBaseBackground(CheckWardrobePage2);
        WardrobeGUI.CreateAvaiableSlotBackground(CheckWardrobePage2, Name, CheckPlayer);
        p.openInventory(CheckWardrobePage2);
    }
}
