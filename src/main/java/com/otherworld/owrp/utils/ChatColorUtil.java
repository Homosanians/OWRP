package com.otherworld.owrp.utils;

import org.bukkit.ChatColor;

public class ChatColorUtil {
    public static ChatColor getChatColor(String color) {
        switch (color) {
            case "BLACK":
                return ChatColor.BLACK;
            case "DARK_BLUE":
                return ChatColor.DARK_BLUE;
            case "DARK_GREEN":
                return ChatColor.DARK_GREEN;
            case "AQUA":
                return ChatColor.AQUA;
            case "DARK_RED":
                return ChatColor.DARK_RED;
            case "DARK_PURPLE":
                return ChatColor.DARK_PURPLE;
            case "GOLD":
                return ChatColor.GOLD;
            case "GRAY":
                return ChatColor.GRAY;
            case "DARK_GRAY":
                return ChatColor.DARK_GRAY;
            case "BLUE":
                return ChatColor.BLUE;
            case "GREEN":
                return ChatColor.GREEN;
            case "DARK_AQUA":
                return ChatColor.DARK_AQUA;
            case "RED":
                return ChatColor.RED;
            case "LIGHT_PURPLE":
                return ChatColor.LIGHT_PURPLE;
            case "YELLOW":
                return ChatColor.YELLOW;
            case "WHITE":
                return ChatColor.WHITE;
            case "BOLD":
                return ChatColor.BOLD;
            case "ITALIC":
                return ChatColor.ITALIC;
            case "MAGIC":
                return ChatColor.MAGIC;
            case "RESET":
                return ChatColor.RESET;
            case "STRIKETHROUGH":
                return ChatColor.STRIKETHROUGH;
            case "UNDERLINE":
                return ChatColor.UNDERLINE;
            default:
                return ChatColor.getByChar(color);
        }
    }
}
