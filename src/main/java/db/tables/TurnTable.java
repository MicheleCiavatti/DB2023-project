package db.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import model.Turn;

public class TurnTable {
    
    private final String TABLE_NAME = "turni";
    private final Connection c;

    public TurnTable(final Connection c) {
        this.c = Objects.requireNonNull(c);
    }

    public String select() {
        try (final Statement s = this.c.createStatement()) {
            final ResultSet r = s.executeQuery("SELECT * FROM " + TABLE_NAME);
            final StringBuilder sb = new StringBuilder("");
            int i = 0;
            while (r.next()) {
                final Turn turn = new Turn(r.getInt(1), r.getString(2), r.getInt(3));
                i++;
                sb.append(i + ". " + turn.toString() + "\n");
            }
            return sb.toString();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public boolean insert(final int numTurn, final String nameCampaign, final int progressiveCode) {
        try (final PreparedStatement s = this.c.prepareStatement("INSERT INTO turni VALUES (?, ?, ?)")) {
            s.setInt(1, numTurn);
            s.setString(2, nameCampaign);
            s.setInt(3, progressiveCode);
            s.executeUpdate();
            return true;
        } catch (final SQLException e) {
            return false;
        }
    }
}
