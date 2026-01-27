package ca.sullyq.ezchat.commands.tags;

import ca.sullyq.ezchat.config.TagConfig;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.util.Config;
import fi.sulku.hytale.TinyMsg;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;


public class RemoveTagSubCommand extends CommandBase {

    private final Config<TagConfig> config;

    private final String[] acceptedColors = {"black", "dark_blue", "dark_green", "dark_aqua", "dark_red",
            "dark_purple", "gold", "gray", "dark_gray", "blue",
            "green", "aqua", "red", "light_purple", "yellow", "white"};


    private final RequiredArg<String> tagName;


    public RemoveTagSubCommand(Config<TagConfig> config) {
        super("remove", "Removes a tag on the server(config)");
        this.config = config;
        this.tagName = withRequiredArg("name", "The tag to remove", ArgTypes.STRING);
    }

    @Override
    protected void executeSync(@NonNullDecl CommandContext commandContext) {

        TagConfig tagConfig = config.get();

        String tagArg = tagName.get(commandContext);

        // TODO: This will be refactored to allow custom / any
        String tagToRemove = "[" + tagArg + "]";


        boolean isTagCreated = tagConfig.getTags().containsKey(tagToRemove);

        if (!isTagCreated) {
            Message tagAlreadyCreatedMessage = TinyMsg.parse("<color:red>This tag has not yet been created</color>");
            commandContext.sendMessage(tagAlreadyCreatedMessage);
            return;
        }

        tagConfig.getTags().remove(tagToRemove);

        // TODO: Use the completable future and make sure this completes.
        config.save();

        Message removedTagMessage = TinyMsg.parse("<color:green>This tag has been removed</color>");
        commandContext.sendMessage(removedTagMessage);

    }

    @Override
    protected boolean canGeneratePermission() {
        return true;
    }

}
