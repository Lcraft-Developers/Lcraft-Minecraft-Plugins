package de.lcraft.cb.systems;

import de.lcraft.cb.main.Starter;
import java.util.ArrayList;

public abstract class System extends Starter {

    public abstract ArrayList<String> allPermissions(ArrayList<String> allPerms);
    public abstract ArrayList<String> allLanguages(ArrayList<String> allLang);

}
