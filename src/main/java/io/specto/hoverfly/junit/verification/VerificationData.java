package io.specto.hoverfly.junit.verification;

import io.specto.hoverfly.junit.core.model.Journal;

public class VerificationData {

    private Journal journal;

    public VerificationData() {
    }

    public VerificationData(Journal journal) {
        this.journal = journal;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }
}
