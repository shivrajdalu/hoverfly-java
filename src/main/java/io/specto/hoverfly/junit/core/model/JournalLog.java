package io.specto.hoverfly.junit.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JournalLog {


    private final List<JournalLogEntry> entries;


    @JsonCreator
    public JournalLog(@JsonProperty("journal") List<JournalLogEntry> entries) {
        this.entries = entries;
    }

    public List<JournalLogEntry> getEntries() {
        return entries;
    }
}
