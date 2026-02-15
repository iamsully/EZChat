package ca.sullyq.ezchat.events;

import ca.sullyq.ezchat.EZChat;
import ca.sullyq.ezchat.config.PlayerConfig;
import ca.sullyq.ezchat.config.TagConfig;
import ca.sullyq.ezchat.helpers.MessageParser;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent;
import com.hypixel.hytale.server.core.util.Config;

import java.util.Map;
import java.util.UUID;

public class ChatEvent {

    private static final Config<PlayerConfig> playerConfig = EZChat.getInstance().getPlayerConfig();
    private static final Config<TagConfig> tagConfig = EZChat.getInstance().getTagConfig();

    public static void onPlayerChat(PlayerChatEvent playerChatEvent) {
        UUID uuid = playerChatEvent.getSender().getUuid();

        Map<String, String> playerTags = playerConfig.get().getPlayerTags();

        if (playerTags.isEmpty()) return;

        String tagName = playerTags.get(uuid.toString());
        String playerTag = tagConfig.get().getTags().get(tagName);

        Message startingTag = MessageParser.parse(playerTag + " ");

        if (!playerTag.isEmpty()) {
            playerChatEvent.setFormatter(((playerRef, message) -> Message.join(
                    startingTag,
                    MessageParser.parse("<b>" + playerRef.getUsername() + ":</b> "),
                    MessageParser.parse("<color:#E6E6E6>" + message + "</color>")
            )));
        }
    }

}
