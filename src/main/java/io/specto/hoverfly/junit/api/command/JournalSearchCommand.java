package io.specto.hoverfly.junit.api.command;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.specto.hoverfly.junit.core.model.Request;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JournalSearchCommand {

    private Request request;

    public JournalSearchCommand() {
    }

    public JournalSearchCommand(Request request) {
        this.request = request;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
