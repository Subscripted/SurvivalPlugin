package dev.subscripted.survivalplugin.utils;


import dev.subscripted.survivalplugin.Main;
import dev.subscripted.survivalplugin.modules.database.MySQL;
import dev.subscripted.survivalplugin.modules.enums.ConfigMessage;
import dev.subscripted.survivalplugin.modules.enums.UIText;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {

    private static File mysqlFile;
    private static File configFile;

    @Getter
    private static FileConfiguration mysqlConfig;

    @Getter
    private static FileConfiguration config;


    public static void setupFiles(Main instance) {
        mysqlFile = new File(instance.getDataFolder(), "mysql.yml");
        configFile = new File(instance.getDataFolder(), "config.yml");

        if (!mysqlFile.exists()) {
            instance.saveResource("mysql.yml", false);
        }
        if (!configFile.exists()) {
            instance.saveResource("config.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(configFile);
        mysqlConfig = YamlConfiguration.loadConfiguration(mysqlFile);
        setDefaultMySQL();
        setDefaultConfig();
    }

    private static void setDefaultConfig() {
        FileConfiguration cfg = getConfig();

        // UI

        if (!cfg.contains("ui.clanui_title")) {
            cfg.set("ui.clanui_title", "&c&lClans");
        }
        if (!cfg.contains("ui.member_ui_title")) {
            cfg.set("ui.member_ui_title", "&b&lMembers");
        }
        if (!cfg.contains("ui.you_are_member_in_clan_name")) {
            cfg.set("ui.you_are_member_in_clan_name", "&7You are a Member in Clan '&c%clan_name%'&7!");
        }
        if (!cfg.contains("ui.you_are_in_no_clan")) {
            cfg.set("ui.you_are_in_no_clan", "&cYou are in no Clan.");
        }

        //Config

        if (!cfg.contains("message.you_must_be_a_player")) {
            cfg.set("message.you_must_be_a_player", "&cyou must be a player to perform that command!");
        }

        try {
            cfg.save(configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setDefaultMySQL() {
        FileConfiguration cfg = getMysqlConfig();

        if (!cfg.contains("username")) {
            cfg.set("username", "root");
        }
        if (!cfg.contains("password")) {
            cfg.set("password", "password");
        }

        if (!cfg.contains("database")) {
            cfg.set("database", "localhost");
        }

        if (!cfg.contains("host")) {
            cfg.set("host", "localhost");
        }

        if (!cfg.contains("port")) {
            cfg.set("port", "3306");
        }
        try {
            cfg.save(mysqlFile);
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    public static void readMySQL() {
        MySQL.username = mysqlConfig.getString("username");
        MySQL.password = mysqlConfig.getString("password");
        MySQL.database = mysqlConfig.getString("database");
        MySQL.host = mysqlConfig.getString("host");
        MySQL.port = mysqlConfig.getString("port");
    }

    public static String getUIText(UIText uiText) {
        return config.getString("ui." + uiText.name().toLowerCase()).replace("&", "§");
    }

    public static String getMessage(ConfigMessage configMessage) {
        String message = config.getString("message." + configMessage.name().toLowerCase());
        return (message != null) ? message.replace("&", "§") : "";
    }

    public static String getPluginPrefix() {
        return config.getString("plugin-prefix");
    }
}
