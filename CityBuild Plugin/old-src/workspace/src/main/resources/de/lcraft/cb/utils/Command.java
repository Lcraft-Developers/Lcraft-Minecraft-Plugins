package de.lcraft.cb.utils;

import de.lcraft.cb.languages.Language;
import de.lcraft.cb.languages.LanguagesManager;
import de.lcraft.cb.main.Main;
import de.lcraft.cb.main.Starter;
import de.lcraft.cb.manager.TabCompleterManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import java.util.ArrayList;
import java.util.UUID;

public abstract class Command extends Starter implements CommandExecutor, Listener {
	
	protected static Main plugin;
	private static TabCompleterManager tabCompleterManager;

	public Command(Main plugin) {
		this.plugin = plugin;
		plugin.registerListener(this);
		tabCompleterManager = new TabCompleterManager(plugin);
	}
	
	public abstract boolean run(CommandSender s, org.bukkit.command.Command cmd, String label, String[] args);
	public abstract ArrayList<String> allPermissions(ArrayList<String> allPerms);
	public abstract ArrayList<String> allLanguages(ArrayList<String> allLang);
	
	@Override
	public boolean onCommand(CommandSender arg0, org.bukkit.command.Command arg1, String arg2, String[] arg3) {
		return run(arg0, arg1, arg2, arg3);
	}

	public boolean hasPermissions(Player p, String perm) {
		return Main.getPlugin().getPermsManager().hasPermissions(p, perm);
	}

	public boolean hasPermissions(Player p, String... perm) {
		boolean a = false;
		for(String c : perm) {
			if(Main.getPlugin().getPermsManager().hasPermissions(p, c)) {
				a = true;
			}
		}
		return a;
	}

	public void addTabComplete(String[] beforeArgs, ArrayList<String> possebilitis) {
		tabCompleterManager.addNewArg(beforeArgs, possebilitis);
	}

	public String getHelpMessage(Language lang, String... help) {
		String end = PREFIX + LanguagesManager.translate("§cPlease use", lang) + " §6/" + help[0];
		for(int i = 1; i < help.length; i++) {
			end = end + " " + LanguagesManager.translate("§cor", lang) + " §6/" + help[i];
		}
		end = end + " §c!";
		if(lang == null) {
			return end;
		}
		return end;
	}

	public String NO_PERMISSIONS(UUID p) {
		if(p == null) {
			return PREFIX + LanguagesManager.translate(NO_PERMISSIONS, LanguagesManager.getNormalLanguage());
		} else {
			return PREFIX + LanguagesManager.translate(NO_PERMISSIONS, p);
		}
	}

	public String NO_WORLD(UUID p) {
		if(p == null) {
			return PREFIX + LanguagesManager.translate(WORLD_NOT_FOUND, LanguagesManager.getNormalLanguage());
		} else {
			return PREFIX + LanguagesManager.translate(WORLD_NOT_FOUND, p);
		}
	}

	public String NO_PLAYER(UUID p) {
		if(p == null) {
			return PREFIX + LanguagesManager.translate(NO_PLAYER, LanguagesManager.getNormalLanguage());
		} else {
			return PREFIX + LanguagesManager.translate(NO_PLAYER, p);
		}
	}

	public String translate(Player p, String msg) {
		if(p != null) {
			return PREFIX + LanguagesManager.translate(msg, p.getUniqueId());
		} else {
			return PREFIX + LanguagesManager.translate(msg, LanguagesManager.getNormalLanguage());
		}
	}

	public String getHelpMessage(Player p, String... help) {
		return getHelpMessage(LanguagesManager.getPlayer(p.getUniqueId()), help);
	}

	public String getHelpMessage(String... help) {
		return getHelpMessage(LanguagesManager.getNormalLanguage(), help);
	}

	public TabCompleterManager getTabCompleterManager() {
		return tabCompleterManager;
	}

}
