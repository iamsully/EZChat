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
//        UUID uuid = playerChatEvent.getSender().getUuid();
//        String username = playerChatEvent.getSender().getUsername();
//
//        Map<String, String> playerTags = playerConfig.get().getPlayerTags();
//
//        if (playerTags.isEmpty()) return;
//
//        String playerTag = playerTags.get(uuid.toString());
//        String tagColor = tagConfig.get().getTags().get(playerTag);
//
//        Message startingTag = TinyMsg.parse("<color:" + tagColor + ">" + playerTag + "</color> ");
//        Message formattedUsername = TinyMsg.parse("<color:" + tagColor + ">" + username + "</color> " + ": ");
//
//        Set<String> playerGroups = PermissionsModule.get().getGroupsForUser(uuid);
//
//        LOGGER.at(Level.INFO).log(Arrays.toString(playerGroups.toArray()));
//
//        if (!playerTag.isEmpty() && !tagColor.isEmpty()) {
//            playerChatEvent.setFormatter(((playerRef, message) -> Message.join(
//                    startingTag,
//                    formattedUsername,
//                    Message.raw(message)
//            )));
//        }

        String rank = tagConfig.get().getTags().get("Tester3");

        Message rankMessage = TinyMsg.parse(rank);
        playerChatEvent.setFormatter(((playerRef, message) -> Message.join(
                rankMessage,
                Message.raw(playerRef.getUsername()),
                Message.raw(message)
        )));
    }

}
