package fr.lernejo.travelsite;

import com.google.gson.Gson;
import fr.lernejo.travelsite.records.Client;
import fr.lernejo.travelsite.records.WeatherExpectation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ControllerTest {
    private final Gson gson = new Gson();

    @Test
    void user_not_exists(@Autowired MockMvc mockMvc) throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/travels?userName=toto"))
            .andExpect(MockMvcResultMatchers.status().is(404));
    }

    @Test
    void bad_request_inscription(@Autowired MockMvc mockMvc) throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders.post("/api/inscription")
                .content("{}")
                .contentType("application/json")
            )
            .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void bad_temp_inscription(@Autowired MockMvc mockMvc) throws Exception {
        Client client = new Client("a@gmail.com", "Toto", "france", WeatherExpectation.COLDER, 100);

        mockMvc
            .perform(MockMvcRequestBuilders.post("/api/inscription")
                .content(gson.toJson(client))
                .contentType("application/json")
            )
            .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void bad_country_inscription(@Autowired MockMvc mockMvc) throws Exception {
        Client client = new Client("a@gmail.com", "Toto", "toto", WeatherExpectation.COLDER, 20);

        mockMvc
            .perform(MockMvcRequestBuilders.post("/api/inscription")
                .content(gson.toJson(client))
                .contentType("application/json")
            )
            .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void inscription_successful(@Autowired MockMvc mockMvc) throws Exception {
        Client client = new Client("a@gmail.com", "Toto", "france", WeatherExpectation.COLDER, 20);

        mockMvc
            .perform(MockMvcRequestBuilders.post("/api/inscription")
                .content(gson.toJson(client))
                .contentType("application/json")
            )
            .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
