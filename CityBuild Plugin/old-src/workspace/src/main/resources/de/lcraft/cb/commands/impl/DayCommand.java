package de.lcraft.cb.commands.impl;

import de.lcraft.cb.main.Main;
import de.lcraft.cb.utils.Command;
import de.lcraft.cb.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class DayCommand extends Command {

    public DayCommand(Main plugin) {
        super(plugin);
    }

    @Override
    public boolean run(CommandSender s, org.bukkit.command.Command cmd, String label, String[] args) {
        boolean perms = false;
        if(s instanceof Player) {
            if(hasPermissions((Player) s, "cb.*", "cb.admin", "cb.commands.*", "cb.commands.admin", "cb.commands.day.*", "cb.commands.day.admin", "cb.commands.day.allworlds", "cb.commands.day.world", "cb.commands.day.worlds.own")) {
                perms = true;
            }
        } else {
            perms = true;
        }

        int time = Integer.valueOf(Config.getOption(plugin.getMainCFG(), "time.day", "600").toString());

        if(perms) {
            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("All")) {
                    boolean hasPermissions = true;

                    if(s instanceof Player) hasPermissions = hasPermissions((Player) s, "cb.*", "cb.admin", "cb.commands.*", "cb.commands.admin", "cb.commands.day.*", "cb.commands.day.admin", "cb.commands.day.worlds.all");

                    if(hasPermissions) {
                        for(World w : Bukkit.getWorlds()) {
                            w.setFullTime(time);
                        }
                        if(s instanceof Player) {
                            s.sendMessage(translate((Player) s, "§aIn all Worlds the time has been seted to §6%TIME%§a.").replace("%TIME%", time + ""));
                        } else {
                            s.sendMessage(translate(null, "§aIn all Worlds the time has been seted to §6%TIME%§a.").replace("%TIME%", time + ""));
                        }
                    } else {
                        if(s instanceof Player) {
                            s.sendMessage(NO_PERMISSIONS(((Player) s).getUniqueId()));
                        } else {
                            s.sendMessage(NO_PERMISSIONS(null));
                        }
                    }
                } else {
                    World w = Bukkit.getWorld(args[0]);
                    if(w != null) {
                        boolean hasPermissions = true;
                        if(s instanceof Player) hasPermissions = hasPermissions((Player) s, "cb.*", "cb.admin", "cb.commands.*", "cb.commands.admin", "cb.commands.day.*", "cb.commands.day.admin", "cb.commands.day.world");

                        if(hasPermissions) {
                            w.setFullTime(time);
                            if(s instanceof Player) {
                                s.sendMessage(translate((Player) s, "§aIn the World %WORLD% the time has been seted to §6%TIME%§a.").replace("%TIME%", time + "").replace("%WORLD%", w.getName()));
                            } else {
                                s.sendMessage(translate(null, "§aIn the World %WORLD% the time has been seted to §6%TIME%§a.").replace("%TIME%", time + "").replace("%WORLD%", w.getName()));
                            }
                        } else {
                            if(s instanceof Player) {
                                s.sendMessage(NO_PERMISSIONS(((Player) s).getUniqueId()));
                            } else {
                                s.sendMessage(NO_PERMISSIONS(null));
                            }
                        }

                    } else {
                        Player c = Bukkit.getPlayer(args[0]);
                        if(c != null) {
                            boolean hasPermissions = true;
                            if(s instanceof Player) hasPermissions = hasPermissions((Player) s, "cb.*", "cb.admin", "cb.commands.*", "cb.commands.admin", "cb.commands.day.*", "cb.commands.day.admin", "cb.commands.day.worlds.other");
                            if(hasPermissions) {
                                c.getLocation().getWorld().setFullTime(time);
                                if(s instanceof Player) {
                                    s.sendMessage(translate((Player) s, "§aIn the World %WORLD% the time has been seted to §6%TIME%§a.").replace("%TIME%", time + "").replace("%WORLD%", c.getLocation().getWorld().getName()));
                                } else {
                                    s.sendMessage(translate(null, "§aIn the World %WORLD% the time has been seted to §6%TIME%§a.").replace("%TIME%", time + "").replace("%WORLD%", c.getLocation().getWorld().getName()));
                                }
                            } else {
                                if(s instanceof Player) {
                                    s.sendMessage(NO_PERMISSIONS(((Player) s).getUniqueId()));
                                } else {
                                    s.sendMessage(NO_PERMISSIONS(null));
                                }
                            }
                        } else {
                            if(s instanceof Player) {
                                s.sendMessage(NO_WORLD(((Player) s).getUniqueId()));
                            } else {
                                s.sendMessage(NO_WORLD(null));
                            }
                        }
                    }
                }
            } else if(args.length == 0) {
                if(s instanceof Player) {
                    Player p = (Player) s;
                    boolean hasPermissions = hasPermissions((Player) s, "cb.*", "cb.admin", "cb.commands.*", "cb.commands.admin", "cb.commands.day.*", "cb.commands.day.admin", "cb.commands.day.worlds.player");

                    if(hasPermissions) {
                        p.getLocation().getWorld().setFullTime(time);
                        p.sendMessage(translate(p, "§aIn the World %WORLD% the time has been seted to §6%TIME%§a.").replace("%TIME%", time + "").replace("%WORLD%", p.getLocation().getWorld().getName()));
                    } else {
                        p.sendMessage(NO_PERMISSIONS(p.getUniqueId()));
                    }
                } else {
                    s.sendMessage(NO_PLAYER(null));
                }
            } else {
                s.sendMessage(getHelpMessage("day", "day all", "day [World]", "day [Player]"));
            }
        } else {
            if(s instanceof Player) {
                s.sendMessage(NO_PERMISSIONS(((Player) s).getUniqueId()));
            } else {
                s.sendMessage(NO_PERMISSIONS(null));
            }
        }
        return false;
    }

    @Override
    public ArrayList<String> allPermissions(ArrayList<String> allPerms) {
        if(!allPerms.contains("cb.commands.day.worlds.player")) allPerms.add("cb.commands.day.worlds.player");
        if(!allPerms.contains("cb.commands.day.worlds.other")) allPerms.add("cb.commands.day.worlds.other");
        if(!allPerms.contains("cb.commands.day.worlds.all")) allPerms.add("cb.commands.day.worlds.all");
        if(!allPerms.contains("cb.commands.day.worlds.own")) allPerms.add("cb.commands.day.worlds.own");
        if(!allPerms.contains("cb.commands.day.world")) allPerms.add("cb.commands.day.world");

        if(!allPerms.contains("cb.commands.admin")) allPerms.add("cb.commands.admin");
        if(!allPerms.contains("cb.admin")) allPerms.add("cb.admin");
        if(!allPerms.contains("cb.*")) allPerms.add("cb.*");
        if(!allPerms.contains("cb.commands.*")) allPerms.add("cb.commands.*");

        return allPerms;
    }

    @Override
    public ArrayList<String> allLanguages(ArrayList<String> allLang) {
        if(!allLang.contains("§aIn the World %WORLD% the time has been seted to §6%TIME%§a.")) allLang.add("§aIn the World %WORLD% the time has been seted to §6%TIME%§a.");
        if(!allLang.contains(getHelpMessage("§aIn all Worlds the time has been seted to §6%TIME%§a."))) allLang.add(getHelpMessage("§aIn all Worlds the time has been seted to §6%TIME%§a."));
        if(!allLang.contains(getHelpMessage("day", "day all", "day [World]", "day [Player]"))) allLang.add(getHelpMessage("day", "day all", "day [World]", "day [Player]"));

        return allLang;
    }

}
