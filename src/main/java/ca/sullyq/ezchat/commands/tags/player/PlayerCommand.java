package ca.sullyq.ezchat.commands.tags;

import ca.sullyq.ezchat.config.PlayerTagConfig;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.util.Config;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class PlayerCommand extends CommandBase {

    private final Config<PlayerTagConfig> config;

    public PlayerCommand(Config<PlayerTagConfig> config) {
        super("player", "Add a tag to a Player");
        this.config = config;
    }

    @Override
    protected void executeSync(@NonNullDecl CommandContext commandContext) {

    }
}
