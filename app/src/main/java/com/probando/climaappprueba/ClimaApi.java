package com.probando.climaappprueba;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/*Interfaz que utilizada para unir el */
public interface ClimaApi {
    @GET("weather");
    Call<Example> getweather(@Query("q") String nombreciudad,
                             @Query("appid") String apikey);
}
