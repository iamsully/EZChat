package ca.sullyq.ezchat.commands.subcommands;

import ca.sullyq.ezchat.helpers.MessageParser;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;


public class HelpSubCommand extends AbstractPlayerCommand {
    public HelpSubCommand() {
        super("help", "Show available commands");
        this.requirePermission("ezchat.commands.help");
    }

    @Override
    protected boolean canGeneratePermission() {
        return true;
    }

    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        Message headerMessage = MessageParser.parse("<color:gold> === EZChat Commands === </color>");
        Message helpMessage = MessageParser.parse("<color:green> /ec help</color> - Show this help message");
        Message addTagMessage = MessageParser.parse("<color:green> /ec tag add </color> - Adds a new tag");
        Message infoMessage = MessageParser.parse("<color:green> /ec info</color> - Show plugin information");
        Message reloadMessage = MessageParser.parse("<color:green> /ec reload</color> - Reload configuration");
        Message uiMessage = MessageParser.parse("<color:green> /ec ui</color> - Open the dashboard UI");
        Message footerMessage = MessageParser.parse("<color:gold> ======================== </color>");

//        Message link = MessageParser.parse("<link:https://google.com><gradient:blue:red>click me</gradient></link>");

        commandContext.sendMessage(Message.raw(""));
        commandContext.sendMessage(headerMessage);
        commandContext.sendMessage(helpMessage);
        commandContext.sendMessage(addTagMessage);
        commandContext.sendMessage(infoMessage);
        commandContext.sendMessage(reloadMessage);
        commandContext.sendMessage(uiMessage);
        commandContext.sendMessage(footerMessage);
    }


}
