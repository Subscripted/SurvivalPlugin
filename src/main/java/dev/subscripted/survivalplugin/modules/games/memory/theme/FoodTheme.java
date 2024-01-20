package dev.subscripted.survivalplugin.modules.games.memory.theme;

import dev.subscripted.survivalplugin.modules.games.memory.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class FoodTheme implements Theme {
    public int getId() {
        return 1;
    }

    public String getName() {
        return "Food";
    }

    public ItemStack[] getPairs() {
        return new ItemStack[] {
                (new ItemBuilder())
                        .type(Material.BREAD).getItem(), (new ItemBuilder())
                .type(Material.COOKED_RABBIT).getItem(), (new ItemBuilder())
                .type(Material.POTATO).getItem(), (new ItemBuilder())
                .type(Material.COOKED_BEEF).getItem(), (new ItemBuilder())
                .type(Material.CARROT).getItem(), (new ItemBuilder())
                .type(Material.CAKE).getItem(), (new ItemBuilder())
                .type(Material.TROPICAL_FISH).getItem(), (new ItemBuilder())
                .type(Material.APPLE).getItem(), (new ItemBuilder())
                .type(Material.COOKED_MUTTON).getItem(), (new ItemBuilder())
                .type(Material.BAKED_POTATO).getItem(),
                (new ItemBuilder())
                        .type(Material.COOKED_PORKCHOP).getItem(), (new ItemBuilder())
                .type(Material.COOKIE).getItem(), (new ItemBuilder())
                .type(Material.PUFFERFISH).getItem(), (new ItemBuilder())
                .type(Material.ENCHANTED_GOLDEN_APPLE).getItem() };
    }
}
