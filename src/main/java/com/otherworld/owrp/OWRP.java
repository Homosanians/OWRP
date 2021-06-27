package com.otherworld.owrp;

import com.otherworld.owrp.commands.*;
import com.otherworld.owrp.dependencies.DependencyManager;
import com.otherworld.owrp.handlers.CommandsHandler;
import com.otherworld.owrp.listeners.ExpressionsChatListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;

public final class OWRP extends JavaPlugin {

    private final Map<Class<?>, Object> dependenciesMap = new HashMap<>();

    private static OWRP instance;
    public static OWRP instance() {
        return OWRP.instance;
    }

    @Override
    public void onEnable() {
        OWRP.instance = OWRP.this;

        handleConfigurationFile();

        register(DependencyManager.class, new DependencyManager(this));

        getCommand("owrp").setExecutor(new ReloadCommand(this));

        new CommandsHandler(this).registerCommands();

        Bukkit.getPluginManager().registerEvents(new ExpressionsChatListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void handleConfigurationFile() {
        saveDefaultConfig();

        Reader defaultStream = null;
        YamlConfiguration defaultConfig = null;
        defaultStream = new InputStreamReader(Objects.requireNonNull(getResource("config.yml")), StandardCharsets.UTF_8);
        defaultConfig = YamlConfiguration.loadConfiguration(defaultStream);

        int actualVersion = defaultConfig.getInt("configVersion");
        int loadedVersion = getConfig().getInt("configVersion");

        if (actualVersion > loadedVersion) {
            File file = new File(getDataFolder(), "config.yml");
            boolean succeed = file.renameTo(new File(getDataFolder(), "config.yml.old"));

            if (!succeed) {
                getLogger().log(Level.WARNING, "Cannot rename config.yml to config.yml.old. " +
                        "The file name has not been changed. However, the current config is outdated.");
            } else {
                boolean deleted = file.delete();

                if (!deleted) {
                    getLogger().log(Level.WARNING, "Cannot delete config.yml after making copy. " +
                            "The current config file were not deleted, however, it is outdated.");
                }
            }
        }
    }

    @SuppressWarnings("all")
    public <T> T getExact(Class<T> clazz) {
        return (T) dependenciesMap.get(clazz);
    }

    public <T> void register(Class<T> clazz, T object) {
        if (dependenciesMap.containsKey(clazz)) {
            throw new IllegalStateException("Dependency is already registered");
        }

        dependenciesMap.put(clazz, object);
    }

    public <T> void unregister(Class<T> clazz) {
        if (!dependenciesMap.containsKey(clazz)) {
            throw new IllegalStateException("Dependency is not registered");
        }

        dependenciesMap.remove(clazz);
    }
}
