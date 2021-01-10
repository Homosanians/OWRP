package com.otherworld.owrp.handlers;

import com.otherworld.owrp.OWRP;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Objects;

public class ExpressionsHandler {
    private final OWRP plugin;
    private final ConfigurationSection expressions;

    public ExpressionsHandler(OWRP plugin) {
        this.plugin = plugin;
        this.expressions = plugin.getConfig().getConfigurationSection("Expressions");
    }

    public void handle(AsyncPlayerChatEvent event) {
        // command : message
        System.out.println(event.getMessage());
        for (String expression : expressions.getKeys(false)) {
            System.out.println(expression);
            System.out.println(expressions.getString(expression));
            if (event.getMessage().equals(expression)) {
                event.setMessage(Objects.requireNonNull(expressions.getString(expression)));
            }
        }
        event.setMessage("хуй");
    }
}
