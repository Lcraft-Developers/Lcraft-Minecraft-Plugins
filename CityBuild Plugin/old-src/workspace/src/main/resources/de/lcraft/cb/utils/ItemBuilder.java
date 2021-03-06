package de.lcraft.cb.utils;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {
	
	private static ItemStack i;
	private static ItemMeta iMeta;
	
	public ItemBuilder(Material m, int amount) {
		i = new ItemStack(m, amount);
		iMeta = i.getItemMeta();
	}
	
	public ItemBuilder(Material m) {
		this(m, 1);
	}
	
	public ItemBuilder setDisplayName(String name) {
		iMeta.setDisplayName(name);
		return this;
	}
	
	public ItemBuilder setLoreString(String... lore) {
		ArrayList<String> l = new ArrayList<>();
		for(String c : lore) {
			l.add(c);
		}
		setLore(l);
		return this;
	}
	
	public ItemBuilder setLore(ArrayList<String> lore) {
		iMeta.setLore(lore);
		return this;
	}
	
	public ItemStack build() {
		i.setItemMeta(iMeta);
		return i;
	}
	
}
