package io.specto.hoverfly.junit.dsl;

import io.specto.hoverfly.junit.dsl.matchers.PlainTextFieldMatcher;

public class RequestedServiceBuilder extends AbstractServiceBuilder {

    RequestedServiceBuilder(String baseUrl) {
        super(baseUrl);
    }

    RequestedServiceBuilder(PlainTextFieldMatcher matcher) {
        super(matcher);
    }

    @Override
    protected RequestMatcherBuilder createRequestMatcherBuilder(HttpMethod httpMethod, PlainTextFieldMatcher path) {
        return new RequestMatcherBuilder(httpMethod, scheme, destination, path);
    }


}
