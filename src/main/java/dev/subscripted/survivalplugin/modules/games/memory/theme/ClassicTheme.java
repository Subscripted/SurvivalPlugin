package dev.subscripted.survivalplugin.modules.games.memory.theme;

import dev.subscripted.survivalplugin.modules.games.memory.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ClassicTheme implements Theme {
    public int getId() {
        return 0;
    }

    public String getName() {
        return "Classic";
    }

    public ItemStack[] getPairs() {
        return new ItemStack[] {
                (new ItemBuilder())
                        .type(Material.MAGENTA_DYE).getItem(), (new ItemBuilder())
                .type(Material.GRAY_DYE).getItem(), (new ItemBuilder())
                .type(Material.RED_DYE).getItem(), (new ItemBuilder())
                .type(Material.GUNPOWDER).getItem(), (new ItemBuilder())
                .type(Material.GLOWSTONE_DUST).getItem(), (new ItemBuilder())
                .type(Material.GREEN_DYE).getItem(), (new ItemBuilder())
                .type(Material.FLINT).getItem(), (new ItemBuilder())
                .type(Material.LIME_DYE).getItem(), (new ItemBuilder())
                .type(Material.LIGHT_BLUE_DYE).getItem(), (new ItemBuilder())
                .type(Material.ORANGE_DYE).getItem(),
                (new ItemBuilder())
                        .type(Material.PINK_DYE).getItem(), (new ItemBuilder())
                .type(Material.PURPLE_DYE).getItem(), (new ItemBuilder())
                .type(Material.LAPIS_LAZULI).getItem(), (new ItemBuilder())
                .type(Material.SUGAR).getItem() };
    }
}