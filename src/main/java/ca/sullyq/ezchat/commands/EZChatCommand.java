package ca.sullyq.ezchat.commands;

import ca.sullyq.ezchat.commands.subcommands.HelpSubCommand;
import ca.sullyq.ezchat.commands.subcommands.UISubCommand;
import ca.sullyq.ezchat.commands.tags.player.GiveTagToPlayerCommand;
import ca.sullyq.ezchat.commands.tags.TagCommand;
import ca.sullyq.ezchat.config.TagConfig;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import com.hypixel.hytale.server.core.util.Config;

public class EZChatCommand extends AbstractCommandCollection {


    public EZChatCommand(Config<TagConfig> config) {
        super("ec", "EZChat plugin commands");

        // Add subcommands
        this.addSubCommand(new HelpSubCommand());
        this.addSubCommand(new UISubCommand());

        this.addSubCommand(new TagCommand(config));
        this.addSubCommand(new GiveTagToPlayerCommand(config));
    }

    @Override
    protected boolean canGeneratePermission() {
        return true;
    }
}