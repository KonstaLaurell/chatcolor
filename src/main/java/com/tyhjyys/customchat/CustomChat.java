package com.tyhjyys.customchat;

import org.bukkit.plugin.java.JavaPlugin;

import com.tyhjyys.customchat.command.ColorCommand;
import com.tyhjyys.customchat.data.DataManager;
import com.tyhjyys.customchat.listener.ChatListener;

public class CustomChat extends JavaPlugin {

    private DataManager dataManager;

    @Override
    public void onEnable() {
        // Generates config.yml if it doesn't exist
        this.saveDefaultConfig(); 
        
        // Initialize our data saver
        this.dataManager = new DataManager(this);

        // Register the chat event listener
        getServer().getPluginManager().registerEvents(new ChatListener(this, dataManager), this);

        // Register the /color command
        getCommand("color").setExecutor(new ColorCommand(dataManager));

        getLogger().info("CustomChat has been enabled!");
    }
}