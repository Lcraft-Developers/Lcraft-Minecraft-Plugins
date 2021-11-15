package de.lcraft.cb.systems.impl;

import de.lcraft.cb.languages.LanguagesManager;
import de.lcraft.cb.main.Main;
import de.lcraft.cb.systems.System;
import de.lcraft.cb.utils.Config;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class TPASystem extends System {

    //              From      To
    private HashMap<Player, Player> tpa;
    //              From      To
    private HashMap<Player, Player> tpahere;

    private Main plugin;
    private Config cfg;

    public TPASystem(Main plugin) {
        this.plugin = plugin;
        tpa = new HashMap<>();
        tpahere = new HashMap<>();
        cfg = new Config("tpa", "config.yml");
    }

    public void makeRequestTPA(Player from, Player to) {
        tpa.put(from, to);
        from.sendMessage(PREFIX + LanguagesManager.translate("§aYou send to the player %PLAYER% a TPA Request", from.getUniqueId()).replace("%PLAYER%", to.getName()));
        to.sendMessage("§7-----------------------------------------------");
        to.sendMessage(LanguagesManager.translate("§a%PLAYER% send your a TPA Request.", to.getUniqueId()).replace("%PLAYER%", from.getName()));
        to.sendMessage(LanguagesManager.translate("§6Do you want to accept?", to.getUniqueId()));
        TextComponent accept = new TextComponent(LanguagesManager.translate("§6Accept", to.getUniqueId()));
        accept.setColor(ChatColor.GOLD);
        TextComponent cancel = new TextComponent(LanguagesManager.translate("§cCancle", to.getUniqueId()));
        cancel.setColor(ChatColor.RED);

        accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "tpaaccept " + from.getName()));
        cancel.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "tpacancle " + from.getName()));

        to.spigot().sendMessage(accept);
        to.spigot().sendMessage(cancel);
        to.sendMessage("§7-----------------------------------------------");
    }

    public void cancelTPA(Player from, Player to) {
        tpa.remove(from);
        from.sendMessage(PREFIX + LanguagesManager.translate("§cThe Player %PLAYER% canceled the TPA Request.", from.getUniqueId()).replace("%PLAYER%", to.getName()));
        to.sendMessage(PREFIX + LanguagesManager.translate("§aYou canceled the TPA of %PLAYER%", from.getUniqueId()).replace("%PLAYER%", from.getName()));
    }

    private int later_tpa;

    public void acceptTPA(Player from, Player to) {
        tpa.remove(from);
        later_tpa = Integer.valueOf(String.valueOf(Config.getOption(cfg, "wait.secounds", 3).toString()));
        from.sendMessage(PREFIX + LanguagesManager.translate("§aThe Player %PLAYER% has accepted the TPA Request.", from.getUniqueId()).replace("%PLAYER%", to.getName()));
        to.sendMessage(PREFIX + LanguagesManager.translate("§aYou will be teleported in %SECOUNDS% secounds.", from.getUniqueId()).replace("%SECOUNDS%", String.valueOf(later_tpa).toString()));
        int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

            @Override
            public void run() {
                if(later_tpa > 0) {
                    later_tpa =- 1;
                } else {
                    from.teleport(to);
                    Thread.currentThread().interrupt();
                }
            }

        }, 0L, 20L);
    }






    public void makeRequestTPAHere(Player from, Player to) {
        tpahere.put(from, to);
        from.sendMessage(PREFIX + LanguagesManager.translate("§aYou send to the player %PLAYER% a TPAHere Request", from.getUniqueId()).replace("%PLAYER%", to.getName()));
        to.sendMessage("§7-----------------------------------------------");
        to.sendMessage(LanguagesManager.translate("§a%PLAYER% send your a TPAHere Request.", to.getUniqueId()).replace("%PLAYER%", from.getName()));
        to.sendMessage(LanguagesManager.translate("§6Do you want to accept?", to.getUniqueId()));
        TextComponent accept = new TextComponent(LanguagesManager.translate("§6Accept", to.getUniqueId()));
        accept.setColor(ChatColor.GOLD);
        TextComponent cancel = new TextComponent(LanguagesManager.translate("§cCancle", to.getUniqueId()));
        cancel.setColor(ChatColor.RED);

        accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "tpaaccept " + from.getName()));
        cancel.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "tpacancle " + from.getName()));

        to.spigot().sendMessage(accept);
        to.spigot().sendMessage(cancel);
        to.sendMessage("§7-----------------------------------------------");
    }

    public void cancelTPAHere(Player from, Player to) {
        tpahere.remove(from);
        from.sendMessage(PREFIX + LanguagesManager.translate("§cThe Player %PLAYER% canceled the TPAHere Request.", from.getUniqueId()).replace("%PLAYER%", to.getName()));
        to.sendMessage(PREFIX + LanguagesManager.translate("§aYou canceled the TPAHere of %PLAYER%", from.getUniqueId()).replace("%PLAYER%", from.getName()));
    }

    private int later_tpahere;

    public void acceptTPAHere(Player from, Player to) {
        tpahere.remove(from);
        later_tpahere = Integer.valueOf(String.valueOf(Config.getOption(cfg, "wait.secounds", 3).toString()));
        from.sendMessage(PREFIX + LanguagesManager.translate("§aThe Player %PLAYER% has accepted the TPAHere Request.", from.getUniqueId()).replace("%PLAYER%", to.getName()));
        from.sendMessage(PREFIX + LanguagesManager.translate("§aYou will be teleported in %SECOUNDS% secounds.", from.getUniqueId()).replace("%SECOUNDS%", String.valueOf(later_tpahere).toString()));
        int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

            @Override
            public void run() {
                if(later_tpahere > 0) {
                    later_tpahere =- 1;
                } else {
                    to.teleport(from);
                    Thread.currentThread().interrupt();
                }
            }

        }, 0L, 20L);
    }

    @Override
    public ArrayList<String> allPermissions(ArrayList<String> allPerms) {
        return allPerms;
    }

    @Override
    public ArrayList<String> allLanguages(ArrayList<String> allLang) {
        if(!allLang.contains("§aYou send to the player %PLAYER% a TPA Request")) allLang.add("§aYou send to the player %PLAYER% a TPA Request");
        if(!allLang.contains("§a%PLAYER% send your a TPA Request.")) allLang.add("§a%PLAYER% send your a TPA Request.");
        if(!allLang.contains("§6Do you want to accept?")) allLang.add("§6Do you want to accept?");
        if(!allLang.contains("§6Accept")) allLang.add("§6Accept");
        if(!allLang.contains("§cCancle")) allLang.add("§cCancle");
        if(!allLang.contains("§cThe Player %PLAYER% canceled the TPA Request.")) allLang.add("§cThe Player %PLAYER% canceled the TPA Request.");
        if(!allLang.contains("§aYou canceled the TPA of %PLAYER%")) allLang.add("§aYou canceled the TPA of %PLAYER%");
        if(!allLang.contains("§aThe Player %PLAYER% has accepted the TPA Request.")) allLang.add("§aThe Player %PLAYER% has accepted the TPA Request.");
        if(!allLang.contains("§aYou will be teleported in %SECOUNDS% secounds.")) allLang.add("§aYou will be teleported in %SECOUNDS% secounds.");
        if(!allLang.contains("§aYou send to the player %PLAYER% a TPAHere Request")) allLang.add("§aYou send to the player %PLAYER% a TPAHere Request");
        if(!allLang.contains("§a%PLAYER% send your a TPAHere Request.")) allLang.add("§a%PLAYER% send your a TPAHere Request.");
        if(!allLang.contains("§cThe Player %PLAYER% canceled the TPAHere Request.")) allLang.add("§cThe Player %PLAYER% canceled the TPAHere Request.");
        if(!allLang.contains("§aYou canceled the TPAHere of %PLAYER%")) allLang.add("§aYou canceled the TPAHere of %PLAYER%");
        if(!allLang.contains("§aThe Player %PLAYER% has accepted the TPAHere Request.")) allLang.add("§aThe Player %PLAYER% has accepted the TPAHere Request.");
        if(!allLang.contains("§aYou will be teleported in %SECOUNDS% secounds.")) allLang.add("§aYou will be teleported in %SECOUNDS% secounds.");

        return allLang;
    }

}
