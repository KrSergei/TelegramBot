import java.sql.*;
import java.sql.Date;


public class ConnectDB {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;


    // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
    public static void Conn() throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\S\\source\\repos\\Java\\TelegramBot\\DB\\WeatherBot_DB.s3db");
        System.out.println("--------------------------------------------------\nПодключение к базе завершено!");
    }
//
////    // --------Создание таблицы--------
////    public static void CreateDB() throws ClassNotFoundException, SQLException
////    {
//////        statmt = conn.createStatement();
//////        statmt.execute("CREATE TABLE if not exists 'signed_users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'chatId' INTEGER UNIQUE, 'date'  date);");
////        System.out.println("Таблица создана или уже существует.");
//    }

    // --------Заполнение таблицы--------
    public static void Subscribe(long chatId, Date date) throws SQLException {

        statmt = conn.createStatement();
        statmt.execute("INSERT INTO signed_users ('chatId', 'date') VALUES ('" + chatId + "', '" + date + "'); ");
        System.out.println("Подписка пользователя " + chatId + " добавлена");
    }

    public static void Unsubscribe(long chatId) throws SQLException {

        statmt = conn.createStatement();
        statmt.execute("DELETE FROM signed_users WHERE chatId = '" + chatId + "' ");
        System.out.println("Подписка пользователя " + chatId + " удалена");
    }

//    // -------- Вывод таблицы--------
    public  static int GetChatId() throws ClassNotFoundException, SQLException
    {

//        statmt = conn.createStatement();
//        statmt.execute("SELECT * from signed_users WHERE chatId = '" + chatId + "' \"");

        try (ResultSet  resSet = statmt.executeQuery("SELECT chatId FROM signed_users")) {
        }

        while(resSet.next())
        {
            int chatId = resSet.getInt("chatId");

//           sendMessage.setChatId(message.getChatId().toString());
////        try {
//            sendMessage.setText("Сейчас погода в Петербурге: \nтемпература " + JsonWeather.jsonGetTemp(getWeaher()) + " C"
//                    + "\nвлажность " + JsonWeather.jsonGetHumidity(getWeaher()) + "%"
//                    + "\nдавление " + JsonWeather.jsonGetPressure(getWeaher()) + " мм.ртс");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }





            System.out.println( "ChatId = " + chatId );
            System.out.println();

        }

        System.out.println("Таблица ID пользователей выведена");
        return 0;
    }

    // --------Закрытие--------
    public static void CloseDB() throws ClassNotFoundException, SQLException {

        statmt.close();
        conn.close();
        resSet.close();

        System.out.println("Соединения с БД закрыты");
    }

}
