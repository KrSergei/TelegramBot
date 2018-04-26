import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.sql.SQLException;


public class Main {

    public static void main (String args[]) throws ClassNotFoundException, SQLException {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        Bot bot = new Bot();
        try{
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
//        ConnectBD.Conn();
//        ConnectBD.CreateDB();
//        ConnectBD.WriteDB();
////        ConnectBD.ReadDB();
//        ConnectBD.CloseDB();
    }
}
