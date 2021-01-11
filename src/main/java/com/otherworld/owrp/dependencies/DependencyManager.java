package com.otherworld.owrp.dependencies;

import com.otherworld.owrp.OWRP;
import lombok.Getter;

import java.util.logging.Level;

public class DependencyManager {

    @Getter public PlaceholderApiHook placeholderApi;

    public DependencyManager(OWRP plugin) {

        if (plugin.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            this.placeholderApi = new PlaceholderApiHook();
            plugin.getLogger().log(Level.INFO, "PlaceholderAPI has successful hooked.");
        }
    }

}
