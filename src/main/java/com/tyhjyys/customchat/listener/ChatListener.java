package com.tyhjyys.customchat.listener;

import com.tyhjyys.customchat.CustomChat;
import com.tyhjyys.customchat.data.DataManager;
import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class ChatListener implements Listener {

    private final CustomChat plugin;
    private final DataManager dataManager;

    public ChatListener(CustomChat plugin, DataManager dataManager) {
        this.plugin = plugin;
        this.dataManager = dataManager;
    }

    @EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        // Modern Paper uses a Renderer to format chat
        event.renderer(new ChatRenderer() {
            @Override
            public @NotNull Component render(@NotNull Player source, @NotNull Component sourceDisplayName, @NotNull Component message, @NotNull Audience viewer) {
                
                String playerColor = dataManager.getPlayerColor(source.getUniqueId());
                String formatString = plugin.getConfig().getString("chat-format", "{color}{name}&8: &f{message}");

                // Replace custom placeholders
                formatString = formatString.replace("{name}", source.getName());
                formatString = formatString.replace("{color}", playerColor);
                
                // Adventure API passes the actual typed 'message' as a separate Component to preserve player hover/click events.
                // We remove "{message}" from your config string layout so we can append the real message Component at the end.
                formatString = formatString.replace("{message}", "");

                // Convert your config format (with & codes) into a modern Component
                Component prefixComponent = LegacyComponentSerializer.legacyAmpersand().deserialize(formatString);

                // Glue the prefix and the typed message together
                return prefixComponent.append(message);
            }
        });
    }
}