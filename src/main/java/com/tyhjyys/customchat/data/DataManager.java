package com.tyhjyys.customchat.data;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import com.tyhjyys.customchat.CustomChat;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class DataManager {

    private final CustomChat plugin;
    private File dataFile;
    private FileConfiguration dataConfig;

    public DataManager(CustomChat plugin) {
        this.plugin = plugin;
        setupDataFile();
    }

    // Creates the data.yml file if it doesn't exist
    private void setupDataFile() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        dataFile = new File(plugin.getDataFolder(), "data.yml");

        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().severe("Could not create data.yml!");
            }
        }
        dataConfig = YamlConfiguration.loadConfiguration(dataFile);
    }

    // Fetches the color, defaults to &7 (gray)
    public String getPlayerColor(UUID uuid) {
        return dataConfig.getString("players." + uuid.toString(), "&7");
    }

    // Saves the new color and writes it to the file immediately
    public void setPlayerColor(UUID uuid, String colorCode) {
        dataConfig.set("players." + uuid.toString(), colorCode);
        try {
            dataConfig.save(dataFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save data to data.yml!");
        }
    }
}