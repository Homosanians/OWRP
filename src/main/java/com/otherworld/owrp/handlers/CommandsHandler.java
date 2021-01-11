package com.otherworld.owrp.handlers;

import com.otherworld.owrp.GenericCommandArgs;
import com.otherworld.owrp.OWRP;
import com.otherworld.owrp.commands.GenericCommand;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

public class CommandsHandler {
    private final OWRP plugin;

    public CommandsHandler(OWRP plugin) {
        this.plugin = plugin;
    }

    public void registerCommands() {
        AtomicInteger commandsRegistered = new AtomicInteger();

        ConfigurationSection commands = plugin.getConfig().getConfigurationSection("Commands");
        for(String commandName:commands.getKeys(false)){
            boolean commandEnabled = commands.getBoolean(String.format("%s.enabled", commandName));
            if (commandEnabled) {
                List<String> commandMessages = commands.getStringList(String.format("%s.messages", commandName));
                String commandMessagesOutputMode = commands.getString(String.format("%s.messagesOutputMode", commandName));
                int commandRadius = commands.getInt(String.format("%s.radius", commandName));
                String commandPermission = commands.getString(String.format("%s.permission", commandName));

                GenericCommandArgs commandArgs;

                assert commandMessagesOutputMode != null;
                if (commandMessagesOutputMode.equals("random")) {
                    String message = commandMessages.get(new Random(Math.round(Math.random() * 100)).nextInt(commandMessages.size()));
                    commandArgs = new GenericCommandArgs(commandName,
                            String.format("/%s <сообщение>", commandName), commandPermission, message, commandRadius);
                }
                else if (commandMessagesOutputMode.equals("consistent")) {
                    // todo тут ты идешь анхуй
                    commandArgs = new GenericCommandArgs(commandName,
                            String.format("/%s <сообщение>", commandName), commandPermission, commandMessages.get(0),
                            commandRadius);
                }
                else {
                    commandArgs = new GenericCommandArgs(commandName,
                            String.format("/%s <сообщение>", commandName), commandPermission, commandMessages.get(0),
                            commandRadius);
                }

                new GenericCommand(plugin, commandArgs);

                commandsRegistered.addAndGet(1);
            }
        }

        if (commandsRegistered.intValue() == 0) {
            plugin.getLogger().log(Level.WARNING, "Commands registered: " + commandsRegistered);
        }
        else {
            plugin.getLogger().log(Level.INFO, "Commands registered: " + commandsRegistered);
        }
    }
}
