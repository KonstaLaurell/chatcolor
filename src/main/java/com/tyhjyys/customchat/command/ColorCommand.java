package com.tyhjyys.customchat.command;

import com.tyhjyys.customchat.data.DataManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ColorCommand implements CommandExecutor {

    private final DataManager dataManager;
    private final Map<String, String> allowedColors = new HashMap<>();

    public ColorCommand(DataManager dataManager) {
        this.dataManager = dataManager;
        
        // We keep the exact same & codes so we don't break your data.yml file
        allowedColors.put("red", "&c");
        allowedColors.put("darkred", "&4");
        allowedColors.put("blue", "&9");
        allowedColors.put("lightblue", "&b");
        allowedColors.put("green", "&a");
        allowedColors.put("darkgreen", "&2");
        allowedColors.put("yellow", "&e");
        allowedColors.put("gold", "&6");
        allowedColors.put("pink", "&d");
        allowedColors.put("purple", "&5");
        allowedColors.put("white", "&f");
        allowedColors.put("gray", "&7");
        allowedColors.put("black", "&0");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Component.text("Only players can set their chat color.").color(NamedTextColor.RED));
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage(Component.text("Usage: /color <colorname>").color(NamedTextColor.RED));
            player.sendMessage(Component.text("Available colors: " + String.join(", ", allowedColors.keySet())).color(NamedTextColor.GRAY));
            return true;
        }

        String chosenColorName = args[0].toLowerCase();

        if (!allowedColors.containsKey(chosenColorName)) {
            player.sendMessage(Component.text("Invalid color!").color(NamedTextColor.RED));
            player.sendMessage(Component.text("Available colors: " + String.join(", ", allowedColors.keySet())).color(NamedTextColor.GRAY));
            return true;
        }

        String colorCode = allowedColors.get(chosenColorName);
        dataManager.setPlayerColor(player.getUniqueId(), colorCode);
        
        // Translate the legacy & code into a modern Adventure Component for the success message
        Component successMessage = LegacyComponentSerializer.legacyAmpersand()
                .deserialize("&aYour name color has been updated to: " + colorCode + player.getName());
        
        player.sendMessage(successMessage);

        return true;
    }
}