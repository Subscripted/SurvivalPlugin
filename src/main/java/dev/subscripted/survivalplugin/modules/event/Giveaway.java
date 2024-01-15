package dev.subscripted.survivalplugin.modules.event;

import dev.subscripted.survivalplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Giveaway implements CommandExecutor, TabCompleter {

    private final Map<Player, ItemStack> participants = new HashMap<>();
    private boolean isGiveawayRunning = false;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("No Permission cause Console");
            return true;
        }

        Player player = (Player) sender;

        if (isGiveawayRunning) {
            player.sendMessage(ChatColor.RED + "Ein Giveaway läuft bereits.");
            return true;
        }

        ItemStack heldItem = player.getInventory().getItemInMainHand();
        if (heldItem.getType().isAir()) {
            player.sendMessage("Du musst ein Item im Inventar halten, um am Giveaway teilzunehmen.");
            return true;
        }
        Bukkit.broadcastMessage("Es wird von " + ((Player) sender).getDisplayName() + heldItem.getAmount() + " " + heldItem.getType().name() + "verlost!");
        isGiveawayRunning = true;
        participants.put(player, heldItem.clone());
        runGiveawayTask();

        return true;
    }

    private void runGiveawayTask() {
        new BukkitRunnable() {
            int seconds = 10;

            @Override
            public void run() {
                if (seconds > 0) {
                    Player randomPlayer = getRandomPlayer();
                    String titleMessage = ChatColor.GREEN + randomPlayer.getDisplayName();
                    Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle("Giveaway", titleMessage, 10, 40, 10));

                    if (randomPlayer != null) {
                        if (!participants.containsValue(randomPlayer)) {
                            randomPlayer.playSound(randomPlayer.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
                        }
                    }

                } else {
                    Player winner = selectWinner();
                    if (winner != null) {
                        Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle(ChatColor.GOLD + "Giveaway Winner", ChatColor.YELLOW + winner.getName(), 10, 40, 10));
                        winner.playSound(winner.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_0, 1.0F, 1.0F);
                        ItemStack prize = participants.get(winner);
                        if (prize != null) {
                            winner.getInventory().addItem(prize.clone());
                        }

                    } else {
                        Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle("", ChatColor.RED + "No participants. Giveaway canceled.", 10, 40, 10));
                    }

                    participants.keySet().forEach(p -> p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F));
                    isGiveawayRunning = false;

                    cancel();
                }

                seconds--;
            }
        }.runTaskTimer(Main.getInstance(), 0L, 20L);
    }

    private Player selectWinner() {
        if (participants.isEmpty()) {
            return null;
        }

        List<Player> playerList = new ArrayList<>(participants.keySet());
        Random random = new Random();
        int randomIndex = random.nextInt(playerList.size());

        return playerList.get(randomIndex);
    }

    private Player getRandomPlayer() {
        if (participants.isEmpty()) {
            return null;
        }

        List<Player> playerList = new ArrayList<>(participants.keySet());
        Random random = new Random();
        int randomIndex = random.nextInt(playerList.size());

        return playerList.get(randomIndex);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 1){
            list.add("confirm");
        }
        return list;
    }
}
