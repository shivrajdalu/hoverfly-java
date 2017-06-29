package io.specto.hoverfly.junit.verification;

import io.specto.hoverfly.junit.core.model.Request;

@FunctionalInterface
public interface VerificationCriteria {

    void verify(Request request, VerificationData data);


}
