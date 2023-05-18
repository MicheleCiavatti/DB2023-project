package db;

import java.sql.Connection;

import db.tables.CampaignTable;
import db.tables.NPCTable;
import db.tables.PartyTable;
import db.tables.ProtagonistTable;
import db.tables.SessionTable;

public class Controller {

    private final Connection connection;
    private final CampaignTable tCampaign;
    private final SessionTable tSessions;
    private final PartyTable tParties;
    private final ProtagonistTable tProtagonists;
    private final NPCTable tNPCs;

    public Controller() {
        this.connection = new ConnectionProvider("root", "Michele", "db2023_dnd").getMySQLConnection();
        this.tCampaign = new CampaignTable(connection);
        this.tSessions = new SessionTable(connection);
        this.tParties = new PartyTable(connection);
        this.tProtagonists = new ProtagonistTable(connection);
        this.tNPCs = new NPCTable(connection);
    }
    
    public String selectAll(final String tableName) {
        final String result = switch(tableName) {
            case "Campagne" -> this.tCampaign.select();
            case "Sessioni" -> this.tSessions.select();
            case "Parties" -> this.tParties.select();
            case "Protagonisti" -> this.tProtagonists.select();
            case "NPCs" -> this.tNPCs.select();
            default -> throw new IllegalStateException();
        };
        return result;
    }

    public void op1(final int numPlayers, final String nameCampaign) {
        this.tCampaign.insert(numPlayers, nameCampaign);
    }

    public void op2(final String nameCampaign, final int progressiveCode, final String codParty) {
        this.tSessions.insert(nameCampaign, progressiveCode, codParty);
    }

    public void op3(final String codParty) {
        this.tParties.insert(codParty);
    }
}
