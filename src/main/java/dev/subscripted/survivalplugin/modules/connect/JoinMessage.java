package dev.subscripted.survivalplugin.modules.connect;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinMessage implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        String PLAYERNAME = event.getPlayer().getName();
        String JOIN_MESSAGE = "§8[§a+§8] §7" + PLAYERNAME;
        event.setJoinMessage(JOIN_MESSAGE);
    }
}
