package ca.sullyq.ezchat.commands.tags;

import ca.sullyq.ezchat.EZChat;
import ca.sullyq.ezchat.config.TagConfig;
import ca.sullyq.ezchat.helpers.MessageHelper;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.util.Config;
import fi.sulku.hytale.TinyMsg;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

import java.util.Map;

public class ReloadTagSubCommand extends CommandBase {

    private final Config<TagConfig> tagConfig = EZChat.getInstance().getTagConfig();

    public ReloadTagSubCommand() {
        super("reload", "reload the tags config");
        this.addAliases("rl");
        this.setPermissionGroup(null);
    }

    @Override
    protected void executeSync(@NonNullDecl CommandContext commandContext) {
        this.tagConfig.load();
        MessageHelper.sendSuccessMessage(commandContext, "Successfully reloaded Tag Config");
    }

}
