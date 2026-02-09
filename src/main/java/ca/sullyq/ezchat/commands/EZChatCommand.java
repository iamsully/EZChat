package ca.sullyq.ezchat.commands;

import ca.sullyq.ezchat.commands.player.RemoveTagFromPlayerCommand;
import ca.sullyq.ezchat.commands.subcommands.HelpSubCommand;
import ca.sullyq.ezchat.commands.subcommands.UISubCommand;
import ca.sullyq.ezchat.commands.player.GiveTagToPlayerCommand;
import ca.sullyq.ezchat.commands.tags.TagCommand;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;

public class EZChatCommand extends AbstractCommandCollection {

    public EZChatCommand() {
        super("ec", "EZChat plugin commands");
        this.addSubCommand(new HelpSubCommand());
        this.addSubCommand(new UISubCommand());
        this.addSubCommand(new TagCommand());
        this.addSubCommand(new GiveTagToPlayerCommand());
        this.addSubCommand(new RemoveTagFromPlayerCommand());
    }

    @Override
    protected boolean canGeneratePermission() {
        return false;
    }
}