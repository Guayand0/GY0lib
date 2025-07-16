package com.Guayand0.GY0lib;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class BankUtils {

    // ------------------------- ECONOMY ------------------------- //

    /**
     * Gets the player's current balance from the Vault economy.
     *
     * @param player The player whose balance is to be retrieved.
     * @param economy The Vault economy instance.
     * @return The player balance.
     */
    public int getPlayerBalance(Player player, Economy economy) {
        return (int) economy.getBalance(player);
    }

    // ------------------------- CONFIG SAVE EXCEPTIONS ------------------------- //

    /**
     * Checks if saving exceptions is allowed from the config.
     *
     * @param plugin The plugin instance.
     * @return If "exception.save" is enabled or not.
     */
    public static boolean getSaveException(Plugin plugin) {
        return plugin.getConfig().getBoolean("exception.save", true);
    }

    /**
     * Checks if exceptions should be deleted on plugin start from the config.
     *
     * @param plugin The plugin instance.
     * @return If "exception.delete-on-start" is enabled or not.
     */
    public static boolean getDeleteOnStartExceptions(Plugin plugin) {
        return plugin.getConfig().getBoolean("exception.delete-on-start", false);
    }

    // ------------------------- SERVER ------------------------- //

    /**
     * Checks if a player is currently online.
     *
     * @param playerName The name of the player to check.
     * @return If the player is online or not.
     */
    public boolean isPlayerOnline(String playerName) {
        for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers())
            if (onlinePlayer.getName().equalsIgnoreCase(playerName)) return true;
        return false;
    }

    // ------------------------- -- ------------------------- //
}
