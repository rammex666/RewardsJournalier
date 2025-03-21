package fr.rammex.rewardsjournalier.commands;

import fr.rammex.rewardsjournalier.RewardsJournalier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RewardReloadCommand implements CommandExecutor {
    public RewardReloadCommand(RewardsJournalier plugin) {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(sender.hasPermission("rd.reload")) {
            RewardsJournalier.instance.reloadConfig();
            sender.sendMessage("§8[§a✔§8] §7Le plugin a été rechargé.");
        } else {
            sender.sendMessage("§8[§a❌§8] §7Vous n'avez pas la permission de faire cela.");
        }

        return true;
    }
}
