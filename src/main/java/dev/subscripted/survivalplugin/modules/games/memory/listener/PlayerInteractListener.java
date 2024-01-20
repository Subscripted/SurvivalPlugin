package dev.subscripted.survivalplugin.modules.games.memory.listener;


import dev.subscripted.survivalplugin.Main;
import dev.subscripted.survivalplugin.modules.games.memory.game.Queue;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Block block = e.getClickedBlock();
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK || block == null)
            return;
        if (!Main.getGameManager().canJoinPerBlock())
            return;
        if (block.getType() == Main.getGameManager().getMaterial()) {
            e.setCancelled(true);
            Queue queue = Main.getGameManager().getQueue();
            if (queue.contains(player)) {
                queue.remove(player);
                player.sendMessage(Main.getMessageFile().getMessage("queue.leave", new String[0]));
            } else {
                queue.add(player);
                if (queue.getSize() == 2) {
                    Player first = queue.getPlayer(0);
                    Main.getGameManager().createGame(first, player);
                    queue.clear();
                    return;
                }
                player.sendMessage(Main.getMessageFile().getMessage("queue.added", new String[0]));
            }
        }
    }
}
