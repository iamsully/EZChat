package ca.sullyq.ezchat.commands.tags.player;

import ca.sullyq.ezchat.config.PlayerConfig;
import ca.sullyq.ezchat.config.TagConfig;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractTargetPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.data.PlayerConfigData;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.Config;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.logging.Level;


public class AddTagToPlayerCommand extends AbstractTargetPlayerCommand {

    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    private final Config<TagConfig> config;
    private PlayerConfigData configData;

    private ComponentType<EntityStore, PlayerConfig> playerDataType;

    private RequiredArg<String> tagArg;

    public AddTagToPlayerCommand(Config<TagConfig> config, ComponentType<EntityStore, PlayerConfig> playerDataType) {
        super("addtag", "Add a tag to a Player");
        this.config = config;
        this.playerDataType = playerDataType;
        this.tagArg = withRequiredArg("Tag", "The tag to add to the player", ArgTypes.STRING);
    }


    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NullableDecl Ref<EntityStore> senderRef, @NonNullDecl Ref<EntityStore> targetRef, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world, @NonNullDecl Store<EntityStore> store) {

        LOGGER.at(Level.INFO).log(playerRef.getUsername());

        Player player = store.getComponent(targetRef, Player.getComponentType());
        if (player == null) return;

        this.configData = player.getPlayerConfigData();

        String tag = tagArg.get(commandContext);

        PlayerConfig playerConfig = store.ensureAndGetComponent(targetRef, playerDataType);
        playerConfig.setTag(tag);

        this.configData.markChanged();

        this.configData = player.getPlayerConfigData();

    }
}
