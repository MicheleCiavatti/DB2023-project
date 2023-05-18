package db.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import model.Race;

public class RaceTable {
    
    private final String TABLE_NAME = "razze";
    private final Connection c;

    public RaceTable(final Connection c) {
        this.c = Objects.requireNonNull(c);
    }

    public String select() {
        try (final Statement s = this.c.createStatement()) {
            final ResultSet r = s.executeQuery("SELECT * FROM " + TABLE_NAME);
            final StringBuilder sb = new StringBuilder("");
            int i = 0;
            while (r.next()) {
                final Race monster = new Race(r.getString(1), r.getString(2));
                i++;
                sb.append(i + ". " + monster.toString() + "\n");
            }
            return sb.toString();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
