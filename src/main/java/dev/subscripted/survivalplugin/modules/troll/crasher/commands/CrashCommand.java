package dev.subscripted.survivalplugin.modules.troll.crasher.commands;

import dev.subscripted.survivalplugin.Main;
import dev.subscripted.survivalplugin.modules.troll.crasher.crasher.CrashType;
import dev.subscripted.survivalplugin.modules.troll.crasher.crasher.CrashUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CrashCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("core.crash")) {
            sender.sendMessage(Main.getPlugin_prefix() + "§cInsufficient permissions!");
            return false;
        }

        if (args.length == 2) {
            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                sender.sendMessage(Main.getPlugin_prefix() + "§cPlayer you specified is offline!");
                return false;
            }

            String method = args[1];

            // Handle crashing with all methods
            if (method.equalsIgnoreCase("all")) {
                for (CrashType crashType : CrashType.values()) {
                    CrashUtils.crashPlayer(sender, target, crashType);

                }
                return true;
            }

            // Handle crashing with specific method
            CrashType type = CrashType.getFromString(method.toUpperCase());

            if (type != null) {
                CrashUtils.crashPlayer(sender, target, type);
                return true;
            } else {
                sender.sendMessage(Main.getPlugin_prefix() + "§cMethod " + method + " doesn't exist!");
                return false;
            }


        } else {
            sender.sendMessage(Main.getPlugin_prefix() + "§cUsage: §b/crash <player> <explosion/position/all>!");
        }

        return true;
    }
}