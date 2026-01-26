package ca.sullyq.ezchat.commands.tags;

import ca.sullyq.ezchat.config.PlayerTagConfig;
import com.hypixel.hytale.common.util.ArrayUtil;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.util.Config;
import fi.sulku.hytale.TinyMsg;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

import java.util.Arrays;
import java.util.Locale;

public class AddTagSubCommand extends CommandBase {

    private final Config<PlayerTagConfig> config;

    private final RequiredArg<String> tagName;

    private final int TAG_NAME_LENGTH = 10;


    public AddTagSubCommand(Config<PlayerTagConfig> config) {
        super("add", "Adds a new tag on the server(config)");
        this.config = config;
        this.tagName = withRequiredArg("name", "The new tag to add", ArgTypes.STRING);
        this.setPermissionGroup(null);
    }

    @Override
    protected boolean canGeneratePermission() {
        return false;
    }

    @Override
    protected void executeSync(@NonNullDecl CommandContext commandContext) {

        PlayerTagConfig playerTagConfig = config.get();

        String tagArg = tagName.get(commandContext);

        if (tagArg.length() > TAG_NAME_LENGTH) {
            Message tagNameLimit = TinyMsg.parse("<color:red>This tag name is too long</color>");
            commandContext.sendMessage(tagNameLimit);
            return;
        }

        boolean isTagAlreadyCreated = Arrays.stream(playerTagConfig.getCustomTags()).anyMatch(tag -> tag.toLowerCase(Locale.ROOT).contains(tagArg.toLowerCase(Locale.ROOT)));

        if (isTagAlreadyCreated) {
            Message tagAlreadyCreated = TinyMsg.parse("<color:red>This tag has already been created</color>");
            commandContext.sendMessage(tagAlreadyCreated);
            return;
        }

        String newTag = "[" + tagArg + "]";
        playerTagConfig.setCustomTags(ArrayUtil.append(playerTagConfig.getCustomTags(), newTag));

        // TODO: Use the completable future and make sure this completes.
        config.save();

        Message savedMessage = TinyMsg.parse("<color:green> Saved new tag: " + newTag + "</color>");
        commandContext.sendMessage(savedMessage);

    }
}
