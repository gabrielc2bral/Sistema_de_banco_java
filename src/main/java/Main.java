import util.DatabaseConfig;
import util.Menu;

public class Main {

    public static void main(String[] args) {
        DatabaseConfig.getInstance().migrate();
        Menu.iniciarMenu();

    }
}
