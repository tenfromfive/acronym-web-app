package org.alohaspark.tron.acronymwebapp;

public class Definition {

    private final String definition;
    private final boolean verified;

    public Definition (String definition, boolean verified) {
        this.definition = definition;
        this.verified = verified;
    }

    public String getDefinition() {
        return definition;
    }

    public boolean isVerified() {
        return verified;
    }

}
