package dev.subscripted.survivalplugin.modules.games.memory.theme;

import org.bukkit.inventory.ItemStack;

public interface Theme {
    int getId();

    String getName();

    ItemStack[] getPairs();
}
