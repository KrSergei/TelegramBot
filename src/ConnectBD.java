import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectBD {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
    public static void Conn() throws ClassNotFoundException, SQLException
    {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\S\\source\\repos\\Java\\TelegramBot\\DB\\WeatherBot_BD.s3db");

        System.out.println("База Подключена!");
    }

    // --------Создание таблицы--------
    public static void CreateDB() throws ClassNotFoundException, SQLException
    {
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'signed_users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'chatId' INTEGER UNIQUE );");

        System.out.println("Таблица создана или уже существует.");
    }

    // --------Заполнение таблицы--------
    public static void WriteDB() throws SQLException
    {

        statmt.execute("INSERT INTO 'signed_users' ('chatId') VALUES ('Petya'); ");
//        statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Vasya', 321789); ");
//        statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Masha', 456123); ");

        System.out.println("Таблица заполнена");
    }

    // -------- Вывод таблицы--------
//    public static void ReadDB() throws ClassNotFoundException, SQLException
//    {
//        resSet = statmt.executeQuery("SELECT * FROM chatId");
//
//        while(resSet.next())
//        {
//            int id = resSet.getInt("id");
//            String  name = resSet.getString("name");
//            String  phone = resSet.getString("phone");
//            System.out.println( "ID = " + id );
//            System.out.println( "name = " + name );
//            System.out.println( "phone = " + phone );
//            System.out.println();
//        }
//
//        System.out.println("Таблица выведена");
//    }

    // --------Закрытие--------
    public static void CloseDB() throws ClassNotFoundException, SQLException
    {
        conn.close();
        statmt.close();
        resSet.close();

        System.out.println("Соединения закрыты");
    }

}