package dev.subscripted.survivalplugin.modules.games.memory.game;

import dev.subscripted.survivalplugin.Main;
import dev.subscripted.survivalplugin.modules.games.memory.profile.Profile;
import dev.subscripted.survivalplugin.modules.games.memory.theme.Theme;
import dev.subscripted.survivalplugin.modules.games.memory.util.Constants;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Game {
private Player first;

private Player second;

private List<Profile> profiles;

private Player turn;

private Theme theme;

private List<Card> cards;

private Inventory inventory;

private boolean clickAble;

private boolean update;

public Game(Player first, Player second) {
    this.first = first;
    this.second = second;
    this.profiles = Arrays.asList(new Profile[] { new Profile(first), new Profile(second) });
    this.turn = getRandomTurn();
    this.theme = Main.getGameManager().getRandomTheme();
    this.cards = new ArrayList<>();
    int i;
    for (i = 0; i < (this.theme.getPairs()).length; i++)
        this.cards.add(new Card(-1, this.theme.getPairs()[i]));
    for (i = 0; i < (this.theme.getPairs()).length; i++)
        this.cards.add(new Card(-1, this.theme.getPairs()[i]));
    Collections.shuffle(this.cards);
    createInventory();
    first.sendMessage(Main.getMessageFile().getMessage("game.against", new String[] { second.getName() }));
    second.sendMessage(Main.getMessageFile().getMessage("game.against", new String[] { first.getName() }));
    sendMessage(Main.getMessageFile().getMessage("game.first_turn", new String[] { first.getName(), this.theme.getName() }));
    first.openInventory(this.inventory);
    second.openInventory(this.inventory);
    setClickAble(true);
}

public void sendMessage(String message) {
    this.first.sendMessage(message);
    this.second.sendMessage(message);
}

public void setTurn(Player p) {
    this.turn = getOpponent(p);
}

public void changeItem(int index, ItemStack item) {
    this.inventory.setItem(index, item);
}

public void updateTitle() {
    setUpdate(true);
    Inventory temp = this.inventory;
    this.inventory = Bukkit.createInventory(null, 54, "§6" + this.first.getName() + " " + getProfile(this.first).getScore() + " : " +
    getProfile(this.second).getScore() + " " + this.second.getName());
    for (int i = 0; i < temp.getSize(); i++) {
        ItemStack item = temp.getItem(i);
        if (item != null && item.getType() != Material.AIR)
            this.inventory.setItem(i, item);
    }
    for (Player p : getPlayers())
        p.openInventory(this.inventory);
    setUpdate(false);
}

public void win(Player player) {
    if (player == null) {
        sendMessage(Main.getMessageFile().getMessage("result.draw", new String[0]));
    } else {
        String score = "" + getProfile(player).getScore();
        sendMessage(Main.getMessageFile().getMessage("result.win", new String[] { player.getName(), score }));
    }
    Main.getGameManager().deleteGame(this);
    if (this.first != null)
        this.first.closeInventory();
    if (this.second != null)
        this.second.closeInventory();
}

public boolean checkWin() {
    int maxPairs = 14;
    if (getProfile(this.first).getScore() + getProfile(this.second).getScore() < maxPairs)
        return false;
    if (getProfile(this.first).getScore() > getProfile(this.second).getScore()) {
        win(this.first);
        return true;
    }
    if (getProfile(this.first).getScore() < getProfile(this.second).getScore()) {
        win(this.second);
        return true;
    }
    win(null);
    return true;
}

public Card getCardBySlot(int slot) {
    for (Card card : this.cards) {
        if (card.getSlot() == slot)
            return card;
    }
    return null;
}

public Player getOpponent(Player p) {
    return p.getName().equalsIgnoreCase(this.first.getName()) ? this.second : this.first;
}

public Player getTurn() {
    return this.turn;
}

public Inventory getInventory() {
    return this.inventory;
}

public List<Player> getPlayers() {
    List<Player> players = new ArrayList<>();
    if (this.first.isOnline())
        players.add(this.first);
    if (this.second.isOnline())
        players.add(this.second);
    return players;
}

public void setClickAble(boolean clickAble) {
    this.clickAble = clickAble;
}

public boolean isClickAble() {
    return this.clickAble;
}

public Profile getProfile(Player player) {
    for (Profile profile : this.profiles) {
        if (player.getUniqueId().equals(profile.getPlayer().getUniqueId()))
            return profile;
    }
    return null;
}

private void createInventory() {
    this.inventory = Bukkit.createInventory(null, 54, "§6" + this.first.getName() + " 0 : 0 " + this.second.getName());
    setBorder();
    for (int i = 0; i < this.inventory.getSize(); i++) {
        ItemStack item = this.inventory.getItem(i);
        if (item == null || item.getType() == null || item.getType() == Material.AIR) {
            this.inventory.setItem(i, Constants.COVER);
            for (Card card : this.cards) {
                if (card.getSlot() == -1) {
                    card.setSlot(i);
                    break;
                }
            }
        }
    }
}

private void setBorder() {
    int i;
    for (i = 0; i < 9; ) {
        this.inventory.setItem(i, Constants.BORDER);
        i++;
    }
    for (i = 45; i < 54; ) {
        this.inventory.setItem(i, Constants.BORDER);
        i++;
    }
    for (i = 0; i < 46; ) {
        this.inventory.setItem(i, Constants.BORDER);
        i += 9;
    }
    for (i = 8; i < 54; ) {
        this.inventory.setItem(i, Constants.BORDER);
        i += 9;
    }
}

private Player getRandomTurn() {
    return (new Random()).nextBoolean() ? this.first : this.second;
}

public void setUpdate(boolean update) {
    this.update = update;
}

public boolean isUpdate() {
    return this.update;
}
}