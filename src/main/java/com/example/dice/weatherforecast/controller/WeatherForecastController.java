package com.example.dice.weatherforecast.controller;

import com.example.dice.weatherforecast.service.WeatherForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

@RestController
@RequestMapping(value = "/weather")
public class WeatherForecastController {

    private final WeatherForecastService weatherForecastService;
    @Autowired
    public WeatherForecastController(final WeatherForecastService weatherForecastService){
        this.weatherForecastService=weatherForecastService;
    }
    @GetMapping(value = "/forecast/city/{city}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getWeatherForecastOfCity(@PathVariable final String city) throws IOException, InterruptedException {
        return weatherForecastService.getCityInfo(city);
    }

    @GetMapping(value = "/forecast/hourly/{city}")
    public ResponseEntity<Object> getHourlyForecastOfCity(@PathVariable final String city) throws IOException, InterruptedException {
        return weatherForecastService.getHourlyInfo(city);
    }



}
