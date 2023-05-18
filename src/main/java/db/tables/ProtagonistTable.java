package db.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Optional;

import model.Protagonist;

public class ProtagonistTable {

    private final String TABLE_NAME = "protagonisti";
    private final Connection c;

    public ProtagonistTable(final Connection c) {
        this.c = Objects.requireNonNull(c);
    }

    public String select() {
        try (final Statement s = this.c.createStatement()) {
            final ResultSet r = s.executeQuery("SELECT * FROM " + TABLE_NAME);
            final StringBuilder sb = new StringBuilder("");
            int i = 0;
            while (r.next()) {
                final Protagonist protagonist = new Protagonist(Optional.ofNullable(r.getBlob(1)), 
                    r.getString(2), r.getInt(3), r.getInt(4), r.getString(5), r.getInt(6), r.getString(7));
                i++;
                sb.append(i + ". " + protagonist.toString() + "\n");
            }
            return sb.toString();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
