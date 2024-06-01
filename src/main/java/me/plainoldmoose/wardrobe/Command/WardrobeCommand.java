package me.plainoldmoose.wardrobe.Command;

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
import java.util.function.BiConsumer;

/**
 * Handles wardrobe-related commands.
 */
public class WardrobeCommand implements CommandExecutor {
    private final Wardrobe plugin;

    /**
     * Constructs a new WardrobeCommand instance.
     *
     * @param plugin The plugin instance.
     */
    public WardrobeCommand(Wardrobe plugin) {
        this.plugin = plugin;
        // Register the commands with this executor
        plugin.getCommand("wardrobe").setExecutor(this);
        plugin.getCommand("wb").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            // If the command sender is a player, handle player commands
            Player player = (Player) sender;
            if (args.length == 0) {
                // If no arguments provided, open the player's wardrobe
                openWardrobe(player.getName());
            } else {
                // Otherwise, handle the player's commands
                handlePlayerCommands(player, args);
            }
        } else {
            // If the command sender is not a player (e.g., console), handle console commands
            if (args.length == 0) {
                // If no arguments provided, prompt the console to choose a work to do
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Please choose a work to do!");
            } else {
                // Otherwise, handle the console's commands
                handleConsoleCommands(args);
            }
        }
        return true;
    }

    /**
     * Handles console commands.
     *
     * @param args The command arguments.
     */
    private void handleConsoleCommands(String[] args) {
        String subCommand = args[0].toLowerCase();
        switch (subCommand) {
            case "reload":
                // Reload the plugin configurations
                reloadConfigs();
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] Successfully reloaded config!");
                break;
            case "open":
                // Open the wardrobe of the specified player (console command)
                if (args.length > 1) {
                    openWardrobe(args[1]);
                } else {
                    // If no player specified, prompt to specify a player name
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Please specify a player name!");
                }
                break;
            case "reset":
                // Reset the wardrobe of the specified player (console command)
                if (args.length > 2) {
                    resetWardrobe(args[1], args[2]);
                } else {
                    // If insufficient arguments, prompt to enter player name and reset type
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Please specify a player name and reset type!");
                }
                break;
            default:
                // Handle unknown commands
                Bukkit.getConsoleSender().sendMessage("Unknown command. Type \"/help\" for help.");
        }
    }

    /**
     * Handles player commands.
     *
     * @param player The player issuing the command.
     * @param args   The command arguments.
     */
    private void handlePlayerCommands(Player player, String[] args) {
        if (!player.hasPermission(Wardrobe.ConfigData.getConfig().getString("Admin-Permission"))) {
            // If the player does not have permission, send a permission denied message
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Wardrobe.ConfigData.getConfig().getString("Wardrobe_Message.Permission_Denied")));
            return;
        }

        // Map of admin commands and their corresponding handlers
        Map<String, BiConsumer<Player, String[]>> adminCommands = Map.of(
                "reload", this::handleReloadCommand,
                "open", this::handleOpenCommand,
                "reset", this::handleResetCommand
        );

        String subCommand = args[0].toLowerCase();
        if (adminCommands.containsKey(subCommand)) {
            // If the command is valid, execute the corresponding handler
            adminCommands.get(subCommand).accept(player, args);
        } else {
            // If the command is unknown, send an unknown command message
            player.sendMessage("Unknown command. Type \"/help\" for");
        }
    }

    /**
     * Handles the reload command.
     *
     * @param player The player issuing the command.
     * @param args   The command arguments.
     */
    private void handleReloadCommand(Player player, String[] args) {
        // Reload plugin configurations
        reloadConfigs();
        player.sendMessage(ChatColor.GREEN + "[Wardrobe] Successfully reloaded config!");
    }

    /**
     * Handles the open command.
     *
     * @param player The player issuing the command.
     * @param args   The command arguments.
     */
    private void handleOpenCommand(Player player, String[] args) {
        if (args.length > 1) {
            // If a player name is provided, open their wardrobe
            openWardrobe(args[1]);
        } else {
            // If no player name is provided, open the sender's wardrobe
            WardrobeGUI.CreateWardrobePage1(player);
        }
    }

    /**
     * Handles the reset command.
     *
     * @param player The player issuing the command.
     * @param args   The command arguments.
     */
    private void handleResetCommand(Player player, String[] args) {
        if (args.length > 2) {
            // If player name and reset type are provided, reset the wardrobe
            resetWardrobe(args[1], args[2]);
        } else {
            // If insufficient arguments provided, prompt to enter player name and reset type
            player.sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Please enter a player name and reset type!");
        }
    }

    /**
     * Reloads plugin configurations.
     */
    private void reloadConfigs() {
        Wardrobe.ConfigData.ReloadConfig();
        Wardrobe.Page_1.reloadConfig();
        Wardrobe.Page_2.reloadConfig();
    }

    /**
     * Opens the wardrobe for the specified player.
     *
     * @param playerName The name of the player whose wardrobe is to be opened.
     */
    private void openWardrobe(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            // If the player is online, open their wardrobe
            WardrobeGUI.CreateWardrobePage1(player);
        } else {
            // If the player is not online, send a message indicating that
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "That player is not online!");
        }
    }

    /**
     * Resets the wardrobe for the specified player.
     *
     * @param playerName The name of the player whose wardrobe is to be reset.
     * @param type       The type of reset (e.g., "all", "page", "slot").
     */
    private void resetWardrobe(String playerName, String type) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null) {
            boolean success;
            if ("all".equalsIgnoreCase(type)) {
                // If reset type is "all", reset all wardrobe items
                success = DataWork.resetAllPlayerWardrobe(player);
            } else {
                // Handle other reset types as needed (assuming implemented elsewhere)
                success = false;
            }
            if (success) {
                // If reset successful, send success message
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] Successfully reset " + ChatColor.GOLD + type + ChatColor.GREEN + " of " + ChatColor.GOLD + playerName + "'s" + ChatColor.GREEN + " Wardrobe!");
            } else {
                // If reset failed or unknown type, send error message
                Bukkit.getConsoleSender().sendMessage("Unknown command. Type \"/help\" for help.");
            }
        } else {
            // If the player is not online, send a message indicating that
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "That player is not online!");
        }
    }
}
