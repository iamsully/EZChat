package ca.sullyq.ezchat.handlers;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import fi.sulku.hytale.TinyMsg;

public class MessageHandler {

    public static void sendErrorMessage(CommandContext commandContext, String message) {
        commandContext.sendMessage(TinyMsg.parse("<color:#f84848><bold>" + message + "</bold></color>"));
    }

}
