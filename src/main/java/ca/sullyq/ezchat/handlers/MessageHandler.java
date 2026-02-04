package ca.sullyq.ezchat.handlers;

import com.hypixel.hytale.server.core.command.system.CommandContext;
import fi.sulku.hytale.TinyMsg;

public class MessageHandler {

    public static void sendErrorMessage(CommandContext commandContext, String message) {
        commandContext.sendMessage(TinyMsg.parse("<color:#f84848><bold>" + message + "</bold></color>"));
    }

    public static void sendSuccessMessage(CommandContext commandContext, String message) {
        commandContext.sendMessage(TinyMsg.parse("<color:#3d9f43><bold>" + message + "</bold></color>"));
    }

    public static void sendWarningMessage(CommandContext commandContext, String message) {
        commandContext.sendMessage(TinyMsg.parse("<color:#d6bf33><bold>" + message + "</bold></color>"));
    }

}
