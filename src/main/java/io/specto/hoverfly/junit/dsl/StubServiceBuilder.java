/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * <p>
 * Copyright 2016-2016 SpectoLabs Ltd.
 */
package io.specto.hoverfly.junit.dsl;

import io.specto.hoverfly.junit.core.model.DelaySettings;
import io.specto.hoverfly.junit.core.model.GlobalActions;
import io.specto.hoverfly.junit.core.model.Request;
import io.specto.hoverfly.junit.core.model.RequestResponsePair;
import io.specto.hoverfly.junit.dsl.matchers.PlainTextFieldMatcher;

import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * Used as part of the DSL for creating a {@link RequestResponsePair} used within a Hoverfly Simulation.  Each builder is locked to a single base URL.
 */
public class StubServiceBuilder extends AbstractServiceBuilder {

    private final Set<RequestResponsePair> requestResponsePairs = new HashSet<>();
    private final List<DelaySettings> delaySettings = new ArrayList<>();

    /**
     * Instantiates builder for a given base URL
     *
     * @param baseUrl the base URL of the service you are going to simulate
     */
    StubServiceBuilder(String baseUrl) {
        super(baseUrl);
    }

    StubServiceBuilder(PlainTextFieldMatcher matcher) {
        super(matcher);
    }

    @Override
    protected RequestMatcherBuilder createRequestMatcherBuilder(HttpMethod httpMethod, PlainTextFieldMatcher path) {
        return new RequestMatcherBuilder(this, httpMethod, scheme, destination, path);
    }

    /**
     * Used for retrieving all the requestResponsePairs that the builder contains
     *
     * @return the set of {@link RequestResponsePair}
     */
    public Set<RequestResponsePair> getRequestResponsePairs() {
        return Collections.unmodifiableSet(requestResponsePairs);
    }

    /**
     * Adds a pair to this builder.  Called by the {@link RequestMatcherBuilder#willReturn} in order for the DSL to be expressive such as:
     * <p>
     * <pre>
     *
     * pairsBuilder.method("/some/path").willReturn(created()).method("/some/other/path").willReturn(noContent())
     * <pre/>
     */
    StubServiceBuilder addRequestResponsePair(final RequestResponsePair requestResponsePair) {
        this.requestResponsePairs.add(requestResponsePair);
        return this;
    }

    /**
     * Used to create url pattenrs of {@link DelaySettings}.
     *
     * @return service destination
     */
    String getDestination() {
        return this.destination.getMatchPattern();
    }
    /**
     * Adds service wide delay settings.
     *
     * @param delay         amount of delay
     * @param delayTimeUnit time unit of delay (e.g. SECONDS)
     * @return delay settings builder
     */
    public StubServiceDelaySettingsBuilder andDelay(int delay, final TimeUnit delayTimeUnit) {
        return new StubServiceDelaySettingsBuilder(delay, delayTimeUnit, this);
    }

    /**
     * Used to initialize {@link GlobalActions}.
     *
     * @return list of {@link DelaySettings}
     */
    public List<DelaySettings> getDelaySettings() {
        return Collections.unmodifiableList(this.delaySettings);
    }

    void addDelaySetting(final DelaySettings delaySettings) {
        if (delaySettings != null) {
            this.delaySettings.add(delaySettings);
        }
    }

    StubServiceBuilder addDelaySetting(final Request request, final ResponseBuilder responseBuilder) {
        responseBuilder.addDelay().to(this).forRequest(request);
        return this;
    }
}
