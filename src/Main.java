import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


public class Main {

    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        Bot bot = new Bot();
        try {
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

//        Timer timer = new Timer();
//        Calendar sendTime = Calendar.getInstance();
//
//        sendTime.get(Calendar.DAY_OF_WEEK);
//        sendTime.set(Calendar.HOUR_OF_DAY, 15);
//        sendTime.set(Calendar.MINUTE, 30);
//        sendTime.set(Calendar.SECOND, 00);
//        sendTime.set(Calendar.MILLISECOND, 0);
//
//        TimerTask timerTask = new SendByTime();
//        // стартуем TimerTask в виде демона
//
//        // будем запускать каждых 10 секунд (10 * 1000 миллисекунд)
//        timer.scheduleAtFixedRate(new SendByTime(), sendTime.getTime(), 2000);
//        System.out.println("---------------------------------------\nРасписание отправки сообщений запущено");
//
//        // вызываем cancel() через какое-то время
//        try {
//            Thread.sleep(120000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        timer.cancel();
//        System.out.println("---------------------------------------\nРасписание отправки сообщений прекращено");
//        try {
//            Thread.sleep(30000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
}
