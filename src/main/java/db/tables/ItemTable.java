package db.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Optional;

import model.Item;

public class ItemTable {
    
    private final String TABLE_NAME = "oggetti";
    private final Connection c;

    public ItemTable(final Connection c) {
        this.c = Objects.requireNonNull(c);
    }

    public String select() {
        try (final Statement s = this.c.createStatement()) {
            final ResultSet r = s.executeQuery("SELECT * FROM " + TABLE_NAME);
            final StringBuilder sb = new StringBuilder("");
            int i = 0;
            while (r.next()) {
                final Item item = new Item(r.getString(1), Optional.ofNullable(r.getInt(2)), r.getString(3), r.getString(4));
                i++;
                sb.append(i + ". " + item.toString() + "\n");
            }
            return sb.toString();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean isPresent (final String nameItem) {
        try(final PreparedStatement s = this.c.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE nomeOggetto = ?")) {
            s.setString(1, nameItem);
            final var r = s.executeQuery();
            return r.next();
        } catch (final SQLException e) {
            return false;
        }
    }

    public boolean insertToProtagonist(final String nameItem, final String nameProtagonist, final int quantity) {
        if (this.isPresent(nameItem)) {
            try (final PreparedStatement s = this.c.prepareStatement("INSERT INTO proprietà VALUES (?, ?, ?)")) {
                s.setString(1, nameItem);
                s.setString(2, nameProtagonist);
                s.setInt(3, quantity);
                s.executeUpdate();
                return true;
            } catch (final SQLException e) {
                return false;
            }
        }
        return false;
    }
}


