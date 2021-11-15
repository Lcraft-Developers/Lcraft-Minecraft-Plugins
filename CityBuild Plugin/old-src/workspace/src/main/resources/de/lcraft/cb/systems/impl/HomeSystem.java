package de.lcraft.cb.systems.impl;

import de.lcraft.cb.main.Main;
import de.lcraft.cb.systems.System;
import de.lcraft.cb.utils.Config;
import org.bukkit.Location;
import java.util.ArrayList;
import java.util.UUID;

public class HomeSystem extends System {

    private Main plugin;
    private Config homes;

    public HomeSystem(Main plugin) {
        this.plugin = plugin;
        homes = new Config("homes.yml");
    }

    public void setHome(Home home) {
        homes.cfg().set("users." + home.getPlayer().toString() + "." + home.getName() + ".enabled", home.enabled);
        homes.save();
        homes.saveLocation("users." + home.getPlayer().toString() + "." + home.getName() + ".loc", home.getLoc());
        homes.cfg().set("users." + home.getPlayer().toString() + "." + home.getName() + ".player", home.getPlayer().toString());
        homes.save();
    }

    public boolean delHome(UUID player, String name) {
        if(existsHome(player, name)) {
            Home h = getHome(player, name);
            h.setEnabled(false);
            setHome(h);
            return true;
        } else {
            return false;
        }
    }

    public Home getHome(UUID player, String name) {
        if(existsHome(player, name)) {
            String root = "users." + player.toString() + "." + name + ".name";
            Location loc = homes.getLocation(root + "." + name + ".loc");
            boolean enabled = homes.cfg().getBoolean(root + "." + name + ".enabled");
            return new Home(player, name, loc, enabled);
        }
        return null;
    }

    public boolean existsHome(UUID player, String name) {
        ArrayList<Home> homes = getAllHomes(player);
        for(Home c : homes) {
            if(c.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Home> getAllHomes(UUID player) {
        ArrayList<Home> home = new ArrayList<>();
        if(homes.cfg().contains("users")) {
            if(homes.cfg().contains("users." + player.toString())) {
                for(String name : homes.cfg().getConfigurationSection("users." + player.toString()).getKeys(false)) {
                    String root = "users." + player.toString() + "." + name + ".name";
                    Location loc = homes.getLocation(root + ".loc");
                    boolean enabled = homes.cfg().getBoolean(root + ".enabled");
                    home.add(new Home(player, name, loc, enabled));
                }
            }
        }
        return home;
    }

    @Override
    public ArrayList<String> allPermissions(ArrayList<String> allPerms) {
        return allPerms;
    }

    @Override
    public ArrayList<String> allLanguages(ArrayList<String> allLang) {
        return allLang;
    }

    public class Home {

        private String name;
        private Location loc;
        private boolean enabled;
        private UUID player;

        public Home(UUID player, String name, Location loc, boolean enabled) {
            this.name = name;
            this.loc = loc;
            this.enabled = enabled;
            this.player = player;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public UUID getPlayer() {
            return player;
        }

        public Location getLoc() {
            return loc;
        }

        public String getName() {
            return name;
        }

        public boolean isEnabled() {
            return enabled;
        }

    }

    public Config getHomes() {
        return homes;
    }

    public Main getPlugin() {
        return plugin;
    }

}
