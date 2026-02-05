package ca.sullyq.ezchat.commands.tags;

import ca.sullyq.ezchat.config.EZChatConfig;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import com.hypixel.hytale.server.core.util.Config;

public class TagCommand extends AbstractCommandCollection {

    private final Config<EZChatConfig> ezChatConfig;

    public TagCommand(Config<EZChatConfig> config) {
        super("tag", "Manages the tags");
        this.ezChatConfig = config;
        this.setPermissionGroup(null);

        this.addSubCommand(new AddTagSubCommand(config));
        this.addSubCommand(new RemoveTagSubCommand(config));
        this.addSubCommand(new ListTagSubCommand(config));
    }

    @Override
    protected boolean canGeneratePermission() {
        return true;
    }

}
