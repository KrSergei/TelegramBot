import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.net.URL;

public class Main {
    public static void main (String args[]){
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        Bot bot = new Bot();
        try{
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

    }
    public static final String WEATHER_URL =
            "http://api.openweathermap.org/data/2.5/weather?q=London,uk" +
                    "&units=metric&appid=241de9349721df959d8800c12ca4f1f3";

        URL url = JsonWeather.createUrl(WEATHER_URL);

        // загружаем Json в виде Java строки
        String resultJson = JsonWeather.parseUrl(url);
        System.out.println("Полученный JSON:\n" + resultJson);

        // парсим полученный JSON и печатаем его на экран
        JsonWeather.parseCurrentWeatherJson(resultJson);

        // формируем новый JSON объект из нужных нам погодных данных
        String json = JsonWeather.buildWeatherJson();
        System.out.println("Созданный нами JSON:\n" + json);



}
