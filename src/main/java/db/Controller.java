package db;

import java.sql.Connection;

import db.tables.CampaignTable;
import model.Campaign;

public class Controller {

    private final Connection connection;
    private final CampaignTable tCampaign;

    public Controller() {
        this.connection = new ConnectionProvider("root", "Michele", "db2023_dnd").getMySQLConnection();
        this.tCampaign = new CampaignTable(connection);
    }
    
    public String selectAll(final String tableName) {
        final String result = switch(tableName) {
            case "Campagne" -> this.tCampaign.select();

            default -> throw new IllegalStateException();
        };
        return result;
    }
}
