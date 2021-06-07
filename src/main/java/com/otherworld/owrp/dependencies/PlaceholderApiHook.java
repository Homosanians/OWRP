package com.otherworld.owrp.dependencies;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.regex.Pattern;

public class PlaceholderApiHook {

    public String setPlaceholders(Player player, String message) {
        System.out.println((OfflinePlayer) player.getPlayer());
        System.out.println(message);
        return PlaceholderAPI.setPlaceholders((OfflinePlayer) player.getPlayer(), message);
    }

    public List<String> setPlaceholders(Player player, List<String> messages) {
        return PlaceholderAPI.setPlaceholders(player, messages);
    }

}
