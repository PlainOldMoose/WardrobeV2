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

    public static void CreateWardrobePage1(Player p) {
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

        CreateGoBackAndCloseButton(WardrobePage1);
        Page1 = WardrobePage1;
        CreateNextAndPreviousButton(WardrobePage1);
        CreateBaseBackground(WardrobePage1);
        CreateAvaiableSlotBackground(WardrobePage1, Name, p);

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
                    WardrobePage1.setItem(i, CreateEmptyButton(i - 36, WardrobePage1, Name));
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

    public static void CreateWardrobePage2(Player p) {
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

        CreateGoBackAndCloseButton(WardrobePage2);
        Page2 = WardrobePage2;
        CreateNextAndPreviousButton(WardrobePage2);
        CreateBaseBackground(WardrobePage2);
        CreateAvaiableSlotBackground(WardrobePage2, Name, p);

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
                    WardrobePage2.setItem(i, CreateEmptyButton(i - 36, WardrobePage2, Name));
                }
            }
        }

        p.openInventory(WardrobePage2);
    }

    public static void CreateBaseBackground(Inventory inv) {
        ItemStack Background;
        ItemMeta BackgroundMeta;
        String Mat;
        ItemStack Button;
        ItemMeta ButtonMeta;
        String Name;
        ArrayList ButtonLore;
        Iterator var8;
        String Lore;
        int i;
        ArrayList BackgroundLore;
        Iterator var13;
        if (inv == Page1) {
            Background = new ItemStack(Material.DIRT);
            BackgroundMeta = Background.getItemMeta();
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

            for(i = 0; i <= 44; ++i) {
                if (i >= 0 && i <= 8) {
                    Name = Wardrobe.ConfigData.getConfig().getString("Locked-Slot.Helmet-Slot.Name").replace("%Slot%", Integer.toString(i + 1));
                    BackgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Name));
                    BackgroundLore = new ArrayList();

                    for(var13 = Wardrobe.ConfigData.getConfig().getStringList("Locked-Slot.Helmet-Slot.Lore").iterator(); var13.hasNext(); BackgroundLore.add(ChatColor.translateAlternateColorCodes('&', Lore))) {
                        Lore = (String)var13.next();
                        if (i + 1 == 1) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-1.Require-Prefix"));
                        }

                        if (i + 1 == 2) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-2.Require-Prefix"));
                        }

                        if (i + 1 == 3) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-3.Require-Prefix"));
                        }

                        if (i + 1 == 4) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-4.Require-Prefix"));
                        }

                        if (i + 1 == 5) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-5.Require-Prefix"));
                        }

                        if (i + 1 == 6) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-6.Require-Prefix"));
                        }

                        if (i + 1 == 7) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-7.Require-Prefix"));
                        }

                        if (i + 1 == 8) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-8.Require-Prefix"));
                        }

                        if (i + 1 == 9) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-9.Require-Prefix"));
                        }
                    }

                    BackgroundMeta.setLore(BackgroundLore);
                    Background.setItemMeta(BackgroundMeta);
                    inv.setItem(i, Background);
                } else if (i >= 9 && i <= 17) {
                    Name = Wardrobe.ConfigData.getConfig().getString("Locked-Slot.Chestplate-Slot.Name").replace("%Slot%", Integer.toString(i - 8));
                    BackgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Name));
                    BackgroundLore = new ArrayList();

                    for(var13 = Wardrobe.ConfigData.getConfig().getStringList("Locked-Slot.Chestplate-Slot.Lore").iterator(); var13.hasNext(); BackgroundLore.add(ChatColor.translateAlternateColorCodes('&', Lore))) {
                        Lore = (String)var13.next();
                        if (i - 8 == 1) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-1.Require-Prefix"));
                        }

                        if (i - 8 == 2) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-2.Require-Prefix"));
                        }

                        if (i - 8 == 3) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-3.Require-Prefix"));
                        }

                        if (i - 8 == 4) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-4.Require-Prefix"));
                        }

                        if (i - 8 == 5) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-5.Require-Prefix"));
                        }

                        if (i - 8 == 6) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-6.Require-Prefix"));
                        }

                        if (i - 8 == 7) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-7.Require-Prefix"));
                        }

                        if (i - 8 == 8) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-8.Require-Prefix"));
                        }

                        if (i - 8 == 9) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-9.Require-Prefix"));
                        }
                    }

                    BackgroundMeta.setLore(BackgroundLore);
                    Background.setItemMeta(BackgroundMeta);
                    inv.setItem(i, Background);
                } else if (i >= 18 && i <= 26) {
                    Name = Wardrobe.ConfigData.getConfig().getString("Locked-Slot.Leggings-Slot.Name").replace("%Slot%", Integer.toString(i - 17));
                    BackgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Name));
                    BackgroundLore = new ArrayList();

                    for(var13 = Wardrobe.ConfigData.getConfig().getStringList("Locked-Slot.Leggings-Slot.Lore").iterator(); var13.hasNext(); BackgroundLore.add(ChatColor.translateAlternateColorCodes('&', Lore))) {
                        Lore = (String)var13.next();
                        if (i - 17 == 1) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-1.Require-Prefix"));
                        }

                        if (i - 17 == 2) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-2.Require-Prefix"));
                        }

                        if (i - 17 == 3) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-3.Require-Prefix"));
                        }

                        if (i - 17 == 4) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-4.Require-Prefix"));
                        }

                        if (i - 17 == 5) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-5.Require-Prefix"));
                        }

                        if (i - 17 == 6) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-6.Require-Prefix"));
                        }

                        if (i - 17 == 7) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-7.Require-Prefix"));
                        }

                        if (i - 17 == 8) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-8.Require-Prefix"));
                        }

                        if (i - 17 == 9) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-9.Require-Prefix"));
                        }
                    }

                    BackgroundMeta.setLore(BackgroundLore);
                    Background.setItemMeta(BackgroundMeta);
                    inv.setItem(i, Background);
                } else if (i >= 27 && i <= 35) {
                    Name = Wardrobe.ConfigData.getConfig().getString("Locked-Slot.Boots-Slot.Name").replace("%Slot%", Integer.toString(i - 26));
                    BackgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Name));
                    BackgroundLore = new ArrayList();

                    for(var13 = Wardrobe.ConfigData.getConfig().getStringList("Locked-Slot.Boots-Slot.Lore").iterator(); var13.hasNext(); BackgroundLore.add(ChatColor.translateAlternateColorCodes('&', Lore))) {
                        Lore = (String)var13.next();
                        if (i - 26 == 1) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-1.Require-Prefix"));
                        }

                        if (i - 26 == 2) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-2.Require-Prefix"));
                        }

                        if (i - 26 == 3) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-3.Require-Prefix"));
                        }

                        if (i - 26 == 4) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-4.Require-Prefix"));
                        }

                        if (i - 26 == 5) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-5.Require-Prefix"));
                        }

                        if (i - 26 == 6) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-6.Require-Prefix"));
                        }

                        if (i - 26 == 7) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-7.Require-Prefix"));
                        }

                        if (i - 26 == 8) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-8.Require-Prefix"));
                        }

                        if (i - 26 == 9) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-9.Require-Prefix"));
                        }
                    }

                    BackgroundMeta.setLore(BackgroundLore);
                    Background.setItemMeta(BackgroundMeta);
                    inv.setItem(i, Background);
                } else if (i >= 36 && i <= 44) {
                    Button = new ItemStack(Material.DIRT);
                    ButtonMeta = Button.getItemMeta();
                    if (Ver.contains("1.8") || Ver.contains("1.9") || Ver.contains("1.10") || Ver.contains("1.11") || Ver.contains("1.12")) {
                        Name = "INK_SACK";
                        Button.setType(Material.valueOf(Name));
                        Button.setDurability((short)1);
                    }

                    if (Ver.contains("1.13")) {
                        Name = "ROSE_RED";
                        Button.setType(Material.valueOf(Name));
                    }

                    if (Ver.contains("1.14") || Ver.contains("1.15") || Ver.contains("1.16") || Ver.contains("1.17") || Ver.contains("1.18") || Ver.contains("1.19") || Ver.contains("1.20")) {
                        Name = "RED_DYE";
                        Button.setType(Material.valueOf(Name));
                    }

                    Name = Wardrobe.ConfigData.getConfig().getString("Locked-Slot.Locked-Button.Name").replace("%Slot%", Integer.toString(i - 35));
                    ButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Name));
                    ButtonLore = new ArrayList();

                    for(var8 = Wardrobe.ConfigData.getConfig().getStringList("Locked-Slot.Locked-Button.Lore").iterator(); var8.hasNext(); ButtonLore.add(ChatColor.translateAlternateColorCodes('&', Lore))) {
                        Lore = (String)var8.next();
                        if (i - 35 == 1) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-1.Require-Prefix"));
                        }

                        if (i - 35 == 2) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-2.Require-Prefix"));
                        }

                        if (i - 35 == 3) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-3.Require-Prefix"));
                        }

                        if (i - 35 == 4) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-4.Require-Prefix"));
                        }

                        if (i - 35 == 5) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-5.Require-Prefix"));
                        }

                        if (i - 35 == 6) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-6.Require-Prefix"));
                        }

                        if (i - 35 == 7) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-7.Require-Prefix"));
                        }

                        if (i - 35 == 8) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-8.Require-Prefix"));
                        }

                        if (i - 35 == 9) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-9.Require-Prefix"));
                        }
                    }

                    ButtonMeta.setLore(ButtonLore);
                    Button.setItemMeta(ButtonMeta);
                    inv.setItem(i, Button);
                }
            }
        } else if (inv == Page2) {
            Background = new ItemStack(Material.DIRT);
            BackgroundMeta = Background.getItemMeta();
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

            for(i = 0; i <= 44; ++i) {
                if (i >= 0 && i <= 8) {
                    Name = Wardrobe.ConfigData.getConfig().getString("Locked-Slot.Helmet-Slot.Name").replace("%Slot%", Integer.toString(i + 10));
                    BackgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Name));
                    BackgroundLore = new ArrayList();

                    for(var13 = Wardrobe.ConfigData.getConfig().getStringList("Locked-Slot.Helmet-Slot.Lore").iterator(); var13.hasNext(); BackgroundLore.add(ChatColor.translateAlternateColorCodes('&', Lore))) {
                        Lore = (String)var13.next();
                        if (i + 10 == 10) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-10.Require-Prefix"));
                        }

                        if (i + 10 == 11) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-11.Require-Prefix"));
                        }

                        if (i + 10 == 12) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-12.Require-Prefix"));
                        }

                        if (i + 10 == 13) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-13.Require-Prefix"));
                        }

                        if (i + 10 == 14) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-14.Require-Prefix"));
                        }

                        if (i + 10 == 15) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-15.Require-Prefix"));
                        }

                        if (i + 10 == 16) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-16.Require-Prefix"));
                        }

                        if (i + 10 == 17) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-17.Require-Prefix"));
                        }

                        if (i + 10 == 18) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-18.Require-Prefix"));
                        }
                    }

                    BackgroundMeta.setLore(BackgroundLore);
                    Background.setItemMeta(BackgroundMeta);
                    inv.setItem(i, Background);
                } else if (i >= 9 && i <= 17) {
                    Name = Wardrobe.ConfigData.getConfig().getString("Locked-Slot.Chestplate-Slot.Name").replace("%Slot%", Integer.toString(i + 1));
                    BackgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Name));
                    BackgroundLore = new ArrayList();

                    for(var13 = Wardrobe.ConfigData.getConfig().getStringList("Locked-Slot.Chestplate-Slot.Lore").iterator(); var13.hasNext(); BackgroundLore.add(ChatColor.translateAlternateColorCodes('&', Lore))) {
                        Lore = (String)var13.next();
                        if (i + 1 == 10) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-10.Require-Prefix"));
                        }

                        if (i + 1 == 11) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-11.Require-Prefix"));
                        }

                        if (i + 1 == 12) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-12.Require-Prefix"));
                        }

                        if (i + 1 == 13) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-13.Require-Prefix"));
                        }

                        if (i + 1 == 14) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-14.Require-Prefix"));
                        }

                        if (i + 1 == 15) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-15.Require-Prefix"));
                        }

                        if (i + 1 == 16) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-16.Require-Prefix"));
                        }

                        if (i + 1 == 17) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-17.Require-Prefix"));
                        }

                        if (i + 1 == 18) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-18.Require-Prefix"));
                        }
                    }

                    BackgroundMeta.setLore(BackgroundLore);
                    Background.setItemMeta(BackgroundMeta);
                    inv.setItem(i, Background);
                } else if (i >= 18 && i <= 26) {
                    Name = Wardrobe.ConfigData.getConfig().getString("Locked-Slot.Leggings-Slot.Name").replace("%Slot%", Integer.toString(i - 8));
                    BackgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Name));
                    BackgroundLore = new ArrayList();

                    for(var13 = Wardrobe.ConfigData.getConfig().getStringList("Locked-Slot.Leggings-Slot.Lore").iterator(); var13.hasNext(); BackgroundLore.add(ChatColor.translateAlternateColorCodes('&', Lore))) {
                        Lore = (String)var13.next();
                        if (i - 8 == 10) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-10.Require-Prefix"));
                        }

                        if (i - 8 == 11) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-11.Require-Prefix"));
                        }

                        if (i - 8 == 12) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-12.Require-Prefix"));
                        }

                        if (i - 8 == 13) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-13.Require-Prefix"));
                        }

                        if (i - 8 == 14) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-14.Require-Prefix"));
                        }

                        if (i - 8 == 15) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-15.Require-Prefix"));
                        }

                        if (i - 8 == 16) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-16.Require-Prefix"));
                        }

                        if (i - 8 == 17) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-17.Require-Prefix"));
                        }

                        if (i - 8 == 18) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-18.Require-Prefix"));
                        }
                    }

                    BackgroundMeta.setLore(BackgroundLore);
                    Background.setItemMeta(BackgroundMeta);
                    inv.setItem(i, Background);
                } else if (i >= 27 && i <= 35) {
                    Name = Wardrobe.ConfigData.getConfig().getString("Locked-Slot.Boots-Slot.Name").replace("%Slot%", Integer.toString(i - 17));
                    BackgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Name));
                    BackgroundLore = new ArrayList();

                    for(var13 = Wardrobe.ConfigData.getConfig().getStringList("Locked-Slot.Boots-Slot.Lore").iterator(); var13.hasNext(); BackgroundLore.add(ChatColor.translateAlternateColorCodes('&', Lore))) {
                        Lore = (String)var13.next();
                        if (i - 17 == 10) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-10.Require-Prefix"));
                        }

                        if (i - 17 == 11) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-11.Require-Prefix"));
                        }

                        if (i - 17 == 12) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-12.Require-Prefix"));
                        }

                        if (i - 17 == 13) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-13.Require-Prefix"));
                        }

                        if (i - 17 == 14) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-14.Require-Prefix"));
                        }

                        if (i - 17 == 15) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-15.Require-Prefix"));
                        }

                        if (i - 17 == 16) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-16.Require-Prefix"));
                        }

                        if (i - 17 == 17) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-17.Require-Prefix"));
                        }

                        if (i - 17 == 18) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-18.Require-Prefix"));
                        }
                    }

                    BackgroundMeta.setLore(BackgroundLore);
                    Background.setItemMeta(BackgroundMeta);
                    inv.setItem(i, Background);
                } else if (i >= 36 && i <= 44) {
                    Button = new ItemStack(Material.DIRT);
                    ButtonMeta = Button.getItemMeta();
                    if (Ver.contains("1.8") || Ver.contains("1.9") || Ver.contains("1.10") || Ver.contains("1.11") || Ver.contains("1.12")) {
                        Name = "INK_SACK";
                        Button.setType(Material.valueOf(Name));
                        Button.setDurability((short)1);
                    }

                    if (Ver.contains("1.13")) {
                        Name = "ROSE_RED";
                        Button.setType(Material.valueOf(Name));
                    }

                    if (Ver.contains("1.14") || Ver.contains("1.15") || Ver.contains("1.16") || Ver.contains("1.17") || Ver.contains("1.18") || Ver.contains("1.19") || Ver.contains("1.20")) {
                        Name = "RED_DYE";
                        Button.setType(Material.valueOf(Name));
                    }

                    Name = Wardrobe.ConfigData.getConfig().getString("Locked-Slot.Locked-Button.Name").replace("%Slot%", Integer.toString(i - 26));
                    ButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Name));
                    ButtonLore = new ArrayList();

                    for(var8 = Wardrobe.ConfigData.getConfig().getStringList("Locked-Slot.Locked-Button.Lore").iterator(); var8.hasNext(); ButtonLore.add(ChatColor.translateAlternateColorCodes('&', Lore))) {
                        Lore = (String)var8.next();
                        if (i - 26 == 10) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-10.Require-Prefix"));
                        }

                        if (i - 26 == 11) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-11.Require-Prefix"));
                        }

                        if (i - 26 == 12) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-12.Require-Prefix"));
                        }

                        if (i - 26 == 13) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-13.Require-Prefix"));
                        }

                        if (i - 26 == 14) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-14.Require-Prefix"));
                        }

                        if (i - 26 == 15) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-15.Require-Prefix"));
                        }

                        if (i - 26 == 16) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-16.Require-Prefix"));
                        }

                        if (i - 26 == 17) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-17.Require-Prefix"));
                        }

                        if (i - 26 == 18) {
                            Lore = Lore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-18.Require-Prefix"));
                        }
                    }

                    ButtonMeta.setLore(ButtonLore);
                    Button.setItemMeta(ButtonMeta);
                    inv.setItem(i, Button);
                }
            }
        }

    }

    public static void CreateAvaiableSlotBackground(Inventory inv, String Name, Player p) {
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
                        inv.setItem(i, Background(i, Page1, Name));
                        ++CheckAmount;
                    } else {
                        var10002 = Wardrobe.Page_1.getConfig();
                        var10003 = p.getUniqueId().toString();
                        inv.setItem(i, var10002.getItemStack(var10003 + ".Slot-" + (i + 1) + ".Helmet"));
                    }

                    var10000 = Wardrobe.Page_1.getConfig();
                    var10001 = p.getUniqueId().toString();
                    if (var10000.getItemStack(var10001 + ".Slot-" + (i + 1) + ".Chestplate") == null) {
                        inv.setItem(i + 9, Background(i + 9, Page1, Name));
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
                        inv.setItem(i + 18, Background(i + 18, Page1, Name));
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
                        inv.setItem(i + 27, Background(i + 27, Page1, Name));
                        ++CheckAmount;
                    } else {
                        var7 = i + 27;
                        var10002 = Wardrobe.Page_1.getConfig();
                        var10003 = p.getUniqueId().toString();
                        inv.setItem(var7, var10002.getItemStack(var10003 + ".Slot-" + (i + 1) + ".Boots"));
                    }

                    if (CheckAmount == 4) {
                        inv.setItem(i + 36, CreateEmptyButton(i, Page1, Name));
                    }

                    var10000 = Wardrobe.Page_1.getConfig();
                    var10001 = p.getUniqueId().toString();
                    if (var10000.getString(var10001 + ".Slot-" + (i + 1) + ".Button") != null) {
                        var10000 = Wardrobe.Page_1.getConfig();
                        var10001 = p.getUniqueId().toString();
                        if (var10000.getString(var10001 + ".Slot-" + (i + 1) + ".Button").contains("Ready")) {
                            inv.setItem(i + 36, CreateReadyButton(i, Page1, Name));
                            continue;
                        }
                    }

                    var10000 = Wardrobe.Page_1.getConfig();
                    var10001 = p.getUniqueId().toString();
                    if (var10000.getString(var10001 + ".Slot-" + (i + 1) + ".Button") != null) {
                        var10000 = Wardrobe.Page_1.getConfig();
                        var10001 = p.getUniqueId().toString();
                        if (var10000.getString(var10001 + ".Slot-" + (i + 1) + ".Button").contains("Equipped")) {
                            inv.setItem(i + 36, CreateEquippedButton(i, Page1, Name));
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
                                    inv.setItem(x, Background(x, Page1, Name));
                                    ++CheckAmount;
                                } else {
                                    var10002 = Wardrobe.Page_1.getConfig();
                                    var10003 = p.getUniqueId().toString();
                                    inv.setItem(x, var10002.getItemStack(var10003 + ".Slot-" + (x + 1) + ".Helmet"));
                                }

                                var10000 = Wardrobe.Page_1.getConfig();
                                var10001 = p.getUniqueId().toString();
                                if (var10000.getItemStack(var10001 + ".Slot-" + (x + 1) + ".Chestplate") == null) {
                                    inv.setItem(x + 9, Background(x + 9, Page1, Name));
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
                                    inv.setItem(x + 18, Background(x + 18, Page1, Name));
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
                                    inv.setItem(x + 27, Background(x + 27, Page1, Name));
                                    ++CheckAmount;
                                } else {
                                    var7 = x + 27;
                                    var10002 = Wardrobe.Page_1.getConfig();
                                    var10003 = p.getUniqueId().toString();
                                    inv.setItem(var7, var10002.getItemStack(var10003 + ".Slot-" + (x + 1) + ".Boots"));
                                }

                                if (CheckAmount == 4) {
                                    inv.setItem(i + 36, CreateEmptyButton(i, Page1, Name));
                                }

                                var10000 = Wardrobe.Page_1.getConfig();
                                var10001 = p.getUniqueId().toString();
                                if (var10000.getString(var10001 + ".Slot-" + (i + 1) + ".Button") != null) {
                                    var10000 = Wardrobe.Page_1.getConfig();
                                    var10001 = p.getUniqueId().toString();
                                    if (var10000.getString(var10001 + ".Slot-" + (i + 1) + ".Button").contains("Ready")) {
                                        inv.setItem(i + 36, CreateReadyButton(i, Page1, Name));
                                        continue;
                                    }
                                }

                                var10000 = Wardrobe.Page_1.getConfig();
                                var10001 = p.getUniqueId().toString();
                                if (var10000.getString(var10001 + ".Slot-" + (i + 1) + ".Button") != null) {
                                    var10000 = Wardrobe.Page_1.getConfig();
                                    var10001 = p.getUniqueId().toString();
                                    if (var10000.getString(var10001 + ".Slot-" + (i + 1) + ".Button").contains("Equipped")) {
                                        inv.setItem(i + 36, CreateEquippedButton(i, Page1, Name));
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
                                inv.setItem(i, Background(i, Page1, Name));
                                ++CheckAmount;
                            } else {
                                var10002 = Wardrobe.Page_1.getConfig();
                                var10003 = p.getUniqueId().toString();
                                inv.setItem(i, var10002.getItemStack(var10003 + ".Slot-" + (i + 1) + ".Helmet"));
                            }

                            var10000 = Wardrobe.Page_1.getConfig();
                            var10001 = p.getUniqueId().toString();
                            if (var10000.getItemStack(var10001 + ".Slot-" + (i + 1) + ".Chestplate") == null) {
                                inv.setItem(i + 9, Background(i + 9, Page1, Name));
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
                                inv.setItem(i + 18, Background(i + 18, Page1, Name));
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
                                inv.setItem(i + 27, Background(i + 27, Page1, Name));
                                ++CheckAmount;
                            } else {
                                var7 = i + 27;
                                var10002 = Wardrobe.Page_1.getConfig();
                                var10003 = p.getUniqueId().toString();
                                inv.setItem(var7, var10002.getItemStack(var10003 + ".Slot-" + (i + 1) + ".Boots"));
                            }

                            if (CheckAmount == 4) {
                                inv.setItem(i + 36, CreateEmptyButton(i, Page1, Name));
                            }

                            var10000 = Wardrobe.Page_1.getConfig();
                            var10001 = p.getUniqueId().toString();
                            if (var10000.getString(var10001 + ".Slot-" + (i + 1) + ".Button") != null) {
                                var10000 = Wardrobe.Page_1.getConfig();
                                var10001 = p.getUniqueId().toString();
                                if (var10000.getString(var10001 + ".Slot-" + (i + 1) + ".Button").contains("Ready")) {
                                    inv.setItem(i + 36, CreateReadyButton(i, Page1, Name));
                                    continue;
                                }
                            }

                            var10000 = Wardrobe.Page_1.getConfig();
                            var10001 = p.getUniqueId().toString();
                            if (var10000.getString(var10001 + ".Slot-" + (i + 1) + ".Button") != null) {
                                var10000 = Wardrobe.Page_1.getConfig();
                                var10001 = p.getUniqueId().toString();
                                if (var10000.getString(var10001 + ".Slot-" + (i + 1) + ".Button").contains("Equipped")) {
                                    inv.setItem(i + 36, CreateEquippedButton(i, Page1, Name));
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
                        inv.setItem(i, Background(i, Page2, Name));
                        ++CheckAmount;
                    } else {
                        var10002 = Wardrobe.Page_2.getConfig();
                        var10003 = p.getUniqueId().toString();
                        inv.setItem(i, var10002.getItemStack(var10003 + ".Slot-" + (i + 10) + ".Helmet"));
                    }

                    var10000 = Wardrobe.Page_2.getConfig();
                    var10001 = p.getUniqueId().toString();
                    if (var10000.getItemStack(var10001 + ".Slot-" + (i + 10) + ".Chestplate") == null) {
                        inv.setItem(i + 9, Background(i + 9, Page2, Name));
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
                        inv.setItem(i + 18, Background(i + 18, Page2, Name));
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
                        inv.setItem(i + 27, Background(i + 27, Page2, Name));
                        ++CheckAmount;
                    } else {
                        var7 = i + 27;
                        var10002 = Wardrobe.Page_2.getConfig();
                        var10003 = p.getUniqueId().toString();
                        inv.setItem(var7, var10002.getItemStack(var10003 + ".Slot-" + (i + 10) + ".Boots"));
                    }

                    if (CheckAmount == 4) {
                        inv.setItem(i + 36, CreateEmptyButton(i, Page2, Name));
                    }

                    var10000 = Wardrobe.Page_2.getConfig();
                    var10001 = p.getUniqueId().toString();
                    if (var10000.getString(var10001 + ".Slot-" + (i + 10) + ".Button") != null) {
                        var10000 = Wardrobe.Page_2.getConfig();
                        var10001 = p.getUniqueId().toString();
                        if (var10000.getString(var10001 + ".Slot-" + (i + 10) + ".Button").contains("Ready")) {
                            inv.setItem(i + 36, CreateReadyButton(i, Page2, Name));
                            continue;
                        }
                    }

                    var10000 = Wardrobe.Page_2.getConfig();
                    var10001 = p.getUniqueId().toString();
                    if (var10000.getString(var10001 + ".Slot-" + (i + 10) + ".Button") != null) {
                        var10000 = Wardrobe.Page_2.getConfig();
                        var10001 = p.getUniqueId().toString();
                        if (var10000.getString(var10001 + ".Slot-" + (i + 10) + ".Button").contains("Equipped")) {
                            inv.setItem(i + 36, CreateEquippedButton(i, Page2, Name));
                        }
                    }
                } else {
                    for(CheckAmount = i + 11; CheckAmount <= 18; ++CheckAmount) {
                        if (p.hasPermission(Wardrobe.ConfigData.getConfig().getString("Slot-Permission.Slot-" + CheckAmount + ".Permission"))) {
                            CheckAmount = 0;
                            var10000 = Wardrobe.Page_2.getConfig();
                            var10001 = p.getUniqueId().toString();
                            if (var10000.getItemStack(var10001 + ".Slot-" + (i + 10) + ".Helmet") == null) {
                                inv.setItem(i, Background(i, Page2, Name));
                                ++CheckAmount;
                            } else {
                                var10002 = Wardrobe.Page_2.getConfig();
                                var10003 = p.getUniqueId().toString();
                                inv.setItem(i, var10002.getItemStack(var10003 + ".Slot-" + (i + 10) + ".Helmet"));
                            }

                            var10000 = Wardrobe.Page_2.getConfig();
                            var10001 = p.getUniqueId().toString();
                            if (var10000.getItemStack(var10001 + ".Slot-" + (i + 10) + ".Chestplate") == null) {
                                inv.setItem(i + 9, Background(i + 9, Page2, Name));
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
                                inv.setItem(i + 18, Background(i + 18, Page2, Name));
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
                                inv.setItem(i + 27, Background(i + 27, Page2, Name));
                                ++CheckAmount;
                            } else {
                                var7 = i + 27;
                                var10002 = Wardrobe.Page_2.getConfig();
                                var10003 = p.getUniqueId().toString();
                                inv.setItem(var7, var10002.getItemStack(var10003 + ".Slot-" + (i + 10) + ".Boots"));
                            }

                            if (CheckAmount == 4) {
                                inv.setItem(i + 36, CreateEmptyButton(i, Page2, Name));
                            }

                            var10000 = Wardrobe.Page_2.getConfig();
                            var10001 = p.getUniqueId().toString();
                            if (var10000.getString(var10001 + ".Slot-" + (i + 10) + ".Button") != null) {
                                var10000 = Wardrobe.Page_2.getConfig();
                                var10001 = p.getUniqueId().toString();
                                if (var10000.getString(var10001 + ".Slot-" + (i + 10) + ".Button").contains("Ready")) {
                                    inv.setItem(i + 36, CreateReadyButton(i, Page2, Name));
                                    continue;
                                }
                            }

                            var10000 = Wardrobe.Page_2.getConfig();
                            var10001 = p.getUniqueId().toString();
                            if (var10000.getString(var10001 + ".Slot-" + (i + 10) + ".Button") != null) {
                                var10000 = Wardrobe.Page_2.getConfig();
                                var10001 = p.getUniqueId().toString();
                                if (var10000.getString(var10001 + ".Slot-" + (i + 10) + ".Button").contains("Equipped")) {
                                    inv.setItem(i + 36, CreateEquippedButton(i, Page2, Name));
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    public static ItemStack Background(int Slot, Inventory inv, String Title) {
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

    public static ItemStack CreateEmptyButton(int Slot, Inventory inv, String Title) {
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

    public static ItemStack CreateReadyButton(int Slot, Inventory inv, String Title) {
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

    public static ItemStack CreateEquippedButton(int Slot, Inventory inv, String Title) {
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

    public static void CreateGoBackAndCloseButton(Inventory inv) {
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

    public static void CreateNextAndPreviousButton(Inventory inv) {
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
