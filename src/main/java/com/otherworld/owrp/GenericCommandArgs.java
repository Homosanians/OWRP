package com.otherworld.owrp;

public class GenericCommandArgs {
    public String commandName;
    public String commandUsage;
    public String commandPermission;
    public String message;
    public int chatRadius;

    public GenericCommandArgs(String commandName, String commandUsage, String commandPermission, String message, int chatRadius) {
        this.commandName = commandName;
        this.commandUsage = commandUsage;
        this.commandPermission = commandPermission;
        this.message = message;
        this.chatRadius = chatRadius;
    }
}
