import org.json.simple.parser.ParseException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

import static java.util.Calendar.getInstance;


//public class SendByTime extends TimerTask {
//
//     @Override
//    public void run() {
//         try {
//             int chatId = ConnectDB.GetChatId();
//
//         } catch (ClassNotFoundException e) {
//             e.printStackTrace();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//
//         try {
//             ConnectDB.Conn();
////             ConnectDB.GetChatId();
//             ConnectDB.CloseDB();
//
//         } catch (ClassNotFoundException e) {
//             e.printStackTrace();
//         } catch (SQLException e) {
//        e.printStackTrace();
//    }
//
//
//    }
//}
