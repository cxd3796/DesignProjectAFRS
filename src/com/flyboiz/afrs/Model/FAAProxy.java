package com.flyboiz.afrs.Model;


import com.google.gson.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


public class FAAProxy {
    String condition;
    String airportCode;
    String airportName;
    String temperature;
    String delay;
    int delayNum;

    /**
     * Constructor for FAAProxy
     *
     * @param airportCode three letter airport code
     */
    public FAAProxy(String airportCode) {
        this.airportCode = airportCode;
        condition = null;
        temperature = "";
        delay = "";
        delayNum = 0;
    }

    /**
     * Grabs the weather in string format
     * Format: "AirportName,condition,temperature,delay"
     *
     * @return String
     */
    public String getWeather() {
        getRemoteWeather();
        return String.format("%s,%s,%s,%s", airportName, condition, temperature, delay);
    }

    /**
     * Gets the delay of the airport in int
     *
     * @return int 0-MaxDelay
     */
    public int getDelay() {
        getRemoteWeather();
        return delayNum;
    }

    /**
     * Pulls the remote weather from the FAA server and parse the appropriate information into the class
     */
    private void getRemoteWeather() {
        try {
            URL url = new URL(String.format("https://soa.smext.faa.gov/asws/api/airport/status/%s", airportCode));
            HttpsURLConnection request = (HttpsURLConnection) url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject rootObj = root.getAsJsonObject();

            // Get airport name
            JsonPrimitive airportName = rootObj.getAsJsonPrimitive("Name");
            this.airportName = airportName.getAsString();

            // Get Weather condition
            JsonObject weatherRoot = rootObj.getAsJsonObject("Weather");
            JsonArray weather = weatherRoot.getAsJsonArray("Weather");
            JsonObject weatherObject = weather.get(0).getAsJsonObject();
            JsonArray weatherArray = weatherObject.getAsJsonArray("Temp");
            JsonPrimitive weatherCondition = weatherArray.get(0).getAsJsonPrimitive();
            this.condition = weatherCondition.getAsString();

            // Get temperature string. Format: "41.0 F (5.0 C)"
            JsonArray temperature = weatherRoot.getAsJsonArray("Temp");
            this.temperature = temperature.getAsString();

            // Get Delay. Order of preference: Average, Maximum, Minimum. Turn it into an integer after
            String delay = getJsonDelay(rootObj);
            this.delay = delay;
            getIntDelay(delay);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper function to get the delay out of the JsonObject
     *
     * @param rootObj root Json object of the FAA Json response
     * @return
     */
    private String getJsonDelay(JsonObject rootObj) {
        JsonArray statusArray = rootObj.getAsJsonArray("Status");
        JsonObject status = (JsonObject) statusArray.get(0);
        String delay = "";
        if (status.has("AvgDelay")) {
            delay = status.get("AvgDelay").getAsString();
        } else if (status.has("MaxDelay")) {
            delay = status.get("MaxDelay").getAsString();
        } else if (status.has("MinDelay")) {
            delay = status.get("MinDelay").getAsString();
        }
        return delay;
    }

    /**
     * Helper function to get the int version of the delay
     *
     * @param delay delay of airport in minutes
     */
    private void getIntDelay(String delay) {
        if (delay.equals("")) {
            this.delay = "0 minutes";
            delayNum = 0;
        }
        else {
            int tempDelay = 0;
            String[] arrayDelay = delay.split(" ");
            int lastValue = 0;
            for (int i=0; i<arrayDelay.length; i++) {
                if (arrayDelay[i].equals("hours")) {
                    tempDelay += 60 * lastValue;
                }
                else if (arrayDelay[i].equals("minutes")) {
                    tempDelay += lastValue;
                }
                else {
                    lastValue = Integer.parseInt(arrayDelay[i]);
                }
            }
            delayNum = tempDelay;
        }
    }
}
