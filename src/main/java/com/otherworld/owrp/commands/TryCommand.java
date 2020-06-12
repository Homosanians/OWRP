package com.otherworld.owrp.commands;

        import com.otherworld.owrp.OWRP;
        import com.otherworld.owrp.utils.PlayerUtil;
        import org.bukkit.ChatColor;
        import org.bukkit.command.Command;
        import org.bukkit.command.CommandExecutor;
        import org.bukkit.command.CommandSender;
        import org.bukkit.entity.Player;

        import java.util.List;

public class TryCommand implements CommandExecutor {
    private OWRP plugin;

    public TryCommand(OWRP plugin) {
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
            sender.sendMessage("Текст сообщения не был найден");
            return true;
        }

        Player player = (Player) sender;
        List<Player> addressees = PlayerUtil.getPlayersWithin(player, plugin.getConfig().getInt("chatRadius"));
        String content = String.join(" ", args);
        Boolean successfulBet = Math.random() < 0.5f;

        for (Player addressee : addressees)
        {
            if (successfulBet) {
                // <nick> <content> [Удачно]
                addressee.sendMessage(ChatColor.AQUA + player.getDisplayName() + ChatColor.GRAY + " " + content + " " + ChatColor.GREEN + "| Удачно");
            }
            else {
                // <nick> <content> [Неудачно]
                addressee.sendMessage(ChatColor.AQUA + player.getDisplayName() + ChatColor.GRAY + " " + content + " " + ChatColor.RED + "| Неудачно");
            }
        }

        return true;
    }
}