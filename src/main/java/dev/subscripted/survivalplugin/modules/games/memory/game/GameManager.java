package dev.subscripted.survivalplugin.modules.games.memory.game;

import dev.subscripted.survivalplugin.Main;
import dev.subscripted.survivalplugin.modules.games.memory.theme.ClassicTheme;
import dev.subscripted.survivalplugin.modules.games.memory.theme.FoodTheme;
import dev.subscripted.survivalplugin.modules.games.memory.theme.Theme;
import dev.subscripted.survivalplugin.modules.games.memory.util.Util;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private List<Game> games;

    private List<Theme> themes;

    private Queue queue;

    private boolean joinPerBlock;

    private Material material;

    public GameManager() {
        this.games = new ArrayList<>();
        this.themes = new ArrayList<>();
        this.queue = new Queue();
        this.joinPerBlock = Main.getConfigFile().canJoinPerBlock();
        this.material = Material.valueOf(Main.getConfigFile().getMaterialType());
        this.themes.add(new ClassicTheme());
        this.themes.add(new FoodTheme());
    }

    public Theme getRandomTheme() {
        return this.themes.get(Util.random.nextInt(this.themes.size()));
    }

    public Game getGame(Player p) {
        for (Game all : this.games) {
            for (Player gPlayer : all.getPlayers()) {
                if (gPlayer.getName().equals(p.getName()))
                    return all;
            }
        }
        return null;
    }

    public void createGame(Player first, Player second) {
        Game game = new Game(first, second);
        this.games.add(game);
    }

    public void deleteGame(Game game) {
        this.games.remove(game);
    }

    public Queue getQueue() {
        return this.queue;
    }

    public boolean canJoinPerBlock() {
        return this.joinPerBlock;
    }

    public Material getMaterial() {
        return this.material;
    }
}

