package de.lcraft.cb.systems;

import de.lcraft.cb.main.Main;
import de.lcraft.cb.utils.Command;

import java.util.ArrayList;

public class SystemsManager {

    private Main plugin;
    private ArrayList<System> systems;

    public SystemsManager(Main plugin) throws InterruptedException {
        this.plugin = plugin;
        systems = new ArrayList<>();
    }

    public void registerSystem(System sys) {
        systems.add(sys);
    }

    public ArrayList<String> docAllPermissions() {
        ArrayList<String> allPerms = new ArrayList<>();

        for(System sys : systems) {
            allPerms = sys.allPermissions(allPerms);
        }

        return allPerms;
    }

    public ArrayList<String> docAllTranslations() {
        ArrayList<String> allLangs = new ArrayList<>();

        for(System sys : systems) {
            allLangs = sys.allLanguages(allLangs);
        }

        return allLangs;
    }

}
