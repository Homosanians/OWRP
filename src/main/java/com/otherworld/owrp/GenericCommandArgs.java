package com.otherworld.owrp;

import java.util.List;

public class GenericCommandArgs {
    public String commandName;
    public String commandUsage;
    public String commandPermission;
    public String commandMessagesOutputMode;
    public List<String> messages;
    public int chatRadius;

    // todo builder pattern
    public GenericCommandArgs(String commandName, String commandUsage, String commandPermission,
                              String commandMessagesOutputMode, List<String> messages, int chatRadius) {
        this.commandName = commandName;
        this.commandUsage = commandUsage;
        this.commandPermission = commandPermission;
        this.commandMessagesOutputMode = commandMessagesOutputMode;
        this.messages = messages;
        this.chatRadius = chatRadius;
    }
}
