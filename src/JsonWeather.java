import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
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
