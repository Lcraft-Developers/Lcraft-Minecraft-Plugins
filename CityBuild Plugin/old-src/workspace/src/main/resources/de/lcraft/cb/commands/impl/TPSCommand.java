package de.lcraft.cb.commands.impl;

import de.lcraft.cb.languages.Language;
import de.lcraft.cb.languages.LanguagesManager;
import de.lcraft.cb.main.Main;
import de.lcraft.cb.utils.Command;
import de.lcraft.cb.utils.TPS;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TPSCommand extends Command {

    public TPSCommand(Main plugin) {
        super(plugin);
    }

    @Override
    public boolean run(CommandSender s, org.bukkit.command.Command cmd, String label, String[] args) {
        if(args.length == 0) {
            boolean hasPermissions = true;
            if(s instanceof Player) {
                hasPermissions = hasPermissions((Player) s, "cb.commands.tps", "cb.commands.admin", "cb.admin", "cb.*", "cb.commands.*");
            }

            if(hasPermissions) {
                if(s instanceof Player) {
                    s.sendMessage(PREFIX + translate((Player) s, "§aCurrent TPS: §6") + TPS.getTPS());
                } else {
                    s.sendMessage(PREFIX + translate(null, "§aCurrent TPS: §6") + TPS.getTPS());
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
                s.sendMessage(getHelpMessage((Player) s, "tps"));
            } else {
                s.sendMessage(getHelpMessage("tps"));
            }
        }
        return false;
    }

    @Override
    public ArrayList<String> allPermissions(ArrayList<String> allPerms) {
        if(!allPerms.contains("cb.commands.tps")) allPerms.add("cb.commands.tps");
        if(!allPerms.contains("cb.commands.admin")) allPerms.add("cb.commands.admin");
        if(!allPerms.contains("cb.admin")) allPerms.add("cb.admin");
        if(!allPerms.contains("cb.*")) allPerms.add("cb.*");
        if(!allPerms.contains("cb.commands.*")) allPerms.add("cb.commands.*");

        return allPerms;
    }

    @Override
    public ArrayList<String> allLanguages(ArrayList<String> allLang) {
        if(!allLang.contains("§aCurrent TPS: §6")) allLang.add("§aCurrent TPS: §6");
        if(!allLang.contains(getHelpMessage("tps"))) allLang.add(getHelpMessage("tps"));

        return allLang;
    }

}
