package io.specto.hoverfly.junit.verification;

import io.specto.hoverfly.junit.api.HoverflyClient;
import io.specto.hoverfly.junit.dsl.RequestMatcherBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.specto.hoverfly.junit.verification.HoverflyVerifications.times;

public class InOrderVerification {


    private HoverflyClient hoverflyClient;
    private List<RequestMatcherBuilder> requestMatchers = new ArrayList<>();

    public InOrderVerification(HoverflyClient hoverflyClient, RequestMatcherBuilder... requestMatchers) {
        this.hoverflyClient = hoverflyClient;
        this.requestMatchers.addAll(Arrays.asList(requestMatchers));
    }

    public void verify() {

        requestMatchers.stream()
                .map(RequestMatcherBuilder::build)
                .map(request -> hoverflyClient.searchJournal(request))
                .map(VerificationData::new)
                .map(data -> {
                    times(1).verify(data);
                    return data.getJournal().getEntries().iterator().next().getTimeStarted();
                })
                .reduce((last, current) -> {
                    if (current.isEqual(last) || current.isAfter(last)) {
                        return current;
                    } else {
                        throw new HoverflyVerificationException("The requests are not in the expected order.");
                    }
                });
    }
}
