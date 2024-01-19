package dev.subscripted.survivalplugin.modules.connect;

import dev.subscripted.survivalplugin.modules.nametag.NameTagManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinMessage implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        String PLAYERNAME = player.getName();
        String JOIN_MESSAGE = "§8[§a+§8] §7" + PLAYERNAME;
        event.setJoinMessage(JOIN_MESSAGE);
        NameTagManager.loadNameTag(player);
    }
}
