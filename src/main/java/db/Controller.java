package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.tables.CampaignTable;
import db.tables.ClassTable;
import db.tables.ItemTable;
import db.tables.MonsterTable;
import db.tables.NPCTable;
import db.tables.PartyTable;
import db.tables.ProtagonistTable;
import db.tables.RaceTable;
import db.tables.SessionTable;
import db.tables.SubclassTable;
import db.tables.TurnTable;

public class Controller {

    private final Connection connection;
    private final CampaignTable tCampaign;
    private final SessionTable tSessions;
    private final PartyTable tParties;
    private final ProtagonistTable tProtagonists;
    private final NPCTable tNPCs;
    private final MonsterTable tMonsters;
    private final RaceTable tRaces;
    private final ClassTable tClasses;
    private final SubclassTable tSubclasses;
    private final ItemTable tItems;
    private final TurnTable tTurns;

    public Controller() {
        this.connection = new ConnectionProvider("root", "Michele", "db2023_dnd").getMySQLConnection();
        this.tCampaign = new CampaignTable(connection);
        this.tSessions = new SessionTable(connection);
        this.tParties = new PartyTable(connection);
        this.tProtagonists = new ProtagonistTable(connection);
        this.tNPCs = new NPCTable(connection);
        this.tMonsters = new MonsterTable(connection);
        this.tRaces = new RaceTable(connection);
        this.tClasses = new ClassTable(connection);
        this.tSubclasses = new SubclassTable(connection);
        this.tItems = new ItemTable(connection);
        this.tTurns = new TurnTable(connection);
    }
    
    public String selectAll(final String tableName) {
        final String result = switch(tableName) {
            case "Campagne" -> this.tCampaign.select();
            case "Sessioni" -> this.tSessions.select();
            case "Parties" -> this.tParties.select();
            case "Protagonisti" -> this.tProtagonists.select();
            case "NPCs" -> this.tNPCs.select();
            case "Mostri" -> this.tMonsters.select();
            case "Razze" -> this.tRaces.select();
            case "Classi" -> this.tClasses.select();
            case "Sottoclassi" -> this.tSubclasses.select();
            case "Oggetti" -> this.tItems.select();
            case "Turni" -> this.tTurns.select();
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
    
   public void op4(final int numTurn, final String nameCampaign, final int progressiveCode, final String nameProtagonist, final int damageProtagonist) {
        // First we add the turn
        this.tTurns.insert(numTurn, nameCampaign, progressiveCode);
        // Then we add the protagonist partecipation
        try (final PreparedStatement s = this.connection.prepareStatement(
            "INSERT INTO part_protagonisti VALUES (" + numTurn + ", ?, " + progressiveCode + ", ?, " + damageProtagonist + ")")) {
            s.setString(1, nameCampaign);
            s.setString(2, nameProtagonist);
            s.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public String op5(final String nameProtagonist) {
        try (final PreparedStatement s = this.connection.prepareStatement(
            "SELECT AVG(dannoProtagonista) FROM part_protagonisti WHERE nomeProtagonista = ?")) {
            s.setString(1, nameProtagonist);
            final ResultSet r = s.executeQuery();
            r.next();
            return r.getDouble(1) == 0 ? "" : nameProtagonist + " infligge mediamente " + String.valueOf(r.getDouble(1)) + " danni.";
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
