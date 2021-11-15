package de.lpd.challenges.chg;

import de.lpd.challenges.invs.Inventory;
import de.lpd.challenges.invs.InventoryManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import de.lpd.challenges.main.ChallengesMainClass;
import de.lpd.challenges.utils.Config;

public abstract class Challenge extends Inventory implements Listener {
	
	public abstract void cfg(Config cfg);
	public abstract ItemStack getItem(Player p);
	public abstract void onRightClick(Player p);
	public abstract void onLeftClick(Player p);
	public abstract void onMiddleClick(Player p);
	public abstract void reset();
	public abstract void ifPlayerDies(Player p);

	private String root;
	private String id;

	public String getId() {
		return id;
	}

	public Challenge(ChallengesMainClass plugin, String cfgPath, String filename, String root, int size, boolean hasMoreThen1Site, String name, String backName, String id, String showBackName) {
		super(plugin, size, hasMoreThen1Site, name, backName, showBackName, new Config("challenges//" + cfgPath, "inv.yml"));
		plugin.registerListener(this);
		cfg(new Config("challenges//" + cfgPath, filename));
		ChallengesMainClass.getInvManager().invs.put(id, this);
		if(!ChallengesMainClass.getInvManager().invs.containsKey(id)) {
			ChallengesMainClass.getInvManager().invs.put(id, this);
		}
		this.root = root;
		this.id = id;
	}
	
	public static Object getOption(Config cfg, String path, Object start) {
		if(cfg.cfg().contains(path)) {
			return cfg.cfg().get(path);
		} else {
			cfg.cfg().set(path, start);
			cfg.save();
			return start;
		}
	}
	
	public static void setOption(Config cfg, String path, Object obj) {
		cfg.cfg().set(path, obj);
		cfg.save();
	}
	
	public boolean isEnabled(String option) {
		if(isToggled(option) && ChallengesMainClass.t.isStarted()) {
			return true;
		}
		return false;
	}
	
	public boolean isToggled(String option) {
		return (boolean) getOption(this.getCfg(), root + "." + option + ".isEnabled", false);
	}
	
	public void toggle(String option) {
		if((boolean) getOption(this.getCfg(), root + "." + option + ".isEnabled", false)) {
			setOption(this.getCfg(), root + "." + option + ".isEnabled", false);
		} else {
			setOption(this.getCfg(), root + "." + option + ".isEnabled", true);
		}
	}
	
}
