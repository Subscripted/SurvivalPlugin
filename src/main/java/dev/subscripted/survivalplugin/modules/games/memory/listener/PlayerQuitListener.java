package dev.subscripted.survivalplugin.modules.games.memory.listener;

import dev.subscripted.survivalplugin.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (Main.getGameManager().getQueue().contains(player))
            Main.getGameManager().getQueue().remove(player);
    }
}
