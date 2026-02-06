package ca.sullyq.ezchat.commands.tags;

import ca.sullyq.ezchat.config.TagConfig;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import com.hypixel.hytale.server.core.util.Config;

public class TagCommand extends AbstractCommandCollection {

    private final Config<TagConfig> tagConfig;

    public TagCommand(Config<TagConfig> config) {
        super("tag", "Manages the tags");
        this.tagConfig = config;
        this.setPermissionGroup(null);

//        this.addSubCommand(new AddTagSubCommand(config));
//        this.addSubCommand(new RemoveTagSubCommand(config));
        this.addSubCommand(new ListTagSubCommand(config));
        this.addSubCommand(new ReloadTagSubCommand(config));
    }

    @Override
    protected boolean canGeneratePermission() {
        return true;
    }

}
