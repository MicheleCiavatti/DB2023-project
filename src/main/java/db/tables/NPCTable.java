package db.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Optional;

import model.NPC;

public class NPCTable {
    
    private final String TABLE_NAME = "npcs";
    private final Connection c;

    public NPCTable(final Connection c) {
        this.c = Objects.requireNonNull(c);
    }

    public String select() {
        try (final Statement s = this.c.createStatement()) {
            final ResultSet r = s.executeQuery("SELECT * FROM " + TABLE_NAME);
            final StringBuilder sb = new StringBuilder("");
            int i = 0;
            while (r.next()) {
                final NPC npc = new NPC(r.getString(1), r.getString(2), Optional.ofNullable(r.getBlob(3)), r.getString(4), r.getString(5));
                i++;
                sb.append(i + ". " + npc.toString() + "\n");
            }
            return sb.toString();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
