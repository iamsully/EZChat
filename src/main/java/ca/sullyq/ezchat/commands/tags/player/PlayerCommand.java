package ca.sullyq.ezchat.commands.tags.player;

import ca.sullyq.ezchat.config.PlayerData;
import ca.sullyq.ezchat.config.TagConfig;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.Config;

public class PlayerCommand extends AbstractCommandCollection {

    private final Config<TagConfig> config;
    private ComponentType<EntityStore, PlayerData> playerDataType;


    public PlayerCommand(Config<TagConfig> config, ComponentType<EntityStore, PlayerData> playerDataType) {
        super("player", "Manage tags on a Player");
        this.config = config;
        this.playerDataType = playerDataType;

        this.addSubCommand(new AddTagToPlayerCommand(config, playerDataType));
    }

}
