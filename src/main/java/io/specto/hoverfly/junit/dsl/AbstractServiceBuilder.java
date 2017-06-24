package io.specto.hoverfly.junit.dsl;

import io.specto.hoverfly.junit.core.model.FieldMatcher;
import io.specto.hoverfly.junit.dsl.matchers.PlainTextFieldMatcher;

import static io.specto.hoverfly.junit.core.model.FieldMatcher.exactlyMatches;
import static io.specto.hoverfly.junit.dsl.AbstractServiceBuilder.HttpMethod.*;
import static io.specto.hoverfly.junit.dsl.AbstractServiceBuilder.HttpMethod.PATCH;
import static io.specto.hoverfly.junit.dsl.matchers.HoverflyMatchers.equalsTo;

public abstract class AbstractServiceBuilder {
    private static final String SEPARATOR = "://";
    protected final FieldMatcher destination;
    protected FieldMatcher scheme;


    /**
     * Instantiates builder for a given base URL
     *
     * @param baseUrl the base URL of the service you are going to simulate
     */
    AbstractServiceBuilder(String baseUrl) {

        String[] elements = baseUrl.split(SEPARATOR);
        if (baseUrl.contains(SEPARATOR)) {
            this.scheme = exactlyMatches(elements[0]);
            this.destination = exactlyMatches(elements[1]);
        } else {
            this.destination = exactlyMatches(elements[0]);
        }

    }

    AbstractServiceBuilder(PlainTextFieldMatcher matcher) {
        this.destination = matcher.getFieldMatcher();
    }

    /**
     * Creating a GET request matcher
     *
     * @param path the path you want the matcher to have
     * @return the {@link RequestMatcherBuilder} for further customizations
     */
    public RequestMatcherBuilder get(final String path) {
        return get(equalsTo(path));
    }


    public RequestMatcherBuilder get(final PlainTextFieldMatcher path) {
        return createRequestMatcherBuilder(GET, path);
    }

    /**
     * Creating a DELETE request matcher
     *
     * @param path the path you want the matcher to have
     * @return the {@link RequestMatcherBuilder} for further customizations
     */
    public RequestMatcherBuilder delete(final String path) {
        return delete(equalsTo(path));
    }

    public RequestMatcherBuilder delete(PlainTextFieldMatcher path) {
        return createRequestMatcherBuilder(DELETE, path);
    }

    /**
     * Creating a PUT request matcher
     *
     * @param path the path you want the matcher to have
     * @return the {@link RequestMatcherBuilder} for further customizations
     */
    public RequestMatcherBuilder put(final String path) {
        return put(equalsTo(path));
    }


    public RequestMatcherBuilder put(PlainTextFieldMatcher path) {
        return createRequestMatcherBuilder(PUT, path);
    }

    /**
     * Creating a POST request matcher
     *
     * @param path the path you want the matcher to have
     * @return the {@link RequestMatcherBuilder} for further customizations
     */
    public RequestMatcherBuilder post(final String path) {
        return post(equalsTo(path));
    }

    public RequestMatcherBuilder post(PlainTextFieldMatcher path) {
        return createRequestMatcherBuilder(POST, path);
    }

    /**
     * Creating a PATCH request matcher
     *
     * @param path the path you want the matcher to have
     * @return the {@link RequestMatcherBuilder} for further customizations
     */
    public RequestMatcherBuilder patch(final String path) {
        return patch(equalsTo(path));
    }

    public RequestMatcherBuilder patch(PlainTextFieldMatcher path) {
        return createRequestMatcherBuilder(PATCH, path);
    }

    public RequestMatcherBuilder anyMethod(String path) {
        return anyMethod(equalsTo(path));
    }

    public RequestMatcherBuilder anyMethod(PlainTextFieldMatcher path) {
        return createRequestMatcherBuilder(ANY, path);
    }

    protected abstract RequestMatcherBuilder createRequestMatcherBuilder(HttpMethod httpMethod, PlainTextFieldMatcher path);

    enum HttpMethod {
        GET,
        PUT,
        POST,
        DELETE,
        PATCH,
        OPTIONS,
        CONNECT,
        HEAD,
        ANY;

        FieldMatcher getFieldMatcher() {
            FieldMatcher fieldMatcher = null;
            if (this != ANY) {
                fieldMatcher = exactlyMatches(this.name());
            }
            return fieldMatcher;
        }
    }
}
