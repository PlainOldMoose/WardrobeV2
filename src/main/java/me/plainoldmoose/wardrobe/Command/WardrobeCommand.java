package me.plainoldmoose.wardrobe.Command;

import me.plainoldmoose.wardrobe.GUI.CheckPlayerGUI;
import me.plainoldmoose.wardrobe.GUI.WardrobeGUI;
import me.plainoldmoose.wardrobe.Wardrobe;
import me.plainoldmoose.wardrobe.Work.DataWork;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

public class WardrobeCommand implements CommandExecutor {
    private final Wardrobe plugin;

    public WardrobeCommand(Wardrobe plugin) {
        this.plugin = plugin;
        plugin.getCommand("wardrobe").setExecutor(this);
        plugin.getCommand("wb").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            return handlePlayerCommands((Player) sender, args);
        } else {
            return handleConsoleCommands(args);
        }
    }

    private boolean handleConsoleCommands(String[] args) {
        if (args.length == 0) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Please choose a work to do!");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                reloadConfigs();
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] Successfully reloaded config!");
                return true;
            case "open":
                return args.length == 2 && openWardrobe(args[1]);
            case "reset":
                if (args.length == 3) return resetWardrobe(args[1], args[2]);
                if (args.length == 4) return resetWardrobeWithDetails(args[1], args[2], args[3]);
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Please choose a work to do!");
                return true;
            default:
                Bukkit.getConsoleSender().sendMessage("Unknown command. Type \"/help\" for help.");
                return false;
        }
    }

    private boolean handlePlayerCommands(Player player, String[] args) {
        if (args.length == 0) {
            return handleNoArgs(player);
        }

        if (!player.hasPermission(Wardrobe.ConfigData.getConfig().getString("Admin-Permission"))) {
            return handleNonAdminCommands(player, args);
        }

        return handleAdminCommands(player, args);
    }

    private boolean handleNoArgs(Player player) {
        if (CheckPlayerGUI.onOpen && CheckPlayerGUI.Path.contains(player.getUniqueId().toString())) {
            player.sendMessage(ChatColor.RED + "An admin is opening your wardrobe, please wait!");
            return false;
        } else {
            WardrobeGUI.CreateWardrobePage1(player);
            return true;
        }
    }

    private boolean handleNonAdminCommands(Player player, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("open")) {
            WardrobeGUI.CreateWardrobePage1(player);
            return true;
        }
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Wardrobe.ConfigData.getConfig().getString("Wardrobe_Message.Permission_Denied")));
        return false;
    }

    private boolean handleAdminCommands(Player player, String[] args) {
        Map<String, BiConsumer<Player, String[]>> adminCommands = Map.of(
                "reload", this::handleReloadCommand,
                "open", this::handleOpenCommand,
                "reset", this::handleResetCommand,
                "check", this::handleCheckCommand
        );

        String command = args[0].toLowerCase();
        if (adminCommands.containsKey(command)) {
            adminCommands.get(command).accept(player, args);
            return true;
        } else {
            player.sendMessage("Unknown command. Type \"/help\" for help.");
            return false;
        }
    }

    private void handleReloadCommand(Player player, String[] args) {
        reloadConfigs();
        player.sendMessage(ChatColor.GREEN + "[Wardrobe] Successfully reloaded config!");
    }

    private void handleOpenCommand(Player player, String[] args) {
        if (args.length == 1) {
            WardrobeGUI.CreateWardrobePage1(player);
        } else {
            Optional.ofNullable(Bukkit.getPlayer(args[1])).ifPresentOrElse(
                    WardrobeGUI::CreateWardrobePage1,
                    () -> player.sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "That player is not online!")
            );
        }
    }

    private void handleResetCommand(Player player, String[] args) {
        if (args.length < 3) {
            player.sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Please enter a player name and reset type!");
            return;
        }
        resetWardrobe(args[1], args[2]);
    }

    private void handleCheckCommand(Player player, String[] args) {
        if (args.length < 3) {
            player.sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Please enter a player name and check type!");
            return;
        }
        checkPlayerWardrobe(player, args[1], args[2]);
    }

    private void reloadConfigs() {
        Wardrobe.ConfigData.ReloadConfig();
        Wardrobe.Page_1.ReloadConfig();
        Wardrobe.Page_2.ReloadConfig();
    }

    private boolean openWardrobe(String playerName) {
        return Optional.ofNullable(Bukkit.getPlayer(playerName))
                .map(player -> {
                    WardrobeGUI.CreateWardrobePage1(player);
                    return true;
                })
                .orElseGet(() -> {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "That player is not online!");
                    return false;
                });
    }

    private boolean resetWardrobe(String playerName, String type) {
        return Optional.ofNullable(Bukkit.getPlayer(playerName))
                .map(player -> {
                    if ("all".equalsIgnoreCase(type) && DataWork.ResetAllPlayerWardrobe(player)) {
                        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] Successfully reset " + ChatColor.GOLD + type + ChatColor.GREEN + " of " + ChatColor.GOLD + playerName + "'s" + ChatColor.GREEN + " Wardrobe!");
                        return true;
                    } else {
                        Bukkit.getConsoleSender().sendMessage("Unknown command. Type \"/help\" for help.");
                        return false;
                    }
                })
                .orElseGet(() -> {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "That player is not online!");
                    return false;
                });
    }

    private boolean resetWardrobeWithDetails(String playerName, String type, String number) {
        return Optional.ofNullable(Bukkit.getPlayer(playerName))
                .map(player -> {
                    switch (type.toLowerCase()) {
                        case "page":
                            return resetPage(player, number, playerName);
                        case "slot":
                            return resetSlot(player, number, playerName);
                        default:
                            Bukkit.getConsoleSender().sendMessage("Unknown command. Type \"/help\" for help.");
                            return false;
                    }
                })
                .orElseGet(() -> {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "That player is not online!");
                    return false;
                });
    }

    private boolean resetPage(Player player, String number, String playerName) {
        if ("1".equalsIgnoreCase(number) || "2".equalsIgnoreCase(number)) {
            if (DataWork.ResetPagePlayerWardrobe(player, number)) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] Successfully reset " + ChatColor.GOLD + "page " + number + ChatColor.GREEN + " of " + ChatColor.GOLD + playerName + "'s" + ChatColor.GREEN + " Wardrobe!");
                return true;
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Unknown page!");
        }
        return false;
    }

    private boolean resetSlot(Player player, String number, String playerName) {
        int slotNumber = Integer.parseInt(number);
        if (slotNumber >= 1 && slotNumber <= 18) {
            if (DataWork.ResetSlotPlayerWardrobe(player, number)) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] Successfully reset " + ChatColor.GOLD + "slot " + number + ChatColor.GREEN + " of " + ChatColor.GOLD + playerName + "'s" + ChatColor.GREEN + " Wardrobe!");
                return true;
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Unknown slot!");
        }
        return false;
    }

    private void checkPlayerWardrobe(Player player, String playerName, String checkType) {
        Optional<Player> target = Optional.ofNullable(Bukkit.getPlayer(playerName));

        if (!target.isPresent()) {
            player.sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "That player is not online!");
            return;
        }

        switch (checkType) {
            case "1":
                if (CheckPlayerGUI.CheckName(playerName)) {
                    CheckPlayerGUI.CheckGUI1(player);
                    player.sendMessage(ChatColor.GREEN + "[Wardrobe] Checking wardrobe page 1 of " + ChatColor.GOLD + playerName);
                } else {
                    player.sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "The player has not opened the Wardrobe yet.");
                }
                break;
            case "2":
                if (CheckPlayerGUI.CheckName(playerName)) {
                    CheckPlayerGUI.CheckGUI2(player);
                    player.sendMessage(ChatColor.GREEN + "[Wardrobe] Checking wardrobe page 2 of " + ChatColor.GOLD + playerName);
                } else {
                    player.sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "The player has not opened the Wardrobe yet.");
                }
                break;
            default:
                player.sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Unknown check type! Use '1' or '2'.");
                break;
        }
    }
}
