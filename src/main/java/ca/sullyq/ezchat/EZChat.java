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

    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    private final Config<TagConfig> tagConfig;

    private final Config<PlayerConfig> playerConfig;

    @Getter
    private static EZChat instance;

    public EZChat(@Nonnull JavaPluginInit init) {
        super(init);
        instance = this;
        this.tagConfig = this.withConfig("TagConfig", TagConfig.CODEC);
        this.playerConfig = this.withConfig("PlayerTagConfig", PlayerConfig.CODEC);
    }

    @Override
    protected void setup() {
        LOGGER.at(Level.INFO).log("[EZ Chat] Setting up...");

        this.tagConfig.save();
        this.playerConfig.save();

        // Register commands
        registerCommands();

        // Register event listeners
        registerListeners();

        LOGGER.at(Level.INFO).log("[EZ Chat] Setup complete!");
    }

    /**
     * Register plugin commands.
     */
    private void registerCommands() {
        try {
            getCommandRegistry().registerCommand(new EZChatCommand(tagConfig));
            LOGGER.at(Level.INFO).log("[EZ Chat] Registered /ec command");
        } catch (Exception e) {
            LOGGER.at(Level.WARNING).withCause(e).log("[EZ Chat] Failed to register commands");
        }
    }

    /**
     * Register event listeners.
     */
    private void registerListeners() {
        try {
            this.getEventRegistry().registerGlobal(PlayerChatEvent.class, ChatEvent::onPlayerChat);
            LOGGER.at(Level.INFO).log("[EZ Chat] Registered player event listeners");
        } catch (Exception e) {
            LOGGER.at(Level.WARNING).withCause(e).log("[EZ Chat] Failed to register listeners");
        }
    }

    @Override
    protected void start() {
        LOGGER.at(Level.INFO).log("[EZ Chat] Started!");
        LOGGER.at(Level.INFO).log("[EZ Chat] Use /ec help for commands");
    }

    @Override
    protected void shutdown() {
        LOGGER.at(Level.INFO).log("[EZ Chat] Shutting down...");
        instance = null;
    }

}
