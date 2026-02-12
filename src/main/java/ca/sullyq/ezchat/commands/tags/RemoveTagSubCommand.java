package ca.sullyq.ezchat.commands.tags;

import ca.sullyq.ezchat.EZChat;
import ca.sullyq.ezchat.config.TagConfig;
import ca.sullyq.ezchat.helpers.MessageHelper;
import com.hypixel.hytale.math.vector.Transform;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.util.Config;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;


public class RemoveTagSubCommand extends CommandBase {

    private final Config<TagConfig> tagConfig = EZChat.getInstance().getTagConfig();
    private final RequiredArg<String> tagName;

    public RemoveTagSubCommand() {
        super("remove", "Removes a tag on the server(config)");
        this.addAliases("rm");
        this.tagName = withRequiredArg("name", "The tag to remove", ArgTypes.STRING);
    }

    @Override
    protected void executeSync(@NonNullDecl CommandContext commandContext) {

        String tagToRemove = tagName.get(commandContext);

        boolean isTagCreated = tagConfig.get().getTags().containsKey(tagToRemove);

        if (!isTagCreated) {
            MessageHelper.sendErrorMessage(commandContext, "This tag has not yet been created");
            return;
        }

        tagConfig.get().getTags().remove(tagToRemove);

        // TODO: Use the completable future and make sure this completes.
        tagConfig.save();

        MessageHelper.sendSuccessMessage(commandContext, tagToRemove + " tag has been removed");

    }

    @Override
    protected boolean canGeneratePermission() {
        return true;
    }

}
