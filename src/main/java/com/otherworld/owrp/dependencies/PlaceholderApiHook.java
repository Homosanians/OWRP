package com.otherworld.owrp.dependencies;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.util.List;

public class PlaceholderApiHook {

    public String setPlaceholders(Player player, String message) {
        return PlaceholderAPI.setPlaceholders(player, message);
    }

    public List<String> setPlaceholders(Player player, List<String> messages) {
        return PlaceholderAPI.setPlaceholders(player, messages);
    }
}
