package com.otherworld.owrp.handlers;

import com.otherworld.owrp.GenericCommandArgs;
import com.otherworld.owrp.OWRP;
import com.otherworld.owrp.commands.GenericCommand;
import com.otherworld.owrp.commands.TestCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CommandsHandler {
    private final OWRP plugin;

    public CommandsHandler(OWRP plugin) {
        this.plugin = plugin;
    }

    public boolean registerCommands() {
        List<String> commands = plugin.getConfig().getStringList("Commands");
        AtomicInteger commandsRegistered = new AtomicInteger();

        commands.forEach((String commandName) -> {
            System.out.println(commandName);

            boolean commandEnabled = plugin.getConfig().getBoolean(String.format("Commands.%s.enabled", commandName));
            if (commandEnabled) {
                List<String> commandMessages = plugin.getConfig().getStringList(String.format("Commands.%s.messages", commandName));
                String commandMessagesOutputMode = plugin.getConfig().getString(String.format("Commands.%s.messagesOutputMode", commandName));
                int commandRadius = plugin.getConfig().getInt(String.format("Commands.%s.radius", commandName));
                String commandPermission = plugin.getConfig().getString(String.format("Commands.%s.permission", commandName));

                plugin.getCommand("try").setExecutor(
                        new GenericCommand(plugin, new GenericCommandArgs(commandMessages.get(0), commandRadius)));

                try {
                    final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

                    bukkitCommandMap.setAccessible(true);
                    CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

                    commandMap.register("todo", new TestCommand("todo"));
                } catch(Exception e) {
                    e.printStackTrace();
                }

                commandsRegistered.addAndGet(1);
            }
        });

        System.out.println("Commands registered: " + commandsRegistered);
        return true;
    }
}
