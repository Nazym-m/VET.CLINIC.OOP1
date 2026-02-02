
import menu.Menu;
import menu.MenuManager;
import database.DatabaseConnection;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        System.out.println("Vet Clinic System Starting...");

        Connection testConn = DatabaseConnection.getConnection();
        if (testConn == null) {
            System.out.println("❌ Cannot start: Database connection failed!");
            return;
        }
        DatabaseConnection.closeConnection(testConn);

        Menu menu = new MenuManager();
        menu.run();
    }
}