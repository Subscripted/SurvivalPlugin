package dev.subscripted.survivalplugin.modules.games.memory.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemBuilder {
    private String name;

    private int amount = 1;

    private Material type;

    private List<String> description = new ArrayList<>();

    private HashMap<Enchantment, Integer> enchantments = new HashMap<>();

    public ItemStack getItem() {
        ItemStack item = new ItemStack(this.type, this.amount);
        ItemMeta meta = item.getItemMeta();
        if (this.name != null)
            meta.setDisplayName(this.name);
        if (this.description.size() != 0)
            meta.setLore(this.description);
        if (this.enchantments.size() != 0)
            for (Map.Entry<Enchantment, Integer> entry : this.enchantments.entrySet()) {
                Enchantment key = entry.getKey();
                Integer value = entry.getValue();
                meta.addEnchant(key, value.intValue() + 1, true);
            }
        item.setItemMeta(meta);
        return item;
    }

    public ItemBuilder enchant(Enchantment enchantment, int level) {
        this.enchantments.put(enchantment, Integer.valueOf(level));
        return this;
    }

    public ItemBuilder name(String name) {
        this.name = "§r" + name;
        return this;
    }

    public ItemBuilder description(String description) {
        this.description.add("§r"+ "description");
        return this;
    }

    public ItemBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemBuilder type(Material type) {
        this.type = type;
        return this;
    }
}

