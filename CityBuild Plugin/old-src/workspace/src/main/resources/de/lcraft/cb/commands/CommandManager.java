package de.lcraft.cb.commands;

import de.lcraft.cb.main.Main;
import de.lcraft.cb.utils.Command;
import org.bukkit.Bukkit;

import java.util.ArrayList;

public class CommandManager {

    private Main plugin;
    private ArrayList<Command> cmds;

    public CommandManager(Main plugin) throws InterruptedException {
        this.plugin = plugin;
        cmds = new ArrayList<>();
    }

    public void registerCommand(String cmd, Command executor) {
        plugin.getCommand(cmd).setExecutor(executor);
        plugin.getCommand(cmd).setTabCompleter(executor.getTabCompleterManager());
        cmds.add(executor);
    }

    public ArrayList<String> docAllPermissions() {
        ArrayList<String> allPerms = new ArrayList<>();

        for(Command cmd : cmds) {
            allPerms = cmd.allPermissions(allPerms);
        }

        return allPerms;
    }

    public ArrayList<String> docAllTranslations() {
        ArrayList<String> allLangs = new ArrayList<>();

        for(Command cmd : cmds) {
            allLangs = cmd.allLanguages(allLangs);
        }

        return allLangs;
    }

}
