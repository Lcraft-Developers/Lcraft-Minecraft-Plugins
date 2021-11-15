package de.lcraft.cb.listeners;

import de.lcraft.cb.languages.LanguagesManager;
import de.lcraft.cb.main.Main;
import de.lcraft.cb.utils.Config;
import de.lcraft.cb.utils.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {

    private Main plugin;

    public JoinListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        boolean isMsg = (boolean) Config.getOption(plugin.getMainCFG(), "server.join.msg.enabled", true);
        Main.getPlugin().users.add(new User(e.getPlayer().getUniqueId()));
        e.setJoinMessage("");
        if(isMsg) {
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(LanguagesManager.getPlayer(p).getJoinMessage().replace("%PLAYER%", e.getPlayer().getName()));
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        boolean isMsg = (boolean) Config.getOption(plugin.getMainCFG(), "server.leave.msg.enabled", false);
        Main.getPlugin().users.remove(Main.getPlugin().getUser(e.getPlayer().getUniqueId()));
        e.setQuitMessage("");

        if(isMsg) {
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(LanguagesManager.getPlayer(p).getQuitMessage().replace("%PLAYER%", e.getPlayer().getName()));
            }
        }
    }

}
