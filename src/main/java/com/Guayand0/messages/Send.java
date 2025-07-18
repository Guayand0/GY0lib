package com.Guayand0.messages;

import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.Map;

public class Send {

    private final MessageUtils MU = new MessageUtils();
    /**
     * Sends a list of messages to a CommandSender, applying color codes and replacing custom placeholders.
     *
     * @param sender The recipient of the messages (player or console).
     * @param messages The list of messages to send.
     * @param placeholders A map of placeholders to replace in each message.
     */
    public void sendMessageList(CommandSender sender, List<String> messages, Map<String, String> placeholders) {
        for (String message : messages) {
            sender.sendMessage(MU.getColoredReplacePluginPlaceholdersText(message, placeholders));
        }
    }

    /**
     * Sends a list of messages to a CommandSender, applying color codes and replacing custom placeholders.
     *
     * @param sender The recipient of the messages (player or console).
     * @param message The message to send.
     * @param placeholders A map of placeholders to replace in each message.
     */
    public void sendMessage(CommandSender sender, String message, Map<String, String> placeholders) {
        sender.sendMessage(MU.getColoredReplacePluginPlaceholdersText(message, placeholders));
    }
}
