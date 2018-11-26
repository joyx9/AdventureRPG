package ui;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;

public class Weather {
    private String weatherDescription;


    public Weather() throws IOException {
        BufferedReader br = null;

        try {
            //API key: openweathermap.org
            String apiKey = "8abaf2d2ba865c17a283ab31d8d87e89";
            String vancouverQuery = "http://api.openweathermap.org/data/2.5/weather?q=Vancouver,can&APPID=";

            String theURL = vancouverQuery+apiKey;
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            String jsonData = sb.toString();

            try {
                parseWeather(jsonData);
            }
            catch (JSONException je){
                System.out.println("Error parsing JSON data");
            }

        } finally {

            if (br != null) {
                br.close();
            }
        }
    }

    public void parseWeather(String jsonData) throws JSONException{
        JSONObject vancouverObject = new JSONObject(jsonData);
        JSONArray weatherArray = vancouverObject.getJSONArray("weather");
        String weatherDescription = weatherArray.getJSONObject(0).getString("main");
        setWeatherDescription(weatherDescription);
        System.out.println("Looks like the weather for today is: " + weatherDescription);
    }

    public void setWeatherDescription(String s){
        this.weatherDescription = s;
    }
    public String getWeatherText(){
        return "Looks like the weather for today is: " + weatherDescription;
    }




}


