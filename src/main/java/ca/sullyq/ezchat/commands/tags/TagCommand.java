package ca.sullyq.ezchat.commands.tags;

import ca.sullyq.ezchat.config.PlayerTagConfig;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import com.hypixel.hytale.server.core.util.Config;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class TagCommand extends AbstractCommandCollection {

    private final Config<PlayerTagConfig> config;

    public TagCommand(Config<PlayerTagConfig> config) {
        super("tag", "Manages the tags");
        this.config = config;
        this.setPermissionGroup(null);

        this.addSubCommand(new AddTagSubCommand(config));
        this.addSubCommand(new ListTagSubCommand(config));
    }
}
