package dev.subscripted.survivalplugin.modules.clans.commands;

import dev.subscripted.survivalplugin.modules.clans.manager.ClanInviteManager;
import dev.subscripted.survivalplugin.modules.clans.manager.ClanManager;
import dev.subscripted.survivalplugin.modules.enums.ConfigMessage;
import dev.subscripted.survivalplugin.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ClanInvite implements CommandExecutor, TabCompleter {

    private final ClanManager clanManager;
    private final ClanInviteManager inviteManager;
    private final String YOU_MUST_BE_A_PLAYER = FileManager.getMessage(ConfigMessage.YOU_MUST_BE_A_PLAYER);
    private final String USAGE_CLAN_INVITE_PLAYER = FileManager.getMessage(ConfigMessage.USAGE_CLAN_INVITE_PLAYER);
    private final String USAGE_FOR_INVITE = FileManager.getMessage(ConfigMessage.USAGE_FOR_INVITE);


    public ClanInvite(ClanManager clanManager, ClanInviteManager inviteManager) {
        this.clanManager = clanManager;
        this.inviteManager = inviteManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(YOU_MUST_BE_A_PLAYER);
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            //player.sendMessage("Verwendung: /clan invite <Spieler> oder /clan accept <Clantag>");
            player.sendMessage(USAGE_CLAN_INVITE_PLAYER);
            return true;
        }

        if (args[0].equalsIgnoreCase("invite")) {
            if (args.length < 2) {
                player.sendMessage("Verwendung: /clan invite <Spieler>");
                return true;
            }

            Player invitedPlayer = Bukkit.getPlayer(args[1]);

            if (invitedPlayer != null && invitedPlayer.isOnline()) {
                inviteManager.invitePlayer(player, invitedPlayer, clanManager.getClan(player.getName()).getClanTag());
            } else {
                player.sendMessage("Spieler nicht gefunden oder offline.");
            }
        } else if (args[0].equalsIgnoreCase("accept")) {
            if (args.length < 2) {
                player.sendMessage("Verwendung: /clan accept <Clantag>");
                return true;
            }

            inviteManager.acceptInvite(player);
        } else {
            player.sendMessage("Unbekannter Befehl. Verwendung: /clan invite <Spieler> oder /clan accept <Clantag>");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 1) {
            list.add("accept");
        }


        return null;
    }
}
