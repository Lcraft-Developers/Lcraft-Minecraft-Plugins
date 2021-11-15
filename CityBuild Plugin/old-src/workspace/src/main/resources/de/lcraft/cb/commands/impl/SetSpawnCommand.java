package de.lcraft.cb.commands.impl;

import de.lcraft.cb.main.Main;
import de.lcraft.cb.utils.Command;
import de.lcraft.cb.utils.Config;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SetSpawnCommand extends Command {

    public SetSpawnCommand(Main plugin) {
        super(plugin);
    }

    @Override
    public boolean run(CommandSender s, org.bukkit.command.Command cmd, String label, String[] args) {
        if(s instanceof Player) {
            Player p = (Player) s;
            if(args.length == 0) {
                if(hasPermissions(p, "cb.admin", "cb.*", "cb.command.*", "cb.command.setspawn", "cb.command.admin")) {
                    Config cfg = new Config("spawn.yml");
                    cfg.saveLocation("spawn.loc", p.getLocation());
                    p.sendMessage(PREFIX + translate(p, "§aThe Spawn is now setted."));
                } else {
                    p.sendMessage(NO_PERMISSIONS(p.getUniqueId()));
                }
            } else {
                p.sendMessage(getHelpMessage(p, "setspawn"));
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
        if(!allLang.contains(getHelpMessage("setspawn"))) allLang.add(getHelpMessage("setspawn"));
        if(!allLang.contains("§aThe Spawn is now setted.")) allLang.add("§aThe Spawn is now setted.");

        return allLang;
    }

}
