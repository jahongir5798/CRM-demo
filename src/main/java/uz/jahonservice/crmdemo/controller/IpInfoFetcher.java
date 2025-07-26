package uz.jahonservice.crmdemo.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class IpInfoFetcher {

    public static String getIpDetails(String ip) {
        StringBuilder result = new StringBuilder();
        try {
            String apiUrl = "http://ip-api.com/json/" + ip;
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik: " + e.getMessage();
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String ipInfo = getIpDetails("195.158.2.220");
        System.out.println(ipInfo);
    }
}
