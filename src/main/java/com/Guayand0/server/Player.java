package com.Guayand0.server;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;

public class Player {
    /**
     * Checks if a player is currently online.
     *
     * @param playerName The name of the player to check.
     * @return If the player is online or not.
     */
    public boolean isPlayerOnline(String playerName) {
        for (org.bukkit.entity.Player onlinePlayer : Bukkit.getServer().getOnlinePlayers())
            if (onlinePlayer.getName().equalsIgnoreCase(playerName)) return true;
        return false;
    }

    /**
     * Gets the player's current balance from the Vault economy.
     *
     * @param player The player whose balance is to be retrieved.
     * @param economy The Vault economy instance.
     * @return The player balance.
     */
    public double getPlayerBalance(org.bukkit.entity.Player player, Economy economy) {
        return economy.getBalance(player);
    }
}
