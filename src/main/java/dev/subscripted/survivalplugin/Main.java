package dev.subscripted.survivalplugin;

import dev.subscripted.survivalplugin.modules.games.memory.file.ConfigFile;
import dev.subscripted.survivalplugin.modules.games.memory.file.MessageFile;
import dev.subscripted.survivalplugin.modules.games.memory.game.GameManager;
import dev.subscripted.survivalplugin.registery.CommandRegister;
import dev.subscripted.survivalplugin.registery.ListenerRegistery;
import dev.subscripted.survivalplugin.utils.FileManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Getter
    private static ConfigFile configFile;
    @Getter
    private static MessageFile messageFile;
    @Getter
    private static GameManager gameManager;

    @Getter
    public static Main instance;


    @Override
    public void onEnable() {
        instance = this;
        initFiles();
        initListener();
        initCommands();
        configFile = new ConfigFile(getDataFolder());
        configFile.load();
        messageFile = new MessageFile(getDataFolder());
        messageFile.load();
        gameManager = new GameManager();
    }

    @Override
    public void onDisable() {

    }

    private static void initCommands() {
        CommandRegister.registerCommands(instance);
    }

    private static void initListener() {
        ListenerRegistery.initListener(instance);
    }

    private static void initFiles() {
        FileManager.setupFiles(instance);
    }

    public void log(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }
}
