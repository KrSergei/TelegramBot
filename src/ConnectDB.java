import java.sql.*;
import java.util.Calendar;
import java.sql.Date;


public class ConnectDB {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;


    // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
    public static void Conn() throws ClassNotFoundException, SQLException
    {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\S\\source\\repos\\Java\\TelegramBot\\DB\\WeatherBot_DB.s3db");

        System.out.println("База Подключена!");
    }
//
////    // --------Создание таблицы--------
////    public static void CreateDB() throws ClassNotFoundException, SQLException
////    {
//////        statmt = conn.createStatement();
//////        statmt.execute("CREATE TABLE if not exists 'signed_users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'chatId' INTEGER UNIQUE, 'firstname' TEXT, 'date'  date);");
////        System.out.println("Таблица создана или уже существует.");
//    }

    // --------Заполнение таблицы--------
    public static void WriteDB(long chatId) throws SQLException
    {

       PreparedStatement statmt=conn.prepareStatement("INSERT INTO signed_users ('chatId') VALUES ('"+ chatId +"'); ");
//       statmt.setInt(1, chatId);
//        statmt.setDate(2, date);
          statmt.executeUpdate();
            System.out.println("Запись в таблицу завершена");
    }

//    // -------- Вывод таблицы--------
//    public static void ReadDB() throws ClassNotFoundException, SQLException
//    {
//        try (ResultSet resultSet = resSet = statmt.executeQuery("SELECT * FROM signed_users")) {
//        }
//
//        while(resSet.next())
//        {
//            int chatid = resSet.getInt("chatId");
////            String  name = resSet.get String("name");
////            String  phone = resSet.getString("phone");
//            System.out.println( "chatId = " + chatid );
//            System.out.println();
//        }
//
//        System.out.println("Таблица выведена");
//    }

    // --------Закрытие--------
    public static void CloseDB() throws ClassNotFoundException, SQLException
    {
        conn.close();
//        statmt.close();
//        resSet.close();

        System.out.println("Соединения закрыты");
    }

}

//" + chatId + ", " + firstname +" "+ date +"