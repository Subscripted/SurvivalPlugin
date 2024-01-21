package dev.subscripted.survivalplugin.modules.chat;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormat implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){

        final Player player = event.getPlayer();
        final String message = event.getMessage().replace("%", "%%");



        if (player.hasPermission("server.emerald")){
            event.setFormat("" + player.getName() + "" + message);
            return;
        }
        if (player.hasPermission("server.dia")){
            event.setFormat("" + player.getName() + "" + message);
            return;
        }
        if (player.hasPermission("server.gold")){
            event.setFormat("" + player.getName() + "" + message);
            return;
        }
        if (player.hasPermission("server.silver")){
            event.setFormat("" + player.getName() + "" + message);
            return;
        }


        Bukkit.getOnlinePlayers().stream().filter(players -> message.contains(players.getName().replace(players.getName(), "§b@§b" + players.getName() +"§r"))).forEach(players -> {
            players.playSound(players.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
        });

    }
}
