package com.otherworld.owrp.listeners;

import com.otherworld.owrp.OWRP;
import com.otherworld.owrp.handlers.ExpressionsHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ExpressionsChatListener implements Listener {
    private final ExpressionsHandler expressionsHandler;

    public ExpressionsChatListener(OWRP plugin) {
        expressionsHandler = new ExpressionsHandler(plugin);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        expressionsHandler.handle(event);
    }
}