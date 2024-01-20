package dev.subscripted.survivalplugin.modules.games.memory.file;

import dev.subscripted.survivalplugin.Main;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigFile {
    private File directory;

    private File configFile;

    private YamlConfiguration configuration;

    private boolean first;

    public ConfigFile(File directory) {
        this.directory = directory;
        this.first = false;
    }

    public void load() {
        try {
            if (!this.directory.exists())
                this.directory.mkdir();
            this.configFile = new File(this.directory.getPath(), "memory.yml");
            if (!this.configFile.exists()) {
                this.configFile.createNewFile();
                this.first = true;
                Main.getInstance().log("was created. values if you want.");
            }
            this.configuration = YamlConfiguration.loadConfiguration(this.configFile);
            if (this.first)
                createDefaultValues();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createDefaultValues() {
        this.configuration.set("join_per_block", Boolean.valueOf(true));
        this.configuration.set("join_block", Material.NOTE_BLOCK.toString().toUpperCase());
        try {
            this.configuration.save(this.configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean canJoinPerBlock() {
        return this.configuration.getBoolean("join_per_block");
    }

    public String getMaterialType() {
        return this.configuration.getString("join_block");
    }
}
