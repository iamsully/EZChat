package ca.sullyq.ezchat.commands.tags;

import ca.sullyq.ezchat.config.TagConfig;
import ca.sullyq.ezchat.handlers.MessageHandler;
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

    private final Config<TagConfig> config;

    private final String[] acceptedColors = {"black", "dark_blue", "dark_green", "dark_aqua", "dark_red",
            "dark_purple", "gold", "gray", "dark_gray", "blue",
            "green", "aqua", "red", "light_purple", "yellow", "white"};


    private final RequiredArg<String> tagName;
    private final RequiredArg<String> color;

    private final int TAG_NAME_LENGTH = 10;


    public AddTagSubCommand(Config<TagConfig> config) {
        super("add", "Adds a new tag on the server(config)");
        this.config = config;
        this.tagName = withRequiredArg("name", "The new tag to add", ArgTypes.STRING);
        this.color = withRequiredArg("color", "The color for this tag", ArgTypes.STRING);
    }

    @Override
    protected void executeSync(@NonNullDecl CommandContext commandContext) {

        TagConfig tagConfig = config.get();

        String tagArg = tagName.get(commandContext);
        String colorArg = color.get(commandContext);

        if (tagArg.length() > TAG_NAME_LENGTH) {
            MessageHandler.sendErrorMessage(commandContext, "This tag is too long. (Max " + TAG_NAME_LENGTH + " characters)");
            return;
        }

        // TODO: Make the tag customizable too
        String newTag = "[" + tagArg + "]";

        boolean isTagAlreadyCreated = tagConfig.getPlayerTags().containsKey(newTag);

        if (isTagAlreadyCreated) {
            Message tagAlreadyCreated = TinyMsg.parse("<color:red>This tag has already been created</color>");
            commandContext.sendMessage(tagAlreadyCreated);
            return;
        }

        boolean isHexColor = colorArg.startsWith("#") && colorArg.length() == 7;

        if (!isHexColor) {
            boolean acceptedNamedColor = Arrays.asList(acceptedColors).contains(colorArg);

            if (!acceptedNamedColor) {
                MessageHandler.sendErrorMessage(commandContext, "This isn't a valid color.");
                return;
            }
        }

        tagConfig.getPlayerTags().put(newTag, colorArg);

        // TODO: Use the completable future and make sure this completes.
        config.save();

        Message savedMessage = TinyMsg.parse("<color:" + colorArg + ">" + " Saved new tag: " + newTag + "</color>");
        commandContext.sendMessage(savedMessage);

    }

    @Override
    protected boolean canGeneratePermission() {
        return true;
    }

}
