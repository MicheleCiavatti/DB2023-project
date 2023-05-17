package db.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import model.Campaign;

public class CampaignTable {
    
    private final String TABLE_NAME = "campagne";
    private final Connection c;

    public CampaignTable(final Connection c) {
        this.c = Objects.requireNonNull(c);
    }

    public String select() throws SQLException {
        final Statement s = this.c.createStatement();
        final ResultSet r = s.executeQuery("SELECT * FROM " + TABLE_NAME);
        final StringBuilder sb = new StringBuilder("");
        while (r.next()) {
            final Campaign campaign = new Campaign(r.getInt(1), r.getString(2));
            sb.append(campaign.toString());
        }
        return sb.toString();
    }
}
