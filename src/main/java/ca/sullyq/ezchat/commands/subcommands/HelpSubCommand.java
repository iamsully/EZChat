package ca.sullyq.ezchat.commands.subcommands;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import fi.sulku.hytale.TinyMsg;

import javax.annotation.Nonnull;

public class HelpSubCommand extends CommandBase {
    public HelpSubCommand() {
        super("help", "Show available commands");
        this.requirePermission("ezchat.commands.help");
    }

    @Override
    protected void executeSync(@Nonnull CommandContext context) {

        Message headerMessage = TinyMsg.parse("<color:gold> === EZChat Commands === </color>");
        Message helpMessage = TinyMsg.parse("<color:green> /ec help</color> - Show this help message");
        Message addTagMessage = TinyMsg.parse("<color:green> /ec addtag </color> - Adds a new tag");
        Message infoMessage = TinyMsg.parse("<color:green> /ec info</color> - Show plugin information");
        Message reloadMessage = TinyMsg.parse("<color:green> /ec reload</color> - Reload configuration");
        Message uiMessage = TinyMsg.parse("<color:green> /ec ui</color> - Open the dashboard UI");
        Message footerMessage = TinyMsg.parse("<color:gold> ======================== </color>");

//        Message link = TinyMsg.parse("<link:https://google.com><gradient:blue:red>click me</gradient></link>");

        context.sendMessage(Message.raw(""));
        context.sendMessage(headerMessage);
        context.sendMessage(helpMessage);
        context.sendMessage(addTagMessage);
        context.sendMessage(infoMessage);
        context.sendMessage(reloadMessage);
        context.sendMessage(uiMessage);
        context.sendMessage(footerMessage);
    }

    @Override
    protected boolean canGeneratePermission() {
        return true;
    }

}
