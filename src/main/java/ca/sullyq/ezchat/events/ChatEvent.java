package ca.sullyq.ezchat.events;

import ca.sullyq.ezchat.EZChat;
import ca.sullyq.ezchat.config.PlayerConfig;
import ca.sullyq.ezchat.config.TagConfig;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent;
import com.hypixel.hytale.server.core.permissions.PermissionsModule;
import com.hypixel.hytale.server.core.util.Config;
import fi.sulku.hytale.TinyMsg;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;

public class ChatEvent {

    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    private static final Config<PlayerConfig> playerConfig = EZChat.getInstance().getPlayerConfig();
    private static final Config<TagConfig> tagConfig = EZChat.getInstance().getTagConfig();

    public static void onPlayerChat(PlayerChatEvent playerChatEvent) {
        UUID uuid = playerChatEvent.getSender().getUuid();

        Map<String, String> playerTags = playerConfig.get().getPlayerTags();

        if (playerTags.isEmpty()) return;

        String tagName = playerTags.get(uuid.toString());
        String playerTag = tagConfig.get().getTags().get(tagName);

        Message startingTag = TinyMsg.parse(playerTag + " ");

        if (!playerTag.isEmpty()) {
            playerChatEvent.setFormatter(((playerRef, message) -> Message.join(
                    startingTag,
                    TinyMsg.parse("<b>" + playerRef.getUsername() + ":</b> "),
                    TinyMsg.parse("<color:#E6E6E6>" + message + "</color>")
            )));
        }
    }

}
