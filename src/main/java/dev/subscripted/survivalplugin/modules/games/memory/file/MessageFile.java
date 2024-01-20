package dev.subscripted.survivalplugin.modules.games.memory.file;

import dev.subscripted.survivalplugin.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessageFile {
    private File directory;

    private File configFile;

    private YamlConfiguration configuration;

    private boolean first;

    public MessageFile(File directory) {
        this.directory = directory;
        this.first = false;
    }

    public void load() {
        try {
            if (!this.directory.exists())
                this.directory.mkdir();
            this.configFile = new File(this.directory.getPath(), "messages.yml");
            if (!this.configFile.exists()) {
                this.configFile.createNewFile();
                this.first = true;
                Main.getInstance().log("was created. and restart your server.");
            }
            this.configuration = YamlConfiguration.loadConfiguration(this.configFile);
            if (this.first)
                createDefaultValues();
            checkUpdates();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createDefaultValues() {
        this.configuration.set("tag", "&8[&eMemory&8]");
        this.configuration.set("game.against", "%tag% &7You are playing against &e%player%&7.");
        this.configuration.set("game.first_turn", "%tag% &e%player% &7starts. (Theme: %theme%))");
        this.configuration.set("game.turn", "%tag% &6%player% goes on.");
        this.configuration.set("game.not_your_turn", "%tag% &cIt's not your turn.");
        this.configuration.set("game.pair_found", "%tag% &e%player% found a pair..");
        this.configuration.set("game.no_pair_found", "%tag% &7%player% didn't find a pair.");
        this.configuration.set("game.again", "%tag% &6%player% can take another card.");
        this.configuration.set("game.again_player", "%tag% &6You can take another card.");
        this.configuration.set("queue.leave", "%tag% &cYou left the queue.");
        this.configuration.set("queue.added", "%tag% &aYou joined the queue. &7Waiting for player..");
        this.configuration.set("result.draw", "%tag% &eDraw! - Nobody won.");
        this.configuration.set("result.win", "%tag% &e%player% has won the game. (%score% pairs)");
        try {
            this.configuration.save(this.configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkUpdates() {
        if (this.configuration.getString("queue.leave") == null) {
            this.configuration.set("queue.leave", "%tag% &cYou left the queue.");
            this.configuration.set("queue.already_in", null);
            try {
                this.configuration.save(this.configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getMessage(String path, String... replace) {
        String message = this.configuration.getString(path);
        message = message.replaceAll("%tag%", this.configuration.getString("tag"));
        if (replace.length != 0) {
            if (message.contains("%player%"))
                message = message.replaceAll("%player%", replace[0]);
            if (message.contains("%theme%"))
                message = message.replaceAll("%theme%", replace[1]);
            if (message.contains("%score%"))
                message = message.replaceAll("%score%", replace[1]);
        }
        message = ChatColor.translateAlternateColorCodes('&', message);
        return message;
    }
}
