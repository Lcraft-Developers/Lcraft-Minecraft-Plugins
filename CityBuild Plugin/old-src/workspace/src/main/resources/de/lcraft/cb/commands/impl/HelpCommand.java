package de.lcraft.cb.commands.impl;

import de.lcraft.cb.languages.Language;
import de.lcraft.cb.languages.LanguagesManager;
import de.lcraft.cb.main.Main;
import de.lcraft.cb.utils.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class HelpCommand extends Command {

    public HelpCommand(Main plugin) {
        super(plugin);
    }

    @Override
    public boolean run(CommandSender s, org.bukkit.command.Command cmd, String label, String[] args) {
        if(args.length == 0) {
            boolean hasPermissions = false;
            if(s instanceof Player) {
                if(hasPermissions((Player) s, "cb.*", "cb.admin", "cb.commands.*", "cb.commands.admin", "cb.commands.help")) {
                   hasPermissions = true;
                } else {
                    hasPermissions = false;
                }
            } else {
                hasPermissions = true;
            }

            if(hasPermissions) {
                Language lang = LanguagesManager.getNormalLanguage();
                if(s instanceof Player) {
                    lang = LanguagesManager.getPlayer((Player) s);
                }
                for(String c : lang.getHelp()) {
                    if(c.startsWith(" ")) {
                        s.sendMessage(c.replaceFirst(" ", ""));
                    } else {
                        s.sendMessage(c);
                    }
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
                s.sendMessage(getHelpMessage((Player) s, "help"));
            } else {
                s.sendMessage(getHelpMessage("help"));
            }
        }
        return false;
    }

    @Override
    public ArrayList<String> allPermissions(ArrayList<String> allPerms) {
        return allPerms;
    }

    @Override
    public ArrayList<String> allLanguages(ArrayList<String> allLang) {
        return allLang;
    }

}
