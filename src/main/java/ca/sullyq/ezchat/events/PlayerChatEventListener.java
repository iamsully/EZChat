package ca.sullyq.ezchat.events;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

public class PlayerChatEventListener {

    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    public static void onPlayerChat(PlayerChatEvent playerChatEvent) {

//        PlayerRef playerRef = playerChatEvent.getSender();

        playerChatEvent.setFormatter(((playerRef, message) -> Message.join(
                Message.raw("[Admin] "),
                Message.raw(playerRef.getUsername() + ": "),
                Message.raw(message)
        )));
    }

}
