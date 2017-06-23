package io.specto.hoverfly.junit.core.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import org.junit.Test;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

public class JournalTest {


    private ObjectMapper objectMapper = new ObjectMapper();
    private URL resource = Resources.getResource("sample-journal.json");

    @Test
    public void shouldDeserializeFromJson() throws Exception {

        Journal journal = objectMapper.readValue(resource, Journal.class);


        assertThat(journal.getEntries()).hasSize(1);

        JournalEntry logEntry = journal.getEntries().iterator().next();

        assertThat(logEntry.getMode()).isEqualTo("simulate");
        assertThat(logEntry.getRequest()).isNotNull();
        assertThat(logEntry.getRequest().getDestination()).isEqualTo("hoverfly.io");
        assertThat(logEntry.getResponse()).isNotNull();
        assertThat(logEntry.getLatency()).isEqualTo(2);
        assertThat(logEntry.getTimeStarted()).isEqualTo(
                LocalDateTime.parse("2017-06-22T13:18:08+01:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));

    }
}