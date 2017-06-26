package io.specto.hoverfly.junit.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Journal {


    @JsonProperty("journal")
    private final List<JournalEntry> entries;


    @JsonCreator
    public Journal(@JsonProperty("journal") List<JournalEntry> entries) {
        this.entries = entries;
    }

    public List<JournalEntry> getEntries() {
        return entries;
    }
}
