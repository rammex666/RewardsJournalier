package fr.rammex.rewardsjournalier.utils;

import fr.rammex.rewardsjournalier.RewardsJournalier;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class rewardManager {
    private static Map<Player, Integer> playerFreeReward = new HashMap<>();
    private static Map<Player, Integer> playerPremiumReward = new HashMap<>();

    public static void getFreeReward(Player player, int day){
        List<String> commands = RewardsJournalier.instance.getConfig().getStringList("rewards.days." + day);
        if(commands != null && !isPlayerInFreeRewardForThisDay(player, day)){
            if(isPlayerInFreeReward(player)){
                playerFreeReward.replace(player, day);
            } else {
                playerFreeReward.put(player, day);
            }
            for(String command : commands){
                RewardsJournalier.instance.getServer().dispatchCommand(RewardsJournalier.instance.getServer().getConsoleSender(), command.replace("%player%", player.getName()));
            }
        } else {
            player.sendMessage(RewardsJournalier.instance.getConfig().getString("messages.already-claimed"));
        }
    }

    public static void getPremiumReward(Player player, int day){
        List<String> commands = RewardsJournalier.instance.getConfig().getStringList("rewardspremium.days." + day);
        if(commands != null && !isPlayerInPremiumRewardForThisDay(player, day)){
            if(isPlayerInPremiumReward(player)){
                playerPremiumReward.replace(player, day);
            } else {
                playerPremiumReward.put(player, day);
            }
            for(String command : commands){
                RewardsJournalier.instance.getServer().dispatchCommand(RewardsJournalier.instance.getServer().getConsoleSender(), command.replace("%player%", player.getName()));
            }
        } else {
            player.sendMessage(RewardsJournalier.instance.getConfig().getString("messages.already-claimed"));
        }
    }

    private static boolean isPlayerInFreeRewardForThisDay(Player player, int day){
        return playerFreeReward.containsKey(player) && playerFreeReward.get(player) == day;
    }

    private static boolean isPlayerInPremiumRewardForThisDay(Player player, int day){
        return playerPremiumReward.containsKey(player) && playerPremiumReward.get(player) == day;
    }

    private static boolean isPlayerInFreeReward(Player player){
        return playerFreeReward.containsKey(player);
    }

    private static boolean isPlayerInPremiumReward(Player player){
        return playerPremiumReward.containsKey(player);
    }
}
