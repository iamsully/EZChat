package ca.sullyq.ezchat.commands.tags.player;

import ca.sullyq.ezchat.config.PlayerData;
import ca.sullyq.ezchat.config.PlayerTagConfig;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.Config;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class PlayerCommand extends AbstractCommandCollection {

    private final Config<PlayerTagConfig> config;
    private ComponentType<EntityStore, PlayerData> playerDataType;


    public PlayerCommand(Config<PlayerTagConfig> config, ComponentType<EntityStore, PlayerData> playerDataType) {
        super("player", "Manage tags on a Player");
        this.config = config;
        this.playerDataType = playerDataType;

        this.addSubCommand(new AddTagToPlayerCommand(config, playerDataType));
    }

}
