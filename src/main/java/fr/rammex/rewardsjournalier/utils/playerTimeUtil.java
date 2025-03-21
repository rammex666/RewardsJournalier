package fr.rammex.rewardsjournalier.utils;

import fr.rammex.rewardsjournalier.database.PlayerTimeManager;
import fr.rammex.rewardsjournalier.database.SQLiteManager;
import org.bukkit.entity.Player;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class playerTimeUtil {


    public static int getPlayerTimeInDay(Player player){
        if(!PlayerTimeManager.isPlayerInTable(player)){
            PlayerTimeManager.setPlayerTime(player);
        }
        LocalDate date = LocalDate.parse(PlayerTimeManager.getPlayerTime(player));
        LocalDate actualDate = LocalDate.now();

        long days = ChronoUnit.DAYS.between(date, actualDate);

        if(date == null){
            return 0;
        }
        return (int) days+1;
    }
}
