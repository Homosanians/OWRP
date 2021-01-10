package com.otherworld.owrp;

import com.otherworld.owrp.commands.*;
import com.otherworld.owrp.handlers.CommandsHandler;
import com.otherworld.owrp.listeners.ExpressionsChatListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;

public final class OWRP extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Deobfuscation, modification, transfer, storage, use of OWRP plugin is prohibited to anyone except Other World and it's authorized third-parties. You are have agreed with these conditions from the moment you begin storage or use. If you are not an authorized person, delete the plugin and it's files immediately. Copyright Other World 2020 Alexander Emelyanov. Contact: contact.emelyanov@gmail.com");

        checkConfigVersions();

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("owrp").setExecutor(new ReloadCommand(this));

        new MyCommand(this);
        //new CommandsHandler(this).registerCommands();

        Bukkit.getPluginManager().registerEvents(new ExpressionsChatListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void checkConfigVersions() {
        Reader defaultStream = null;
        YamlConfiguration defaultConfig = null;
        try {
            defaultStream = new InputStreamReader(getResource("config.yml"), "UTF8");
        } catch (UnsupportedEncodingException ex) { }
        if (defaultStream != null) {
            defaultConfig = YamlConfiguration.loadConfiguration(defaultStream);
        }

        Integer actualVersion = defaultConfig.getInt("configVersion");
        Integer loadedVersion = getConfig().getInt("configVersion");

        if (actualVersion > loadedVersion) {
            System.out.println();
            System.out.println("-------------[OWRP]-----------");
            System.out.println("         Config outdated      ");
            System.out.println("         Plugin may crash     ");
            System.out.println("------------------------------");
            System.out.println();
        }
    }
}
