package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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
        this.connection = new ConnectionProvider("root", "", "db2023_dnd").getMySQLConnection();
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

    public void op4b(final int numTurn, final String nameCampaign, final int progressiveCode, final String nameMonster, final int damageMonster) {
        // First we add the turn
        this.tTurns.insert(numTurn, nameCampaign, progressiveCode);
        // Then we add the protagonist partecipation
        try (final PreparedStatement s = this.connection.prepareStatement(
            "INSERT INTO part_mostri VALUES (" + numTurn + ", ?, " + progressiveCode + ", ?, " + damageMonster + ")")) {
            s.setString(1, nameCampaign);
            s.setString(2, nameMonster);
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

    public boolean op6(final String typeInteraction ,final String nameNPC, final String descPersonality, final String nameCampaign, final int progressiveCode) {
        try (final PreparedStatement s = this.connection.prepareStatement("INSERT INTO comparse VALUES (?, ?, ?, ?)")) {
            s.setString(1, nameCampaign);
            s.setInt(2, progressiveCode);
            s.setString(3, nameNPC);
            s.setString(4, typeInteraction);
            return this.tNPCs.insert(typeInteraction, nameNPC, Optional.empty(), descPersonality, nameCampaign);
        } catch (final SQLException e) {
            return false;
        }
    }

    public boolean op7(final String nameNPC, final String nameCampaign, final int progressiveCode, final String typeInteraction) {
        try (final PreparedStatement s = this.connection.prepareStatement(
            "UPDATE comparse SET tipoRapporto = ? WHERE nomeCampagna = ? AND codProgressivo = ? AND nomeNPC = ?")) {
            s.setString(1, typeInteraction);
            s.setString(2, nameCampaign);
            s.setInt(2, progressiveCode);
            s.setString(3, nameNPC);
            s.executeUpdate();
            return this.tNPCs.update(nameNPC, typeInteraction);
        } catch (final SQLException e) {
            return false;
        }
    }

    public String op8(final String nameCampaign) {
        return this.tNPCs.selectByCampaign(nameCampaign);
    }

    public String op9(final String codParty) {
        try (final PreparedStatement s = this.connection.prepareStatement(
            "SELECT P.nomeProtagonista, P.nomeRazza, P.nomeClasse, P.numeroSottoclasse " +
            "FROM composizioni C JOIN protagonisti P ON (C.nomeProtagonista = P.nomeProtagonista) " +
            "WHERE codParty = ?")) {
                s.setString(1, codParty);
                final var r = s.executeQuery();
                int i = 0;
                final var sb = new StringBuilder();
                while (r.next()) {
                    i++;
                    sb.append(i + ". nomeProtagonista = " + r.getString(1) + "; nomeRazza = " + r.getString(2) + 
                        "; nomeClasse = " + r.getString(3) + "; numSottoclasse = " + r.getInt(4) + "\n");
                }
                return sb.toString();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public boolean op10(final String nameItem, final String nameProtagonist, final int quantity) {
        return this.tItems.insertToProtagonist(nameItem, nameProtagonist, quantity);
    }

    public String op11(final String nameProtagonist) {
        return this.tItems.selectByProtagonist(nameProtagonist);
    }

    public boolean op12(final String nameItem, final String codParty) {
        try (final PreparedStatement s = this.connection.prepareStatement(
            "SELECT EXISTS (SELECT PR.nomeOggetto " +
            "FROM composizioni C, protagonisti P, proprieta PR " +
            "WHERE C.nomeProtagonista = P.nomeProtagonista AND P.nomeProtagonista = PR.nomeProtagonista " +
            "AND C.codParty = ? AND PR.nomeOggetto = ?)")) {
                s.setString(1, codParty);
                s.setString(2, nameItem);
                final var r = s.executeQuery();
                r.next();
                System.out.println(r.getBoolean(1));
                return r.getInt(1) != 0;
            } catch (final SQLException e) {
                e.printStackTrace();
                return false;
            }
    }
}
