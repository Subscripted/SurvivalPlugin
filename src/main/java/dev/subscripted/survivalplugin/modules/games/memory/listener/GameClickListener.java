package dev.subscripted.survivalplugin.modules.games.memory.listener;

import dev.subscripted.survivalplugin.Main;
import dev.subscripted.survivalplugin.modules.games.memory.event.GameClickEvent;
import dev.subscripted.survivalplugin.modules.games.memory.game.Card;
import dev.subscripted.survivalplugin.modules.games.memory.game.Game;
import dev.subscripted.survivalplugin.modules.games.memory.profile.Profile;
import dev.subscripted.survivalplugin.modules.games.memory.util.Constants;
import dev.subscripted.survivalplugin.modules.games.memory.util.ItemBuilder;
import dev.subscripted.survivalplugin.modules.games.memory.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class GameClickListener implements Listener {
    @EventHandler
    public void onClick(GameClickEvent e) {
        Player player = e.getPlayer();
        Game game = e.getGame();
        Profile profile = game.getProfile(player);
        ItemStack item = e.getClickedItem();
        int slot = e.getSlot();
        if (item == null || item.getType() == null || item.getType() == Material.AIR)
            return;
        if (!game.getTurn().getUniqueId().equals(player.getUniqueId())) {
            player.sendMessage(Main.getMessageFile().getMessage("game.not_your_turn", new String[0]));
            return;
        }
        if (Util.isSame(item, Constants.BORDER) || !game.isClickAble())
            return;
        Card card = game.getCardBySlot(slot);
        if (profile.hasCard() &&
                profile.getLastCard().getSlot() == slot)
            return;
        game.setClickAble(false);
        game.changeItem(slot, card.getItem());
        if (profile.hasCard()) {
            if (profile.getLastCard().equals(card)) {
                game.sendMessage(Main.getMessageFile().getMessage("game.pair_found", new String[] { player.getName() }));
                profile.addScore();
                if (game.checkWin())
                    return;
            } else {
                game.sendMessage(Main.getMessageFile().getMessage("game.no_pair_found", new String[] { player.getName() }));
                game.setTurn(player);
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
                if (profile.getLastCard().equals(card)) {
                    game.changeItem(profile.getLastCard().getSlot(), (new ItemBuilder()).type(Material.AIR).getItem());
                    game.changeItem(slot, (new ItemBuilder()).type(Material.AIR).getItem());
                    game.updateTitle();
                    game.sendMessage(Main.getMessageFile().getMessage("game.again", new String[] { player.getName() }));
                } else {
                    game.changeItem(slot, Constants.COVER);
                    game.changeItem(profile.getLastCard().getSlot(), Constants.COVER);
                    game.setTurn(player);
                    game.sendMessage(Main.getMessageFile().getMessage("game.turn", new String[] { game.getTurn().getName() }));
                }
                profile.setLastCard(null);
                game.setClickAble(true);
            },20L);
        } else {
            profile.setLastCard(card);
            player.sendMessage(Main.getMessageFile().getMessage("game.again_player", new String[0]));
            game.setClickAble(true);
        }
    }
}