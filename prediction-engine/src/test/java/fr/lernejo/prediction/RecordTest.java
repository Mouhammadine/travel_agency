package fr.lernejo.prediction;

import fr.lernejo.prediction.records.Temperatures;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.*;

public class RecordTest {
    @Test
    public void temperatures_constructor_ok() {
        Instant desiredOutput = Instant.ofEpochSecond(1640000000); // 2021-12-20T11:33:20Z
        Temperatures temp = new Temperatures("france", desiredOutput, 10.0, 12.0);

        assertThat(temp.country()).isEqualTo("france");
        assertThat(temp.temperatures().size()).isEqualTo(2);
        assertThat(temp.temperatures().get(0).date()).isEqualTo("2021-12-20");
        assertThat(temp.temperatures().get(0).temperature()).isEqualTo(10.0);
        assertThat(temp.temperatures().get(1).date()).isEqualTo("2021-12-19");
        assertThat(temp.temperatures().get(1).temperature()).isEqualTo(12.0);
    }
}
