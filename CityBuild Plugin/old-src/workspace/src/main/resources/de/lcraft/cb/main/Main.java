package de.lcraft.cb.main;

import de.lcraft.cb.commands.CommandManager;
import de.lcraft.cb.commands.impl.*;
import de.lcraft.cb.languages.LanguagesManager;
import de.lcraft.cb.listeners.JoinListener;
import de.lcraft.cb.permissions.PermissionsManager;
import de.lcraft.cb.systems.SystemsManager;
import de.lcraft.cb.utils.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.*;
import org.bukkit.event.*;
import java.util.ArrayList;
import java.util.UUID;

public class Main extends JavaPlugin {

    private static Main plugin;
    private Config mainCFG;
    private LanguagesManager langManager;
    private PermissionsManager permsManager;
    public ArrayList<User> users;
    private CommandManager commandManager;
    private Starter starter;
    private SystemsManager sysManager;

    public synchronized void load() throws InterruptedException {
        plugin = this;
        users = new ArrayList<>();
        starter = new Starter();
        mainCFG = starter.startPlugin(mainCFG, plugin);
        permsManager = new PermissionsManager(plugin);
        langManager = new LanguagesManager();
        commandManager = new CommandManager(plugin);
        sysManager = new SystemsManager(plugin);
    }

    public  LanguagesManager getLangManager() {
        return langManager;
    }

    @Override
    public synchronized void onEnable() {
        plugin = this;
        try {
            load();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Register all Commands
        commandManager.registerCommand("tps", new TPSCommand(plugin));
        commandManager.registerCommand("gm", new GamemodeCommand(plugin));
        commandManager.registerCommand("gamemode", new GamemodeCommand(plugin));
        commandManager.registerCommand("spawn", new SpawnCommand(plugin));
        commandManager.registerCommand("setspawn", new SetSpawnCommand(plugin));
        commandManager.registerCommand("night", new NightCommand(plugin));
        commandManager.registerCommand("help", new HelpCommand(plugin));
        commandManager.registerCommand("day", new DayCommand(plugin));

        for(Player p : Bukkit.getOnlinePlayers()) {
            p.kickPlayer(Config.getOption(mainCFG, "server.reload.msg", "§6Please rejoin").toString());
        }

        if(Internet.SpigotMc.isOutdated(95641, "1.0.2", plugin)) {
            Internet.SpigotMc.getLatestVersion(95641, version -> {
                Bukkit.broadcastMessage(starter.PREFIX + LanguagesManager.translate("§cPlease update. New Version: %NEW%, Current Version: %OLD%", LanguagesManager.getNormalLanguage())
                        .replace("%NEW%", version).replace("%OLD%", "1.0.2"));
            }, plugin);
        }

        // Register all Listeners
        registerListener(new JoinListener(plugin));

        // Init the Starter
        starter.init(plugin);

        Bukkit.broadcastMessage(Starter.ON_START);
    }

    @Override
    public void onDisable() {
        Bukkit.broadcastMessage(Starter.ON_STOP);
        Bukkit.getScheduler().cancelTasks(plugin);
    }

    public void registerCommand(String cmd, Command executor) {
        getCommand(cmd).setExecutor(executor);
    }

    public void registerListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    public static Main getPlugin() {
        return plugin;
    }

    public Config getMainCFG() {
        return mainCFG;
    }

    public PermissionsManager getPermsManager() {
        return permsManager;
    }

    public  int getHighestY(Location loc) {
        int y = 255;
        while(new Location(loc.getWorld(), loc.getX(), y, loc.getZ()).getBlock().getType() == Material.AIR) {
            y--;
        }
        return y;
    }

    public User getUser(UUID p) {
        for(User c : users) {
            if(c != null && c.getUUID() != null && c.getUUID().equals(p)) {
                return c;
            }
        }
        return null;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public SystemsManager getSysManager() {
        return sysManager;
    }
    
}
