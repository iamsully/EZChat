package ca.sullyq.ezchat.commands.tags;

import ca.sullyq.ezchat.config.PlayerTagConfig;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.util.Config;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

import java.util.Arrays;
import java.util.stream.Stream;


public class ListTagSubCommand extends CommandBase {

    private final Config<PlayerTagConfig> config;

    public ListTagSubCommand(Config<PlayerTagConfig> config) {
        super("list", "List all the tags");
        this.config = config;
        this.setPermissionGroup(null);
    }

    @Override
    protected boolean canGeneratePermission() {
        return false;
    }

    @Override
    protected void executeSync(@NonNullDecl CommandContext commandContext) {

        PlayerTagConfig playerTagConfig = config.get();

        String[] tags = playerTagConfig.getCustomTags();

        commandContext.sendMessage(Message.raw(Arrays.toString(tags)));

    }
}
