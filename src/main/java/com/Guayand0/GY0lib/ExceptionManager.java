package com.Guayand0.GY0lib;

import org.bukkit.plugin.Plugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExceptionManager {

    /**
     * Saves the details of an exception to a log file inside the plugin's data folder.
     * The log file is stored in the "exceptions" folder and named with the current timestamp.
     *
     * @param e The exception to be logged.
     * @param plugin The plugin instance.
     * @return A message indicating the success or failure of saving the exception.
     */
    public static String saveInLog(Exception e, Plugin plugin) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        File logDirectory = new File(plugin.getDataFolder() + "/exceptions/");

        // Crear el directorio si no existe
        if (!logDirectory.exists()) logDirectory.mkdirs();

        File logFile = new File(logDirectory, date + ".txt");

        try (BufferedWriter logWriter = new BufferedWriter(new FileWriter(logFile, true))) {
            logWriter.write("Plugin version: " + plugin.getDescription().getVersion() + "\n");
            // Hashmap con datos de variables -- "playerBalance: 300" -- "playerLevel: -1"
            logWriter.write("Type: " + e.getClass().getName() + "\n");
            logWriter.write("Message: " + e.getMessage() + "\n");
            logWriter.write("StackTrace: \n");
            for (StackTraceElement element : e.getStackTrace()) logWriter.write("\t" + element.toString() + "\n");
            return " &4&lException saved in " + logFile.getPath();
        } catch (Exception ex) {
            return " &4&lERROR when trying to save exception to " + logFile.getPath();
        }
    }

    /**
     * Deletes all saved exception log files and the "exceptions" folder from the plugin's data folder.
     *
     * @param plugin The plugin instance.
     * @return A message indicating the success or failure of deletion.
     */
    public static String deleteLogFile(Plugin plugin) {
        File logDirectory = new File(plugin.getDataFolder() + "/exceptions/");

        // Comprobar si la carpeta existe
        if (logDirectory.exists() && logDirectory.isDirectory()) {
            // Borrar todos los archivos dentro de la carpeta
            File[] files = logDirectory.listFiles();
            if (files != null) {
                for (File file : files) {
                    // Borrar cada archivo dentro de la carpeta
                    if (!file.delete()) return "%plugin% &4&lERROR when trying to delete file: " + file.getName();
                }
            }

            // Borrar la carpeta después de eliminar los archivos
            if (!logDirectory.delete()) return "%plugin% &4&lERROR when trying to delete directory: " + logDirectory.getName();
            else return "%plugin% &eException folder deleted successfully.";
        } else return "";
    }
}
