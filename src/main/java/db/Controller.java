package db;

import java.sql.Connection;

public class Controller {

    private final Connection connection;

    public Controller() {
        this.connection = new ConnectionProvider("Michele", "Michele7", "db2023_dnd").getMySQLConnection();
    }
    
    public String selectAll(final String tableName) {
        final String result = switch(tableName) {
            case "Campagne" -> null;

            default -> throw new IllegalStateException();
        };
        return result;
    }
}
