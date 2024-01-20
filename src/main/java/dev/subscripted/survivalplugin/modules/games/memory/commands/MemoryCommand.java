package dev.subscripted.survivalplugin.modules.games.memory.commands;

import dev.subscripted.survivalplugin.Main;
import dev.subscripted.survivalplugin.modules.games.memory.game.Queue;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MemoryCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
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
                    return true;
                }
                player.sendMessage(Main.getMessageFile().getMessage("queue.added", new String[0]));
            }
        } else {
            Main.getInstance().log("cannot run this command from console.");
        }
        return true;
    }
}