package db.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import model.Party;

public class PartyTable {
    
    private final String TABLE_NAME = "parties";
    private final Connection c;

    public PartyTable(final Connection c) {
        this.c = Objects.requireNonNull(c);
    }

    public String select() {
        System.out.println("Hello");
        try (final Statement s = this.c.createStatement()) {
            final ResultSet r = s.executeQuery("SELECT * FROM " + TABLE_NAME);
            final StringBuilder sb = new StringBuilder("");
            int i = 0;
            while (r.next()) {
                final Party party = new Party(r.getString(1));
                i++;
                sb.append(i + ". " + party.toString() + "\n");
            }
            return sb.toString();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
