package com.otherworld.owrp.handlers;

import com.otherworld.owrp.GenericCommandArgs;
import com.otherworld.owrp.OWRP;
import com.otherworld.owrp.commands.GenericCommand;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class CommandsHandler {
    private final OWRP plugin;

    public CommandsHandler(OWRP plugin) {
        this.plugin = plugin;
    }

    public boolean registerCommands() {
        AtomicInteger commandsRegistered = new AtomicInteger();

        ConfigurationSection commands = plugin.getConfig().getConfigurationSection("Commands");

        for(String commandName:commands.getKeys(false)){
            System.out.println(commandName);

            boolean commandEnabled = commands.getBoolean(String.format("%s.enabled", commandName));
            if (commandEnabled) {
                System.out.println(commands.getInt(String.format("%s.radius", commandName)));
                List<String> commandMessages = commands.getStringList(String.format("%s.messages", commandName));
                String commandMessagesOutputMode = commands.getString(String.format("%s.messagesOutputMode", commandName));
                int commandRadius = commands.getInt(String.format("%s.radius", commandName));
                String commandPermission = commands.getString(String.format("%s.permission", commandName));

                GenericCommandArgs commandArgs;

                assert commandMessagesOutputMode != null;
                if (commandMessagesOutputMode.equals("random")) {
                    commandArgs = new GenericCommandArgs(commandName,
                            String.format("/%s <сообщение>", commandName), commandPermission, commandMessages.get(
                                    new Random().nextInt(commandMessages.size())), commandRadius);
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

        System.out.println("Commands registered: " + commandsRegistered);
        return true;
    }
}
