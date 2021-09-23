package com.example.demo.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckContoller {
    private final String SITE_IS_UP = "Site is up!";
    private final String SITE_IS_DOWN = "Site is down!";
    private final String INCORRECT_URL = "URL is incorrect!";

    @GetMapping("/check1")
    public String getUtlStatusMessage(@RequestParam(required = false) String url){
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode() / 100;
            System.out.println(responseCode);
            if(responseCode !=2 && responseCode !=3){
                return SITE_IS_DOWN;
            }else{
                return SITE_IS_UP;
            }
        } catch (MalformedURLException e) {
            return INCORRECT_URL;
        } catch (IOException e) {
            return INCORRECT_URL;
        }
    }
}
