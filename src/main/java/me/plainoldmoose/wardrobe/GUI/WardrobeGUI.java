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

import java.util.*;

public class WardrobeGUI {
    public static String Ver = Bukkit.getServer().getVersion();
    public static Inventory Page1;
    public static Inventory Page2;
    public static String Page1Name = "";
    public static String Page2Name = "";

    /**
     * Creates the first page of the wardrobe inventory for the player.
     * @param p The player for whom the wardrobe inventory is created.
     */
    public static void createWardrobePage1(Player p) {
        // Get configuration data for the wardrobe
        FileConfiguration config = Wardrobe.ConfigData.getFileConfig();
        String Name = ChatColor.translateAlternateColorCodes('&', config.getString("Title")) + " (1/2)";

        // Create the inventory for the first page
        Inventory wardrobePage1 = Bukkit.createInventory(p, 54, Name);

        // Set the name of the first page
        Page1Name = Name;

        // Create background item
        ItemStack background = new ItemStack(Material.DIRT);
        ItemMeta backgroundMeta = background.getItemMeta();
        String mat;

        // Determine appropriate material based on Minecraft version
        if (!Ver.contains("1.8") && !Ver.contains("1.9") && !Ver.contains("1.10") && !Ver.contains("1.11") && !Ver.contains("1.12")) {
            if (Ver.contains("1.13") || Ver.contains("1.14") || Ver.contains("1.15") || Ver.contains("1.16") || Ver.contains("1.17") || Ver.contains("1.18") || Ver.contains("1.19") || Ver.contains("1.20")) {
                mat = "BLACK_STAINED_GLASS_PANE";
                background.setType(Material.valueOf(mat));
            }
        } else {
            mat = "STAINED_GLASS_PANE";
            background.setType(Material.valueOf(mat));
            background.setDurability((short) 15);
        }

        // Set display name of the background item
        backgroundMeta.setDisplayName(" ");
        background.setItemMeta(backgroundMeta);

        // Fill the inventory with background items
        for (int i = 45; i <= 53; ++i) {
            wardrobePage1.setItem(i, background);
        }

        // Add navigation buttons and background elements
        createCloseButton(wardrobePage1);
        Page1 = wardrobePage1;
        createNextAndPreviousButton(wardrobePage1);
        createBaseBackground(wardrobePage1);
        createAvailableSlotBackground(wardrobePage1, Name, p);

        // Check and update equipped items
        for (int i = 36; i <= 44; ++i) {
            String path = "";
            if (Ver.contains("1.8") || Ver.contains("1.9") || Ver.contains("1.10") || Ver.contains("1.11") || Ver.contains("1.12")) {
                path = wardrobePage1.getItem(i).getData().toString();
            }

            if (Ver.contains("1.13") || Ver.contains("1.14") || Ver.contains("1.15") || Ver.contains("1.16") || Ver.contains("1.17") || Ver.contains("1.18") || Ver.contains("1.19") || Ver.contains("1.20")) {
                path = wardrobePage1.getItem(i).getType().toString();
            }

            if (path.contains("PINK_DYE") || path.contains("PINK DYE")) {
                String checkSlot1 = wardrobePage1.getItem(i - 36).getType().toString();
                String checkSlot2 = wardrobePage1.getItem(i - 27).getType().toString();
                String checkSlot3 = wardrobePage1.getItem(i - 18).getType().toString();
                String checkSlot4 = wardrobePage1.getItem(i - 9).getType().toString();
                if (checkSlot1.contains("STAINED_GLASS_PANE") && checkSlot2.contains("STAINED_GLASS_PANE") && checkSlot3.contains("STAINED_GLASS_PANE") && checkSlot4.contains("STAINED_GLASS_PANE")) {
                    wardrobePage1.setItem(i, createEmptyButton(i - 36, wardrobePage1, Name));
                }
            } else if (path.contains("LIME_DYE") || path.contains("LIME DYE")) {
                GUIWork.CheckArmor(p, wardrobePage1, i, Name);
            }
        }
        // Open the inventory for the player
        p.openInventory(wardrobePage1);
    }

