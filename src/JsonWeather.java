import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonWeather {
    public static String parseUrl(URL url) {
        if (url == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        // открываем соедиение к указанному URL
        // помощью конструкции try-with-resources
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {

            String inputLine;
            // построчно считываем результат в объект StringBuilder
            while ((inputLine = in.readLine()) != null) {
                stringBuilder.append(inputLine);
                System.out.println(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
//    public static void parseCurrentWeatherJson(String resultJson) {
//        try {
//            // конвертируем строку с Json в JSONObject для дальнейшего его парсинга
//            JSONObject weatherJsonObject = (JSONObject) JSONValue.parseWithException(resultJson);
//
//            // получаем название города, для которого смотрим погоду
////            System.out.println("Название города: " + weatherJsonObject.get("name"));
//
//            // получаем массив элементов для поля weather
//            /* ... "weather": [
//            {
//                "id": 500,
//                    "main": "Rain",
//                    "description": "light rain",
//                    "icon": "10d"
//            }
//            ], ... */
//            JSONArray weatherArray = (JSONArray) weatherJsonObject.get("weather");
//            // достаем из массива первый элемент
//            JSONObject weatherData = (JSONObject) weatherArray.get(0);
//
//            // печатаем текущую погоду в консоль
////            System.out.println("Погода на данный момент: " + weatherData.get("main"));
////            // и описание к ней
////            System.out.println("Более детальное описание погоды: " + weatherData.get("description"));
//
//        } catch (org.json.simple.parser.ParseException e) {
//            e.printStackTrace();
//        }
//    }
//    public static String buildWeatherJson() {
//        // для простоты примера просто хардкодим нужные данные в методе
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("name", "Сейчас погода в Петербурге");
//        jsonObject.put("main", "Солнечно");
//        jsonObject.put("description", "Мороз трескучий, На небе ни единой тучи");
//
//        return jsonObject.toString();
//    }

    // создаем объект URL из указанной в параметре строки
    public static URL createUrl(String link) {
        try {
            return new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object jsonGetTemp (String weather) throws ParseException {
        JSONObject weatherJsonObject = (JSONObject) JSONValue.parseWithException(weather);
        JSONObject temp = (JSONObject) weatherJsonObject.get("main");
        return temp.get("temp");

    }
    public static Object jsonGetHumidity (String weather) throws ParseException {
        JSONObject weatherJsonObject = (JSONObject) JSONValue.parseWithException(weather);
      JSONObject humidity = (JSONObject) weatherJsonObject.get("main");
        return humidity.get("humidity");
}
    public static Object jsonGetPressure (String weather) throws ParseException {
        JSONObject weatherJsonObject = (JSONObject) JSONValue.parseWithException(weather);
        JSONObject pressure = (JSONObject) weatherJsonObject.get("main");
        return pressure.get("pressure");
}
}
