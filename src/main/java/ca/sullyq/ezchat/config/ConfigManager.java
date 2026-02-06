package ca.sullyq.ezchat.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.hypixel.hytale.logger.HytaleLogger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;

public class ConfigManager {
    private static final HytaleLogger logger = HytaleLogger.get("EZChat");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    private TagConfig tagConfig;

    private final File dataFolder;

    public ConfigManager(File dataFolder) {
        this.dataFolder = dataFolder;
    }

    public void loadConfig() {
        if (!dataFolder.exists()) {
            if (dataFolder.mkdirs()) {
                logger.at(Level.INFO).log("Created plugin folder: " + dataFolder.getAbsolutePath());
            } else {
                logger.at(Level.WARNING).log("Could not create plugin folder: " + dataFolder.getAbsolutePath());
            }
        }

        File configFile = new File(dataFolder, "config.json");

        if (!configFile.exists()) {
            logger.at(Level.INFO).log("Could not find the config file, creating default config.json");
//            ezChatConfig = new EZChatConfig();
            saveConfig();
        }

    }

    public void saveConfig() {
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        File configFile = new File(dataFolder, "config.json");
        JsonObject configJson = gson.toJsonTree(tagConfig).getAsJsonObject();

        try (Writer writer = new OutputStreamWriter(new FileOutputStream(configFile), StandardCharsets.UTF_8)) {
            gson.toJson(configJson, writer);
            logger.at(Level.INFO).log("Configuration saved to: " + configFile.getAbsolutePath());
        } catch (IOException e) {
            logger.at(Level.SEVERE).log("Failed to save config.json");
        }

    }
}
