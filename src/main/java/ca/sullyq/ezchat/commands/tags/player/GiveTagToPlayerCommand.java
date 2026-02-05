package ca.sullyq.ezchat.commands.tags.player;

import ca.sullyq.ezchat.EZChat;
import ca.sullyq.ezchat.config.PlayerConfig;
import ca.sullyq.ezchat.config.TagConfig;
import ca.sullyq.ezchat.helpers.MessageHelper;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractTargetPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.Config;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.logging.Level;

public class GiveTagToPlayerCommand extends AbstractTargetPlayerCommand {

    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    private final Config<TagConfig> tagConfig;
    private final Config<PlayerConfig> playerConfig = EZChat.getInstance().getPlayerConfig();

    private final RequiredArg<String> tagArg;

    public GiveTagToPlayerCommand(Config<TagConfig> config) {
        super("give", "Give a tag to a player");
        this.tagConfig = config;
        this.tagArg = withRequiredArg("Tag", "The tag to give the player", ArgTypes.STRING);
    }

    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NullableDecl Ref<EntityStore> senderRef, @NonNullDecl Ref<EntityStore> targetRef, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world, @NonNullDecl Store<EntityStore> store) {

        if (playerConfig == null) {
            MessageHelper.sendErrorMessage(commandContext, "Couldn't find the PlayerConfig");
            return;
        }

        // TODO: Implement the --overwrite flag
        if (playerConfig.get().getPlayerTags().containsKey(playerRef.getUuid().toString())) {
            MessageHelper.sendWarningMessage(commandContext, "This player has a tag already, please use the --overwrite flag to overwrite their tag");
        }

        LOGGER.at(Level.INFO).log(playerRef.getUsername());

        // TODO: I don't think i need this
        Player player = store.getComponent(targetRef, Player.getComponentType());
        if (player == null) return;

        String tag = tagArg.get(commandContext);

        // TODO: This is silly.. i know.
        String tagToGive = "[" + tag + "]";

        boolean isTagCreated = tagConfig.get().getTags().containsKey(tagToGive);

        if (!isTagCreated) {
            MessageHelper.sendErrorMessage(commandContext, "Tag not found");
            return;
        }

        playerConfig.get().getPlayerTags().put(playerRef.getUuid().toString(), tagToGive);

        playerConfig.save();

        MessageHelper.sendSuccessMessage(commandContext, "Successfully gave " + playerRef.getUsername() + " the tag " + tagToGive);

    }

    @Override
    protected boolean canGeneratePermission() {
        return true;
    }
}
