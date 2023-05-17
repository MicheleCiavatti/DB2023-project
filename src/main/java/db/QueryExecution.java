package db;

import java.sql.Connection;

public class QueryExecution {
    
    private final Connection connection;

    public QueryExecution() {
        this.connection = new ConnectionProvider("Michele", "Michele7", "db2023_dnd").getMySQLConnection();
    }
}
