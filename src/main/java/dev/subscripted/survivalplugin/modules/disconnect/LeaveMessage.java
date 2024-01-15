package dev.subscripted.survivalplugin.modules.disconnect;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveMessage implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        String PLAYERNAME = event.getPlayer().getName();
        String QUIT_MESSAGE = "§8[§c-§8] §7" + PLAYERNAME;
        event.setQuitMessage(QUIT_MESSAGE);
    }
}
