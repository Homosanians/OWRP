package com.otherworld.owrp.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerUtil {
    public static List<Player> getPlayersWithin(final Player player, final int distance) {
        final List<Player> res = new ArrayList<Player>();
        final int d2 = distance * distance;
        for (final Player p : Bukkit.getServer().getOnlinePlayers()) {
            if (p.getWorld() == player.getWorld() && p.getLocation().distanceSquared(player.getLocation()) <= d2) {
                res.add(p);
            }
        }
        return res;
    }
}