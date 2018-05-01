import org.json.simple.parser.ParseException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Date;

public class Bot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            if (message.getText().equals("/help"))
                sendMsg(message, "Привет, я робот");
            if (message.getText().equals("/weather") || message.getText().equals("Weather")) {
                try {
                    sendMsg(message, "Сейчас погода в Петербурге: \nтемпература " + JsonWeather.jsonGetTemp(getWeaher()) + " C"
                            + "\nвлажность " + JsonWeather.jsonGetHumidity(getWeaher()) + "%"
                            + "\nдавление " + JsonWeather.jsonGetPressure(getWeaher()) + " мм.ртс");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            else
                sendMsg(message, "Hello, " + message.getFrom().getFirstName());
        }
        System.out.println("Пользователь с ID - " + update.getMessage().getChatId() + " запросил погоду.");

        if (message.getText().equals("Subscribe")) {
            if (ConnectDB.conn != null) {
                sendMsg(message, "Вы уже подписаны!");
            } else {
                try {
//                   long chatId = update.getMessage().getChatId();
                    ConnectDB.Conn();
                    ConnectDB.WriteDB( update.getMessage().getChatId());
                    ConnectDB.CloseDB();


                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                sendMsg(message, "Вы подписались на рассылку прогноза погоды!");
            }
            System.out.println("Пользователь с ID - " + update.getMessage().getChatId() + " подписался на прогноз погоды.");
        }
    }


    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add("Weather");

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add("Subscribe");
        keyboardSecondRow.add("Unsubscribe");

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);


        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "WeatherBot";
    }

    @Override
    public String getBotToken() {
        return "422723427:AAG5at1VNQDw_L2RYhg5AZuRkNGQETeO33w";
    }

    public static final String WEATHER_URL =
            "http://api.openweathermap.org/data/2.5/weather?q=Saint Petersburg,ru" + "&units=metric&appid=241de9349721df959d8800c12ca4f1f3";

    private String getWeaher (){
        URL url = JsonWeather.createUrl(WEATHER_URL);

        // загружаем Json в виде Java строки
        String resultJson = JsonWeather.parseUrl(url);
//        System.out.println("Полученный JSON:\n" + resultJson);

        return resultJson;
    }
}
