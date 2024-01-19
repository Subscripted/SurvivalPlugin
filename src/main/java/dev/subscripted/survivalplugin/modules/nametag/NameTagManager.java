package dev.subscripted.survivalplugin.modules.nametag;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class NameTagManager {


    public static void loadNameTag(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("text", "text", "Test");
        objective.setDisplayName("§c§l    Test    ");
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        board.resetScores(player);
        player.setScoreboard(board);
    }
}
