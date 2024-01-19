package dev.subscripted.survivalplugin.modules.troll.trollmenu.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;
import java.util.UUID;

public class Menus {

    public static void openPlayerGui(Player player) {


        Inventory menu = Bukkit.createInventory(player, 53, "%placeholder%");
        List<UUID> onlinePlayers = Bukkit.getOnlinePlayers().stream()
                .map(Player::getUniqueId)
                .toList();

        for (UUID selectedUUID : onlinePlayers) {
            ItemStack playerHead = createPlayerHead(selectedUUID);
            menu.addItem(playerHead);
        }

        player.openInventory(menu);
    }

    private static ItemStack createPlayerHead(UUID playerUUID) {
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) playerHead.getItemMeta();
        Player onlinePlayer = Bukkit.getPlayer(playerUUID);

        if (onlinePlayer != null) {
            meta.setOwningPlayer(onlinePlayer);
            meta.setDisplayName(onlinePlayer.getName());
        } else {

            meta.setOwner(Bukkit.getOfflinePlayer(playerUUID).getName());
            meta.setDisplayName("Offline Player");
        }
        playerHead.setItemMeta(meta);
        return playerHead;
    }
}
