package com.example.recap11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpsManager {

    private String urlAddress;
    private BufferedReader bufferedReader;
    private HttpsURLConnection connection;

    public HttpsManager(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    public String procesare() {
        try {
            return preluareJsonHttps();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            inchidereConexiuni();
        }
    }

    private void inchidereConexiuni() {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("Eroare la închiderea bufferedReader", e);
        }
        if (connection != null) {
            connection.disconnect();
        }
    }

    private String preluareJsonHttps() throws IOException {
        connection = (HttpsURLConnection) new URL(urlAddress).openConnection();
        bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String line;
        while((line = bufferedReader.readLine()) != null) {
            builder.append(line);
        }

        return builder.toString();
    }
}
