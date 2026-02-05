package ca.sullyq.ezchat.commands.tags;

import ca.sullyq.ezchat.config.TagConfig;
import ca.sullyq.ezchat.helpers.MessageHelper;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.util.Config;
import fi.sulku.hytale.TinyMsg;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

import java.util.Map;


public class ListTagSubCommand extends CommandBase {

    private final Config<TagConfig> config;

    public ListTagSubCommand(Config<TagConfig> config) {
        super("list", "List all the tags");
        this.config = config;
        this.setPermissionGroup(null);
    }

    @Override
    protected void executeSync(@NonNullDecl CommandContext commandContext) {

        TagConfig tagConfig = config.get();

        Map<String, String> playerTagsMap = tagConfig.getTags();

        if (playerTagsMap.isEmpty()) {
            MessageHelper.sendErrorMessage(commandContext, "There is no created Tags");
            return;
        }

        StringBuilder formattedString = new StringBuilder();

        playerTagsMap.forEach((key, value) -> {
            formattedString.append("<color:").append(value).append(">").append(key).append("</color>").append(" ");
        });

        Message message = TinyMsg.parse(formattedString.toString());

        commandContext.sendMessage(message);

    }

    @Override
    protected boolean canGeneratePermission() {
        return true;
    }

}
