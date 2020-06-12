package com.otherworld.owrp.commands;

import java.util.ArrayList;
import java.util.Iterator;

import com.otherworld.owrp.OWRP;
import org.bukkit.plugin.Plugin;
import java.util.List;
import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class MeCommand implements CommandExecutor
{
    private OWRP plugin;

    public MeCommand(final OWRP plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(final CommandSender player, final Command cmd, final String label, final String[] args) {
        System.out.println(player);
        if (!(player instanceof Player)) {
            player.sendMessage("\u043f\u0438\u0437\u0434\u0435\u0446");
            return true;
        }
        final Player pl = (Player)player;
        if (args.length == 0) {
            pl.sendMessage(ChatColor.RED + "\u041d\u0435\u0442 \u0441\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u044f");
            return true;
        }
        final List<Player> wiwiw = this.getPlayersWithin(pl, 100);
        final StringBuilder sb = new StringBuilder();
        for (final String word : args) {
            sb.append(word);
            sb.append(" ");
        }
        final Plugin chatty = Bukkit.getServer().getPluginManager().getPlugin("Chatty");
        final File chattyignore = new File(chatty.getDataFolder() + File.separator + "storage.json");
        for (final Player playerr : wiwiw) {
            System.out.println("b");
            playerr.sendMessage(ChatColor.AQUA + "* " + pl.getDisplayName() + ChatColor.GRAY + " " + sb.toString().trim());
        }
        return true;
    }

    public List<Player> getPlayersWithin(final Player player, final int distance) {
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
