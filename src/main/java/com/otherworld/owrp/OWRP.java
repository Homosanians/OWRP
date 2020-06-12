package com.otherworld.owrp;

import com.otherworld.owrp.commands.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

public final class OWRP extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Deobfuscation, modification, transfer, storage, use of the plugin is prohibited to anyone except Other World and it's authorized third-parties. You are have agreed with these conditions from the moment you begin storage or use. Copyright Other World 2020 Alexander Emelyanov. Contact: contact.emelyanov@gmail.com");

        checkConfigVersions();

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

    private void checkConfigVersions() {
        Reader defaultStream = null;
        YamlConfiguration defaultConfig = null;
        try {
            defaultStream = new InputStreamReader(getResource("config.yml"), "UTF8");
        } catch (UnsupportedEncodingException ex) { }
        if (defaultStream != null) {
            defaultConfig = YamlConfiguration.loadConfiguration(defaultStream);
        }

        Integer actualVersion = defaultConfig.getInt("version");
        Integer loadedVersion = getConfig().getInt("version");

        if (actualVersion > loadedVersion) {
            System.out.println("==============================");
            System.out.println("              OWRP            ");
            System.out.println("         Config outdated      ");
            System.out.println("==============================");
        }
    }
}
