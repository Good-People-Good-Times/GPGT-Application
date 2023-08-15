package org.goodpeoplegoodtimes.exception.party;

public class PartyNotFoundException extends RuntimeException {
    public PartyNotFoundException(String message) {
        super(message);
    }
}
