package me.plainoldmoose.wardrobe.Listener;

import me.plainoldmoose.wardrobe.GUI.CheckPlayerGUI;
import me.plainoldmoose.wardrobe.GUI.WardrobeGUI;
import me.plainoldmoose.wardrobe.Wardrobe;
import me.plainoldmoose.wardrobe.Work.GUIWork;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class WardrobeListener implements Listener {
    public Wardrobe plugin;
    public String Ver = Bukkit.getServer().getVersion();

    public WardrobeListener(Wardrobe plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(WardrobeGUI.Page1Name) || e.getView().getTitle().equals(WardrobeGUI.Page2Name)) {
            if (e.getClickedInventory() == null) {
                return;
            }

            Player p;
            if (e.getClickedInventory().getType() == InventoryType.PLAYER) {
                if (e.getClick() != ClickType.SHIFT_LEFT && e.getClick() != ClickType.SHIFT_RIGHT) {
                    return;
                }

                if (e.getCurrentItem() == null) {
                    return;
                }

                p = (Player)e.getWhoClicked();
                if (GUIWork.ShiftClick(e.getSlot(), e.getInventory(), p, e.getCurrentItem(), e.getView().getTitle())) {
                    e.getClickedInventory().setItem(e.getSlot(), (ItemStack)null);
                    p.updateInventory();
                    return;
                }

                return;
            }

            p = (Player)e.getWhoClicked();
            e.setCancelled(true);
            if (e.getSlot() == Wardrobe.ConfigData.getFileConfig().getInt("Next-Page-Button.Slot") && Wardrobe.ConfigData.getFileConfig().getBoolean("Next-Page-Button.Enable") && !e.getInventory().getItem(e.getSlot()).getType().toString().contains("STAINED_GLASS_PANE")) {
                if (e.getWhoClicked().getItemOnCursor() != null) {
                    e.getWhoClicked().getInventory().addItem(new ItemStack[]{e.getWhoClicked().getItemOnCursor().clone()});
                    e.getWhoClicked().setItemOnCursor((ItemStack)null);
                }

                WardrobeGUI.createWardrobePage2(p);
            }

            if (e.getSlot() == Wardrobe.ConfigData.getFileConfig().getInt("Previous-Page-Button.Slot") && Wardrobe.ConfigData.getFileConfig().getBoolean("Previous-Page-Button.Enable") && !e.getInventory().getItem(e.getSlot()).getType().toString().contains("STAINED_GLASS_PANE")) {
                if (e.getWhoClicked().getItemOnCursor() != null) {
                    e.getWhoClicked().getInventory().addItem(new ItemStack[]{e.getWhoClicked().getItemOnCursor().clone()});
                    e.getWhoClicked().setItemOnCursor((ItemStack)null);
                }

                WardrobeGUI.createWardrobePage1(p);
            }

            if (e.getSlot() == Wardrobe.ConfigData.getFileConfig().getInt("Go-Back-Button.Slot") && Wardrobe.ConfigData.getFileConfig().getBoolean("Go-Back-Button.Enable") && !e.getInventory().getItem(e.getSlot()).getType().toString().contains("STAINED_GLASS_PANE")) {
                if (e.getWhoClicked().getItemOnCursor() != null) {
                    e.getWhoClicked().getInventory().addItem(new ItemStack[]{e.getWhoClicked().getItemOnCursor().clone()});
                    e.getWhoClicked().setItemOnCursor((ItemStack)null);
                }

                p.performCommand(Wardrobe.ConfigData.getFileConfig().getString("Go-Back-Button.Command"));
            }

            if (e.getSlot() == Wardrobe.ConfigData.getFileConfig().getInt("Close-Button.Slot") && Wardrobe.ConfigData.getFileConfig().getBoolean("Close-Button.Enable") && !e.getInventory().getItem(e.getSlot()).getType().toString().contains("STAINED_GLASS_PANE")) {
                if (e.getWhoClicked().getItemOnCursor() != null) {
                    e.getWhoClicked().getInventory().addItem(new ItemStack[]{e.getWhoClicked().getItemOnCursor().clone()});
                    e.getWhoClicked().setItemOnCursor((ItemStack)null);
                }

                p.closeInventory();
            }

            ChatColor var10001;
            ItemStack PresentButton;
            String ButtonCheck;
            String sound;
            if (e.getClick() != ClickType.SHIFT_LEFT && e.getClick() != ClickType.SHIFT_RIGHT) {
                PresentButton = new ItemStack(Material.DIRT);
                if (e.getSlot() >= 0 && e.getSlot() <= 8) {
                    PresentButton = e.getClickedInventory().getItem(e.getSlot() + 36);
                } else if (e.getSlot() >= 9 && e.getSlot() <= 17) {
                    PresentButton = e.getClickedInventory().getItem(e.getSlot() + 27);
                } else if (e.getSlot() >= 18 && e.getSlot() <= 26) {
                    PresentButton = e.getClickedInventory().getItem(e.getSlot() + 18);
                } else if (e.getSlot() >= 27 && e.getSlot() <= 35) {
                    PresentButton = e.getClickedInventory().getItem(e.getSlot() + 9);
                }

                ButtonCheck = "";
                if (this.Ver.contains("1.8") || this.Ver.contains("1.9") || this.Ver.contains("1.10") || this.Ver.contains("1.11") || this.Ver.contains("1.12")) {
                    ButtonCheck = PresentButton.getData().toString();
                }

                if (this.Ver.contains("1.13") || this.Ver.contains("1.14") || this.Ver.contains("1.15") || this.Ver.contains("1.16") || this.Ver.contains("1.17") || this.Ver.contains("1.18") || this.Ver.contains("1.19") || this.Ver.contains("1.20")) {
                    ButtonCheck = PresentButton.getType().toString();
                }

                if (ButtonCheck.contains("LIME_DYE") || ButtonCheck.contains("LIME DYE")) {
                    var10001 = ChatColor.RED;
                    p.sendMessage(var10001 + ChatColor.translateAlternateColorCodes('&', Wardrobe.ConfigData.getFileConfig().getString("Wardrobe_Message.Modify_Armor_Denied")));
                    sound = "";
                    if (this.Ver.contains("1.8")) {
                        sound = "VILLAGER_NO";
                    }

                    if (this.Ver.contains("1.9") || this.Ver.contains("1.10") || this.Ver.contains("1.11") || this.Ver.contains("1.12") || this.Ver.contains("1.13") || this.Ver.contains("1.14") || this.Ver.contains("1.15") || this.Ver.contains("1.16") || this.Ver.contains("1.17") || this.Ver.contains("1.18") || this.Ver.contains("1.19") || this.Ver.contains("1.20")) {
                        sound = "ENTITY_VILLAGER_NO";
                    }

                    p.playSound(p.getLocation(), Sound.valueOf(sound), 1.0F, 1.0F);
                    return;
                }

                ItemStack ClickedItem = e.getCurrentItem();
                ItemStack ItemOnCursor = p.getItemOnCursor();
                GUIWork.SetAllowItem(e.getSlot(), e.getInventory(), p, ClickedItem, ItemOnCursor, e.getView().getTitle());
                p.updateInventory();
            } else {
                PresentButton = new ItemStack(Material.DIRT);
                if (e.getSlot() >= 0 && e.getSlot() <= 8) {
                    PresentButton = e.getClickedInventory().getItem(e.getSlot() + 36);
                } else if (e.getSlot() >= 9 && e.getSlot() <= 17) {
                    PresentButton = e.getClickedInventory().getItem(e.getSlot() + 27);
                } else if (e.getSlot() >= 18 && e.getSlot() <= 26) {
                    PresentButton = e.getClickedInventory().getItem(e.getSlot() + 18);
                } else if (e.getSlot() >= 27 && e.getSlot() <= 35) {
                    PresentButton = e.getClickedInventory().getItem(e.getSlot() + 9);
                }

                ButtonCheck = "";
                if (this.Ver.contains("1.8") || this.Ver.contains("1.9") || this.Ver.contains("1.10") || this.Ver.contains("1.11") || this.Ver.contains("1.12")) {
                    ButtonCheck = PresentButton.getData().toString();
                }

                if (this.Ver.contains("1.13") || this.Ver.contains("1.14") || this.Ver.contains("1.15") || this.Ver.contains("1.16") || this.Ver.contains("1.17") || this.Ver.contains("1.18") || this.Ver.contains("1.19") || this.Ver.contains("1.20")) {
                    ButtonCheck = PresentButton.getType().toString();
                }

                if (ButtonCheck.contains("LIME_DYE") || ButtonCheck.contains("LIME DYE")) {
                    var10001 = ChatColor.RED;
                    p.sendMessage(var10001 + ChatColor.translateAlternateColorCodes('&', Wardrobe.ConfigData.getFileConfig().getString("Wardrobe_Message.Modify_Armor_Denied")));
                    sound = "";
                    if (this.Ver.contains("1.8")) {
                        sound = "VILLAGER_NO";
                    }

                    if (this.Ver.contains("1.9") || this.Ver.contains("1.10") || this.Ver.contains("1.11") || this.Ver.contains("1.12") || this.Ver.contains("1.13") || this.Ver.contains("1.14") || this.Ver.contains("1.15") || this.Ver.contains("1.16") || this.Ver.contains("1.17") || this.Ver.contains("1.18") || this.Ver.contains("1.19") || this.Ver.contains("1.20")) {
                        sound = "ENTITY_VILLAGER_NO";
                    }

                    p.playSound(p.getLocation(), Sound.valueOf(sound), 1.0F, 1.0F);
                    return;
                }

                GUIWork.ShiftClickInv(p, e.getInventory(), e.getSlot(), e.getView().getTitle());
            }
        }

    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (!CheckPlayerGUI.onOpen) {
            int i;
            String ButtonCheck;
            if (e.getView().getTitle().equals(WardrobeGUI.Page1Name)) {
                Wardrobe.Page_1.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".name", e.getPlayer().getName());

                for(i = 0; i <= 8; ++i) {
                    if (e.getInventory().getItem(i).getType().toString().contains("STAINED_GLASS_PANE")) {
                        Wardrobe.Page_1.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".Slot-" + (i + 1) + ".Helmet", "none");
                    } else {
                        Wardrobe.Page_1.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".Slot-" + (i + 1) + ".Helmet", e.getInventory().getItem(i));
                    }

                    if (e.getInventory().getItem(i + 9).getType().toString().contains("STAINED_GLASS_PANE")) {
                        Wardrobe.Page_1.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".Slot-" + (i + 1) + ".Chestplate", "none");
                    } else {
                        Wardrobe.Page_1.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".Slot-" + (i + 1) + ".Chestplate", e.getInventory().getItem(i + 9));
                    }

                    if (e.getInventory().getItem(i + 18).getType().toString().contains("STAINED_GLASS_PANE")) {
                        Wardrobe.Page_1.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".Slot-" + (i + 1) + ".Leggings", "none");
                    } else {
                        Wardrobe.Page_1.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".Slot-" + (i + 1) + ".Leggings", e.getInventory().getItem(i + 18));
                    }

                    if (e.getInventory().getItem(i + 27).getType().toString().contains("STAINED_GLASS_PANE")) {
                        Wardrobe.Page_1.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".Slot-" + (i + 1) + ".Boots", "none");
                    } else {
                        Wardrobe.Page_1.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".Slot-" + (i + 1) + ".Boots", e.getInventory().getItem(i + 27));
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
                                Wardrobe.Page_1.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".Slot-" + (i + 1) + ".Button", "Locked");
                            } else {
                                Wardrobe.Page_1.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".Slot-" + (i + 1) + ".Button", "Ready");
                            }
                        } else {
                            Wardrobe.Page_1.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".Slot-" + (i + 1) + ".Button", "Empty");
                        }
                    } else {
                        Wardrobe.Page_1.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".Slot-" + (i + 1) + ".Button", "Equipped");
                    }
                }

                Wardrobe.Page_1.saveConfig();
                Wardrobe.Page_1.reloadConfig();
            } else if (e.getView().getTitle().equals(WardrobeGUI.Page2Name)) {
                Wardrobe.Page_2.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".name", e.getPlayer().getName());

                for(i = 0; i <= 8; ++i) {
                    if (e.getInventory().getItem(i).getType().toString().contains("STAINED_GLASS_PANE")) {
                        Wardrobe.Page_2.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".Slot-" + (i + 10) + ".Helmet", "none");
                    } else {
                        Wardrobe.Page_2.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".Slot-" + (i + 10) + ".Helmet", e.getInventory().getItem(i));
                    }

                    if (e.getInventory().getItem(i + 9).getType().toString().contains("STAINED_GLASS_PANE")) {
                        Wardrobe.Page_2.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".Slot-" + (i + 10) + ".Chestplate", "none");
                    } else {
                        Wardrobe.Page_2.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".Slot-" + (i + 10) + ".Chestplate", e.getInventory().getItem(i + 9));
                    }

                    if (e.getInventory().getItem(i + 18).getType().toString().contains("STAINED_GLASS_PANE")) {
                        Wardrobe.Page_2.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".Slot-" + (i + 10) + ".Leggings", "none");
                    } else {
                        Wardrobe.Page_2.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".Slot-" + (i + 10) + ".Leggings", e.getInventory().getItem(i + 18));
                    }

                    if (e.getInventory().getItem(i + 27).getType().toString().contains("STAINED_GLASS_PANE")) {
                        Wardrobe.Page_2.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".Slot-" + (i + 10) + ".Boots", "none");
                    } else {
                        Wardrobe.Page_2.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".Slot-" + (i + 10) + ".Boots", e.getInventory().getItem(i + 27));
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
                                Wardrobe.Page_2.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".Slot-" + (i + 10) + ".Button", "Locked");
                            } else {
                                Wardrobe.Page_2.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".Slot-" + (i + 10) + ".Button", "Ready");
                            }
                        } else {
                            Wardrobe.Page_2.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".Slot-" + (i + 10) + ".Button", "Empty");
                        }
                    } else {
                        Wardrobe.Page_2.getFileConfig().set(e.getPlayer().getUniqueId().toString() + ".Slot-" + (i + 10) + ".Button", "Equipped");
                    }
                }

                Wardrobe.Page_2.saveConfig();
                Wardrobe.Page_2.reloadConfig();
            }
        }

    }
}