    /**
     * Creates the second page of the wardrobe inventory for the player.
     * @param p The player for whom the wardrobe inventory is created.
     */
    public static void createWardrobePage2(Player p) {
        // Get configuration data for the wardrobe
        FileConfiguration config = Wardrobe.ConfigData.getFileConfig();
        String Name = ChatColor.translateAlternateColorCodes('&', config.getString("Title")) + " (2/2)";

        // Create the inventory for the second page
        Inventory wardrobePage2 = Bukkit.createInventory(p, 54, Name);

        // Set the name of the second page
        Page2Name = Name;

        // Create background item
        ItemStack background = new ItemStack(Material.DIRT);
        ItemMeta backgroundMeta = background.getItemMeta();
        String mat;

        // Determine appropriate material based on Minecraft version
        if (!Ver.contains("1.8") && !Ver.contains("1.9") && !Ver.contains("1.10") && !Ver.contains("1.11") && !Ver.contains("1.12")) {
            if (Ver.contains("1.13") || Ver.contains("1.14") || Ver.contains("1.15") || Ver.contains("1.16") || Ver.contains("1.17") || Ver.contains("1.18") || Ver.contains("1.19") || Ver.contains("1.20")) {
                mat = "BLACK_STAINED_GLASS_PANE";
                background.setType(Material.valueOf(mat));
            }
        } else {
            mat = "STAINED_GLASS_PANE";
            background.setType(Material.valueOf(mat));
            background.setDurability((short) 15);
        }

        // Set display name of the background item
        backgroundMeta.setDisplayName(" ");
        background.setItemMeta(backgroundMeta);

        // Fill the inventory with background items
        for (int i = 45; i <= 53; ++i) {
            wardrobePage2.setItem(i, background);
        }

        // Add navigation buttons and background elements
        createCloseButton(wardrobePage2);
        Page2 = wardrobePage2;
        createNextAndPreviousButton(wardrobePage2);
        createBaseBackground(wardrobePage2);
        createAvailableSlotBackground(wardrobePage2, Name, p);

        // Check and update equipped items
        for (int i = 36; i <= 44; ++i) {
            String buttonCheck = "";
            if (Ver.contains("1.8") || Ver.contains("1.9") || Ver.contains("1.10") || Ver.contains("1.11") || Ver.contains("1.12")) {
                buttonCheck = wardrobePage2.getItem(i).getData().toString();
            }

            if (Ver.contains("1.13") || Ver.contains("1.14") || Ver.contains("1.13") || Ver.contains("1.14") || Ver.contains("1.15") || Ver.contains("1.16") || Ver.contains("1.17") || Ver.contains("1.18") || Ver.contains("1.19") || Ver.contains("1.20")) {
                buttonCheck = wardrobePage2.getItem(i).getType().toString();
            }

            if (buttonCheck.contains("PINK_DYE") || buttonCheck.contains("PINK DYE")) {
                String checkSlot1 = wardrobePage2.getItem(i - 36).getType().toString();
                String checkSlot2 = wardrobePage2.getItem(i - 27).getType().toString();
                String checkSlot3 = wardrobePage2.getItem(i - 18).getType().toString();
                String checkSlot4 = wardrobePage2.getItem(i - 9).getType().toString();
                if (checkSlot1.contains("STAINED_GLASS_PANE") && checkSlot2.contains("STAINED_GLASS_PANE") && checkSlot3.contains("STAINED_GLASS_PANE") && checkSlot4.contains("STAINED_GLASS_PANE")) {
                    wardrobePage2.setItem(i, createEmptyButton(i - 36, wardrobePage2, Name));
                }
            } else if (buttonCheck.contains("LIME_DYE") || buttonCheck.contains("LIME DYE")) {
                GUIWork.CheckArmor(p, wardrobePage2, i, Name);
            }
        }

        // Open the inventory for the player
        p.openInventory(wardrobePage2);
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
                    name = Wardrobe.ConfigData.getFileConfig().getString("Locked-Slot.Helmet-Slot.Name").replace("%Slot%", Integer.toString(i + 1));
                    backgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                    backgroundLore = new ArrayList<>();
                    for (String slotLore : Wardrobe.ConfigData.getFileConfig().getStringList("Locked-Slot.Helmet-Slot.Lore")) {
                        lore = slotLore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getFileConfig().getString("Slot-Permission.Slot-" + (i + 1) + ".Require-Prefix"));
                        backgroundLore.add(ChatColor.translateAlternateColorCodes('&', lore));
                    }
                    backgroundMeta.setLore(backgroundLore);
                    background.setItemMeta(backgroundMeta);
                    inv.setItem(i, background);
                } else if (i >= 9 && i <= 17) {
                    // Chestplate slots
                    name = Wardrobe.ConfigData.getFileConfig().getString("Locked-Slot.Chestplate-Slot.Name").replace("%Slot%", Integer.toString(i - 8));
                    backgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                    backgroundLore = new ArrayList<>();
                    for (String slotLore : Wardrobe.ConfigData.getFileConfig().getStringList("Locked-Slot.Chestplate-Slot.Lore")) {
                        lore = slotLore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getFileConfig().getString("Slot-Permission.Slot-" + (i - 8) + ".Require-Prefix"));
                        backgroundLore.add(ChatColor.translateAlternateColorCodes('&', lore));
                    }
                    backgroundMeta.setLore(backgroundLore);
                    background.setItemMeta(backgroundMeta);
                    inv.setItem(i, background);
                } else if (i >= 18 && i <= 26) {
                    // Leggings slots
                    name = Wardrobe.ConfigData.getFileConfig().getString("Locked-Slot.Leggings-Slot.Name").replace("%Slot%", Integer.toString(i - 17));
                    backgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                    backgroundLore = new ArrayList<>();
                    for (String slotLore : Wardrobe.ConfigData.getFileConfig().getStringList("Locked-Slot.Leggings-Slot.Lore")) {
                        lore = slotLore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getFileConfig().getString("Slot-Permission.Slot-" + (i - 17) + ".Require-Prefix"));
                        backgroundLore.add(ChatColor.translateAlternateColorCodes('&', lore));
                    }
                    backgroundMeta.setLore(backgroundLore);
                    background.setItemMeta(backgroundMeta);
                    inv.setItem(i, background);
                } else if (i >= 27 && i <= 35) {
                    // Boots slots
                    name = Wardrobe.ConfigData.getFileConfig().getString("Locked-Slot.Boots-Slot.Name").replace("%Slot%", Integer.toString(i - 26));
                    backgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                    backgroundLore = new ArrayList<>();
                    for (String slotLore : Wardrobe.ConfigData.getFileConfig().getStringList("Locked-Slot.Boots-Slot.Lore")) {
                        lore = slotLore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getFileConfig().getString("Slot-Permission.Slot-" + (i - 26) + ".Require-Prefix"));
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
                    name = Wardrobe.ConfigData.getFileConfig().getString("Locked-Slot.Locked-Button.Name").replace("%Slot%", Integer.toString(i - 26));
                    buttonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                    buttonLore = new ArrayList<>();
                    for (String slotLore : Wardrobe.ConfigData.getFileConfig().getStringList("Locked-Slot.Locked-Button.Lore")) {
                        lore = slotLore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getFileConfig().getString("Slot-Permission.Slot-" + (i - 26) + ".Require-Prefix"));
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
                    name = Wardrobe.ConfigData.getFileConfig().getString("Locked-Slot.Helmet-Slot.Name").replace("%Slot%", Integer.toString(i + 10));
                    backgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                    backgroundLore = new ArrayList<>();
                    for (String slotLore : Wardrobe.ConfigData.getFileConfig().getStringList("Locked-Slot.Helmet-Slot.Lore")) {
                        lore = slotLore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getFileConfig().getString("Slot-Permission.Slot-" + (i + 10) + ".Require-Prefix"));
                        backgroundLore.add(ChatColor.translateAlternateColorCodes('&', lore));
                    }
                    backgroundMeta.setLore(backgroundLore);
                    background.setItemMeta(backgroundMeta);
                    inv.setItem(i, background);
                } else if (i >= 9 && i <= 17) {
                    // Chestplate slots
                    name = Wardrobe.ConfigData.getFileConfig().getString("Locked-Slot.Chestplate-Slot.Name").replace("%Slot%", Integer.toString(i + 1));
                    backgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                    backgroundLore = new ArrayList<>();
                    for (String slotLore : Wardrobe.ConfigData.getFileConfig().getStringList("Locked-Slot.Chestplate-Slot.Lore")) {
                        lore = slotLore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getFileConfig().getString("Slot-Permission.Slot-" + (i + 1) + ".Require-Prefix"));
                        backgroundLore.add(ChatColor.translateAlternateColorCodes('&', lore));
                    }
                    backgroundMeta.setLore(backgroundLore);
                    background.setItemMeta(backgroundMeta);
                    inv.setItem(i, background);
                } else if (i >= 18 && i <= 26) {
                    // Leggings slots
                    name = Wardrobe.ConfigData.getFileConfig().getString("Locked-Slot.Leggings-Slot.Name").replace("%Slot%", Integer.toString(i - 8));
                    backgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                    backgroundLore = new ArrayList<>();
                    for (String slotLore : Wardrobe.ConfigData.getFileConfig().getStringList("Locked-Slot.Leggings-Slot.Lore")) {
                        lore = slotLore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getFileConfig().getString("Slot-Permission.Slot-" + (i - 8) + ".Require-Prefix"));
                        backgroundLore.add(ChatColor.translateAlternateColorCodes('&', lore));
                    }
                    backgroundMeta.setLore(backgroundLore);
                    background.setItemMeta(backgroundMeta);
                    inv.setItem(i, background);
                } else if (i >= 27 && i <= 35) {
                    // Boots slots
                    name = Wardrobe.ConfigData.getFileConfig().getString("Locked-Slot.Boots-Slot.Name").replace("%Slot%", Integer.toString(i - 17));
                    backgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                    backgroundLore = new ArrayList<>();
                    for (String slotLore : Wardrobe.ConfigData.getFileConfig().getStringList("Locked-Slot.Boots-Slot.Lore")) {
                        lore = slotLore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getFileConfig().getString("Slot-Permission.Slot-" + (i - 17) + ".Require-Prefix"));
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
                    name = Wardrobe.ConfigData.getFileConfig().getString("Locked-Slot.Locked-Button.Name").replace("%Slot%", Integer.toString(i - 26));
                    buttonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                    buttonLore = new ArrayList<>();
                    for (String slotLore : Wardrobe.ConfigData.getFileConfig().getStringList("Locked-Slot.Locked-Button.Lore")) {
                        lore = slotLore.replace("%Permission_Require_Prefix%", Wardrobe.ConfigData.getFileConfig().getString("Slot-Permission.Slot-" + (i - 26) + ".Require-Prefix"));
                        buttonLore.add(ChatColor.translateAlternateColorCodes('&', lore));
                    }
                    buttonMeta.setLore(buttonLore);
                    button.setItemMeta(buttonMeta);
                    inv.setItem(i, button);
                }
            }
        }
    }

    /**
     * Populates the given inventory with available slot backgrounds and buttons for the specified player.
     *
     * @param inv  The inventory to populate.
     * @param name The name associated with the inventory.
     * @param p    The player for whom the inventory is being populated.
     */
    public static void createAvailableSlotBackground(Inventory inv, String name, Player p) {
        FileConfiguration playerConfig;
        String playerId;
        int pageOffset;

        // Determine the offset based on the inventory type
        if (inv == Page1) {
            pageOffset = 0; // Page 1 starts at slot 0
        } else if (inv == Page2) {
            pageOffset = 9; // Page 2 starts at slot 9
        } else {
            return; // Unknown page, exit method
        }

        // Loop through slots in the inventory
        for (int i = 0; i <= 8; ++i) {
            // Calculate the actual slot number to check based on page offset
            int slotToCheck = i + 1 + pageOffset;

            // Check if player has permission for the slot
            if (p.hasPermission(Wardrobe.ConfigData.getFileConfig().getString("Slot-Permission.Slot-" + slotToCheck + ".Permission"))) {
                playerConfig = inv == Page1 ? Wardrobe.Page_1.getFileConfig() : Wardrobe.Page_2.getFileConfig(); // Get appropriate player configuration
                playerId = p.getUniqueId().toString(); // Get player's UUID

                // Check and set helmet item
                if (playerConfig.getItemStack(playerId + ".Slot-" + slotToCheck + ".Helmet") == null) {
                    inv.setItem(i, background(i, inv, name)); // Set background if helmet is null
                } else {
                    inv.setItem(i, playerConfig.getItemStack(playerId + ".Slot-" + slotToCheck + ".Helmet")); // Set helmet item
                }

                // Check and set chestplate item
                if (playerConfig.getItemStack(playerId + ".Slot-" + slotToCheck + ".Chestplate") == null) {
                    inv.setItem(i + 9, background(i + 9, inv, name)); // Set background if chestplate is null
                } else {
                    inv.setItem(i + 9, playerConfig.getItemStack(playerId + ".Slot-" + slotToCheck + ".Chestplate")); // Set chestplate item
                }

                // Check and set leggings item
                if (playerConfig.getItemStack(playerId + ".Slot-" + slotToCheck + ".Leggings") == null) {
                    inv.setItem(i + 18, background(i + 18, inv, name)); // Set background if leggings is null
                } else {
                    inv.setItem(i + 18, playerConfig.getItemStack(playerId + ".Slot-" + slotToCheck + ".Leggings")); // Set leggings item
                }

                // Check and set boots item
                if (playerConfig.getItemStack(playerId + ".Slot-" + slotToCheck + ".Boots") == null) {
                    inv.setItem(i + 27, background(i + 27, inv, name)); // Set background if boots is null
                } else {
                    inv.setItem(i + 27, playerConfig.getItemStack(playerId + ".Slot-" + slotToCheck + ".Boots")); // Set boots item
                }

                // Check if all slots are empty and set empty button
                if (playerConfig.getItemStack(playerId + ".Slot-" + slotToCheck + ".Helmet") == null &&
                        playerConfig.getItemStack(playerId + ".Slot-" + slotToCheck + ".Chestplate") == null &&
                        playerConfig.getItemStack(playerId + ".Slot-" + slotToCheck + ".Leggings") == null &&
                        playerConfig.getItemStack(playerId + ".Slot-" + slotToCheck + ".Boots") == null) {
                    inv.setItem(i + 36, createEmptyButton(i, inv, name)); // Set empty button
                }

                // Check and set appropriate button based on configuration
                String buttonType = playerConfig.getString(playerId + ".Slot-" + slotToCheck + ".Button");
                if (buttonType != null) {
                    if (buttonType.contains("Ready")) {
                        inv.setItem(i + 36, createReadyButton(i, inv, name)); // Set ready button
                    } else if (buttonType.contains("Equipped")) {
                        inv.setItem(i + 36, createEquippedButton(i, inv, name)); // Set equipped button
                    }
                }
            }
        }
    }

    /**
     * Generates a background item stack based on the slot, inventory, and title.
     *
     * @param Slot  The slot index.
     * @param inv   The inventory.
     * @param Title The title of the inventory.
     * @return The background item stack.
     */
    public static ItemStack background(int Slot, Inventory inv, String Title) {
        // Initialize default background item
        ItemStack AvailableBackground = new ItemStack(Material.DIRT);
        ItemMeta AvailableBackgroundMeta = AvailableBackground.getItemMeta();
        String Mat = "";

// Replace the color mappings
        Map<Integer, Material> materialMap = new HashMap<>();
        materialMap.put(0, Material.ORANGE_STAINED_GLASS_PANE);
        materialMap.put(1, Material.YELLOW_STAINED_GLASS_PANE);
        materialMap.put(2, Material.LIME_STAINED_GLASS_PANE);
        materialMap.put(3, Material.GREEN_STAINED_GLASS_PANE);
        materialMap.put(4, Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        materialMap.put(5, Material.CYAN_STAINED_GLASS_PANE);
        materialMap.put(6, Material.BLUE_STAINED_GLASS_PANE);
        materialMap.put(7, Material.PURPLE_STAINED_GLASS_PANE);
        materialMap.put(8, Material.MAGENTA_STAINED_GLASS_PANE);

// Update setting the background color
        if (materialMap.containsKey(Slot % 9)) {
            Material material = materialMap.get(Slot % 9);
            AvailableBackground.setType(material);
        } else {
            // For non-colored panes, use default glass pane
            AvailableBackground.setType(Material.GLASS_PANE);
        }




        // Create lore list
        List<String> AvailableBackgroundLore = new ArrayList<>();
        String Name = "";

        // Check slot range and assign appropriate lore
        if (Slot >= 0 && Slot <= 8) {
            Name = Title.equals(Page1Name) ?
                    Wardrobe.ConfigData.getFileConfig().getString("Availabel-Slot.Helmet-Slot.Name").replace("%Slot%", Integer.toString(Slot + 1)) :
                    Wardrobe.ConfigData.getFileConfig().getString("Availabel-Slot.Helmet-Slot.Name").replace("%Slot%", Integer.toString(Slot + 10));
        } else if (Slot >= 9 && Slot <= 17) {
            Name = Title.equals(Page1Name) ?
                    Wardrobe.ConfigData.getFileConfig().getString("Availabel-Slot.Chestplate-Slot.Name").replace("%Slot%", Integer.toString(Slot - 8)) :
                    Wardrobe.ConfigData.getFileConfig().getString("Availabel-Slot.Chestplate-Slot.Name").replace("%Slot%", Integer.toString(Slot + 1));
        } else if (Slot >= 18 && Slot <= 26) {
            Name = Title.equals(Page1Name) ?
                    Wardrobe.ConfigData.getFileConfig().getString("Availabel-Slot.Leggings-Slot.Name").replace("%Slot%", Integer.toString(Slot - 17)) :
                    Wardrobe.ConfigData.getFileConfig().getString("Availabel-Slot.Leggings-Slot.Name").replace("%Slot%", Integer.toString(Slot - 8));
        } else if (Slot >= 27 && Slot <= 35) {
            Name = Title.equals(Page1Name) ?
                    Wardrobe.ConfigData.getFileConfig().getString("Availabel-Slot.Boots-Slot.Name").replace("%Slot%", Integer.toString(Slot - 26)) :
                    Wardrobe.ConfigData.getFileConfig().getString("Availabel-Slot.Boots-Slot.Name").replace("%Slot%", Integer.toString(Slot - 17));
        }

        // Set display name and add lore
        AvailableBackgroundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Name));
        List<String> loreList = Wardrobe.ConfigData.getFileConfig().getStringList(String.format("Availabel-Slot.%s.Lore", getSlotName(Slot, Title)));
        for (String lore : loreList) {
            AvailableBackgroundLore.add(ChatColor.translateAlternateColorCodes('&', lore));
        }

        // Set lore and metadata for the background item stack
        AvailableBackgroundMeta.setLore(AvailableBackgroundLore);
        AvailableBackground.setItemMeta(AvailableBackgroundMeta);
        return AvailableBackground;
    }

    // Utility method to get slot name based on page and slot index
    private static String getSlotName(int slot, String title) {
        String prefix;
        if (title.equals(Page1Name)) {
            prefix = "Helmet-Slot";
        } else {
            prefix = (slot >= 0 && slot <= 8) ? "Helmet-Slot" :
                    (slot >= 9 && slot <= 17) ? "Chestplate-Slot" :
                            (slot >= 18 && slot <= 26) ? "Leggings-Slot" :
                                    "Boots-Slot";
        }
        return prefix;
    }

    /**
     * Creates an empty button ItemStack for a specified slot in the inventory.
     *
     * @param slotIndex The index of the slot.
     * @param inventory The inventory.
     * @param title     The title of the inventory.
     * @return The empty button ItemStack.
     */
    public static ItemStack createEmptyButton(int slotIndex, Inventory inventory, String title) {
        // Initialize default button ItemStack
        ItemStack button = new ItemStack(Material.DIRT);
        ItemMeta buttonMeta = button.getItemMeta();
        String materialName;

        // Determine button material based on Minecraft version
        if (Ver.contains("1.8") || Ver.contains("1.9") || Ver.contains("1.10") || Ver.contains("1.11") || Ver.contains("1.12")) {
            materialName = "INK_SACK";
            button.setType(Material.valueOf(materialName));
            button.setDurability((short) 8); // Set durability for pre-1.13 versions
        }

        if (Ver.contains("1.13") || Ver.contains("1.14") || Ver.contains("1.15") || Ver.contains("1.16") || Ver.contains("1.17") || Ver.contains("1.18") || Ver.contains("1.19") || Ver.contains("1.20")) {
            materialName = "GRAY_DYE";
            button.setType(Material.valueOf(materialName)); // Set material for 1.13+ versions
        }

        // Determine button display name based on the title
        String buttonName = "";
        if (title.contains(Page1Name)) {
            buttonName = Wardrobe.ConfigData.getFileConfig().getString("Availabel-Slot.Empty-Slot-Button.Name").replace("%Slot%", Integer.toString(slotIndex + 1));
        } else if (title.contains(Page2Name)) {
            buttonName = Wardrobe.ConfigData.getFileConfig().getString("Availabel-Slot.Empty-Slot-Button.Name").replace("%Slot%", Integer.toString(slotIndex + 10));
        }

        // Set display name and lore for the button
        buttonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', buttonName));
        List<String> buttonLore = new ArrayList<>();
        Iterator<String> loreIterator = Wardrobe.ConfigData.getFileConfig().getStringList("Availabel-Slot.Empty-Slot-Button.Lore").iterator();

        // Translate lore color codes and add them to the button lore list
        while (loreIterator.hasNext()) {
            String lore = loreIterator.next();
            buttonLore.add(ChatColor.translateAlternateColorCodes('&', lore));
        }

        // Set lore and metadata for the button ItemStack
        buttonMeta.setLore(buttonLore);
        button.setItemMeta(buttonMeta);
        return button;
    }

    /**
     * Creates a ready button ItemStack for a specified slot in the inventory.
     *
     * @param slotIndex The index of the slot.
     * @param inventory The inventory.
     * @param title     The title of the inventory.
     * @return The ready button ItemStack.
     */
    public static ItemStack createReadyButton(int slotIndex, Inventory inventory, String title) {
        // Initialize default button ItemStack
        ItemStack button = new ItemStack(Material.DIRT);
        ItemMeta buttonMeta = button.getItemMeta();
        String materialName;

        // Determine button material based on Minecraft version
        if (Ver.contains("1.8") || Ver.contains("1.9") || Ver.contains("1.10") || Ver.contains("1.11") || Ver.contains("1.12")) {
            materialName = "INK_SACK";
            button.setType(Material.valueOf(materialName));
            button.setDurability((short) 9); // Set durability for pre-1.13 versions
        }

        if (Ver.contains("1.13") || Ver.contains("1.14") || Ver.contains("1.15") || Ver.contains("1.16") || Ver.contains("1.17") || Ver.contains("1.18") || Ver.contains("1.19") || Ver.contains("1.20")) {
            materialName = "PINK_DYE";
            button.setType(Material.valueOf(materialName)); // Set material for 1.13+ versions
        }

        // Determine button display name based on the title
        String buttonName = "";
        if (title.contains(Page1Name)) {
            buttonName = Wardrobe.ConfigData.getFileConfig().getString("Availabel-Slot.Ready-Button.Name").replace("%Slot%", Integer.toString(slotIndex + 1));
        } else if (title.contains(Page2Name)) {
            buttonName = Wardrobe.ConfigData.getFileConfig().getString("Availabel-Slot.Ready-Button.Name").replace("%Slot%", Integer.toString(slotIndex + 10));
        }

        // Set display name and lore for the button
        buttonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', buttonName));
        List<String> buttonLore = new ArrayList<>();
        Iterator<String> loreIterator = Wardrobe.ConfigData.getFileConfig().getStringList("Availabel-Slot.Ready-Button.Lore").iterator();

        // Translate lore color codes and add them to the button lore list
        while (loreIterator.hasNext()) {
            String lore = loreIterator.next();
            buttonLore.add(ChatColor.translateAlternateColorCodes('&', lore));
        }

        // Set lore and metadata for the button ItemStack
        buttonMeta.setLore(buttonLore);
        button.setItemMeta(buttonMeta);
        return button;
    }

    /**
     * Creates an equipped button for an inventory.
     *
     * @param Slot  The slot index of the button.
     * @param inv   The inventory where the button will be added.
     * @param Title The title of the inventory.
     * @return The equipped button as an ItemStack.
     */
    public static ItemStack createEquippedButton(int Slot, Inventory inv, String Title) {
        // Create a new ItemStack and its metadata
        ItemStack Button = new ItemStack(Material.DIRT);
        ItemMeta ButtonMeta = Button.getItemMeta();
        String Name;

        // Determine the material based on server version
        if (Ver.contains("1.8") || Ver.contains("1.9") || Ver.contains("1.10") || Ver.contains("1.11") || Ver.contains("1.12")) {
            Name = "INK_SACK";
            Button.setType(Material.valueOf(Name));
            Button.setDurability((short) 10);
        } else if (Ver.contains("1.13") || Ver.contains("1.14") || Ver.contains("1.15") || Ver.contains("1.16") || Ver.contains("1.17") || Ver.contains("1.18") || Ver.contains("1.19") || Ver.contains("1.20")) {
            Name = "LIME_DYE";
            Button.setType(Material.valueOf(Name));
        }

        // Determine the display name based on the title
        Name = "";
        if (Title.contains(Page1Name)) {
            Name = Wardrobe.ConfigData.getFileConfig().getString("Availabel-Slot.Equipped-Button.Name").replace("%Slot%", Integer.toString(Slot + 1));
        } else if (Title.contains(Page2Name)) {
            Name = Wardrobe.ConfigData.getFileConfig().getString("Availabel-Slot.Equipped-Button.Name").replace("%Slot%", Integer.toString(Slot + 10));
        }

        // Set display name and lore for the button
        ButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Name));
        List<String> ButtonLore = new ArrayList<>();
        Iterator<String> loreIterator = Wardrobe.ConfigData.getFileConfig().getStringList("Availabel-Slot.Equipped-Button.Lore").iterator();
        while (loreIterator.hasNext()) {
            String Lore = loreIterator.next();
            ButtonLore.add(ChatColor.translateAlternateColorCodes('&', Lore));
        }
        ButtonMeta.setLore(ButtonLore);

        // Apply metadata to the ItemStack and return it
        Button.setItemMeta(ButtonMeta);
        return Button;
    }

    /**
     * Creates a "Close" button in the inventory if enabled in the configuration.
     *
     * @param inv The inventory where the "Close" button will be added.
     */
    public static void createCloseButton(Inventory inv) {
        // Check if the "Close" button is enabled
        if (Wardrobe.ConfigData.getFileConfig().getBoolean("Close-Button.Enable")) {
            ItemStack close = new ItemStack(Material.BARRIER);
            ItemMeta closeMeta = close.getItemMeta();
            closeMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Wardrobe.ConfigData.getFileConfig().getString("Close-Button.Name")));

            List<String> closeLore = new ArrayList<>();
            Iterator<String> loreIterator = Wardrobe.ConfigData.getFileConfig().getStringList("Close-Button.Lore").iterator();
            while (loreIterator.hasNext()) {
                String lore = loreIterator.next();
                closeLore.add(ChatColor.translateAlternateColorCodes('&', lore));
            }

            closeMeta.setLore(closeLore);
            close.setItemMeta(closeMeta);

            inv.setItem(Wardrobe.ConfigData.getFileConfig().getInt("Close-Button.Slot"), close);
        }
    }

    /**
     * Creates "Next" and "Previous" page navigation buttons in the inventory if enabled in the configuration.
     *
     * @param inv The inventory where the navigation buttons will be added.
     */
    public static void createNextAndPreviousButton(Inventory inv) {
        // Check if the inventory is Page1
        if (inv == Page1) {
            // Check if the "Next Page" button is enabled
            if (Wardrobe.ConfigData.getFileConfig().getBoolean("Next-Page-Button.Enable")) {
                ItemStack nextPage = new ItemStack(Material.ARROW);
                ItemMeta nextPageMeta = nextPage.getItemMeta();
                nextPageMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Wardrobe.ConfigData.getFileConfig().getString("Next-Page-Button.Name")));

                List<String> nextPageLore = new ArrayList<>();
                Iterator<String> loreIterator = Wardrobe.ConfigData.getFileConfig().getStringList("Next-Page-Button.Lore").iterator();
                while (loreIterator.hasNext()) {
                    String lore = loreIterator.next();
                    nextPageLore.add(ChatColor.translateAlternateColorCodes('&', lore));
                }

                nextPageMeta.setLore(nextPageLore);
                nextPage.setItemMeta(nextPageMeta);
                inv.setItem(Wardrobe.ConfigData.getFileConfig().getInt("Next-Page-Button.Slot"), nextPage);
            }
        }
        // Check if the inventory is Page2 and "Previous Page" button is enabled
        else if (inv == Page2 && Wardrobe.ConfigData.getFileConfig().getBoolean("Previous-Page-Button.Enable")) {
            ItemStack previousPage = new ItemStack(Material.ARROW);
            ItemMeta previousPageMeta = previousPage.getItemMeta();
            previousPageMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Wardrobe.ConfigData.getFileConfig().getString("Previous-Page-Button.Name")));

            List<String> previousPageLore = new ArrayList<>();
            Iterator<String> loreIterator = Wardrobe.ConfigData.getFileConfig().getStringList("Previous-Page-Button.Lore").iterator();
            while (loreIterator.hasNext()) {
                String lore = loreIterator.next();
                previousPageLore.add(ChatColor.translateAlternateColorCodes('&', lore));
            }

            previousPageMeta.setLore(previousPageLore);
            previousPage.setItemMeta(previousPageMeta);
            inv.setItem(Wardrobe.ConfigData.getFileConfig().getInt("Previous-Page-Button.Slot"), previousPage);
        }
    }
}
