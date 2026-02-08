package ca.sullyq.ezchat.commands.tags;

import ca.sullyq.ezchat.config.TagConfig;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import com.hypixel.hytale.server.core.util.Config;

public class TagCommand extends AbstractCommandCollection {

    public TagCommand() {
        super("tag", "Manages the tags");
        this.addAliases("t");
        this.setPermissionGroup(null);

//        this.addSubCommand(new AddTagSubCommand(config));
        this.addSubCommand(new RemoveTagSubCommand());
        this.addSubCommand(new ListTagSubCommand());
        this.addSubCommand(new ReloadTagSubCommand());
    }

    @Override
    protected boolean canGeneratePermission() {
        return true;
    }

}
