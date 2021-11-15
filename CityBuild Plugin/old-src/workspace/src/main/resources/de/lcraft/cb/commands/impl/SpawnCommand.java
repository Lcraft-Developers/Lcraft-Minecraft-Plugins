package de.lcraft.cb.commands.impl;

import de.lcraft.cb.main.Main;
import de.lcraft.cb.utils.Command;
import de.lcraft.cb.utils.Config;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SpawnCommand extends Command {

    public SpawnCommand(Main plugin) {
        super(plugin);
    }

    @Override
    public boolean run(CommandSender s, org.bukkit.command.Command cmd, String label, String[] args) {
        if(s instanceof Player) {
            Player p = (Player) s;
            if(args.length == 0) {
                if(hasPermissions(p, "cb.admin", "cb.*", "cb.command.spawn", "cb.command.admin", "cb.command.*")) {
                    Config cfg = new Config("spawn.yml");
                    Location loc = cfg.getLocation("spawn.loc");
                    if(loc == null) {
                        p.sendMessage(PREFIX + translate(p, "§cThe spawn location has not been set yet."));
                    } else {
                        p.teleport(loc);
                        p.sendMessage(PREFIX + translate(p, "§aYou have been teleported to the spawn."));
                    }
                } else {
                    p.sendMessage(NO_PERMISSIONS(p.getUniqueId()));
                }
            } else {
                p.sendMessage(getHelpMessage(p, "spawn"));
            }
        } else {
            s.sendMessage(NO_PLAYER(null));
        }
        return false;
    }

    @Override
    public ArrayList<String> allPermissions(ArrayList<String> allPerms) {
        if(!allPerms.contains("cb.admin")) allPerms.add(getHelpMessage("cb.admin"));
        if(!allPerms.contains("cb.*")) allPerms.add(getHelpMessage("cb.*"));
        if(!allPerms.contains("cb.admin")) allPerms.add(getHelpMessage("cb.command.admin"));
        if(!allPerms.contains("cb.admin")) allPerms.add(getHelpMessage("cb.command.*"));
        if(!allPerms.contains("cb.admin")) allPerms.add(getHelpMessage("cb.admin"));
        return allPerms;
    }

    @Override
    public ArrayList<String> allLanguages(ArrayList<String> allLang) {
        if(!allLang.contains(getHelpMessage("spawn"))) allLang.add(getHelpMessage("spawn"));
        if(!allLang.contains("§aYou have been teleported to the spawn.")) allLang.add("§aYou have been teleported to the spawn.");
        if(!allLang.contains("§cThe spawn location has not been set yet.")) allLang.add("§cThe spawn location has not been set yet.");
        return allLang;
    }

}