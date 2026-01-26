package ca.sullyq.ezchat.commands.tags.player;

import ca.sullyq.ezchat.config.PlayerTagConfig;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.util.Config;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class AddTagToPlayerCommand extends CommandBase {

    private final Config<PlayerTagConfig> config;

    private final RequiredArg<String> playerArg;


    public AddTagToPlayerCommand(Config<PlayerTagConfig> config) {
        super("add", "Add a tag to a Player");
        this.config = config;

        playerArg = withRequiredArg("player", "Target Player", ArgTypes.STRING);
    }

    @Override
    protected void executeSync(@NonNullDecl CommandContext commandContext) {
        String player = playerArg.get(commandContext);

        commandContext.sendMessage(Message.raw(player));
    }
}
