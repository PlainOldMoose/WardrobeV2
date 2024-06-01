package me.plainoldmoose.wardrobe.Command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides tab completion for wardrobe-related commands.
 */
public class WardrobeTabCompleter implements TabCompleter {

    private final List<String> baseCommands = List.of("reload", "open", "reset", "check", "allow");
    private final List<String> resetSubCommands = List.of("page", "all", "slot");
    private final List<String> armorTypes = List.of("helmet", "chestplate", "leggings", "boots");
    private final List<String> pageNumbers = List.of("1", "2");
    private final List<String> slotNumbers;

    public WardrobeTabCompleter() {
        slotNumbers = new ArrayList<>();
        for (int i = 1; i <= 18; i++) {
            slotNumbers.add(String.valueOf(i));
        }
    }

    /**
     * Handles tab completion for wardrobe-related commands.
     *
     * @param sender the entity that sent the command
     * @param cmd    the command
     * @param label  the command label
     * @param args   the command arguments
     * @return a list of tab completion options
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return handleNonPlayer(args);
        }

        Player player = (Player) sender;
        if (player.hasPermission("CustomCrafting.Admin")) {
            return handleAdminPlayer(args);
        }

        return null;
    }

    /**
     * Handles tab completion for non-player senders (e.g., console).
     *
     * @param args the command arguments
     * @return a list of tab completion options
     */
    private List<String> handleNonPlayer(String[] args) {
        return getTabCompletion(args, false);
    }

    /**
     * Handles tab completion for players with admin permissions.
     *
     * @param args the command arguments
     * @return a list of tab completion options
     */
    private List<String> handleAdminPlayer(String[] args) {
        return getTabCompletion(args, true);
    }

    /**
     * Generates the tab completion options based on the command arguments.
     *
     * @param args    the command arguments
     * @param isAdmin indicates if the sender has admin permissions
     * @return a list of tab completion options
     */
    private List<String> getTabCompletion(String[] args, boolean isAdmin) {
        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            return getMatchingArguments(args[0], baseCommands);
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("open") || args[0].equalsIgnoreCase("reset") || (isAdmin && args[0].equalsIgnoreCase("check"))) {
                return getMatchingArguments(args[1], getOnlinePlayerNames());
            }
            if (isAdmin && args[0].equalsIgnoreCase("allow")) {
                return getMatchingArguments(args[1], armorTypes);
            }
        } else if (args.length == 3 && args[0].equalsIgnoreCase("reset")) {
            return getMatchingArguments(args[2], resetSubCommands);
        } else if (args.length == 4 && args[0].equalsIgnoreCase("reset")) {
            if (args[2].equalsIgnoreCase("page")) {
                return getMatchingArguments(args[3], pageNumbers);
            }
            if (args[2].equalsIgnoreCase("slot")) {
                return getMatchingArguments(args[3], slotNumbers);
            }
        }
        result.clear();
        return result;
    }

    /**
     * Filters the arguments based on the input prefix.
     *
     * @param prefix    the input prefix
     * @param arguments the list of potential arguments
     * @return a list of matching arguments
     */
    private List<String> getMatchingArguments(String prefix, List<String> arguments) {
        List<String> result = new ArrayList<>();
        for (String arg : arguments) {
            if (arg.toLowerCase().startsWith(prefix.toLowerCase())) {
                result.add(arg);
            }
        }
        return result;
    }

    /**
     * Retrieves the names of online players.
     *
     * @return a list of online player names
     */
    private List<String> getOnlinePlayerNames() {
        List<String> playerNames = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            playerNames.add(player.getName());
        }
        return playerNames;
    }
}
