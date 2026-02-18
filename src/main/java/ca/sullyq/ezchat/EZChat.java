package ca.sullyq.ezchat;

import ca.sullyq.ezchat.commands.EZChatCommand;
import ca.sullyq.ezchat.config.PlayerConfig;
import ca.sullyq.ezchat.config.TagConfig;
import ca.sullyq.ezchat.events.ChatEvent;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.util.Config;
import lombok.Getter;

import javax.annotation.Nonnull;
import java.util.logging.Level;

@Getter
public class EZChat extends JavaPlugin {
    private final String GREEN = "\u001b[0;32m";
    private final String PLUGIN_NAME = GREEN + "[EZ Chat] ";

    private static final HytaleLogger logger = HytaleLogger.forEnclosingClass();

    private final Config<PlayerConfig> playerConfig;
    private final Config<TagConfig> tagConfig;

    @Getter
    private static EZChat instance;

    public EZChat(@Nonnull JavaPluginInit init) {
        super(init);
        instance = this;
        this.playerConfig = this.withConfig("PlayerTagConfig", PlayerConfig.CODEC);
        this.tagConfig = this.withConfig("TagConfig", TagConfig.CODEC);
    }

    @Override
    protected void setup() {
        logger.at(Level.INFO).log(PLUGIN_NAME+"Setting up...");

        this.playerConfig.save();
        this.tagConfig.save();

        // Register commands
        registerCommands();

        // Register event listeners
        registerListeners();

        logger.at(Level.INFO).log(PLUGIN_NAME+"Setup complete!");
    }

    /**
     * Register plugin commands.
     */
    private void registerCommands() {
        try {
            getCommandRegistry().registerCommand(new EZChatCommand());
            logger.at(Level.INFO).log(PLUGIN_NAME+"Registered /ec command");
        } catch (Exception e) {
            logger.at(Level.WARNING).withCause(e).log(PLUGIN_NAME+"Failed to register commands");
        }
    }

    /**
     * Register event listeners.
     */
    private void registerListeners() {
        try {
            this.getEventRegistry().registerGlobal(PlayerChatEvent.class, ChatEvent::onPlayerChat);
            logger.at(Level.INFO).log(PLUGIN_NAME+"Registered player event listeners");
        } catch (Exception e) {
            logger.at(Level.WARNING).withCause(e).log(PLUGIN_NAME+"Failed to register listeners");
        }
    }

    @Override
    protected void start() {
        logger.at(Level.INFO).log(PLUGIN_NAME+"Started!");
        logger.at(Level.INFO).log(PLUGIN_NAME+"Use /ec help for commands");
    }

    @Override
    protected void shutdown() {
        logger.at(Level.INFO).log(PLUGIN_NAME+"Shutting down...");
        instance = null;
    }

}
