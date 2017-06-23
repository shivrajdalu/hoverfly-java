package io.specto.hoverfly.junit.verification;

public class HoverflyVerifications {

    private HoverflyVerifications() {
    }

    public static VerificationCriteria times(int expectedNumberOfRequests) {
        return data -> {
            int actualNumberOfRequests = data.getJournal().getEntries().size();
            if (actualNumberOfRequests != expectedNumberOfRequests) {
                throw new HoverflyVerificationException(String.format("%d requests are expected, but only recorded %d requests", expectedNumberOfRequests, actualNumberOfRequests));
            }
        };
    }
}
