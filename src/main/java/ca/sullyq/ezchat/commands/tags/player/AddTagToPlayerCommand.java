package ca.sullyq.ezchat.commands.tags.player;

import ca.sullyq.ezchat.config.PlayerData;
import ca.sullyq.ezchat.config.TagConfig;
import ca.sullyq.ezchat.handlers.MessageHandler;
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


public class AddTagToPlayerCommand extends AbstractTargetPlayerCommand {

    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    private final Config<TagConfig> config;
    private PlayerConfigData configData;

    private final RequiredArg<String> playerArg;
    private ComponentType<EntityStore, PlayerData> playerDataType;


    public AddTagToPlayerCommand(Config<TagConfig> config, ComponentType<EntityStore, PlayerData> playerDataType) {
        super("add", "Add a tag to a Player");
        this.config = config;
        this.playerDataType = playerDataType;

        playerArg = withRequiredArg("player", "Target Player", ArgTypes.STRING);
    }

//    @Override
//    protected void execute(@NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
//
//        Universe universe = Universe.get();
//
//
//        Player player = store.getComponent(ref, Player.getComponentType());
//        if (player == null) return;
//
//
//        this.configData = player.getPlayerConfigData();
//
//        LOGGER.at(Level.INFO).log("made it here?");
//
//        PlayerData playerData = store.ensureAndGetComponent(ref, playerDataType);
//        playerData.setTag("TestTag");
//        LOGGER.at(Level.INFO).log("made it here!!");
//
//        this.configData.markChanged();
//
//        commandContext.sendMessage(Message.raw("Added tag to player save?"));
//

    /// /        playerRef.referToServer("some ip address", 5520);
//    }
    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NullableDecl Ref<EntityStore> ref, @NonNullDecl Ref<EntityStore> ref1, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world, @NonNullDecl Store<EntityStore> store) {

        if (ref == null) {
            MessageHandler.sendErrorMessage(commandContext, "There was an error finding this player");
            return;
        }

        Player player = store.getComponent(ref, Player.getComponentType());
        if (player == null) return;

        this.configData = player.getPlayerConfigData();

    }
}
