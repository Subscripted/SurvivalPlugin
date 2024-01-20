package dev.subscripted.survivalplugin.modules.troll.trollmenu.commands;

import dev.subscripted.survivalplugin.modules.troll.trollmenu.gui.Menus;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerSelectorCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("troll.*") || !player.isOp()) {
            player.sendMessage("No Permission");
            return true;
        }
        Menus.openPlayerGui(player);
        player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1f, 1f);


        return false;
    }
}
