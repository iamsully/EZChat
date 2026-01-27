package ca.sullyq.ezchat.events;

import com.hypixel.hytale.event.EventRegistry;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent;

import java.util.logging.Level;

public class PlayerChatListener {

    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();


    public static void onPlayerChat(PlayerChatEvent playerChatEvent) {
        playerChatEvent.setFormatter(((playerRef, message) -> Message.join(
                Message.raw("[Admin] "),
                Message.raw(playerRef.getUsername() + ": "),
                Message.raw(message)
        )));
    }

}
