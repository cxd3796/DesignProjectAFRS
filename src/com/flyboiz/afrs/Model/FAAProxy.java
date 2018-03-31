package com.flyboiz.afrs.Model;


import com.google.gson.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class FAAProxy {
    String condition;
    String airportCode;
    String temperature;
    String delay;

    /**
     *
     * @param airportCode
     */
    public FAAProxy(String airportCode) {
        this.airportCode = airportCode;
        condition = null;
        temperature = "";
        delay = "";
    }

    /**
     *
     * @return
     */
    public String getWeather() {
        getRemoteWeather();
        return "";
    }

    /**
     *
     * @return
     */
    public int getDelay() {
        return 0;
    }

    /**
     *
     */
    private void getRemoteWeather() {
        try {
            URL url = new URL(String.format("https://soa.smext.faa.gov/asws/api/airport/status/%s", airportCode));
            HttpsURLConnection request = (HttpsURLConnection) url.openConnection();
            request.connect();

            Gson gson = new Gson();
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject rootObj = root.getAsJsonObject();
            Map<String, Object> map = new HashMap<String,Object>();
            map = (Map<String, Object>) gson.fromJson(rootObj, map.getClass());
            System.out.println(map);
            JsonArray status = rootObj.getAsJsonArray("Status");
            JsonObject weatherRoot = rootObj.getAsJsonObject("Weather");
            JsonArray weather = weatherRoot.getAsJsonArray("Weather");

            // Get temperature string. Format: "41.0 F (5.0 C)"
            JsonArray temperature = weatherRoot.getAsJsonArray("Temp");
            this.temperature = temperature.getAsString();

            // Get Delay. Order of preference: Average, Maximum, Minimum
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

            System.out.println(this.temperature);
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
        FAAProxy proxy = new FAAProxy("ORD");
        proxy.getWeather();
    }

}
