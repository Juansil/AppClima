package com.probando.climaappprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    /**/
EditText entrada;
TextView temperatura;
TextView temperaturamin;
TextView temperaturamax;
TextView sensacion;
String url ="api.openweathermap.org/data/2.5/weather?q={city name}&appid={your api key}";
String api_key ="472760e77bb233064a2c8ac1335221ce";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entrada =findViewById(R.id.entrada);
        temperatura =findViewById(R.id.temperatura);
        temperaturamin =findViewById(R.id.temperaturamin);
        temperaturamax =findViewById(R.id.temperaturamax);
        sensacion =findViewById(R.id.sensacion);

    }
    /*Esta funcion nos permite obeter los datos de la API*/
    public void ObtenerClima(View v){
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
         ClimaApi miapi= retrofit.create(ClimaApi.class);
        Call<Example> exampleCall =miapi.getweather(entrada.getText().toString().trim(),
                api_key);
        exampleCall.enqueue(new Callback<Example>() {
            /*Aqui se estara capturando los errores*/
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.code()==404){
                    Toast.makeText( context:MainActivity.this,resid :"Ingrese una Ciudad Valida",Toast.LENGTH_LONG).show();
                }
                else if((!response.isSuccessful())) {
                    Toast.makeText(context:MainActivity.this, response.code(), Toast.LENGTH_LONG).
                    show();
                }

                /* Una vez que se haya obetino los datos se guardaran se alcenaran en un para acceder al ellos mas facilmente*/
                Example datos = response.body();
                ClimaDatos climaDatos = datos.getClimaDatos();

                Double temperaturakelvin = climaDatos.getTemp();
                Double tempminkelvin = climaDatos.getTempMin();
                Double tempmaxkelvin = climaDatos.getTempMax();
                Double termicakelvin = climaDatos.getFeelsLike();

                /*como los datos obtenidos se encuentra en la unida de medida kelvin se prodece a realizar el cambio a celsius*/
                Integer temperaturacelsius = (int)(temperaturakelvin - 273);
                Integer tempmincelsius = (int)(tempminkelvin - 273);
                Integer tempmaxcelsius = (int)(tempmaxkelvin - 273);
                Integer termicacelsius = (int)(termicakelvin - 273);
            /* Se cargan los datos en Variables para visualizarlo en la aplicacion*/
                temperatura.setText(String.valueOf(temperaturacelsius)+"°C");
                temperaturamin.setText(String.valueOf(temperaturacelsius)+"°C"+"Min");
                temperaturamax.setText(String.valueOf(temperaturacelsius)+"°C"+"Max");
                sensacion.setText(String.valueOf(temperaturacelsius)+"°C"+"Sesacion Termica");
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
}