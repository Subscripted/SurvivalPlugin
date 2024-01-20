package dev.subscripted.survivalplugin.modules.games.memory.util;


import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Constants {
    public static final String TAG = "§8[§eMemory§8] §7";
    public static final ItemStack COVER = (new ItemBuilder()).name("?").type(Material.WHITE_STAINED_GLASS_PANE).getItem();
    public static final ItemStack BORDER = (new ItemBuilder()).name("").type(Material.BLACK_STAINED_GLASS_PANE).getItem();
}

