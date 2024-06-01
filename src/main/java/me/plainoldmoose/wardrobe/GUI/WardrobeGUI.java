package me.plainoldmoose.wardrobe.GUI;


import me.plainoldmoose.wardrobe.Wardrobe;
import me.plainoldmoose.wardrobe.Work.GUIWork;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WardrobeGUI {
    public static String Ver = Bukkit.getServer().getVersion();
    public static Inventory Page1;
    public static Inventory Page2;
    public static String Page1Name = "";
    public static String Page2Name = "";

    public static void createWardrobePage1(Player p) {
        FileConfiguration var10001 = Wardrobe.ConfigData.getConfig();
        String Name = ChatColor.translateAlternateColorCodes('&', var10001.getString("Title")) + " (1/2)";
        Inventory WardrobePage1 = Bukkit.createInventory(p, 54, Name);
        Page1Name = Name;
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

        int i;
        for(i = 45; i <= 53; ++i) {
            WardrobePage1.setItem(i, Background);
        }

        createGoBackAndCloseButton(WardrobePage1);
        Page1 = WardrobePage1;
        createNextAndPreviousButton(WardrobePage1);
        createBaseBackground(WardrobePage1);
        createAvailableSlotBackground(WardrobePage1, Name, p);

        String Path;
        for(i = 36; i <= 44; ++i) {
            Path = "";
            if (Ver.contains("1.8") || Ver.contains("1.9") || Ver.contains("1.10") || Ver.contains("1.11") || Ver.contains("1.12")) {
                Path = WardrobePage1.getItem(i).getData().toString();
            }

            if (Ver.contains("1.13") || Ver.contains("1.14") || Ver.contains("1.15") || Ver.contains("1.16") || Ver.contains("1.17") || Ver.contains("1.18") || Ver.contains("1.19") || Ver.contains("1.20")) {
                Path = WardrobePage1.getItem(i).getType().toString();
            }

            if (!Path.contains("PINK_DYE") && !Path.contains("PINK DYE")) {
                if (Path.contains("LIME_DYE") || Path.contains("LIME DYE")) {
                    GUIWork.CheckArmor(p, WardrobePage1, i, Name);
                }
            } else {
                String CheckSlot1 = WardrobePage1.getItem(i - 36).getType().toString();
                String CheckSlot2 = WardrobePage1.getItem(i - 27).getType().toString();
                String CheckSlot3 = WardrobePage1.getItem(i - 18).getType().toString();
                String CheckSlot4 = WardrobePage1.getItem(i - 9).getType().toString();
                if (CheckSlot1.contains("STAINED_GLASS_PANE") && CheckSlot2.contains("STAINED_GLASS_PANE") && CheckSlot3.contains("STAINED_GLASS_PANE") && CheckSlot4.contains("STAINED_GLASS_PANE")) {
                    WardrobePage1.setItem(i, createEmptyButton(i - 36, WardrobePage1, Name));
                }
            }
        }

        if (Wardrobe.Page_2.getConfig().getConfigurationSection(p.getUniqueId().toString()) != null) {
            Iterator var12 = Wardrobe.Page_2.getConfig().getConfigurationSection(p.getUniqueId().toString()).getKeys(false).iterator();

            while(var12.hasNext()) {
                Path = (String)var12.next();
                if (!Path.contains("name")) {
                    FileConfiguration var10000 = Wardrobe.Page_2.getConfig();
                    String var17 = p.getUniqueId().toString();
                    if (var10000.getString(var17 + "." + Path + ".Button").contains("Equipped")) {
                        ItemStack Helmet = p.getInventory().getHelmet();
                        ItemStack Chestplate = p.getInventory().getChestplate();
                        ItemStack Leggings = p.getInventory().getLeggings();
                        ItemStack Boots = p.getInventory().getBoots();
                        if (Helmet != null) {
                            Wardrobe.Page_2.getConfig().set(p.getUniqueId().toString() + "." + Path + ".Helmet", Helmet);
                        } else {
                            Wardrobe.Page_2.getConfig().set(p.getUniqueId().toString() + "." + Path + ".Helmet", "none");
                        }

                        if (Chestplate != null) {
                            Wardrobe.Page_2.getConfig().set(p.getUniqueId().toString() + "." + Path + ".Chestplate", Chestplate);
                        } else {
                            Wardrobe.Page_2.getConfig().set(p.getUniqueId().toString() + "." + Path + ".Chestplate", "none");
                        }

                        if (Leggings != null) {
                            Wardrobe.Page_2.getConfig().set(p.getUniqueId().toString() + "." + Path + ".Leggings", Leggings);
                        } else {
                            Wardrobe.Page_2.getConfig().set(p.getUniqueId().toString() + "." + Path + ".Leggings", "none");
                        }

                        if (Boots != null) {
                            Wardrobe.Page_2.getConfig().set(p.getUniqueId().toString() + "." + Path + ".Boots", Boots);
                        } else {
                            Wardrobe.Page_2.getConfig().set(p.getUniqueId().toString() + "." + Path + ".Boots", "none");
                        }

                        Wardrobe.Page_2.saveConfig();
                        Wardrobe.Page_2.reloadConfig();
                    }
                }
            }
        }

        p.openInventory(WardrobePage1);
    }

    public static void createWardrobePage2(Player p) {
        FileConfiguration var10001 = Wardrobe.ConfigData.getConfig();
        String Name = ChatColor.translateAlternateColorCodes('&', var10001.getString("Title")) + " (2/2)";
        Inventory WardrobePage2 = Bukkit.createInventory(p, 54, Name);
        Page2Name = Name;
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

        int i;
        for(i = 45; i <= 53; ++i) {
            WardrobePage2.setItem(i, Background);
        }

        createGoBackAndCloseButton(WardrobePage2);
        Page2 = WardrobePage2;
        createNextAndPreviousButton(WardrobePage2);
        createBaseBackground(WardrobePage2);
        createAvailableSlotBackground(WardrobePage2, Name, p);

        for(i = 36; i <= 44; ++i) {
            String ButtonCheck = "";
            if (Ver.contains("1.8") || Ver.contains("1.9") || Ver.contains("1.10") || Ver.contains("1.11") || Ver.contains("1.12")) {
                ButtonCheck = WardrobePage2.getItem(i).getData().toString();
            }

            if (Ver.contains("1.13") || Ver.contains("1.14") || Ver.contains("1.15") || Ver.contains("1.16") || Ver.contains("1.17") || Ver.contains("1.18") || Ver.contains("1.19") || Ver.contains("1.20")) {
                ButtonCheck = WardrobePage2.getItem(i).getType().toString();
            }

            if (!ButtonCheck.contains("PINK_DYE") && !ButtonCheck.contains("PINK DYE")) {
                if (ButtonCheck.contains("LIME_DYE") || ButtonCheck.contains("LIME DYE")) {
                    GUIWork.CheckArmor(p, WardrobePage2, i, Name);
                }
            } else {
                String CheckSlot1 = WardrobePage2.getItem(i - 36).getType().toString();
                String CheckSlot2 = WardrobePage2.getItem(i - 27).getType().toString();
                String CheckSlot3 = WardrobePage2.getItem(i - 18).getType().toString();
                String CheckSlot4 = WardrobePage2.getItem(i - 9).getType().toString();
                if (CheckSlot1.contains("STAINED_GLASS_PANE") && CheckSlot2.contains("STAINED_GLASS_PANE") && CheckSlot3.contains("STAINED_GLASS_PANE") && CheckSlot4.contains("STAINED_GLASS_PANE")) {
                    WardrobePage2.setItem(i, createEmptyButton(i - 36, WardrobePage2, Name));
                }
            }
        }

        p.openInventory(WardrobePage2);
    }

    /**
     * Creates a base background for the inventory.
     *
     * @param inv The inventory to create the background for.
     */
    public static void createBaseBackground(Inventory inv) {
        // Declare variables for background and button items
        ItemStack background;
        ItemMeta backgroundMeta;
        String mat;
        ItemStack button;
        ItemMeta buttonMeta;
        String name;
        ArrayList<String> buttonLore;
        String lore;
        ArrayList<String> backgroundLore;

        // Check if the inventory is for Page1
        if (inv == Page1) {
            // Set background material based on server version
            background = new ItemStack(Material.DIRT);
            backgroundMeta = background.getItemMeta();

            // Check server version and set appropriate background material
            if (!Ver.contains("1.8") && !Ver.contains("1.9") && !Ver.contains("1.10") && !Ver.contains("1.11") && !Ver.contains("1.12")) {
                // For newer versions, use BLACK_STAINED_GLASS_PANE
                mat = "BLACK_STAINED_GLASS_PANE";
                background.setType(Material.valueOf(mat));
            } else {
                // For older versions, use STAINED_GLASS_PANE with durability
                mat = "STAINED_GLASS_PANE";
                background.setType(Material.valueOf(mat));
                background.setDurability((short) 15);
            }

            // Loop through inventory slots to set display name and lore
            for (int i = 0; i <= 44; ++i) {
                if (i >= 0 && i <= 8) {
                    // Helmet slots
                    name = Wardrobe.ConfigData.getConfig().getString("Locked-Slot.Helmet-Slot.Name").replace("%Slot%", Integer.toString(i + 1));
                    backgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                    backgroundLore = new ArrayList<>();
                    for (String slotLore : Wardrobe.ConfigData.getConfig().getStringList("Locked-Slot.Helmet-Slot.Lore")) {
                        lore = slotLore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-" + (i + 1) + ".Require-Prefix"));
                        backgroundLore.add(ChatColor.translateAlternateColorCodes('&', lore));
                    }
                    backgroundMeta.setLore(backgroundLore);
                    background.setItemMeta(backgroundMeta);
                    inv.setItem(i, background);
                } else if (i >= 9 && i <= 17) {
                    // Chestplate slots
                    name = Wardrobe.ConfigData.getConfig().getString("Locked-Slot.Chestplate-Slot.Name").replace("%Slot%", Integer.toString(i - 8));
                    backgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                    backgroundLore = new ArrayList<>();
                    for (String slotLore : Wardrobe.ConfigData.getConfig().getStringList("Locked-Slot.Chestplate-Slot.Lore")) {
                        lore = slotLore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-" + (i - 8) + ".Require-Prefix"));
                        backgroundLore.add(ChatColor.translateAlternateColorCodes('&', lore));
                    }
                    backgroundMeta.setLore(backgroundLore);
                    background.setItemMeta(backgroundMeta);
                    inv.setItem(i, background);
                } else if (i >= 18 && i <= 26) {
                    // Leggings slots
                    name = Wardrobe.ConfigData.getConfig().getString("Locked-Slot.Leggings-Slot.Name").replace("%Slot%", Integer.toString(i - 17));
                    backgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                    backgroundLore = new ArrayList<>();
                    for (String slotLore : Wardrobe.ConfigData.getConfig().getStringList("Locked-Slot.Leggings-Slot.Lore")) {
                        lore = slotLore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-" + (i - 17) + ".Require-Prefix"));
                        backgroundLore.add(ChatColor.translateAlternateColorCodes('&', lore));
                    }
                    backgroundMeta.setLore(backgroundLore);
                    background.setItemMeta(backgroundMeta);
                    inv.setItem(i, background);
                } else if (i >= 27 && i <= 35) {
                    // Boots slots
                    name = Wardrobe.ConfigData.getConfig().getString("Locked-Slot.Boots-Slot.Name").replace("%Slot%", Integer.toString(i - 26));
                    backgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                    backgroundLore = new ArrayList<>();
                    for (String slotLore : Wardrobe.ConfigData.getConfig().getStringList("Locked-Slot.Boots-Slot.Lore")) {
                        lore = slotLore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-" + (i - 26) + ".Require-Prefix"));
                        backgroundLore.add(ChatColor.translateAlternateColorCodes('&', lore));
                    }
                    backgroundMeta.setLore(backgroundLore);
                    background.setItemMeta(backgroundMeta);
                    inv.setItem(i, background);
                } else if (i >= 36 && i <= 44) {
                    // Locked button slots
                    button = new ItemStack(Material.DIRT);
                    buttonMeta = button.getItemMeta();

                    // Set button type based on server version
                    if (Ver.contains("1.8") || Ver.contains("1.9") || Ver.contains("1.10") || Ver.contains("1.11") || Ver.contains("1.12")) {
                        name = "INK_SACK";
                        button.setType(Material.valueOf(name));
                        button.setDurability((short) 1);
                    } else if (Ver.contains("1.13")) {
                        name = "ROSE_RED";
                        button.setType(Material.valueOf(name));
                    } else {
                        name = "RED_DYE";
                        button.setType(Material.valueOf(name));
                    }

                    // Set button display name and lore
                    name = Wardrobe.ConfigData.getConfig().getString("Locked-Slot.Locked-Button.Name").replace("%Slot%", Integer.toString(i - 26));
                    buttonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                    buttonLore = new ArrayList<>();
                    for (String slotLore : Wardrobe.ConfigData.getConfig().getStringList("Locked-Slot.Locked-Button.Lore")) {
                        lore = slotLore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-" + (i - 26) + ".Require-Prefix"));
                        buttonLore.add(ChatColor.translateAlternateColorCodes('&', lore));
                    }
                    buttonMeta.setLore(buttonLore);
                    button.setItemMeta(buttonMeta);
                    inv.setItem(i, button);
                }
            }
        } else if (inv == Page2) {
            // Same logic as Page1 but different slot numbers

            // Set background material based on server version
            background = new ItemStack(Material.DIRT);
            backgroundMeta = background.getItemMeta();
            if (!Ver.contains("1.8") && !Ver.contains("1.9") && !Ver.contains("1.10") && !Ver.contains("1.11") && !Ver.contains("1.12")) {
                // For newer versions, use BLACK_STAINED_GLASS_PANE
                mat = "BLACK_STAINED_GLASS_PANE";
                background.setType(Material.valueOf(mat));
            } else {
                // For older versions, use STAINED_GLASS_PANE with durability
                mat = "STAINED_GLASS_PANE";
                background.setType(Material.valueOf(mat));
                background.setDurability((short) 15);
            }

            // Loop through inventory slots to set display name and lore
            for (int i = 0; i <= 44; ++i) {
                if (i >= 0 && i <= 8) {
                    // Helmet slots
                    name = Wardrobe.ConfigData.getConfig().getString("Locked-Slot.Helmet-Slot.Name").replace("%Slot%", Integer.toString(i + 10));
                    backgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                    backgroundLore = new ArrayList<>();
                    for (String slotLore : Wardrobe.ConfigData.getConfig().getStringList("Locked-Slot.Helmet-Slot.Lore")) {
                        lore = slotLore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-" + (i + 10) + ".Require-Prefix"));
                        backgroundLore.add(ChatColor.translateAlternateColorCodes('&', lore));
                    }
                    backgroundMeta.setLore(backgroundLore);
                    background.setItemMeta(backgroundMeta);
                    inv.setItem(i, background);
                } else if (i >= 9 && i <= 17) {
                    // Chestplate slots
                    name = Wardrobe.ConfigData.getConfig().getString("Locked-Slot.Chestplate-Slot.Name").replace("%Slot%", Integer.toString(i + 1));
                    backgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                    backgroundLore = new ArrayList<>();
                    for (String slotLore : Wardrobe.ConfigData.getConfig().getStringList("Locked-Slot.Chestplate-Slot.Lore")) {
                        lore = slotLore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-" + (i + 1) + ".Require-Prefix"));
                        backgroundLore.add(ChatColor.translateAlternateColorCodes('&', lore));
                    }
                    backgroundMeta.setLore(backgroundLore);
                    background.setItemMeta(backgroundMeta);
                    inv.setItem(i, background);
                } else if (i >= 18 && i <= 26) {
                    // Leggings slots
                    name = Wardrobe.ConfigData.getConfig().getString("Locked-Slot.Leggings-Slot.Name").replace("%Slot%", Integer.toString(i - 8));
                    backgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                    backgroundLore = new ArrayList<>();
                    for (String slotLore : Wardrobe.ConfigData.getConfig().getStringList("Locked-Slot.Leggings-Slot.Lore")) {
                        lore = slotLore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-" + (i - 8) + ".Require-Prefix"));
                        backgroundLore.add(ChatColor.translateAlternateColorCodes('&', lore));
                    }
                    backgroundMeta.setLore(backgroundLore);
                    background.setItemMeta(backgroundMeta);
                    inv.setItem(i, background);
                } else if (i >= 27 && i <= 35) {
                    // Boots slots
                    name = Wardrobe.ConfigData.getConfig().getString("Locked-Slot.Boots-Slot.Name").replace("%Slot%", Integer.toString(i - 17));
                    backgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                    backgroundLore = new ArrayList<>();
                    for (String slotLore : Wardrobe.ConfigData.getConfig().getStringList("Locked-Slot.Boots-Slot.Lore")) {
                        lore = slotLore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-" + (i - 17) + ".Require-Prefix"));
                        backgroundLore.add(ChatColor.translateAlternateColorCodes('&', lore));
                    }
                    backgroundMeta.setLore(backgroundLore);
                    background.setItemMeta(backgroundMeta);
                    inv.setItem(i, background);
                } else if (i >= 36 && i <= 44) {
                    // Locked button slots
                    button = new ItemStack(Material.DIRT);
                    buttonMeta = button.getItemMeta();

                    // Set button type based on server version
                    if (Ver.contains("1.8") || Ver.contains("1.9") || Ver.contains("1.10") || Ver.contains("1.11") || Ver.contains("1.12")) {
                        name = "INK_SACK";
                        button.setType(Material.valueOf(name));
                        button.setDurability((short) 1);
                    } else if (Ver.contains("1.13")) {
                        name = "ROSE_RED";
                        button.setType(Material.valueOf(name));
                    } else {
                        name = "RED_DYE";
                        button.setType(Material.valueOf(name));
                    }

                    // Set button display name and lore
                    name = Wardrobe.ConfigData.getConfig().getString("Locked-Slot.Locked-Button.Name").replace("%Slot%", Integer.toString(i - 26));
                    buttonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                    buttonLore = new ArrayList<>();
                    for (String slotLore : Wardrobe.ConfigData.getConfig().getStringList("Locked-Slot.Locked-Button.Lore")) {
                        lore = slotLore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-" + (i - 26) + ".Require-Prefix"));
                        buttonLore.add(ChatColor.translateAlternateColorCodes('&', lore));
                    }
                    buttonMeta.setLore(buttonLore);
                    button.setItemMeta(buttonMeta);
                    inv.setItem(i, button);
                }
            }
        }
    }

    public static void createAvailableSlotBackground(Inventory inv, String Name, Player p) {
        FileConfiguration var10000;
        String var10001;
        FileConfiguration var10002;
        int i;
        String var10003;
        int CheckAmount;
        int var7;
        if (inv == Page1) {
            for(i = 0; i <= 8; ++i) {
                if (p.hasPermission(Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-" + Integer.toString(i + 1) + ".Permission"))) {
                    CheckAmount = 0;
                    var10000 = Wardrobe.Page_1.getConfig();
                    var10001 = p.getUniqueId().toString();
                    if (var10000.getItemStack(var10001 + ".Slot-" + (i + 1) + ".Helmet") == null) {
                        inv.setItem(i, background(i, Page1, Name));
                        ++CheckAmount;
                    } else {
                        var10002 = Wardrobe.Page_1.getConfig();
                        var10003 = p.getUniqueId().toString();
                        inv.setItem(i, var10002.getItemStack(var10003 + ".Slot-" + (i + 1) + ".Helmet"));
                    }

                    var10000 = Wardrobe.Page_1.getConfig();
                    var10001 = p.getUniqueId().toString();
                    if (var10000.getItemStack(var10001 + ".Slot-" + (i + 1) + ".Chestplate") == null) {
                        inv.setItem(i + 9, background(i + 9, Page1, Name));
                        ++CheckAmount;
                    } else {
                        var7 = i + 9;
                        var10002 = Wardrobe.Page_1.getConfig();
                        var10003 = p.getUniqueId().toString();
                        inv.setItem(var7, var10002.getItemStack(var10003 + ".Slot-" + (i + 1) + ".Chestplate"));
                    }

                    var10000 = Wardrobe.Page_1.getConfig();
                    var10001 = p.getUniqueId().toString();
                    if (var10000.getItemStack(var10001 + ".Slot-" + (i + 1) + ".Leggings") == null) {
                        inv.setItem(i + 18, background(i + 18, Page1, Name));
                        ++CheckAmount;
                    } else {
                        var7 = i + 18;
                        var10002 = Wardrobe.Page_1.getConfig();
                        var10003 = p.getUniqueId().toString();
                        inv.setItem(var7, var10002.getItemStack(var10003 + ".Slot-" + (i + 1) + ".Leggings"));
                    }

                    var10000 = Wardrobe.Page_1.getConfig();
                    var10001 = p.getUniqueId().toString();
                    if (var10000.getItemStack(var10001 + ".Slot-" + (i + 1) + ".Boots") == null) {
                        inv.setItem(i + 27, background(i + 27, Page1, Name));
                        ++CheckAmount;
                    } else {
                        var7 = i + 27;
                        var10002 = Wardrobe.Page_1.getConfig();
                        var10003 = p.getUniqueId().toString();
                        inv.setItem(var7, var10002.getItemStack(var10003 + ".Slot-" + (i + 1) + ".Boots"));
                    }

                    if (CheckAmount == 4) {
                        inv.setItem(i + 36, createEmptyButton(i, Page1, Name));
                    }

                    var10000 = Wardrobe.Page_1.getConfig();
                    var10001 = p.getUniqueId().toString();
                    if (var10000.getString(var10001 + ".Slot-" + (i + 1) + ".Button") != null) {
                        var10000 = Wardrobe.Page_1.getConfig();
                        var10001 = p.getUniqueId().toString();
                        if (var10000.getString(var10001 + ".Slot-" + (i + 1) + ".Button").contains("Ready")) {
                            inv.setItem(i + 36, createReadyButton(i, Page1, Name));
                            continue;
                        }
                    }

                    var10000 = Wardrobe.Page_1.getConfig();
                    var10001 = p.getUniqueId().toString();
                    if (var10000.getString(var10001 + ".Slot-" + (i + 1) + ".Button") != null) {
                        var10000 = Wardrobe.Page_1.getConfig();
                        var10001 = p.getUniqueId().toString();
                        if (var10000.getString(var10001 + ".Slot-" + (i + 1) + ".Button").contains("Equipped")) {
                            inv.setItem(i + 36, createEquippedButton(i, Page1, Name));
                        }
                    }
                } else {
                    for(CheckAmount = i + 11; CheckAmount <= 18; ++CheckAmount) {
                        if (p.hasPermission(Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-" + CheckAmount + ".Permission"))) {
                            CheckAmount = 0;

                            for(int x = i; x <= 8; ++x) {
                                var10000 = Wardrobe.Page_1.getConfig();
                                var10001 = p.getUniqueId().toString();
                                if (var10000.getItemStack(var10001 + ".Slot-" + (x + 1) + ".Helmet") == null) {
                                    inv.setItem(x, background(x, Page1, Name));
                                    ++CheckAmount;
                                } else {
                                    var10002 = Wardrobe.Page_1.getConfig();
                                    var10003 = p.getUniqueId().toString();
                                    inv.setItem(x, var10002.getItemStack(var10003 + ".Slot-" + (x + 1) + ".Helmet"));
                                }

                                var10000 = Wardrobe.Page_1.getConfig();
                                var10001 = p.getUniqueId().toString();
                                if (var10000.getItemStack(var10001 + ".Slot-" + (x + 1) + ".Chestplate") == null) {
                                    inv.setItem(x + 9, background(x + 9, Page1, Name));
                                    ++CheckAmount;
                                } else {
                                    var7 = x + 9;
                                    var10002 = Wardrobe.Page_1.getConfig();
                                    var10003 = p.getUniqueId().toString();
                                    inv.setItem(var7, var10002.getItemStack(var10003 + ".Slot-" + (x + 1) + ".Chestplate"));
                                }

                                var10000 = Wardrobe.Page_1.getConfig();
                                var10001 = p.getUniqueId().toString();
                                if (var10000.getItemStack(var10001 + ".Slot-" + (x + 1) + ".Leggings") == null) {
                                    inv.setItem(x + 18, background(x + 18, Page1, Name));
                                    ++CheckAmount;
                                } else {
                                    var7 = x + 18;
                                    var10002 = Wardrobe.Page_1.getConfig();
                                    var10003 = p.getUniqueId().toString();
                                    inv.setItem(var7, var10002.getItemStack(var10003 + ".Slot-" + (x + 1) + ".Leggings"));
                                }

                                var10000 = Wardrobe.Page_1.getConfig();
                                var10001 = p.getUniqueId().toString();
                                if (var10000.getItemStack(var10001 + ".Slot-" + (x + 1) + ".Boots") == null) {
                                    inv.setItem(x + 27, background(x + 27, Page1, Name));
                                    ++CheckAmount;
                                } else {
                                    var7 = x + 27;
                                    var10002 = Wardrobe.Page_1.getConfig();
                                    var10003 = p.getUniqueId().toString();
                                    inv.setItem(var7, var10002.getItemStack(var10003 + ".Slot-" + (x + 1) + ".Boots"));
                                }

                                if (CheckAmount == 4) {
                                    inv.setItem(i + 36, createEmptyButton(i, Page1, Name));
                                }

                                var10000 = Wardrobe.Page_1.getConfig();
                                var10001 = p.getUniqueId().toString();
                                if (var10000.getString(var10001 + ".Slot-" + (i + 1) + ".Button") != null) {
                                    var10000 = Wardrobe.Page_1.getConfig();
                                    var10001 = p.getUniqueId().toString();
                                    if (var10000.getString(var10001 + ".Slot-" + (i + 1) + ".Button").contains("Ready")) {
                                        inv.setItem(i + 36, createReadyButton(i, Page1, Name));
                                        continue;
                                    }
                                }

                                var10000 = Wardrobe.Page_1.getConfig();
                                var10001 = p.getUniqueId().toString();
                                if (var10000.getString(var10001 + ".Slot-" + (i + 1) + ".Button") != null) {
                                    var10000 = Wardrobe.Page_1.getConfig();
                                    var10001 = p.getUniqueId().toString();
                                    if (var10000.getString(var10001 + ".Slot-" + (i + 1) + ".Button").contains("Equipped")) {
                                        inv.setItem(i + 36, createEquippedButton(i, Page1, Name));
                                    }
                                }
                            }

                            return;
                        }
                    }

                    for(CheckAmount = i + 1; CheckAmount <= 9; ++CheckAmount) {
                        if (p.hasPermission(Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-" + Integer.toString(CheckAmount) + ".Permission"))) {
                            CheckAmount = 0;
                            var10000 = Wardrobe.Page_1.getConfig();
                            var10001 = p.getUniqueId().toString();
                            if (var10000.getItemStack(var10001 + ".Slot-" + (i + 1) + ".Helmet") == null) {
                                inv.setItem(i, background(i, Page1, Name));
                                ++CheckAmount;
                            } else {
                                var10002 = Wardrobe.Page_1.getConfig();
                                var10003 = p.getUniqueId().toString();
                                inv.setItem(i, var10002.getItemStack(var10003 + ".Slot-" + (i + 1) + ".Helmet"));
                            }

                            var10000 = Wardrobe.Page_1.getConfig();
                            var10001 = p.getUniqueId().toString();
                            if (var10000.getItemStack(var10001 + ".Slot-" + (i + 1) + ".Chestplate") == null) {
                                inv.setItem(i + 9, background(i + 9, Page1, Name));
                                ++CheckAmount;
                            } else {
                                var7 = i + 9;
                                var10002 = Wardrobe.Page_1.getConfig();
                                var10003 = p.getUniqueId().toString();
                                inv.setItem(var7, var10002.getItemStack(var10003 + ".Slot-" + (i + 1) + ".Chestplate"));
                            }

                            var10000 = Wardrobe.Page_1.getConfig();
                            var10001 = p.getUniqueId().toString();
                            if (var10000.getItemStack(var10001 + ".Slot-" + (i + 1) + ".Leggings") == null) {
                                inv.setItem(i + 18, background(i + 18, Page1, Name));
                                ++CheckAmount;
                            } else {
                                var7 = i + 18;
                                var10002 = Wardrobe.Page_1.getConfig();
                                var10003 = p.getUniqueId().toString();
                                inv.setItem(var7, var10002.getItemStack(var10003 + ".Slot-" + (i + 1) + ".Leggings"));
                            }

                            var10000 = Wardrobe.Page_1.getConfig();
                            var10001 = p.getUniqueId().toString();
                            if (var10000.getItemStack(var10001 + ".Slot-" + (i + 1) + ".Boots") == null) {
                                inv.setItem(i + 27, background(i + 27, Page1, Name));
                                ++CheckAmount;
                            } else {
                                var7 = i + 27;
                                var10002 = Wardrobe.Page_1.getConfig();
                                var10003 = p.getUniqueId().toString();
                                inv.setItem(var7, var10002.getItemStack(var10003 + ".Slot-" + (i + 1) + ".Boots"));
                            }

                            if (CheckAmount == 4) {
                                inv.setItem(i + 36, createEmptyButton(i, Page1, Name));
                            }

                            var10000 = Wardrobe.Page_1.getConfig();
                            var10001 = p.getUniqueId().toString();
                            if (var10000.getString(var10001 + ".Slot-" + (i + 1) + ".Button") != null) {
                                var10000 = Wardrobe.Page_1.getConfig();
                                var10001 = p.getUniqueId().toString();
                                if (var10000.getString(var10001 + ".Slot-" + (i + 1) + ".Button").contains("Ready")) {
                                    inv.setItem(i + 36, createReadyButton(i, Page1, Name));
                                    continue;
                                }
                            }

                            var10000 = Wardrobe.Page_1.getConfig();
                            var10001 = p.getUniqueId().toString();
                            if (var10000.getString(var10001 + ".Slot-" + (i + 1) + ".Button") != null) {
                                var10000 = Wardrobe.Page_1.getConfig();
                                var10001 = p.getUniqueId().toString();
                                if (var10000.getString(var10001 + ".Slot-" + (i + 1) + ".Button").contains("Equipped")) {
                                    inv.setItem(i + 36, createEquippedButton(i, Page1, Name));
                                }
                            }
                        }
                    }
                }
            }
        } else if (inv == Page2) {
            for(i = 0; i <= 8; ++i) {
                if (p.hasPermission(Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-" + (i + 10) + ".Permission"))) {
                    CheckAmount = 0;
                    var10000 = Wardrobe.Page_2.getConfig();
                    var10001 = p.getUniqueId().toString();
                    if (var10000.getItemStack(var10001 + ".Slot-" + (i + 10) + ".Helmet") == null) {
                        inv.setItem(i, background(i, Page2, Name));
                        ++CheckAmount;
                    } else {
                        var10002 = Wardrobe.Page_2.getConfig();
                        var10003 = p.getUniqueId().toString();
                        inv.setItem(i, var10002.getItemStack(var10003 + ".Slot-" + (i + 10) + ".Helmet"));
                    }

                    var10000 = Wardrobe.Page_2.getConfig();
                    var10001 = p.getUniqueId().toString();
                    if (var10000.getItemStack(var10001 + ".Slot-" + (i + 10) + ".Chestplate") == null) {
                        inv.setItem(i + 9, background(i + 9, Page2, Name));
                        ++CheckAmount;
                    } else {
                        var7 = i + 9;
                        var10002 = Wardrobe.Page_2.getConfig();
                        var10003 = p.getUniqueId().toString();
                        inv.setItem(var7, var10002.getItemStack(var10003 + ".Slot-" + (i + 10) + ".Chestplate"));
                    }

                    var10000 = Wardrobe.Page_2.getConfig();
                    var10001 = p.getUniqueId().toString();
                    if (var10000.getItemStack(var10001 + ".Slot-" + (i + 10) + ".Leggings") == null) {
                        inv.setItem(i + 18, background(i + 18, Page2, Name));
                        ++CheckAmount;
                    } else {
                        var7 = i + 18;
                        var10002 = Wardrobe.Page_2.getConfig();
                        var10003 = p.getUniqueId().toString();
                        inv.setItem(var7, var10002.getItemStack(var10003 + ".Slot-" + (i + 10) + ".Leggings"));
                    }

                    var10000 = Wardrobe.Page_2.getConfig();
                    var10001 = p.getUniqueId().toString();
                    if (var10000.getItemStack(var10001 + ".Slot-" + (i + 10) + ".Boots") == null) {
                        inv.setItem(i + 27, background(i + 27, Page2, Name));
                        ++CheckAmount;
                    } else {
                        var7 = i + 27;
                        var10002 = Wardrobe.Page_2.getConfig();
                        var10003 = p.getUniqueId().toString();
                        inv.setItem(var7, var10002.getItemStack(var10003 + ".Slot-" + (i + 10) + ".Boots"));
                    }

                    if (CheckAmount == 4) {
                        inv.setItem(i + 36, createEmptyButton(i, Page2, Name));
                    }

                    var10000 = Wardrobe.Page_2.getConfig();
                    var10001 = p.getUniqueId().toString();
                    if (var10000.getString(var10001 + ".Slot-" + (i + 10) + ".Button") != null) {
                        var10000 = Wardrobe.Page_2.getConfig();
                        var10001 = p.getUniqueId().toString();
                        if (var10000.getString(var10001 + ".Slot-" + (i + 10) + ".Button").contains("Ready")) {
                            inv.setItem(i + 36, createReadyButton(i, Page2, Name));
                            continue;
                        }
                    }

                    var10000 = Wardrobe.Page_2.getConfig();
                    var10001 = p.getUniqueId().toString();
                    if (var10000.getString(var10001 + ".Slot-" + (i + 10) + ".Button") != null) {
                        var10000 = Wardrobe.Page_2.getConfig();
                        var10001 = p.getUniqueId().toString();
                        if (var10000.getString(var10001 + ".Slot-" + (i + 10) + ".Button").contains("Equipped")) {
                            inv.setItem(i + 36, createEquippedButton(i, Page2, Name));
                        }
                    }
                } else {
                    for(CheckAmount = i + 11; CheckAmount <= 18; ++CheckAmount) {
                        if (p.hasPermission(Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-" + CheckAmount + ".Permission"))) {
                            CheckAmount = 0;
                            var10000 = Wardrobe.Page_2.getConfig();
                            var10001 = p.getUniqueId().toString();
                            if (var10000.getItemStack(var10001 + ".Slot-" + (i + 10) + ".Helmet") == null) {
                                inv.setItem(i, background(i, Page2, Name));
                                ++CheckAmount;
                            } else {
                                var10002 = Wardrobe.Page_2.getConfig();
                                var10003 = p.getUniqueId().toString();
                                inv.setItem(i, var10002.getItemStack(var10003 + ".Slot-" + (i + 10) + ".Helmet"));
                            }

                            var10000 = Wardrobe.Page_2.getConfig();
                            var10001 = p.getUniqueId().toString();
                            if (var10000.getItemStack(var10001 + ".Slot-" + (i + 10) + ".Chestplate") == null) {
                                inv.setItem(i + 9, background(i + 9, Page2, Name));
                                ++CheckAmount;
                            } else {
                                var7 = i + 9;
                                var10002 = Wardrobe.Page_2.getConfig();
                                var10003 = p.getUniqueId().toString();
                                inv.setItem(var7, var10002.getItemStack(var10003 + ".Slot-" + (i + 10) + ".Chestplate"));
                            }

                            var10000 = Wardrobe.Page_2.getConfig();
                            var10001 = p.getUniqueId().toString();
                            if (var10000.getItemStack(var10001 + ".Slot-" + (i + 10) + ".Leggings") == null) {
                                inv.setItem(i + 18, background(i + 18, Page2, Name));
                                ++CheckAmount;
                            } else {
                                var7 = i + 18;
                                var10002 = Wardrobe.Page_2.getConfig();
                                var10003 = p.getUniqueId().toString();
                                inv.setItem(var7, var10002.getItemStack(var10003 + ".Slot-" + (i + 10) + ".Leggings"));
                            }

                            var10000 = Wardrobe.Page_2.getConfig();
                            var10001 = p.getUniqueId().toString();
                            if (var10000.getItemStack(var10001 + ".Slot-" + (i + 10) + ".Boots") == null) {
                                inv.setItem(i + 27, background(i + 27, Page2, Name));
                                ++CheckAmount;
                            } else {
                                var7 = i + 27;
                                var10002 = Wardrobe.Page_2.getConfig();
                                var10003 = p.getUniqueId().toString();
                                inv.setItem(var7, var10002.getItemStack(var10003 + ".Slot-" + (i + 10) + ".Boots"));
                            }

                            if (CheckAmount == 4) {
                                inv.setItem(i + 36, createEmptyButton(i, Page2, Name));
                            }

                            var10000 = Wardrobe.Page_2.getConfig();
                            var10001 = p.getUniqueId().toString();
                            if (var10000.getString(var10001 + ".Slot-" + (i + 10) + ".Button") != null) {
                                var10000 = Wardrobe.Page_2.getConfig();
                                var10001 = p.getUniqueId().toString();
                                if (var10000.getString(var10001 + ".Slot-" + (i + 10) + ".Button").contains("Ready")) {
                                    inv.setItem(i + 36, createReadyButton(i, Page2, Name));
                                    continue;
                                }
                            }

                            var10000 = Wardrobe.Page_2.getConfig();
                            var10001 = p.getUniqueId().toString();
                            if (var10000.getString(var10001 + ".Slot-" + (i + 10) + ".Button") != null) {
                                var10000 = Wardrobe.Page_2.getConfig();
                                var10001 = p.getUniqueId().toString();
                                if (var10000.getString(var10001 + ".Slot-" + (i + 10) + ".Button").contains("Equipped")) {
                                    inv.setItem(i + 36, createEquippedButton(i, Page2, Name));
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    public static ItemStack background(int Slot, Inventory inv, String Title) {
        ItemStack AvailableBackground = new ItemStack(Material.DIRT);
        ItemMeta AvailableBackgroundMeta = AvailableBackground.getItemMeta();
        String Mat = "";
        if (Slot != 0 && Slot - 9 != 0 && Slot - 18 != 0 && Slot - 27 != 0) {
            if (Slot != 1 && Slot - 9 != 1 && Slot - 18 != 1 && Slot - 27 != 1) {
                if (Slot != 2 && Slot - 9 != 2 && Slot - 18 != 2 && Slot - 27 != 2) {
                    if (Slot != 3 && Slot - 9 != 3 && Slot - 18 != 3 && Slot - 27 != 3) {
                        if (Slot != 4 && Slot - 9 != 4 && Slot - 18 != 4 && Slot - 27 != 4) {
                            if (Slot != 5 && Slot - 9 != 5 && Slot - 18 != 5 && Slot - 27 != 5) {
                                if (Slot != 6 && Slot - 9 != 6 && Slot - 18 != 6 && Slot - 27 != 6) {
                                    if (Slot != 7 && Slot - 9 != 7 && Slot - 18 != 7 && Slot - 27 != 7) {
                                        if (Slot == 8 || Slot - 9 == 8 || Slot - 18 == 8 || Slot - 27 == 8) {
                                            if (Ver.contains("1.8") || Ver.contains("1.9") || Ver.contains("1.10") || Ver.contains("1.11") || Ver.contains("1.12")) {
                                                Mat = "STAINED_GLASS_PANE";
                                                AvailableBackground.setType(Material.valueOf(Mat));
                                                AvailableBackground.setDurability((short)10);
                                            }

                                            if (Ver.contains("1.13") || Ver.contains("1.14") || Ver.contains("1.15") || Ver.contains("1.16") || Ver.contains("1.17") || Ver.contains("1.18") || Ver.contains("1.19") || Ver.contains("1.20")) {
                                                Mat = "PURPLE_STAINED_GLASS_PANE";
                                                AvailableBackground.setType(Material.valueOf(Mat));
                                            }
                                        }
                                    } else {
                                        if (Ver.contains("1.8") || Ver.contains("1.9") || Ver.contains("1.10") || Ver.contains("1.11") || Ver.contains("1.12")) {
                                            Mat = "STAINED_GLASS_PANE";
                                            AvailableBackground.setType(Material.valueOf(Mat));
                                            AvailableBackground.setDurability((short)2);
                                        }

                                        if (Ver.contains("1.13") || Ver.contains("1.14") || Ver.contains("1.15") || Ver.contains("1.16") || Ver.contains("1.17") || Ver.contains("1.18") || Ver.contains("1.19") || Ver.contains("1.20")) {
                                            Mat = "MAGENTA_STAINED_GLASS_PANE";
                                            AvailableBackground.setType(Material.valueOf(Mat));
                                        }
                                    }
                                } else {
                                    if (Ver.contains("1.8") || Ver.contains("1.9") || Ver.contains("1.10") || Ver.contains("1.11") || Ver.contains("1.12")) {
                                        Mat = "STAINED_GLASS_PANE";
                                        AvailableBackground.setType(Material.valueOf(Mat));
                                        AvailableBackground.setDurability((short)11);
                                    }

                                    if (Ver.contains("1.13") || Ver.contains("1.14") || Ver.contains("1.15") || Ver.contains("1.16") || Ver.contains("1.17") || Ver.contains("1.18") || Ver.contains("1.19") || Ver.contains("1.20")) {
                                        Mat = "BLUE_STAINED_GLASS_PANE";
                                        AvailableBackground.setType(Material.valueOf(Mat));
                                    }
                                }
                            } else {
                                if (Ver.contains("1.8") || Ver.contains("1.9") || Ver.contains("1.10") || Ver.contains("1.11") || Ver.contains("1.12")) {
                                    Mat = "STAINED_GLASS_PANE";
                                    AvailableBackground.setType(Material.valueOf(Mat));
                                    AvailableBackground.setDurability((short)4);
                                }

                                if (Ver.contains("1.13") || Ver.contains("1.14") || Ver.contains("1.15") || Ver.contains("1.16") || Ver.contains("1.17") || Ver.contains("1.18") || Ver.contains("1.19") || Ver.contains("1.20")) {
                                    Mat = "LIGHT_BLUE_STAINED_GLASS_PANE";
                                    AvailableBackground.setType(Material.valueOf(Mat));
                                }
                            }
                        } else {
                            if (Ver.contains("1.8") || Ver.contains("1.9") || Ver.contains("1.10") || Ver.contains("1.11") || Ver.contains("1.12")) {
                                Mat = "STAINED_GLASS_PANE";
                                AvailableBackground.setType(Material.valueOf(Mat));
                                AvailableBackground.setDurability((short)13);
                            }

                            if (Ver.contains("1.13") || Ver.contains("1.14") || Ver.contains("1.15") || Ver.contains("1.16") || Ver.contains("1.17") || Ver.contains("1.18") || Ver.contains("1.19") || Ver.contains("1.20")) {
                                Mat = "GREEN_STAINED_GLASS_PANE";
                                AvailableBackground.setType(Material.valueOf(Mat));
                            }
                        }
                    } else {
                        if (Ver.contains("1.8") || Ver.contains("1.9") || Ver.contains("1.10") || Ver.contains("1.11") || Ver.contains("1.12")) {
                            Mat = "STAINED_GLASS_PANE";
                            AvailableBackground.setType(Material.valueOf(Mat));
                            AvailableBackground.setDurability((short)5);
                        }

                        if (Ver.contains("1.13") || Ver.contains("1.14") || Ver.contains("1.15") || Ver.contains("1.16") || Ver.contains("1.17") || Ver.contains("1.18") || Ver.contains("1.19") || Ver.contains("1.20")) {
                            Mat = "LIME_STAINED_GLASS_PANE";
                            AvailableBackground.setType(Material.valueOf(Mat));
                        }
                    }
                } else {
                    if (Ver.contains("1.8") || Ver.contains("1.9") || Ver.contains("1.10") || Ver.contains("1.11") || Ver.contains("1.12")) {
                        Mat = "STAINED_GLASS_PANE";
                        AvailableBackground.setType(Material.valueOf(Mat));
                        AvailableBackground.setDurability((short)4);
                    }

                    if (Ver.contains("1.13") || Ver.contains("1.14") || Ver.contains("1.15") || Ver.contains("1.16") || Ver.contains("1.17") || Ver.contains("1.18") || Ver.contains("1.19") || Ver.contains("1.20")) {
                        Mat = "YELLOW_STAINED_GLASS_PANE";
                        AvailableBackground.setType(Material.valueOf(Mat));
                    }
                }
            } else {
                if (Ver.contains("1.8") || Ver.contains("1.9") || Ver.contains("1.10") || Ver.contains("1.11") || Ver.contains("1.12")) {
                    Mat = "STAINED_GLASS_PANE";
                    AvailableBackground.setType(Material.valueOf(Mat));
                    AvailableBackground.setDurability((short)1);
                }

                if (Ver.contains("1.13") || Ver.contains("1.14") || Ver.contains("1.15") || Ver.contains("1.16") || Ver.contains("1.17") || Ver.contains("1.18") || Ver.contains("1.19") || Ver.contains("1.20")) {
                    Mat = "ORANGE_STAINED_GLASS_PANE";
                    AvailableBackground.setType(Material.valueOf(Mat));
                }
            }
        } else if (!Ver.contains("1.8") && !Ver.contains("1.9") && !Ver.contains("1.10") && !Ver.contains("1.11") && !Ver.contains("1.12")) {
            if (Ver.contains("1.13") || Ver.contains("1.14") || Ver.contains("1.15") || Ver.contains("1.16") || Ver.contains("1.17") || Ver.contains("1.18") || Ver.contains("1.19") || Ver.contains("1.20")) {
                Mat = "RED_STAINED_GLASS_PANE";
                AvailableBackground.setType(Material.valueOf(Mat));
            }
        } else {
            Mat = "STAINED_GLASS_PANE";
            AvailableBackground.setType(Material.valueOf(Mat));
            AvailableBackground.setDurability((short)14);
        }

        List<String> AvailableBackgroundLore = new ArrayList();
        String Name;
        Iterator var8;
        String Lore;
        if (Slot >= 0 && Slot <= 8) {
            Name = "";
            if (Title.equals(Page1Name)) {
                Name = Wardrobe.ConfigData.getConfig().getString("Availabel-Slot.Helmet-Slot.Name").replace("%Slot%", Integer.toString(Slot + 1));
            } else if (Title.equals(Page2Name)) {
                Name = Wardrobe.ConfigData.getConfig().getString("Availabel-Slot.Helmet-Slot.Name").replace("%Slot%", Integer.toString(Slot + 10));
            }

            AvailableBackgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Name));
            var8 = Wardrobe.ConfigData.getConfig().getStringList("Availabel-Slot.Helmet-Slot.Lore").iterator();

            while(var8.hasNext()) {
                Lore = (String)var8.next();
                AvailableBackgroundLore.add(ChatColor.translateAlternateColorCodes('&', Lore));
            }
        } else if (Slot >= 9 && Slot <= 17) {
            Name = "";
            if (Title.equals(Page1Name)) {
                Name = Wardrobe.ConfigData.getConfig().getString("Availabel-Slot.Chestplate-Slot.Name").replace("%Slot%", Integer.toString(Slot - 8));
            } else if (Title.equals(Page2Name)) {
                Name = Wardrobe.ConfigData.getConfig().getString("Availabel-Slot.Chestplate-Slot.Name").replace("%Slot%", Integer.toString(Slot + 1));
            }

            AvailableBackgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Name));
            var8 = Wardrobe.ConfigData.getConfig().getStringList("Availabel-Slot.Chestplate-Slot.Lore").iterator();

            while(var8.hasNext()) {
                Lore = (String)var8.next();
                AvailableBackgroundLore.add(ChatColor.translateAlternateColorCodes('&', Lore));
            }
        } else if (Slot >= 18 && Slot <= 26) {
            Name = "";
            if (Title.equals(Page1Name)) {
                Name = Wardrobe.ConfigData.getConfig().getString("Availabel-Slot.Leggings-Slot.Name").replace("%Slot%", Integer.toString(Slot - 17));
            } else if (Title.equals(Page2Name)) {
                Name = Wardrobe.ConfigData.getConfig().getString("Availabel-Slot.Leggings-Slot.Name").replace("%Slot%", Integer.toString(Slot - 8));
            }

            AvailableBackgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Name));
            var8 = Wardrobe.ConfigData.getConfig().getStringList("Availabel-Slot.Leggings-Slot.Lore").iterator();

            while(var8.hasNext()) {
                Lore = (String)var8.next();
                AvailableBackgroundLore.add(ChatColor.translateAlternateColorCodes('&', Lore));
            }
        } else if (Slot >= 27 && Slot <= 35) {
            Name = "";
            if (Title.equals(Page1Name)) {
                Name = Wardrobe.ConfigData.getConfig().getString("Availabel-Slot.Boots-Slot.Name").replace("%Slot%", Integer.toString(Slot - 26));
            } else if (Title.equals(Page2Name)) {
                Name = Wardrobe.ConfigData.getConfig().getString("Availabel-Slot.Boots-Slot.Name").replace("%Slot%", Integer.toString(Slot - 17));
            }

            AvailableBackgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Name));
            var8 = Wardrobe.ConfigData.getConfig().getStringList("Availabel-Slot.Boots-Slot.Lore").iterator();

            while(var8.hasNext()) {
                Lore = (String)var8.next();
                AvailableBackgroundLore.add(ChatColor.translateAlternateColorCodes('&', Lore));
            }
        }

        AvailableBackgroundMeta.setLore(AvailableBackgroundLore);
        AvailableBackground.setItemMeta(AvailableBackgroundMeta);
        return AvailableBackground;
    }

    public static ItemStack createEmptyButton(int Slot, Inventory inv, String Title) {
        ItemStack Button = new ItemStack(Material.DIRT);
        ItemMeta ButtonMeta = Button.getItemMeta();
        String Name;
        if (Ver.contains("1.8") || Ver.contains("1.9") || Ver.contains("1.10") || Ver.contains("1.11") || Ver.contains("1.12")) {
            Name = "INK_SACK";
            Button.setType(Material.valueOf(Name));
            Button.setDurability((short)8);
        }

        if (Ver.contains("1.13") || Ver.contains("1.14") || Ver.contains("1.15") || Ver.contains("1.16") || Ver.contains("1.17") || Ver.contains("1.18") || Ver.contains("1.19") || Ver.contains("1.20")) {
            Name = "GRAY_DYE";
            Button.setType(Material.valueOf(Name));
        }

        Name = "";
        if (Title.contains(Page1Name)) {
            Name = Wardrobe.ConfigData.getConfig().getString("Availabel-Slot.Empty-Slot-Button.Name").replace("%Slot%", Integer.toString(Slot + 1));
        } else if (Title.contains(Page2Name)) {
            Name = Wardrobe.ConfigData.getConfig().getString("Availabel-Slot.Empty-Slot-Button.Name").replace("%Slot%", Integer.toString(Slot + 10));
        }

        ButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Name));
        List<String> ButtonLore = new ArrayList();
        Iterator var7 = Wardrobe.ConfigData.getConfig().getStringList("Availabel-Slot.Empty-Slot-Button.Lore").iterator();

        while(var7.hasNext()) {
            String Lore = (String)var7.next();
            ButtonLore.add(ChatColor.translateAlternateColorCodes('&', Lore));
        }

        ButtonMeta.setLore(ButtonLore);
        Button.setItemMeta(ButtonMeta);
        return Button;
    }

    public static ItemStack createReadyButton(int Slot, Inventory inv, String Title) {
        ItemStack Button = new ItemStack(Material.DIRT);
        ItemMeta ButtonMeta = Button.getItemMeta();
        String Name;
        if (Ver.contains("1.8") || Ver.contains("1.9") || Ver.contains("1.10") || Ver.contains("1.11") || Ver.contains("1.12")) {
            Name = "INK_SACK";
            Button.setType(Material.valueOf(Name));
            Button.setDurability((short)9);
        }

        if (Ver.contains("1.13") || Ver.contains("1.14") || Ver.contains("1.15") || Ver.contains("1.16") || Ver.contains("1.17") || Ver.contains("1.18") || Ver.contains("1.19") || Ver.contains("1.20")) {
            Name = "PINK_DYE";
            Button.setType(Material.valueOf(Name));
        }

        Name = "";
        if (Title.contains(Page1Name)) {
            Name = Wardrobe.ConfigData.getConfig().getString("Availabel-Slot.Ready-Button.Name").replace("%Slot%", Integer.toString(Slot + 1));
        } else if (Title.contains(Page2Name)) {
            Name = Wardrobe.ConfigData.getConfig().getString("Availabel-Slot.Ready-Button.Name").replace("%Slot%", Integer.toString(Slot + 10));
        }

        ButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Name));
        List<String> ButtonLore = new ArrayList();
        Iterator var7 = Wardrobe.ConfigData.getConfig().getStringList("Availabel-Slot.Ready-Button.Lore").iterator();

        while(var7.hasNext()) {
            String Lore = (String)var7.next();
            ButtonLore.add(ChatColor.translateAlternateColorCodes('&', Lore));
        }

        ButtonMeta.setLore(ButtonLore);
        Button.setItemMeta(ButtonMeta);
        return Button;
    }

    public static ItemStack createEquippedButton(int Slot, Inventory inv, String Title) {
        ItemStack Button = new ItemStack(Material.DIRT);
        ItemMeta ButtonMeta = Button.getItemMeta();
        String Name;
        if (Ver.contains("1.8") || Ver.contains("1.9") || Ver.contains("1.10") || Ver.contains("1.11") || Ver.contains("1.12")) {
            Name = "INK_SACK";
            Button.setType(Material.valueOf(Name));
            Button.setDurability((short)10);
        }

        if (Ver.contains("1.13") || Ver.contains("1.14") || Ver.contains("1.15") || Ver.contains("1.16") || Ver.contains("1.17") || Ver.contains("1.18") || Ver.contains("1.19") || Ver.contains("1.20")) {
            Name = "LIME_DYE";
            Button.setType(Material.valueOf(Name));
        }

        Name = "";
        if (Title.contains(Page1Name)) {
            Name = Wardrobe.ConfigData.getConfig().getString("Availabel-Slot.Equipped-Button.Name").replace("%Slot%", Integer.toString(Slot + 1));
        } else if (Title.contains(Page2Name)) {
            Name = Wardrobe.ConfigData.getConfig().getString("Availabel-Slot.Equipped-Button.Name").replace("%Slot%", Integer.toString(Slot + 10));
        }

        ButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Name));
        List<String> ButtonLore = new ArrayList();
        Iterator var7 = Wardrobe.ConfigData.getConfig().getStringList("Availabel-Slot.Equipped-Button.Lore").iterator();

        while(var7.hasNext()) {
            String Lore = (String)var7.next();
            ButtonLore.add(ChatColor.translateAlternateColorCodes('&', Lore));
        }

        ButtonMeta.setLore(ButtonLore);
        Button.setItemMeta(ButtonMeta);
        return Button;
    }

    public static void createGoBackAndCloseButton(Inventory inv) {
        ItemStack Close;
        ItemMeta CloseMeta;
        ArrayList CloseLore;
        Iterator var4;
        String Lore;
        if (Wardrobe.ConfigData.getConfig().getBoolean("Go-Back-Button.Enable")) {
            Close = new ItemStack(Material.ARROW);
            CloseMeta = Close.getItemMeta();
            CloseMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Wardrobe.ConfigData.getConfig().getString("Go-Back-Button.Name")));
            CloseLore = new ArrayList();
            if (Wardrobe.ConfigData.getConfig().getStringList("Go-Back-Button.Lore") != null) {
                var4 = Wardrobe.ConfigData.getConfig().getStringList("Go-Back-Button.Lore").iterator();

                while(var4.hasNext()) {
                    Lore = (String)var4.next();
                    CloseLore.add(ChatColor.translateAlternateColorCodes('&', Lore));
                }
            }

            CloseMeta.setLore(CloseLore);
            Close.setItemMeta(CloseMeta);
            inv.setItem(Wardrobe.ConfigData.getConfig().getInt("Go-Back-Button.Slot"), Close);
        }

        if (Wardrobe.ConfigData.getConfig().getBoolean("Close-Button.Enable")) {
            Close = new ItemStack(Material.BARRIER);
            CloseMeta = Close.getItemMeta();
            CloseMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Wardrobe.ConfigData.getConfig().getString("Close-Button.Name")));
            CloseLore = new ArrayList();
            var4 = Wardrobe.ConfigData.getConfig().getStringList("Close-Button.Lore").iterator();

            while(var4.hasNext()) {
                Lore = (String)var4.next();
                CloseLore.add(ChatColor.translateAlternateColorCodes('&', Lore));
            }

            CloseMeta.setLore(CloseLore);
            Close.setItemMeta(CloseMeta);
            inv.setItem(Wardrobe.ConfigData.getConfig().getInt("Close-Button.Slot"), Close);
        }

    }

    public static void createNextAndPreviousButton(Inventory inv) {
        ItemStack NextPage;
        ItemMeta NextPageMeta;
        ArrayList NextPageLore;
        Iterator var4;
        String Lore;
        if (inv == Page1) {
            if (Wardrobe.ConfigData.getConfig().getBoolean("Next-Page-Button.Enable")) {
                NextPage = new ItemStack(Material.ARROW);
                NextPageMeta = NextPage.getItemMeta();
                NextPageMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Wardrobe.ConfigData.getConfig().getString("Next-Page-Button.Name")));
                NextPageLore = new ArrayList();
                if (Wardrobe.ConfigData.getConfig().getStringList("Next-Page-Button.Lore") != null) {
                    var4 = Wardrobe.ConfigData.getConfig().getStringList("Next-Page-Button.Lore").iterator();

                    while(var4.hasNext()) {
                        Lore = (String)var4.next();
                        NextPageLore.add(ChatColor.translateAlternateColorCodes('&', Lore));
                    }
                }

                NextPageMeta.setLore(NextPageLore);
                NextPage.setItemMeta(NextPageMeta);
                inv.setItem(Wardrobe.ConfigData.getConfig().getInt("Next-Page-Button.Slot"), NextPage);
            }
        } else if (inv == Page2 && Wardrobe.ConfigData.getConfig().getBoolean("Previous-Page-Button.Enable")) {
            NextPage = new ItemStack(Material.ARROW);
            NextPageMeta = NextPage.getItemMeta();
            NextPageMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Wardrobe.ConfigData.getConfig().getString("Previous-Page-Button.Name")));
            NextPageLore = new ArrayList();
            if (Wardrobe.ConfigData.getConfig().getStringList("Previous-Page-Button.Lore") != null) {
                var4 = Wardrobe.ConfigData.getConfig().getStringList("Previous-Page-Button.Lore").iterator();

                while(var4.hasNext()) {
                    Lore = (String)var4.next();
                    NextPageLore.add(ChatColor.translateAlternateColorCodes('&', Lore));
                }
            }

            NextPageMeta.setLore(NextPageLore);
            NextPage.setItemMeta(NextPageMeta);
            inv.setItem(Wardrobe.ConfigData.getConfig().getInt("Previous-Page-Button.Slot"), NextPage);
        }

    }
}
