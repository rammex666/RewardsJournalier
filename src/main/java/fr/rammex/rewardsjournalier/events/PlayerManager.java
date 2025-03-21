package fr.rammex.rewardsjournalier.events;

import fr.rammex.rewardsjournalier.database.PlayerTimeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerManager implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(!PlayerTimeManager.isPlayerInTable(player)){
            PlayerTimeManager.setPlayerTime(player);
        }
    }
}
