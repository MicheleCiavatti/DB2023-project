package db;

import java.sql.Connection;

import db.tables.CampaignTable;
import db.tables.SessionTable;

public class Controller {

    private final Connection connection;
    private final CampaignTable tCampaign;
    private final SessionTable tSessions;

    public Controller() {
        this.connection = new ConnectionProvider("root", "Michele", "db2023_dnd").getMySQLConnection();
        this.tCampaign = new CampaignTable(connection);
        this.tSessions = new SessionTable(connection);
    }
    
    public String selectAll(final String tableName) {
        final String result = switch(tableName) {
            case "Campagne" -> this.tCampaign.select();
            case "Sessioni" -> this.tSessions.select();
            default -> throw new IllegalStateException();
        };
        return result;
    }

    public void op1(final int numPlayers, final String nameCampaign) {
        this.tCampaign.insert(numPlayers, nameCampaign);
    }
}
