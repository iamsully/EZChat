package ca.sullyq.ezchat.commands;

import ca.sullyq.ezchat.commands.tags.TagCommand;
import ca.sullyq.ezchat.commands.tags.player.PlayerCommand;
import ca.sullyq.ezchat.config.PlayerTagConfig;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import com.hypixel.hytale.server.core.util.Config;

public class EZChatCommand extends AbstractCommandCollection {

    public EZChatCommand(Config<PlayerTagConfig> config) {
        super("ec", "EZChat plugin commands");

        // Add subcommands
        this.addSubCommand(new HelpSubCommand());
        this.addSubCommand(new TagCommand(config));
        this.addSubCommand(new PlayerCommand(config));
    }

    @Override
    protected boolean canGeneratePermission() {
        return false; // No permission required for base command
    }
}