package com.Guayand0.data;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Utility for collecting and sending server/plugin data to an external API.
 */
public class DataSaver {

    /**
     * Collects server, system, and plugin data and sends it asynchronously via POST request to a remote endpoint.
     *
     * @param plugin The plugin instance triggering the data save.
     */
    public static void save(Plugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Connection connection = null;
                PreparedStatement statement = null;

                try {
                    // IP del servidor
                    String ip = getServerIp();

                    // Datos del servidor
                    String puerto = Bukkit.getServer().getPort() + "";
                    String minecraftVersion = getMinecraftVersion();
                    String javaVersion = System.getProperty("java.version");
                    String serverType = Bukkit.getName();
                    String maxPlayers = Bukkit.getMaxPlayers() + "";
                    String motd = Bukkit.getServer().getMotd();
                    String totalRam = Runtime.getRuntime().totalMemory() / 1024 / 1024 + " MB";
                    String onlineMode = Bukkit.getOnlineMode() ? 1 + "" : 0 + "";
                    String plugins = getPlugins();
                    //int onlinePlayers = Bukkit.getOnlinePlayers().size();
                    //long uptime = System.currentTimeMillis() - Bukkit.getServer().getWorlds().get(0).getFullTime();

                    // Datos del plugin
                    String pluginVersion = plugin.getDescription().getVersion();

                    // Datos adicionales
                    String fechaHoraActual = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());

                    // Datos del sistema
                    String operatingSystem = System.getProperty("os.name");
                    String systemArch = System.getProperty("os.arch");
                    String systemVersion = System.getProperty("os.version");
                    String coreCount = Runtime.getRuntime().availableProcessors() + "";
                    String cpuBrand = getCPUBrand();
                    String country = Locale.getDefault().getDisplayCountry();

                    URL url = new URL("https://iplugin.vercel.app/api/stats");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoOutput(true);

                    String pluginName = plugin.getDescription().getName().toLowerCase();

                    String json = "{"
                            + "\"plugin_name\":\"" + pluginName + "\","
                            + "\"ip\":\"" + ip + "\","
                            + "\"port\":\"" + puerto + "\","
                            + "\"server_type\":\"" + serverType + "\","
                            + "\"minecraft_version\":\"" + minecraftVersion + "\","
                            + "\"java_version\":\"" + javaVersion + "\","
                            + "\"plugin_version\":\"" + pluginVersion + "\","
                            + "\"date_time\":\"" + fechaHoraActual + "\","
                            + "\"max_players\":\"" + maxPlayers + "\","
                            + "\"motd\":\"" + motd + "\","
                            + "\"total_ram\":\"" + totalRam + "\","
                            + "\"online_mode\":\"" + onlineMode + "\","
                            + "\"operating_system\":\"" + operatingSystem + "\","
                            + "\"system_version\":\"" + systemVersion + "\","
                            + "\"system_arch\":\"" + systemArch + "\","
                            + "\"cpu_brand\":\"" + cpuBrand + "\","
                            + "\"core_count\":\"" + coreCount + "\","
                            + "\"country\":\"" + country + "\","
                            + "\"plugins\":\"" + plugins + "\""
                            + "}";

                    try (OutputStream os = conn.getOutputStream()) {
                        byte[] input = json.getBytes("utf-8");
                        os.write(input, 0, input.length);
                    }

                    conn.getResponseCode();

                    // Mostrar variables [debug]
                    /*System.out.println("ip: " + ip);
                    System.out.println("puerto: " + puerto);
                    System.out.println("minecraftVersion: " + minecraftVersion);
                    System.out.println("javaVersion: " + javaVersion);
                    System.out.println("serverType: " + serverType);
                    System.out.println("maxPlayers: " + maxPlayers);
                    System.out.println("motd: " + motd);
                    System.out.println("totalRam: " + totalRam);
                    System.out.println("onlineMode: " + onlineMode);
                    System.out.println("plugins: " + plugins);
                    //System.out.println("onlinePlayers: " + onlinePlayers);
                    //System.out.println("uptime: " + uptime);
                    System.out.println("pluginVersion: " + pluginVersion);
                    System.out.println("fechaHoraActual: " + fechaHoraActual);
                    System.out.println("operatingSystem: " + operatingSystem);
                    System.out.println("systemArch: " + systemArch);
                    System.out.println("systemVersion: " + systemVersion);
                    System.out.println("coreCount: " + coreCount);
                    System.out.println("cpuBrand: " + cpuBrand);
                    System.out.println("country: " + country);*/

                    // Conectar a la base de datos MySQL
                    /*String dbURL = "jdbc:mysql://bdgnqijlwepivp9knhwi-mysql.services.clever-cloud.com:3306/bdgnqijlwepivp9knhwi?useSSL=false";
                    String dbUser = "ujm7kv0hroajtegw";
                    String dbPassword = "Ac1hTT2nDV1s46iJxslj";
                    connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);

                    // Nombre de la tabla (en minúsculas)
                    String tableName = plugin.getDescription().getName().toLowerCase();

                    // Consulta SQL con los nuevos campos
                    String query = "INSERT INTO " + tableName + " (ip, port, server_type, minecraft_version, java_version, plugin_version, date_time, " +
                            "max_players, motd, total_ram, online_mode, operating_system, system_version, system_arch, cpu_brand, core_count, country, plugins)" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    statement = connection.prepareStatement(query);
                    statement.setString(1, ip);
                    statement.setString(2, puerto);
                    statement.setString(3, serverType);
                    statement.setString(4, minecraftVersion);
                    statement.setString(5, javaVersion);
                    statement.setString(6, pluginVersion);
                    statement.setString(7, fechaHoraActual);
                    statement.setString(8, maxPlayers);
                    statement.setString(9, motd);
                    statement.setString(10, totalRam);
                    statement.setString(11, onlineMode);
                    statement.setString(12, operatingSystem);
                    statement.setString(13, systemVersion);
                    statement.setString(14, systemArch);
                    statement.setString(15, cpuBrand);
                    statement.setString(16, coreCount);
                    statement.setString(17, country);
                    statement.setString(18, plugins);

                    statement.executeUpdate();
                    statement.close();

                } catch (SQLException ignored) {*/
                } catch (IOException ignored) {
                } finally {
                    try {if (statement != null) {statement.close();}} catch (Exception ignored) {}
                    try {if (connection != null) {connection.close();}} catch (Exception ignored) {}
                }
            }
        }.runTaskAsynchronously(plugin);
    }

    // ------------------------- METODOS ------------------------- //

    /**
     * Retrieves the list of installed plugin names.
     *
     * @return A list of plugin names in the server.
     */
    public static String getPlugins() {
        StringBuilder pluginsList = new StringBuilder();
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            pluginsList.append(plugin.getName()).append(", ");
        }
        return pluginsList.length() > 2 ? pluginsList.substring(0, pluginsList.length() - 2) : "NULL";
    }

    /**
     * Retrieves the public IP address of the server using an external service.
     *
     * @return The public IP of server host.
     */
    public static String getServerIp() {
        String ip = "Desconocida";
        try {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL("http://checkip.amazonaws.com").openStream()))) {
                ip = br.readLine();
            }
        } catch (Exception ignored) {}
        return ip;
    }

    /**
     * Returns the Minecraft server version in format x.x.x.
     *
     * @return The Minecraft version played.
     */
    public static String getMinecraftVersion() {
        try {
            String serverVersion = Bukkit.getVersion();
            String minecraftVersion = serverVersion.split("MC: ")[1].split(" ")[0];
            minecraftVersion = minecraftVersion.substring(0, minecraftVersion.length() - 1);
            return minecraftVersion;
        } catch (Exception e) {
            return "Desconocida";
        }
    }

    /**
     * Attempts to retrieve the brand/model of the server's CPU.
     *
     * @return The CPU brand.
     */

    public static String getCPUBrand() {
        String cpuBrand = "Desconocida";

        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            cpuBrand = System.getenv("PROCESSOR_IDENTIFIER");
        } else if (os.contains("linux")) {
            try {
                Process process = Runtime.getRuntime().exec("cat /proc/cpuinfo");
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("model name")) {
                        cpuBrand = line.split(":")[1].trim();
                        break;
                    }
                }
                reader.close();
            } catch (Exception ignored) {}
        } else if (os.contains("mac")) {
            try {
                Process process = Runtime.getRuntime().exec("sysctl -n machdep.cpu.brand_string");
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                cpuBrand = reader.readLine();
                reader.close();
            } catch (Exception ignored) {}
        }

        return cpuBrand;
    }
}
