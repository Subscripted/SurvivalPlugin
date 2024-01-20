package dev.subscripted.survivalplugin.modules.games.memory.profile;


import dev.subscripted.survivalplugin.modules.games.memory.game.Card;
import org.bukkit.entity.Player;

public class Profile {
    private Player player;

    private Card lastCard;

    private int score;

    public Profile(Player player) {
        this.player = player;
    }

    public void setLastCard(Card card) {
        this.lastCard = card;
    }

    public boolean hasCard() {
        return (this.lastCard != null);
    }

    public void addScore() {
        this.score++;
    }

    public Card getLastCard() {
        return this.lastCard;
    }

    public int getScore() {
        return this.score;
    }

    public Player getPlayer() {
        return this.player;
    }
}
