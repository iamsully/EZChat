package ca.sullyq.ezchat.commands.player;

import ca.sullyq.ezchat.EZChat;
import ca.sullyq.ezchat.config.PlayerConfig;
import ca.sullyq.ezchat.config.TagConfig;
import ca.sullyq.ezchat.helpers.MessageHelper;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.FlagArg;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractTargetPlayerCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.Config;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class GiveTagToPlayerCommand extends AbstractTargetPlayerCommand {

    private final Config<TagConfig> tagConfig = EZChat.getInstance().getTagConfig();
    private final Config<PlayerConfig> playerConfig = EZChat.getInstance().getPlayerConfig();

    private final RequiredArg<String> tagArg;
    private final FlagArg confirmOverwriteTagArg;

    public GiveTagToPlayerCommand() {
        super("give-tag", "Give a tag to a player");
        this.addAliases("gvtag", "gt");
        this.tagArg = withRequiredArg("tag", "The tag to give the player", ArgTypes.STRING);
        this.confirmOverwriteTagArg = withFlagArg("confirm", "Overwrite the players current tag");
    }

    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NullableDecl Ref<EntityStore> senderRef, @NonNullDecl Ref<EntityStore> targetRef, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world, @NonNullDecl Store<EntityStore> store) {

        if (playerConfig == null) {
            MessageHelper.sendErrorMessage(commandContext, "Couldn't find the PlayerConfig");
            return;
        }

        boolean isOverwrite = confirmOverwriteTagArg.get(commandContext);

        if (playerConfig.get().getPlayerTags().containsKey(playerRef.getUuid().toString()) && !isOverwrite) {
            MessageHelper.sendWarningMessage(commandContext, "This player has a tag already, please use the --confirm flag to overwrite their tag");
            return;
        }

        String tag = tagArg.get(commandContext);

        boolean isTagCreated = tagConfig.get().getTags().containsKey(tag);

        if (!isTagCreated) {
            MessageHelper.sendErrorMessage(commandContext, "Tag not found");
            return;
        }

        playerConfig.get().getPlayerTags().put(playerRef.getUuid().toString(), tag);

        playerConfig.save();

        MessageHelper.sendSuccessMessage(commandContext, "Successfully gave " + playerRef.getUsername() + " the tag " + tag);

    }

    @Override
    protected boolean canGeneratePermission() {
        return true;
    }
}
