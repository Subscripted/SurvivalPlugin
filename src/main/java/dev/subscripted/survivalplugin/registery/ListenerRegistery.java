package dev.subscripted.survivalplugin.registery;

import dev.subscripted.survivalplugin.Main;
import dev.subscripted.survivalplugin.modules.clans.manager.ItemInteraction;
import dev.subscripted.survivalplugin.modules.connect.JoinMessage;
import dev.subscripted.survivalplugin.modules.disconnect.LeaveMessage;
import dev.subscripted.survivalplugin.modules.games.memory.listener.*;
import dev.subscripted.survivalplugin.modules.syntax.UnknownCommand;
import org.bukkit.Bukkit;

public class ListenerRegistery {

    public static void initListener(Main instance){
        Bukkit.getPluginManager().registerEvents(new JoinMessage(), instance);
        Bukkit.getPluginManager().registerEvents(new LeaveMessage(), instance);
        Bukkit.getPluginManager().registerEvents(new UnknownCommand(), instance);
        Bukkit.getPluginManager().registerEvents(new ItemInteraction(), instance);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), instance);
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(), instance);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), instance);
        Bukkit.getPluginManager().registerEvents(new GameClickListener(), instance);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), instance);
    }
}
