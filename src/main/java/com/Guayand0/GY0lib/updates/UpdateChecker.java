package com.Guayand0.GY0lib.updates;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Utility class for checking plugin versions.
 */
public class UpdateChecker {

    /**
     * Compares two plugin versions in the format x.x.x.
     *
     * @param currentVersion The current version of the plugin.
     * @param lastVersion The latest available version of the plugin.
     * @return -1 if the latest version is greater, 1 if the current version is greater,
     *         0 if both versions are equal, and a positive or negative integer depending on the length difference.
     */
    public int compareVersions(String currentVersion, String lastVersion) {
        String[] currentParts = currentVersion.split("\\.");
        String[] latestParts = lastVersion.split("\\.");

        for (int i = 0; i < Math.min(currentParts.length, latestParts.length); i++) {
            int currentPart = Integer.parseInt(currentParts[i]);
            int latestPart = Integer.parseInt(latestParts[i]);
            if (latestPart > currentPart) {
                return -1; // The latest version is greater
            } else if (latestPart < currentPart) {
                return 1; // The current version is greater
            }
        }
        return Integer.compare(latestParts.length, currentParts.length);
    }

    /**
     * Get the latest plugin version from Spigot.
     *
     * @param spigotID The resource ID of the plugin on Spigot.
     * @return The latest version of the plugin.
     * @throws Exception If an error occurs during the HTTP request.
     */
    public String getLatestSpigotVersion(int spigotID, int timeOut) throws Exception {
        HttpURLConnection con = (HttpURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?resource=" + spigotID).openConnection();
        con.setConnectTimeout(timeOut);
        con.setReadTimeout(timeOut);
        return new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
    }

    /**
     * Checks if there is a newer plugin version available compared to the current one.
     *
     * @param currentVersion The version currently running.
     * @param newVersion The latest available version.
     * @return true if the new version is newer than the current version; false otherwise.
     */
    public boolean newPluginVersion(String currentVersion, String newVersion) {
        return compareVersions(currentVersion, newVersion) < 0;
    }
}
