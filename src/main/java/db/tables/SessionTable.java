package db.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import model.Session;

public class SessionTable {
    
    private final String TABLE_NAME = "sessioni";
    private final Connection c;

    public SessionTable(final Connection c) {
        this.c = Objects.requireNonNull(c);
    }

    public String select() {
        System.out.println("Hello");
        try (final Statement s = this.c.createStatement()) {
            final ResultSet r = s.executeQuery("SELECT * FROM " + TABLE_NAME);
            final StringBuilder sb = new StringBuilder("");
            int i = 0;
            while (r.next()) {
                final Session session = new Session(r.getString(1), r.getInt(2), r.getString(3));
                i++;
                sb.append(i + ". " + session.toString() + "\n");
            }
            return sb.toString();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void insert(final int numPlayers, final String nameCampaign) {
        try (final PreparedStatement s = this.c.prepareStatement("INSERT INTO " + TABLE_NAME + " VALUES (" + numPlayers + ", ?)")) {
            s.setString(1, nameCampaign);
            s.executeUpdate();
        } catch (SQLException e ) {
            throw new IllegalStateException(e);
        }
    }
}
