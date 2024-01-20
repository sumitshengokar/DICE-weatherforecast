package com.example.dice.weatherforecast.service;

import com.example.dice.weatherforecast.entity.ErrorEntity;
import com.example.dice.weatherforecast.exceptions.CityNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class WeatherForecastService {
    public ResponseEntity<Object> getCityInfo(String city) throws IOException, InterruptedException {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://forecast9.p.rapidapi.com/rapidapi/forecast/" + city + "/summary/"))
                    .header("X-RapidAPI-Key", "94ac71f0c9msh8d5bf23728b3c6dp1f0827jsn39bfcb799372")
                    .header("X-RapidAPI-Host", "forecast9.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode()==404){
                throw new CityNotFound("please enter proper city name");
            }
            System.out.println(response.body());
            return new ResponseEntity<Object>(response.body(), HttpStatus.OK);
        }
        catch(CityNotFound e){
            ErrorEntity errorEntity = new ErrorEntity(e.getMessage(),"NOT_FOUND","404");
            return new ResponseEntity<Object>(errorEntity,HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            ErrorEntity errorEntity = new ErrorEntity(e.getMessage(),"INTERNAL_SERVER_ERROR","500");
            return new ResponseEntity<Object>(errorEntity,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getHourlyInfo(String city) throws IOException, InterruptedException {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://forecast9.p.rapidapi.com/rapidapi/forecast/" + city + "/hourly/"))
                    .header("X-RapidAPI-Key", "94ac71f0c9msh8d5bf23728b3c6dp1f0827jsn39bfcb799372")
                    .header("X-RapidAPI-Host", "forecast9.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            return new ResponseEntity<Object>(response.body(), HttpStatus.OK);
        }catch (CityNotFound e){
            ErrorEntity errorEntity = new ErrorEntity(e.getMessage(),"NOT_FOUND","404");
            return new ResponseEntity<Object>(errorEntity,HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            ErrorEntity errorEntity = new ErrorEntity(e.getMessage(),"INTERNAL_SERVER_ERROR","500");
            return new ResponseEntity<Object>(errorEntity,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
