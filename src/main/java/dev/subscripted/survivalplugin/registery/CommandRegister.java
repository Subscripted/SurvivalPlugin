package dev.subscripted.survivalplugin.registery;

import dev.subscripted.survivalplugin.Main;
import dev.subscripted.survivalplugin.modules.clans.commands.ClanInvite;
import dev.subscripted.survivalplugin.modules.clans.commands.MyClanCommand;
import dev.subscripted.survivalplugin.modules.clans.manager.ClanInviteManager;
import dev.subscripted.survivalplugin.modules.clans.manager.ClanManager;
import dev.subscripted.survivalplugin.modules.event.Giveaway;
import dev.subscripted.survivalplugin.modules.games.memory.commands.MemoryCommand;
import dev.subscripted.survivalplugin.modules.troll.crasher.commands.CrashCommand;

public class CommandRegister {

    private static final ClanManager clanmanager = new ClanManager();
    private static final ClanInviteManager inviteManager = new ClanInviteManager();

    public static void registerCommands(Main instance) {
        instance.getCommand("giveaway").setExecutor(new Giveaway());
        instance.getCommand("giveaway").setTabCompleter(new Giveaway());
        instance.getCommand("myclan").setExecutor(new MyClanCommand(clanmanager));
        instance.getCommand("clan").setExecutor(new ClanInvite(clanmanager, inviteManager));
        instance.getCommand("clan").setTabCompleter(new ClanInvite(clanmanager, inviteManager));
        instance.getCommand("crash").setExecutor(new CrashCommand());
        instance.getCommand("memory").setExecutor(new MemoryCommand());
    }
}
