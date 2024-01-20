package dev.subscripted.survivalplugin.modules.games.memory.listener;

import dev.subscripted.survivalplugin.Main;
import dev.subscripted.survivalplugin.modules.games.memory.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {
    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getPlayer() instanceof Player) {
            Player player = (Player)e.getPlayer();
            Game game = Main.getGameManager().getGame(player);
            if (game != null && !game.isUpdate())
                game.win(game.getOpponent(player));
        }
    }
}