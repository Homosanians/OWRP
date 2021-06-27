package com.otherworld.owrp;

public class GenericCommandArgsBuilder {
    public GenericCommandArgs args;

    public GenericCommandArgsBuilder() {
        args = new GenericCommandArgs();
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
