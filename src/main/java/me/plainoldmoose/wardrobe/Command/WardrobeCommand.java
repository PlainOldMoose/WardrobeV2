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

public class WardrobeCommand implements CommandExecutor {
    public Wardrobe plugin;

    public WardrobeCommand(Wardrobe plugin) {
        this.plugin = plugin;
        plugin.getCommand("wardrobe").setExecutor(this);
        plugin.getCommand("wb").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p;
        String Number;
        if (!(sender instanceof Player)) {
            if (args.length == 0) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Please choose a work to do!");
                return true;
            }

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    Wardrobe.ConfigData.ReloadConfig();
                    Wardrobe.Page_1.ReloadConfig();
                    Wardrobe.Page_2.ReloadConfig();
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] Success reloaded config!");
                    return true;
                }

                p = Bukkit.getPlayer(args[1]);
                if (p != null) {
                    WardrobeGUI.CreateWardrobePage1(p);
                    return true;
                }

                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "That player is not online!");
                return false;
            }

            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("open")) {
                    p = Bukkit.getPlayer(args[1]);
                    if (p != null) {
                        WardrobeGUI.CreateWardrobePage1(p);
                        return true;
                    }

                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "That player is not online!");
                    return false;
                }

                if (args[0].equalsIgnoreCase("reset")) {
                    p = Bukkit.getPlayer(args[1]);
                    if (p != null) {
                        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Please choose a work to do!");
                        return true;
                    }

                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "That player is not online!");
                    return false;
                }

                Bukkit.getConsoleSender().sendMessage("Unknown command. Type \"/help\" for help.");
                return false;
            }

            if (args.length == 3) {
                if (args[0].equalsIgnoreCase("reset")) {
                    p = Bukkit.getPlayer(args[1]);
                    if (p != null) {
                        if (!args[2].equalsIgnoreCase("page") && !args[2].equalsIgnoreCase("slot")) {
                            if (args[2].equalsIgnoreCase("all")) {
                                if (DataWork.ResetAllPlayerWardrobe(p)) {
                                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] Success reset " + ChatColor.GOLD + args[2] + ChatColor.GREEN + " of " + ChatColor.GOLD + args[1] + "'s" + ChatColor.GREEN + " Wardrobe!");
                                    return true;
                                }

                                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Something wrong when execute this command!");
                                return false;
                            }

                            Bukkit.getConsoleSender().sendMessage("Unknown command. Type \"/help\" for help.");
                            return false;
                        }

                        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Please choose a number!");
                        return false;
                    }

                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "That player is not online!");
                    return false;
                }

                Bukkit.getConsoleSender().sendMessage("Unknown command. Type \"/help\" for help.");
                return false;
            }

            if (args.length == 4) {
                if (!args[0].equalsIgnoreCase("reset")) {
                    Bukkit.getConsoleSender().sendMessage("Unknown command. Type \"/help\" for help.");
                    return false;
                }

                p = Bukkit.getPlayer(args[1]);
                if (p == null) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "That player is not online!");
                    return false;
                }

                if (args[2].equalsIgnoreCase("page")) {
                    if (!args[3].equalsIgnoreCase("1") && !args[3].equalsIgnoreCase("2")) {
                        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Unknown page!");
                        return false;
                    } else if (DataWork.ResetPagePlayerWardrobe(p, args[3])) {
                        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] Success reset " + ChatColor.GOLD + args[2] + " " + args[3] + ChatColor.GREEN + " of " + ChatColor.GOLD + args[1] + "'s" + ChatColor.GREEN + " Wardrobe!");
                        return true;
                    } else {
                        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Something wrong when execute this command!");
                        return false;
                    }
                }

                if (!args[2].equalsIgnoreCase("slot")) {
                    Bukkit.getConsoleSender().sendMessage("Unknown command. Type \"/help\" for help.");
                    return false;
                }

                Number = args[3];
                if (Integer.valueOf(Number) < 1 || Integer.valueOf(Number) > 18) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Unknown slot!");
                    return false;
                }

                if (DataWork.ResetSlotPlayerWardrobe(p, Number)) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] Success reset " + ChatColor.GOLD + args[2] + " " + args[3] + ChatColor.GREEN + " of " + ChatColor.GOLD + args[1] + "'s" + ChatColor.GREEN + " Wardrobe!");
                    return true;
                }

                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Something wrong when execute this command!");
            }
        }

        p = (Player)sender;
        if (args.length == 0) {
            if (CheckPlayerGUI.onOpen && CheckPlayerGUI.Path.contains(p.getUniqueId().toString())) {
                p.sendMessage(ChatColor.RED + "An admin is opening your wardrobe, please wait!");
                return false;
            } else {
                WardrobeGUI.CreateWardrobePage1(p);
                return true;
            }
        } else if (p.hasPermission(Wardrobe.ConfigData.getConfig().getString("Admin-Permission"))) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    Wardrobe.ConfigData.ReloadConfig();
                    Wardrobe.Page_1.ReloadConfig();
                    Wardrobe.Page_2.ReloadConfig();
                    p.sendMessage(ChatColor.GREEN + "[Wardrobe] Success reloaded config!");
                    return true;
                } else if (args[0].equalsIgnoreCase("open")) {
                    WardrobeGUI.CreateWardrobePage1(p);
                    return true;
                } else if (!args[0].equalsIgnoreCase("reset") && !args[0].equalsIgnoreCase("check")) {
                    p.sendMessage("Unknown command. Type \"/help\" for help.");
                    return false;
                } else {
                    p.sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Please enter a player name!");
                    return false;
                }
            } else {
                Player target;
                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("open")) {
                        target = Bukkit.getPlayer(args[1]);
                        if (target != null) {
                            WardrobeGUI.CreateWardrobePage1(target);
                            return true;
                        } else {
                            p.sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "That player is not online!");
                            return false;
                        }
                    } else if (!args[0].equalsIgnoreCase("reset") && !args[0].equalsIgnoreCase("check")) {
                        p.sendMessage("Unknown command. Type \"/help\" for help.");
                        return false;
                    } else {
                        target = Bukkit.getPlayer(args[1]);
                        if (target != null) {
                            p.sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Please choose a work to do!");
                            return true;
                        } else {
                            p.sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "That player is not online!");
                            return false;
                        }
                    }
                } else if (args.length != 3) {
                    if (args.length == 4) {
                        if (args[0].equalsIgnoreCase("reset")) {
                            target = Bukkit.getPlayer(args[1]);
                            if (target != null) {
                                if (args[2].equalsIgnoreCase("page")) {
                                    if (!args[3].equalsIgnoreCase("1") && !args[3].equalsIgnoreCase("2")) {
                                        p.sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Unknown page!");
                                        return false;
                                    } else if (DataWork.ResetPagePlayerWardrobe(target, args[3])) {
                                        p.sendMessage(ChatColor.GREEN + "[Wardrobe] Success reset " + ChatColor.GOLD + args[2] + " " + args[3] + ChatColor.GREEN + " of " + ChatColor.GOLD + args[1] + "'s" + ChatColor.GREEN + " Wardrobe!");
                                        return true;
                                    } else {
                                        p.sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Something wrong when execute this command!");
                                        return false;
                                    }
                                } else if (args[2].equalsIgnoreCase("slot")) {
                                    Number = args[3];
                                    if (Integer.valueOf(Number) >= 1 && Integer.valueOf(Number) <= 18) {
                                        if (DataWork.ResetSlotPlayerWardrobe(target, Number)) {
                                            p.sendMessage(ChatColor.GREEN + "[Wardrobe] Success reset " + ChatColor.GOLD + args[2] + " " + args[3] + ChatColor.GREEN + " of " + ChatColor.GOLD + args[1] + "'s" + ChatColor.GREEN + " Wardrobe!");
                                            return true;
                                        } else {
                                            p.sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Something wrong when execute this command!");
                                            return false;
                                        }
                                    } else {
                                        p.sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Unknown slot!");
                                        return false;
                                    }
                                } else {
                                    p.sendMessage("Unknown command. Type \"/help\" for help.");
                                    return false;
                                }
                            } else {
                                p.sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "That player is not online!");
                                return false;
                            }
                        } else {
                            p.sendMessage("Unknown command. Type \"/help\" for help.");
                            return false;
                        }
                    } else {
                        p.sendMessage("Unknown command. Type \"/help\" for help.");
                        return false;
                    }
                } else if (args[0].equalsIgnoreCase("reset")) {
                    target = Bukkit.getPlayer(args[1]);
                    if (target != null) {
                        if (!args[2].equalsIgnoreCase("page") && !args[2].equalsIgnoreCase("slot")) {
                            if (args[2].equalsIgnoreCase("all")) {
                                if (DataWork.ResetAllPlayerWardrobe(target)) {
                                    p.sendMessage(ChatColor.GREEN + "[Wardrobe] Success reset " + ChatColor.GOLD + args[2] + ChatColor.GREEN + " of " + ChatColor.GOLD + args[1] + "'s" + ChatColor.GREEN + " Wardrobe!");
                                    return true;
                                } else {
                                    p.sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Something wrong when execute this command!");
                                    return false;
                                }
                            } else {
                                p.sendMessage("Unknown command. Type \"/help\" for help.");
                                return false;
                            }
                        } else {
                            p.sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "Please choose a number!");
                            return false;
                        }
                    } else {
                        p.sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "That player is not online!");
                        return false;
                    }
                } else if (!args[0].equalsIgnoreCase("check")) {
                    p.sendMessage("Unknown command. Type \"/help\" for help.");
                    return false;
                } else {
                    Number = args[1];
                    if (args[2].equalsIgnoreCase("1") && CheckPlayerGUI.CheckName(Number)) {
                        CheckPlayerGUI.CheckGUI1(p);
                    } else {
                        if (!args[2].equalsIgnoreCase("2") || !CheckPlayerGUI.CheckName(Number)) {
                            p.sendMessage(ChatColor.GREEN + "[Wardrobe] " + ChatColor.RED + "The player has not opened the Wardrobe yet");
                            return false;
                        }

                        CheckPlayerGUI.CheckGUI2(p);
                    }

                    return true;
                }
            }
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("open")) {
                WardrobeGUI.CreateWardrobePage1(p);
                return true;
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Wardrobe.ConfigData.getConfig().getString("Wardrobe_Message.Permission_Denied")));
                return false;
            }
        } else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Wardrobe.ConfigData.getConfig().getString("Wardrobe_Message.Permission_Denied")));
            return false;
        }
    }
}
