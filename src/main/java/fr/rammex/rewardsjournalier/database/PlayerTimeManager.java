package fr.rammex.rewardsjournalier.database;

import fr.rammex.rewardsjournalier.RewardsJournalier;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;

import static fr.rammex.rewardsjournalier.database.SQLiteManager.getSQLConnection;

public class PlayerTimeManager {

    private static SQLiteManager sqLiteManager;

    public PlayerTimeManager(SQLiteManager sqLiteManager) {
        this.sqLiteManager = sqLiteManager;
    }

    public static void setPlayerTime(Player player) {
        String playerUUID = player.getUniqueId().toString();
        LocalDate date = LocalDate.now();

        try (Connection connection = getSQLConnection()) {
            String query = "INSERT OR REPLACE INTO playerTime (player_uuid, dateOnJoin) VALUES (?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, playerUUID);
                ps.setString(2, String.valueOf(date));
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            RewardsJournalier.instance.getLogger().log(Level.SEVERE, "Unable to save inventory", ex);
        }
    }

    public static String getPlayerTime(Player player) {
        String playerUUID = player.getUniqueId().toString();
        LocalDate date = LocalDate.now();

        try (Connection connection = getSQLConnection()) {
            String query = "SELECT * FROM playerTime WHERE player_uuid = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, playerUUID);
                ps.executeQuery();
            }
        } catch (SQLException ex) {
            RewardsJournalier.instance.getLogger().log(Level.SEVERE, "Unable to save inventory", ex);
        }
        return String.valueOf(date);
    }

    public static boolean isPlayerInTable(Player player) {
        String playerUUID = player.getUniqueId().toString();

        try (Connection connection = getSQLConnection()) {
            String query = "SELECT * FROM playerTime WHERE player_uuid = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, playerUUID);
                return ps.executeQuery().next();
            }
        } catch (SQLException ex) {
            RewardsJournalier.instance.getLogger().log(Level.SEVERE, "Unable to save inventory", ex);
        }
        return false;
    }
}
