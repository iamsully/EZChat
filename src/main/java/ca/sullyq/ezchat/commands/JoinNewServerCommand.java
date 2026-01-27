package ca.sullyq.ezchat.commands;

import ca.sullyq.ezchat.config.PlayerConfig;
import ca.sullyq.ezchat.config.TagConfig;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.player.data.PlayerConfigData;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.Config;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class JoinNewServerCommand extends AbstractPlayerCommand {

    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    private final Config<TagConfig> config;
    private ComponentType<EntityStore, PlayerConfig> playerDataType;

    private PlayerConfigData configData;


    public JoinNewServerCommand(Config<TagConfig> config, ComponentType<EntityStore, PlayerConfig> playerDataType) {
        super("join", "Join DarkMatterEconomy Server");
        this.config = config;
        this.playerDataType = playerDataType;
    }

    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        playerRef.referToServer("play.hyclash.com", 5520);
    }
}
