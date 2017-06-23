package io.specto.hoverfly.junit.verification;

@FunctionalInterface
public interface VerificationCriteria {

    void verify(VerificationData data);


}
