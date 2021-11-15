package de.lcraft.cb.utils;

import de.lcraft.cb.main.Main;
import java.util.ArrayList;

public class TabComplete {

    private String[] beforeArgs;
    private ArrayList<String> pos;

    public TabComplete(Main plugin, String[] beforeArgs, ArrayList<String> possebilitis) {
        this.beforeArgs = beforeArgs;
        this.pos = possebilitis;
    }

    public ArrayList<String> getPos() {
        return pos;
    }

    public String[] getBeforeArgs() {
        return beforeArgs;
    }

}