package de.lcraft.cb.commands.impl;

import de.lcraft.cb.main.Main;
import de.lcraft.cb.utils.Command;
import de.lcraft.cb.utils.User;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class GamemodeCommand extends Command {

    public GamemodeCommand(Main plugin) {
        super(plugin);
    }

    @Override
    public boolean run(CommandSender s, org.bukkit.command.Command cmd, String label, String[] args) {
        ArrayList<String> before;

        // /gamemode [Player, Gamemode]
        before = new ArrayList<>();
        before.add("0");
        before.add("1");
        before.add("2");
        before.add("3");
        before.add("survival");
        before.add("creative");
        before.add("adventure");
        before.add("spectator");
        for(User p : Main.getPlugin().users) {
            if(p.getPlayer() != null) {
                before.add(p.getPlayer().getName());
            }
        }
        addTabComplete(new String[]{}, before);

        // /gamemode [Player] Gamemode
        for(User p : Main.getPlugin().users) {
            before = new ArrayList<>();
            if(p.getPlayer() != null) {
                String[] a = new String[1];
                a[0] = p.getPlayer().getName();
                before.add("0");
                before.add("1");
                before.add("2");
                before.add("3");
                before.add("survival");
                before.add("creative");
                before.add("adventure");
                before.add("spectator");
                addTabComplete(new String[]{p.getPlayer().getName()}, before);
            }
        }

        if(args.length == 2) {
            String cS = args[0];
            Player cU = Bukkit.getPlayer(cS);
            if(cU != null) {
                User c = Main.getPlugin().getUser(cU.getUniqueId());
                String gm = args[1];
                boolean hasPermissions = false;
                if(s instanceof Player) {
                    Player p = (Player) s;
                    if(hasPermissions(p, "cb.gamemode.other", "cb.gamemode.other.0", "cb.gamemode.other.1", "cb.gamemode.other.2", "cb.gamemode.other.3") ||
                            hasPermissions(p, "cb.admin", "cb.*", "cb.gamemode.other.*", "cb.gamemode.*", "cb.gamemode.admin")) {
                        hasPermissions = true;
                    }
                } else {
                    hasPermissions = true;
                }

                if(hasPermissions) {
                    if(gm.equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                        if(s instanceof Player) {
                            if(hasPermissions((Player)s, "cb.admin", "cb.*") || hasPermissions((Player)s, "cb.gamemode.other.*") || hasPermissions((Player)s, "cb.gamemode.other.0") ||
                            hasPermissions((Player)s, "cb.gamemode.admin", "cb.gamemode.*") || hasPermissions((Player) s, "cb.gamemode.*")) {
                                c.setGamemode("0");
                                cU.sendMessage(translate(cU, "§aYour gamemode has changed to §6Survival"));
                                s.sendMessage(translate((Player)s, "§aPlayer §6%PLAYER%s§a gamemode has been changed to §6Survival").replace("%PLAYER%", cU.getName()));
                            } else {
                                s.sendMessage(NO_PERMISSIONS(((Player) s).getUniqueId()));
                            }
                        } else {
                            c.setGamemode("0");
                            cU.sendMessage(translate(cU, "§aYour gamemode has changed to §6Survival"));
                            s.sendMessage(translate(null, "§aPlayer §6%PLAYER%s§a gamemode has been changed to §6Survival").replace("%PLAYER%", cU.getName()));
                        }
                    } else if(gm.equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
                        if(s instanceof Player) {
                            if(hasPermissions((Player)s, "cb.admin", "cb.*") || hasPermissions((Player)s, "cb.gamemode.other.*") || hasPermissions((Player)s, "cb.gamemode.other.1") ||
                                    hasPermissions((Player)s, "cb.gamemode.admin", "cb.gamemode.*") || hasPermissions((Player) s, "cb.gamemode.*")) {
                                c.setGamemode("1");
                                cU.sendMessage(translate(cU, "§aYour gamemode has changed to §6Creative"));
                                s.sendMessage(translate((Player)s, "§aPlayer §6%PLAYER%s§a gamemode has been changed to §6Creative").replace("%PLAYER%", cU.getName()));
                            } else {
                                s.sendMessage(NO_PERMISSIONS(((Player) s).getUniqueId()));
                            }
                        } else {
                            c.setGamemode("0");
                            cU.sendMessage(translate(cU, "§aYour gamemode has changed to §6Creative"));
                            s.sendMessage(translate(null, "§aPlayer §6%PLAYER%s§a gamemode has been changed to §6Creative").replace("%PLAYER%", cU.getName()));
                        }
                    } else if(gm.equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                        if(s instanceof Player) {
                            if(hasPermissions((Player)s, "cb.admin", "cb.*") || hasPermissions((Player)s, "cb.gamemode.other.*") || hasPermissions((Player)s, "cb.gamemode.other.2") ||
                                    hasPermissions((Player)s, "cb.gamemode.admin", "cb.gamemode.*") || hasPermissions((Player) s, "cb.gamemode.*")) {
                                c.setGamemode("2");
                                cU.sendMessage(translate(cU, "§aYour gamemode has changed to §6Adventure"));
                                s.sendMessage(translate((Player)s, "§aPlayer §6%PLAYER%s§a gamemode has been changed to §6Adventure").replace("%PLAYER%", cU.getName()));
                            } else {
                                s.sendMessage(NO_PERMISSIONS(((Player) s).getUniqueId()));
                            }
                        } else {
                            c.setGamemode("2");
                            cU.sendMessage(translate(cU, "§aYour gamemode has changed to §6Adventure"));
                            s.sendMessage(translate(null, "§aPlayer §6%PLAYER%s§a gamemode has been changed to §6Adventure").replace("%PLAYER%", cU.getName()));
                        }
                    } else if(gm.equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                        if(s instanceof Player) {
                            if(hasPermissions((Player)s, "cb.admin, \"cb.*\"") || hasPermissions((Player)s, "cb.gamemode.other.*") || hasPermissions((Player)s, "cb.gamemode.other.0") ||
                                    hasPermissions(null, "cb.gamemode.admin", "cb.gamemode.*") || hasPermissions((Player) s, "cb.gamemode.*")) {
                                c.setGamemode("3");
                                cU.sendMessage(translate(cU, "§aYour gamemode has changed to §6Spectator"));
                                s.sendMessage(translate((Player)s, "§aPlayer §6%PLAYER%s§a gamemode has been changed to §6Spectator").replace("%PLAYER%", cU.getName()));
                            } else {
                                s.sendMessage(NO_PERMISSIONS(((Player) s).getUniqueId()));
                            }
                        } else {
                            c.setGamemode("3");
                            cU.sendMessage(translate(cU, "§aYour gamemode has changed to §6Spectator"));
                            s.sendMessage(translate(null, "§aPlayer §6%PLAYER%s§a gamemode has been changed to §6Speactator").replace("%PLAYER%", cU.getName()));
                        }
                    } else {
                        s.sendMessage(getHelpMessage("gm [Player] [0, 1, 2, 3]", "gm [Player] [survival, creative, adventure, spectator]", "gamemode [Player] [0, 1, 2, 3]", "gamemode [Player] [survival, creative, adventure, spectator]"));
                    }
                } else {
                    s.sendMessage(NO_PERMISSIONS(null));
                }
            } else {
                s.sendMessage(NO_PLAYER(null));
            }
        } else if(args.length == 1) {
            if(s instanceof Player) {
                Player p = (Player) s;
                User u = Main.getPlugin().getUser(p.getUniqueId());
                if(hasPermissions(p, "cb.gamemode.self", "cb.gamemode.self.*", "cb.gamemode.self.0", "cb.gamemode.self.1", "cb.gamemode.self.2", "cb.gamemode.self.3") ||
                        hasPermissions(p, "cb.gamemode.admin", "cb.gamemode.*", "cb.admin", "cb.*")) {
                    if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                        if(hasPermissions(p, "cb.admin", "cb.*", "cb.gamemode.self.*", "cb.gamemode.self.0", "cb.gamemode.admin", "cb.gamemode.*")) {
                            u.setGamemode("0");
                            p.sendMessage(translate(p, "§aYour gamemode has changed to §6Survival"));
                        } else {
                            p.sendMessage(NO_PERMISSIONS(p.getUniqueId()));
                        }
                    } else if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
                        if(hasPermissions(p, "cb.admin", "cb.*", "cb.gamemode.self.*", "cb.gamemode.self.1", "cb.gamemode.admin", "cb.gamemode.*")) {
                            u.setGamemode("1");
                            p.sendMessage(translate(p, "§aYour gamemode has changed to §6Creative"));
                        } else {
                            p.sendMessage(NO_PERMISSIONS(p.getUniqueId()));
                        }
                    } else if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                        if(hasPermissions(p, "cb.admin", "cb.*", "cb.gamemode.self.*", "cb.gamemode.self.2", "cb.gamemode.admin", "cb.gamemode.*")) {
                            u.setGamemode("2");
                            p.sendMessage(translate(p, "§aYour gamemode has changed to §6Adventure"));
                        } else {
                            p.sendMessage(NO_PERMISSIONS(p.getUniqueId()));
                        }
                    } else if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                        if(hasPermissions(p, "cb.admin", "cb.*", "cb.gamemode.self.*", "cb.gamemode.self.3", "cb.gamemode.admin", "cb.gamemode.*")) {
                            u.setGamemode("3");
                            p.sendMessage(translate(p, "§aYour gamemode has changed to §6Spectator"));
                        } else {
                            p.sendMessage(NO_PERMISSIONS(p.getUniqueId()));
                        }
                    } else {
                        p.sendMessage(getHelpMessage(p, "gm [0, 1, 2, 3]", "gm [survival, creative, adventure, spectator]", "gamemode [0, 1, 2, 3]", "gamemode [survival, creative, adventure, spectator]"));
                    }
                } else {
                    p.sendMessage(NO_PERMISSIONS(p.getUniqueId()));
                }
            } else {
                s.sendMessage(NO_PLAYER(null));
            }
        } else {
            s.sendMessage(getHelpMessage("gamemode [Gamemode]", "gamemode [Player] [Gamemode]", "gm [Gamemode]", "gm [Player] [Gamemode]"));
        }

        return false;
    }

    @Override
    public ArrayList<String> allPermissions(ArrayList<String> allPerms) {
        if(!allPerms.contains("cb.gamemode.other")) allPerms.add("cb.gamemode.other");
        if(!allPerms.contains("cb.gamemode.other.0")) allPerms.add("cb.gamemode.other.0");
        if(!allPerms.contains("cb.gamemode.other.1")) allPerms.add("cb.gamemode.other.1");
        if(!allPerms.contains("cb.gamemode.other.2")) allPerms.add("cb.gamemode.other.2");
        if(!allPerms.contains("cb.gamemode.other.3")) allPerms.add("cb.gamemode.other.3");
        if(!allPerms.contains("cb.gamemode.other.admin")) allPerms.add("cb.gamemode.other.admin");
        if(!allPerms.contains("cb.gamemode.other.*")) allPerms.add("cb.gamemode.other.*");

        if(!allPerms.contains("cb.gamemode.self")) allPerms.add("cb.gamemode.self");
        if(!allPerms.contains("cb.gamemode.self.0")) allPerms.add("cb.gamemode.self.0");
        if(!allPerms.contains("cb.gamemode.self.1")) allPerms.add("cb.gamemode.self.1");
        if(!allPerms.contains("cb.gamemode.self.2")) allPerms.add("cb.gamemode.self.2");
        if(!allPerms.contains("cb.gamemode.self.3")) allPerms.add("cb.gamemode.self.3");
        if(!allPerms.contains("cb.gamemode.self.admin")) allPerms.add("cb.gamemode.self.admin");
        if(!allPerms.contains("cb.gamemode.self.*")) allPerms.add("cb.gamemode.self.*");

        if(!allPerms.contains("cb.gamemode.*")) allPerms.add("cb.gamemode.*");
        if(!allPerms.contains("cb.gamemode.admin")) allPerms.add("cb.gamemode.admin");
        if(!allPerms.contains("cb.admin")) allPerms.add("cb.admin");
        if(!allPerms.contains("cb.*")) allPerms.add("cb.*");

        return allPerms;
    }

    @Override
    public ArrayList<String> allLanguages(ArrayList<String> allLang) {
        if(!allLang.contains("§aYour gamemode has changed to §6Survival")) allLang.add("§aYour gamemode has changed to §6Survival");
        if(!allLang.contains("§aYour gamemode has changed to §6Creative")) allLang.add("§aYour gamemode has changed to §6Creative");
        if(!allLang.contains("§aYour gamemode has changed to §6Adventure")) allLang.add("§aYour gamemode has changed to §6Adventure");
        if(!allLang.contains("§aYour gamemode has changed to §6Spectator")) allLang.add("§aYour gamemode has changed to §6Spectator");
        if(!allLang.contains("§aPlayer §6%PLAYER%s§a gamemode has been changed to §6Survival")) allLang.add("aPlayer §6%PLAYER%s§a gamemode has been changed to §6Survival");
        if(!allLang.contains("§aPlayer §6%PLAYER%s§a gamemode has been changed to §6Creative")) allLang.add("aPlayer §6%PLAYER%s§a gamemode has been changed to §6Creative");
        if(!allLang.contains("§aPlayer §6%PLAYER%s§a gamemode has been changed to §6Adventure")) allLang.add("aPlayer §6%PLAYER%s§a gamemode has been changed to §6Adventure");
        if(!allLang.contains("§aPlayer §6%PLAYER%s§a gamemode has been changed to §6Spectator")) allLang.add("aPlayer §6%PLAYER%s§a gamemode has been changed to §6Spectator");
        if(!allLang.contains(getHelpMessage("gamemode [Gamemode]", "gamemode [Player] [Gamemode]", "gm [Gamemode]", "gm [Player] [Gamemode]"))) allLang.add(getHelpMessage("gamemode [Gamemode]", "gamemode [Player] [Gamemode]", "gm [Gamemode]", "gm [Player] [Gamemode]"));
        if(!allLang.contains(getHelpMessage("gm [0, 1, 2, 3]", "gm [survival, creative, adventure, spectator]", "gamemode [0, 1, 2, 3]", "gamemode [survival, creative, adventure, spectator]"))) allLang.add(getHelpMessage("gm [Player] [0, 1, 2, 3]", "gm [Player] [survival, creative, adventure, spectator]", "gamemode [Player] [0, 1, 2, 3]"));
        if(!allLang.contains(getHelpMessage("gm [0, 1, 2, 3]", "gm [survival, creative, adventure, spectator]", "gamemode [0, 1, 2, 3]", "gamemode [survival, creative, adventure, spectator]"))) allLang.add(getHelpMessage("gm [0, 1, 2, 3]", "gm [survival, creative, adventure, spectator]", "gamemode [0, 1, 2, 3]", "gamemode [survival, creative, adventure, spectator]"));

        return allLang;
    }

}
