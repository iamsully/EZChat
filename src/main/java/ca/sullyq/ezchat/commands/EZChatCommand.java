package ca.sullyq.ezchat.commands;

import ca.sullyq.ezchat.commands.tags.player.GiveTagToPlayerCommand;
import ca.sullyq.ezchat.commands.tags.TagCommand;
import ca.sullyq.ezchat.config.PlayerConfig;
import ca.sullyq.ezchat.config.TagConfig;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.Config;

public class EZChatCommand extends AbstractCommandCollection {

    private ComponentType<EntityStore, PlayerConfig> playerDataType;


    public EZChatCommand(Config<TagConfig> config, ComponentType<EntityStore, PlayerConfig> playerDataType) {
        super("ec", "EZChat plugin commands");
        this.playerDataType = playerDataType;

        // Add subcommands
        this.addSubCommand(new HelpSubCommand());
        this.addSubCommand(new TagCommand(config));
        this.addSubCommand(new GiveTagToPlayerCommand(config, playerDataType));
    }

    @Override
    protected boolean canGeneratePermission() {
        return true;
    }
}