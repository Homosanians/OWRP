package com.otherworld.owrp.handlers;

import com.otherworld.owrp.OWRP;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class ExpressionsHandler {
    private OWRP plugin;

    public ExpressionsHandler(OWRP plugin) {
        this.plugin = plugin;
    }

    public void handle(AsyncPlayerChatEvent event) {
        List<String> commands = plugin.getConfig().getStringList("Commands");
        // command : message
        for (String command : commands) {
            System.out.println(command);
            if (event.getMessage().equals(command)) {
                execute(command);
            }
        }
    }

    private void execute(String message) {
        // /me message
    }
}
