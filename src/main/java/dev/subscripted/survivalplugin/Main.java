package dev.subscripted.survivalplugin;

import dev.subscripted.survivalplugin.registery.CommandRegister;
import dev.subscripted.survivalplugin.registery.ListenerRegistery;
import dev.subscripted.survivalplugin.utils.FileManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Getter
    public static Main instance;

    @Getter
    public static String plugin_prefix = FileManager.getPluginPrefix();

    @Override
    public void onEnable() {
        instance = this;
        initListener();
        initCommands();
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
}
