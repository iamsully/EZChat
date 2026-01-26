package ca.sullyq.ezchat.commands;

import ca.sullyq.ezchat.config.PlayerTagConfig;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.util.Config;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class AddTagSubCommand extends CommandBase {

    private final Config<PlayerTagConfig> config;

    private final RequiredArg<String> tagName;

    private final int TAG_NAME_LENGTH = 10;


    public AddTagSubCommand(Config<PlayerTagConfig> config) {
        super("add", "Adds a new tag on the server(config)");
        this.config = config;
        this.tagName = withRequiredArg("tag", "The new tag to add", ArgTypes.STRING);
        this.setPermissionGroup(null);
    }

    @Override
    protected boolean canGeneratePermission() {
        return false;
    }

    @Override
    protected void executeSync(@NonNullDecl CommandContext commandContext) {

        PlayerTagConfig playerTagConfig = config.get();

        String tag = playerTagConfig.getCustomTags()[0];

        if (tag.isEmpty()) return;

        String tagArg = tagName.get(commandContext);

        if (tagArg.length() > TAG_NAME_LENGTH) {
            commandContext.sendMessage(Message.raw("This tag is too many characters."));
            return;
        }

        String newTag = "[" + tagArg + "]";

        commandContext.sendMessage(Message.raw("First Tag" + tag + "   New Tag: " + newTag));

    }
}
