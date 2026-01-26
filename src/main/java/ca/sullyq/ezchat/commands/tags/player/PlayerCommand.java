package ca.sullyq.ezchat.commands.tags.player;

import ca.sullyq.ezchat.config.PlayerTagConfig;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.util.Config;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class PlayerCommand extends AbstractCommandCollection {

    private final Config<PlayerTagConfig> config;

    public PlayerCommand(Config<PlayerTagConfig> config) {
        super("player", "Manage tags on a Player");
        this.config = config;

        this.addSubCommand(new AddTagToPlayerCommand(config));
    }

}
