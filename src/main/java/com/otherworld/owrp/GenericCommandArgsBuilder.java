package com.otherworld.owrp;

import java.util.List;

public class GenericCommandArgsBuilder {
    public GenericCommandArgs args;

    public void SetChatRadius(int value) {
        args.chatRadius = value;
    }

    public void SetMessages(List<String> value) {
        args.messages = value;
    }

    public void SetMessagesOutputMode(String value) {
        args.commandMessagesOutputMode = value;
    }

    public void SetName(String value) {
        args.commandName = value;
    }

    public void SetPermission(String value) {
        args.commandPermission = value;
    }

    public void SetUsage(String value) {
        args.commandUsage = value;
    }

    public GenericCommandArgs Build() {
        return args;
    }
}
