package me.plainoldmoose.wardrobe.Listener;

import me.plainoldmoose.wardrobe.GUI.CheckPlayerGUI;
import me.plainoldmoose.wardrobe.GUI.WardrobeGUI;
import me.plainoldmoose.wardrobe.Wardrobe;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class CheckPlayerGUIListener implements Listener {
    public Wardrobe plugin;
    public String Ver = Bukkit.getServer().getVersion();

    public CheckPlayerGUIListener(Wardrobe plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (CheckPlayerGUI.onOpen) {
            int i;
            String ButtonCheck;
            if (e.getView().getTitle().equals(WardrobeGUI.Page1Name)) {
                Wardrobe.Page_1.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".name", CheckPlayerGUI.CheckPlayerMain.getName());

                for(i = 0; i <= 8; ++i) {
                    if (e.getInventory().getItem(i).getType().toString().contains("STAINED_GLASS_PANE")) {
                        Wardrobe.Page_1.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".Slot-" + (i + 1) + ".Helmet", "none");
                    } else {
                        Wardrobe.Page_1.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".Slot-" + (i + 1) + ".Helmet", e.getInventory().getItem(i));
                    }

                    if (e.getInventory().getItem(i + 9).getType().toString().contains("STAINED_GLASS_PANE")) {
                        Wardrobe.Page_1.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".Slot-" + (i + 1) + ".Chestplate", "none");
                    } else {
                        Wardrobe.Page_1.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".Slot-" + (i + 1) + ".Chestplate", e.getInventory().getItem(i + 9));
                    }

                    if (e.getInventory().getItem(i + 18).getType().toString().contains("STAINED_GLASS_PANE")) {
                        Wardrobe.Page_1.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".Slot-" + (i + 1) + ".Leggings", "none");
                    } else {
                        Wardrobe.Page_1.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".Slot-" + (i + 1) + ".Leggings", e.getInventory().getItem(i + 18));
                    }

                    if (e.getInventory().getItem(i + 27).getType().toString().contains("STAINED_GLASS_PANE")) {
                        Wardrobe.Page_1.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".Slot-" + (i + 1) + ".Boots", "none");
                    } else {
                        Wardrobe.Page_1.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".Slot-" + (i + 1) + ".Boots", e.getInventory().getItem(i + 27));
                    }

                    ButtonCheck = "";
                    if (this.Ver.contains("1.8") || this.Ver.contains("1.9") || this.Ver.contains("1.10") || this.Ver.contains("1.11") || this.Ver.contains("1.12")) {
                        ButtonCheck = e.getInventory().getItem(i + 36).getData().toString();
                    }

                    if (this.Ver.contains("1.13") || this.Ver.contains("1.14") || this.Ver.contains("1.15") || this.Ver.contains("1.16") || this.Ver.contains("1.17") || this.Ver.contains("1.18") || this.Ver.contains("1.19") || this.Ver.contains("1.20")) {
                        ButtonCheck = e.getInventory().getItem(i + 36).getType().toString();
                    }

                    if (!ButtonCheck.contains("LIME_DYE") && !ButtonCheck.contains("LIME DYE")) {
                        if (!ButtonCheck.contains("GRAY_DYE") && !ButtonCheck.contains("GRAY DYE")) {
                            if (!ButtonCheck.contains("PINK_DYE") && !ButtonCheck.contains("PINK DYE")) {
                                Wardrobe.Page_1.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".Slot-" + (i + 1) + ".Button", "Locked");
                            } else {
                                Wardrobe.Page_1.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".Slot-" + (i + 1) + ".Button", "Ready");
                            }
                        } else {
                            Wardrobe.Page_1.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".Slot-" + (i + 1) + ".Button", "Empty");
                        }
                    } else {
                        Wardrobe.Page_1.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".Slot-" + (i + 1) + ".Button", "Equipped");
                    }
                }

                Wardrobe.Page_1.saveConfig();
                Wardrobe.Page_1.ReloadConfig();
            } else if (e.getView().getTitle().equals(WardrobeGUI.Page2Name)) {
                Wardrobe.Page_2.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".name", CheckPlayerGUI.CheckPlayerMain.getName());

                for(i = 0; i <= 8; ++i) {
                    if (e.getInventory().getItem(i).getType().toString().contains("STAINED_GLASS_PANE")) {
                        Wardrobe.Page_2.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".Slot-" + (i + 10) + ".Helmet", "none");
                    } else {
                        Wardrobe.Page_2.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".Slot-" + (i + 10) + ".Helmet", e.getInventory().getItem(i));
                    }

                    if (e.getInventory().getItem(i + 9).getType().toString().contains("STAINED_GLASS_PANE")) {
                        Wardrobe.Page_2.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".Slot-" + (i + 10) + ".Chestplate", "none");
                    } else {
                        Wardrobe.Page_2.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".Slot-" + (i + 10) + ".Chestplate", e.getInventory().getItem(i + 9));
                    }

                    if (e.getInventory().getItem(i + 18).getType().toString().contains("STAINED_GLASS_PANE")) {
                        Wardrobe.Page_2.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".Slot-" + (i + 10) + ".Leggings", "none");
                    } else {
                        Wardrobe.Page_2.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".Slot-" + (i + 10) + ".Leggings", e.getInventory().getItem(i + 18));
                    }

                    if (e.getInventory().getItem(i + 27).getType().toString().contains("STAINED_GLASS_PANE")) {
                        Wardrobe.Page_2.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".Slot-" + (i + 10) + ".Boots", "none");
                    } else {
                        Wardrobe.Page_2.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".Slot-" + (i + 10) + ".Boots", e.getInventory().getItem(i + 27));
                    }

                    ButtonCheck = "";
                    if (this.Ver.contains("1.8") || this.Ver.contains("1.9") || this.Ver.contains("1.10") || this.Ver.contains("1.11") || this.Ver.contains("1.12")) {
                        ButtonCheck = e.getInventory().getItem(i + 36).getData().toString();
                    }

                    if (this.Ver.contains("1.13") || this.Ver.contains("1.14") || this.Ver.contains("1.15") || this.Ver.contains("1.16") || this.Ver.contains("1.17") || this.Ver.contains("1.18") || this.Ver.contains("1.19") || this.Ver.contains("1.20")) {
                        ButtonCheck = e.getInventory().getItem(i + 36).getType().toString();
                    }

                    if (!ButtonCheck.contains("LIME_DYE") && !ButtonCheck.contains("LIME DYE")) {
                        if (!ButtonCheck.contains("GRAY_DYE") && !ButtonCheck.contains("GRAY DYE")) {
                            if (!ButtonCheck.contains("PINK_DYE") && !ButtonCheck.contains("PINK DYE")) {
                                Wardrobe.Page_2.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".Slot-" + (i + 10) + ".Button", "Locked");
                            } else {
                                Wardrobe.Page_2.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".Slot-" + (i + 10) + ".Button", "Ready");
                            }
                        } else {
                            Wardrobe.Page_2.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".Slot-" + (i + 10) + ".Button", "Empty");
                        }
                    } else {
                        Wardrobe.Page_2.getConfig().set(CheckPlayerGUI.CheckPlayerMain.getUniqueId().toString() + ".Slot-" + (i + 10) + ".Button", "Equipped");
                    }
                }

                Wardrobe.Page_2.saveConfig();
                Wardrobe.Page_2.ReloadConfig();
            }

            CheckPlayerGUI.onOpen = false;
        }

    }
}