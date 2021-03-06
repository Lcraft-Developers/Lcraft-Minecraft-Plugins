package de.lpd.challenges.utils;


import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.generator.ChunkGenerator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;

public class WorldUtil {

    public void resetWorld(){
        deleteFolder("world");
        deleteFolder("world_nether");
        deleteFolder("world_the_end");
    }

    private void deleteFolder(String folder){
        if (Files.exists(Paths.get(folder)))
            try {
                Files.walk(Paths.get(folder), new FileVisitOption[0])
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);

                createFiles();
                Bukkit.getLogger().log(Level.INFO, "§2" + folder.toUpperCase() + " §awird gel§scht und resetet");
            } catch (IOException e) {
                Bukkit.getLogger().log(Level.SEVERE, "§4" + folder.toUpperCase() + " §ckonnte nicht gel§scht werden\n" +
                        "§4Grund: §c" + e.getCause().toString());
            }


    }

    private void createFiles(){
        File propertiesFile = new File(Bukkit.getWorldContainer(), "server.properties");
        try(FileInputStream stream = new FileInputStream(propertiesFile)) {
            Properties properties = new Properties();
            properties.load(stream);
            
            File world = new File(Bukkit.getWorldContainer(), properties.getProperty("level-name"));
            world.delete();
            world.mkdirs();
            
            File nether = new File(Bukkit.getWorldContainer(), "world_nether");
            nether.delete();
            nether.mkdirs();

            new File(world, "data").mkdirs();
            new File(world, "datapacks").mkdirs();
            new File(world, "playerdata").mkdirs();
            new File(world, "poi").mkdirs();
            new File(world, "region").mkdirs();
            new File(nether, "data").mkdirs();
            new File(nether, "datapacks").mkdirs();
            new File(nether, "playerdata").mkdirs();
            new File(nether, "poi").mkdirs();
            new File(nether, "region").mkdirs();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}