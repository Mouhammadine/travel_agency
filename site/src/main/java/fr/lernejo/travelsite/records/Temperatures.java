package fr.lernejo.travelsite.records;

import java.util.ArrayList;
import java.util.List;

public class Temperatures {
    public final String country;
    public final List<Temperature> temperatures;

    public Temperatures() {
        this.country = null;
        this.temperatures = new ArrayList<>();
    }
}
