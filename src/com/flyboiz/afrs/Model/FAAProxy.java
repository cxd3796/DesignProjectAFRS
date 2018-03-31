package com.flyboiz.afrs.Model;


import com.google.gson.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class FAAProxy {
    String condition;
    String airportCode;
    int temperature;
    int delay;

    public FAAProxy(String airportCode) {
        this.airportCode = airportCode;
        condition = null;
        temperature = Integer.MAX_VALUE;
        delay = 0;
    }

    public void getWeather() {
        try {
            URL url = (URL) new URL(String.format("https://soa.smext.faa.gov/asws/api/airport/status/%s", airportCode));
            HttpsURLConnection request = (HttpsURLConnection) url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject rootObj = root.getAsJsonObject();
            JsonArray status = rootObj.getAsJsonArray("Status");
            JsonObject weatherRoot = rootObj.getAsJsonObject("Weather");
            JsonArray weather = weatherRoot.getAsJsonArray("Weather");

            // Get temperature string. Format: "41.0 F (5.0 C)"
            JsonArray temperature = weatherRoot.getAsJsonArray("Temp");
            String temperatureAsString = temperature.getAsString();

            // Get Delay
            int avgDelay = Integer.MIN_VALUE;
            int minDelay = Integer.MIN_VALUE;
            int maxDelay = Integer.MIN_VALUE;
            for (JsonElement statusElement : status) {
                JsonObject statusObject = (JsonObject) statusElement;
                if (statusObject.has("avgDelay")) {
                    avgDelay = statusObject.get("avgDelay").getAsInt();
                }
                else if (statusObject.has("minDelay")) {
                    minDelay = statusObject.get("minDelay").getAsInt();
                }
                else if (statusObject.has("maxDelay")) {
                    maxDelay = statusObject.get("maxDelay").getAsInt();
                }
            }
            if (avgDelay != Integer.MIN_VALUE) {
                delay = avgDelay;
            }
            else if (maxDelay != Integer.MIN_VALUE) {
                delay = maxDelay;
            }
            else if (minDelay != Integer.MIN_VALUE) {
                delay = minDelay;
            }


            System.out.println(temperatureAsString);
            System.out.println(status);
            System.out.println(weatherRoot);
            System.out.println(weather);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FAAProxy proxy = new FAAProxy("JFK");
        proxy.getWeather();
    }

}
