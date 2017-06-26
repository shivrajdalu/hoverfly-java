package io.specto.hoverfly.junit.verification;

import org.apache.commons.lang3.StringUtils;

public class HoverflyVerifications {

    private HoverflyVerifications() {
    }

    public static VerificationCriteria times(int expectedNumberOfRequests) {
        return data -> {
            int actualNumberOfRequests = getActualNumberOfRequests(data);
            if (actualNumberOfRequests != expectedNumberOfRequests) {
                handleVerificationFailure(expectedNumberOfRequests, actualNumberOfRequests);
            }
        };
    }

    public static VerificationCriteria never() {
        return times(0);
    }

    public static VerificationCriteria atLeast(int expectedNumberOfRequests) {
        return data -> {
            int actualNumberOfRequests = getActualNumberOfRequests(data);
            if (actualNumberOfRequests < expectedNumberOfRequests) {
                handleVerificationFailure(expectedNumberOfRequests, actualNumberOfRequests, "at least");
            }
        };
    }

    public static VerificationCriteria atMost(int expectedNumberOfRequests) {
        return data -> {
            int actualNumberOfRequests = getActualNumberOfRequests(data);
            if (actualNumberOfRequests > expectedNumberOfRequests) {
                handleVerificationFailure(expectedNumberOfRequests, actualNumberOfRequests, "at most");
            }
        };
    }

    public static VerificationCriteria atLeastOnce() {
        return atLeast(1);
    }

    private static int getActualNumberOfRequests(VerificationData data) {
        if (data == null || data.getJournal() == null || data.getJournal().getEntries() == null) {
            throw new HoverflyVerificationException("Failed to get journal for verification.");
        }
        return data.getJournal().getEntries().size();
    }


    private static void handleVerificationFailure(int expectedNumberOfRequests, int actualNumberOfRequests) {
        handleVerificationFailure(expectedNumberOfRequests, actualNumberOfRequests, "");
    }

    private static void handleVerificationFailure(int expectedNumberOfRequests, int actualNumberOfRequests, String description) {

        StringBuilder sb = new StringBuilder();

        if (expectedNumberOfRequests == 0) {
            sb.append("Not expected any request, ");
        } else {
            sb.append("Expected ");

            if (StringUtils.isNotBlank(description)) {
                sb.append(description).append(" ");
            }

            sb.append(expectedNumberOfRequests).append(" ");

            if (expectedNumberOfRequests > 1) {
                sb.append("requests, ");
            } else {
                sb.append("request, ");
            }
        }

        sb.append("but actual number of requests is ").append(actualNumberOfRequests).append(".");

        throw new HoverflyVerificationException(sb.toString());
    }
}
