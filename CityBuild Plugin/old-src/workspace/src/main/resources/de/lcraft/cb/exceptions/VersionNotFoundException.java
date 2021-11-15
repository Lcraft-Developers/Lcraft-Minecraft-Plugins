package de.lcraft.cb.exceptions;

public class VersionNotFoundException extends Exception {

    public VersionNotFoundException(String id) {
        super("Cannot look for updates: " + id);
    }
    public VersionNotFoundException(int id) {
        super("Cannot look for updates: " + id);
    }

}
