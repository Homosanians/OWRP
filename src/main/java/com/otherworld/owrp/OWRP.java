package com.otherworld.owrp;

import com.otherworld.owrp.commands.*;
import com.otherworld.owrp.handlers.CommandsHandler;
import com.otherworld.owrp.listeners.ExpressionsChatListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

public final class OWRP extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        checkConfigVersions();

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("owrp").setExecutor(new ReloadCommand(this));

        new CommandsHandler(this).registerCommands();

        // todo Bukkit.getPluginManager().registerEvents(new ExpressionsChatListener(this), this);
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
