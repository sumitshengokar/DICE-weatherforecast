package com.example.dice.weatherforecast.exceptions;

public class CityNotFound extends RuntimeException{
    public CityNotFound(String message){
        super(message);
    }
}
