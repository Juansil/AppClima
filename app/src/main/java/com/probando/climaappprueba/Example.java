package com.probando.climaappprueba;
import com.google.gson.annotations.SerializedName;

public class Example {
    @SerializedName("climaDatos")
    ClimaDatos climaDatos;

    public ClimaDatos getClimaDatos() {
        return climaDatos;
    }

    public void setClimaDatos(ClimaDatos climaDatos) {
        this.climaDatos = climaDatos;
    }
}
