package db.tables;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public boolean insert(final String typeInteraction ,final String nameNPC, final Optional<Blob> imageNPC, final String descPersonality, final String nameCampaign) {
        try (final PreparedStatement s = this.c.prepareStatement("INSERT INTO npcs VALUES (?, ?, ?, ?, ?)")) {
            s.setString(1, typeInteraction);
            s.setString(2, nameNPC);
            s.setBlob(3, imageNPC.orElse(null));
            s.setString(4, descPersonality);
            s.setString(5, nameCampaign);
            s.executeUpdate();
            return true;
        } catch (final SQLException e) {
            return false;
        }
    }

    public boolean update(final String nameNPC, final String typeInteraction) {
        try (final PreparedStatement s = this.c.prepareStatement("UPDATE npcs SET tipoInterazione = ? WHERE nomeNPC = ?")) {
            s.setString(1, typeInteraction);
            s.setString(2, nameNPC);
            return true;
        } catch (final SQLException e) {
            return false;
        }
    }
}
