package com.otherworld.owrp.commands;

import com.otherworld.owrp.OWRP;
import com.otherworld.owrp.utils.ChatColorUtil;
import com.otherworld.owrp.utils.PlayerUtil;
import org.bukkit.plugin.Plugin;
import java.util.List;
import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class MeCommand implements CommandExecutor {
    private OWRP plugin;

    public MeCommand(OWRP plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Completes execution of the command if it is not sent by a player.
        if (!(sender instanceof Player)) {
            sender.sendMessage("Сообщения из консоли не принимаются");
            return true;
        }

        // Check weather content arguments are present
        if (args.length == 0) {
            sender.sendMessage(
                    ChatColorUtil.getChatColor(plugin.getConfig().getString("Me.Color.error"))
                    + "Текст сообщения не был найден"
            );
            return true;
        }

        Player player = (Player) sender;
        List<Player> addressees = PlayerUtil.getPlayersWithin(player, plugin.getConfig().getInt("chatRadius"));
        String content = String.join(" ", args);

        for (Player addressee : addressees)
        {
            // <content> (<player>)
            addressee.sendMessage(
                    ChatColorUtil.getChatColor(plugin.getConfig().getString("Me.Color.nickname"))
                    + plugin.getConfig().getString("Me.Message.prefix")
                    + player.getDisplayName()
                    + ChatColorUtil.getChatColor(plugin.getConfig().getString("Me.Color.content"))
                    + " "
                    + content
            );
        }

        return true;
    }
}