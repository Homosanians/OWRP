package com.otherworld.owrp.handlers;

import com.otherworld.owrp.OWRP;
import com.otherworld.owrp.utils.PlayerUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;
import java.util.Objects;

public class ExpressionsHandler {
    private final OWRP plugin;
    private final ConfigurationSection expressions;

    public ExpressionsHandler(OWRP plugin) {
        this.plugin = plugin;
        this.expressions = plugin.getConfig().getConfigurationSection("Expressions.list");
    }

    // todo permission
    public void handle(AsyncPlayerChatEvent event) {
        for (String expression : expressions.getKeys(false)) {
            if (event.getMessage().trim().equals(expression.trim())) {
                List<Player> addressees = PlayerUtil.getPlayersWithin(event.getPlayer(),
                        plugin.getConfig().getInt("Expressions.chatRadius"));

                String message = Objects.requireNonNull(plugin.getConfig().getString("Expressions.message"))
                        .replace("{player}", event.getPlayer().getDisplayName())
                        .replace("{message}", Objects.requireNonNull(expressions.getString(expression)));

                for (Player addressee : addressees)
                {
                    addressee.sendMessage(message);
                }
                event.setCancelled(true);
            }
        }
    }
}
