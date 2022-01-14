package fr.lernejo.travelsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SpringBootApplication
public class Launcher {

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }

    @Bean
    PredictionEngineClient predictionEngineClient() {
        String port = System.getenv("tackRedirectPort");
        if (port == null)
            port = "7080";

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:" + port + "/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        return retrofit.create(PredictionEngineClient.class);
    }
}
