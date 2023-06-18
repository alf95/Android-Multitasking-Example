package com.example.HW2.multitasking;

import android.os.Bundle;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class BackgroundTaskInfo {

    public static String getCurrentDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault());
        String dateString = dateFormat.format(new Date());
        return dateString;
    }

    public static Map getRandomUser() throws IOException {
        String str = "https://random-data-api.com/api/v2/users?size=1";
        URLConnection urlConn = null;
        BufferedReader bufferedReader = null;

        URL url = new URL(str);
        urlConn = url.openConnection();
        bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line);
        }

        return new Gson().fromJson(stringBuffer.toString(), Map.class);
    }
}
