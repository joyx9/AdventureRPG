package ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Weather {


    public static void main(String[] args) throws MalformedURLException, IOException {
        BufferedReader br = null;

        try {
            //API key: openweathermap.org
            String apiKey = "8abaf2d2ba865c17a283ab31d8d87e89";
            String vancouverQuery = "api.openweathermap.org/data/2.5/weather?q=Vancouver,can&mode=html&APPID=";
            String theURL = vancouverQuery+apiKey;
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            System.out.println(sb);
        } finally {

            if (br != null) {
                br.close();
            }
        }
    }
}


