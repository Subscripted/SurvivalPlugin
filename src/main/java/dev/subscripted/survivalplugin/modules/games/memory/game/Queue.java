package dev.subscripted.survivalplugin.modules.games.memory.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Queue {
    private List<UUID> uuid = new ArrayList<>();

    public void add(Player p) {
        this.uuid.add(p.getUniqueId());
    }

    public void remove(Player p) {
        this.uuid.remove(p.getUniqueId());
    }

    public boolean contains(Player p) {
        return this.uuid.contains(p.getUniqueId());
    }

    public void clear() {
        this.uuid.clear();
    }

    public Player getPlayer(int index) {
        return Bukkit.getPlayer(this.uuid.get(index));
    }

    public int getSize() {
        return this.uuid.size();
    }
}