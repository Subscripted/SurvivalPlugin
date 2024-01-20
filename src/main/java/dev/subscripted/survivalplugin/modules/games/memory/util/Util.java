package dev.subscripted.survivalplugin.modules.games.memory.util;

import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class Util {
    public static final Random random = new Random();

    public static boolean isSame(ItemStack item, ItemStack copy) {
        return (item != null && item.isSimilar(copy));
    }
}