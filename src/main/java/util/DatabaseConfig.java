package util;

import org.flywaydb.core.Flyway;

public class DatabaseConfig {

    private final String URL = JDBCUtil.getJdbc().getURL();
    private final String  USER = JDBCUtil.getJdbc().getUSER();
    private final String PASSWORD = JDBCUtil.getJdbc().getPASSWORD();
    private static DatabaseConfig databaseConfig;

    private DatabaseConfig() {
    }

    public static DatabaseConfig getDatabaseConfig() {
        if (databaseConfig == null){
            databaseConfig = new DatabaseConfig();
        }
        return databaseConfig;
    }

    public void migrate() {

        Flyway flyway = Flyway.configure()
                .dataSource(URL, USER, PASSWORD)
                .load();

        flyway.migrate();
        System.out.println("Migrations executadas com sucesso!");
    }

}

