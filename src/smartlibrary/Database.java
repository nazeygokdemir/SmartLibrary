package smartlibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static final String URL = "jdbc:sqlite:smartlibrary.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite sürücüsü bulunamadı!");
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initialize() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            String createBooksTable =
                    "CREATE TABLE IF NOT EXISTS books(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "title TEXT," +
                    "author TEXT," +
                    "year INTEGER" +
                    ")";

            String createStudentsTable =
                    "CREATE TABLE IF NOT EXISTS students(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "department TEXT" +
                    ")";

            String createLoansTable =
                    "CREATE TABLE IF NOT EXISTS loans(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "bookId INTEGER," +
                    "studentId INTEGER," +
                    "dateBorrowed TEXT," +
                    "dateReturned TEXT" +
                    ")";

            stmt.execute(createBooksTable);
            stmt.execute(createStudentsTable);
            stmt.execute(createLoansTable);

            System.out.println("Veritabanı hazır.");
        } catch (SQLException e) {
            System.out.println("Veritabanı hatası: " + e.getMessage());
        }
    }
}
