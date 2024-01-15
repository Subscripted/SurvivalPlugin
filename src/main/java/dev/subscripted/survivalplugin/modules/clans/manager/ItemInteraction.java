package dev.subscripted.survivalplugin.modules.clans.manager;

import dev.subscripted.survivalplugin.modules.enums.UIText;
import dev.subscripted.survivalplugin.utils.FileManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ItemInteraction implements Listener {

    @EventHandler
    public void onInteract(InventoryClickEvent event) {
        if (!event.getView().getTitle().equalsIgnoreCase(FileManager.getUIText(UIText.CLANUI_TITLE))) {
            event.setCancelled(false);
        } else {
            event.setCancelled(true);
        }
    }

}
