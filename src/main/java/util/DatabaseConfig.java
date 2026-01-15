package util;

import org.flywaydb.core.Flyway;

public class DatabaseConfig {

    private final String URL = JDBCUtil.getInstance().getURL();
    private final String USER = JDBCUtil.getInstance().getUSER();
    private final String PASSWORD = JDBCUtil.getInstance().getPASSWORD();
    private static DatabaseConfig databaseConfig;

    private DatabaseConfig() {
    }

    public static DatabaseConfig getInstance() {
        if (databaseConfig == null) {
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

