package dev.subscripted.survivalplugin.modules.disconnect;

import dev.subscripted.survivalplugin.modules.discord.DiscordWebhook;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.awt.*;
import java.io.IOException;

public class LeaveMessage implements Listener {

    DiscordWebhook discordWebhook = DiscordWebhook.getDiscordWebhook();


    @EventHandler
    public void onLeave(PlayerQuitEvent event) throws IOException {
        String PLAYERNAME = event.getPlayer().getName();
        String QUIT_MESSAGE = "§8[§c-§8] §7" + PLAYERNAME;
        event.setQuitMessage(QUIT_MESSAGE);

        discordWebhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setDescription(PLAYERNAME + " hat den Server verlassen!")
                .setColor(Color.RED));
        discordWebhook.execute();
    }
}
