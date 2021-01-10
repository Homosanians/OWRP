package com.otherworld.owrp.listeners;

import com.otherworld.owrp.OWRP;
import com.otherworld.owrp.handlers.ExpressionsHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ExpressionsChatListener implements Listener {
    private OWRP plugin;
    private ExpressionsHandler emoteHandler;

    public ExpressionsChatListener(OWRP plugin) {
        this.plugin = plugin;

        emoteHandler = new ExpressionsHandler(plugin);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        emoteHandler.handle(event);

        event.setCancelled(true);
    }
}