package dev.subscripted.survivalplugin.modules.connect;

import dev.subscripted.survivalplugin.modules.discord.DiscordWebhook;
import dev.subscripted.survivalplugin.modules.nametag.NameTagManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.awt.*;
import java.io.IOException;
import java.util.UUID;

public class JoinMessage implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws IOException {


        DiscordWebhook discordWebhook = DiscordWebhook.getDiscordWebhook();

        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        String PLAYERNAME = player.getName();
        String JOIN_MESSAGE = "§8[§a+§8] §7" + PLAYERNAME;
        event.setJoinMessage(JOIN_MESSAGE);
        System.out.println(playerUUID);
        NameTagManager.loadNameTag(player);

        String playerHeadUrl = getPlayerHeadUrl(playerUUID);

        discordWebhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setDescription(PLAYERNAME + " ist auf den Server gejoint!")
                .setColor(Color.GREEN)
                .setFooter("", playerHeadUrl)
                .setImage(playerHeadUrl));
        discordWebhook.execute();
    }
        public static String getPlayerHeadUrl(UUID playerUUID) {
            return "https://crafatar.com/avatars/" + playerUUID + "?overlay";
        }
    }