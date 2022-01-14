package fr.lernejo.travelsite;

import fr.lernejo.travelsite.records.Temperatures;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PredictionEngineClient {
    @GET("/api/temperature")
    Call<Temperatures> getTemperatures(@Query("country") String country);
}
