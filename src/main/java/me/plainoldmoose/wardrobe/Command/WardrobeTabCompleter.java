package me.plainoldmoose.wardrobe.Command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WardrobeTabCompleter implements TabCompleter {
    List<String> arguments = new ArrayList();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        String a;
        if (!(sender instanceof Player)) {
            List<String> result = new ArrayList();
            Iterator var11;
            if (args.length == 1) {
                this.arguments.clear();
                result.clear();
                this.arguments.add("reload");
                this.arguments.add("open");
                this.arguments.add("reset");
                var11 = this.arguments.iterator();

                while(var11.hasNext()) {
                    a = (String)var11.next();
                    if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                        result.add(a);
                    }
                }

                return result;
            }

            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("reload")) {
                    result.clear();
                    return result;
                }

                if (!args[0].equalsIgnoreCase("open") && !args[0].equalsIgnoreCase("reset")) {
                    result.clear();
                    return result;
                }

                this.arguments.clear();
                result.clear();
                var11 = Bukkit.getOnlinePlayers().iterator();

                while(var11.hasNext()) {
                    Player player = (Player)var11.next();
                    a = player.getName().toString();
                    this.arguments.add(a);
                }

                var11 = this.arguments.iterator();

                while(var11.hasNext()) {
                    a = (String)var11.next();
                    if (a.toLowerCase().startsWith(args[1])) {
                        result.add(a);
                    }
                }

                return result;
            }

            if (args.length == 3) {
                if (!args[0].equalsIgnoreCase("reload") && !args[0].equalsIgnoreCase("open")) {
                    if (args[0].equalsIgnoreCase("reset")) {
                        this.arguments.clear();
                        result.clear();
                        this.arguments.add("page");
                        this.arguments.add("all");
                        this.arguments.add("slot");
                        var11 = this.arguments.iterator();

                        while(var11.hasNext()) {
                            a = (String)var11.next();
                            if (a.toLowerCase().startsWith(args[2].toLowerCase())) {
                                result.add(a);
                            }
                        }

                        return result;
                    }

                    result.clear();
                    return result;
                }

                result.clear();
                return result;
            }

            if (args.length == 4) {
                if (!args[0].equalsIgnoreCase("reload") && !args[0].equalsIgnoreCase("open")) {
                    if (args[0].equalsIgnoreCase("reset")) {
                        if (args[2].equalsIgnoreCase("page")) {
                            this.arguments.clear();
                            result.clear();
                            this.arguments.add("1");
                            this.arguments.add("2");
                            var11 = this.arguments.iterator();

                            while(var11.hasNext()) {
                                a = (String)var11.next();
                                if (a.toLowerCase().startsWith(args[3].toLowerCase())) {
                                    result.add(a);
                                }
                            }

                            return result;
                        }

                        if (args[2].equalsIgnoreCase("slot")) {
                            this.arguments.clear();
                            result.clear();
                            this.arguments.add("1");
                            this.arguments.add("2");
                            this.arguments.add("3");
                            this.arguments.add("4");
                            this.arguments.add("5");
                            this.arguments.add("6");
                            this.arguments.add("7");
                            this.arguments.add("8");
                            this.arguments.add("9");
                            this.arguments.add("10");
                            this.arguments.add("11");
                            this.arguments.add("12");
                            this.arguments.add("13");
                            this.arguments.add("14");
                            this.arguments.add("15");
                            this.arguments.add("16");
                            this.arguments.add("17");
                            this.arguments.add("18");
                            var11 = this.arguments.iterator();

                            while(var11.hasNext()) {
                                a = (String)var11.next();
                                if (a.toLowerCase().startsWith(args[3].toLowerCase())) {
                                    result.add(a);
                                }
                            }

                            return result;
                        }

                        result.clear();
                        return result;
                    }

                    result.clear();
                    return result;
                }

                result.clear();
                return result;
            }

            if (args.length > 4) {
                result.clear();
                return result;
            }
        }

        Player p = (Player)sender;
        List<String> result = new ArrayList();
        if (p.hasPermission("CustomCrafting.Admin")) {
            Iterator var7;
            if (args.length == 1) {
                this.arguments.clear();
                result.clear();
                this.arguments.add("reload");
                this.arguments.add("open");
                this.arguments.add("reset");
                this.arguments.add("check");
                this.arguments.add("allow");
                var7 = this.arguments.iterator();

                while(var7.hasNext()) {
                    a = (String)var7.next();
                    if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                        result.add(a);
                    }
                }

                return result;
            }

            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("reload")) {
                    result.clear();
                    return result;
                }

                if (!args[0].equalsIgnoreCase("open") && !args[0].equalsIgnoreCase("reset") && !args[0].equalsIgnoreCase("check")) {
                    if (args[0].equalsIgnoreCase("allow")) {
                        this.arguments.clear();
                        result.clear();
                        this.arguments.add("helmet");
                        this.arguments.add("chestplate");
                        this.arguments.add("leggings");
                        this.arguments.add("boots");
                        var7 = this.arguments.iterator();

                        while(var7.hasNext()) {
                            a = (String)var7.next();
                            if (a.toLowerCase().startsWith(args[1].toLowerCase())) {
                                result.add(a);
                            }
                        }

                        return result;
                    }

                    result.clear();
                    return result;
                }

                this.arguments.clear();
                result.clear();
                var7 = Bukkit.getOnlinePlayers().iterator();

                while(var7.hasNext()) {
                    Player player = (Player)var7.next();
                    String player1 = player.getName().toString();
                    this.arguments.add(player1);
                }

                var7 = this.arguments.iterator();

                while(var7.hasNext()) {
                    a = (String)var7.next();
                    if (a.toLowerCase().startsWith(args[1])) {
                        result.add(a);
                    }
                }

                return result;
            }

            if (args.length == 3) {
                if (!args[0].equalsIgnoreCase("reload") && !args[0].equalsIgnoreCase("open")) {
                    if (args[0].equalsIgnoreCase("reset")) {
                        this.arguments.clear();
                        result.clear();
                        this.arguments.add("page");
                        this.arguments.add("all");
                        this.arguments.add("slot");
                        var7 = this.arguments.iterator();

                        while(var7.hasNext()) {
                            a = (String)var7.next();
                            if (a.toLowerCase().startsWith(args[2].toLowerCase())) {
                                result.add(a);
                            }
                        }

                        return result;
                    }

                    result.clear();
                    return result;
                }

                result.clear();
                return result;
            }

            if (args.length == 4) {
                if (!args[0].equalsIgnoreCase("reload") && !args[0].equalsIgnoreCase("open")) {
                    if (args[0].equalsIgnoreCase("reset")) {
                        if (args[2].equalsIgnoreCase("page")) {
                            this.arguments.clear();
                            result.clear();
                            this.arguments.add("1");
                            this.arguments.add("2");
                            var7 = this.arguments.iterator();

                            while(var7.hasNext()) {
                                a = (String)var7.next();
                                if (a.toLowerCase().startsWith(args[3].toLowerCase())) {
                                    result.add(a);
                                }
                            }

                            return result;
                        }

                        if (args[2].equalsIgnoreCase("slot")) {
                            this.arguments.clear();
                            result.clear();
                            this.arguments.add("1");
                            this.arguments.add("2");
                            this.arguments.add("3");
                            this.arguments.add("4");
                            this.arguments.add("5");
                            this.arguments.add("6");
                            this.arguments.add("7");
                            this.arguments.add("8");
                            this.arguments.add("9");
                            this.arguments.add("10");
                            this.arguments.add("11");
                            this.arguments.add("12");
                            this.arguments.add("13");
                            this.arguments.add("14");
                            this.arguments.add("15");
                            this.arguments.add("16");
                            this.arguments.add("17");
                            this.arguments.add("18");
                            var7 = this.arguments.iterator();

                            while(var7.hasNext()) {
                                a = (String)var7.next();
                                if (a.toLowerCase().startsWith(args[3].toLowerCase())) {
                                    result.add(a);
                                }
                            }

                            return result;
                        }

                        result.clear();
                        return result;
                    }

                    result.clear();
                    return result;
                }

                result.clear();
                return result;
            }

            if (args.length > 4) {
                result.clear();
                return result;
            }
        }

        return null;
    }
}
