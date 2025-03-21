package fr.rammex.rewardsjournalier;

import fr.rammex.rewardsjournalier.commands.RewardCommand;
import fr.rammex.rewardsjournalier.commands.RewardReloadCommand;
import fr.rammex.rewardsjournalier.database.SQLiteManager;
import fr.rammex.rewardsjournalier.events.PlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class RewardsJournalier extends JavaPlugin {
    public static RewardsJournalier instance;

    @Override
    public void onEnable() {
        instance = this;
        //CONFIG.YML
        saveDefaultConfig();

        //COMMANDS
        loadCommads();

        //EVENTS
        loadEvents();

        // DATABASE
        SQLiteManager sqLiteManager = new SQLiteManager("rewardsjournalier", new File(getDataFolder(), "data.db"));
        sqLiteManager.load();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadCommads() {
        getCommand("rd").setExecutor(new RewardCommand(this));
        getCommand("rdreload").setExecutor(new RewardReloadCommand(this));
    }

    private void loadEvents(){
        getServer().getPluginManager().registerEvents(new PlayerManager(), this);
    }
}
