package fr.rammex.rewardsjournalier.commands;

import fr.rammex.rewardsjournalier.RewardsJournalier;
import fr.rammex.rewardsjournalier.utils.playerTimeUtil;
import fr.rammex.rewardsjournalier.utils.rewardManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RewardCommand implements CommandExecutor {
    RewardsJournalier plugin;
    public RewardCommand(RewardsJournalier plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§8[§a❌§8] Cette commande ne peut être utilisée que par un joueur.");
            return true;
        }
        Player player = (Player) sender;

        if(args.length < 3) {
            player.sendMessage("§8[§a❌§8] §7Utilisation: /rd <get> <free|premium> <player>");
            return true;
        }

        String action = args[0];
        String type = args[1];
        String playerName = args[2];
        String premiumRewardPermission = RewardsJournalier.instance.getConfig().getString("premiumperm");

        Player target = plugin.getServer().getPlayer(playerName);
        if(target == null) {
            player.sendMessage("§8[§a❌§8] §7Le joueur n'est pas connecté.");
            return true;
        }

        int playerTime = playerTimeUtil.getPlayerTimeInDay(target);

        if(action.equalsIgnoreCase("get")) {
            if(type.equalsIgnoreCase("free")) {
                rewardManager.getFreeReward(target, playerTime);
                target.sendMessage(RewardsJournalier.instance.getConfig().getString("messages.claim"));
            } else if(type.equalsIgnoreCase("premium")) {
                if(target.hasPermission(premiumRewardPermission)) {
                    rewardManager.getPremiumReward(target, playerTime);
                    target.sendMessage(RewardsJournalier.instance.getConfig().getString("messages.claim-premium"));
                } else {
                    player.sendMessage("§8[§a❌§8] §7Le joueur n'a pas la permission de recevoir cette récompense.");
                }
            } else {
                player.sendMessage("§8[§a❌§8] §7Utilisation: /rd <get> <free|premium> <player>");
            }
        } else {
            player.sendMessage("§8[§a❌§8] §7Utilisation: /rd <get> <free|premium> <player>");
        }

        return true;
    }
}
