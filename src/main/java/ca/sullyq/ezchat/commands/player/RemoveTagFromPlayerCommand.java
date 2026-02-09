package ca.sullyq.ezchat.commands.player;

import ca.sullyq.ezchat.EZChat;
import ca.sullyq.ezchat.config.PlayerConfig;
import ca.sullyq.ezchat.config.TagConfig;
import ca.sullyq.ezchat.helpers.MessageHelper;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractTargetPlayerCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.Config;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class RemoveTagFromPlayerCommand extends AbstractTargetPlayerCommand {

    private final Config<TagConfig> tagConfig = EZChat.getInstance().getTagConfig();
    private final Config<PlayerConfig> playerConfig = EZChat.getInstance().getPlayerConfig();

    private final RequiredArg<String> tagArg;

    public RemoveTagFromPlayerCommand() {
        super("remove", "Give a tag to a player");
        this.tagArg = withRequiredArg("tag", "The tag to give the player", ArgTypes.STRING);
    }

    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NullableDecl Ref<EntityStore> senderRef, @NonNullDecl Ref<EntityStore> targetRef, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world, @NonNullDecl Store<EntityStore> store) {

        if (playerConfig == null) {
            MessageHelper.sendErrorMessage(commandContext, "Couldn't find the PlayerConfig");
            return;
        }

        if (!playerConfig.get().getPlayerTags().containsKey(playerRef.getUuid().toString())) {
            MessageHelper.sendWarningMessage(commandContext, "This player does not have a tag");
            return;
        }

        /* TODO -- Maybe just have the command /ec remove. Having to specify the tag seems redundant considering there's
             Only one tag per player..
        */
        String tag = tagArg.get(commandContext);

        boolean isTagCreated = tagConfig.get().getTags().containsKey(tag);

        if (!isTagCreated) {
            MessageHelper.sendErrorMessage(commandContext, "Tag not found");
            return;
        }

        playerConfig.get().getPlayerTags().remove(playerRef.getUuid().toString());

        playerConfig.save();

        MessageHelper.sendSuccessMessage(commandContext, "Successfully removed " + tag + " from " + playerRef.getUsername());

    }

    @Override
    protected boolean canGeneratePermission() {
        return true;
    }
}
