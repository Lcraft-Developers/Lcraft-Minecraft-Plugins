package de.lcraft.cb.manager;

import de.lcraft.cb.main.Main;
import de.lcraft.cb.utils.TabComplete;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import java.util.ArrayList;
import java.util.List;

public class TabCompleterManager implements Listener, TabCompleter {

    private Main plugin;
    private ArrayList<de.lcraft.cb.utils.TabComplete> tabs;

    public TabCompleterManager(Main plugin) {
        this.plugin = plugin;
        this.plugin.registerListener(this);
        tabs = new ArrayList<>();
    }

    public void addNewArg(String[] beforeArgs, ArrayList<String> possebilitis) {
        tabs.add(new de.lcraft.cb.utils.TabComplete(plugin, beforeArgs, possebilitis));
    }

    public Main getPlugin() {
        return plugin;
    }


    @Override
    public List<String> onTabComplete(CommandSender s, Command cmd, String lable, String[] args) {
        for(TabComplete c : tabs) {
            if(args == c.getBeforeArgs()) {
                return c.getPos();
            }
        }
        return null;
    }

}
