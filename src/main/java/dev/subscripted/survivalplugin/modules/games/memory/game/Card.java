package dev.subscripted.survivalplugin.modules.games.memory.game;

import dev.subscripted.survivalplugin.modules.games.memory.util.Util;
import org.bukkit.inventory.ItemStack;

public class Card {
    private int slot;

    private ItemStack item;

    public Card(int slot, ItemStack item) {
        this.slot = slot;
        this.item = item;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return this.slot;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Card) {
            Card card = (Card)obj;
            return Util.isSame(this.item, card.getItem());
        }
        return false;
    }
}