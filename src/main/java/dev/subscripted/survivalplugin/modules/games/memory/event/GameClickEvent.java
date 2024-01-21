package dev.subscripted.survivalplugin.modules.games.memory.event;

import dev.subscripted.survivalplugin.modules.games.memory.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class GameClickEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Player p;

    private final Game game;

    private final ItemStack item;

    private final int slot;

    private final Type type;

    public GameClickEvent(Player p, Game game, ItemStack item, int slot, Type type) {
        this.p = p;
        this.game = game;
        this.item = item;
        this.slot = slot;
        this.type = type;
    }

    public Player getPlayer() {
        return this.p;
    }

    public Game getGame() {
        return this.game;
    }

    public ItemStack getClickedItem() {
        return this.item;
    }

    public int getSlot() {
        return this.slot;
    }

    public Type getType() {
        return this.type;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public enum Type {
        PLAYER, GAME;
    }
}
