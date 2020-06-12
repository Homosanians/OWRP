package com.otherworld.owrp;

import com.otherworld.owrp.commands.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class OWRP extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Deobfuscation, modification, transfer, storage, use of the plugin is prohibited to anyone except Other World and it's authorized third-parties. You are have agreed with these conditions from the moment you begin storage or use. Copyright Other World 2020 Alexander Emelyanov. Contact: contact.emelyanov@gmail.com");

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("me").setExecutor(new MeCommand(this));
        getCommand("do").setExecutor(new DoCommand(this));
        getCommand("try").setExecutor(new TryCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
